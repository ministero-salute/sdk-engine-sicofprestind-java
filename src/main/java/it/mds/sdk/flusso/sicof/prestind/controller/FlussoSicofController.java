/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.sicof.prestind.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.mds.sdk.engine.Engine;
import it.mds.sdk.engine.mapper.EngineMapper;
import it.mds.sdk.flusso.sicof.prestind.dto.RecordRequest;
import it.mds.sdk.flusso.sicof.prestind.dto.RecordValidationResponse;
import it.mds.sdk.flusso.sicof.prestind.dto.ValidationResult;
import it.mds.sdk.flusso.sicof.prestind.dto.ValidationStartResultResponse;
import it.mds.sdk.flusso.sicof.prestind.mapper.CampiInputMapper;
import it.mds.sdk.flusso.sicof.prestind.mapper.RecordDtoMapper;
import it.mds.sdk.model.CampiInputBean;
import it.mds.sdk.model.FlussoRequest;
import it.mds.sdk.run.InfoRun;
import it.mds.sdk.run.RunTracker;
import it.mds.sdk.validation.exception.RecordValidationException;
import it.mds.sdk.validation.validator.ValidationEngineBuilder;
import lombok.extern.slf4j.Slf4j;

@RestController
@Configuration
@EnableAsync
@Slf4j
public class FlussoSicofController {

	private final HttpHeaders headers;
	
    @Autowired
    private Engine engine;
	
    @Autowired
    private RunTracker runTracker;
	
    @Autowired
    private ValidationEngineBuilder validationEngineBuilder;
    
    @Value("${flusso.categoria}")
    private String categoriaFlusso;

    @Value("${flusso.codifica}")
    private String codificaFlusso;
	
    public FlussoSicofController() {

    	headers = new HttpHeaders();
    	headers.add("X-Content-Type-Options", "nosniff");
        headers.add("X-Frame-Options", "DENY");
        headers.add("X-XSS-Protection", "1; mode=block");
        headers.add("Content-Security-Policy", "default-src 'self'");

    }
    
    @PostMapping(path = "v1/flusso/{nomeFlusso}")
    public ResponseEntity<ValidationStartResultResponse> validaTracciato(@RequestBody FlussoRequest flusso, 
    																	@PathVariable String nomeFlusso) {
		ValidationStartResultResponse result;
		try {
			
			CampiInputBean datiFLusso = new CampiInputBean();
	        datiFLusso.setPeriodoRiferimentoInput(flusso.getPeriodoRiferimento());
	        datiFLusso.setAnnoRiferimentoInput(flusso.getAnnoRiferimento());
	        datiFLusso.setCodiceRegioneInput(flusso.getCodiceRegione());
	        datiFLusso.setIdClient(flusso.getIdClient());
	        datiFLusso.setNomeFile(flusso.getNomeFile());
	        datiFLusso.setModalitaOperativa(flusso.getModalitaOperativa());
	        datiFLusso.setCategoriaFlusso(categoriaFlusso);
	        datiFLusso.setCodificaFlusso(codificaFlusso);
	        datiFLusso.setIdRun(runTracker.createRunInfo(datiFLusso));
        	engine.process(datiFLusso);
        	result = new ValidationStartResultResponse(true, null, datiFLusso.getIdRun(), flusso.getIdClient());
        	return ResponseEntity.ok(result);
        	
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			result = new ValidationStartResultResponse(false, e.getMessage(), null, flusso.getIdClient());
            return ResponseEntity.internalServerError().body(result);
		}
	
	}

    @PostMapping("v1/flusso/{nomeFlusso}/record")
    public ResponseEntity<RecordValidationResponse> validaRecord(@PathVariable String nomeFlusso,
    																@RequestBody RecordRequest recordRequest) {
    	
    	CampiInputBean datiFLusso = CampiInputMapper.INSTANCE.map(recordRequest);
        datiFLusso.setIdRun(runTracker.createRunInfo(datiFLusso));
        RecordValidationResponse result;

        try {
            
        	validationEngineBuilder.build(datiFLusso)
            	.validate(RecordDtoMapper.INSTANCE.map(recordRequest));
        	result = new RecordValidationResponse(true, null, datiFLusso.getIdRun(), recordRequest.getIdClient());
        	
        }catch (RecordValidationException rve) {
        	ValidationResult esiti = new ValidationResult(EngineMapper.MAPPER.rveToInesito(rve).getListaEsiti());
        	result = new RecordValidationResponse(false, esiti, datiFLusso.getIdRun(), recordRequest.getIdClient());
        } 
        
        return ResponseEntity.ok()
        		.headers(headers)
        		.body(result);
    	
    }

    @GetMapping("v1/flusso/{nomeFlusso}/info")
    public ResponseEntity<InfoRun> informazioniRun(@PathVariable String nomeFlusso,
    												@RequestParam(required = false) String idRun, 
    												@RequestParam(required = false) String idClient) {
    	
    	InfoRun infoRun = null;
    	
        if (idRun != null)
            infoRun = runTracker.getInfoRun(idRun);
        else if (idClient != null)
            infoRun = null; //gestoreRunLog.getRunFromClient(idClient);
        
        if (infoRun == null)
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run non trovata");
        else
        	return ResponseEntity.ok()
        			.headers(headers)
        			.body(infoRun);
    	
    }

}

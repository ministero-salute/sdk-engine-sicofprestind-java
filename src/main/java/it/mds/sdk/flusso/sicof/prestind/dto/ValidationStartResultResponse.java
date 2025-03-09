/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.sicof.prestind.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ValidationStartResultResponse  {

    private Boolean isIniziato;
    private String descrizioneErrore;
    private String idrun;
    private String idClient;

    public ValidationStartResultResponse(@JsonProperty("idIniziato") Boolean isIniziato,
                                      @JsonProperty("descrizioneErrore") String descrizioneErrore,
                                      @JsonProperty("idRun") String idrun,
                                      @JsonProperty("idClient") String idClient) {
        this.isIniziato = isIniziato;
        this.descrizioneErrore = descrizioneErrore;
        this.idrun = idrun;
        this.idClient = idClient;
    }

}

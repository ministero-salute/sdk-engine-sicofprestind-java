/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.sicof.prestind.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import it.mds.sdk.flusso.sicof.prestind.dto.RecordRequest;
import it.mds.sdk.model.CampiInputBean;

@Mapper
public interface CampiInputMapper {

    CampiInputMapper INSTANCE = Mappers.getMapper(CampiInputMapper.class);

    @Mapping(source = "periodoRiferimento", target = "periodoRiferimentoInput")
    @Mapping(source = "annoRiferimento", target = "annoRiferimentoInput")
    @Mapping(source = "codiceRegione", target = "codiceRegioneInput")
    @Mapping(source = "idClient", target = "idClient")
    @Mapping(source = "modalitaOperativa", target = "modalitaOperativa")
    CampiInputBean map(RecordRequest source);

}

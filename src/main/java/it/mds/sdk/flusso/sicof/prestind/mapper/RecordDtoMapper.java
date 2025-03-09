/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.sicof.prestind.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import it.mds.sdk.flusso.sicof.prestind.dto.RecordRequest;
import it.mds.sdk.model.RecordDtoGenerico;

@Mapper
public interface RecordDtoMapper {

    RecordDtoMapper INSTANCE = Mappers.getMapper(RecordDtoMapper.class);

    @Mapping(source = "annoRiferimento", target = "campiInput.annoRiferimentoInput")
    @Mapping(source = "idClient", target = "campiInput.idClient")
    @Mapping(source = "periodoRiferimento", target = "campiInput.periodoRiferimentoInput")
    @Mapping(source = "codiceRegione", target = "campiInput.codiceRegioneInput")
    @Mapping(source = "modalitaOperativa", target = "campiInput.modalitaOperativa")
    @Mapping(source = "recordDto", target = "values")
    @Mapping(target = "row", expression = "java(1L)")
    RecordDtoGenerico map(RecordRequest source);

}

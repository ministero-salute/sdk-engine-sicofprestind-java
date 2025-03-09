/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.sicof.prestind.dto;

import java.util.Map;

import it.mds.sdk.model.ModalitaOperativa;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecordRequest {
	
    String idClient;
    ModalitaOperativa modalitaOperativa;
    Map<String, String> recordDto;
    String annoRiferimento;
    String periodoRiferimento;
    String codiceRegione;
    
}

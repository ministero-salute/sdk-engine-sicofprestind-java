/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.sicof.prestind.dto;

import it.mds.sdk.esiti.Esito;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult {
	
    private Collection<Esito> esiti;
    
}

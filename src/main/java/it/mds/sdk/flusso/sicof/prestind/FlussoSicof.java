/* SPDX-License-Identifier: BSD-3-Clause */

package it.mds.sdk.flusso.sicof.prestind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
//import lombok.extern.slf4j.Slf4j;

@EnableWebMvc
@SpringBootApplication
@ComponentScan({"it.mds.sdk"})
//@Slf4j
//@OpenAPIDefinition(info=@Info(title = "SDK Ministero Della Salute - Flusso SICOF", version = "0.0.5-SNAPSHOT", description = "Flusso SICOF"))
@EnableAsync
public class FlussoSicof {
	
    public static void main(String[] args) {
        SpringApplication.run(FlussoSicof.class, args);
    }
    
}

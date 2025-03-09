# sdk-engine-sicofprestind-java

## Sistema informativo Consultori Familiari PRESTAZIONI INDIVIDUALI

### Descrizione repository:

Il microservizio ha come scopo l'acquisizione dei dati inviati dalle Regioni in merito alle prestazioni consultoriali previste dall'articolo 24 <<Assistenza sociosanitaria ai minori, alle donne, alle coppie, alle famiglie>> DPCM 12 gennaio 2017 (Nuovi LEA).

I dati richiesti sono relativi al set di informazioni individuali riguardanti le attività consultoriali, a favore degli assistiti residenti e non residenti nel territorio italiano. Il tracciato SICOF_PREIND comprende le informazioni relative alle prestazioni erogate individualmente ad un assistito oppure ad un nucleo\piccolo gruppo.

Il Sistema viene alimentato con le informazioni relative all'attività dei Consultori Familiari a partire dai dati attinenti al secondo semestre 2023 con cadenza SEMESTRALE

L'invio dei file viene effettuato mediante un tracciato XML.
Per ogni tracciato XML, è fornito il relativo schema XSD di convalida a cui far riferimento

#### Struttura XML:

Campo tecnico Trasmissione:
-Tipo

Evento:
- Accesso

Nodi di riferimento Accesso:
- Erogazione
- Accesso
- Valutazione
- Diagnosi
- Prestazione

Campi Erogazione:
- CodRegioneErg     (campo chiave)
- CodASLErg         (campo chiave)
- CodConsultorio    (campo chiave)

Campi Accesso: 
- Data              (campo chiave)
- IdAccesso         (campo chiave)
- IdNucleo          (campo chiave)
- IdCiclo
- MandatoGdz

Campi Valutazione
- Diagnosi
- SupportoSociale

Campi Prestazione:
- ProgrPres         (campo chiave)
- AreaAttivita
- SottoareaAttivita
- TipoPrestazione
- NumeroIncontriPrevisti
- DataPrenotazione
- DataInizioCicloPrestazioni
- DataPresuntaFinePrestazioni
- SedePrestazione
- MediazioneCulturale
- PrestazioneMultiprofessionale
- PersonaleCoinvolto_1
- PersonaleCoinvolto_2
- PersonaleCoinvolto_3
- PersonaleCoinvolto_4
- TerziCoinvolti_1
- TerziCoinvolti_2
- TerziCoinvolti_3

XSD:
```
<?xml version="1.0"?>
<xs:schema xmlns:fls="http://flussi.mds.it/FlsSicof_2" xmlns:xs="http://www.w3.org/2001/XMLSchema" targetNamespace="http://flussi.mds.it/FlsSicof_2" elementFormDefault="qualified" attributeFormDefault="unqualified">
	<xs:simpleType name="TipoTrasmissione">
		<xs:restriction base="xs:string">
			<xs:pattern value="[IVC]{1}"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="IdAccesso">
		<xs:restriction base="xs:int">
			<xs:maxInclusive value="99"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="IdNucleo">
		<xs:restriction base="xs:int">
			<xs:maxInclusive value="9999999"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="RegioneErogazione">
		<xs:restriction base="xs:string">
			<xs:pattern value="010"/>
			<xs:pattern value="020"/>
			<xs:pattern value="030"/>
			<xs:pattern value="041"/>
			<xs:pattern value="042"/>
			<xs:pattern value="050"/>
			<xs:pattern value="060"/>
			<xs:pattern value="070"/>
			<xs:pattern value="080"/>
			<xs:pattern value="090"/>
			<xs:pattern value="100"/>
			<xs:pattern value="110"/>
			<xs:pattern value="120"/>
			<xs:pattern value="130"/>
			<xs:pattern value="140"/>
			<xs:pattern value="150"/>
			<xs:pattern value="160"/>
			<xs:pattern value="170"/>
			<xs:pattern value="180"/>
			<xs:pattern value="190"/>
			<xs:pattern value="200"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="CodASLErogazione">
		<xs:restriction base="xs:string">
			<xs:length value="3"/>
			<xs:pattern value="[0-9]{3}"/>
			<xs:pattern value="999"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="CodiceConsultorioErogazione">
		<xs:restriction base="xs:string">
			<xs:length value="6"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="StatoEstero">
		<xs:restriction base="xs:string">
			<xs:pattern value="[A-Za-z]{2}"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="IdCiclo">
		<xs:restriction base="xs:positiveInteger">
			<xs:maxInclusive value="9999999"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="DiagnosiRilevata">
		<xs:restriction base="xs:string">
			<xs:maxLength value="6"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="SupportoSociale">
		<xs:restriction base="xs:positiveInteger">
			<xs:pattern value="[1-3]{1}"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="MandatoUnitaGiudz">
		<xs:restriction base="xs:positiveInteger">
			<xs:pattern value="[1-2]{1}"/>
			<xs:pattern value="9"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="ProgPrest">
		<xs:restriction base="xs:positiveInteger">
			<xs:maxInclusive value="99"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="AreaAtt">
		<xs:restriction base="xs:string">
			<xs:length value="1"/>
			<xs:pattern value="[a-fA-F]{1}"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="SottoAreaAtt">
		<xs:restriction base="xs:string">
			<xs:length value="4"/>
			<xs:pattern value="9999"/>
			<xs:pattern value="[a-fA-F]{1}.[0-1]{1}[0-9]{1}"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="TipoPres">
		<xs:restriction base="xs:string">
			<xs:maxLength value="3"/>
			<xs:pattern value="999"/>
			<xs:pattern value="[0-9]{3}"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="NumIncontriPrev">
		<xs:restriction base="xs:positiveInteger">
			<xs:maxInclusive value="999"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="DataPrenotazione">
		<xs:restriction base="xs:date"/>
	</xs:simpleType>
	<xs:simpleType name="DataIniPres">
		<xs:restriction base="xs:date"/>
	</xs:simpleType>
	<xs:simpleType name="DataPresuntaFinePres">
		<xs:restriction base="xs:date"/>
	</xs:simpleType>
	<xs:simpleType name="SedePres">
		<xs:restriction base="xs:positiveInteger">
			<xs:pattern value="[1-5]{1}"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="MediazioneCulturale">
		<xs:restriction base="xs:positiveInteger">
			<xs:pattern value="[1-2]{1}"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="PresMultiProfessionale">
		<xs:restriction base="xs:positiveInteger">
			<xs:pattern value="[1-2]{1}"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="PersonaleCoinvolto_1">
		<xs:restriction base="xs:positiveInteger">
			<xs:pattern value="[1-8]{1}"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="PersonaleCoinvolto_2">
		<xs:restriction base="xs:positiveInteger">
			<xs:pattern value="[1-9]{1}"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="PersonaleCoinvolto_3">
		<xs:restriction base="xs:positiveInteger">
			<xs:pattern value="[1-9]{1}"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="PersonaleCoinvolto_4">
		<xs:restriction base="xs:positiveInteger">
			<xs:pattern value="[1-9]{1}"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="TerziCoinvolti_1">
		<xs:restriction base="xs:positiveInteger">
			<xs:pattern value="[1-9]{1}"/>
			<xs:pattern value="10"/>
			<xs:pattern value="11"/>
			<xs:pattern value="12"/>
			<xs:pattern value="13"/>
			<xs:pattern value="14"/>
			<xs:pattern value="15"/>
			<xs:pattern value="99"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="TerziCoinvolti_2">
		<xs:restriction base="xs:positiveInteger">
			<xs:pattern value="[1-9]{1}"/>
			<xs:pattern value="10"/>
			<xs:pattern value="11"/>
			<xs:pattern value="12"/>
			<xs:pattern value="13"/>
			<xs:pattern value="14"/>
			<xs:pattern value="15"/>
			<xs:pattern value="99"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="TerziCoinvolti_3">
		<xs:restriction base="xs:positiveInteger">
			<xs:pattern value="[1-9]{1}"/>
			<xs:pattern value="10"/>
			<xs:pattern value="11"/>
			<xs:pattern value="12"/>
			<xs:pattern value="13"/>
			<xs:pattern value="14"/>
			<xs:pattern value="15"/>
			<xs:pattern value="99"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="Data_F">
		<xs:restriction base="xs:string">
			<xs:length value="10"/>
			<xs:pattern value="(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(19|20)\d\d(19|20)\d\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])"/>
			<xs:pattern value="9999999999"/>
			<xs:pattern value="8888888888"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:simpleType name="Data_F2">
		<xs:restriction base="xs:string">
			<xs:pattern value="(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(19|20)\d\d(19|20)\d\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])"/>
		</xs:restriction>
	</xs:simpleType>
	<xs:element name="Trasmissione">
		<xs:complexType>
			<xs:attribute name="Tipo" type="fls:TipoTrasmissione" use="required"/>
		</xs:complexType>
	</xs:element>
	<xs:element name="Erogazione">
		<xs:complexType>
			<xs:sequence>
				<xs:element name="CodRegioneErg" type="fls:RegioneErogazione"/>
				<xs:element name="CodASLErg" type="fls:CodASLErogazione"/>
				<xs:element name="CodConsultorio" type="fls:CodiceConsultorioErogazione"/>
			</xs:sequence>
		</xs:complexType>
	</xs:element>
	<xs:element name="FlsSicof_2">
		<xs:complexType>
			<xs:sequence>
				<xs:element ref="fls:Accesso" minOccurs="1" maxOccurs="unbounded"/>
			</xs:sequence>
		</xs:complexType>
	</xs:element>
	<xs:element name="Accesso">
		<xs:complexType>
			<xs:sequence>
				<xs:element ref="fls:Trasmissione"/>
				<xs:element ref="fls:Erogazione"/>
				<xs:element ref="fls:Valutazione" minOccurs="0"/>
				<xs:element ref="fls:Prestazione" minOccurs="1" maxOccurs="unbounded"/>
			</xs:sequence>
			<xs:attribute name="Data" type="fls:Data_F2" use="required"/>
			<xs:attribute name="IdAccesso" type="fls:IdAccesso" use="optional"/>
			<xs:attribute name="IdNucleo" type="fls:IdNucleo" use="optional"/>
			<xs:attribute name="IdCiclo" type="fls:IdCiclo"/>
			<xs:attribute name="MandatoGdz" type="fls:MandatoUnitaGiudz" use="required"/>
		</xs:complexType>
	</xs:element>
	<xs:element name="Valutazione" nillable="false">
		<xs:complexType>
			<xs:attribute name="Diagnosi" type="fls:DiagnosiRilevata"/>
			<xs:attribute name="SupportoSociale" type="fls:SupportoSociale"/>
		</xs:complexType>
	</xs:element>
	<xs:element name="Prestazione">
		<xs:complexType>
			<xs:attribute name="AreaAttivita" type="fls:AreaAtt" use="required"/>
			<xs:attribute name="SottoAreaAttivita" type="fls:SottoAreaAtt" use="required"/>
			<xs:attribute name="TipoPrestazione" type="fls:TipoPres" use="required"/>
			<xs:attribute name="NumeroIncontriPrevisti" type="fls:NumIncontriPrev" use="required"/>
			<xs:attribute name="DataPrenotazione" type="fls:Data_F" use="required"/>
			<xs:attribute name="DataInizioCicloPrestazioni" type="fls:Data_F" use="required"/>
			<xs:attribute name="DataPresuntaFinePrestazioni" type="fls:Data_F" use="required"/>
			<xs:attribute name="SedePrestazione" type="fls:SedePres" use="required"/>
			<xs:attribute name="MediazioneCulturale" type="fls:MediazioneCulturale"/>
			<xs:attribute name="PrestazioneMultiProfessionale" type="fls:PresMultiProfessionale" use="required"/>
			<xs:attribute name="PersonaleCoinvolto_1" type="fls:PersonaleCoinvolto_1" use="required"/>
			<xs:attribute name="PersonaleCoinvolto_2" type="fls:PersonaleCoinvolto_2"/>
			<xs:attribute name="PersonaleCoinvolto_3" type="fls:PersonaleCoinvolto_3"/>
			<xs:attribute name="PersonaleCoinvolto_4" type="fls:PersonaleCoinvolto_4"/>
			<xs:attribute name="TerziCoinvolti_1" type="fls:TerziCoinvolti_1" use="required"/>
			<xs:attribute name="TerziCoinvolti_2" type="fls:TerziCoinvolti_2" use="required"/>
			<xs:attribute name="TerziCoinvolti_3" type="fls:TerziCoinvolti_3" use="required"/>
			<xs:attribute name="ProgressivoPrestazione" type="fls:ProgPrest" use="required"/>
		</xs:complexType>
	</xs:element>
</xs:schema>
```


### Struttura repository

La cartella principale è src/main/java che è organizzata nelle seguenti cartelle:

- it.mds.sdk.engine.config che raccoglie i file di configurazione
- it.mds.sdk.flusso.sicof.prestind che contiene il file necessario all'avvio dell'applicazione
- it.mds.sdk.flusso.sicof.prestind.controller che contiene i file che vengono invocati direttamente dal client
- it.mds.sdk.flusso.sicof.prestind.dto che contiene le classi che rappresentano il modello interno di sdk
- it.mds.sdk.flusso.sicof.prestind.mapper che contiene le classi di conversione

Nella cartella src/main/resources sono presenti le seguenti cartelle:
- META-INF che contiene file di configurazione
- sicof xhe contiene file .moustache
- template contiene un file di regole e file .csv, .xml e .xsd

sono presenti in resources anche file di configurazione:
- application.yaml
- config-flusso.properties
- csvHeaderMapping.properties
- logback-spring.xml
- tmp.xml

è presente il file pom.xml (Project Object Model), è un file XML che contiene le informazioni sul progetto e dettagli sulle configurazioni utilizzate da Maven per eseguire la build del progetto

### Prerequisiti e dipendenze

Il microservizio può girare su qualsiasi sistema operativo per cui è prevista una JVM (Java Virtual Machine) e utilizza la versione 2.7.17 di Spring Boot.

### Istruzioni per l'installazione

Per poter eseguire la build: 
build system maven,
comando mvn clean package

Per eseguire l'installazione è necessario prendere il jar generato dal build system e copiarlo in una cartella, successivamente all'interno della root folder ( / su Linux mentre C:\ su Windows) creare la cartella sdk e tutte le sottocartelle e i file necessari:
```
 /
 +- sdk
   +- db - la directory in cui viene genrato il db sqllite per la storicizzazione dele anagrafiche
   +- dir - la directory in cui inserire i csv da validare e inviare
   +- esiti - la directory in cui verranno depositati i file con gli esiti delle esecuzioni dell'sdk
   +- log - la directory in cui verranno scritti i log delll'applicativo
   +- progressivo - la directory in cui l'applicativo mantiene un file dat per la generazione degli id di esecuzione
   +- properties - la directory contenente i file di configurazione dell'sdk
      +- config-flusso.properties - file di configurazione principale (da referenziare in fase di avvio)  
      \- configurazioni-anagrafiche.properties - file per la configurazione del recupero delle anagrafiche 
   +- regole - la directory contenente le regole da applicare ai csv
   +- run - la cartella contenente i file di run per ogni singola esecuzione
   +- xmloutput - la directory contenente gli xml di output
   \- sent - la directory in cui vengono spostati gli xml inviati al ministero
``` 
- #### config-flusso.properties
```
nome.flusso=<nome del flusso lato ministero>
flusso.categoria=<categoria del flusso lato ministero>
flusso.codifica=<codice identificativo del flusso lato ministero>
regole.percorso=<path completo al file xml delle regole: /sdk/regole/regole.xml>

xmloutput.percorso=<path completo della folder in cui scrivere l'output>/SDK_{{periodoRiferimento}}_{{idRun}}.xml (/sdk/xmloutput/SDK_{{periodoRiferimento}}_{{idRun}}.xml)

sent.percorso=<path completo della directory in cui spostare gli xml inviati al ministero: /sdk/sent/>
flusso.percorso=<path completo della directory in cui verranno depositati i file csv: /sdk/dir/>
soglia.invio.mds=<numero intero indicante quanti la soglia di record validi per file affinché possa essere inviato l'xml di output: 100>
separatore=<carattere di separazione dei valori nel csv: ~>

run.percorso=<path completo della directory in storicizzare i file di run: /sdk/run>
esito.percorso=<path completo della directory in cui depositare i file di esito: /sdk/esiti>
progressivo.percorso=<path completo della directory in cui generare il file dat dei progressivi: /sdk/progressivo>

url.rest.gaf=<url per l'invio degli xml: https://api.salute.gov.it/api/gaf/upldfunc>
gaf.sender.authorizer.token-issuer.url=<url per l'autenticazione dell'invio: https://nsis-ids.sanita.it/nidp/oauth/nam/token>
gaf.sender.authorizer.token-issuer.grant_type=<flow di autenticazione: verrà fornita dal ministero>
gaf.sender.authorizer.token-issuer.username=<username: verrà fornita dal ministero>
gaf.sender.authorizer.token-issuer.password=<password: verrà fornita dal ministero>
gaf.sender.authorizer.token-issuer.client_id=<client id: verrà fornita dal ministero>
gaf.sender.authorizer.token-issuer.client_secret=<client secret: verrà fornita dal ministero>
gaf.sender.authorizer.token-issuer.scope=<scopes a cui deve appartenere l'utente: verranno forniti dal ministero>
gaf.sender.authorizer.type=<tipo di token di autenticazione: JWT>
gaf.sender.type=REST
gaf.sender.fileType=<categoria del flusso: valorizzare come flusso.categoria>
```

- #### configurazioni-anagrafiche.properties
```
sqlite.database.file.path=<path completo in cui creare il db sqllite: /sdk/db/anagrafica.db>
resilienza.ore=<time to live delle anagrafiche nel DB in ore: 2>

client.rest.headers.x-pplication-id: APP_ID_REGISTRYDOWNLOADER_CLIENT
client.type=REST
client.host=<url del gestore anagrafi: https://api.salute.gov.it/api/gestanag/v1>

rest.authorizer.type=<tipo del token di autorizzazione/autenticazione: JWT>
rest.authorizer.token-issuer.url=<url per la generazione di un token di autenticazione: https://nsis-ids.sanita.it/nidp/oauth/nam/token>
rest.authorizer.token-issuer.grant_type=<tipo di flow da utilizzare per l'autenticazione: client_credentials>
rest.authorizer.token-issuer.username=<username: verrà fornita dal ministero>
rest.authorizer.token-issuer.password=<password: verrà fornita dal ministero>
rest.authorizer.token-issuer.client_id=<client id: verrà fornita dal ministero>
rest.authorizer.token-issuer.client_secret=<client secret: verrà fornita dal ministero>
rest.authorizer.token-issuer.scope=<scopes a cui deve appartenere l'utenza: verrà fornita dal ministero>
```

### Istruzioni di avvio

Per avviare l'applicativo eseguire il seguente comando
```
java -jar <path completo del jar> --config=<path completo al file di configurazione principale>
```
Il comando farà partire il software che rimarrà in attesa di richieste sulla porta 8080

#### Avvio validazione di un csv
```
curl --location --request POST 'http://localhost:8080/v1/flusso/SICOF_PRESTIND' \
--header 'Content-Type: application/json' \
--header 'Accept: */*' \
--data-raw '{
    "nomeFile": "test.csv",
    "idClient": "1663" ,
    "modalitaOperativa":"P",
    "annoRiferimento": "2022",
    "periodoRiferimento": "13",
    "codiceRegione": "120"
}'
```

#### Recupero dell'esito di un'elaborazione
```
curl --location --request GET 'http://localhost:8080/v1/flusso/SICOF_PRESTIND/info?idRun=34' --header 'Accept: */*'
```

### Detentori di copyright

### Incaricati del mantenimento del progetto open source

### Contatti per segnalazioni

## mantainer:
Accenture SpA until January 2026


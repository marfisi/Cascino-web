//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.11.19 alle 09:25:29 AM CET 
//


package it.cascino.idrolab.model.ws1_19;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "proCodice",
    "proRagionesociale",
    "proCodiceB2BAngaisa",
    "marCodice",
    "marDescrizione",
    "marCodiceAngaisa",
    "artCodiceproduttore",
    "artCodiceabarre",
    "artCodice",
    "artDescrizioneridotta",
    "artDescrizioneestesa",
    "artUm",
    "artMinacq",
    "artUmConf",
    "artConf",
    "artArticoloConf",
    "artUmConfIi",
    "artConfIi",
    "artArticoloConfIi",
    "artUmConfIii",
    "artConfIii",
    "artArticoloConfIii",
    "lisPrezzoeuro1",
    "ivaCodice",
    "setCodice",
    "setDescrizione",
    "macCodice",
    "macDescrizione",
    "famCodice",
    "famDescrizione",
    "artCatsc",
    "artModello",
    "artDimensioni",
    "artColore",
    "artPeso",
    "artCaratteristiche",
    "artMateriale",
    "artFuorilistinocartaceo",
    "artGaranzia",
    "artRicambio",
    "artStato",
    "lisDatalistino",
    "lisDatavalidita",
    "artDisegnotecnico",
    "artImmaginegrande",
    "artTipoprezzo",
    "lisPrezzoeuroprec",
    "lisDatavaliditaprec",
    "lcaCodice",
    "lcaDescrizione",
    "lcaCodicelistinoproduttore",
    "lcrRevisione",
    "lcrDescrizione",
    "lcrData",
    "lcrStatus",
    "lciSezione",
    "lciDescrizionesezione",
    "lciCodicesezioneprod",
    "lciCapitolo",
    "lciDescrizionecapitolo",
    "lciCodicecapitoloprod",
    "linCodice",
    "linDescrizione",
    "smaCodice",
    "artScoaum1",
    "artScoaum2",
    "artScoaum3",
    "artScoaum4",
    "artScoaum5",
    "fmpCodice",
    "fmpDescrizione"
})
@XmlRootElement(name = "articolo")
public class Articolo {

    @XmlElement(name = "pro_codice", required = true)
    protected String proCodice;
    @XmlElement(name = "pro_ragionesociale", required = true)
    protected String proRagionesociale;
    @XmlElement(name = "pro_codice_b2b_angaisa", required = true)
    protected String proCodiceB2BAngaisa;
    @XmlElement(name = "mar_codice", required = true)
    protected String marCodice;
    @XmlElement(name = "mar_descrizione", required = true)
    protected String marDescrizione;
    @XmlElement(name = "mar_codice_angaisa", required = true)
    protected String marCodiceAngaisa;
    @XmlElement(name = "art_codiceproduttore", required = true)
    protected String artCodiceproduttore;
    @XmlElement(name = "art_codiceabarre", required = true)
    protected String artCodiceabarre;
    @XmlElement(name = "art_codice", required = true)
    protected String artCodice;
    @XmlElement(name = "art_descrizioneridotta", required = true)
    protected String artDescrizioneridotta;
    @XmlElement(name = "art_descrizioneestesa", required = true)
    protected String artDescrizioneestesa;
    @XmlElement(name = "art_um", required = true)
    protected String artUm;
    @XmlElement(name = "art_minacq", required = true)
    protected String artMinacq;
    @XmlElement(name = "art_um_conf", required = true)
    protected String artUmConf;
    @XmlElement(name = "art_conf", required = true)
    protected String artConf;
    @XmlElement(name = "art_articolo_conf", required = true)
    protected String artArticoloConf;
    @XmlElement(name = "art_um_conf_ii", required = true)
    protected String artUmConfIi;
    @XmlElement(name = "art_conf_ii", required = true)
    protected String artConfIi;
    @XmlElement(name = "art_articolo_conf_ii", required = true)
    protected String artArticoloConfIi;
    @XmlElement(name = "art_um_conf_iii", required = true)
    protected String artUmConfIii;
    @XmlElement(name = "art_conf_iii", required = true)
    protected String artConfIii;
    @XmlElement(name = "art_articolo_conf_iii", required = true)
    protected String artArticoloConfIii;
    @XmlElement(name = "lis_prezzoeuro1", required = true)
    protected String lisPrezzoeuro1;
    @XmlElement(name = "iva_codice", required = true)
    protected String ivaCodice;
    @XmlElement(name = "set_codice", required = true)
    protected String setCodice;
    @XmlElement(name = "set_descrizione", required = true)
    protected String setDescrizione;
    @XmlElement(name = "mac_codice", required = true)
    protected String macCodice;
    @XmlElement(name = "mac_descrizione", required = true)
    protected String macDescrizione;
    @XmlElement(name = "fam_codice", required = true)
    protected String famCodice;
    @XmlElement(name = "fam_descrizione", required = true)
    protected String famDescrizione;
    @XmlElement(name = "art_catsc", required = true)
    protected String artCatsc;
    @XmlElement(name = "art_modello", required = true)
    protected String artModello;
    @XmlElement(name = "art_dimensioni", required = true)
    protected String artDimensioni;
    @XmlElement(name = "art_colore", required = true)
    protected String artColore;
    @XmlElement(name = "art_peso", required = true)
    protected String artPeso;
    @XmlElement(name = "art_caratteristiche", required = true)
    protected String artCaratteristiche;
    @XmlElement(name = "art_materiale", required = true)
    protected String artMateriale;
    @XmlElement(name = "art_fuorilistinocartaceo", required = true)
    protected String artFuorilistinocartaceo;
    @XmlElement(name = "art_garanzia", required = true)
    protected String artGaranzia;
    @XmlElement(name = "art_ricambio", required = true)
    protected String artRicambio;
    @XmlElement(name = "art_stato", required = true)
    protected String artStato;
    @XmlElement(name = "lis_datalistino", required = true)
    protected String lisDatalistino;
    @XmlElement(name = "lis_datavalidita", required = true)
    protected String lisDatavalidita;
    @XmlElement(name = "art_disegnotecnico", required = true)
    protected String artDisegnotecnico;
    @XmlElement(name = "art_immaginegrande", required = true)
    protected String artImmaginegrande;
    @XmlElement(name = "art_tipoprezzo", required = true)
    protected String artTipoprezzo;
    @XmlElement(name = "lis_prezzoeuroprec", required = true)
    protected String lisPrezzoeuroprec;
    @XmlElement(name = "lis_datavaliditaprec", required = true)
    protected String lisDatavaliditaprec;
    @XmlElement(name = "lca_codice", required = true)
    protected String lcaCodice;
    @XmlElement(name = "lca_descrizione", required = true)
    protected String lcaDescrizione;
    @XmlElement(name = "lca_codicelistinoproduttore", required = true)
    protected String lcaCodicelistinoproduttore;
    @XmlElement(name = "lcr_revisione", required = true)
    protected String lcrRevisione;
    @XmlElement(name = "lcr_descrizione", required = true)
    protected String lcrDescrizione;
    @XmlElement(name = "lcr_data", required = true)
    protected String lcrData;
    @XmlElement(name = "lcr_status", required = true)
    protected String lcrStatus;
    @XmlElement(name = "lci_sezione", required = true)
    protected String lciSezione;
    @XmlElement(name = "lci_descrizionesezione", required = true)
    protected String lciDescrizionesezione;
    @XmlElement(name = "lci_codicesezioneprod", required = true)
    protected String lciCodicesezioneprod;
    @XmlElement(name = "lci_capitolo", required = true)
    protected String lciCapitolo;
    @XmlElement(name = "lci_descrizionecapitolo", required = true)
    protected String lciDescrizionecapitolo;
    @XmlElement(name = "lci_codicecapitoloprod", required = true)
    protected String lciCodicecapitoloprod;
    @XmlElement(name = "lin_codice", required = true)
    protected String linCodice;
    @XmlElement(name = "lin_descrizione", required = true)
    protected String linDescrizione;
    @XmlElement(name = "sma_codice", required = true)
    protected String smaCodice;
    @XmlElement(name = "art_scoaum1", required = true)
    protected String artScoaum1;
    @XmlElement(name = "art_scoaum2", required = true)
    protected String artScoaum2;
    @XmlElement(name = "art_scoaum3", required = true)
    protected String artScoaum3;
    @XmlElement(name = "art_scoaum4", required = true)
    protected String artScoaum4;
    @XmlElement(name = "art_scoaum5", required = true)
    protected String artScoaum5;
    @XmlElement(name = "fmp_codice", required = true)
    protected String fmpCodice;
    @XmlElement(name = "fmp_descrizione", required = true)
    protected String fmpDescrizione;

    /**
     * Recupera il valore della proprietà proCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProCodice() {
        return proCodice;
    }

    /**
     * Imposta il valore della proprietà proCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProCodice(String value) {
        this.proCodice = value;
    }

    /**
     * Recupera il valore della proprietà proRagionesociale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProRagionesociale() {
        return proRagionesociale;
    }

    /**
     * Imposta il valore della proprietà proRagionesociale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProRagionesociale(String value) {
        this.proRagionesociale = value;
    }

    /**
     * Recupera il valore della proprietà proCodiceB2BAngaisa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProCodiceB2BAngaisa() {
        return proCodiceB2BAngaisa;
    }

    /**
     * Imposta il valore della proprietà proCodiceB2BAngaisa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProCodiceB2BAngaisa(String value) {
        this.proCodiceB2BAngaisa = value;
    }

    /**
     * Recupera il valore della proprietà marCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarCodice() {
        return marCodice;
    }

    /**
     * Imposta il valore della proprietà marCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarCodice(String value) {
        this.marCodice = value;
    }

    /**
     * Recupera il valore della proprietà marDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarDescrizione() {
        return marDescrizione;
    }

    /**
     * Imposta il valore della proprietà marDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarDescrizione(String value) {
        this.marDescrizione = value;
    }

    /**
     * Recupera il valore della proprietà marCodiceAngaisa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarCodiceAngaisa() {
        return marCodiceAngaisa;
    }

    /**
     * Imposta il valore della proprietà marCodiceAngaisa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarCodiceAngaisa(String value) {
        this.marCodiceAngaisa = value;
    }

    /**
     * Recupera il valore della proprietà artCodiceproduttore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtCodiceproduttore() {
        return artCodiceproduttore;
    }

    /**
     * Imposta il valore della proprietà artCodiceproduttore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtCodiceproduttore(String value) {
        this.artCodiceproduttore = value;
    }

    /**
     * Recupera il valore della proprietà artCodiceabarre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtCodiceabarre() {
        return artCodiceabarre;
    }

    /**
     * Imposta il valore della proprietà artCodiceabarre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtCodiceabarre(String value) {
        this.artCodiceabarre = value;
    }

    /**
     * Recupera il valore della proprietà artCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtCodice() {
        return artCodice;
    }

    /**
     * Imposta il valore della proprietà artCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtCodice(String value) {
        this.artCodice = value;
    }

    /**
     * Recupera il valore della proprietà artDescrizioneridotta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtDescrizioneridotta() {
        return artDescrizioneridotta;
    }

    /**
     * Imposta il valore della proprietà artDescrizioneridotta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtDescrizioneridotta(String value) {
        this.artDescrizioneridotta = value;
    }

    /**
     * Recupera il valore della proprietà artDescrizioneestesa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtDescrizioneestesa() {
        return artDescrizioneestesa;
    }

    /**
     * Imposta il valore della proprietà artDescrizioneestesa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtDescrizioneestesa(String value) {
        this.artDescrizioneestesa = value;
    }

    /**
     * Recupera il valore della proprietà artUm.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtUm() {
        return artUm;
    }

    /**
     * Imposta il valore della proprietà artUm.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtUm(String value) {
        this.artUm = value;
    }

    /**
     * Recupera il valore della proprietà artMinacq.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtMinacq() {
        return artMinacq;
    }

    /**
     * Imposta il valore della proprietà artMinacq.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtMinacq(String value) {
        this.artMinacq = value;
    }

    /**
     * Recupera il valore della proprietà artUmConf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtUmConf() {
        return artUmConf;
    }

    /**
     * Imposta il valore della proprietà artUmConf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtUmConf(String value) {
        this.artUmConf = value;
    }

    /**
     * Recupera il valore della proprietà artConf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtConf() {
        return artConf;
    }

    /**
     * Imposta il valore della proprietà artConf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtConf(String value) {
        this.artConf = value;
    }

    /**
     * Recupera il valore della proprietà artArticoloConf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtArticoloConf() {
        return artArticoloConf;
    }

    /**
     * Imposta il valore della proprietà artArticoloConf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtArticoloConf(String value) {
        this.artArticoloConf = value;
    }

    /**
     * Recupera il valore della proprietà artUmConfIi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtUmConfIi() {
        return artUmConfIi;
    }

    /**
     * Imposta il valore della proprietà artUmConfIi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtUmConfIi(String value) {
        this.artUmConfIi = value;
    }

    /**
     * Recupera il valore della proprietà artConfIi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtConfIi() {
        return artConfIi;
    }

    /**
     * Imposta il valore della proprietà artConfIi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtConfIi(String value) {
        this.artConfIi = value;
    }

    /**
     * Recupera il valore della proprietà artArticoloConfIi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtArticoloConfIi() {
        return artArticoloConfIi;
    }

    /**
     * Imposta il valore della proprietà artArticoloConfIi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtArticoloConfIi(String value) {
        this.artArticoloConfIi = value;
    }

    /**
     * Recupera il valore della proprietà artUmConfIii.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtUmConfIii() {
        return artUmConfIii;
    }

    /**
     * Imposta il valore della proprietà artUmConfIii.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtUmConfIii(String value) {
        this.artUmConfIii = value;
    }

    /**
     * Recupera il valore della proprietà artConfIii.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtConfIii() {
        return artConfIii;
    }

    /**
     * Imposta il valore della proprietà artConfIii.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtConfIii(String value) {
        this.artConfIii = value;
    }

    /**
     * Recupera il valore della proprietà artArticoloConfIii.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtArticoloConfIii() {
        return artArticoloConfIii;
    }

    /**
     * Imposta il valore della proprietà artArticoloConfIii.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtArticoloConfIii(String value) {
        this.artArticoloConfIii = value;
    }

    /**
     * Recupera il valore della proprietà lisPrezzoeuro1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLisPrezzoeuro1() {
        return lisPrezzoeuro1;
    }

    /**
     * Imposta il valore della proprietà lisPrezzoeuro1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLisPrezzoeuro1(String value) {
        this.lisPrezzoeuro1 = value;
    }

    /**
     * Recupera il valore della proprietà ivaCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvaCodice() {
        return ivaCodice;
    }

    /**
     * Imposta il valore della proprietà ivaCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvaCodice(String value) {
        this.ivaCodice = value;
    }

    /**
     * Recupera il valore della proprietà setCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetCodice() {
        return setCodice;
    }

    /**
     * Imposta il valore della proprietà setCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetCodice(String value) {
        this.setCodice = value;
    }

    /**
     * Recupera il valore della proprietà setDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetDescrizione() {
        return setDescrizione;
    }

    /**
     * Imposta il valore della proprietà setDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetDescrizione(String value) {
        this.setDescrizione = value;
    }

    /**
     * Recupera il valore della proprietà macCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacCodice() {
        return macCodice;
    }

    /**
     * Imposta il valore della proprietà macCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacCodice(String value) {
        this.macCodice = value;
    }

    /**
     * Recupera il valore della proprietà macDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacDescrizione() {
        return macDescrizione;
    }

    /**
     * Imposta il valore della proprietà macDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacDescrizione(String value) {
        this.macDescrizione = value;
    }

    /**
     * Recupera il valore della proprietà famCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamCodice() {
        return famCodice;
    }

    /**
     * Imposta il valore della proprietà famCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamCodice(String value) {
        this.famCodice = value;
    }

    /**
     * Recupera il valore della proprietà famDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamDescrizione() {
        return famDescrizione;
    }

    /**
     * Imposta il valore della proprietà famDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamDescrizione(String value) {
        this.famDescrizione = value;
    }

    /**
     * Recupera il valore della proprietà artCatsc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtCatsc() {
        return artCatsc;
    }

    /**
     * Imposta il valore della proprietà artCatsc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtCatsc(String value) {
        this.artCatsc = value;
    }

    /**
     * Recupera il valore della proprietà artModello.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtModello() {
        return artModello;
    }

    /**
     * Imposta il valore della proprietà artModello.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtModello(String value) {
        this.artModello = value;
    }

    /**
     * Recupera il valore della proprietà artDimensioni.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtDimensioni() {
        return artDimensioni;
    }

    /**
     * Imposta il valore della proprietà artDimensioni.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtDimensioni(String value) {
        this.artDimensioni = value;
    }

    /**
     * Recupera il valore della proprietà artColore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtColore() {
        return artColore;
    }

    /**
     * Imposta il valore della proprietà artColore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtColore(String value) {
        this.artColore = value;
    }

    /**
     * Recupera il valore della proprietà artPeso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtPeso() {
        return artPeso;
    }

    /**
     * Imposta il valore della proprietà artPeso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtPeso(String value) {
        this.artPeso = value;
    }

    /**
     * Recupera il valore della proprietà artCaratteristiche.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtCaratteristiche() {
        return artCaratteristiche;
    }

    /**
     * Imposta il valore della proprietà artCaratteristiche.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtCaratteristiche(String value) {
        this.artCaratteristiche = value;
    }

    /**
     * Recupera il valore della proprietà artMateriale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtMateriale() {
        return artMateriale;
    }

    /**
     * Imposta il valore della proprietà artMateriale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtMateriale(String value) {
        this.artMateriale = value;
    }

    /**
     * Recupera il valore della proprietà artFuorilistinocartaceo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtFuorilistinocartaceo() {
        return artFuorilistinocartaceo;
    }

    /**
     * Imposta il valore della proprietà artFuorilistinocartaceo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtFuorilistinocartaceo(String value) {
        this.artFuorilistinocartaceo = value;
    }

    /**
     * Recupera il valore della proprietà artGaranzia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtGaranzia() {
        return artGaranzia;
    }

    /**
     * Imposta il valore della proprietà artGaranzia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtGaranzia(String value) {
        this.artGaranzia = value;
    }

    /**
     * Recupera il valore della proprietà artRicambio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtRicambio() {
        return artRicambio;
    }

    /**
     * Imposta il valore della proprietà artRicambio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtRicambio(String value) {
        this.artRicambio = value;
    }

    /**
     * Recupera il valore della proprietà artStato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtStato() {
        return artStato;
    }

    /**
     * Imposta il valore della proprietà artStato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtStato(String value) {
        this.artStato = value;
    }

    /**
     * Recupera il valore della proprietà lisDatalistino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLisDatalistino() {
        return lisDatalistino;
    }

    /**
     * Imposta il valore della proprietà lisDatalistino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLisDatalistino(String value) {
        this.lisDatalistino = value;
    }

    /**
     * Recupera il valore della proprietà lisDatavalidita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLisDatavalidita() {
        return lisDatavalidita;
    }

    /**
     * Imposta il valore della proprietà lisDatavalidita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLisDatavalidita(String value) {
        this.lisDatavalidita = value;
    }

    /**
     * Recupera il valore della proprietà artDisegnotecnico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtDisegnotecnico() {
        return artDisegnotecnico;
    }

    /**
     * Imposta il valore della proprietà artDisegnotecnico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtDisegnotecnico(String value) {
        this.artDisegnotecnico = value;
    }

    /**
     * Recupera il valore della proprietà artImmaginegrande.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtImmaginegrande() {
        return artImmaginegrande;
    }

    /**
     * Imposta il valore della proprietà artImmaginegrande.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtImmaginegrande(String value) {
        this.artImmaginegrande = value;
    }

    /**
     * Recupera il valore della proprietà artTipoprezzo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtTipoprezzo() {
        return artTipoprezzo;
    }

    /**
     * Imposta il valore della proprietà artTipoprezzo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtTipoprezzo(String value) {
        this.artTipoprezzo = value;
    }

    /**
     * Recupera il valore della proprietà lisPrezzoeuroprec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLisPrezzoeuroprec() {
        return lisPrezzoeuroprec;
    }

    /**
     * Imposta il valore della proprietà lisPrezzoeuroprec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLisPrezzoeuroprec(String value) {
        this.lisPrezzoeuroprec = value;
    }

    /**
     * Recupera il valore della proprietà lisDatavaliditaprec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLisDatavaliditaprec() {
        return lisDatavaliditaprec;
    }

    /**
     * Imposta il valore della proprietà lisDatavaliditaprec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLisDatavaliditaprec(String value) {
        this.lisDatavaliditaprec = value;
    }

    /**
     * Recupera il valore della proprietà lcaCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcaCodice() {
        return lcaCodice;
    }

    /**
     * Imposta il valore della proprietà lcaCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcaCodice(String value) {
        this.lcaCodice = value;
    }

    /**
     * Recupera il valore della proprietà lcaDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcaDescrizione() {
        return lcaDescrizione;
    }

    /**
     * Imposta il valore della proprietà lcaDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcaDescrizione(String value) {
        this.lcaDescrizione = value;
    }

    /**
     * Recupera il valore della proprietà lcaCodicelistinoproduttore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcaCodicelistinoproduttore() {
        return lcaCodicelistinoproduttore;
    }

    /**
     * Imposta il valore della proprietà lcaCodicelistinoproduttore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcaCodicelistinoproduttore(String value) {
        this.lcaCodicelistinoproduttore = value;
    }

    /**
     * Recupera il valore della proprietà lcrRevisione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcrRevisione() {
        return lcrRevisione;
    }

    /**
     * Imposta il valore della proprietà lcrRevisione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcrRevisione(String value) {
        this.lcrRevisione = value;
    }

    /**
     * Recupera il valore della proprietà lcrDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcrDescrizione() {
        return lcrDescrizione;
    }

    /**
     * Imposta il valore della proprietà lcrDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcrDescrizione(String value) {
        this.lcrDescrizione = value;
    }

    /**
     * Recupera il valore della proprietà lcrData.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcrData() {
        return lcrData;
    }

    /**
     * Imposta il valore della proprietà lcrData.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcrData(String value) {
        this.lcrData = value;
    }

    /**
     * Recupera il valore della proprietà lcrStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLcrStatus() {
        return lcrStatus;
    }

    /**
     * Imposta il valore della proprietà lcrStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLcrStatus(String value) {
        this.lcrStatus = value;
    }

    /**
     * Recupera il valore della proprietà lciSezione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLciSezione() {
        return lciSezione;
    }

    /**
     * Imposta il valore della proprietà lciSezione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLciSezione(String value) {
        this.lciSezione = value;
    }

    /**
     * Recupera il valore della proprietà lciDescrizionesezione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLciDescrizionesezione() {
        return lciDescrizionesezione;
    }

    /**
     * Imposta il valore della proprietà lciDescrizionesezione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLciDescrizionesezione(String value) {
        this.lciDescrizionesezione = value;
    }

    /**
     * Recupera il valore della proprietà lciCodicesezioneprod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLciCodicesezioneprod() {
        return lciCodicesezioneprod;
    }

    /**
     * Imposta il valore della proprietà lciCodicesezioneprod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLciCodicesezioneprod(String value) {
        this.lciCodicesezioneprod = value;
    }

    /**
     * Recupera il valore della proprietà lciCapitolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLciCapitolo() {
        return lciCapitolo;
    }

    /**
     * Imposta il valore della proprietà lciCapitolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLciCapitolo(String value) {
        this.lciCapitolo = value;
    }

    /**
     * Recupera il valore della proprietà lciDescrizionecapitolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLciDescrizionecapitolo() {
        return lciDescrizionecapitolo;
    }

    /**
     * Imposta il valore della proprietà lciDescrizionecapitolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLciDescrizionecapitolo(String value) {
        this.lciDescrizionecapitolo = value;
    }

    /**
     * Recupera il valore della proprietà lciCodicecapitoloprod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLciCodicecapitoloprod() {
        return lciCodicecapitoloprod;
    }

    /**
     * Imposta il valore della proprietà lciCodicecapitoloprod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLciCodicecapitoloprod(String value) {
        this.lciCodicecapitoloprod = value;
    }

    /**
     * Recupera il valore della proprietà linCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinCodice() {
        return linCodice;
    }

    /**
     * Imposta il valore della proprietà linCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinCodice(String value) {
        this.linCodice = value;
    }

    /**
     * Recupera il valore della proprietà linDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinDescrizione() {
        return linDescrizione;
    }

    /**
     * Imposta il valore della proprietà linDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinDescrizione(String value) {
        this.linDescrizione = value;
    }

    /**
     * Recupera il valore della proprietà smaCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmaCodice() {
        return smaCodice;
    }

    /**
     * Imposta il valore della proprietà smaCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmaCodice(String value) {
        this.smaCodice = value;
    }

    /**
     * Recupera il valore della proprietà artScoaum1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtScoaum1() {
        return artScoaum1;
    }

    /**
     * Imposta il valore della proprietà artScoaum1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtScoaum1(String value) {
        this.artScoaum1 = value;
    }

    /**
     * Recupera il valore della proprietà artScoaum2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtScoaum2() {
        return artScoaum2;
    }

    /**
     * Imposta il valore della proprietà artScoaum2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtScoaum2(String value) {
        this.artScoaum2 = value;
    }

    /**
     * Recupera il valore della proprietà artScoaum3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtScoaum3() {
        return artScoaum3;
    }

    /**
     * Imposta il valore della proprietà artScoaum3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtScoaum3(String value) {
        this.artScoaum3 = value;
    }

    /**
     * Recupera il valore della proprietà artScoaum4.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtScoaum4() {
        return artScoaum4;
    }

    /**
     * Imposta il valore della proprietà artScoaum4.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtScoaum4(String value) {
        this.artScoaum4 = value;
    }

    /**
     * Recupera il valore della proprietà artScoaum5.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtScoaum5() {
        return artScoaum5;
    }

    /**
     * Imposta il valore della proprietà artScoaum5.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtScoaum5(String value) {
        this.artScoaum5 = value;
    }

    /**
     * Recupera il valore della proprietà fmpCodice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFmpCodice() {
        return fmpCodice;
    }

    /**
     * Imposta il valore della proprietà fmpCodice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFmpCodice(String value) {
        this.fmpCodice = value;
    }

    /**
     * Recupera il valore della proprietà fmpDescrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFmpDescrizione() {
        return fmpDescrizione;
    }

    /**
     * Imposta il valore della proprietà fmpDescrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFmpDescrizione(String value) {
        this.fmpDescrizione = value;
    }

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artArticoloConf == null) ? 0 : artArticoloConf.hashCode());
		result = prime * result + ((artArticoloConfIi == null) ? 0 : artArticoloConfIi.hashCode());
		result = prime * result + ((artArticoloConfIii == null) ? 0 : artArticoloConfIii.hashCode());
		result = prime * result + ((artCaratteristiche == null) ? 0 : artCaratteristiche.hashCode());
		result = prime * result + ((artCatsc == null) ? 0 : artCatsc.hashCode());
		result = prime * result + ((artCodice == null) ? 0 : artCodice.hashCode());
		result = prime * result + ((artCodiceabarre == null) ? 0 : artCodiceabarre.hashCode());
		result = prime * result + ((artCodiceproduttore == null) ? 0 : artCodiceproduttore.hashCode());
		result = prime * result + ((artColore == null) ? 0 : artColore.hashCode());
		result = prime * result + ((artConf == null) ? 0 : artConf.hashCode());
		result = prime * result + ((artConfIi == null) ? 0 : artConfIi.hashCode());
		result = prime * result + ((artConfIii == null) ? 0 : artConfIii.hashCode());
		result = prime * result + ((artDescrizioneestesa == null) ? 0 : artDescrizioneestesa.hashCode());
		result = prime * result + ((artDescrizioneridotta == null) ? 0 : artDescrizioneridotta.hashCode());
		result = prime * result + ((artDimensioni == null) ? 0 : artDimensioni.hashCode());
		result = prime * result + ((artDisegnotecnico == null) ? 0 : artDisegnotecnico.hashCode());
		result = prime * result + ((artFuorilistinocartaceo == null) ? 0 : artFuorilistinocartaceo.hashCode());
		result = prime * result + ((artGaranzia == null) ? 0 : artGaranzia.hashCode());
		result = prime * result + ((artImmaginegrande == null) ? 0 : artImmaginegrande.hashCode());
		result = prime * result + ((artMateriale == null) ? 0 : artMateriale.hashCode());
		result = prime * result + ((artMinacq == null) ? 0 : artMinacq.hashCode());
		result = prime * result + ((artModello == null) ? 0 : artModello.hashCode());
		result = prime * result + ((artPeso == null) ? 0 : artPeso.hashCode());
		result = prime * result + ((artRicambio == null) ? 0 : artRicambio.hashCode());
		result = prime * result + ((artScoaum1 == null) ? 0 : artScoaum1.hashCode());
		result = prime * result + ((artScoaum2 == null) ? 0 : artScoaum2.hashCode());
		result = prime * result + ((artScoaum3 == null) ? 0 : artScoaum3.hashCode());
		result = prime * result + ((artScoaum4 == null) ? 0 : artScoaum4.hashCode());
		result = prime * result + ((artScoaum5 == null) ? 0 : artScoaum5.hashCode());
		result = prime * result + ((artStato == null) ? 0 : artStato.hashCode());
		result = prime * result + ((artTipoprezzo == null) ? 0 : artTipoprezzo.hashCode());
		result = prime * result + ((artUm == null) ? 0 : artUm.hashCode());
		result = prime * result + ((artUmConf == null) ? 0 : artUmConf.hashCode());
		result = prime * result + ((artUmConfIi == null) ? 0 : artUmConfIi.hashCode());
		result = prime * result + ((artUmConfIii == null) ? 0 : artUmConfIii.hashCode());
		result = prime * result + ((famCodice == null) ? 0 : famCodice.hashCode());
		result = prime * result + ((famDescrizione == null) ? 0 : famDescrizione.hashCode());
		result = prime * result + ((fmpCodice == null) ? 0 : fmpCodice.hashCode());
		result = prime * result + ((fmpDescrizione == null) ? 0 : fmpDescrizione.hashCode());
		result = prime * result + ((ivaCodice == null) ? 0 : ivaCodice.hashCode());
		result = prime * result + ((lcaCodice == null) ? 0 : lcaCodice.hashCode());
		result = prime * result + ((lcaCodicelistinoproduttore == null) ? 0 : lcaCodicelistinoproduttore.hashCode());
		result = prime * result + ((lcaDescrizione == null) ? 0 : lcaDescrizione.hashCode());
		result = prime * result + ((lciCapitolo == null) ? 0 : lciCapitolo.hashCode());
		result = prime * result + ((lciCodicecapitoloprod == null) ? 0 : lciCodicecapitoloprod.hashCode());
		result = prime * result + ((lciCodicesezioneprod == null) ? 0 : lciCodicesezioneprod.hashCode());
		result = prime * result + ((lciDescrizionecapitolo == null) ? 0 : lciDescrizionecapitolo.hashCode());
		result = prime * result + ((lciDescrizionesezione == null) ? 0 : lciDescrizionesezione.hashCode());
		result = prime * result + ((lciSezione == null) ? 0 : lciSezione.hashCode());
		result = prime * result + ((lcrData == null) ? 0 : lcrData.hashCode());
		result = prime * result + ((lcrDescrizione == null) ? 0 : lcrDescrizione.hashCode());
		result = prime * result + ((lcrRevisione == null) ? 0 : lcrRevisione.hashCode());
		result = prime * result + ((lcrStatus == null) ? 0 : lcrStatus.hashCode());
		result = prime * result + ((linCodice == null) ? 0 : linCodice.hashCode());
		result = prime * result + ((linDescrizione == null) ? 0 : linDescrizione.hashCode());
		result = prime * result + ((lisDatalistino == null) ? 0 : lisDatalistino.hashCode());
		result = prime * result + ((lisDatavalidita == null) ? 0 : lisDatavalidita.hashCode());
		result = prime * result + ((lisDatavaliditaprec == null) ? 0 : lisDatavaliditaprec.hashCode());
		result = prime * result + ((lisPrezzoeuro1 == null) ? 0 : lisPrezzoeuro1.hashCode());
		result = prime * result + ((lisPrezzoeuroprec == null) ? 0 : lisPrezzoeuroprec.hashCode());
		result = prime * result + ((macCodice == null) ? 0 : macCodice.hashCode());
		result = prime * result + ((macDescrizione == null) ? 0 : macDescrizione.hashCode());
		result = prime * result + ((marCodice == null) ? 0 : marCodice.hashCode());
		result = prime * result + ((marCodiceAngaisa == null) ? 0 : marCodiceAngaisa.hashCode());
		result = prime * result + ((marDescrizione == null) ? 0 : marDescrizione.hashCode());
		result = prime * result + ((proCodice == null) ? 0 : proCodice.hashCode());
		result = prime * result + ((proCodiceB2BAngaisa == null) ? 0 : proCodiceB2BAngaisa.hashCode());
		result = prime * result + ((proRagionesociale == null) ? 0 : proRagionesociale.hashCode());
		result = prime * result + ((setCodice == null) ? 0 : setCodice.hashCode());
		result = prime * result + ((setDescrizione == null) ? 0 : setDescrizione.hashCode());
		result = prime * result + ((smaCodice == null) ? 0 : smaCodice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Articolo other = (Articolo)obj;
		if(artArticoloConf == null) {
			if(other.artArticoloConf != null)
				return false;
		}else if(!artArticoloConf.equals(other.artArticoloConf))
			return false;
		if(artArticoloConfIi == null) {
			if(other.artArticoloConfIi != null)
				return false;
		}else if(!artArticoloConfIi.equals(other.artArticoloConfIi))
			return false;
		if(artArticoloConfIii == null) {
			if(other.artArticoloConfIii != null)
				return false;
		}else if(!artArticoloConfIii.equals(other.artArticoloConfIii))
			return false;
		if(artCaratteristiche == null) {
			if(other.artCaratteristiche != null)
				return false;
		}else if(!artCaratteristiche.equals(other.artCaratteristiche))
			return false;
		if(artCatsc == null) {
			if(other.artCatsc != null)
				return false;
		}else if(!artCatsc.equals(other.artCatsc))
			return false;
		if(artCodice == null) {
			if(other.artCodice != null)
				return false;
		}else if(!artCodice.equals(other.artCodice))
			return false;
		if(artCodiceabarre == null) {
			if(other.artCodiceabarre != null)
				return false;
		}else if(!artCodiceabarre.equals(other.artCodiceabarre))
			return false;
		if(artCodiceproduttore == null) {
			if(other.artCodiceproduttore != null)
				return false;
		}else if(!artCodiceproduttore.equals(other.artCodiceproduttore))
			return false;
		if(artColore == null) {
			if(other.artColore != null)
				return false;
		}else if(!artColore.equals(other.artColore))
			return false;
		if(artConf == null) {
			if(other.artConf != null)
				return false;
		}else if(!artConf.equals(other.artConf))
			return false;
		if(artConfIi == null) {
			if(other.artConfIi != null)
				return false;
		}else if(!artConfIi.equals(other.artConfIi))
			return false;
		if(artConfIii == null) {
			if(other.artConfIii != null)
				return false;
		}else if(!artConfIii.equals(other.artConfIii))
			return false;
		if(artDescrizioneestesa == null) {
			if(other.artDescrizioneestesa != null)
				return false;
		}else if(!artDescrizioneestesa.equals(other.artDescrizioneestesa))
			return false;
		if(artDescrizioneridotta == null) {
			if(other.artDescrizioneridotta != null)
				return false;
		}else if(!artDescrizioneridotta.equals(other.artDescrizioneridotta))
			return false;
		if(artDimensioni == null) {
			if(other.artDimensioni != null)
				return false;
		}else if(!artDimensioni.equals(other.artDimensioni))
			return false;
		if(artDisegnotecnico == null) {
			if(other.artDisegnotecnico != null)
				return false;
		}else if(!artDisegnotecnico.equals(other.artDisegnotecnico))
			return false;
		if(artFuorilistinocartaceo == null) {
			if(other.artFuorilistinocartaceo != null)
				return false;
		}else if(!artFuorilistinocartaceo.equals(other.artFuorilistinocartaceo))
			return false;
		if(artGaranzia == null) {
			if(other.artGaranzia != null)
				return false;
		}else if(!artGaranzia.equals(other.artGaranzia))
			return false;
		if(artImmaginegrande == null) {
			if(other.artImmaginegrande != null)
				return false;
		}else if(!artImmaginegrande.equals(other.artImmaginegrande))
			return false;
		if(artMateriale == null) {
			if(other.artMateriale != null)
				return false;
		}else if(!artMateriale.equals(other.artMateriale))
			return false;
		if(artMinacq == null) {
			if(other.artMinacq != null)
				return false;
		}else if(!artMinacq.equals(other.artMinacq))
			return false;
		if(artModello == null) {
			if(other.artModello != null)
				return false;
		}else if(!artModello.equals(other.artModello))
			return false;
		if(artPeso == null) {
			if(other.artPeso != null)
				return false;
		}else if(!artPeso.equals(other.artPeso))
			return false;
		if(artRicambio == null) {
			if(other.artRicambio != null)
				return false;
		}else if(!artRicambio.equals(other.artRicambio))
			return false;
		if(artScoaum1 == null) {
			if(other.artScoaum1 != null)
				return false;
		}else if(!artScoaum1.equals(other.artScoaum1))
			return false;
		if(artScoaum2 == null) {
			if(other.artScoaum2 != null)
				return false;
		}else if(!artScoaum2.equals(other.artScoaum2))
			return false;
		if(artScoaum3 == null) {
			if(other.artScoaum3 != null)
				return false;
		}else if(!artScoaum3.equals(other.artScoaum3))
			return false;
		if(artScoaum4 == null) {
			if(other.artScoaum4 != null)
				return false;
		}else if(!artScoaum4.equals(other.artScoaum4))
			return false;
		if(artScoaum5 == null) {
			if(other.artScoaum5 != null)
				return false;
		}else if(!artScoaum5.equals(other.artScoaum5))
			return false;
		if(artStato == null) {
			if(other.artStato != null)
				return false;
		}else if(!artStato.equals(other.artStato))
			return false;
		if(artTipoprezzo == null) {
			if(other.artTipoprezzo != null)
				return false;
		}else if(!artTipoprezzo.equals(other.artTipoprezzo))
			return false;
		if(artUm == null) {
			if(other.artUm != null)
				return false;
		}else if(!artUm.equals(other.artUm))
			return false;
		if(artUmConf == null) {
			if(other.artUmConf != null)
				return false;
		}else if(!artUmConf.equals(other.artUmConf))
			return false;
		if(artUmConfIi == null) {
			if(other.artUmConfIi != null)
				return false;
		}else if(!artUmConfIi.equals(other.artUmConfIi))
			return false;
		if(artUmConfIii == null) {
			if(other.artUmConfIii != null)
				return false;
		}else if(!artUmConfIii.equals(other.artUmConfIii))
			return false;
		if(famCodice == null) {
			if(other.famCodice != null)
				return false;
		}else if(!famCodice.equals(other.famCodice))
			return false;
		if(famDescrizione == null) {
			if(other.famDescrizione != null)
				return false;
		}else if(!famDescrizione.equals(other.famDescrizione))
			return false;
		if(fmpCodice == null) {
			if(other.fmpCodice != null)
				return false;
		}else if(!fmpCodice.equals(other.fmpCodice))
			return false;
		if(fmpDescrizione == null) {
			if(other.fmpDescrizione != null)
				return false;
		}else if(!fmpDescrizione.equals(other.fmpDescrizione))
			return false;
		if(ivaCodice == null) {
			if(other.ivaCodice != null)
				return false;
		}else if(!ivaCodice.equals(other.ivaCodice))
			return false;
		if(lcaCodice == null) {
			if(other.lcaCodice != null)
				return false;
		}else if(!lcaCodice.equals(other.lcaCodice))
			return false;
		if(lcaCodicelistinoproduttore == null) {
			if(other.lcaCodicelistinoproduttore != null)
				return false;
		}else if(!lcaCodicelistinoproduttore.equals(other.lcaCodicelistinoproduttore))
			return false;
		if(lcaDescrizione == null) {
			if(other.lcaDescrizione != null)
				return false;
		}else if(!lcaDescrizione.equals(other.lcaDescrizione))
			return false;
		if(lciCapitolo == null) {
			if(other.lciCapitolo != null)
				return false;
		}else if(!lciCapitolo.equals(other.lciCapitolo))
			return false;
		if(lciCodicecapitoloprod == null) {
			if(other.lciCodicecapitoloprod != null)
				return false;
		}else if(!lciCodicecapitoloprod.equals(other.lciCodicecapitoloprod))
			return false;
		if(lciCodicesezioneprod == null) {
			if(other.lciCodicesezioneprod != null)
				return false;
		}else if(!lciCodicesezioneprod.equals(other.lciCodicesezioneprod))
			return false;
		if(lciDescrizionecapitolo == null) {
			if(other.lciDescrizionecapitolo != null)
				return false;
		}else if(!lciDescrizionecapitolo.equals(other.lciDescrizionecapitolo))
			return false;
		if(lciDescrizionesezione == null) {
			if(other.lciDescrizionesezione != null)
				return false;
		}else if(!lciDescrizionesezione.equals(other.lciDescrizionesezione))
			return false;
		if(lciSezione == null) {
			if(other.lciSezione != null)
				return false;
		}else if(!lciSezione.equals(other.lciSezione))
			return false;
		if(lcrData == null) {
			if(other.lcrData != null)
				return false;
		}else if(!lcrData.equals(other.lcrData))
			return false;
		if(lcrDescrizione == null) {
			if(other.lcrDescrizione != null)
				return false;
		}else if(!lcrDescrizione.equals(other.lcrDescrizione))
			return false;
		if(lcrRevisione == null) {
			if(other.lcrRevisione != null)
				return false;
		}else if(!lcrRevisione.equals(other.lcrRevisione))
			return false;
		if(lcrStatus == null) {
			if(other.lcrStatus != null)
				return false;
		}else if(!lcrStatus.equals(other.lcrStatus))
			return false;
		if(linCodice == null) {
			if(other.linCodice != null)
				return false;
		}else if(!linCodice.equals(other.linCodice))
			return false;
		if(linDescrizione == null) {
			if(other.linDescrizione != null)
				return false;
		}else if(!linDescrizione.equals(other.linDescrizione))
			return false;
		if(lisDatalistino == null) {
			if(other.lisDatalistino != null)
				return false;
		}else if(!lisDatalistino.equals(other.lisDatalistino))
			return false;
		if(lisDatavalidita == null) {
			if(other.lisDatavalidita != null)
				return false;
		}else if(!lisDatavalidita.equals(other.lisDatavalidita))
			return false;
		if(lisDatavaliditaprec == null) {
			if(other.lisDatavaliditaprec != null)
				return false;
		}else if(!lisDatavaliditaprec.equals(other.lisDatavaliditaprec))
			return false;
		if(lisPrezzoeuro1 == null) {
			if(other.lisPrezzoeuro1 != null)
				return false;
		}else if(!lisPrezzoeuro1.equals(other.lisPrezzoeuro1))
			return false;
		if(lisPrezzoeuroprec == null) {
			if(other.lisPrezzoeuroprec != null)
				return false;
		}else if(!lisPrezzoeuroprec.equals(other.lisPrezzoeuroprec))
			return false;
		if(macCodice == null) {
			if(other.macCodice != null)
				return false;
		}else if(!macCodice.equals(other.macCodice))
			return false;
		if(macDescrizione == null) {
			if(other.macDescrizione != null)
				return false;
		}else if(!macDescrizione.equals(other.macDescrizione))
			return false;
		if(marCodice == null) {
			if(other.marCodice != null)
				return false;
		}else if(!marCodice.equals(other.marCodice))
			return false;
		if(marCodiceAngaisa == null) {
			if(other.marCodiceAngaisa != null)
				return false;
		}else if(!marCodiceAngaisa.equals(other.marCodiceAngaisa))
			return false;
		if(marDescrizione == null) {
			if(other.marDescrizione != null)
				return false;
		}else if(!marDescrizione.equals(other.marDescrizione))
			return false;
		if(proCodice == null) {
			if(other.proCodice != null)
				return false;
		}else if(!proCodice.equals(other.proCodice))
			return false;
		if(proCodiceB2BAngaisa == null) {
			if(other.proCodiceB2BAngaisa != null)
				return false;
		}else if(!proCodiceB2BAngaisa.equals(other.proCodiceB2BAngaisa))
			return false;
		if(proRagionesociale == null) {
			if(other.proRagionesociale != null)
				return false;
		}else if(!proRagionesociale.equals(other.proRagionesociale))
			return false;
		if(setCodice == null) {
			if(other.setCodice != null)
				return false;
		}else if(!setCodice.equals(other.setCodice))
			return false;
		if(setDescrizione == null) {
			if(other.setDescrizione != null)
				return false;
		}else if(!setDescrizione.equals(other.setDescrizione))
			return false;
		if(smaCodice == null) {
			if(other.smaCodice != null)
				return false;
		}else if(!smaCodice.equals(other.smaCodice))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Articolo [proCodice=" + proCodice + ", proRagionesociale=" + proRagionesociale + ", proCodiceB2BAngaisa=" + proCodiceB2BAngaisa + ", marCodice=" + marCodice + ", marDescrizione=" + marDescrizione + ", marCodiceAngaisa=" + marCodiceAngaisa + ", artCodiceproduttore=" + artCodiceproduttore + ", artCodiceabarre=" + artCodiceabarre + ", artCodice=" + artCodice + ", artDescrizioneridotta=" + artDescrizioneridotta + ", artDescrizioneestesa=" + artDescrizioneestesa + ", artUm=" + artUm + ", artMinacq=" + artMinacq + ", artUmConf=" + artUmConf + ", artConf=" + artConf + ", artArticoloConf=" + artArticoloConf + ", artUmConfIi=" + artUmConfIi + ", artConfIi=" + artConfIi + ", artArticoloConfIi=" + artArticoloConfIi + ", artUmConfIii=" + artUmConfIii + ", artConfIii=" + artConfIii + ", artArticoloConfIii=" + artArticoloConfIii + ", lisPrezzoeuro1=" + lisPrezzoeuro1 + ", ivaCodice=" + ivaCodice + ", setCodice=" + setCodice + ", setDescrizione=" + setDescrizione + ", macCodice=" + macCodice + ", macDescrizione=" + macDescrizione + ", famCodice=" + famCodice + ", famDescrizione=" + famDescrizione + ", artCatsc=" + artCatsc + ", artModello=" + artModello + ", artDimensioni=" + artDimensioni + ", artColore=" + artColore + ", artPeso=" + artPeso + ", artCaratteristiche=" + artCaratteristiche + ", artMateriale=" + artMateriale + ", artFuorilistinocartaceo=" + artFuorilistinocartaceo + ", artGaranzia=" + artGaranzia + ", artRicambio=" + artRicambio + ", artStato=" + artStato + ", lisDatalistino=" + lisDatalistino + ", lisDatavalidita=" + lisDatavalidita + ", artDisegnotecnico=" + artDisegnotecnico + ", artImmaginegrande=" + artImmaginegrande + ", artTipoprezzo=" + artTipoprezzo + ", lisPrezzoeuroprec=" + lisPrezzoeuroprec + ", lisDatavaliditaprec=" + lisDatavaliditaprec + ", lcaCodice=" + lcaCodice + ", lcaDescrizione=" + lcaDescrizione + ", lcaCodicelistinoproduttore=" + lcaCodicelistinoproduttore + ", lcrRevisione=" + lcrRevisione + ", lcrDescrizione=" + lcrDescrizione + ", lcrData=" + lcrData + ", lcrStatus=" + lcrStatus + ", lciSezione=" + lciSezione + ", lciDescrizionesezione=" + lciDescrizionesezione + ", lciCodicesezioneprod=" + lciCodicesezioneprod + ", lciCapitolo=" + lciCapitolo + ", lciDescrizionecapitolo=" + lciDescrizionecapitolo + ", lciCodicecapitoloprod=" + lciCodicecapitoloprod + ", linCodice=" + linCodice + ", linDescrizione=" + linDescrizione + ", smaCodice=" + smaCodice + ", artScoaum1=" + artScoaum1 + ", artScoaum2=" + artScoaum2 + ", artScoaum3=" + artScoaum3 + ", artScoaum4=" + artScoaum4 + ", artScoaum5=" + artScoaum5 + ", fmpCodice=" + fmpCodice + ", fmpDescrizione=" + fmpDescrizione + "]";
	}
}

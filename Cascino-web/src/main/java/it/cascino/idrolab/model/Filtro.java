package it.cascino.idrolab.model;

import org.apache.commons.lang3.StringUtils;

public class Filtro{
	private final String parIni = "?";
	private final String parSep = "&";
	
	private String page;
	private String offset;
	private String marca;
	private String settore; // set
	private String macrofamiglia; // mac
	private String famiglia; // fam
	private String listino; // lis
	private String linea; // lin
	private String dadata;
	private String adata;
	private String tutte;
	private String obs;
	
	public Filtro(){
		this.page = null;
		this.offset = null;
		this.marca = null;
		this.settore = null;
		this.macrofamiglia = null;
		this.famiglia = null;
		this.listino = null;
		this.linea = null;
		this.dadata = null;
		this.adata = null;
		this.tutte = null;
		this.obs = null;
	}
	
	public Filtro(String page, String offset, String marca, String settore, String macrofamiglia, String famiglia, String listino, String linea, String dadata, String adata, String tutte, String obs){
		super();
		this.page = page;
		this.offset = offset;
		this.marca = marca;
		this.settore = settore;
		this.macrofamiglia = macrofamiglia;
		this.famiglia = famiglia;
		this.listino = listino;
		this.linea = linea;
		this.dadata = dadata;
		this.adata = adata;
		this.tutte = tutte;
		this.obs = obs;
	}
	
	public String getPage(){
		return page;
	}
	
	public void setPage(String page){
		this.page = page;
	}
	
	public String getOffset(){
		return offset;
	}
	
	public void setOffset(String offset){
		this.offset = offset;
	}
	
	public String getMarca(){
		return marca;
	}
	
	public void setMarca(String marca){
		this.marca = marca;
	}
	
	public String getSettore(){
		return settore;
	}
	
	public void setSettore(String settore){
		this.settore = settore;
	}
	
	public String getMacrofamiglia(){
		return macrofamiglia;
	}
	
	public void setMacrofamiglia(String macrofamiglia){
		this.macrofamiglia = macrofamiglia;
	}
	
	public String getFamiglia(){
		return famiglia;
	}
	
	public void setFamiglia(String famiglia){
		this.famiglia = famiglia;
	}
	
	public String getListino(){
		return listino;
	}
	
	public void setListino(String listino){
		this.listino = listino;
	}
	
	public String getLinea(){
		return linea;
	}
	
	public void setLinea(String linea){
		this.linea = linea;
	}
	
	public String getDadata(){
		return dadata;
	}
	
	public void setDadata(String dadata){
		this.dadata = dadata;
	}
	
	public String getAdata(){
		return adata;
	}
	
	public void setAdata(String adata){
		this.adata = adata;
	}
	
	public String getTutte(){
		return tutte;
	}
	
	public void setTutte(String tutte){
		this.tutte = tutte;
	}
	
	public String getObs(){
		return obs;
	}
	
	public void setObs(String obs){
		this.obs = obs;
	}
	
	public void reset(){
		this.page = null;
		this.offset = null;
		this.marca = null;
		this.settore = null;
		this.macrofamiglia = null;
		this.famiglia = null;
		this.listino = null;
		this.linea = null;
		this.dadata = null;
		this.adata = null;
		this.tutte = null;
		this.obs = null;
	}
	
	public String filterToUrl(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(parIni);
		stringBuilder.append((page != null) ? "page=" + page + parSep : "");
		stringBuilder.append((offset != null) ? "offset=" + offset + parSep : "");
		stringBuilder.append((marca != null) ? "marca=" + marca + parSep : "");
		stringBuilder.append((settore != null) ? "set=" + settore + parSep : "");
		stringBuilder.append((macrofamiglia != null) ? "mac=" + macrofamiglia + parSep : "");
		stringBuilder.append((famiglia != null) ? "fam=" + famiglia + parSep : "");
		stringBuilder.append((listino != null) ? "lis=" + listino + parSep : "");
		stringBuilder.append((linea != null) ? "lin=" + linea + parSep : "");
		stringBuilder.append((dadata != null) ? "dadata=" + dadata + parSep : "");
		stringBuilder.append((adata != null) ? "adata=" + adata + parSep : "");
		stringBuilder.append((tutte != null) ? "tutte=" + tutte + parSep : "");
		stringBuilder.append((obs != null) ? "obs=" + obs + parSep : "");
		return StringUtils.substring(stringBuilder.toString(), 0, stringBuilder.length() - 1);
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adata == null) ? 0 : adata.hashCode());
		result = prime * result + ((dadata == null) ? 0 : dadata.hashCode());
		result = prime * result + ((famiglia == null) ? 0 : famiglia.hashCode());
		result = prime * result + ((linea == null) ? 0 : linea.hashCode());
		result = prime * result + ((listino == null) ? 0 : listino.hashCode());
		result = prime * result + ((macrofamiglia == null) ? 0 : macrofamiglia.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((offset == null) ? 0 : offset.hashCode());
		result = prime * result + ((page == null) ? 0 : page.hashCode());
		result = prime * result + ((settore == null) ? 0 : settore.hashCode());
		result = prime * result + ((tutte == null) ? 0 : tutte.hashCode());
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
		Filtro other = (Filtro)obj;
		if(adata == null) {
			if(other.adata != null)
				return false;
		}else if(!adata.equals(other.adata))
			return false;
		if(dadata == null) {
			if(other.dadata != null)
				return false;
		}else if(!dadata.equals(other.dadata))
			return false;
		if(famiglia == null) {
			if(other.famiglia != null)
				return false;
		}else if(!famiglia.equals(other.famiglia))
			return false;
		if(linea == null) {
			if(other.linea != null)
				return false;
		}else if(!linea.equals(other.linea))
			return false;
		if(listino == null) {
			if(other.listino != null)
				return false;
		}else if(!listino.equals(other.listino))
			return false;
		if(macrofamiglia == null) {
			if(other.macrofamiglia != null)
				return false;
		}else if(!macrofamiglia.equals(other.macrofamiglia))
			return false;
		if(marca == null) {
			if(other.marca != null)
				return false;
		}else if(!marca.equals(other.marca))
			return false;
		if(obs == null) {
			if(other.obs != null)
				return false;
		}else if(!obs.equals(other.obs))
			return false;
		if(offset == null) {
			if(other.offset != null)
				return false;
		}else if(!offset.equals(other.offset))
			return false;
		if(page == null) {
			if(other.page != null)
				return false;
		}else if(!page.equals(other.page))
			return false;
		if(settore == null) {
			if(other.settore != null)
				return false;
		}else if(!settore.equals(other.settore))
			return false;
		if(tutte == null) {
			if(other.tutte != null)
				return false;
		}else if(!tutte.equals(other.tutte))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "Filtro [page=" + page + ", offset=" + offset + ", marca=" + marca + ", settore=" + settore + ", macrofamiglia=" + macrofamiglia + ", famiglia=" + famiglia + ", listino=" + listino + ", linea=" + linea + ", dadata=" + dadata + ", adata=" + adata + ", tutte=" + tutte + ", obs=" + obs + "]";
	}
	
}

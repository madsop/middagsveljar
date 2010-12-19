package middagsveljar.buss;

public class Butikk {
	private String plass;
	private String namn;
	
	public Butikk(String plass, String namn){
		this.plass = plass;
		this.namn = namn;
	}
	
	public String getPlass(){
		return plass;
	}
	public String getNamn(){
		return namn;
	}
}

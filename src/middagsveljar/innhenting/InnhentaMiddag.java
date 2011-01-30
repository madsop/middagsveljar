package middagsveljar.innhenting;

public class InnhentaMiddag {
	private int id;
	private String namn;
	private int plass;
	
	public InnhentaMiddag(String namn, int id, int plass){
		this.id = id;
		this.namn = namn;
		this.plass = plass;
	}
	
	public String getNamn(){
		return namn;
	}
	public int getID(){
		return id;
	}
	public int getPlass(){
		return plass;
	}
}

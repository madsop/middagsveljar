package middagsveljar;

import java.util.ArrayList;

import middagsveljar.data.Folk;

public class Middag {
	private String namn;
	private ArrayList<String> handleliste;
	private ArrayList<String> oppskrift;
	private ArrayList<Folk> ikkjeLiktAv;
	
	public Middag (String namn, ArrayList<Folk> ikkjeLiktAv, ArrayList<String> ingrediensar, ArrayList<String> oppskrift) {
		this.namn = namn;
		if (ikkjeLiktAv.size() == 0){
			this.ikkjeLiktAv = new ArrayList<Folk>();
		}
		else {
			this.ikkjeLiktAv = new ArrayList<Folk>();
			this.ikkjeLiktAv.add(Folk.Britti);
		}
		this.handleliste = ingrediensar;
		this.oppskrift = oppskrift;
	}
	
	public ArrayList<Folk> getIkkjeLiktAv(){
		return ikkjeLiktAv;
	}
	
	public void likerIkkje(Folk folk){
		ikkjeLiktAv.add(folk);
	}

	public String getNamn() {
		return namn;
	}

	public ArrayList<String> getIngrediensar() {
		return handleliste;
	}

	public ArrayList<String> getOppskrift() {
		return oppskrift;
	}
}
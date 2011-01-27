package middagsveljar.buss;

import java.util.ArrayList;

import middagsveljar.Middagsveljar;

public class Busskontakt implements Runnable{
	public static final String bussurl = "http://www.atb.no/xmlhttprequest.php?service=routeplannerOracle.getOracleAnswer&question="; 
	private Middagsveljar middagsveljar;
	
	public Busskontakt(Middagsveljar middagsveljar){
		this.middagsveljar = middagsveljar;
	}
	
	public void run() {
		Busslesar busslesar = new Busslesar();
		ArrayList<Butikk> butikkar = busslesar.dekodButikkar();
		ArrayList<String> svar = new ArrayList<String>();
		for (int i = 1; i < butikkar.size(); i++){
			svar.add(Bussoppslag.sendGetRequest(bussurl,Busslesar.Startstring+butikkar.get(0).getPlass()+Busslesar.tilstring+butikkar.get(i).getPlass()));
		}
		System.out.println("ANtal butikkar: " +	svar.size());
		
		String svarstreng = "";
		for (int i = 0; i < svar.size(); i++){
			svarstreng+=busslesar.parseBusstuc(svar.get(i),butikkar.get(0).getPlass())+"\n";
		}
		svarstreng+=busslesar.parseTrikk(); // Obs: veldig spesialtilfelle-hack, denne.
		middagsveljar.oppdaterGUImedBuss(svarstreng);
	}

}

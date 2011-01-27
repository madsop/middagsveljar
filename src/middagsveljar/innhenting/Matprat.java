package middagsveljar.innhenting;

import java.util.ArrayList;
import java.util.Random;

import middagsveljar.Middag;
import middagsveljar.Middagsveljar;
import middagsveljar.buss.Bussoppslag;
import middagsveljar.data.Folk;

public class Matprat implements HentFraNett{
	private String URL = "http://www.matprat.no/artikkel.aspx?artid=";
	private int antalArtiklar = 19715;	
	private Middagsveljar middagsveljar;
	
	public Matprat(Middagsveljar middagsveljar){
		this.middagsveljar = middagsveljar;
	}

	public void getTilfeldigMiddag(){
		Middag m = null;
		Random generator = new Random();
		while (m == null){
			int tilfeldigTal = generator.nextInt(antalArtiklar);
			System.out.println(tilfeldigTal);
			m = getMiddag(tilfeldigTal); 
		}
		System.out.println(m.getNamn());
		middagsveljar.innhentaMiddag(m);
	}

	private Middag getMiddag(int artikkelURL) {
		String heilesida = Bussoppslag.sendGetRequest(URL+artikkelURL, null);
		Middag m = parseRaatekst(heilesida);
		return m;
	}

	private Middag parseRaatekst(String heilesida){
		heilesida = heilesida.split("<h1>")[2];
		String namn = heilesida.split("</h1>")[0].trim();
		String[] splitta = heilesida.split("<h3>Ingredienser</h3>");
		if (splitta.length < 2){
			return null;
		}
		heilesida = splitta[1].trim();
		splitta = heilesida.split("</ul>");
		String ingrediensramt = splitta[0];
		String[] ingrediensarRaa = ingrediensramt.split("<li>");
		ArrayList<String> ingrediensar = new ArrayList<String>();
		for (String k : ingrediensarRaa){
			if (k.trim().length() < 6){
				continue;
			} 
			k = k.replace("<strong> ","");
			k = k.replace("</strong>" ,"");
			k = k.replace("</li>", "").trim();
			ingrediensar.add(k);
		}
		ingrediensramt = splitta[1].split("\"text\">")[1].split("</div>")[0].trim().replace("<p>�</p>", "").replace("<p>", "").replace("</p>", "");
		ingrediensarRaa = ingrediensramt.split("				");
		ArrayList<String> oppskrift = new ArrayList<String>();
		for (String k : ingrediensarRaa){
			oppskrift.add(k);
		}
		ArrayList<Folk> folk = new ArrayList<Folk>();
		return new Middag(namn,folk,ingrediensar,oppskrift);
	}
}
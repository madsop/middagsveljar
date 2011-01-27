package middagsveljar.innhenting;

import java.util.ArrayList;

import middagsveljar.Middag;
import middagsveljar.buss.Bussoppslag;
import middagsveljar.data.Folk;
import middagsveljar.data.Ingrediens;

public class Matprat implements HentFraNett{
	private String URL = "http://www.matprat.no/artikkel.aspx?artid=";
	private String artikkelURL="9114";
	
	public Middag getMiddag() {
		String heilesida = Bussoppslag.sendGetRequest(URL+artikkelURL, null);
		Middag m = parseRaatekst(heilesida);
		System.out.println(heilesida.length());
		return m;
	}
	
	private Middag parseRaatekst(String heilesida){
		heilesida = heilesida.split("<h1>")[2];
		String namn = heilesida.split("</h1>")[0].trim();
		heilesida = heilesida.split("<h3>Ingredienser</h3>")[1].trim();
		String[] splitta = heilesida.split("</ul>");
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
		ArrayList<Ingrediens> ing = new ArrayList<Ingrediens>();
		return new Middag(namn,folk,ing,oppskrift);
	}
}
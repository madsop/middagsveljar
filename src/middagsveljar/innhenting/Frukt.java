package middagsveljar.innhenting;

import java.util.ArrayList;
import java.util.Random;

import middagsveljar.Middag;
import middagsveljar.Middagsveljar;
import middagsveljar.buss.Bussoppslag;
import middagsveljar.data.Folk;

public class Frukt implements HentFraNett{
	private String URL = "http://www.frukt.no/oppskrift.aspx?artid=";
	private int antalArtiklar = 19715;
	private Middagsveljar middagsveljar;
	private static final int thisPlass = 0;
	
	public Frukt(Middagsveljar middagsveljar){
		this.middagsveljar = middagsveljar;
	}

	public void getTilfeldigMiddag(){
		Middag m = null;
		Random generator = new Random();
		int tilfeldigTal = -1;
		while (m == null){
			tilfeldigTal = generator.nextInt(antalArtiklar);
			System.out.println(tilfeldigTal);
			m = getMiddag(tilfeldigTal); 
		}
		System.out.println(m.getNamn());
		middagsveljar.innhentaMiddag(m,tilfeldigTal, thisPlass);
	}
	public void getMiddagFraaID(int id){
		middagsveljar.innhentaMiddag(getMiddag(id),id, thisPlass);
	}

	private Middag getMiddag(int artikkelURL) {
		String heilesida = Bussoppslag.sendGetRequest(URL+artikkelURL, null);
		Middag m = parseRaatekst(heilesida);
		return m;
	}

	private Middag parseRaatekst(String heilesida){
		System.out.println(heilesida);
		String[] split = heilesida.split("<h1>"); 
		if (split.length > 2){
			heilesida = split[2];
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
			ingrediensramt = splitta[1].split("\"text\">")[1].split("</div>")[0].trim().replaceAll("<p>�</p>", "").replaceAll("<p>", "").replaceAll("</p>", "");
			ingrediensramt = ingrediensramt.replaceAll("<P>�</P>", "");
			ingrediensramt = ingrediensramt.replaceAll("<P>", "");
			ingrediensramt = ingrediensramt.replaceAll("</P>", "");
			ingrediensramt = ingrediensramt.replaceAll("<br />"," ");
			ingrediensramt = ingrediensramt.replaceAll("<br>"," ");
			ingrediensramt = ingrediensramt.replaceAll("<BR>", " ");
			ingrediensramt = ingrediensramt.replaceAll(" 				","\n");
			System.out.println(ingrediensramt);
			ingrediensarRaa = ingrediensramt.split("\\.");
			ArrayList<String> oppskrift = new ArrayList<String>();
			for (String k : ingrediensarRaa){
				oppskrift.add(k);
			}
			System.out.println(oppskrift.size());
			
			ArrayList<Folk> folk = new ArrayList<Folk>();
			return new Middag(namn,folk,ingrediensar,oppskrift);
		}
		else {
			return null;
		}
	}
}
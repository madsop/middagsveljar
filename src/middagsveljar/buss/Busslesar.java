package middagsveljar.buss;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import middagsveljar.fillesing.Fillesar;

public class Busslesar extends Fillesar {
	public static final String Startstring = "Når%20går%20neste%20buss%20frå%20";
	public static final String tilstring = "%20til%20";
	public Busslesar(){
		filnamn = filsti+"butikkar.txt";
	}
	
	public ArrayList<Butikk> dekodButikkar(){
		try {
			bufferedreader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filnamn)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<Butikk> butikkar = new ArrayList<Butikk>();
		ArrayList<String> parsa = parseFilTilString(",");
		for (int i = 0; i < parsa.size(); i++){
			String[] splitta = parsa.get(i).split(",");
			if (splitta[0].charAt(0)!='/'){				
				butikkar.add(new Butikk(splitta[0], splitta[1]));
			}
		}
		return butikkar;
	}

	public String parseBusstuc(String tucsvar, String startplass){
		String[] tucs = tucsvar.split("Buss ");
		if (tucs.length < 2){
			return "Det går visst ikkje ein buss her på ei god stund...";
		}
		tucsvar = tucs[1];
		int bussnummer = Integer.parseInt(tucsvar.substring(0,tucsvar.indexOf(" ")));
		String klokkaFrå = tucsvar.substring(tucsvar.indexOf("kl. ")+4,tucsvar.indexOf("kl. ")+8);
		
		int setningferdig = tucsvar.indexOf(".  Tidene angir");
		String til = tucsvar.substring(0,setningferdig-9);
		int tilindeks = til.lastIndexOf("til")+4;
		til = til.substring(tilindeks);

		String klokkaTil = "";
		if (!tucsvar.contains("senere")){
			klokkaTil = tucsvar.substring(setningferdig-4,setningferdig);
		}
		else {
			til = til.substring(1);
			klokkaTil = "senere.";
		}
		
		return "Neste buss frå " +startplass +": nr. " +bussnummer +" klokka " +klokkaFrå +", er på " +til +" " +klokkaTil;
	}

	// Obs: skummelt hardkoda
	public String parseTrikk(){
		Calendar kalender = new GregorianCalendar();
		int time = kalender.get(Calendar.HOUR_OF_DAY);
		int min = kalender.get(Calendar.MINUTE);
		int vekedag = kalender.get(Calendar.DAY_OF_WEEK);
		
		int tidtilnesteoppover = -1;
		int tidtilnestenedover = -1;
		if ( (time >= 4 || (time == 3 && min > 25) ) && time < 6){
			return "Natt-trikken har slutta å gå. Hallo!";
		}
		if ( (time >= 1 || (time==0 && min > 25)) && time < 6){
			return "Det går ikkje trikk før om altfor lengje... Legg deg, din slask!";
		}
		
		if (vekedag == 0 || time > 18 || (vekedag==5 && (time < 9 || time > 15) )){ // For kvar-halvtime-periodane
			if (min > 25 && min <= 55){ // Nedover
				tidtilnestenedover = 55-min;
			}
			else if (min > 55){
				tidtilnestenedover = 25+(60-min);
			}
			else {
				tidtilnestenedover = 25-min;
			}
			if (min > 23 && min <= 53){ // Oppover
				tidtilnesteoppover = 53-min;
			}
			else if ( min > 53){
				tidtilnesteoppover = 23+(60-min);
			}
			else {
				tidtilnesteoppover = 23-min;
			}
		}
		else if (time < 1){
			if (min < 25){
				String retstring="Neste trikk nedover går om altfor lenge.\n";
				tidtilnesteoppover = 25-min;
				retstring+="Neste trikk oppover går om " +tidtilnesteoppover +" minutt. \n";
				return retstring;	
			}
			else {
				return "Det går ikkje trikk før om altfor lenge..";
			}
		}
		else {
			if (min < 10){
				tidtilnestenedover = 10-min;
			}
			else if (min >= 10 && min < 25){
				tidtilnestenedover = 25-min;
			}
			else if (min >= 25 && min < 40){ // Nedover
				tidtilnestenedover = 40-min;
			}
			else if (min >= 40 && min < 55){
				tidtilnestenedover = 55-min;
			}
			else if (min >= 55){
				tidtilnestenedover = 10+(60-min);
			}
			if (min < 8){
				tidtilnesteoppover = 8-min;
			}
			else if (min >= 8 && min < 23){
				tidtilnesteoppover = 23-min;
			}
			else if (min >= 23 && min < 38){ // Oppover
				tidtilnesteoppover = 38-min;
			}
			else if (min >= 38 && min < 53){
				tidtilnesteoppover = 53-min;
			}
			else if (min >= 53){
				System.out.println(min);
				tidtilnesteoppover = 8+(60-min);
			}
		}
		
		String retstring = "";
		retstring+="Neste trikk nedover går om " +tidtilnestenedover +" minutt.\n";
		retstring+="Neste trikk oppover går om " +tidtilnesteoppover +" minutt. \n";
		return retstring;
	}
}
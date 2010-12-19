package middagsveljar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Busslesar extends Fillesar {
	public static final String Startstring = "Når%20går%20neste%20buss%20frå%20";
	public static final String tilstring = "%20til%20";
	public Busslesar(){
		filnamn = filsti+"butikkar.txt";
	}
	
	public ArrayList<Butikk> dekodButikkar(){
		try {
			bufferedreader = new BufferedReader(new FileReader(filnamn));
		} catch (FileNotFoundException e) {
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
		tucsvar = tucsvar.split("Buss ")[1];
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
			//klokkaTil = tucsvar.substring(tilindeks,)
		}
		
		return "Neste buss frå " +startplass +": nr. " +bussnummer +" klokka " +klokkaFrå +", er på " +til +" " +klokkaTil;
	}

	public String parseTrikk(){
		Calendar kalender = new GregorianCalendar();
		int time = kalender.get(Calendar.HOUR_OF_DAY);
		int min = kalender.get(Calendar.MINUTE);
		int vekedag = kalender.get(Calendar.DAY_OF_WEEK);
		
		int tidtilnesteoppover = -1;
		int tidtilnestenedover = -1;
		if (time > 1 && time < 6){
			return "Det går ikkje trikk før om altfor lengje...";
		}
		if (vekedag == 1 || time > 18 || (vekedag==6 && (time < 9 || time > 15) )){ // For kvar-halvtime-periodane
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
		else {
			if (min > 25 && min <= 40){ // Nedover
				tidtilnestenedover = 40-min;
			}
			else if (min > 10 && min <= 25){
				tidtilnestenedover = 25-min;
			}
			else if (min > 55){
				tidtilnestenedover = 10+(60-min);
			}
			else if (min > 40 && min <= 55){
				tidtilnestenedover = 55-min;
			}
			else {
				tidtilnestenedover = 10-min;
			}
			if (min > 23 && min <= 38){ // Oppover
				tidtilnesteoppover = 40-min;
			}
			else if (min > 8 && min <= 23){
				tidtilnestenedover = 23-min;
			}
			else if (min > 53){
				tidtilnestenedover = 8+(60-min);
			}
			else if (min > 38 && min <= 53){
				tidtilnestenedover = 53-min;
			}
			else {
				tidtilnestenedover = 8-min;
			}
		}
		
		String retstring = "";
		retstring+="Neste trikk nedover går om " +tidtilnestenedover +" minutt.\n";
		retstring+="Neste trikk oppover går om " +tidtilnesteoppover +" minutt. \n";
		return retstring;
	}
}
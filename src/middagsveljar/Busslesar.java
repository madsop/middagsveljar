package middagsveljar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

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
		System.out.println(parsa.size());
		for (int i = 0; i < parsa.size(); i++){
			String[] splitta = parsa.get(i).split(",");
			if (splitta[0].charAt(0)!='/'){				
				butikkar.add(new Butikk(splitta[0], splitta[1]));
			}
		}
		return butikkar;
	}
}
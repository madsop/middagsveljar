package middagsveljar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Fillesar {
	public static final String filnamn = "/home/mads/Dropbox/Programmering/Eclipse-workspace/middagsveljar/src/middagsveljar/liste.txt";
	private File fil;
	private BufferedReader bufferedreader;
	private int[] antalAvKvar;
	
	public int[] getAntalAvKvar(){
		return antalAvKvar;
	}
	
	public Fillesar(){
		try {
			fil = new File(filnamn);
			bufferedreader = new BufferedReader(new FileReader(fil));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> parseFilTilString(){
		ArrayList<String> lesiInn = new ArrayList<String>();
		String readline = "";
		System.out.println(bufferedreader);
		try {
			while ((readline = bufferedreader.readLine() ) != null){
				if (readline.contains(":")){
					lesiInn.add(readline);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lesiInn;
	}

	public ArrayList<String> kuttBort(ArrayList<String> lesiInn){
		ArrayList<String> kuttaBort = new ArrayList<String>();
		String[] splitta;
		for (int i = 0; i < lesiInn.size(); i++){
			splitta = lesiInn.get(i).split(":");
			if (splitta[1].equals("-") && splitta[1].split(" ")[0].equals("-")){ // - altså ingen
			}
			else {
				if (splitta[1].contains("(")){
					//splitta[1] = splitta[1].split("\\(")[0];
				}
				if (splitta[1].contains("{")){
					splitta[1] = splitta[1].split("\\{")[0];
				}
				splitta[1] = splitta[1].split("	")[1];
				kuttaBort.add(splitta[1]);
			}
		}
		return kuttaBort;
	}
	
	public void telAntalAvKvar(ArrayList<Middag> middagar){
		antalAvKvar = new int[middagar.size()];
		ArrayList<String> lesiInn = parseFilTilString();
		lesiInn = kuttBort(lesiInn);
		for (int i = 0; i < lesiInn.size(); i++){
			for (int j = 0; j < middagar.size(); j++){
				if (lesiInn.get(i).toLowerCase().contains(middagar.get(j).getNamn().toLowerCase())){
					antalAvKvar[j]++;
				}
			}
		}
		int teljar = 0;
		for (int k = 0; k < antalAvKvar.length; k++){
			if (antalAvKvar[k] > 0){
				System.out.println(middagar.get(k).getNamn() +": " +antalAvKvar[k] + " gongar");
			}
			teljar+=antalAvKvar[k];
		}
		System.out.println("Oppfatta totalt " +teljar +" middagar.");
	}
}

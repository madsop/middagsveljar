package middagsveljar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Fillesar {
	protected String filnamn = "/home/mads/Dropbox/Programmering/Eclipse-workspace/middagsveljar/src/middagsveljar/liste.txt";
	private File fil;
	protected BufferedReader bufferedreader;
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

	public ArrayList<String> parseFilTilString(String notcontains){
		ArrayList<String> lesiInn = new ArrayList<String>();
		String readline = "";
		try {
			while ((readline = bufferedreader.readLine() ) != null){
				if (readline.contains(notcontains)){
					lesiInn.add(readline);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lesiInn;
	}

	public ArrayList<String> kuttBort(ArrayList<String> lesiInn){
		ArrayList<String> kuttaBort = new ArrayList<String>();
		String[] splitta;
		for (int i = 0; i < lesiInn.size(); i++){
			splitta = lesiInn.get(i).split(":	");
			if (!(splitta[1].contains("-") && splitta[1].charAt(0)=='-')){ // - altså ingen
				if (splitta[1].contains("(") && splitta[1].charAt(0)!='('){
					splitta[1] = splitta[1].split("\\(")[0];
				}
				if (splitta[1].contains("{") && splitta[1].charAt(0)!='{'){
					splitta[1] = splitta[1].split("\\{")[0];
				}
				if (!splitta[1].contains("/")){
					kuttaBort.add(splitta[1]);
				}
			}
		}
		return kuttaBort;
	}

	public void telAntalAvKvar(ArrayList<Middag> middagar){
		antalAvKvar = new int[middagar.size()];
		ArrayList<String> lesiInn = parseFilTilString(":");
		lesiInn = kuttBort(lesiInn);
		ArrayList<String> attst = new ArrayList<String>();
		
		for (int i = 0; i < lesiInn.size(); i++){
			boolean lagttil = false;
			for (int j = 0; j < middagar.size(); j++){
				if (lesiInn.get(i).toLowerCase().contains(middagar.get(j).getNamn().toLowerCase())){
					antalAvKvar[j]++;
					lagttil = true;
				//	System.out.println("Fann middag " +lesiInn.get(i) +", matcha " +middagar.get(j).getNamn());
				}
			}	
			if (!lagttil && !lesiInn.get(i).toLowerCase().contains("pizza")){
				attst.add(lesiInn.get(i));
			}
		}
		for (int k = 0; k < attst.size(); k++){
			System.out.println("Attstår: " +attst.get(k));
		}
		System.out.println("Attst.size: " +attst.size());
		int teljar = 0;
		for (int k = 0; k < antalAvKvar.length; k++){
			if (antalAvKvar[k] > 0){
				//System.out.println(middagar.get(k).getNamn() +": " +antalAvKvar[k] + " gongar");
			}
			teljar+=antalAvKvar[k];
		}
		System.out.println("Oppfatta totalt " +teljar +" middagar.");
	}
}

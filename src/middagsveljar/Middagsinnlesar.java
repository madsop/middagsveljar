package middagsveljar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Middagsinnlesar extends Fillesar{

	public Middagsinnlesar(){
		filnamn = "/home/mads/Dropbox/Programmering/Eclipse-workspace/middagsveljar/src/middagsveljar/middagar.txt";
	}

	public ArrayList<Middag> lesInnMiddag(){
		try {
			bufferedreader = new BufferedReader(new FileReader(filnamn));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> lesiInn = parseFilTilString("__");
		ArrayList<Middag> middagar = new ArrayList<Middag>();
		for (int i = 0; i < lesiInn.size(); i++){
			String[] splitta = lesiInn.get(i).split("__");
			String namn = splitta[0];
			ArrayList<Folk> likerIkkje = new ArrayList<Folk>();
			if (!splitta[1].equals("-")){
				String[] ikkjeLikande = splitta[1].split(",");
				for (int k = 0; k < ikkjeLikande.length; k++){
					Folk f = Folk.valueOf(ikkjeLikande[k]);
					likerIkkje.add(f);
				}
			}
			ArrayList<Ingrediens> ingrediensar = new ArrayList<Ingrediens>();
			if(!splitta[2].equals("-")){	
				String[] ingrediens = splitta[2].split(",");
				for (int j = 0; j < ingrediens.length; j++){
					ingrediensar.add(Ingrediens.valueOf(ingrediens[j]));
				}
			}
			ArrayList<String> oppskrift = new ArrayList<String>();
			if(!splitta[2].equals("-")){	
				String[] oppskriftstring = splitta[2].split(",");
				for (int j = 0; j < oppskriftstring.length; j++){
					oppskrift.add(oppskriftstring[j]);
				}
			}
			middagar.add(new Middag(namn, likerIkkje, ingrediensar, oppskrift));
		}
		try {
			bufferedreader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Middagslista vår har totalt " +middagar.size() +" middagar.");
		return middagar;
	}
}

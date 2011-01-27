package middagsveljar.fillesing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import middagsveljar.Middag;
import middagsveljar.data.Folk;
import middagsveljar.data.Ingrediens;

public class Middagsinnlesar extends Fillesar{
	private static final String attributtskilje = "__";

	public Middagsinnlesar(){
		filnamn=filsti+"middagar.txt";
	}

	public ArrayList<Middag> lesInnMiddag(){
		try {
			bufferedreader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filnamn)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<String> lesiInn = parseFilTilString(attributtskilje);
		ArrayList<Middag> middagar = new ArrayList<Middag>();	
		for (int i = 0; i < lesiInn.size(); i++){
			String[] splitta = lesiInn.get(i).split(attributtskilje);
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

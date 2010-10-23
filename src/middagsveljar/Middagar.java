package middagsveljar;

import java.util.ArrayList;

public class Middagar {
	private ArrayList<Middag> middagar;
	public Middagar() {
		middagar = new ArrayList<Middag>();
		ArrayList<Folk> kjotthaldig = new ArrayList<Folk>();
		kjotthaldig.add(Folk.Britti);
		ArrayList<Folk> vegetarvennleg = new ArrayList<Folk>();
		
		ArrayList<Ingrediens> tomHandleliste = new ArrayList<Ingrediens>();
		ArrayList<String> tomOppskrift = new ArrayList<String>();
		
		// Taco
		ArrayList<Ingrediens> tacoHandleliste = new ArrayList<Ingrediens>();
		tacoHandleliste.add(Ingrediens.kjøttdeig);
		tacoHandleliste.add(Ingrediens.ost);
		tacoHandleliste.add(Ingrediens.tacokrydder);
		tacoHandleliste.add(Ingrediens.rømme);
		tacoHandleliste.add(Ingrediens.paprika);
		tacoHandleliste.add(Ingrediens.agurk);
		tacoHandleliste.add(Ingrediens.tomat);
		tacoHandleliste.add(Ingrediens.mais);
		tacoHandleliste.add(Ingrediens.ananas);
		tacoHandleliste.add(Ingrediens.lefse);
		tacoHandleliste.add(Ingrediens.jalapeños);
		tacoHandleliste.add(Ingrediens.lauk);
		tacoHandleliste.add(Ingrediens.tacosaus);
		tacoHandleliste.add(Ingrediens.salat);
		middagar.add(new Middag("Taco/Burritos", vegetarvennleg,tacoHandleliste,tomOppskrift));
		
		// Nachos
		ArrayList<Ingrediens> nachosHandleliste = new ArrayList<Ingrediens>();
		nachosHandleliste.add(Ingrediens.kjøttdeig);
		nachosHandleliste.add(Ingrediens.nachochips);
		nachosHandleliste.add(Ingrediens.ost);
		nachosHandleliste.add(Ingrediens.tacokrydder);
		nachosHandleliste.add(Ingrediens.tomatsaus);
		middagar.add(new Middag("Nachos", vegetarvennleg,nachosHandleliste,tomOppskrift));
		
		// Pasta di Parma
		ArrayList<Ingrediens> pastaDiParmaHandleliste = new ArrayList<Ingrediens>();
		pastaDiParmaHandleliste.add(Ingrediens.pasta_pose);
		pastaDiParmaHandleliste.add(Ingrediens.mjølk);
		pastaDiParmaHandleliste.add(Ingrediens.kvitlauksbrød);
		pastaDiParmaHandleliste.add(Ingrediens.kjøttdeig);
		pastaDiParmaHandleliste.add(Ingrediens.salat);
		pastaDiParmaHandleliste.add(Ingrediens.mais);
		pastaDiParmaHandleliste.add(Ingrediens.agurk);
		pastaDiParmaHandleliste.add(Ingrediens.paprika);
		middagar.add(new Middag("Pasta di Parma", vegetarvennleg,pastaDiParmaHandleliste,tomOppskrift));
		
		
		middagar.add(new Middag("Baguettar", vegetarvennleg,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Spaghetti ala Ålz", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Mekso-amerikansk gryte", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Tacogryte", vegetarvennleg,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Spansk gryte", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Lasagne", vegetarvennleg,tomHandleliste,tomOppskrift));
		
		Middag mattasGryte = new Middag("Mattas gryte", kjotthaldig,tomHandleliste,tomOppskrift); 
		mattasGryte.likerIkkje(Folk.Marta);
		middagar.add(mattasGryte);
		
		middagar.add(new Middag("Laksekotelettar/-filetar", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Ørretfilet", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Laksetagliatelle", vegetarvennleg,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Fiskepinnar", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Burger", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Lam i rull", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Heimelaga pizza", vegetarvennleg,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Kyllingfilet", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Grilla kylling", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Kylling i baconost-saus", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Wok", vegetarvennleg,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Zhonguá", vegetarvennleg,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Grønn pasta ala Silje", vegetarvennleg,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Rema-pizza", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Karibisk gryte", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Tomatsuppe", vegetarvennleg,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Svinekotelettar", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Løvbiff", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Fylte bakte potetar", vegetarvennleg,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Chili con carne", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Pannekaker", vegetarvennleg,tomHandleliste,tomOppskrift));
	
		Middag risotto = new Middag("Risotto", kjotthaldig,tomHandleliste,tomOppskrift);
		risotto.likerIkkje(Folk.Britti);
		risotto.likerIkkje(Folk.Matta);
		middagar.add(risotto);

		middagar.add(new Middag("Kalkun", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Tikka Masala", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Lapskaus", vegetarvennleg,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Osteschnitzel", kjotthaldig,tomHandleliste,tomOppskrift));	
		middagar.add(new Middag("Flintsteik", kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Pitapizza", vegetarvennleg,tomHandleliste,tomOppskrift));
		
		Middag fyltPaprika = new Middag("Fylt paprika", kjotthaldig,tomHandleliste,tomOppskrift);
		fyltPaprika.likerIkkje(Folk.Elias);
		middagar.add(fyltPaprika);
		
		
		middagar.add(new Middag("Ovnsgratinert laks",kjotthaldig,tomHandleliste,tomOppskrift));
		middagar.add(new Middag("Falafel",vegetarvennleg,tomHandleliste,tomOppskrift));
		
		Middag fiskegrateng = new Middag("Fiskegrateng",kjotthaldig,tomHandleliste,tomOppskrift);	
		fiskegrateng.likerIkkje(Folk.Elias);
		fiskegrateng.likerIkkje(Folk.Matta);
		middagar.add(fiskegrateng);
		
		Middag graut = new Middag("Graut",kjotthaldig,tomHandleliste,tomOppskrift);
		graut.likerIkkje(Folk.Elias);
		middagar.add(graut);
		
		Middag vårrullar = new Middag("Vårrullar",kjotthaldig,tomHandleliste,tomOppskrift);
		vårrullar.likerIkkje(Folk.Elias);
		middagar.add(vårrullar);
		
		middagar.add(new Middag("Seifilet",kjotthaldig,tomHandleliste,tomOppskrift));
		
		Middag kyllingsuppe = new Middag("Krema kyllingsuppe",kjotthaldig,tomHandleliste,tomOppskrift);
		kyllingsuppe.likerIkkje(Folk.Elias);
		middagar.add(kyllingsuppe);
		
		// Veggishimmel
		ArrayList<Ingrediens> veggishimmelHandleliste = new ArrayList<Ingrediens>();
		veggishimmelHandleliste.add(Ingrediens.sopp);
		veggishimmelHandleliste.add(Ingrediens.fløtegratinerte_potetar);
		veggishimmelHandleliste.add(Ingrediens.peppersaus);
		veggishimmelHandleliste.add(Ingrediens.lauk);
		veggishimmelHandleliste.add(Ingrediens.kvitlauk);
		veggishimmelHandleliste.add(Ingrediens.brokkoli);
		Middag veggishimmel = new Middag("Veggishimmel",vegetarvennleg,veggishimmelHandleliste,tomOppskrift);
		veggishimmel.likerIkkje(Folk.Britti); // ??
		veggishimmel.likerIkkje(Folk.Matta); // ??
		middagar.add(veggishimmel);
		
		Middag stektRis = new Middag("Stekt ris", vegetarvennleg, tomHandleliste, tomOppskrift);
		middagar.add(stektRis);
	
		Middag paella = new Middag("Paella", vegetarvennleg, tomHandleliste, tomOppskrift);
		middagar.add(paella);
		
		Middag fiskMasala = new Middag("Fisk masala", kjotthaldig, tomHandleliste, tomOppskrift);
		middagar.add(fiskMasala);
		
		// Squashgrateng
		ArrayList<Ingrediens> squashgratengHandleliste = new ArrayList<Ingrediens>();
		squashgratengHandleliste.add(Ingrediens.kjøttdeig);
		squashgratengHandleliste.add(Ingrediens.kvitlauk);
		squashgratengHandleliste.add(Ingrediens.lauk);
		squashgratengHandleliste.add(Ingrediens.smør);
		squashgratengHandleliste.add(Ingrediens.hakka_tomat);
		squashgratengHandleliste.add(Ingrediens.oregano);
		squashgratengHandleliste.add(Ingrediens.squash);
		squashgratengHandleliste.add(Ingrediens.tomat);
		squashgratengHandleliste.add(Ingrediens.ost);
		
		ArrayList<String> squashOppskrift = new ArrayList<String>();
		squashOppskrift.add("Brun kjøttdeigen i smør i en panne");
		squashOppskrift.add("Tilsett hakkede tomater, oregano, salt, pepper og hakket hvitløk.");
		squashOppskrift.add("La det koke i ca. 10 minutter.");
		squashOppskrift.add("Legg kjøttdeigen i en ildfast form.");
		squashOppskrift.add("Skjær løk, squash og tomater i tynne skiver, og legg det taksteinslagt om hverandre over kjøttdeigen");
		squashOppskrift.add("Strø på revet ost.");
		squashOppskrift.add("Stekes ved 225 grader C i ca. 20 minutter.");
		squashOppskrift.add("Server med kokte poteter, ris eller pasta.");
		
		Middag squashgrateng = new Middag("Squashgrateng", vegetarvennleg, squashgratengHandleliste, squashOppskrift);
		squashgrateng.likerIkkje(Folk.Matta);
		middagar.add(squashgrateng);
		
		Middag faarikaal = new Middag("Fårikål",kjotthaldig, tomHandleliste, tomOppskrift);
		middagar.add(faarikaal);
		
		Middag fransktoast = new Middag("Fransk toast", vegetarvennleg, tomHandleliste, tomOppskrift);
		middagar.add(fransktoast);
	}
	
	public ArrayList<Middag> getMiddagar() {
		return middagar;
	}
	
}
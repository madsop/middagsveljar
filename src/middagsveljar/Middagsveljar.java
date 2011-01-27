package middagsveljar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.*;

import middagsveljar.buss.Busskontakt;
import middagsveljar.data.Folk;
import middagsveljar.data.Ingrediens;
import middagsveljar.fillesing.Fillesar;
import middagsveljar.fillesing.Middagsinnlesar;


/**
 * Hovudklassa i prosjektet.
 * @author mads
 *
 */
public class Middagsveljar extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private MiddagsveljarModell middagsmodell;
	private Middagsinnlesar mil;
	private final int breidde = 1;
	private JTextField velMiddagHjelpar;
	private JButton velMiddagKnapp,tilfeldigAntalMiddagar;
	private JButton visIngrediensar,visOppskrift,visBuss;
	private GridBagLayout gbl;
	private JLabel velLabel;
	private JList middagarListe;
	private JPanel kvenErHer;
	private JCheckBox kven[];
	public static String newline = System.getProperty("line.separator");
	private Busskontakt busskontakt;
	private Thread bussthread;
	
	private Fillesar fillesar;

	/**
	 * Enkel konstruktør som lager gui.
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Vel middag");
		frame.setContentPane(new Middagsveljar());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Fiksar boksane for kven som er her.
	 */
	public void fiksKvenErHer(){
		kvenErHer = new JPanel();
		kvenErHer.setPreferredSize(new Dimension(100,300));
		kven = new JCheckBox[Folk.values().length];
		for (int i = 0; i < kven.length; i++){
			kven[i] = new JCheckBox();
		}

		for (int i = 0; i < kven.length; i++){
			kven[i].setText(Folk.values()[i].toString());
			kvenErHer.add(kven[i]);
		}
	}

	/**
	 * Lager alle GUI-variablar og gir dei meining.
	 */
	public void settOppGUI(){
		
		gbl = new GridBagLayout();
		GridBagConstraints c;
		setLayout(gbl);
		c = new GridBagConstraints();
		c.ipadx = 20;
		c.ipady = 10;
		c.anchor = GridBagConstraints.WEST;

		velMiddagHjelpar = new JTextField(Integer.toString(middagsmodell.getAntalMiddagar()));
		velMiddagHjelpar.setColumns(breidde);
		velMiddagHjelpar.addKeyListener(new KorMangeHjelpar());

		velMiddagKnapp = new JButton();
		velMiddagKnapp.setText("Vel!");
		velMiddagKnapp.addActionListener(new VeljarHjelpar(this));

		tilfeldigAntalMiddagar = new JButton();
		tilfeldigAntalMiddagar.setText("Tilfeldig antal");
		tilfeldigAntalMiddagar.addActionListener(new VeljarHjelpar(this));

		visIngrediensar = new JButton("Vis ingrediensar!");
		visIngrediensar.addActionListener(new VeljarHjelpar(this));

		visOppskrift = new JButton("Vis oppskrift!");
		visOppskrift.addActionListener(new VeljarHjelpar(this));
		
		visBuss = new JButton("Neste buss!");
		visBuss.addActionListener(new VeljarHjelpar(this));

		fiksKvenErHer();

		velLabel = new JLabel("Antal middagar");

		middagarListe = new JList(middagsmodell);

		JScrollPane sp = new JScrollPane();
		sp.setPreferredSize(new Dimension(380, 600));
		sp.getViewport().add(middagarListe);

		c.gridx = 0;
		c.gridy = 1;
		add(velLabel,c);

		c.gridx = 0;
		c.gridy = 2;
		add(velMiddagHjelpar,c);

		c.gridx = 0;
		c.gridy = 3;
		add(velMiddagKnapp,c);

		c.gridy = 4;
		add(tilfeldigAntalMiddagar,c);

		c.gridy = 5;
		add(visIngrediensar,c);

		c.gridy = 6;
		add(visOppskrift,c);

		c.gridy = 7;
		add(visBuss,c);
		
		c.gridy = 8;
		add(kvenErHer,c);

		c.gridheight = 9;
		c.gridx = 3;
		c.gridy = 0;
		add(sp,c);
	}

	/**
	 * Lager ein ny modell, kallar oppsettet av gui og gjer eit startval.
	 */
	public Middagsveljar() {
		mil = new Middagsinnlesar();
		middagsmodell = new MiddagsveljarModell(mil.lesInnMiddag());
		middagsmodell.addPropertyChangeListener(this);
		
		fillesar = new Fillesar(); 
		fillesar.telAntalAvKvar(middagsmodell.getMiddagar());
		middagsmodell.setAntalAvKvar(fillesar.getAntalAvKvar());

		settOppGUI();
		busskontakt = new Busskontakt(this);

		kast();
	}
	
	public void oppdaterGUImedBuss(String svarstreng){
		JOptionPane.showMessageDialog(null, svarstreng,"Neste buss...",JOptionPane.INFORMATION_MESSAGE);
		bussthread.interrupt();
	}

	/**
	 * Veljer ein aktuell middag som er i orden for alle som er her. 
	 */
	public void kast () {
		for (int i = 0; i < middagsmodell.getAntalMiddagar(); i++) {
			int j = middagsmodell.nyttMiddagsnummer();

			Middag aktuellMiddag = middagsmodell.getMiddagar().get(j);

			boolean[] desseErHer = new boolean[kven.length];
			for (int person = 0; person < desseErHer.length; person++){
				desseErHer[person] = kven[person].isSelected();
			}

			boolean liktAvAlleAktuelle = greittForAlleInvolverte(desseErHer, aktuellMiddag);
			while (!liktAvAlleAktuelle){
				j = middagsmodell.nyttMiddagsnummer();
				aktuellMiddag = middagsmodell.getMiddagar().get(j);
				liktAvAlleAktuelle = greittForAlleInvolverte(desseErHer, aktuellMiddag);
			}
			if (greittForAlleInvolverte(desseErHer, aktuellMiddag)){
				middagsmodell.IncreaseElementAt(j);
			}
		}
		middagsmodell.setAntalAvKvar(fillesar.getAntalAvKvar()); //a
	}

	/**
	 * Sjekkar om maten som foreslås er ok for alle som skal ha.
	 * @param involverte
	 * @param middag
	 * @return temp
	 */
	public boolean greittForAlleInvolverte(boolean[] involverte, Middag middag){
		boolean temp = true;
		for (int i = 0; i < involverte.length; i++){
			if (involverte[i] == true){
				if (middag.getIkkjeLiktAv().contains(Folk.values()[i])){
					temp = false;
				}
			}
		}
		return temp;
	}


	/**
	 * Handsamar antal middagar ein vil ha.
	 * @author mads
	 *
	 */
	class KorMangeHjelpar implements KeyListener {
		public void keyPressed(KeyEvent arg0) {}
		public void keyReleased(KeyEvent arg0) {
			int t = Integer.parseInt(velMiddagHjelpar.getText());
			try { middagsmodell.nyMiddagVald(t);}
			catch (NumberFormatException nfe) { }
		}
		public void keyTyped(KeyEvent arg0) {}					
	}

	/**
	 * Handsamar veljinga av ny middag. 
	 * @author mads
	 * 
	 */
	class VeljarHjelpar implements ActionListener {
		Middagsveljar t;

		public VeljarHjelpar(Middagsveljar t) {
			this.t = t;
		}

		/** Brukaren vil ha ny(e) middag(ar) */
		public void velMiddagsknappTrykt(){
			middagsmodell = new MiddagsveljarModell(mil.lesInnMiddag());
			middagsmodell.addPropertyChangeListener(t);
			middagarListe.setModel(middagsmodell);
			kast();
		}

		/** Brukaren vil sjå ingrediensane til den valde middagen */
		public void visIngrediensar(){
			ArrayList<Ingrediens> ingrediensar;
			Middag valdMiddag;
			if (middagarListe.isSelectionEmpty()){
				int forsteValdeMiddag = middagsmodell.getForsteValdeMiddagInt();
				//Middag forsteValdeMiddag = middagsmodell.getForsteValdeMiddag();
				valdMiddag = middagsmodell.getMiddagar().get(forsteValdeMiddag);
				ingrediensar = valdMiddag.getIngrediensar();
			}
			else{
				int vald = middagarListe.getSelectedIndex();
				valdMiddag = middagsmodell.getMiddagar().get(vald);
				ingrediensar = valdMiddag.getIngrediensar();
			}

			if (ingrediensar.size() > 0){
				String ingrediensarVisbar = "";
				for (int i = 0; i < ingrediensar.size(); i++){
					ingrediensarVisbar += i +": " +ingrediensar.get(i) + '\n';
				}
				JOptionPane.showMessageDialog(null, ingrediensarVisbar, valdMiddag.getNamn() +"-ingrediensar", JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null, "Har ikkje kommi så langt enno,dessverre", "Viser ingrediensar", 1);
			}
		}

		/** Brukaren vil sjå oppskrifta til den valde middagen */
		public void visOppskrift(){
			Middag valdMiddag;
			ArrayList<String> oppskrift;
			if (middagarListe.isSelectionEmpty()){
				valdMiddag = middagsmodell.getMiddagar().get(middagsmodell.getForsteValdeMiddagInt());
			}
			else{
				valdMiddag = middagsmodell.getMiddagar().get(middagarListe.getSelectedIndex());
			}
			oppskrift = valdMiddag.getOppskrift();

			if (oppskrift.size() > 0){
				String oppskriftVisbar = "";
				for (int i = 0; i < oppskrift.size(); i++){
					oppskriftVisbar += i +": " +oppskrift.get(i) + '\n';
				}
				JOptionPane.showMessageDialog(null, oppskriftVisbar,  valdMiddag.getNamn() +"-oppskrift", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "Har ikkje kommi så langt enno,dessverre", "Viser oppskrift", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		public void visBuss(){
			bussthread = new Thread(busskontakt);
			System.out.println(bussthread);
			bussthread.start();
		}

		/** Brukaren trykkar på ein knapp */
		public void actionPerformed(ActionEvent arg0) {
			/** Ein vil ha ein ny middag. Da lager ein ein ny modell og viser denne. */
			if (arg0.getSource() == velMiddagKnapp) {
				velMiddagsknappTrykt();
			}
			/** Ein får eit tilfeldig antal middagar */
			if (arg0.getSource() == tilfeldigAntalMiddagar) {
				middagsmodell.nyMiddagVald(middagsmodell.nyttMiddagsnummer());
			}
			/** Vis ingrediensane for den valde middagen */
			if (arg0.getSource() == visIngrediensar) {
				visIngrediensar();
			}
			/** Vis oppskrifta for den valde middagen */
			if (arg0.getSource() == visOppskrift) {
				visOppskrift();
			}
			if (arg0.getSource() == visBuss){
				visBuss();
			}
		}
	}
	/**
	 * Kva skjer om noko blir endra i modellen?
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		/** Foreløpig er det berre dette, antal middagar, som kan endres */
		if (evt.getPropertyName() == MiddagsveljarModell.ANTAL_PROPERTY) {
			velMiddagHjelpar.setText(Integer.toString(middagsmodell.getAntalMiddagar()));
		}
	}
}

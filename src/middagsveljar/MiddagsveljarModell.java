package middagsveljar;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractListModel;


public class MiddagsveljarModell extends AbstractListModel{
	
	private static final long serialVersionUID = 1L;
	private Middagar middagar;
	private int antalMiddagar;
	public final static String ANTAL_PROPERTY = "kast";
	public final static String RESULTAT_PROPERTY = "resultat";
	private PropertyChangeSupport pcs;
	private Random generator;
	private int[] resultat;

	/**
	 * Listar opp alle middagane vi har.
	 * @return Alle middagar i databasen
	 */
	public ArrayList<Middag> getMiddagar(){
		return middagar.getMiddagar();
	}
	
	/**
	 * Lager ein ny modell. Oppretter ein hjelpar, ein tilfeldig-generator og
	 * vel nye middagar for alle dagane ønska.
	 * @param antalMiddagar - kor mange middagar vil vi ha?
	 */
	public MiddagsveljarModell(int antalMiddagar) {
		pcs = new PropertyChangeSupport(this);
		generator = new Random();
		middagar = new Middagar();
		nyMiddagVald(antalMiddagar);
		resultat = new int[middagar.getMiddagar().size()];
	}
	
	/**
	 * Kor mange middagar er det eigentleg vi vil ha?
	 * @return kor mange middagar modellen har
	 */
	public int getAntalMiddagar() {
		return antalMiddagar;
	}
	
	/**
	 * Forteller alle som bryr seg at vi har vald eit nytt antal middagar.
	 * @param antalMiddagar - kor mange middagar skal vi ha?
	 */
	public void nyMiddagVald(int antalMiddagar) {
		int oldValue = this.antalMiddagar;
		this.antalMiddagar = antalMiddagar;
		pcs.firePropertyChange(ANTAL_PROPERTY, oldValue, antalMiddagar);
	}
	/**
	 * Finn ein ny middag.
	 * @return Eit tal innanfor storleiken til middagsdatabasen.
	 */
	public int nyttMiddagsnummer() {
		return generator.nextInt(middagar.getMiddagar().size());
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
	public Object getElementAt(int index) {
		if (resultat[index] == 0) {
			return null;
		}
		int i = index+1;
		
		if (resultat[index] == 1) {
			return "Middag nr " +i + ": " +middagar.getMiddagar().get(index).getNamn();
		}
		
		if (resultat[index] > 1) {
			return resultat[index] + " gongar middag nr " +i +": " +middagar.getMiddagar().get(index).getNamn();
		}
		return null;
	}
	
	public int getSize() {
		return middagar.getMiddagar().size();
	}
	public void IncreaseElementAt(int index) {
		resultat[index]+=1;
	}
	public int getResultat(Middag middag){
		int index = -1;
		for (int i = 0; i < middagar.getMiddagar().size(); i++){
			if (middag==middagar.getMiddagar().get(i)){
				index = i;
			}
		}
		return resultat[index];
	}
	
	public Middag getForsteValdeMiddag(){
		Middag temp = null;
		for (int i = 0; i < resultat.length; i++){
			if (i>0){
				temp = middagar.getMiddagar().get(i);
				break;
			}
		}
		return temp;
	}
	
	public int getForsteValdeMiddagInt(){
		for (int i = 0; i < resultat.length; i++){
			if (resultat[i]>0){
				return i;
			}
		}
		return -1;
	}
}
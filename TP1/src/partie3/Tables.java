package partie3;

import java.util.ArrayList;

public class Tables {
	
	//permet d'avoir des tables avec des numéros différents
	
	public ArrayList<Clients> listeClients = new ArrayList<Clients>();
	private double montantTotal = 0;
	private int numeroTable;
	
	public Tables(ArrayList<Clients> listeClients, int numeroTable){
		
		this.listeClients = listeClients;
		this.numeroTable = numeroTable;
		
	}




}

package partie3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class ChezBarette {
	
	private String nomFic = "ChezBarette.txt";
	private BufferedReader fic;
	private boolean valide = true;
	private Clients leClient;
	
	private ArrayList<Clients> listeClients = new ArrayList<Clients>();
	private ArrayList<Plats> listePlats = new ArrayList<Plats>();
	//private ArrayList<Commandes> listeCommandes;
	
	public ChezBarette(){
		
		
		//lecture du fichier texte
		
		try {
			
			fic = ouvrirFicTexteLecture(nomFic);
			
			String ligne = "";
			

			int status = 0;
			
			//tant que le fichier text n'est pas termin� et que la facture est valide
			while ( (ligne = fic.readLine() ) != null && valide ){
			
		
				if ( !ligne.equals("Clients:") && !ligne.equals("Plats:") && !ligne.equals("Commandes:") && !ligne.equals("Fin")){
					
				
					switch (status){
					
					case 1:	
							if (verifierClient(ligne)){
								
								Clients client = new Clients(ligne);
								listeClients.add(client);;
							}
							

							break;
					case 2:
							ajouterPlat(ligne);
							
							verifierPlat(ligne);
							break;
					//si ce n'est pas valide on arrete la lecture du document
					case 3:
							valide = verifierCommande(ligne);
							break;
					
					}
					
				} else {
					
					//le status va permettre de changer d'instruction selon o� nous somme rendu dans le fichier 
					// status 1 = clients , status 2 = plats, status 3 = commandes
					status++;
				}
				
	
				
			}
			
		}
		
		catch (Exception e){
			
		}
		
		//message de sortie
		facture( valide );

		
		
	}
	
	public void facture(boolean valide) {
		
		if (valide) {
			
			System.out.println("Bienvenue chez Barette!"
					+ "\nFactures:");
			for ( Clients client : listeClients){
				System.out.println(client.getNom() + " " + client.getMontantTotal());
			}
			
		} else {
			System.out.println("Le fichier ne respecte pas le format demand�!");
		}
		
	}
	
	public boolean verifierCommande(String commande){
		boolean valide = true;
		
		String[] laCommande = new String[3];
		
		laCommande = commande.split(" ");

		
		// On verifie si il y a bien 3 entr�es ( laCommande[0] = nom, [1]=repas [2]= nb de plats)
			if (laCommande.length != 3 ){
				valide = false;
			}
			
			
			//si c'est toujours valide, on verifie si le nom de la commande fait partie de la liste des clients
			if (valide){
				
				valide = estPresent(laCommande);
				
			}
			
			//si c'est toujours valide, on verifie si le plat existe dans la liste de Plats
			
			if (valide) {
				
				valide = platPresent(laCommande);
			}
			
			
			

		
		return valide;
	}
	/*
	 * VERIFICATION DU FORMAT DU CLIENT ( PAS D'ESPACE )
	 */
	public boolean verifierClient(String client){
		boolean valide = true;
		
		char[] tabCar = client.toCharArray();
		
		for ( int i = 0; i< client.length() && valide; i++){
			
			if ( tabCar[i] == ' ') {
				valide = false;
			}
			
		}
		
		return valide;
	}
	
	
	public boolean verifierPlat(String plat){
		boolean valide = true;
		
		char[] tabCar = plat.toCharArray();
		int phase = 1;
		char[] tabNb = null;
		
		for ( int i = 0; i< plat.length() && valide; i++){
			

				//si le char est une lettre, rien ne se passe. 
					//Si le char est un espace, on passe � la phase 2.
					//Si pas lettre et pas un espace, le plat ne respecte pas le format.
				if ( !Character.isLetter(tabCar[i]) && tabCar[i] != '_') {
					
					if ( tabCar[i] == ' ') {
						
						phase = 2;
						tabNb = Arrays.copyOfRange(tabCar, i+1 , tabCar.length);
						
					} else {
						System.out.println("PAS BON");
						valide = false;
					}
					
					break;
				}	
					
		}
		
		//si la premiere partie du plat est un nom sans espace, la 2e partie doit etre un double
		if ( phase == 2 ) {
			for (int i = 0; i < tabNb.length; i++) {
				System.out.print(tabNb[i]);
			}
			System.out.println();
				
				try
				{
					String prix = new String(tabNb);
					Double.parseDouble(prix);
					}
					catch(NumberFormatException e)
					{
					  
						valide = false;
					}					
		
		}
		
		
		return valide;
	}
	
	public boolean platPresent(String[] laCommande){
		boolean valide = true;
		boolean estPresent = false;
		
		double prix;
		
		for ( Plats plat : listePlats ) {
			
			//on v�rifie si le plat est pr�sent
			if ( plat.getPlat().equals(laCommande[1]) ) {
				
				//si pr�sent, on fait prix*qte puis on ajoute se prix au Client
				estPresent = true;
				prix = plat.getPrix() * Integer.parseInt(laCommande[2]);
				leClient.setMontantTotal(leClient.getMontantTotal() + prix);
				break;
			}
			
		}
		
		
		if(!estPresent) {
			valide = false;
		}
		
		return valide;
	}
	
	public boolean estPresent(String[] laCommande){
		boolean valide = true;
		
		boolean estPresent = false;
		
		for (Clients client : listeClients){
			
			//on verifie si le client est pr�sent
			if ( client.getNom().equals(laCommande[0]) ) {
				
				estPresent = true;
				leClient = client;
				break;			
			}
		}
		
		if (!estPresent){
			
			valide = false;

		}
		
		
		
		return valide;
	}
	public void ajouterPlat(String platPrix){
		
		String[] tab;
		
		tab = platPrix.split(" ");
		
		String nom = tab[0];
		Double prix = Double.parseDouble(tab[1]);
		
		try {
			Plats plat = new Plats(nom,prix);
			listePlats.add(plat);
			
		}catch (Exception e) {
			System.out.println("Erreur dans le fichier.");
		}
		
	}
	
	public static BufferedReader ouvrirFicTexteLecture( String nomFichier ) {

		boolean valide = true;
		Path chemin = null;
		String cheminAbsolu = "";
		BufferedReader ficLecture = null;

		// Cr�ation du chemin.
		try {
			chemin = Paths.get( nomFichier );

		} 
		catch ( InvalidPathException errNomFichier ) {
			System.out.println( "\nErreur, le fichier " + nomFichier 
					+ " contient des caract�res ill�gaux." );
			valide = false;
		}

		// Si la cr�ation du chemin est valide, on peut poursuivre.

		if ( valide ) {
			cheminAbsolu = chemin.toAbsolutePath().toString();

			// V�rifier l'existence du fichier.

			if ( Files.notExists( chemin ) ) {
				// Le fichier n'existe pas.
				System.out.println( "\nErreur, le fichier " + cheminAbsolu
						+ " n'existe pas." );
				valide = false;

			} else if ( Files.exists( chemin ) ) {
				// Le fichier existe.
				// Est-ce un fichier ordinaire ?

				if ( !Files.isRegularFile( chemin ) ) {

					System.out.println( "\nErreur, le fichier " + cheminAbsolu
							+ " n'est pas un fichier ordinaire." );
					valide = false;
				} else {
					// C'est un fichier ordinaire.
					// Est-ce un fichier permis en lecture ?

					if ( !Files.isReadable( chemin ) ) {

						System.out.println( "\nErreur, le fichier "
								+ cheminAbsolu
								+ " n'est pas permis en lecture." );
						valide = false;
					} else {
						// Le fichier existe, est ordinaire 
						// et permis en lecture.

						// Ouverture du fichier texte en mode lecture.

						try {
							ficLecture = Files.newBufferedReader( chemin,
											        Charset.defaultCharset() );
						}
						catch ( IOException errIO ) {
							System.out
									.println( "\nErreur, impossible d'ouvrir "
											+ "le fichier " + cheminAbsolu
											+ " en mode lecture texte." );
							valide = false;
						}
					}
				}
			} else {
				System.out.println( "\nErreur, impossible de v�rifier "
						+ "l'existence du fichier " + cheminAbsolu + "." );
				valide = false;
			}
		}

		return ficLecture;
	}

}

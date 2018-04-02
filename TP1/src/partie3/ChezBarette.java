package partie3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class ChezBarette {

	private String nomFic = "ChezBarette.txt";
	private BufferedReader fic;
	private boolean valide = true;
	private Clients leClient;
	private int numeroTable;

	
	private int NB_MAX_TABLES = 30;
	
	private ArrayList<Clients> listeClients = new ArrayList<Clients>();
	private ArrayList<Plats> listePlats = new ArrayList<Plats>();
	
	private Tables table;

	public ChezBarette() {

		// lecture du fichier texte

		try {

			fic = ouvrirFicTexteLecture(nomFic);

			String ligne = "";

			int status = 0;

			// tant que le fichier text n'est pas terminé et que la facture est
			// valide
			while ((ligne = fic.readLine()) != null && valide) {

				if (!ligne.equals("Clients:") && !ligne.equals("Plats:")
						&& !ligne.equals("Commandes:") && !ligne.equals("Fin") && !ligne.equals("Table:")) {

					switch (status) {
					
					case 1:
						valide = verifierTable(ligne);	
						break;

					case 2:
						if (verifierClient(ligne)) {

							Clients client = new Clients(ligne);
							table.ajoutClient(client);

							
						}

						valide = verifierClient(ligne);

						break;
					case 3:
						//ajouterPlat(ligne);

						valide = verifierPlat(ligne);
						break;
					// si ce n'est pas valide on arrete la lecture du document
					case 4:
						valide = verifierCommande(ligne);
						break;

					}

				} else {

					// le status va permettre de changer d'instruction selon où
					// nous somme rendu dans le fichier
					// status 1 = numeroTable, status 2 = clients, status 3 = plats
					// commandes
					status++;
				}

			}

		}

		catch (Exception e) {

		}

		// message de sortie
		facture(valide);

	}



	public void facture(boolean valide) {

		if (valide) {

			System.out.println("Bienvenue chez Barette!");
			
			System.out.println("\nTABLE #: " + this.numeroTable + "\n");

			double prixAvantTaxes;
			double prixApresTaxes;
			
			double totalAvantTaxes = 0;
			double totalApresTaxes = 0;
			double totalTaxes = 0;
			
			DecimalFormat f = new DecimalFormat("##.00");
			
			for (Clients client : table.getListeClients()) {
				
				prixAvantTaxes = client.getMontantTotal();
				
				totalAvantTaxes += prixAvantTaxes;
				//on affiche seulement les clients avec une facture > 0$
				if ( prixAvantTaxes > 0) {

				//prixApresTaxes = calculerPrix(prixAvantTaxes);
				
				
				System.out.println(client.getNom() + ": " + f.format(client.getMontantTotal()) + "$ ");
				
				}
			}
			
			
			
			
			table.setMontantTotal(totalAvantTaxes);
			
			String fact = "\nTAXES : " + f.format(table.getMontantTaxes()) + "$\n" + 
					"Frais : " +  (table.getFrais() == 0 ? "0" : f.format(table.getFrais())) + "$ \n" + 
					"\nTOTAL: " + f.format(table.getMontantTotal()) + "$\n" + 
					"\n**UN FRAIS DE SERVICE DE 15% S'AJOUTE LORSQUE LE MONTANT DE LA FACTURE DÉPASSE 100$ OU QU'IL Y A 3 CLIENTS ET PLUS À UNE TABLE**";
			
			System.out.println(fact);

			
		} else {
			System.out.println("Le fichier ne respecte pas le format demandé!");
		}

	}
	
	/*public double calculerPrix(double prixAvantTaxes){
		
		return calculerTaxes(prixAvantTaxes) + prixAvantTaxes;
	}
	//ajoute les taxes au prix
	public double calculerTaxes(double prix) {
		// TODO Auto-generated method stub
		double tauxTPS = 0.05;
		double tauxTVQ = 0.1;
		
		return ((prix * tauxTPS) + (prix * tauxTVQ));
	}*/

	public boolean verifierTable(String ligne) {
		// on verifie si la ligne contient un int et qu'il est plus petit que le nb de table
		boolean valide = true;
		
		try {
			
			//si la ligne n'est pas un int, il va y avoir un erreur donc on va aller dans le catch
			this.numeroTable = Integer.parseInt(ligne);
			
			//si le numero de table est plus grand que le nombre de tables dans le restaurant la facture n'est pas valide
			if (this.numeroTable > NB_MAX_TABLES){
				valide = false;
			} else {
				table = new Tables(this.numeroTable);
			}
			
			
		} catch(Exception e){
			valide = false;
		}
		
		return valide;
	}
	
	public boolean verifierCommande(String commande) {
		boolean valide = true;

		String[] laCommande = new String[3];

		laCommande = commande.split(" ");

		// On verifie si il y a bien 3 entrées ( laCommande[0] = nom, [1]=repas
		// [2]= nb de plats)
		if (laCommande.length != 3) {
			valide = false;
		}

		// si c'est toujours valide, on verifie si le nom de la commande fait
		// partie de la liste des clients
		if (valide) {

			valide = estPresent(laCommande);

		}

		// si c'est toujours valide, on verifie si le plat existe dans la liste
		// de Plats

		if (valide) {

			valide = platPresent(laCommande);
		}

		return valide;
	}

	/*
	 * VERIFICATION DU FORMAT DU CLIENT ( PAS D'ESPACE )
	 */
	public boolean verifierClient(String client) {
		boolean valide = true;

		if ( !client.equals("")){
			
		
			char[] tabCar = client.toCharArray();
	
			for (int i = 0; i < client.length() && valide; i++) {
	
				if (tabCar[i] == ' ') {
					valide = false;
				}
	
			}
		} else {
			
			valide = false;
		}

		return valide;
	}

	/*
	 * VERIFICATION DU FORMAT DU PLAT ( Nom_sans_Espace + espace + unDouble) ex:
	 * Poutine 2.67
	 */
	public boolean verifierPlat(String platPrix) {
		boolean valide = true;

		String[] tab;

		tab = platPrix.split(" ");
		
		//si la ligne du plat contient plus ou moins que 1 espace, ce n'Est pas bon
		if (tab.length == 2) {
			try {
	
				String nom = tab[0];
				Double prix = Double.parseDouble(tab[1]);
				Plats plat = new Plats(nom, prix);
				listePlats.add(plat);
	
			} catch (Exception e) {
	
				System.out.println("Erreur dans le fichier.");
				valide = false;
			}
		}
		else {
			valide = false;
		}


		return valide;
	}

	public boolean platPresent(String[] laCommande) {
		boolean valide = true;
		boolean estPresent = false;

		double prix;

		for (Plats plat : listePlats) {

			// on vérifie si le plat est présent
			if (plat.getPlat().equals(laCommande[1])) {

				// si présent, on fait prix*qte puis on ajoute se prix au Client
				estPresent = true;
				prix = plat.getPrix() * Integer.parseInt(laCommande[2]);
				leClient.setMontantTotal(leClient.getMontantTotal() + prix);
				break;
			}

		}

		if (!estPresent) {
			valide = false;
		}

		return valide;
	}

	public boolean estPresent(String[] laCommande) {
		boolean valide = true;

		boolean estPresent = false;

		for (Clients client : table.getListeClients()) {

			// on verifie si le client est présent
			if (client.getNom().equals(laCommande[0])) {

				estPresent = true;
				
				
				leClient = client;
				break;
			}
		}

		if (!estPresent) {

			valide = false;

		}

		return valide;
	}
	

	/*public void ajouterPlat(String platPrix) {

		String[] tab;

		tab = platPrix.split(" ");

		String nom = tab[0];
		Double prix = Double.parseDouble(tab[1]);

		try {
			Plats plat = new Plats(nom, prix);
			listePlats.add(plat);

		} catch (Exception e) {
			System.out.println("Erreur dans le fichier.");
		}

	}*/

	public static BufferedReader ouvrirFicTexteLecture(String nomFichier) {

		boolean valide = true;
		Path chemin = null;
		String cheminAbsolu = "";
		BufferedReader ficLecture = null;

		// Création du chemin.
		try {
			chemin = Paths.get(nomFichier);

		} catch (InvalidPathException errNomFichier) {
			System.out.println("\nErreur, le fichier " + nomFichier
					+ " contient des caractères illégaux.");
			valide = false;
		}

		// Si la création du chemin est valide, on peut poursuivre.

		if (valide) {
			cheminAbsolu = chemin.toAbsolutePath().toString();

			// Vérifier l'existence du fichier.

			if (Files.notExists(chemin)) {
				// Le fichier n'existe pas.
				System.out.println("\nErreur, le fichier " + cheminAbsolu
						+ " n'existe pas.");
				valide = false;

			} else if (Files.exists(chemin)) {
				// Le fichier existe.
				// Est-ce un fichier ordinaire ?

				if (!Files.isRegularFile(chemin)) {

					System.out.println("\nErreur, le fichier " + cheminAbsolu
							+ " n'est pas un fichier ordinaire.");
					valide = false;
				} else {
					// C'est un fichier ordinaire.
					// Est-ce un fichier permis en lecture ?

					if (!Files.isReadable(chemin)) {

						System.out.println("\nErreur, le fichier "
								+ cheminAbsolu
								+ " n'est pas permis en lecture.");
						valide = false;
					} else {
						// Le fichier existe, est ordinaire
						// et permis en lecture.

						// Ouverture du fichier texte en mode lecture.

						try {
							ficLecture = Files.newBufferedReader(chemin,
									Charset.defaultCharset());
						} catch (IOException errIO) {
							System.out.println("\nErreur, impossible d'ouvrir "
									+ "le fichier " + cheminAbsolu
									+ " en mode lecture texte.");
							valide = false;
						}
					}
				}
			} else {
				System.out.println("\nErreur, impossible de vérifier "
						+ "l'existence du fichier " + cheminAbsolu + ".");
				valide = false;
			}
		}

		return ficLecture;
	}

}

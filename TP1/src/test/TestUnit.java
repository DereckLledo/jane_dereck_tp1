package test;
import java.util.ArrayList;

import partie3.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;


public class TestUnit {

	ChezBarette chezB;

	Plats plat;	
	Clients cli;
	
	
	@Before
	public void AvantChaqueTest() {
		chezB = new ChezBarette();
		cli = Mockito.mock(Clients.class);
		plat = Mockito.mock(Plats.class);

	}


	
	@Test
	public void testCalculerTaxes() {
		double a = 10;
		double resultat = (a * 0.05) + (a * 0.1);
		
		assertEquals(resultat, chezB.calculerTaxes(a), 0);
	}
	
	@Test
	public void testVerifierCommandeFalse(){
		//un string vide n'est pas un bon format

		String testCommande = "";
		assertFalse(chezB.verifierCommande(testCommande));
	}
	

	@Test
	public void testVerifierClientTrue(){
		// un string sans espace 
		String nomTest = "Jean";
		assertTrue(chezB.verifierClient(nomTest));
		
	}
	
	@Test
	public void testVerifierClientFalse(){
		// un string avec un espace
		String nomTest = "Jean test";
		assertFalse(chezB.verifierClient(nomTest));
		
	}
	
	@Test
	public void testVerifierClientVide(){
		// un string vide
		String nomTest = "";
		assertFalse(chezB.verifierClient(nomTest));
		
	}
	
	
	
	/*
	@Test
	public void testVerifierCommandeTrue(){
		ChezBarette bar = Mockito.mock(ChezBarette.class);
		bar.getListeClients().add(new Clients("testNom"));
		bar.getListePlats().add(new Plats("testCommande", 2));
		String commande = "testNom testCommande 2";
		String[] laCommande = {"testNom", "testCommande", "2"};

		Mockito.when(bar.estPresent(any())).thenReturn(true);
		Mockito.when(bar.platPresent(any())).thenReturn(true);
		
		assertTrue(bar.verifierCommande(commande));
	}

	
/*	@Test
	public void testVerifierCommandeTrue(){
		
		Clients cli = mock(Clients.class);
		String test = "testNom testPlat 10.65"; 
		
		when()
		
		assertTrue(chezB.verifierCommande(laCommande));
		
		cli = new Clients("testNom");
		plat = new Plats("testPlat", 10.65);
		String test = "testNom testPlat 10.65"; 
		Clients cli = new Clients("nomTest");
		Plats plat = new Plats("platTest", 10);
		
		ArrayList<Clients> listeClients = new ArrayList<Clients>();
		ArrayList<Plats> listePlats = new ArrayList<Plats>();
		
		listeClients.add(cli);
		listePlats.add(plat);
		
		chezB.setListeClients(listeClients);
		
		String laCommande = "nomTest platTest 10";
		
		
		//assertTrue(chezB.verifierCommande(test));
	}*/
	

}

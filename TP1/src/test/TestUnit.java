package test;
import partie3.*;
import static org.junit.Assert.*;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestUnit {

	ChezBarette chezB;
	Clients cli;
	Plats plat;
	
	@Before
	public void AvantChaqueTest() {
		chezB = new ChezBarette();

	}

	@After
	public void ApresChaqueTest() {


	}
	
	@Test
	public void testCalculerTaxes() {
		double a = 10;
		double resultat = (a * 0.05) + (a * 0.1);
		
		assertEquals(resultat, chezB.calculerTaxes(a), 0.01);
	}
	
	@Test
	public void testVerifierCommandeFalse(){
		//un string vide n'est pas un bon f

		String a = "";
		assertFalse(chezB.verifierCommande(a));
	}
	
	@Test
	public void testVerifierCommandeTrue(){
		//le format d'une commande est "client plat nbPlats"
		
	/*	cli = new Clients("testNom");
		plat = new Plats("testPlat", 10.65);
		String test = "testNom testPlat 10.65"; */
		
		
		//assertTrue(chezB.verifierCommande(test));
	}
	

}

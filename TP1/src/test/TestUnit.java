package test;
import partie3.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;



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
	
	@Test
	public void testVerifierPlatTrue(){
		String platTest = "Soupe 6.3";
		assertTrue(chezB.verifierPlat(platTest));
	}
	
	@Test
	public void testVerifierPlatFalse(){
		String platTest = "Sou pe 6.3";
		assertFalse(chezB.verifierPlat(platTest));
	}
	
	@Test
	public void testVerifierPlatVide(){
		String platTest = "";
		assertFalse(chezB.verifierPlat(platTest));
	}
	
	@Test
	public void testCalculerPrix() {
		double prixAvantTaxes = 10;
		//le résultat est prixAvantTaxes + (5% du prixAvantTaxes) + (10% du prixAvantTaxes);
		double resultat = prixAvantTaxes + (prixAvantTaxes * 0.05) + (prixAvantTaxes * 0.1);
		
		assertEquals(resultat, chezB.calculerPrix(prixAvantTaxes), 0);
	}

}

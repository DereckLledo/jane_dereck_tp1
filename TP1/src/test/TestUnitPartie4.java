package test;
import partie3.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;



public class TestUnitPartie4 {

	ChezBarette chezB;

	Plats plat;	
	Clients cli;
	
	
	@Before
	public void AvantChaqueTest() {
		chezB = new ChezBarette();

	}
	
	@Test
	public void testVerifierTableTrue() {
		//true la table 15 est fonctionnelle
		assertTrue(chezB.verifierTable("15"));
	}
	
	@Test
	public void testVerifierTableFalseTropHaut() {
		//false la table 99 n'est pas bonne puisque le nombre maximal de table est de 30
		assertFalse(chezB.verifierTable("99"));
	}
	
	@Test
	public void testVerifierTableFalseDouble() {
		//false la table ne peut pas etre un double
		assertFalse(chezB.verifierTable("65.434"));
	}
	
	@Test
	public void testVerifierTableFalsePasUnInt() {
		//false la table ne peut pas etre un string
		assertFalse(chezB.verifierTable("Bonjour ceci n'Est pas un int"));
	}

}

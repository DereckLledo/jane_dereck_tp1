package test;
import partie3.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



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
	
	@Test
	public void testVerifierMontantTotal100(){
		 
		Tables tableTest = new Tables(1);
		tableTest.setMontantTotal(100); //un montant de 100$ va avoir des frais de 15% + des taxes de 15% donc le résultat sera 130
		
		assertEquals( 130,tableTest.getMontantTotal(), 0.1 );
	}
	
	@Test
	public void testVerifierMontantTotal50(){
		 
		Tables tableTest = new Tables(1);
		tableTest.setMontantTotal(50); //un montant de 50$ va avoir des frais de 0$ + des taxes de 15% donc le résultat sera 57.5
		
		assertEquals( 57.5,tableTest.getMontantTotal(), 0.2 );
	}
	
	@Test
	public void testVerifierMontantTotal50Et3Clients(){
		 
		Tables tableTest = new Tables(1);
		tableTest.ajoutClient(new Clients("Jean"));
		tableTest.ajoutClient(new Clients("Roger"));
		tableTest.ajoutClient(new Clients("Jeanne"));
		
		tableTest.setMontantTotal(50); //un montant de 50$ avec 3 clients va avoir des frais de 15% + des taxes de 15% donc le résultat sera 65
		
		assertEquals( 65,tableTest.getMontantTotal(), 0.2 );
	}

}

package it.prova.gestioneproprietari.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.management.RuntimeErrorException;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
import it.prova.gestioneproprietari.model.Automobile;
import it.prova.gestioneproprietari.model.Proprietario;
import it.prova.gestioneproprietari.service.MyServiceFactory;
import it.prova.gestioneproprietari.service.automobile.AutomobileService;
import it.prova.gestioneproprietari.service.proprietario.ProprietarioService;

public class TestGestioneProprietari {

	public static void main(String[] args) {

		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();

		try {

			// Test Metodi di Proprietario

			testCRUDProprietario(proprietarioService);
			System.out.println("In tabella proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi. \n");

			testContaQuantiProprietariHannoAutoImmatricolateDopoProprietario(proprietarioService, automobileService);
			System.out.println("In tabella proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi. \n");

			// Test Metodi di Automobile

			testCRUDAutomobile(automobileService, proprietarioService);
			System.out.println(
					"In tabella automobile ci sono " + automobileService.listAllAutomobili().size() + " elementi. \n");

			testCercaTutteConProprietarioCodiceFiscaleIniziaCon(automobileService, proprietarioService);
			System.out.println(
					"In tabella automobile ci sono " + automobileService.listAllAutomobili().size() + " elementi. \n");

			testCercaTutteConErrore(automobileService, proprietarioService);
			System.out.println(
					"In tabella automobile ci sono " + automobileService.listAllAutomobili().size() + " elementi. \n");

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	private static void testCercaTutteConErrore(AutomobileService automobileService,
			ProprietarioService proprietarioService) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testCercaTutteConErrore inizio.............");


		// Proprietario
		Proprietario testProprietario = new Proprietario("Rossenberg", "Ramboanga", "RMBRSN97P07Z216A",
				new SimpleDateFormat("dd-MM-yyyy").parse("07-09-2020"));
		proprietarioService.inserisciNuovo(testProprietario);


		// Automobile
		Automobile testAutomobile = new Automobile("Fiat", "500X", "DP235ZF", 2008);
		testAutomobile.setProprietario(testProprietario);
		automobileService.inserisciNuovo(testAutomobile);


		// PROPER TEST
		List<Automobile> result = automobileService.cercaTutteConErrore();
		System.out.println(result);
		if (result.size() != 1)
			throw new RuntimeException("testCercaTutteConErrore fallito");

		// Delete
		automobileService.rimuovi(testAutomobile);
		proprietarioService.rimuovi(testProprietario);

		System.out.println(".......testCercaTutteConErrore fine: PASSED.............");

	}

	private static void testCercaTutteConProprietarioCodiceFiscaleIniziaCon(AutomobileService automobileService,
			ProprietarioService proprietarioService) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testCercaTutteConProprietarioCodiceFiscaleIniziaCon inizio.............");

		// Proprietario
		Proprietario testProprietario = new Proprietario("Rossenberg", "Ramboanga", "RMBRSN97P07Z216A",
				new SimpleDateFormat("dd-MM-yyyy").parse("07-09-1996"));
		proprietarioService.inserisciNuovo(testProprietario);

		// Automobile
		Automobile testAutomobile = new Automobile("Fiat", "500X", "DP235ZF", 2008);
		testAutomobile.setProprietario(testProprietario);
		automobileService.inserisciNuovo(testAutomobile);

		// PROPER TEST
		List<Automobile> result = automobileService.cercaTutteConProprietarioCodiceFiscaleIniziaCon("R");
		if (result.size() != 1)
			throw new RuntimeException("testCercaTutteConProprietarioCodiceFiscaleIniziaCon fallito");

		// Delete
		automobileService.rimuovi(testAutomobile);
		proprietarioService.rimuovi(testProprietario);

		System.out.println(".......testCercaTutteConProprietarioCodiceFiscaleIniziaCon fine: PASSED.............");

	}

	private static void testCRUDAutomobile(AutomobileService automobileService, ProprietarioService proprietarioService)
			throws Exception {

		// TODO Auto-generated method stub
		System.out.println(".......testCRUDAutomobile inizio.............");

		// Proprietario
		Proprietario testProprietario = new Proprietario("Rossenberg", "Ramboanga", "RMBRSN97P07Z216A",
				new SimpleDateFormat("dd-MM-yyyy").parse("07-09-1996"));
		proprietarioService.inserisciNuovo(testProprietario);

		// Automobile
		Automobile testAutomobile = new Automobile("Fiat", "500X", "DP235ZF", 2008);
		testAutomobile.setProprietario(testProprietario);

		automobileService.inserisciNuovo(testAutomobile);
		List<Automobile> listaAutomobili = automobileService.listAllAutomobili();
		if (listaAutomobili.size() != 1)
			throw new RuntimeException("testInserisciNuovaAutomobile fallito");
		Long idAutomobile = listaAutomobili.get(0).getId();

		// Update
		testAutomobile.setMarca("Lamborghini");
		testAutomobile.setModello("Urus");
		automobileService.aggiorna(testAutomobile);

		if (!testAutomobile.getMarca().equals("Lamborghini"))
			throw new RuntimeException("testAggiornaAutomobile fallito");

		// Delete
		automobileService.rimuovi(testAutomobile);
		proprietarioService.rimuovi(testProprietario);

		System.out.println(".......testCRUDAutomobile fine: PASSED.............");

	}

	private static void testContaQuantiProprietariHannoAutoImmatricolateDopoProprietario(
			ProprietarioService proprietarioService, AutomobileService automobileService) throws Exception {
		// TODO Auto-generated method stub
		System.out
				.println(".......testContaQuantiProprietariHannoAutoImmatricolateDopoProprietario inizio.............");

		// Proprietario
		Proprietario testProprietario = new Proprietario("Rossenberg", "Ramboanga", "RMBRSN97P07Z216A",
				new SimpleDateFormat("dd-MM-yyyy").parse("07-09-1996"));
		proprietarioService.inserisciNuovo(testProprietario);
		List<Proprietario> listaProprietari = proprietarioService.listAllProprietari();
		if (listaProprietari.size() != 1)
			throw new RuntimeException("testInserisciNuovoProprietario fallito");

		// Automobile
		Automobile testAutomobile = new Automobile("Fiat", "500X", "DP235ZF", 2008);
		testAutomobile.setProprietario(testProprietario);
		automobileService.inserisciNuovo(testAutomobile);

		// PROPER TEST
		int result = proprietarioService.contaQuantiProprietariHannoAutoImmatricolateDopo(2000);
		if (result != 1)
			throw new RuntimeException("testContaQuantiProprietariHannoAutoImmatricolateDopoProprietario fallito");

		// Delete
		automobileService.rimuovi(testAutomobile);
		proprietarioService.rimuovi(testProprietario);

		System.out.println(
				".......testContaQuantiProprietariHannoAutoImmatricolateDopoProprietario fine: PASSED.............");

	}

	private static void testCRUDProprietario(ProprietarioService proprietarioService) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testCRUDProprietario inizio.............");

		// Create
		Proprietario testProprietario = new Proprietario("Rossenberg", "Ramboanga", "RMBRSN97P07Z216A",
				new SimpleDateFormat("dd-MM-yyyy").parse("07-09-1996"));
		proprietarioService.inserisciNuovo(testProprietario);

		// Read
		List<Proprietario> listaProprietari = proprietarioService.listAllProprietari();
		if (listaProprietari.size() != 1)
			throw new RuntimeException("testInserisciNuovoProprietario fallito");
		Long idTestProprietario = testProprietario.getId();

		// Update
		String nomeAggiornato = "RossenbergVillanueva";
		testProprietario.setNome(nomeAggiornato);
		proprietarioService.aggiorna(testProprietario);

		if (!testProprietario.getNome().equals("RossenbergVillanueva"))
			throw new RuntimeException("testAggiornamnetoProprietario fallito");

		// Delete
		proprietarioService.rimuovi(testProprietario);
		if (proprietarioService.caricaSingoloProprietario(idTestProprietario) != null)
			throw new RuntimeException("testRimozioneProprietario fallito");

		System.out.println(".......testCRUDProprietario fine: PASSED.............");

	}

}

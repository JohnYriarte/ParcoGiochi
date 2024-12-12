package Main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// ------------ VARIABILI ------------
		
		// 1) giroOperativo per Giorno
		// 2) Aver usato Array
		
		// PARCO
		int parcoGiorniLavorativi = 3; // Cambiare giorni lavorativi
		int parcoAttrazioni = 3;
		int parcoVisitatoriTot = 0;
		int parcoPersoneSoddisfatte = 0;
		double parcoGuadagnoNetto = 0;
		String parcoAttrPiuVisitata = "";
		int parcoVisitatoriGiornaliero;

		// ATTRAZIONE
		String[] attrNome = { "Montagne Russe 🎢", "Ruota Panoramica 🎡", "Torre 🏰" };
		int[] attrCapacitaMax = { 20, 10, 15 };
		int[] attrGiroMax = { 10, 5, 15 };
		double[] attrCostoOperativo = { 50, 70, 40 };
		double[] attrBiglietto = { 9.99, 12.99, 7.99 };
		int[] attrTempoPersona = { 2, 3, 2 };

		// Valori random
		int[] attrGiroAttuale = new int[parcoAttrazioni];
		int[] attrCapacitaAttuale = new int[parcoAttrazioni];
		int[] attrCoda = new int[parcoAttrazioni];

		// Valori da calcolare durante l'esecuzione
		int attrTempoAttesaAttuale = 0;
		int[] attrCodaTot = new int[parcoAttrazioni];
		int[] attrTempoAttesaTot = new int[parcoAttrazioni];
		int[] attrVisiteTot = new int[parcoAttrazioni];
		double[] attrGuadagnoTot = new double[3];

		// VISITATORE
		int visNumeroAttr = 0;
		int visCodiceAttr;

		// Valore random
		double visBudget;
		int visTempoAttesa;

		// Apertura Scanner
		Scanner scanner = new Scanner(System.in);

		// ------------ MAIN ------------

		System.out.println(
				"                        \\______   \\ ____   _______  __ ____   ____  __ ___/  |_|__| _____        |    |____ ___  _______  |    |   _____    ____    __| _/\r\n"
						+ "			 |    |  _// __ \\ /    \\  \\/ // __ \\ /    \\|  |  \\   __\\  | \\__  \\       |    \\__  \\\\  \\/ /\\__  \\ |    |   \\__  \\  /    \\  / __ | \r\n"
						+ "			 |    |   \\  ___/|   |  \\   /\\  ___/|   |  \\  |  /|  | |  |  / __ \\_ /\\__|    |/ __ \\\\   /  / __ \\|    |___ / __ \\|   |  \\/ /_/ | \r\n"
						+ "			 |______  /\\___  >___|  /\\_/  \\___  >___|  /____/ |__| |__| (____  / \\________(____  /\\_/  (____  /_______ (____  /___|  /\\____ | \r\n"
						+ "			        \\/     \\/     \\/          \\/     \\/                      \\/                \\/           \\/        \\/    \\/     \\/      \\/");

		// LOOP PER GIORNI LAVORATIVI : contera' soltanto i giorni lavorativi escludendo i giorni feriali
		for (int giorno = 1; giorno <= parcoGiorniLavorativi; giorno++) {

			// Generazione valore random per il numero di visitatori giornalieri
			parcoVisitatoriGiornaliero = (int) Math.round(Math.random() * 15 + 5);

			// LOOP PER VISITATORI GIORNALIERI 
			for (int visitatore = 1; visitatore <= parcoVisitatoriGiornaliero; visitatore++) {

				// Assegnazione valori random + reset valori
				visBudget = Math.random() * 50; // Budget del Visitatore
				double visBudgetVecchio = visBudget; // Variabile per controllare se il budget e' stato aggiornato
				visTempoAttesa = (int) Math.round(Math.random() * 30); // Tempo disponibilita' del Visitatore
				visNumeroAttr = 0; // Valore default : numero attrazioni visitate
				visCodiceAttr = -1; // Valore default : attrazione scelta in codice

				
				// Interfaccia Principale Visitatore : Loop principale dove l'utente interagisce con il console
				while (true) {

					// Uscita dall'interfaccia principale visitatore
					if (visCodiceAttr == 9) {
						System.out.println("👋 Grazie per averci visitato, Visitatore " + visitatore + "!");
						break;
					}

					
					//  Rappresentano lo stato (randomico) dell'attrazione in fase di scelta
					for (int i = 0; i < attrCoda.length; i++) {

						
						/*
						 * Controllo dello stato delle attrazioni e aggiornamento rispetto all'attrazione scelta precedentemente e dal budget
						 * Controlla se sia lo stesso codice attrazione e se l'utente abbia compiuto un'attrazione prima
						 */
						if (!(visCodiceAttr == i && visBudgetVecchio == visBudget)) { 
							attrCapacitaAttuale[i] = (int) Math.round(Math.random() * attrCapacitaMax[i] * 1.5);
							attrGiroAttuale[i] = (int) Math.round(Math.random() * attrGiroMax[i]);
							attrCoda[i] = (int) Math.round(Math.random() * attrCapacitaMax[i] * 1.5 + 1);
						}

					}

					visBudgetVecchio = visBudget;

					
					// Stampo automatico del Menu Attrazioni 
					System.out.println(String.format("\n(Giorno %d) Visitatore %d: 💸%.2f", giorno, visitatore, visBudget));
					System.out.println("✨🌟 LE NOSTRE ATTRAZIONI 🌟✨");
					System.out.println("*****************************");
					// Stampo automatico dell'elenco delle attrazioni
					for (int i = 0; i < attrNome.length; i++) {

						System.out.println((i + 1) + "." + attrNome[i] + " ( 💸" + attrBiglietto[i] + " )");

					}
					System.out.println("9. Esci"); // Aggiungere uscita alla fine

					
					
					// Input Codice Attrazione
					visCodiceAttr = scanner.nextInt();
					
					

					// Blocco principale per il controllo del codice attrazione scelta
					if (visCodiceAttr >= 1 && visCodiceAttr <= parcoAttrazioni) {
						visCodiceAttr--;

						// CONTROLLO BUDGET : confronto tra budget e costo biglietto
						if (visBudget >= attrBiglietto[visCodiceAttr]) {
							System.out.println("✅ Budget sufficiente per accedere all'attrazione ✅");

							// CONTROLLO GIRO : confronto tra i giri attuali effettuati e la disponibilita' massima dei giri attrazione
							if (attrGiroAttuale[visCodiceAttr] < attrGiroMax[visCodiceAttr]) {
								String scelta = "";
								System.out.println("✅ Giro disponibile ✅");

								// CONTROLLO CODA : controlla se la capacita' attuale non superi il limite massimo di visitatori
								if (attrCapacitaAttuale[visCodiceAttr] >= attrCapacitaMax[visCodiceAttr]) {
									System.out.println("⚠️ Capacità massima raggiunta per l'attrazione ⚠️");
									attrTempoAttesaAttuale = attrCoda[visCodiceAttr] * attrTempoPersona[visCodiceAttr]; // Calcolo il tempo di attesa
									
									if (attrTempoAttesaAttuale <= visTempoAttesa) { // Controllo se il tempo di attesa dell'attrazione rientra nel tempo di attesa del visitatore
										do {
											System.out.print("⏳ La coda è breve! (" + attrTempoAttesaAttuale
													+ "m) Vuoi metterti in coda? (SI/NO): ");
											scelta = scanner.next().toUpperCase();
										} while (!(scelta.equals("SI") || scelta.equals("NO"))); // Finche' l'input non e' valido, il visitatore deve scegliere solo se il tempo di attesa del visitatore rientra nel tempo di attesa dell'attrazione

									} else {
										scelta = "NO"; // Assegnazione di valore default quando non soddisfa il punto precedente
									}
								} else {
									System.out.println("✅ Posti disponibili per l'attrazione ✅");
								}

								
								// AGGIORNAMENTO DATABASE
								switch (scelta) {
								case "NO":
									System.out.println("⏳ Tempo di attesa troppo lungo, scegli un'altra attrazione.");
									break;
								case "SI":
									attrTempoAttesaTot[visCodiceAttr] += attrTempoAttesaAttuale;
									attrCodaTot[visCodiceAttr]++;
									System.out.println("✅ Puoi attendere ✅");
								default:
									// CONTROLLO PERSONE SODDISFATTE : Quando il numero di attrazione visitate e' 0
									if (visNumeroAttr == 0) {
										parcoPersoneSoddisfatte++; // Incremento delle persone soddisfatte
									}

									attrVisiteTot[visCodiceAttr]++; // Incremento il numero delle persone per quell'attrazione
									visNumeroAttr++; // Incremento delle attrazioni visitate
									visBudget -= attrBiglietto[visCodiceAttr]; // Operazione acquisto biglietto
									attrGuadagnoTot[visCodiceAttr] += attrBiglietto[visCodiceAttr]; // Incremento il guadagno totale dell'attrazione
									System.out.println("✅ Buon divertimento! ✅");
								}

							} else {
								System.out.println("⚠️ Numero massimo di giri raggiunto per questa attrazione ⚠️");
							}
						} else {
							System.out.println("❌ Budget insufficiente per accedere all'attrazione ❌");
						}
					} else if (visCodiceAttr != 9) {
						System.out.println(
								"⚠️ Codice non valido! Nel frattempo, sei andato a fare un giro nel parco! 🌳 🚶‍♂️");
					}

				} // FINE WHILE INTERFACE

			} // FINE LOOP VISITATORI GIORNALIERI

			
			parcoVisitatoriTot += parcoVisitatoriGiornaliero; // Sommo il numero dei visitatori giornalieri al numero dei visitatori totali del parco

		} // FINE LOOP GIORNI LAVORATIVI

		// CALCOLO RISULTATI
		for (int i = 0; i < 3; i++) {
			parcoGuadagnoNetto += attrGuadagnoTot[i] - attrCostoOperativo[i] * parcoGiorniLavorativi;
		}
		System.out.println(String.format("Il guadagno netto e': %.2f 💸", parcoGuadagnoNetto));
		int visitatoriMax = 0;
		int temp = 0;
		// Calcolo quale attrazione e' stata visitata piu volte
		for (int i = 0; i < parcoAttrazioni; i++) {
			temp = 0;
			if (attrVisiteTot[i] > visitatoriMax) {
				visitatoriMax = attrVisiteTot[i];
				parcoAttrPiuVisitata = attrNome[i];

			}
			
			// Controllo per evitare di dividere un numero per 0
			if (attrCodaTot[i] != 0) {
				temp = attrTempoAttesaTot[i] / attrCodaTot[i];
			}
			
			System.out.println("Attrazione: " + attrNome[i] + "\n\t Tempo medio di attesa: "
					+ temp + "m");

		}
		
		System.out.println("L'attrazione più visitata è: " + parcoAttrPiuVisitata + " 🏆");
		System.out.println(String.format("La percentuale di visitatori soddisfatti è %.2f%%", ((double) parcoPersoneSoddisfatte / parcoVisitatoriTot) * 100));
		
		
		// Chiusura Scanner
		scanner.close();
	}
}

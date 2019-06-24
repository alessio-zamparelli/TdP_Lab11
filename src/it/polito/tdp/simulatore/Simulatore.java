package it.polito.tdp.simulatore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.simulatore.Evento.TipoEvento;

public class Simulatore {

	/*
	 * Tavoli: 2 tavoli da 10 posti 4 tavoli da 8 posti 4 tavoli da 6 posti - 5
	 * tavoli da 4 posti
	 */
	// statistiche in out

	// var della simulazione
	private PriorityQueue<Evento> queue;
	private Random rSeed;
	private List<Tavolo> tavoli;
	private Stats stats;

	public void init() {
		this.queue = new PriorityQueue<>();
		this.stats = new Stats();
		this.tavoli = new ArrayList<>();
		rSeed = new Random();
		LocalDateTime tempoAttuale = LocalDateTime.of(LocalDate.of(2000, 01, 01), LocalTime.of(8, 00));
		for (int i = 0; i < 2000; i++) {
			tempoAttuale = tempoAttuale.plusMinutes(rSeed.nextInt(10) + 1);
			int numPersone = rSeed.nextInt(10) + 1;
			int permanenza = rSeed.nextInt(60) + 60;
			float tolleranza = (float) (rSeed.nextFloat() * 0.9);
			Evento e = new Evento(tempoAttuale, TipoEvento.ARRIVO_GRUPPO_CLIENTI, numPersone, permanenza, tolleranza,
					i);
			queue.add(e);
		}
		// 2x10
		tavoli.add(new Tavolo(10, 1));
		tavoli.add(new Tavolo(10, 2));
		// 4x8
		tavoli.add(new Tavolo(8, 3));
		tavoli.add(new Tavolo(8, 4));
		tavoli.add(new Tavolo(8, 5));
		tavoli.add(new Tavolo(8, 6));
		// 4x6
		tavoli.add(new Tavolo(6, 7));
		tavoli.add(new Tavolo(6, 8));
		tavoli.add(new Tavolo(6, 9));
		tavoli.add(new Tavolo(6, 10));
		// 5x4
		tavoli.add(new Tavolo(4, 11));
		tavoli.add(new Tavolo(4, 12));
		tavoli.add(new Tavolo(4, 13));
		tavoli.add(new Tavolo(4, 14));
		tavoli.add(new Tavolo(4, 15));

	}

	public void run() {
		Evento e;
		while ((e = this.queue.poll()) != null) {
			switch (e.getTipo()) {
				case ARRIVO_GRUPPO_CLIENTI:
					System.out.println("ARRIVO CLIENTI " + e.getId());
					System.out.println("\t" + e);
					System.out.println("\tTavoli disponibili: " + tavoli);
					Tavolo t = cercaTavolo(e.getNumPersone());
					if (t == null) {
						// Tavolo non trovato, provo al bancone
						System.out.println("\tTavolo non trovato");
						float tolleranza = e.getTolleranza();
						float probBancone = rSeed.nextFloat();
						if (tolleranza < probBancone) {
							// il cliente va al bancone
							System.out.println("\tVa al bancone");
							Evento newEvento = new Evento(e.getTempo().plusMinutes(e.getPermanenza()),
									TipoEvento.USCITA_GRUPPO_CLIENTI, e.getNumPersone(), e.getPermanenza(),
									e.getTolleranza(), e.getId());
							queue.add(newEvento);
						} else {
							// cliente insoddisfatto
							System.out.println("\tVa via");
							this.stats.insoddisfatti(e.getNumPersone());
						}
					} else {
						// Tavolo trovato
						System.out.println("\tTavolo trovato " + t.getId());
						t.setLibero(false);
						Evento newEvento = new Evento(e.getTempo().plusMinutes(e.getPermanenza()),
								TipoEvento.USCITA_GRUPPO_CLIENTI, e.getNumPersone(), e.getPermanenza(),
								e.getTolleranza(), e.getId());
						newEvento.setTavolo(t);
						queue.add(newEvento);
					}
					break;

				case USCITA_GRUPPO_CLIENTI:
					System.out.println("USCITA CLIENTI " + e.getId());
					this.stats.soddisfatti(e.getNumPersone());
					if (e.getTavolo() != null)
						e.getTavolo().setLibero(true);
					break;

			}
		}

	}

	private Tavolo cercaTavolo(int numPersone) {
		System.out.println("\tCerco il tavolo per " + numPersone + " persone");
		Tavolo bestTavolo = null;
		for (Tavolo t : this.tavoli) {
			if (t.isLibero() && numPersone <= t.getNumPosti() && numPersone >= t.getNumPosti() / 2) {
				bestTavolo = t;
			}
		}
		return bestTavolo;
	}

	public Stats getStats() {
		return this.stats;
	}

}

package it.polito.tdp.simulatore;

import java.time.LocalDateTime;

public class Evento implements Comparable<Evento> {
	public enum TipoEvento {
		ARRIVO_GRUPPO_CLIENTI, USCITA_GRUPPO_CLIENTI
	}

	private LocalDateTime tempo;
	private TipoEvento tipo;
	private int numPersone;
	private int permanenza;
	private float tolleranza;
	private Tavolo tavolo;
	private int id;

	public Evento(LocalDateTime tempo, TipoEvento tipo, int numPersone, int permanenza, float tolleranza, int id) {
		this.tempo = tempo;
		this.tipo = tipo;
		this.numPersone = numPersone;
		this.permanenza = permanenza;
		this.tolleranza = tolleranza;
		this.id = id;
	}

	public LocalDateTime getTempo() {
		return tempo;
	}
	
	public TipoEvento getTipo() {
		return tipo;
	}

	public int getNumPersone() {
		return numPersone;
	}

	public int getPermanenza() {
		return permanenza;
	}

	public float getTolleranza() {
		return tolleranza;
	}
	
	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}
	
	public Tavolo getTavolo() {
		return this.tavolo;
	}

	public int getId() {
		return this.id;
	}
	@Override
	public int compareTo(Evento o) {
		return this.tempo.compareTo(o.tempo);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Evento [id=");
		builder.append(id);
		builder.append(", numPersone=");
		builder.append(numPersone);
		builder.append(", permanenza=");
		builder.append(permanenza);
		builder.append(", tolleranza=");
		builder.append(tolleranza);
		builder.append("]");
		return builder.toString();
	}

}

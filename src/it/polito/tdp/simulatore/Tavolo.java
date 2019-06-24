package it.polito.tdp.simulatore;

public class Tavolo {

	private int numPosti;
	private boolean libero;
	private int id;
	
	public Tavolo(int numPosti, int id) {
		this.numPosti = numPosti;
		this.id = id;
		this.libero = true;
	}
	public boolean isLibero() {
		return this.libero;
	}
	public boolean getStato() {
		return this.libero;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setLibero(boolean libero) {
		this.libero = libero;
	}
	public int getNumPosti() {
		return this.numPosti;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("T[Posti=");
		builder.append(numPosti);
		builder.append(", Lib=");
		builder.append(libero);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}

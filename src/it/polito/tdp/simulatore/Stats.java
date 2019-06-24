package it.polito.tdp.simulatore;

public class Stats {
	private int numero_totale_clienti;
	private int numero_clienti_soddisfatti;
	private int numero_clienti_insoddisfatti;

	public Stats() {
		numero_totale_clienti = 0;
		numero_clienti_soddisfatti = 0;
		numero_clienti_insoddisfatti = 0;
	}

	public void insoddisfatti(int numPersone) {
		numero_clienti_insoddisfatti += numPersone;
		numero_totale_clienti += numPersone;
	}

	public void soddisfatti(int numPersone) {
		numero_clienti_soddisfatti += numPersone;
		numero_totale_clienti += numPersone;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stats [numero_totale_clienti=");
		builder.append(numero_totale_clienti);
		builder.append(", numero_clienti_soddisfatti=");
		builder.append(numero_clienti_soddisfatti);
		builder.append(", numero_clienti_insoddisfatti=");
		builder.append(numero_clienti_insoddisfatti);
		builder.append("]");
		return builder.toString();
	}
}

package it.polito.tdp.simulatore;

public class Model {

	private Simulatore sim;
	private Stats stats;
	public Model() {
		sim = new Simulatore();
	}

	public void simula() {
		this.sim.init();
		this.sim.run();
		this.stats = this.sim.getStats();
	}
	
	public Stats getStats() {
		return this.stats;
	}

}

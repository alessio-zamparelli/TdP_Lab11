package it.polito.tdp.simulatore;

public class TestModel {
	
	public static void main(String[] args) {
		Model model = new Model();
		model.simula();
		System.out.println("\n\nRisultati simulazione\n");
		System.out.println(model.getStats().toString());
	}

}

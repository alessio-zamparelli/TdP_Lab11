package it.polito.tdp.bar;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.simulatore.Model;
import it.polito.tdp.simulatore.Stats;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class BarController {
	private Model model;
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtResults"
	private TextArea txtResults; // Value injected by FXMLLoader

	@FXML
	void doRunSimulation(ActionEvent event) {
		this.model.simula();
		Stats stats = this.model.getStats();
		txtResults.setText(stats.toString());
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtResults != null : "fx:id=\"txtResults\" was not injected: check your FXML file 'Bar.fxml'.";
	}

	public void setModel(Model model) {
		this.model = model;
	}
}

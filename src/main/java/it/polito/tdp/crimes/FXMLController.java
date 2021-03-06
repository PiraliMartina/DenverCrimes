/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.time.Month;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.crimes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {

	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="boxCategoria"
	private ComboBox<String> boxCategoria; // Value injected by FXMLLoader

	@FXML // fx:id="boxMese"
	private ComboBox<Month> boxMese; // Value injected by FXMLLoader

	@FXML // fx:id="btnAnalisi"
	private Button btnAnalisi; // Value injected by FXMLLoader

	@FXML // fx:id="boxArco"
	private ComboBox<DefaultWeightedEdge> boxArco; // Value injected by FXMLLoader

	@FXML // fx:id="btnPercorso"
	private Button btnPercorso; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doCalcolaPercorso(ActionEvent event) {
		try {
			DefaultWeightedEdge e = boxArco.getValue();
			if (e == null) {
				txtResult.appendText("Non hai selezionato una coppia di vertici \n");
				return;
			}
			model.calcolaPercorso(e);
			txtResult.appendText(model.formatta()+"\n");
		} catch (Exception e) {
			txtResult.setText("Errore!");
		}
	}

	@FXML
	void doCreaGrafo(ActionEvent event) {
		try {
			
			btnPercorso.setDisable(true);
			String categoria = boxCategoria.getValue();
			Month mese = boxMese.getValue();
			int month = mese.getValue();
			model.creaGrafo(month, categoria);

			boxArco.getItems().addAll(model.getEdges());
			btnPercorso.setDisable(false);

			txtResult.setText(model.output() + "\n");
		} catch (Exception e) {
			txtResult.setText("Errore!");
		}
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert boxCategoria != null : "fx:id=\"boxCategoria\" was not injected: check your FXML file 'Scene.fxml'.";
		assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert boxArco != null : "fx:id=\"boxArco\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;

		List<String> ls = model.getCategories();
		boxCategoria.getItems().addAll(ls);
		boxCategoria.setValue(ls.get(0));

		List<Month> lc = model.getMonths();
		boxMese.getItems().addAll(lc);
		boxMese.setValue(lc.get(0));
	}
}

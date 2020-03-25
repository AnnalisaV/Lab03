package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.ModelDictionary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class FXMLController {

	private ModelDictionary model; 
	
	public void setModel(ModelDictionary model) {
		this.model= model; 
	}
	
	private ObservableList<String> lista = FXCollections.observableArrayList(); // per popolare il choiceBox
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextArea txtInserito;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtErrate;


    @FXML
    private Text numeroErrori;

    @FXML
    private Button btnClear;

    @FXML
    private Text tempo;

    /**
     * Pulire le aree di testo
     * @param event
     */
    @FXML
    void doClearText(ActionEvent event) {

    	this.txtInserito.clear();
    	this.txtErrate.clear();
    	this.numeroErrori.setText("The text contains / errors.");
    	this.tempo.setText("Spell check completed in / seconds");
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	// avvio il calcolo dle tempo per effettuare qst operazione
    	long inizio = System.nanoTime();
    	
    	
    	txtErrate.clear();
    	
    	String input= txtInserito.getText(); 
    	input=input.toLowerCase(); // conversione in minuscolo
    	input= input.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_()\\[\\]\"]", ""); // per eliminare i segni di interpunzione
    	
    	List<String> errate= this.model.spellCheckText(input,choiceBox.getValue()); 
    	
    	for (String s: errate) {
    		txtErrate.appendText(s+"\n");
    	}
    	
    	numeroErrori.setText("The text contains "+errate.size()+" errors."); 

    	this.tempo.setText("Spell check completed in "+ (System.nanoTime()-inizio)/1e9+ " seconds");
    }

    @FXML
    void initialize() {
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInserito != null : "fx:id=\"txtInserito\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrate != null : "fx:id=\"txtErrate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert numeroErrori != null : "fx:id=\"numeroErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tempo != null : "fx:id=\"tempo\" was not injected: check your FXML file 'Scene.fxml'.";

        
        // popolare il choiceBox 
       lista.addAll("English", "Italiano"); 
       choiceBox.setItems(lista);
       choiceBox.setValue("Italiano");
    }
}


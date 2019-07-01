package controller;

import java.util.Observable;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.Collana;
import models.Gioiello;
import models.MATERIALE;

public class ModificaDatiCollanaController extends Observable
{
	Collana collana;
	
	@FXML
    private JFXComboBox<MATERIALE> selezionaMaterialeBox;

    @FXML
    private JFXTextField lunghezzaTextField;

    @FXML
    private JFXTextField nomeGioielloTextField;

    @FXML
    private JFXTextField spessoreTextField;

    @FXML
    private JFXTextField inserisciPesoTextField;

    @FXML
    private JFXTextField inserisciPrezzoTextField;

    @FXML
    private JFXComboBox<String> selezionaGenereBox;

    @FXML
    private JFXComboBox<String> ciondoloComboBox;

    @FXML
    private JFXTextArea descrizioneTextArea;
    
    public void initialize(Collana gioiello)
    {
    	this.collana = gioiello;
    	
    	ObservableList<MATERIALE> opzioni = FXCollections.observableArrayList(MATERIALE.ORO_BIANCO,MATERIALE.ORO_GIALLO,MATERIALE.ARGENTO,MATERIALE.ACCIAIO,MATERIALE.RAME,MATERIALE.ZINCO);
		selezionaMaterialeBox.setItems(opzioni);
		
		ObservableList<String> opzioni2 = FXCollections.observableArrayList("Maschile","Femminile");
		selezionaGenereBox.setItems(opzioni2);
		
		ObservableList<String> opzioni3 = FXCollections.observableArrayList("Si","No");
		ciondoloComboBox.setItems(opzioni3);
		
		selezionaMaterialeBox.setValue(collana.getMateriale());
		inserisciPrezzoTextField.setText(Double.toString(collana.getPrezzo()));
		lunghezzaTextField.setText(Double.toString(collana.getLunghezza()));
		nomeGioielloTextField.setText(collana.getNomeGioiello());
		spessoreTextField.setText(Double.toString(collana.getSpessore()));
		inserisciPesoTextField.setText(Double.toString(collana.getPeso()));
		selezionaGenereBox.setValue(collana.getGenere());
		descrizioneTextArea.setText(collana.getDescrizione());
		
		if(collana.isCiondolo()) ciondoloComboBox.setValue("Si");
		else ciondoloComboBox.setValue("No");
    }
    
    public Gioiello getGioiello() { return this.collana; }
    
    @FXML
    void submit(ActionEvent event) 
    {
    	collana.setDescrizione(descrizioneTextArea.getText());
    	collana.setGenere(selezionaGenereBox.getValue());
    	collana.setLunghezza(Double.parseDouble(lunghezzaTextField.getText()));
    	collana.setMateriale(selezionaMaterialeBox.getValue());
    	collana.setNomeGioiello(nomeGioielloTextField.getText());
    	collana.setPeso(Double.parseDouble(inserisciPesoTextField.getText()));
    	collana.setPrezzo(Double.parseDouble(inserisciPesoTextField.getText()));
    	collana.setSpessore(Double.parseDouble(spessoreTextField.getText()));
    	
    	boolean ciondolo = false;
    	if(ciondoloComboBox.getValue().equals("Si")) ciondolo = true;
    	
    	collana.setCiondolo(ciondolo);
    	collana.modificaDati();
    	setChanged();
    	notifyObservers("Collana modificata");	
    }

}

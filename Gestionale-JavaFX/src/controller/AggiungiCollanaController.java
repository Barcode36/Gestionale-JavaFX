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

public class AggiungiCollanaController extends Observable
{
	Gioiello gioiello;
	
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

    public void initialize()
    {
    	ObservableList<MATERIALE> opzioni = FXCollections.observableArrayList(MATERIALE.ORO_BIANCO,MATERIALE.ORO_GIALLO,MATERIALE.ARGENTO,MATERIALE.ACCIAIO,MATERIALE.RAME,MATERIALE.ZINCO);
		selezionaMaterialeBox.setItems(opzioni);
		
		ObservableList<String> opzioni2 = FXCollections.observableArrayList("Maschile","Femminile");
		selezionaGenereBox.setItems(opzioni2);
		
		ObservableList<String> opzioni3 = FXCollections.observableArrayList("Si","No");
		ciondoloComboBox.setItems(opzioni3);
    }
    
    public Gioiello getGioiello() { return this.gioiello; }
    
    @FXML
    void submit(ActionEvent event) 
    {
    	boolean ciondolo = false;
    	if(ciondoloComboBox.getValue().equals("Si")) ciondolo = true;
    	
    	double prezzo = Double.parseDouble(inserisciPrezzoTextField.getText());
		double peso = Double.parseDouble(inserisciPesoTextField.getText());
		MATERIALE materiale = selezionaMaterialeBox.getValue();
		String genere = selezionaGenereBox.getValue();
		boolean venduto = false;
		double lunghezza = Double.parseDouble(lunghezzaTextField.getText());
		double spessore = Double.parseDouble(spessoreTextField.getText());
		String nomeGioiello = nomeGioielloTextField.getText();
		String descrizione = descrizioneTextArea.getText();
		
		gioiello = new Collana(prezzo,peso,materiale,genere,venduto,descrizione,nomeGioiello,lunghezza,ciondolo,spessore);
		gioiello.salva();
		setChanged();
		notifyObservers("Collana creata");
    }

}

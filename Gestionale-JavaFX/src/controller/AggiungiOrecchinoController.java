package controller;

import java.util.Observable;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.Bracciale;
import models.Gioiello;
import models.MATERIALE;
import models.Orecchino;

public class AggiungiOrecchinoController extends Observable
{
	private Gioiello gioiello;
	
	@FXML
    private JFXComboBox<MATERIALE> selezionaMaterialeBox;

    @FXML
    private JFXTextField nomeGioielloTextField;

    @FXML
    private JFXTextField spessoreTextField;

    @FXML
    private JFXTextField inserisciPesoTextField;

    @FXML
    private JFXTextField altezzaTextField;

    @FXML
    private JFXTextField tipologiaTextField;

    @FXML
    private JFXTextField inserisciPrezzoTextField;

    @FXML
    private JFXComboBox<String> selezionaGenereBox;

    @FXML
    private JFXTextArea descrizioneTextArea;

    public void initialize()
	{
		ObservableList<MATERIALE> opzioni = FXCollections.observableArrayList(MATERIALE.ORO_BIANCO,MATERIALE.ORO_GIALLO,MATERIALE.ARGENTO,MATERIALE.ACCIAIO,MATERIALE.RAME,MATERIALE.ZINCO);
		selezionaMaterialeBox.setItems(opzioni);
		
		ObservableList<String> opzioni2 = FXCollections.observableArrayList("Maschile","Femminile");
		selezionaGenereBox.setItems(opzioni2);
	}
    
    public Gioiello getGioiello() { return this.gioiello; }
    
    @FXML
    void submit(ActionEvent event) 
    {
    	double prezzo = Double.parseDouble(inserisciPrezzoTextField.getText());
		double peso = Double.parseDouble(inserisciPesoTextField.getText());
		MATERIALE materiale = selezionaMaterialeBox.getValue();
		String genere = selezionaGenereBox.getValue();
		boolean venduto = false;
		double spessore = Double.parseDouble(spessoreTextField.getText());
		String nomeGioiello = nomeGioielloTextField.getText();
		String descrizione = descrizioneTextArea.getText();
		double altezza = Double.parseDouble(spessoreTextField.getText());
		String tipologiaOrecchino = tipologiaTextField.getText();
		gioiello = new Orecchino(prezzo, peso, materiale, genere, venduto, descrizione, nomeGioiello, spessore, altezza, tipologiaOrecchino);
		gioiello.salva();
		setChanged();
		notifyObservers("Orecchino Creato");
    }

}

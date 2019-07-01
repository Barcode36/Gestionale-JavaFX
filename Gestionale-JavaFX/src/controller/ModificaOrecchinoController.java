package controller;

import java.util.Observable;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.Gioiello;
import models.MATERIALE;
import models.Orecchino;

public class ModificaOrecchinoController extends Observable
{
	private Orecchino orecchino;
	
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
    
    public Gioiello getGioiello() { return this.orecchino; }
    
    public void initialize(Gioiello gioiello)
	{
    	orecchino = (Orecchino)gioiello;
		ObservableList<MATERIALE> opzioni = FXCollections.observableArrayList(MATERIALE.ORO_BIANCO,MATERIALE.ORO_GIALLO,MATERIALE.ARGENTO,MATERIALE.ACCIAIO,MATERIALE.RAME,MATERIALE.ZINCO);
		selezionaMaterialeBox.setItems(opzioni);
		
		ObservableList<String> opzioni2 = FXCollections.observableArrayList("Maschile","Femminile");
		selezionaGenereBox.setItems(opzioni2);
		
		selezionaMaterialeBox.setValue(orecchino.getMateriale());
		nomeGioielloTextField.setText(orecchino.getNomeGioiello());
		spessoreTextField.setText(Double.toString(orecchino.getSpessore()));
		inserisciPesoTextField.setText(Double.toString(orecchino.getPeso()));
		altezzaTextField.setText(Double.toString(orecchino.getAltezza()));
		tipologiaTextField.setText(orecchino.getTipologiaOrecchino());
		inserisciPrezzoTextField.setText(Double.toString(orecchino.getPrezzo()));
		selezionaGenereBox.setValue(orecchino.getGenere());
		descrizioneTextArea.setText(orecchino.getDescrizione());
	}
    
    @FXML
    void submit(ActionEvent event) 
    {
    	orecchino.setMateriale(selezionaMaterialeBox.getValue());
    	orecchino.setNomeGioiello(nomeGioielloTextField.getText());
    	orecchino.setSpessore(Double.parseDouble(spessoreTextField.getText()));
    	orecchino.setPeso(Double.parseDouble(inserisciPesoTextField.getText()));
    	orecchino.setAltezza(Double.parseDouble(altezzaTextField.getText()));
    	orecchino.setTipologiaOrecchino(tipologiaTextField.getText());
    	orecchino.setGenere(selezionaGenereBox.getValue());
    	orecchino.setPrezzo(Double.parseDouble(inserisciPrezzoTextField.getText()));
    	orecchino.setDescrizione(descrizioneTextArea.getText());
    	
    	orecchino.modificaDati();
    	setChanged();
    	notifyObservers("Orecchino modificato");
    }
}

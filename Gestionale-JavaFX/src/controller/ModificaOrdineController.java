package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import models.Gioiello;
import models.Ordine;

public class ModificaOrdineController 
{
	private Ordine ordine;
	private Gioiello gioiello;
	
	@FXML
	private DatePicker selettoreDataConsegna;

	@FXML
	private JFXListView<Gioiello> gioielloListView;

	@FXML
	private JFXComboBox<String> tipologiaComboBox;

	@FXML
	private JFXButton bottone;

	@FXML
	private JFXTextArea descrizioneTextArea;
	
	public void initialize(Ordine o, ArrayList<Gioiello> gioielli)
	{
		ObservableList<String> opzioni = FXCollections.observableArrayList("Riparazione","Creazione","Altro");
    	tipologiaComboBox.setItems(opzioni);
    	tipologiaComboBox.setValue(o.getTipologia());
    	
		gioielloListView.getItems().addAll(gioielli);
		ordine = o;
		selettoreDataConsegna.setValue(LocalDate.parse(ordine.getDataScadenza()));
		descrizioneTextArea.setText(o.getDescrizione());
		
		gioielloListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Gioiello> obs, Gioiello oldVal, Gioiello newVal) -> {
    		
    		gioiello = newVal;
		});
	}
	
	@FXML
    void bottonePremuto(ActionEvent event) 
    {
    	ordine.setDataScadenza(selettoreDataConsegna.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    	ordine.setTipologia(tipologiaComboBox.getValue());
    	ordine.setDescrizione(descrizioneTextArea.getText());
    	ordine.setGioiello(gioiello);
    }
}

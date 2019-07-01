package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Observable;

import gestioneDB.GestioneQuery;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import models.Gioiello;
import models.Ordine;

public class AggiungiOrdineController extends Observable
{
	private Ordine ordine;
	private Gioiello gioiello;
	private String data;
	
	@FXML
    private DatePicker selettoreDataConsegna;

    @FXML
    private ListView<Gioiello> gioielloListView;

    @FXML
    private ComboBox<String> tipologiaComboBox;

    @FXML
    private TextArea descrizioneTextArea;
	
    @FXML
    private Button bottone;
    
    public Ordine getOrdine() { return this.ordine; }
    
    public void initialize(ArrayList<Gioiello> gioielli)
    {
    	gioielloListView.getItems().clear();
    	gioielloListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	ObservableList<String> opzioni = FXCollections.observableArrayList("Riparazione","Creazione","Altro");
    	tipologiaComboBox.setItems(opzioni);
    	gioielloListView.getItems().addAll(gioielli);
		
    	gioielloListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Gioiello> obs, Gioiello oldVal, Gioiello newVal) -> {
    		
    		gioiello = newVal;
    		try
    		{
    			data = selettoreDataConsegna.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    		}
    		catch(NullPointerException e)
    		{
    			data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    		}
    		
		});
    }
    
    @FXML
    void bottonePremuto(ActionEvent event) 
    {
    	GestioneQuery database = new GestioneQuery();
    	ordine = new Ordine(database.getNumUltimoOrdine() + 1,data,gioiello,tipologiaComboBox.getValue(),descrizioneTextArea.getText(),0);
    	database.chiudiConnessione();
    	setChanged();
    	notifyObservers("Ordine Creato");
    }
}

package controller;

import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import eventi.CaricaFinestre;
import gestioneDB.GestioneQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import models.Gioiello;
import models.Ordine;

public class AggiungiOrdineController extends Observable implements Observer
{
	private Ordine ordine;
	private Gioiello gioiello;
	private String data;
	private VisualizzaTuttoController visualizzaTutto;
	private SearchController cercaController;
	
	@FXML
	private DatePicker selettoreDataConsegna;

	@FXML
	private JFXButton bottoneCerca;

	@FXML
	private JFXTextField nomeGioielloTextField;

	@FXML
	private JFXComboBox<String> tipologiaComboBox;

	@FXML
	private JFXButton bottoneVisualizzaTutti;

	@FXML
	private JFXButton bottone;

	@FXML
	private JFXTextArea descrizioneTextArea;

	
    
    public Ordine getOrdine() { return this.ordine; }
    
    public void initialize()
    {
    	ObservableList<String> opzioni = FXCollections.observableArrayList("Riparazione","Creazione","Altro");
    	tipologiaComboBox.setItems(opzioni);
    	
    }
    
    @FXML
    void bottonePremuto(ActionEvent event) 
    {
    	data =  selettoreDataConsegna.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    	GestioneQuery database = new GestioneQuery();
    	ordine = new Ordine(database.getNumUltimoOrdine() + 1,data,gioiello,tipologiaComboBox.getValue(),descrizioneTextArea.getText(),0);
    	database.chiudiConnessione();
    	setChanged();
    	notifyObservers("Ordine Creato");
    }
    
    @FXML
	void BottoneCercaPremuto(ActionEvent event) 
    {
    	CaricaFinestre carica = new CaricaFinestre();
    	cercaController = carica.getSearchController();
    	cercaController.start();
    	cercaController.addObserver(this);
	}

	@FXML
	void bottoneVisualizzaTuttiPremuto(ActionEvent event) 
	{
		CaricaFinestre carica = new CaricaFinestre();
		visualizzaTutto = carica.getVisualizzaTutto();
		visualizzaTutto.start();
		visualizzaTutto.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg.equals("Gioiello selezionato cerca"))
		{
			gioiello = cercaController.getGioiello();
			nomeGioielloTextField.setText(gioiello.getNomeGioiello());
		}
		
		if(arg.equals("Gioiello selezionato visualizza"))
		{
			gioiello = visualizzaTutto.getGioiello();
			nomeGioielloTextField.setText(gioiello.getNomeGioiello());
		}
	}
}

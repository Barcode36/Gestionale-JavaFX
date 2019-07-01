package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import eventi.CaricaFinestre;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Anello;
import models.Bracciale;
import models.Gioiello;
import models.Immagine;
import models.MATERIALE;

public class SearchController extends Observable implements Observer 
{
	final ToggleGroup gruppoPeso = new ToggleGroup();
	final ToggleGroup gruppoPrezzo = new ToggleGroup();
	private ContextMenu contextMenuImmagini;
	private ContextMenu contextMenuGioielli;
	private ModificaDatiAnelloController modificaAnello;
	private ModificaDatiBraccialeController modificaBracciale;
	
	@FXML
    private JFXComboBox<MATERIALE> materialeComboBox;

    @FXML
    private JFXButton searchButton;
    
    @FXML
    private JFXTextArea textAreaGioielli;

    @FXML
    private JFXTextField prezzoTextField;

    @FXML
    private JFXComboBox<String> genereComboBox;

    @FXML
    private JFXRadioButton ugualeRadioPrezzo;

    @FXML
    private JFXComboBox<String> tipologiaComboBox;

    @FXML
    private JFXListView<Gioiello> listViewGioielli;

    @FXML
    private JFXTextField pesoTextField;

    @FXML
    private JFXRadioButton maggioreRadioPeso;

    @FXML
    private JFXRadioButton ugualeRadioPeso;

    @FXML
    private ListView<ImageView> listViewImmagini;

    @FXML
    private JFXTextField nomeTextField;

    @FXML
    private JFXRadioButton minoreRadioPrezzo;

    @FXML
    private JFXRadioButton maggioreRadioPrezzo;

    @FXML
    private JFXRadioButton minoreRadioPeso;

    private void initialize()
    {
    	ObservableList<MATERIALE> opzioni = FXCollections.observableArrayList(MATERIALE.ORO_BIANCO,MATERIALE.ORO_GIALLO,MATERIALE.ARGENTO,MATERIALE.ACCIAIO,MATERIALE.RAME,MATERIALE.ZINCO);
		materialeComboBox.setItems(opzioni);
		
		ObservableList<String> opzioni2 = FXCollections.observableArrayList("Anello", "Bracciale", "Orecchino", "Collana");
		tipologiaComboBox.setItems(opzioni2);
		
		ObservableList<String> opzioni3 = FXCollections.observableArrayList("Maschile","Femminile");
		genereComboBox.setItems(opzioni3);
		
		ugualeRadioPrezzo.setToggleGroup(gruppoPrezzo);
		maggioreRadioPrezzo.setToggleGroup(gruppoPrezzo);
		minoreRadioPrezzo.setToggleGroup(gruppoPrezzo);
		
		ugualeRadioPeso.setToggleGroup(gruppoPeso);
		minoreRadioPeso.setToggleGroup(gruppoPeso);
		maggioreRadioPeso.setToggleGroup(gruppoPeso);
		
		contextMenuImmagini = new ContextMenu();
		contextMenuImmagini.getItems().addAll(new MenuItem("Elimina Immagine"), new MenuItem("Visualizza"));
		listViewImmagini.setContextMenu(contextMenuImmagini);
		
		contextMenuGioielli = new ContextMenu();
		contextMenuGioielli.getItems().addAll(new MenuItem("Elimina Gioiello"), new MenuItem("Aggiungi Immagine"), new MenuItem("Modifica"));
		listViewGioielli.setContextMenu(contextMenuGioielli);
		listViewGioielli.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    
    public void start()
    {
    	initialize();
    	tabGioielli();
    	visualizzazioneImmagini();
    }
    
    @FXML
    void searchButtonPressed(ActionEvent event) 
    {	
    	listViewGioielli.getItems().clear();
    	listViewGioielli.getItems().addAll(Gioiello.caricaGioielli(componiQuery(), tipologiaComboBox.getValue()));
    }
    
    private String componiQuery()
    {
    	Pattern stringaVuota = Pattern.compile("^$");	
    	Matcher nomeVuoto = stringaVuota.matcher(nomeTextField.getText());
    	Matcher prezzoVuoto = stringaVuota.matcher(prezzoTextField.getText());
    	Matcher pesoVuoto = stringaVuota.matcher(pesoTextField.getText());
    	
    	String nome = " ";
    	if(!nomeVuoto.matches()) nome = " and nomeGioiello = '" + nomeTextField.getText() + "'";
    	
    	String prezzo = " ";
    	String maggioreMinoreUgualePrezzo = " ";
    	if(!prezzoVuoto.matches())
    	{
    		if(maggioreRadioPrezzo.isSelected()) maggioreMinoreUgualePrezzo = " > ";
    		if(minoreRadioPrezzo.isSelected()) maggioreMinoreUgualePrezzo = " < ";
    		if(ugualeRadioPrezzo.isSelected()) maggioreMinoreUgualePrezzo = " = ";
    		
    		prezzo = " and prezzo " + maggioreMinoreUgualePrezzo + prezzoTextField.getText();
    	}
    	
    	String peso = " ";
    	String maggioreMinoreUgualePeso = " ";
    	if(!pesoVuoto.matches())
    	{
    		
    		if(maggioreRadioPeso.isSelected()) maggioreMinoreUgualePeso = " > ";
    		if(minoreRadioPeso.isSelected()) maggioreMinoreUgualePeso = " < ";
    		if(ugualeRadioPeso.isSelected()) maggioreMinoreUgualePeso = " = ";
    		
    		peso = " and peso " + maggioreMinoreUgualePeso + pesoTextField.getText();
    	}
    	
    	String query = "select * from prodotto inner join "+ tipologiaComboBox.getValue()+ " on " + tipologiaComboBox.getValue() + ".idProdotto = prodotto.idProdotto "
    			+ "where genere = '" + genereComboBox.getValue() + "' and "
    			+ "materiale = '" + materialeComboBox.getValue()+"' " + prezzo + nome +  peso;
    	System.out.println(query);
    	return query;
    }
    
    private void tabGioielli()
	{
		listViewGioielli.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Gioiello> obs, Gioiello oldVal, Gioiello newVal) -> {
			textAreaGioielli.clear();
			textAreaGioielli.setText(newVal.stampaCaratteristiche());
			try
			{
				listViewImmagini.getItems().clear();
				textAreaGioielli.setText(newVal.stampaCaratteristiche());
				listViewImmagini.getItems().addAll(caricaImmagini(newVal.caricaImmagini()));
				//gioiello = newVal; //per controllare il tipo di gioiello quando si premono i bottoni modifica ed elimina
				//indice = listViewGioielli.getSelectionModel().getSelectedIndex(); //conservo l'indice per eliminare il gioiello dalla listView
			}
			catch(NullPointerException e)
			{
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.getButtonTypes().clear();
	    		alert.getButtonTypes().add(new ButtonType("Chiudi") );
	    		alert.setTitle(null);
	    		alert.setHeaderText(null);
	    		alert.setContentText("Ora non è presente nessun prodotto");
	    		alert.showAndWait();
			}
			
			contextMenuGioielli.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					listViewGioielli.getItems().remove(listViewGioielli.getSelectionModel().getSelectedItem());
					newVal.eliminaGioiello();
				}
			});
			
			contextMenuGioielli.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					FileChooser percorsoSalvataggio = new FileChooser();
					percorsoSalvataggio.setTitle("Carica Immagine");
					//percorsoSalvataggio.getExtensionFilters().add();	
					List<File> immagini = percorsoSalvataggio.showOpenMultipleDialog(new Stage());
					if(immagini != null)
					{
						for(File f : immagini)
						{
							try 
							{
								newVal.aggiungiImmagine(new FileInputStream(f));
								ImageView im = new ImageView(new Immagine(new FileInputStream(f)));
								im.setFitHeight(300);
								im.setFitWidth(300);
								listViewImmagini.getItems().add(im);
							} 
							catch (FileNotFoundException e) 
							{
								e.printStackTrace();
							}
						}
					}
				}
			});
			
			contextMenuGioielli.getItems().get(2).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					if(newVal instanceof Anello)
					{
						
						modificaAnello = getModificaDatiAnello();
						modificaAnello.initialize((Anello)newVal);
					}
					if(newVal instanceof Bracciale)
					{
						modificaBracciale = getModificaDatiBracciale();
						modificaBracciale.initialize((Bracciale)newVal);
					}
				}
			});
		});
	}
    
    private void visualizzazioneImmagini()
	{
		listViewImmagini.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends ImageView> obs, ImageView oldVal, ImageView newVal) -> {
			
			contextMenuImmagini.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					listViewImmagini.getItems().remove(listViewImmagini.getSelectionModel().getSelectedIndex());
					Immagine i = (Immagine) newVal.getImage();
					i.eliminaImmagine();
				}
			});
			
			contextMenuImmagini.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					CaricaFinestre caricaFinestre = new CaricaFinestre();
					ControllerVisualizzatoreImmagini visualizzatore = caricaFinestre.getControllerVisualizzatoreImmagini();
					visualizzatore.setImmagini(listViewImmagini);
				}
			});
		});
	}
    
    private ArrayList<ImageView> caricaImmagini(ArrayList<Immagine> immagini)
	{
		ArrayList<ImageView> imageViewImmagini = new ArrayList<ImageView>();
		for(Immagine i : immagini)
		{
			ImageView im = new ImageView(i);
			im.setFitHeight(300);
			im.setFitWidth(300);
			imageViewImmagini.add(im);
		}
		
		return imageViewImmagini;
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg.equals("modificato bracciale"))
		{
			Gioiello g = modificaBracciale.getGioiello();
			textAreaGioielli.setText(g.stampaCaratteristiche());
		}
		if(arg.equals("anello modificato"))
		{
			Gioiello g = modificaAnello.getAnello();
			textAreaGioielli.setText(g.stampaCaratteristiche());
		}
	}
	
	private ModificaDatiAnelloController getModificaDatiAnello()
	{
		CaricaFinestre caricaFinestre = new CaricaFinestre();
		ModificaDatiAnelloController controller = caricaFinestre.getModificaDatiAnello();
		controller.addObserver(this);
		return controller;
	}
	
	private ModificaDatiBraccialeController getModificaDatiBracciale()
	{
		CaricaFinestre caricaFinestre = new CaricaFinestre();
		ModificaDatiBraccialeController controller = caricaFinestre.getModificaDatiBracciale();
		controller.addObserver(this);
		return controller;
	}
}

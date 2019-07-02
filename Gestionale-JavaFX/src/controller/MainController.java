package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;

import eventi.CaricaFinestre;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import models.Anello;
import models.Bracciale;
import models.Cliente;
import models.Collana;
import models.Fattura;
import models.Gioiello;
import models.Immagine;
import models.Ordine;
import models.Orecchino;

public class MainController implements Observer
{	
	private Gioiello gioiello;
	private Cliente cliente;
	private int indice; //indice del gioiello selezionato nella listView
	private ContextMenu contextMenuClienti;
	private ContextMenu contextMenuGioielli;
	private ContextMenu contextMenuOrdini;
	private ContextMenu contextMenuFatture;
	private ContextMenu contextMenuImmagini;
	private AggiungiAnelloController controllerAnello;
	private AggiungiClienteController controllerCliente;
	private AggiungiBraccialeController controllerBracciale;
	private AggiungiOrdineController controllerOrdine;
	private AggiungiOrecchinoController controllerOrecchino;
	private AggiungiCollanaController controllerCollana;
	private ModificaDatiBraccialeController modificaBracciale;
	private ModificaDatiAnelloController modificaAnello;
	private ModificaDatiCollanaController modificaCollana;
	private ModificaOrecchinoController modificaOrecchino;
	private ModificaDatiClienteController modificaCliente;
	private ModificaOrdineController modificaOrdine;
	private VisualizzaTuttoController visualizzaTuttoController;
	private ControllerEmettiFattura emettiFatturaController;
	private CaricaFinestre caricaFinestre;
	
	@FXML
    private TabPane tabPane;
	
	@FXML
	private BorderPane borderPaneRoot;
	
	@FXML
    private Tab tabClienti;
	
	@FXML
	private JFXTextArea textAreaGioielli;
	
	@FXML
    private JFXTextArea textAreaClienti;
	
	@FXML
    private JFXTextArea textAreaFatture;
	
	@FXML
	private JFXTextArea textAreaOrdine;
	
	@FXML
    private JFXTextArea textAreaGioielloOrdine;
	
	@FXML
	private MenuItem salvaMenuItem;
	
	@FXML
    private MenuItem aggiungiOrecchinoMenuItem;
	
	@FXML
    private JFXListView<Gioiello> listViewGioielli;
	
	@FXML
	private ListView<ImageView> listViewImmagini;
	
	@FXML
	private JFXListView<Cliente> listViewClienti;
	
	@FXML
    private JFXListView<Ordine> listViewOrdini;
	
	@FXML
    private JFXListView<Fattura> listViewFatture;
	
	@FXML
    private JFXButton aggiungiClienteButton;
	
	@FXML
    private JFXButton eliminaButtonGioielli;
	
	@FXML
	private JFXButton modificaButtonGioielli;
	
	@FXML
	private JFXButton visualizzaTuttoButton;
	
	@FXML
    private JFXButton serchButton;
	
	@FXML
    private MenuItem aggiungiBraccialeMenuItem;
	
	@FXML
    private MenuItem aggiungiAnelloMenuItem;
	
	@FXML
    private MenuItem aggiungiCollanaMenuItem;
	
	
	public void start()
	{	
		caricaFinestre = new CaricaFinestre();
		carica();
		contextMenuClienti = new ContextMenu();
		contextMenuGioielli = new ContextMenu();
		contextMenuOrdini = new ContextMenu();
		contextMenuFatture = new ContextMenu();
		contextMenuImmagini = new ContextMenu();
		contextMenuClienti.getItems().addAll(new MenuItem("Aggiungi Ordine"),new MenuItem("Modifica"), new MenuItem("Elimina"));
		contextMenuGioielli.getItems().addAll(new MenuItem("Elimina Gioiello"), new MenuItem("Aggiungi Immagine"), new MenuItem("Modifica"));
		contextMenuOrdini.getItems().addAll(new MenuItem("Elimina Ordine"), new MenuItem("Emetti Fattura"), new MenuItem("Modifica"));
		contextMenuFatture.getItems().addAll(new MenuItem("Stampa Fattura"),new MenuItem("Elimina Fattura"));
		contextMenuImmagini.getItems().addAll(new MenuItem("Elimina Immagine"), new MenuItem("Visualizza"));
		listViewOrdini.setContextMenu(contextMenuOrdini);
		listViewGioielli.setContextMenu(contextMenuGioielli);
		listViewClienti.setContextMenu(contextMenuClienti);
		listViewFatture.setContextMenu(contextMenuFatture);
		listViewImmagini.setContextMenu(contextMenuImmagini);
		listViewGioielli.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listViewClienti.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listViewOrdini.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listViewImmagini.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		showInListView();
	}
	
	private void showInListView()
	{	
		tabGioielli();
		tabClienti();
		visualizzazioneOrdini();
		tabFatture();
		visualizzazioneImmagini();
	}
	
	@FXML
	void aggiungiAnelloClicked(ActionEvent event)
	{
		controllerAnello = caricaFinestre.getAnelloController();
		controllerAnello.initialize();
		controllerAnello.addObserver(this); 
	}
	
	@FXML
    void aggiungiBraccialeClicked(ActionEvent event)
	{
		controllerBracciale = caricaFinestre.getBraccialeController();
		controllerBracciale.initialize();
		controllerBracciale.addObserver(this); 
    }
	
	@FXML
    void aggiungiOrecchinoClicked(ActionEvent event) 
	{
		controllerOrecchino = caricaFinestre.getOrecchinoController();
		controllerOrecchino.initialize();
		controllerOrecchino.addObserver(this);
    }
	
	@FXML
    void aggiungiCollanaClicked(ActionEvent event) 
	{
		controllerCollana = caricaFinestre.getCollanaController();
		controllerCollana.initialize();
		controllerCollana.addObserver(this);
    }
	
	@FXML
    void aggiungiCliente(ActionEvent event)
	{
		controllerCliente = caricaFinestre.getClienteController();
		controllerCliente.addObserver(this);
    }

	@FXML
    void menuItemChiudiPressed(ActionEvent event) 
	{
		Stage root = (Stage) borderPaneRoot.getScene().getWindow();
		root.close();
    }
	
	@FXML
	void menuItemAboutPressed(ActionEvent event) 
	{
		AboutController about = caricaFinestre.getAboutController();
		about.initialize();
	}
	
	@FXML
    void modificaButtonGioielliPressed(ActionEvent event) 
	{
		if(gioiello != null) apriFinestreModifica(gioiello);
    }

    @FXML
    void eliminaButtonGioielliPressed(ActionEvent event) 
    {
    	if(gioiello != null)
    	{
    		ButtonType si = new ButtonType("Si");
    		ButtonType annulla = new ButtonType("Annulla");
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.getButtonTypes().clear();
    		alert.getButtonTypes().addAll(si,annulla);
    		alert.setTitle("ATTENZIONE");
    		alert.setHeaderText(null);
    		alert.setContentText("Vuoi davvero eliminare quest'oggetto?");
    		Optional<ButtonType> result = alert.showAndWait();
    		if(result.get().equals(si))
    		{
    			listViewGioielli.getItems().remove(indice);
    			textAreaGioielli.clear();
        		gioiello.eliminaGioiello();
        		gioiello = null;
    		}
    		else if(result.get() == annulla);
    	}
    }
    
    @FXML
    void searchButtonPressed(ActionEvent event) 
    {
    	SearchController search = caricaFinestre.getSearchController();
    	search.start();
    }
    
    @FXML
    void visualizzaTuttoPressed(ActionEvent event) 
    {
    	visualizzaTuttoController = caricaFinestre.getVisualizzaTutto();
    	visualizzaTuttoController.addObserver(this);
    	visualizzaTuttoController.start();
    }
	
	@Override
	public void update(Observable o, Object arg) 
	{ 
		if(arg.equals("Anello Creato")) listViewGioielli.getItems().add(controllerAnello.getGioiello());
		if(arg.equals("Bracciale Creato")) listViewGioielli.getItems().add(controllerBracciale.getGioiello());
		if(arg.equals("Cliente creato")) listViewClienti.getItems().add(cliente = controllerCliente.getCliente());
		
		if(arg.equals("Ordine Creato"))
		{
			Ordine ordine = controllerOrdine.getOrdine();
			ordine.setIdCLiente(cliente.getId());
			cliente.aggiungiOrdine(controllerOrdine.getOrdine());
			listViewOrdini.getItems().add(controllerOrdine.getOrdine());
		}
		
		if(arg.equals("Orecchino Creato")) listViewGioielli.getItems().add(controllerOrecchino.getGioiello());
		if(arg.equals("Collana creata")) listViewGioielli.getItems().add(controllerCollana.getGioiello());
		if(arg.equals("modificato bracciale")) textAreaGioielli.setText(modificaBracciale.getGioiello().stampaCaratteristiche());
		if(arg.equals("anello modificato")) textAreaGioielli.setText(modificaAnello.getAnello().stampaCaratteristiche());
		if(arg.equals("Collana modificata")) textAreaGioielli.setText(modificaCollana.getGioiello().stampaCaratteristiche());
		if(arg.equals("Orecchino modificato")) textAreaGioielli.setText(modificaOrecchino.getGioiello().stampaCaratteristiche());
		
		if(arg.equals("Gioiello eliminato"))
		{
			Gioiello gio = visualizzaTuttoController.getGioiello();
			for(int i = 0; i < listViewGioielli.getItems().size(); i++)
			{
				if(listViewGioielli.getItems().get(i).getId() == gio.getId()) listViewGioielli.getItems().remove(i);
			}
		}
	}
	
	private AggiungiOrdineController getControllerOrdine()
	{
		AggiungiOrdineController controller = caricaFinestre.getControllerOrdine();
		controller.addObserver(this);
		return controller;
	}
	
	private void tabGioielli()
	{
		listViewGioielli.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Gioiello> obs, Gioiello oldVal, Gioiello newVal) -> {
			
			try
			{
				listViewImmagini.getItems().clear();
				textAreaGioielli.setText(newVal.stampaCaratteristiche());
				listViewImmagini.getItems().addAll(caricaImmagini(newVal.caricaImmagini()));
				gioiello = newVal; //per controllare il tipo di gioiello quando si premono i bottoni modifica ed elimina
				indice = listViewGioielli.getSelectionModel().getSelectedIndex();
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
					apriFinestreModifica(newVal);
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
	
	private void tabClienti()
	{
		listViewClienti.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Cliente> obs, Cliente oldVal, Cliente newVal) -> {
			textAreaClienti.clear();
			textAreaClienti.setText(newVal.stampaCaratteristiche());
			
			listViewOrdini.getItems().clear();
			textAreaOrdine.clear();
			textAreaGioielloOrdine.clear();
			ArrayList<Ordine> ordini = newVal.getOrdini();
			if(ordini.size() > 0) listViewOrdini.getItems().addAll(ordini);
			
			contextMenuClienti.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
						controllerOrdine = getControllerOrdine();
						controllerOrdine.initialize();
						cliente = newVal;
				}
			});
			
			contextMenuClienti.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					modificaCliente = caricaFinestre.getModificaCliente();
					modificaCliente.initialize(newVal);
				}
			});
			
			contextMenuClienti.getItems().get(2).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					listViewClienti.getItems().remove(listViewClienti.getSelectionModel().getSelectedIndex());
					newVal.eliminaCliente();
				}
			});
		});
	}
	
	private void visualizzazioneOrdini()
	{
		listViewOrdini.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Ordine> obs, Ordine oldVal, Ordine newVal) -> {
			
			if(newVal != null) textAreaOrdine.setText(newVal.getInformazioni());
			if(newVal != null && newVal.getGioiello() != null) textAreaGioielloOrdine.setText(newVal.getGioiello().stampaCaratteristiche());
			else textAreaGioielloOrdine.setText("Nessun Gioiello presente in quest'ordine");
			contextMenuOrdini.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) 
				{
					listViewOrdini.getItems().remove(listViewOrdini.getSelectionModel().getSelectedIndex());
					textAreaOrdine.clear();
					textAreaGioielloOrdine.clear();
					newVal.eliminaOrdine();
				}
			});
			
			contextMenuOrdini.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					try
					{	
						Fattura.emettiFattura(newVal);
						emettiFatturaController = caricaFinestre.getEmettiFattura();
						emettiFatturaController.initialize(Fattura.caricaFattura(newVal));
						Fattura fattura = Fattura.caricaFattura(newVal);
						listViewFatture.getItems().add(fattura);
					}
					catch(SQLException e)
					{
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("ATTENZIONE");
						alert.setHeaderText(null);
						alert.setContentText("ERRORE: Fattura già emessa per quest'ordine");
						alert.showAndWait();
					}
				}
			});
			
			contextMenuOrdini.getItems().get(2).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					modificaOrdine = caricaFinestre.getModificaOrdine();
					modificaOrdine.initialize(newVal);
				}
			});
		});
	}
	
	private void tabFatture()
	{
		listViewFatture.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Fattura> obs, Fattura oldVal, Fattura newVal) -> {
			
			textAreaFatture.setText(newVal.stampaFattura());
			
			contextMenuFatture.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					FileChooser percorsoSalvataggio = new FileChooser();
					percorsoSalvataggio.setTitle("Salva Fattura");
					percorsoSalvataggio.setInitialFileName("Fattura #"+newVal.getIdFattura()+".pdf");
					percorsoSalvataggio.getExtensionFilters().add(new ExtensionFilter("pdf file", ".pdf"));
					File file = percorsoSalvataggio.showSaveDialog(new Stage());
					if(file != null)
					{
						newVal.fatturaToFile(file.getPath());
					}
				}
			});
			
			contextMenuFatture.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) 
				{
					listViewFatture.getItems().remove(listViewFatture.getSelectionModel().getSelectedIndex());
					newVal.elimina();
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
					ControllerVisualizzatoreImmagini visualizzatore = caricaFinestre.getControllerVisualizzatoreImmagini();
					visualizzatore.setImmagini(listViewImmagini);
				}
			});
		});
	}
	
	private void carica()
	{
		System.out.println("sto caricando");
		
		ProgressBarController bar = caricaFinestre.getProgressBarController();
		Task<Object> task = traskCreator();
		Thread t = new Thread(task);
		t.start();
		bar.progressBar.progressProperty().unbind();
		bar.progressBar.progressProperty().bind(task.progressProperty());
		
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			
			@Override
			public void handle(WorkerStateEvent event) 
			{
				Stage s = (Stage) bar.progressBarPane.getScene().getWindow();
				s.close();
			}
		});
		//new Thread(task).start(); la stessa cosa che è scritta sopra
	}
	
	private Task<Object> traskCreator()
	{
		return new Task<Object>() {

			@Override
			protected Object call() throws Exception 
			{
				//updateProgress(0, 10);
				
				ArrayList<Gioiello> gi = Gioiello.caricamentoParzialeGioielli(); //Gioiello.caricaGioielli();
				ArrayList<Cliente> cli = Cliente.caricaClienti();
				ArrayList<Fattura> fat = Fattura.caricaFatture();
				
				
				int tot = gi.size() + cli.size() + fat.size(); 
				updateProgress(0, tot);
				
				for(int i = 0; i < gi.size(); i++)
				{
					Thread.sleep(3);
					listViewGioielli.getItems().add(gi.get(i));
					updateProgress(i, tot);
				}
				
				for(int i = 0; i < cli.size(); i++)
				{
					//Thread.sleep(5);
					listViewClienti.getItems().add(cli.get(i));
					updateProgress(gi.size()+i, tot);
				}
				
				for(int i = 0; i < fat.size(); i++)
				{
					//Thread.sleep(5);
					listViewFatture.getItems().add(fat.get(i));
					updateProgress(tot-fat.size()+i, tot);
				}
				
				return true;
			}
		};
	}
	
	private void apriFinestreModifica(Gioiello g)
	{
		if(g instanceof Anello)
		{
			modificaAnello = caricaFinestre.getModificaDatiAnello();
			modificaAnello.initialize((Anello)g);
			modificaAnello.addObserver(this);
		}
		if(g instanceof Bracciale)
		{
			modificaBracciale = caricaFinestre.getModificaDatiBracciale();
			modificaBracciale.initialize((Bracciale)g);
			modificaBracciale.addObserver(this);
		}
		if(g instanceof Collana)
		{
			modificaCollana = caricaFinestre.getModificaCollana();
			modificaCollana.initialize((Collana)g);
			modificaCollana.addObserver(this);
		}
		if(g instanceof Orecchino)
		{
			modificaOrecchino = caricaFinestre.getModificaOrecchino();
			modificaOrecchino.initialize((Orecchino)g);
			modificaOrecchino.addObserver(this);
		}
	}
}

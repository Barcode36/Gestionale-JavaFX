package eventi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
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
import models.Fattura;
import models.Gioiello;
import models.Immagine;
import models.Ordine;

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
	private ModificaDatiBraccialeController modificaBracciale;
	private ModificaDatiAnelloController modificaAnello;
	
	@FXML
    private TabPane tabPane;
	
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
    private MenuItem aggiungiBraccialeMenuItem;
	
	@FXML
    private MenuItem aggiungiAnelloMenuItem;

	
	private void setGioielliEClienti()
	{ 
//		GestioneQuery database = new GestioneQuery();
//		database.popola();
//		database.chiudiConnessione();
		
		barraDiCaricamento();
		listViewGioielli.getItems().addAll(Gioiello.caricaGioielli());
		listViewClienti.getItems().addAll(Cliente.caricaClienti());
		listViewFatture.getItems().addAll(Fattura.caricaFatture());
	}
	
	public void start()
	{
		setGioielliEClienti();
		contextMenuClienti = new ContextMenu();
		contextMenuGioielli = new ContextMenu();
		contextMenuOrdini = new ContextMenu();
		contextMenuFatture = new ContextMenu();
		contextMenuImmagini = new ContextMenu();
		contextMenuClienti.getItems().addAll(new MenuItem("Aggiungi Ordine"), new MenuItem("Elimina Cliente"));
		contextMenuGioielli.getItems().addAll(new MenuItem("Elimina Gioiello"), new MenuItem("Aggiungi Immagine"), new MenuItem("Modifica"));
		contextMenuOrdini.getItems().addAll(new MenuItem("Elimina Ordine"), new MenuItem("Emetti Fattura"));
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
	void aggiungiAnelloClicked(ActionEvent event) throws IOException 
	{
		Stage aggiungiGioielloStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiGioiello.fxml"));
		BorderPane aggiungiGioielloPane = (BorderPane) loader.load();
		Scene scene = new Scene(aggiungiGioielloPane,900,600);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		aggiungiGioielloStage.setResizable(false);
		aggiungiGioielloStage.setTitle("Aggiungi Anello");
		aggiungiGioielloStage.setScene(scene);
		aggiungiGioielloStage.show();
		controllerAnello = loader.getController();
		controllerAnello.initialize();
		controllerAnello.addObserver(this); 
	}
	
	@FXML
    void aggiungiBraccialeClicked(ActionEvent event) throws IOException 
	{
		Stage aggiungiGioielloStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiBracciale.fxml"));
		BorderPane aggiungiGioielloPane = (BorderPane) loader.load();
		Scene scene = new Scene(aggiungiGioielloPane,900,600);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		aggiungiGioielloStage.setResizable(false);
		aggiungiGioielloStage.setTitle("Aggiungi Bracciale");
		aggiungiGioielloStage.setScene(scene);
		aggiungiGioielloStage.show();
		//aggiungiGioielloStage.showAndWait();
		controllerBracciale = loader.getController();
		controllerBracciale.initialize();
		controllerBracciale.addObserver(this); 
    }
	
	@FXML
    void aggiungiCliente(ActionEvent event) throws IOException 
	{
		Stage aggiungiClienteStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiCliente.fxml"));
		BorderPane aggiungiClientePane = (BorderPane) loader.load();
		Scene scene = new Scene(aggiungiClientePane,900,600);
		aggiungiClienteStage.setResizable(false);
		aggiungiClienteStage.setTitle("Aggiungi Cliente");
		aggiungiClienteStage.setScene(scene);
		aggiungiClienteStage.show();
		controllerCliente = loader.getController();
		controllerCliente.addObserver(this);
    }
	
	@FXML
    void tabClientiSelezionato(ActionEvent event) 
	{
		System.out.println("TAB");
    }
	
	@FXML
    void tabClientiChiuso(ActionEvent event) 
	{
		System.out.println("chiuso");
    }
	
	@FXML
    void modificaButtonGioielliPressed(ActionEvent event) 
	{
		if(gioiello != null)
		{
			if(gioiello instanceof Anello)
			{
				modificaAnello = getModificaDatiAnello();
				modificaAnello.initialize((Anello)gioiello);
			}
			if(gioiello instanceof Bracciale)
			{
				modificaBracciale = getModificaDatiBracciale();
				modificaBracciale.initialize((Bracciale)gioiello);
			}
		}
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
	
	@Override
	public void update(Observable o, Object arg) 
	{ 
		if(arg.equals("Anello Creato")) //messaggio ricevuto dal controller Anello
		{
			listViewGioielli.getItems().add(controllerAnello.getGioiello());
		}
		
		if(arg.equals("Bracciale Creato")) //messaggio ricevuto dal controller bracciale
		{
			listViewGioielli.getItems().add(controllerBracciale.getGioiello());
		}
		
		if(arg.equals("Cliente creato")) //messaggio ricevuto dal controlle cliente
		{
			listViewClienti.getItems().add(cliente = controllerCliente.getCliente());
		}
		
		if(arg.equals("Ordine Creato"))
		{
			Ordine ordine = controllerOrdine.getOrdine();
			ordine.setIdCLiente(cliente.getId());
			cliente.aggiungiOrdine(controllerOrdine.getOrdine());
			listViewOrdini.getItems().add(controllerOrdine.getOrdine());
		}
		
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
	
	private AggiungiOrdineController getControllerOrdine()
	{
		Stage aggiungiClienteStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiOrdine.fxml"));
		BorderPane aggiungiClientePane = null;
		try 
		{
			aggiungiClientePane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(aggiungiClientePane,900,600);
		aggiungiClienteStage.setResizable(false);
		aggiungiClienteStage.setTitle("Aggiungi Cliente");
		aggiungiClienteStage.setScene(scene);
		aggiungiClienteStage.show();
		AggiungiOrdineController controller = loader.getController();
		controller.addObserver(this);
		return controller;
	}
	
	private ControllerVisualizzatoreImmagini getControllerVisualizzatoreImmagini()
	{
		Stage visualizzatoreImmagini = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("VisualizzatoreImmagini.fxml"));
		BorderPane visualizzatoreImmaginiPane = null;
		try 
		{
			visualizzatoreImmaginiPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(visualizzatoreImmaginiPane,900,600);
		visualizzatoreImmagini.setTitle("Aggiungi Cliente");
		visualizzatoreImmagini.setScene(scene);
		visualizzatoreImmagini.show();
		ControllerVisualizzatoreImmagini controller = loader.getController();
		return controller;
	}
	
	private ModificaDatiAnelloController getModificaDatiAnello()
	{
		Stage modificaDati = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaDatiAnello.fxml"));
		BorderPane modificadatiPane = null;
		try 
		{
			modificadatiPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(modificadatiPane,900,600);
		modificaDati.setTitle("Modifica Anello");
		modificaDati.setScene(scene);
		modificaDati.show();
		ModificaDatiAnelloController controller = loader.getController();
		controller.addObserver(this);
		return controller;
	}
	
	private ModificaDatiBraccialeController getModificaDatiBracciale()
	{
		Stage modificaDati = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaDatiBracciale.fxml"));
		BorderPane modificadatiPane = null;
		try 
		{
			modificadatiPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(modificadatiPane,900,600);
		modificaDati.setTitle("Modifica Bracciale");
		modificaDati.setScene(scene);
		modificaDati.show();
		ModificaDatiBraccialeController controller = loader.getController();
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
		listViewClienti.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Cliente> obs, Cliente oldVal, Cliente newVal) ->{
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
						controllerOrdine.initialize(Gioiello.caricaGioielli());
						cliente = newVal;
				}
			});
			
			contextMenuClienti.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {

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
		listViewOrdini.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Ordine> obs, Ordine oldVal, Ordine newVal)->{
			
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
		});
	}
	
	private void tabFatture()
	{
		listViewFatture.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Fattura> obs, Fattura oldVal, Fattura newVal)->{
			
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
		listViewImmagini.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends ImageView> obs, ImageView oldVal, ImageView newVal)->{
			
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
					ControllerVisualizzatoreImmagini visualizzatore = getControllerVisualizzatoreImmagini();
					//System.out.println(visualizzatore);
					visualizzatore.setImmagini(listViewImmagini);
				}
				
			});
		});
	}
	
	private void barraDiCaricamento()
	{
		 
	}
}

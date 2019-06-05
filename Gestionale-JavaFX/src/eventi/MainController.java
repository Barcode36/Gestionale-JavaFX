package eventi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import gestioneDB.GestioneQuery;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import models.Cliente;
import models.Fattura;
import models.Gioiello;
import models.Ordine;

public class MainController extends Observable implements Observer
{	
	//private Gioiello g;
	private Cliente cliente;
	private ContextMenu contextMenuClienti;
	private ContextMenu contextMenuGioielli;
	private ContextMenu contextMenuOrdini;
	private ContextMenu contextMenuFatture;
	private AggiungiAnelloController controllerAnello;
	private AggiungiClienteController controllerCliente;
	private AggiungiBraccialeController controllerBracciale;
	private AggiungiOrdineController controllerOrdine;
	
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
    private MenuItem aggiungiBraccialeMenuItem;
	
	@FXML
    private MenuItem aggiungiAnelloMenuItem;

	
	private void setGioielliEClienti()
	{ 
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
		contextMenuClienti.getItems().addAll(new MenuItem("Aggiungi Ordine"), new MenuItem("Elimina Cliente"));
		contextMenuGioielli.getItems().addAll(new MenuItem("Elimina Gioiello"), new MenuItem("AggiungiGioiello"));
		contextMenuOrdini.getItems().addAll(new MenuItem("Elimina Ordine"), new MenuItem("Emetti Fattura"));
		contextMenuFatture.getItems().addAll(new MenuItem("Stampa Fattura"),new MenuItem("Elimina Fattura"));
		listViewOrdini.setContextMenu(contextMenuOrdini);
		listViewGioielli.setContextMenu(contextMenuGioielli);
		listViewClienti.setContextMenu(contextMenuClienti);
		listViewFatture.setContextMenu(contextMenuFatture);
		listViewGioielli.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listViewClienti.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listViewOrdini.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		showInListView();
	}
	
	private void showInListView()
	{	
		
		listViewGioielli.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Gioiello> obs, Gioiello oldVal, Gioiello newVal) -> {
			textAreaGioielli.setText(newVal.stampaCaratteristiche());
			
			contextMenuGioielli.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					listViewGioielli.getItems().remove(listViewGioielli.getSelectionModel().getSelectedItem());
					newVal.eliminaGioiello();
				}
			});
			
			try 
			{
				listViewImmagini.getItems().clear();
				listViewImmagini.getItems().add(new ImageView(new Image(new FileInputStream("immagine.jpg"))));
				listViewImmagini.getItems().add(new ImageView(new Image(new FileInputStream("immagine.jpg"))));
			} 
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		});
		
		
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
						GestioneQuery database = new GestioneQuery();
						controllerOrdine.initialize(database.caricaGioielli());
						database.chiudiConnessione();
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
						//e.printStackTrace();
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("ATTENZIONE");
						alert.setHeaderText(null);
						alert.setContentText("ERRORE: Fattura già emessa per quest'ordine");
						alert.showAndWait();
					}
				}
				
			});
		});
		
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
					newVal.elimina();
				}
			});
		});
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
	}
	
	@FXML
	void menuItemSave(ActionEvent event) 
	{
		setChanged();
		notifyObservers("Salvato");
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
}

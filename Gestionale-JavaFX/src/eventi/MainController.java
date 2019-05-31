package eventi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import gestioneDB.GestioneQuery;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logica.GestioneOrdini;
import logica.Gioielleria;
import models.Cliente;
import models.Gioiello;
import models.Ordine;

public class MainController extends Observable implements Observer
{	
	private Gioiello g;
	private Cliente cliente;
	private ContextMenu contextMenu;
	private Ordine ordine;
	private AggiungiAnelloController controllerAnello;
	private AggiungiClienteController controllerCliente;
	private AggiungiBraccialeController controllerBracciale;
	private AggiungiOrdineController controllerOrdine;
	
	@FXML
    private TabPane tabPane;
	
	@FXML
    private Tab tabClienti;
	
	@FXML
	private TextArea textAreaGioielli;
	
	@FXML
    private TextArea textAreaClienti;
	
	@FXML
    private TextArea textAreaSpecificheOrdine;
	
	@FXML
	private MenuItem salvaMenuItem;
	 
	@FXML
	private ListView<Gioiello> listView;
	
	@FXML
	private ListView<ImageView> listViewImmagini;
	
	@FXML
	private ListView<Cliente> listViewClienti;
	
	@FXML
    private ListView<Ordine> listViewOrdini;
	
	@FXML
    private Button aggiungiClienteButton;
	
	@FXML
    private MenuItem aggiungiBraccialeMenuItem;
	
	@FXML
    private MenuItem aggiungiAnelloMenuItem;
	
	public Gioiello getGioiello() { return this.g; }
	
	public void aggiungiInListViewGioiello(Gioiello gioiello) { listView.getItems().add(gioiello); }
	public void aggiungiInListViewCliente(Cliente cliente) { listViewClienti.getItems().add(cliente); }
	
	public void setGioielliEClienti(Gioielleria gioielleria, GestioneOrdini ordini)
	{ 
		listView.getItems().addAll(gioielleria.getGioielli());
		listViewClienti.getItems().addAll(ordini.getClienti());
	}
	
	public void start()
	{
		contextMenu = new ContextMenu();
		contextMenu.getItems().addAll(new MenuItem("Aggiungi Ordine"), new MenuItem("Elimina Cliente"));
		listViewClienti.setContextMenu(contextMenu);
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listViewClienti.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listViewOrdini.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		showInListView();
	}
	
	private void showInListView()
	{	
		
		listView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Gioiello> obs, Gioiello oldVal, Gioiello newVal) -> {
			textAreaGioielli.setText(newVal.stampaCaratteristiche());
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
			textAreaClienti.setText(newVal.stampaCaratteristiche());
			
			ArrayList<Ordine> ordini = newVal.getOrdini();
			if(ordini.size() > 0) listViewOrdini.getItems().addAll(ordini);
			else listViewOrdini.getItems().clear();
			
			
			contextMenu.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {

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
			
			contextMenu.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					listViewClienti.getItems().remove(listViewClienti.getSelectionModel().getSelectedIndex());
					newVal.eliminaCliente();
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
		if(arg.equals("Anello Creato"))
		{
			g = controllerAnello.getGioiello();
			setChanged();
			notifyObservers("Anello Creato");
		}
		
		if(arg.equals("Bracciale Creato"))
		{
			g = controllerBracciale.getGioiello();
			setChanged();
			notifyObservers("Bracciale Creato");
		}
		
		if(arg.equals("Cliente creato"))
		{
			cliente = controllerCliente.getCliente();
			setChanged();
			notifyObservers("Cliente creato");
		}
		
		if(arg.equals("Ordine Creato"))
		{
			cliente.aggiungiOrdine(controllerOrdine.getOrdine());
		}
	}
	
	public Cliente getCliente() { return this.cliente; }
	public Ordine getOrdine() {return this.ordine;}
	
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

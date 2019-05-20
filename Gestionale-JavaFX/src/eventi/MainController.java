package eventi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import logica.Cliente;
import logica.Gioielleria;
import logica.Gioiello;

public class MainController extends Observable implements Observer
{	
	private Gioiello g;
	private Cliente cliente;
	private AggiungiAnelloController controllerAnello;
	private AggiungiClienteController controllerCliente;
	private AggiungiBraccialeController controllerBracciale;
	
	@FXML
    private TabPane tabPane;
	
	@FXML
    private Tab tabClienti;
	
	@FXML
	private TextArea textAreaGioielli;
	
	@FXML
	private MenuItem salvaMenuItem;
	 
	@FXML
	private ListView<Gioiello> listView;
	
	@FXML
	private ListView<ImageView> listViewImmagini;
	
	@FXML
    private Button aggiungiClienteButton;
	
	@FXML
    private MenuItem aggiungiBraccialeMenuItem;
	
	@FXML
    private MenuItem aggiungiAnelloMenuItem;
	
	public Gioiello getGioiello() { return this.g; }
	
	public void aggiungiInListView(Gioiello gioiello) { listView.getItems().add(gioiello); }
	
	public void setGioielli(Gioielleria gioielleria)
	{ 
		for(Gioiello g : gioielleria.getGioielli())
		{
			listView.getItems().add(g);
		}
	}
	
	public Tab getTab() { return tabClienti; }
	
	public void showInListView()
	{	
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
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
	}
	
	public Cliente getCliente() { return this.cliente; }
	
	@FXML
	void menuItemSave(ActionEvent event) 
	{
		setChanged();
		notifyObservers("Salvato");
	}
}

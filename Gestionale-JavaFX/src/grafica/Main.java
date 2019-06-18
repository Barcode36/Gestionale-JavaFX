package grafica;

import java.util.Optional;

import eventi.MainController;
import gestioneDB.InizializzazioneDatabase;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application
{
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ControllerPrincipale.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root,1366,768);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setMinHeight(800);
			primaryStage.setMinWidth(1000);
			primaryStage.setTitle("Gestionale");
			primaryStage.setScene(scene);
			//primaryStage.setAlwaysOnTop(true);
			primaryStage.show();
			MainController controller = loader.getController();
			controller.start();
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) 
				{	
					ButtonType si = new ButtonType("Si");
					ButtonType no = new ButtonType("No");
					ButtonType annulla = new ButtonType("Annulla");
					Alert alert = finestraSalvataggio();
					alert.getButtonTypes().clear();
					alert.getButtonTypes().addAll(si,no,annulla);
					Optional<ButtonType> result = alert.showAndWait();
					if(result.get() == si)
					{
						//t.interrupt();
					} 
					else if(result.get() == no)
					{
						//chiedi:
						//chiedere al prof
					}
					else if(result.get() == annulla)
					{
						//goto chiedi
					}
				}
			});
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		InizializzazioneDatabase data = new InizializzazioneDatabase();
		if(data.controllaPrimoAvvio())
		{
			finestraPrimoAvvio(data);
		}
		data.chiudiConnessione();
		launch(args);
	}
	
	public Alert finestraSalvataggio()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Attenzione");
		alert.setHeaderText(null);
		alert.setContentText("Salvare i dati prima di uscire?");
		return alert;
	}
	
	public static void finestraPrimoAvvio(InizializzazioneDatabase data)
	{
		Platform.runLater(new Runnable() {
			@Override
			public void run() 
			{	
				ButtonType ok = new ButtonType("Ok");
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.getButtonTypes().clear();
				alert.getButtonTypes().add(ok);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.setContentText("Questa è la prima volta che avvii il programma,\n"
						+ "verranno quindi creati tutti i file di configurazione\n"
						+ "per il corretto funzionamento.");
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == ok)
				{
					data.scriviFile();
				}
			}
		});
	}
}


//caratteristiche pietre
//peso
//caratura
//diametro

//aggiungere delle stringhe che riescono a descrivere le caratteristiche di un oggetto
//se un prodotto è da troppo tempo nel magazzino viene deprezzato per farlo vendere

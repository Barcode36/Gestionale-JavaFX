package grafica;

import java.util.Optional;

import eventi.MainController;
import javafx.application.Application;
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
}

//caratteristiche pietre
//peso
//caratura
//diametro

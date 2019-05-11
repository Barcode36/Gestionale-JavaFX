package grafica;

import logica.Gioielleria;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			Gioielleria gioielleria = new Gioielleria("Francesco");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaRoot.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root,1920,1080);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setMinHeight(800);
			primaryStage.setMinWidth(1000);
			primaryStage.setTitle(gioielleria.getNomeGioielleria());
			primaryStage.setScene(scene);
			primaryStage.show();
			//MainController controller = loader.getController();
			GestioneInterfaccia gestioneInterfaccia = new GestioneInterfaccia();
			gestioneInterfaccia.start(loader.getController(), gioielleria);
			
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
}

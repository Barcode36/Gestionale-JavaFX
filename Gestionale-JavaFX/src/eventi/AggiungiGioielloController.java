package eventi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AggiungiGioielloController extends Application
{
	@FXML
    private TextField textFieldPeso;
	
	@FXML
	private TextField textFieldNomeGioiello;
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		try 
		{	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiGioiello.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root,900,600);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setMinHeight(600);
			primaryStage.setMinWidth(900);
			primaryStage.setTitle("Aggiungi Gioiello");
			primaryStage.setScene(scene);
			primaryStage.show();
			//MainController controller = loader.getController();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@FXML
    void submit(ActionEvent event) 
	{
		System.out.println(textFieldNomeGioiello.getText());
		System.out.println(textFieldPeso.getText());
    }
	
}

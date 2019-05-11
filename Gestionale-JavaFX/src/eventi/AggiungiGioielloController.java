package eventi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logica.Anello;
import logica.Gioiello;
import logica.MATERIALE;

public class AggiungiGioielloController extends Application
{
	private Gioiello gioiello;
	
	@FXML
    private TextField textFieldPeso;
	
	@FXML
	private TextField textFieldNomeGioiello;
	
	@FXML
    private TextField textFieldTipo;
	
	@FXML
	private Button submitButton;
	
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
			primaryStage.setResizable(false);
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
	
	public Gioiello getGioiello() { return this.gioiello; }
	
	@FXML
    void submit(ActionEvent event) //quando il tasto ok è premuto
    {
		Gioiello g = creaGioiello();
    }
	
	Gioiello creaGioiello()
	{
		if(textFieldTipo.getText().equals("Anello"))
		{
			gioiello = new Anello("012",15.2,Double.parseDouble(textFieldPeso.getText()),MATERIALE.ACCIAIO,"Maschile",15,true,textFieldNomeGioiello.getText());
			return gioiello;
		}
		return  null;
	}
}

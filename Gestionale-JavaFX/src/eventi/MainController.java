package eventi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logica.*;

public class MainController implements Runnable
{
	//private Gioielleria gioielleria;
	private AggiungiGioielloController controller = null;
	
	@FXML
	private TextArea textAreaGioielli;
	
	@FXML
    private Tab tabClienti;
	
	@FXML
    private TextArea textAreaClienti;
	 
	@FXML
	private ListView<Gioiello> listView;
	
	@FXML
	private ImageView imageViewer1;
	
	@FXML
    private ImageView imageViewer2;
	
	@FXML
    private Button nuovoGioielloButton;
	
	@FXML
    private Button submitButton;

    @FXML
    private TextField textFieldNomeGioiello;

    @FXML
    private TextField textFieldPeso;

    @FXML
    private TextField textFieldTipo;
	
	public void setGioielli(Gioielleria gioielleria)
	{ 
		//this.gioielleria = gioielleria; 
		for(Gioiello g : gioielleria.getGioielli())
		{
			listView.getItems().add(g);
		}
	}
	
	public void setClienti(ArrayList<Cliente> clienti)
	{
		
	}
	
	//public void setAggiungiGioielloController(AggiungiGioielloController controller) {this.controller = controller; }
	
	public void showInListView()
	{	 
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Gioiello> obs, Gioiello oldVal, Gioiello newVal) -> {
			textAreaGioielli.setText(newVal.getNomeGioiello());
			try 
			{
				imageViewer1.setImage(new Image(new FileInputStream("immagine.jpg")));
				imageViewer2.setImage(new Image(new FileInputStream("immagine.jpg")));
			} 
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		});
	}
	
	@FXML
    void aggiungiGioiello(ActionEvent event) throws IOException //bottone aggiungi gioiello cliccato
	{
		try 
		{
			controller = new AggiungiGioielloController();
			controller.start(new Stage());
			
			while(controller.getGioiello() == null) System.out.println("gioiello nunllo");
			//listView.getItems().add(controller.submit(new Event()));
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
		}
		
		
		//listView.getItems().add(controller.getGioiello());
    }
	
	@FXML
    void tabClientiSelezionato(ActionEvent event) 
	{
		listView.getItems().clear();
    }

	@Override
	public void run() 
	{
		while(true)
		{
			if(controller != null)
			{
				if(controller.getGioiello() != null)
				{
					listView.getItems().add(controller.getGioiello());
					controller = null;
				}
			}
		}
	}
}

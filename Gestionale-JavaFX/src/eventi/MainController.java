package eventi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logica.*;

public class MainController
{
	private Gioielleria gioielleria;
	
	@FXML
	private TextArea textAreaGioielli;
	 
	@FXML
	private ListView<Gioiello> listView;
	
	@FXML
	private ImageView imageViewer1;
	
	@FXML
    private ImageView imageViewer2;
	
	@FXML
    private Button nuovoGioielloButton;
	 
	public void setGioielli(Gioielleria gioielleria) 
	{ 
		this.gioielleria = gioielleria; 
		for(Gioiello g : this.gioielleria.getGioielli())
		{
			listView.getItems().add(g);
		}
	}
	
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
				System.out.println("impossibile caricare l'immagine");
				e.printStackTrace();
			}
		});
	}
	
	@FXML
    void aggiungiGioiello(ActionEvent event) //bottone aggiungi gioiello cliccato
	{
		AggiungiGioielloController controller = new AggiungiGioielloController();
		try 
		{
			controller.start(new Stage());
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

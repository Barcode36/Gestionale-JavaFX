package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import eventi.CaricaFinestre;
import gestioneDB.GestioneQuery;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Anello;
import models.Bracciale;
import models.Collana;
import models.Gioiello;
import models.Immagine;
import models.Orecchino;

public class VisualizzaTuttoController extends Observable implements Observer
{
	private ModificaDatiAnelloController modificaAnello;
	private ModificaOrecchinoController modificaOrecchino;
	private ModificaDatiBraccialeController modificaBracciale;
	private ModificaDatiCollanaController modificaCollana;
	private ContextMenu contextMenuGioielli;
	private ContextMenu contextMenuImmagini;
	private CaricaFinestre caricaFinestre;
	private Gioiello gioiello;
	private int indice;
	private int numeroAnelli = 50;
	private int numeroBracciali = 50;
	private int numeroCollane = 50;
	private int numeroOrecchini = 50;
	
	@FXML
	private JFXComboBox<String> tipologiaGioielloComboBox;
	
	@FXML
    private ProgressBar progressBar;

	@FXML
	private JFXTextArea textAreaGioielli;

	@FXML
	private JFXButton modificaButton;
	
	@FXML
	private JFXButton visualizzaButton;

	@FXML
	private ListView<ImageView> listViewImmagini;

	@FXML
	private JFXButton visualizzaAncoraButton;

	@FXML
	private JFXButton elimina;

	@FXML
	private JFXListView<Gioiello> listViewGioielli;

	public void start()
	{
		progressBar.setVisible(false);
		caricaFinestre = new CaricaFinestre();
		contextMenuImmagini = new ContextMenu();
		contextMenuImmagini.getItems().addAll(new MenuItem("Elimina Immagine"), new MenuItem("Visualizza"));
		listViewImmagini.setContextMenu(contextMenuImmagini);
		
		contextMenuGioielli = new ContextMenu();
		contextMenuGioielli.getItems().addAll(new MenuItem("Elimina Gioiello"), new MenuItem("Aggiungi Immagine"), new MenuItem("Modifica"), new MenuItem("Aggiungi ad Ordine"));
		listViewGioielli.setContextMenu(contextMenuGioielli);
		ObservableList<String> opzioni = FXCollections.observableArrayList("Anello","Bracciale","Orecchini","Collane");
		tipologiaGioielloComboBox.setItems(opzioni);
		
		tabGioielli();
		visualizzazioneImmagini();
	}
	
	private void tabGioielli()
	{
		listViewGioielli.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Gioiello> obs, Gioiello oldVal, Gioiello newVal) -> {
			
			try
			{
				listViewImmagini.getItems().clear();
				textAreaGioielli.setText(newVal.stampaCaratteristiche());
				listViewImmagini.getItems().addAll(caricaImmagini(newVal.caricaImmagini()));
				gioiello = newVal; //per controllare il tipo di gioiello quando si premono i bottoni modifica ed elimina
				indice = listViewGioielli.getSelectionModel().getSelectedIndex();
			}
			catch(NullPointerException e)
			{
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.getButtonTypes().clear();
	    		alert.getButtonTypes().add(new ButtonType("Chiudi") );
	    		alert.setTitle(null);
	    		alert.setHeaderText(null);
	    		alert.setContentText("Ora non è presente nessun prodotto");
	    		alert.showAndWait();
			}
			
			contextMenuGioielli.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					listViewGioielli.getItems().remove(listViewGioielli.getSelectionModel().getSelectedItem());
					setChanged();
	        		notifyObservers("Gioiello eliminato");
					newVal.eliminaGioiello();
				}
			});
			
			contextMenuGioielli.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					FileChooser percorsoSalvataggio = new FileChooser();
					percorsoSalvataggio.setTitle("Carica Immagine");
					//percorsoSalvataggio.getExtensionFilters().add();	
					List<File> immagini = percorsoSalvataggio.showOpenMultipleDialog(new Stage());
					if(immagini != null)
					{
						for(File f : immagini)
						{
							try 
							{
								newVal.aggiungiImmagine(new FileInputStream(f));
								ImageView im = new ImageView(new Immagine(new FileInputStream(f)));
								im.setFitHeight(300);
								im.setFitWidth(300);
								listViewImmagini.getItems().add(im);
							} 
							catch (FileNotFoundException e) 
							{
								e.printStackTrace();
							}
						}
					}
				}
			});
			
			contextMenuGioielli.getItems().get(2).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					apriFinestreModifica(newVal);
				}
			});
			
			contextMenuGioielli.getItems().get(3).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					gioiello = newVal;
					setChanged();
					notifyObservers("Gioiello selezionato visualizza");
				}
			});
		});
	}
	
	private void visualizzazioneImmagini()
	{
		listViewImmagini.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends ImageView> obs, ImageView oldVal, ImageView newVal) -> {
			
			contextMenuImmagini.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					listViewImmagini.getItems().remove(listViewImmagini.getSelectionModel().getSelectedIndex());
					Immagine i = (Immagine) newVal.getImage();
					i.eliminaImmagine();
				}
			});
			
			contextMenuImmagini.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) 
				{
					ControllerVisualizzatoreImmagini visualizzatore = caricaFinestre.getControllerVisualizzatoreImmagini();
					visualizzatore.setImmagini(listViewImmagini);
				}
			});
		});
	}
	
	@FXML
    void visualizzaButtonPressed(ActionEvent event) 
	{
		listViewGioielli.getItems().clear();
		
		String tipologia = "";
		String query = "";
		if(tipologiaGioielloComboBox.getValue().equals("Collane"))
		{
			query = "select * from prodotto inner join collana on collana.idProdotto = prodotto.idProdotto limit 50";
			tipologia = GestioneQuery.collana;
		}
		if(tipologiaGioielloComboBox.getValue().equals("Anello")) 
		{
			query = "select * from prodotto inner join anello on anello.idProdotto = prodotto.idProdotto limit 50";
			tipologia = GestioneQuery.anello;
		}
		if(tipologiaGioielloComboBox.getValue().equals("Bracciale")) 
		{
			query = "select * from prodotto inner join bracciale on bracciale.idProdotto = prodotto.IdProdotto limit 50";
			tipologia = GestioneQuery.bracciale;
		}
		if(tipologiaGioielloComboBox.getValue().equals("Orecchini")) 
		{
			query = "select * from prodotto inner join orecchino on orecchino.idProdotto = prodotto.idProdotto limit 50";
			tipologia = GestioneQuery.orecchino;
		}
		
		Task<Object> task = taskCreator(Gioiello.caricaGioielli(query, tipologia));
		Thread t = new Thread(task);
		t.start();
		progressBar.setVisible(true);
		progressBar.progressProperty().unbind();
		progressBar.progressProperty().bind(task.progressProperty());
		
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent event) 
			{
				progressBar.setVisible(false);
			}
			
		});
    }
	
	@FXML
	void visualizzaAncoraPressed(ActionEvent event) 
	{
		String tipologia = "";
		String query = "";
		if(tipologiaGioielloComboBox.getValue().equals("Collane"))
		{
			query = "select * from prodotto inner join collana on collana.idProdotto = prodotto.idProdotto limit 50 offset "+numeroCollane;
			numeroCollane+=50;
			tipologia = GestioneQuery.collana;
		}
		if(tipologiaGioielloComboBox.getValue().equals("Anello")) 
		{
			query = "select * from prodotto inner join anello on anello.idProdotto = prodotto.idProdotto limit 50 offset "+numeroAnelli;
			numeroAnelli+=50;
			tipologia = GestioneQuery.anello;
		}
		if(tipologiaGioielloComboBox.getValue().equals("Bracciale")) 
		{
			query = "select * from prodotto inner join bracciale on bracciale.idProdotto = prodotto.IdProdotto limit 50 offset "+numeroBracciali;
			numeroBracciali+=50;
			tipologia = GestioneQuery.bracciale;
		}
		if(tipologiaGioielloComboBox.getValue().equals("Orecchini")) 
		{
			query = "select * from prodotto inner join orecchino on orecchino.idProdotto = prodotto.idProdotto limit 50 offset "+numeroOrecchini;
			numeroOrecchini+=50;
			tipologia = GestioneQuery.orecchino;
		}
		
		ArrayList<Gioiello> gioielli = Gioiello.caricaGioielli(query, tipologia);
		if(gioielli.size() > 0)
		{
			Task<Object> task = taskCreator(gioielli);
			Thread t = new Thread(task);
			t.start();
			progressBar.setVisible(true);
			progressBar.progressProperty().unbind();
			progressBar.progressProperty().bind(task.progressProperty());
			
			task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

				@Override
				public void handle(WorkerStateEvent event) 
				{
					progressBar.setVisible(false);					
				}
			});
		}
		else
		{
			ButtonType si = new ButtonType("Ok");
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.getButtonTypes().clear();
    		alert.getButtonTypes().addAll(si);
    		alert.setTitle("ATTENZIONE");
    		alert.setHeaderText(null);
    		alert.setContentText("Non c'è più nulla da mostrare");
    		//Optional<ButtonType> result = alert.showAndWait();
    		alert.showAndWait();
		}
	}
	
	private Task<Object> taskCreator(ArrayList<Gioiello> gioielli)
	{
		return new Task<Object>() {

			@Override
			protected Object call() throws Exception 
			{
				updateProgress(0, gioielli.size());
				for(int i = 0; i < gioielli.size(); i++)
				{
					Thread.sleep(3);
					listViewGioielli.getItems().add(gioielli.get(i));
					updateProgress(i, gioielli.size());
				}
				
				return true;
			}
		};
	}
	
	private ArrayList<ImageView> caricaImmagini(ArrayList<Immagine> immagini)
	{
		ArrayList<ImageView> imageViewImmagini = new ArrayList<ImageView>();
		for(Immagine i : immagini)
		{
			ImageView im = new ImageView(i);
			im.setFitHeight(300);
			im.setFitWidth(300);
			imageViewImmagini.add(im);
		}
		
		return imageViewImmagini;
	}
	
	public Gioiello getGioiello() { return this.gioiello; }
	
	private void apriFinestreModifica(Gioiello g)
	{
		if(g instanceof Anello)
		{
			modificaAnello = caricaFinestre.getModificaDatiAnello();
			modificaAnello.initialize((Anello)g);
			modificaAnello.addObserver(this);
		}
		if(g instanceof Bracciale)
		{
			modificaBracciale = caricaFinestre.getModificaDatiBracciale();
			modificaBracciale.initialize((Bracciale)g);
			modificaBracciale.addObserver(this);
		}
		if(g instanceof Collana)
		{
			modificaCollana = caricaFinestre.getModificaCollana();
			modificaCollana.initialize((Collana)g);
			modificaCollana.addObserver(this);
		}
		if(g instanceof Orecchino)
		{
			modificaOrecchino = caricaFinestre.getModificaOrecchino();
			modificaOrecchino.initialize((Orecchino)g);
			modificaOrecchino.addObserver(this);
		}
	}
	
	@FXML
	void modificaButtonPressed(ActionEvent event) 
	{
		apriFinestreModifica(gioiello);
	}

	@FXML
	void eliminaButtonPressed(ActionEvent event) 
	{
		if(gioiello != null)
    	{
    		ButtonType si = new ButtonType("Si");
    		ButtonType annulla = new ButtonType("Annulla");
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.getButtonTypes().clear();
    		alert.getButtonTypes().addAll(si,annulla);
    		alert.setTitle("ATTENZIONE");
    		alert.setHeaderText(null);
    		alert.setContentText("Vuoi davvero eliminare quest'oggetto?");
    		Optional<ButtonType> result = alert.showAndWait();
    		if(result.get().equals(si))
    		{
    			listViewGioielli.getItems().remove(indice);
    			textAreaGioielli.clear();
        		gioiello.eliminaGioiello();
        		setChanged();
        		notifyObservers("Gioiello eliminato");
        		gioiello = null;
    		}
    		else if(result.get() == annulla);
    	}
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg.equals("modificato bracciale")) textAreaGioielli.setText(modificaBracciale.getGioiello().stampaCaratteristiche());
		if(arg.equals("anello modificato")) textAreaGioielli.setText(modificaAnello.getAnello().stampaCaratteristiche());
		if(arg.equals("Collana modificata")) textAreaGioielli.setText(modificaCollana.getGioiello().stampaCaratteristiche());
		if(arg.equals("Orecchino modificato")) textAreaGioielli.setText(modificaOrecchino.getGioiello().stampaCaratteristiche());
	}
}

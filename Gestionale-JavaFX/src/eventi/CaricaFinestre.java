package eventi;

import java.io.IOException;

import controller.AggiungiAnelloController;
import controller.AggiungiBraccialeController;
import controller.AggiungiClienteController;
import controller.AggiungiCollanaController;
import controller.AggiungiOrdineController;
import controller.AggiungiOrecchinoController;
import controller.ControllerEmettiFattura;
import controller.ControllerVisualizzatoreImmagini;
import controller.ModificaDatiAnelloController;
import controller.ModificaDatiBraccialeController;
import controller.ModificaDatiClienteController;
import controller.ModificaDatiCollanaController;
import controller.ModificaOrdineController;
import controller.ModificaOrecchinoController;
import controller.ProgressBarController;
import controller.SearchController;
import controller.VisualizzaTuttoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CaricaFinestre 
{
	public AggiungiOrdineController getControllerOrdine()
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
		return loader.getController();
	}
	
	public ControllerVisualizzatoreImmagini getControllerVisualizzatoreImmagini()
	{
		Stage visualizzatoreImmagini = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("VisualizzatoreImmagini.fxml"));
		BorderPane visualizzatoreImmaginiPane = null;
		try 
		{
			visualizzatoreImmaginiPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(visualizzatoreImmaginiPane,900,600);
		visualizzatoreImmagini.setTitle("Aggiungi Cliente");
		visualizzatoreImmagini.setScene(scene);
		visualizzatoreImmagini.show();
		return loader.getController();
	}
	
	public ModificaDatiAnelloController getModificaDatiAnello()
	{
		Stage modificaDati = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaDatiAnello.fxml"));
		BorderPane modificadatiPane = null;
		try 
		{
			modificadatiPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(modificadatiPane,900,600);
		modificaDati.setTitle("Modifica Anello");
		modificaDati.setScene(scene);
		modificaDati.show();
		//System.out.println(loader.getController().toString());
		return loader.getController();
	}
	
	public  ModificaDatiBraccialeController getModificaDatiBracciale()
	{
		Stage modificaDati = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaDatiBracciale.fxml"));
		BorderPane modificadatiPane = null;
		try 
		{
			modificadatiPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(modificadatiPane,900,600);
		modificaDati.setTitle("Modifica Bracciale");
		modificaDati.setScene(scene);
		modificaDati.show();
		return loader.getController();
	}
	
	public ProgressBarController getProgressBarController()
	{
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ProgressBar.fxml"));
		BorderPane progressBarPane = null;
		try 
		{
			progressBarPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(progressBarPane,400,100);
		stage.setResizable(false);
		stage.setTitle("Caricamento");
		stage.setScene(scene);
		//stage.initStyle(StageStyle.UNDECORATED);
		stage.setAlwaysOnTop(true);
		stage.show();
		return loader.getController();
	}
	
	public AggiungiAnelloController getAnelloController()
	{
		Stage aggiungiGioielloStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiGioiello.fxml"));
		BorderPane aggiungiGioielloPane = null;
		try 
		{
			aggiungiGioielloPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(aggiungiGioielloPane,900,600);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		aggiungiGioielloStage.setResizable(false);
		aggiungiGioielloStage.setTitle("Aggiungi Anello");
		aggiungiGioielloStage.setScene(scene);
		aggiungiGioielloStage.show();
		return loader.getController();
	}
	
	public AggiungiBraccialeController getBraccialeController()
	{
		Stage aggiungiGioielloStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiBracciale.fxml"));
		BorderPane aggiungiGioielloPane = null;
		try
		{
			aggiungiGioielloPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(aggiungiGioielloPane,900,600);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		aggiungiGioielloStage.setResizable(false);
		aggiungiGioielloStage.setTitle("Aggiungi Bracciale");
		aggiungiGioielloStage.setScene(scene);
		aggiungiGioielloStage.show();
		return loader.getController();
	}
	
	public AggiungiClienteController getClienteController()
	{
		Stage aggiungiClienteStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacciaAggiungiCliente.fxml"));
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
		return loader.getController();
	}
	
	public SearchController getSearchController()
	{
		Stage search = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
		BorderPane searchPane = null;
		try 
		{
			searchPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(searchPane,900,600);
		search.setTitle("Cerca");
		search.setScene(scene);
		search.show();
		return loader.getController();
	}
	
	public AggiungiOrecchinoController getOrecchinoController()
	{
		Stage search = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiOrecchino.fxml"));
		BorderPane orecchinoPane = null;
		try 
		{
			orecchinoPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(orecchinoPane,900,600);
		search.setTitle("Aggiungi Orecchino");
		search.setScene(scene);
		search.show();
		return loader.getController();
	}
	
	public AggiungiCollanaController getCollanaController()
	{
		Stage search = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiCollana.fxml"));
		BorderPane collanaPane = null;
		try 
		{
			collanaPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(collanaPane,900,600);
		search.setTitle("Aggiungi collana");
		search.setScene(scene);
		search.show();
		return loader.getController();
	}
	
	public ModificaDatiCollanaController getModificaCollana()
	{
		Stage search = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaDatiCollana.fxml"));
		BorderPane collanaPane = null;
		try 
		{
			collanaPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(collanaPane,900,600);
		search.setTitle("Modifica collana");
		search.setScene(scene);
		search.show();
		return loader.getController();
	}
	
	public ModificaOrecchinoController getModificaOrecchino()
	{
		Stage search = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaOrecchino.fxml"));
		BorderPane collanaPane = null;
		try 
		{
			collanaPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(collanaPane,900,600);
		search.setTitle("Modifica orecchino");
		search.setScene(scene);
		search.show();
		return loader.getController();
	}
	
	public VisualizzaTuttoController getVisualizzaTutto()
	{

		Stage search = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("VisualizzaTutto.fxml"));
		BorderPane visualizzaPane = null;
		try 
		{
			visualizzaPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(visualizzaPane,1366,768);
		search.setTitle("Visualizza Tutto");
		search.setScene(scene);
		search.show();
		return loader.getController();
	}
	
	public ModificaDatiClienteController getModificaCliente()
	{
		Stage search = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaDatiCliente.fxml"));
		BorderPane visualizzaPane = null;
		try 
		{
			visualizzaPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(visualizzaPane,900,600);
		search.setTitle("Modifica Cliente");
		search.setScene(scene);
		search.show();
		return loader.getController();
	}
	
	public ModificaOrdineController getModificaOrdine()
	{
		Stage search = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaOrdine.fxml"));
		BorderPane visualizzaPane = null;
		try 
		{
			visualizzaPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(visualizzaPane,900,600);
		search.setTitle("Modifica Ordine");
		search.setScene(scene);
		search.show();
		return loader.getController();
	}
	
	public ControllerEmettiFattura getEmettiFattura()
	{
		Stage search = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EmettiFattura.fxml"));
		BorderPane visualizzaPane = null;
		try 
		{
			visualizzaPane = (BorderPane) loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Scene scene = new Scene(visualizzaPane,900,800);
		search.setTitle("VisualizzaFattura");
		search.setScene(scene);
		search.show();
		return loader.getController();
	}
}

package eventi;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
}

package grafica;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import eventi.MainController;
import logica.GestioneOrdini;
import logica.Gioielleria;

public class GestioneInterfaccia implements Observer
{
	private MainController controller;
	private Gioielleria gioielleria;
	private GestioneOrdini gestioneOrdini;
	
	public void inizio() throws IOException
	{
		controller.start();
		controller.addObserver(this);
		//System.out.println(gioielleria.getGioiello(3));
	}
	
	public GestioneInterfaccia(MainController controller, Gioielleria gioielleria)
	{
		this.controller = controller;
		this.gioielleria = gioielleria;
		this.gestioneOrdini = new GestioneOrdini();
		this.gioielleria.caricaGioielli();
		this.gestioneOrdini.caricaClienti();
		this.controller.setGioielliEClienti(this.gioielleria, this.gestioneOrdini);
		
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg.equals("Anello Creato"))
		{
			gioielleria.aggiungiGioiello(controller.getGioiello());
			controller.aggiungiInListViewGioiello(controller.getGioiello());
		}
		
		if(arg.equals("Bracciale Creato"))
		{
			gioielleria.aggiungiGioiello(controller.getGioiello());
			controller.aggiungiInListViewGioiello(controller.getGioiello());
		}
		
		if(arg.equals("Salvato"))
		{
			gioielleria.salvaGioielli();
			gestioneOrdini.salvaClienti();
		}
			
		
		if(arg.equals("Cliente creato"))
		{
			gestioneOrdini.aggiungiCliente(controller.getCliente());
			controller.aggiungiInListViewCliente(controller.getCliente());
			System.out.println("cliente aggiunto");
		}
	}
}

package grafica;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import eventi.MainController;
import logica.GestioneOrdini;
import logica.Gioielleria;
import logica.Gioiello;

public class GestioneInterfaccia implements Observer
{
	private MainController controller;
	private Gioielleria gioielleria;
	private GestioneOrdini gestioneOrdini;
	
	public void inizio() throws IOException
	{
		controller.showInListView();
		controller.addObserver(this);
		//System.out.println(gioielleria.getGioiello(3));
	}
	
	public GestioneInterfaccia(MainController controller, Gioielleria gioielleria)
	{
		this.controller = controller;
		this.gioielleria = gioielleria;
		this.gioielleria.caricaGioielli();
		this.controller.setGioielli(this.gioielleria);
		gestioneOrdini = new GestioneOrdini();
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg.equals("Gioiello Creato"))
		{
			gioielleria.aggiungiGioiello(controller.getGioiello());
			controller.aggiungiInListView(controller.getGioiello());
		}
		
		if(arg.equals("Salvato")) gioielleria.salvaGioielli();
		
		if(arg.equals("Cliente creato"))
		{
			gestioneOrdini.aggiungiCliente(controller.getCliente());
			System.out.println("cliente aggiunto");
		}
		
	}
}

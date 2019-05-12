package grafica;

import eventi.MainController;
import logica.GestioneOrdini;
import logica.Gioielleria;

public class GestioneInterfaccia //implements Runnable
{
	private MainController controller;
	private Gioielleria gioielleria;
	private GestioneOrdini ordini;
	public void inizio()
	{
		//controller.showInListView();
		Thread t = new Thread(controller);
		t.start();
	}
	
	public GestioneInterfaccia(MainController controller, Gioielleria gioielleria) 
	{
		this.controller = controller;
		this.gioielleria = gioielleria;
		this.gioielleria.caricaGioielli();
		this.controller.setGioielli(this.gioielleria);
		this.ordini = new GestioneOrdini();
	}
	
//	@Override
//	public void run() 
//	{
//		while(true)
//		{
//			inizio();
//		}
//		
//	}
}

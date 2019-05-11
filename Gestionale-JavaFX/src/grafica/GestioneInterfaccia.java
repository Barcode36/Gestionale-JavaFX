package grafica;

import eventi.MainController;
import logica.Gioielleria;

public class GestioneInterfaccia //implements Runnable
{
	public void inizio(MainController controller, Gioielleria gioielleria)
	{
		//AggiungiGioielloController aggiungiGioielloController = new AggiungiGioielloController();
		gioielleria.caricaGioielli();
		controller.setGioielli(gioielleria);
		//controller.setAggiungiGioielloController(aggiungiGioielloController);
		controller.showInListView();
		//controller.addGioiello(aggiungiGioielloController.getGioiello());
		
	}
}

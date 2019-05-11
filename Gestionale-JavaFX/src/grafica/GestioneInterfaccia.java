package grafica;

import eventi.MainController;
import logica.Gioielleria;

public class GestioneInterfaccia
{
	public void start(MainController controller, Gioielleria gioielleria)
	{
		gioielleria.caricaGioielli();
		controller.setGioielli(gioielleria);
		//ArrayList<Gioiello> gioielli = gioielleria.getGioielli();
		controller.showInListView();
		
	}
}

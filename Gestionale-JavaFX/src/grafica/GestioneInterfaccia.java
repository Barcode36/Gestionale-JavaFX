package grafica;

import java.util.ArrayList;

import eventi.*;
import javafx.scene.layout.BorderPane;
import logica.*;

public class GestioneInterfaccia extends BorderPane implements Runnable
{
	private static Thread thread;
	public GestioneInterfaccia()
	{
		thread = new Thread();
	}
	public void start(MainController controller, Gioielleria gioielleria)
	{
		ArrayList<Gioiello> gioielli = gioielleria.getGioielli();
		controller.showListView(gioielli);
//		for(Gioiello a : gioielli)
//		{
//			controller.stampaGioielli(a.toString());
//		}
		
	}

	@Override
	public void run() 
	{
		//while(true) {System.out.println("ciao");}
	}
}

package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Scanner;
import java.util.Date;

public class Main 
{
	public static void main(String[] args) 
	{
//		//prova gioielleria
//		Gioielleria gioielleria = new Gioielleria("Francesco");
//		gioielleria.caricaGioielli();
////		for(int i = 0; i < 10; i++)
////		{
////			if(i<5) gioielleria.aggiungiGioiello(new Anello("00"+i,10.2+i,15.0+i,MATERIALE.ARGENTO,"Maschile",12.02+i,true));
////			else gioielleria.aggiungiGioiello(new Bracciale("00"+i,25+i,10+i,MATERIALE.RAME,"Femminile",10+i,0.5+i,0.75+i));
////		}
//		
//		String id = "0015";
//		Gioiello g = gioielleria.getGioiello(id);
//		
//		if(g == null)
//		{
//			System.out.println("non esiste alcun gioiello con questo id: "+ id);
//		}
//		else System.out.println(g);
//		gioielleria.stampaGioielli();
		//gioielleria.salvaGioielli();
		
//		//prova ordini
//		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy"); //formato data
//		
//		try 
//		{
//			Scheduler s = new Scheduler();
//			
//			for(int i = 0; i < 20; i++)
//			{
//				s.aggiungiOrdine(new Ordine("00"+i, data.parse(i+"/04/2019"), "Cuazm", new Anello("001", 15.4, 20.1, MATERIALE.ACCIAIO, "Maschile", 15.0, true)));
//			}
//			s.sort();
//			s.stampaOrdini();
//		} 
//		catch (ParseException e) 
//		{
//			System.out.println("la data non corrisponde al formato corretto");
//			e.printStackTrace();
//		}
		
		GestioneOrdini gestione = new GestioneOrdini();
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		for(int i = 0; i < 5; i++)
		{
			ArrayList<Ordine> ordini = new ArrayList<Ordine>();
			for(int j = 0; j < 5; j++)
			{
				try 
				{
					if(j < 3)
						ordini.add(new Ordine(""+j, data.parse(j+"/11/2019"),"Enzuccio",new Anello("00"+i,10.2+i,15.0+i,MATERIALE.ARGENTO,"Maschile",12.02+i,true),"Riparazione"));
					else
						ordini.add(new Ordine(""+j, data.parse(j+"/11/2019"), "Martina", new Bracciale("00"+i,j,15+i,MATERIALE.ACCIAIO,"Femminile",20,30,1),"Creazione"));
				} 
				catch (ParseException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Cliente c = new Cliente("Fabrizio","Moro",338512456,ordini);
			gestione.aggiungiCliente(c);
		}
		gestione.salvaOrdini();
		gestione.caricaOrdini();
		System.out.println("fatto");
		
		
	}
}
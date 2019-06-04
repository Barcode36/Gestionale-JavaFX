package logica;

import java.util.ArrayList;

import models.Ordine;

public class Scheduler
{
	private ArrayList<Ordine> ordini;
	
	public Scheduler()
	{
		ordini = new ArrayList<Ordine>();
	}
	
	public Ordine getProssimoOrdine()
	{
		if(!ordini.isEmpty())
		{
			return ordini.get(0);
		}
		return null;
	}
	
	public void aggiungiOrdine(Ordine ordine)
	{
		ordini.add(ordine);
	}
	
	public void sort()
	{
//		ordini.sort(new Comparator<Ordine>() 
//		{
//
//			@Override
//			public int compare(Ordine o1, Ordine o2) 
//			{
//				int compare = 0;
//				if(o1.getDataScadenza().before(o2.getDataScadenza())) compare = -1;
//				if(o1.getDataScadenza().equals(o2.getDataScadenza())) compare = 0;
//				if(o1.getDataScadenza().after(o2.getDataScadenza())) compare = 1;
//				return compare;
//			}
//		});
	}
	
	public void stampaOrdini()
	{
		for(Ordine o : ordini)
		{
			System.out.println(o);
		}
	}
}

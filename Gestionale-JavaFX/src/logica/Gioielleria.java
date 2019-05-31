package logica;

import java.util.ArrayList;
import java.util.Comparator;
import gestioneDB.GestioneQuery;
import models.Anello;
import models.Bracciale;
import models.Gioiello;

public class Gioielleria 
{
	private GestioneQuery database;
	private ArrayList <Gioiello> gioielli;
	private long gioielliNelDatabase;
	private String nomeGioielleria;
	private double incassoGiornaliero;
	private double guadagnoGiornaliero;
	
	public Gioielleria(String nomeGioielleria)
	{
		database = new GestioneQuery();
		this.nomeGioielleria = nomeGioielleria;
		this.guadagnoGiornaliero = 0;
		this.incassoGiornaliero = 0;
	}
	
	public void salvaGioielli()
	{
		for(long i = gioielliNelDatabase; i < gioielli.size(); i++)
		{
			if(gioielli.get((int)i) instanceof Anello)
			{
				database.salvaGioiello(gioielli.get((int)i));
				gioielliNelDatabase++;
			}
			else if(gioielli.get((int)i) instanceof Bracciale)
			{
				database.salvaGioiello(gioielli.get((int)i));
				gioielliNelDatabase++;
			}
		}
		System.out.println("Ora i gioielli nel DB sono " + gioielliNelDatabase);
	}
	
	public void caricaGioielli()
	{
		gioielliNelDatabase = database.getGioielliNelDB();
		System.out.println("Database size "+ gioielliNelDatabase);
		gioielli = database.caricaGioielli();
		
		gioielli.sort(new Comparator<Gioiello>() {

			@Override
			public int compare(Gioiello o1, Gioiello o2) 
			{
				int compare = 0;
				if(o1.getId() < o2.getId()) compare = -1;
				if(o1.getId() == o2.getId()) compare = 0;
				if(o1.getId() > o2.getId()) compare = 1;
				
				return compare;
			}
			
		});
	}
	
	public String getNomeGioielleria() { return this.nomeGioielleria; }
	
	public int getNumeroGioielli() { return this.gioielli.size(); }
	
	public void aggiungiGioiello(Gioiello g)
	{
		if(gioielli.size() == 0)
		{
			g.setId(0);
			this.gioielli.add(g);
			System.out.println("id gioiello " + g.getId());
		}
		else
		{
			g.setId(gioielli.get(gioielli.size()-1).getId()+1);
			System.out.println("id gioiello " + g.getId());
			this.gioielli.add(g);
		}
	}
	
	public boolean rimuoviGioiello(int id)
	{
		boolean trovato = false;
		for(int i = 0; i < gioielli.size(); i++)
		{
			if(gioielli.get(i).getId() == id)
			{
				gioielli.remove(i);
				trovato = true;
			}
		}
		
		return trovato;
	}
	
	public boolean vendiGioiello(int id, float prezzoVenduto)
	{
		boolean venduto = false;
		for(int i = 0; i < gioielli.size(); i++)
		{
			if(gioielli.get(i).getId() == id)
			{
				this.incassoGiornaliero += prezzoVenduto;
				this.guadagnoGiornaliero += prezzoVenduto - gioielli.get(i).getPrezzo();
				venduto = true;
				gioielli.get(i).setVenduto(true);
			}
		}
		
		return venduto;
	}
	
	public double getIncassoGiornaliero() { return this.incassoGiornaliero; }
	public double getGuadagnoGiornaliero() { return this.guadagnoGiornaliero; }
	
	public void stampaGioielli()
	{
		for(Gioiello s : gioielli)
		{
			System.out.println(s);
		}
	}

	public ArrayList<Gioiello> getGioielli() { return gioielli; }

}
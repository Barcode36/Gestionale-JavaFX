package logica;

import java.sql.SQLException;
import java.util.ArrayList;
import gestioneDB.GestioneQuery;;
public class Gioielleria 
{
	private GestioneQuery database;
	private ArrayList <Gioiello> gioielli;
	private String nomeGioielleria;
	private double incassoGiornaliero;
	private double guadagnoGiornaliero;
	
	public Gioielleria(String nomeGioielleria) throws ClassNotFoundException, SQLException
	{
		database = new GestioneQuery();
		this.nomeGioielleria = nomeGioielleria;
		this.guadagnoGiornaliero = 0;
		this.incassoGiornaliero = 0;
	}
	
	public void salvaGioielli()
	{
		
	}
	
	public void caricaGioielli()
	{
		gioielli = database.caricaGioielli();
	}
	
	public String getNomeGioielleria() { return this.nomeGioielleria; }
	
	public int getNumeroGioielli() { return this.gioielli.size(); }
	
	public void aggiungiGioiello(Gioiello g) { this.gioielli.add(g);}
	
	public Gioiello getGioiello(int id)
	{
//		for(Gioiello g : gioielli)
//		{
//			if(g.getId() == id) return g;
//		}
		
		return database.findByPrimaryKey(id);
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

	public ArrayList<Gioiello> getGioielli() {
		return gioielli;
	}

}

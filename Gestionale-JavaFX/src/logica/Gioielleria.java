package logica;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Gioielleria 
{
	private ArrayList <Gioiello> gioielli;
	private String nomeGioielleria;
	private double incassoGiornaliero;
	private double guadagnoGiornaliero;
	
	public Gioielleria(String nomeGioielleria)
	{
		gioielli = new ArrayList <Gioiello>();
		this.nomeGioielleria = nomeGioielleria;
		this.guadagnoGiornaliero = 0;
		this.incassoGiornaliero = 0;
	}
	
	public boolean salvaGioielli()
	{
		try 
		{
			BufferedWriter file = new BufferedWriter(new FileWriter("gioielli.txt"));
			
			for(Gioiello g : gioielli)
			{
				file.append(g.toString());
				file.newLine();
			}
			
			file.close();
			return true;
		} 
		catch (IOException e) 
		{
			System.out.println("impossibile salvare il file");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean caricaGioielli()
	{
		try 
		{
			BufferedReader file = new BufferedReader(new FileReader("gioielli.txt"));
			
			while(file.ready())
			{
				String gioiello = file.readLine();
				String[] dati = gioiello.split(";");
				
				if(Gioiello.costruisciGioiello(dati) != null) gioielli.add(Gioiello.costruisciGioiello(dati));
			}
			file.close();
			return true;
		} 
		catch (IOException e) 
		{
			System.out.println("file non trovato");
			e.printStackTrace();
			return false;
		}
	}
	
	public String getNomeGioielleria() { return this.nomeGioielleria; }
	
	public int getNumeroGioielli() { return this.gioielli.size(); }
	
	public void aggiungiGioiello(Gioiello g) { this.gioielli.add(g);}
	
	public Gioiello getGioiello(String id)
	{
		for(Gioiello g : gioielli)
		{
			if(g.getId().equals(id)) return g;
		}
		
		return null;
		//return new Bracciale("0",0,0,MATERIALE.ZINCO,"null",0,0,0);
	}
	
	public boolean rimuoviGioiello(String id)
	{
		boolean trovato = false;
		for(int i = 0; i < gioielli.size(); i++)
		{
			if(gioielli.get(i).getId().equals(id))
			{
				gioielli.remove(i);
				trovato = true;
			}
		}
		
		return trovato;
	}
	
	public boolean vendiGioiello(String id, int prezzoVenduto)
	{
		boolean venduto = false;
		for(int i = 0; i < gioielli.size(); i++)
		{
			if(gioielli.get(i).getId().equals(id))
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

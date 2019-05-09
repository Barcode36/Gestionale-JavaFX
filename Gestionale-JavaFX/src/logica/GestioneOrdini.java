package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GestioneOrdini 
{
	private ArrayList <Cliente> clienti;
	
	public GestioneOrdini()
	{
		this.clienti = new ArrayList<Cliente>();
	}
	
	public ArrayList <Cliente> getClienti(){ return this.clienti;}
	public void aggiungiCliente(Cliente cliente) {this.clienti.add(cliente);}
	public Cliente getCliente(int index)
	{
		if(index < clienti.size()) return clienti.get(index);
		else return null;
	}
	
	public boolean rimuoviCliente(String nomeCliente, int numeroTelefono)
	{
		for(int i = 0; i < clienti.size(); i++)
		{
			if(clienti.get(i).getNomeCliente().equals(nomeCliente) && clienti.get(i).getNumeroTelefono() == numeroTelefono)
			{
				clienti.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	public void salvaOrdini()
	{
		try 
		{
			BufferedWriter file = new BufferedWriter(new FileWriter("ordini.txt"));
			
			for(Cliente c : clienti)
			{
				ArrayList <Ordine> temp = c.getOrdini();
				
				for(Ordine o : temp)
				{
					file.append(o.toString());
					file.newLine();
				}
			}
			file.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void caricaOrdini()
	{
		try 
		{
			BufferedReader file = new BufferedReader(new FileReader("ordini.txt"));
			
			int k = 0; 
			while(file.ready() && k < 1)
			{
				k++;
				String s = file.readLine();
				String[] dati = s.split(";");
				String id = dati[0];
				String nomeCliente = dati[1];
				Date scadenza;
				Date dataOrdine;
				
				if(dati[2].equals("Anello"))
				{
					String[] temp = new String[8];
					for(int i = 0; i < 8; i++)
					{
						temp[i] = dati[i+2];
					}
					
					Gioiello g = Gioiello.costruisciGioiello(temp);
					System.out.println(g);
					SimpleDateFormat data = new SimpleDateFormat();
					
					try 
					{
						scadenza = data.parse(temp[10]);
						dataOrdine = data.parse(temp[11]);
					} 
					catch (ParseException e) 
					{
						System.out.println("impossibile parserizzare la data");
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String tipologia = temp[12];
					
					Cliente c = new Cliente(nomeCliente,"Marcolino",333333,new ArrayList<Ordine>()); //bisogna fare l'unique dei nomi dei clienti e creare un ArrayList<Ordine> da passare nel costruttore
				}
				
				//clienti.add(new Cliente(dati[0], ));
			}
			
			file.close();
		} 
		catch (IOException e) 
		{
			System.out.println("ordini non trovati");
			e.printStackTrace();
		}
	}
}

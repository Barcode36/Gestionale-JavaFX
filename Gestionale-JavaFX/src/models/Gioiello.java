package models;

import java.io.FileInputStream;
import java.util.ArrayList;
import gestioneDB.GestioneQuery;

public abstract class Gioiello 
{
	private int id;
	private double prezzo; 
	private double peso;
	private MATERIALE materiale;
	private boolean venduto;
	private String genere; //maschile,femminile
	protected String nomeGioiello;
	private String descrizione;
	
	public Gioiello(double prezzo, double peso, MATERIALE materiale, String genere, boolean venduto, String descrizione)
	{
		if(prezzo >= 0) this.prezzo = prezzo;
		if(peso >= 0) this.peso = peso;
		this.materiale = materiale;
		if(genere.equals("Maschile") || genere.equals("Femminile"))
			this.genere = genere;
		this.venduto = venduto;
		this.descrizione = descrizione;
		
		GestioneQuery database = new GestioneQuery();
		this.id = database.getNumUltimoGioiello() + 1;
		database.chiudiConnessione();
	}
	
	public String getNomeGioiello() { return this.nomeGioiello; }
	public void setNomeGioiello(String nomeGioiello) { this.nomeGioiello = nomeGioiello;}
	
	public boolean getVenduto() {return this.venduto;}
	public void setVenduto(boolean venduto) { this.venduto = venduto;}
	
	public int getId() { return this.id; }
	public void setId(int id) { this.id = id; }
	
	public double getPrezzo() { return prezzo; }
	public void setPrezzo(double prezzo) { this.prezzo = prezzo; }

	public double getPeso() { return peso; }
	public void setPeso(double peso) { this.peso = peso; }

	public MATERIALE getMateriale() { return materiale; }
	public void setMateriale(MATERIALE materiale) { this.materiale = materiale; }

	public String getGenere() { return genere; }
	public void setGenere(String genere) { this.genere = genere; }
	
	public String getDescrizione() { return this.descrizione; }
	public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
	
	public abstract String stampaCaratteristiche();
	
	public void salva()
	{
		GestioneQuery database = new GestioneQuery();
		database.salvaGioiello(this);
		database.chiudiConnessione();
	}
	
	public void eliminaGioiello()
	{
		GestioneQuery database = new GestioneQuery();
		database.eliminaGioiello(this);
		database.chiudiConnessione();
	}
	
	public static ArrayList<Gioiello> caricaGioielli()
	{
		GestioneQuery database = new GestioneQuery();
		System.out.println("sto caricando i gioielli");
		ArrayList<Gioiello> gioielli = database.caricaGioielli();
		database.chiudiConnessione();
		return gioielli;
	}
	
	public static ArrayList<Gioiello> caricaGioielli(String query, String tipologia)
	{
		GestioneQuery database = new GestioneQuery();
		ArrayList<Gioiello> gioielli = database.getGioielli(query, tipologia);
		database.chiudiConnessione();
		
		return gioielli;
	}
	
	public ArrayList<Immagine> caricaImmagini()
	{
		//non inserire png
		GestioneQuery database = new GestioneQuery();
		ArrayList<Immagine> immagini = database.caricaImmagini(this);
		database.chiudiConnessione();
		return immagini;
	}
	
	public void aggiungiImmagine(FileInputStream immagine)
	{
		GestioneQuery database = new GestioneQuery();
		database.inserisciImmagine(immagine, this);
		database.chiudiConnessione();
	}
}

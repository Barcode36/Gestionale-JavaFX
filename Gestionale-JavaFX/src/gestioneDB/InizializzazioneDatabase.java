package gestioneDB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//classe che serve a creare il database al primo avvio del programma

public class InizializzazioneDatabase 
{
	boolean avvio = false; //serve nel main al primo avvio
	private final String driver = "org.postgresql.Driver";
	private final String url = "jdbc:postgresql://localhost/Gioielleria";
	private final String databaseType = "postgres";
	private final String password = "3890498266";
	private Connection con; 
	
	public InizializzazioneDatabase() 
	{
		try 
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url, databaseType, password);
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void chiudiConnessione()
	{
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean getAvvio() { return this.avvio; }
	public void setAvvio(boolean avvio) { this.avvio = avvio; }
	
	public static boolean controllaPrimoAvvio()
	{
		boolean primoAvvio = false;
		BufferedReader file;
		try 
		{
			file = new BufferedReader(new FileReader("primoAvvio.txt"));
			while(file.ready())
			{
				primoAvvio = Boolean.parseBoolean(file.readLine());
			}
			
			file.close();
		} 
		catch (IOException e) 
		{
			primoAvvio = true;
		}
		
		return primoAvvio;
	}
	
	public void scriviFile()
	{
		try 
		{
			BufferedWriter file = new BufferedWriter(new FileWriter("primoAvvio.txt"));
			file.append(Boolean.FALSE.toString());
			file.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
	}
	
	public void creaTabelle()
	{
		String query = "create table if not exists Prodotto\r\n" + 
				"(\r\n" + 
				"	idProdotto serial not null primary key,\r\n" + 
				"	prezzo float not null,\r\n" + 
				"	peso float not null,\r\n" + 
				"	materiale varchar not null,\r\n" + 
				"	genere varchar not null,\r\n" + 
				"	venduto boolean not null,\r\n" + 
				"	nomeGioiello varchar not null,\r\n" + 
				"	nomeTabella varchar not null,\r\n" + 
				"	descrizione text\r\n" + 
				");\r\n" + 
				"create table if not exists Anello\r\n" + 
				"(\r\n" + 
				"	idProdotto int not null,\r\n" + 
				"	raggio double not null,\r\n" + 
				"	pietra boolean not null,\r\n" + 
				"	foreign key(idProdotto) references prodotto(idProdotto)\r\n" + 
				");\r\n" + 
				"create table if not exists Bracciale\r\n" + 
				"(\r\n" + 
				"	idProdotto int not null,\r\n" + 
				"	lunghezza double not null,\r\n" + 
				"	spessore double not null,\r\n" + 
				"	larghezza double not null,\r\n" + 
				"	foreign key(idProdotto) references prodotto(idProdotto)\r\n" + 
				");\r\n" + 
				"create table if not exists Orecchino\r\n" + 
				"(\r\n" + 
				"	idProdotto int not null,\r\n" + 
				"	spessore double not null,\r\n" + 
				"	altezza double not null,\r\n" + 
				"	tipologiaOrecchino varchar not null,\r\n" + 
				"	foreign key(idProdotto) references prodotto(idProdotto)\r\n" + 
				");\r\n" + 
				"create table if not exists Collana\r\n" + 
				"(\r\n" + 
				"	idProdotto int not null,\r\n" + 
				"	lunghezza double not null,\r\n" + 
				"	spessore double not null,\r\n" + 
				"	ciondolo boolean not null,\r\n" + 
				"	foreign key(idProdotto) references prodotto(idProdotto)\r\n" + 
				");\r\n" + 
				"create table if not exists Cliente\r\n" + 
				"(\r\n" + 
				"	idCliente serial not null primary key,\r\n" + 
				"	nome varchar not null,\r\n" + 
				"	cognome varchar not null,\r\n" + 
				"	numeroTelefono varchar\r\n" + 
				");\r\n" + 
				"create table if not exists Ordine\r\n" + 
				"(\r\n" + 
				"	idOrdine serial not null primary key,\r\n" + 
				"	idProdotto int,\r\n" + 
				"	dataEmissione varchar not null,\r\n" + 
				"	dataScadenza varchar not null,\r\n" + 
				"	tipologia varchar not null,\r\n" + 
				"	idCliente int not null,\r\n" + 
				"	descrizione text,\r\n" + 
				"	foreign key(idProdotto) references prodotto(idProdotto),\r\n" + 
				"	foreign key(idCliente) references cliente(idCliente)\r\n" + 
				");\r\n" + 
				"\r\n" + 
				"create table if not exists Fattura\r\n" + 
				"(\r\n" + 
				"	dataEmissione varchar not null,\r\n" + 
				"	importo double not null,\r\n" + 
				"	idOrdine int,\r\n" + 
				"	idCliente int,\r\n" + 
				"	nomeCliente varchar not null,\r\n" + 
				"	nomeProdotto varchar not null,\r\n" + 
				"	foreign key(idProdotto) references prodotto(idProdotto),\r\n" + 
				"	foreign key(idCliente) references cliente(idCliente)\r\n" + 
				");\r\n" + 
				"\r\n" + 
				"create table if not exists Immagini\r\n" + 
				"(\r\n" + 
				"	idImmagine serial not null primary key,\r\n" + 
				"	immagine bytea not null,\r\n" + 
				"	idProdotto int not null,\r\n" + 
				"	foreign key(idProdotto) references Prodotto(idProdotto)\r\n" + 
				");";
		try 
		{
			PreparedStatement cmd = con.prepareStatement(query);
			cmd.executeUpdate();
			cmd.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
}

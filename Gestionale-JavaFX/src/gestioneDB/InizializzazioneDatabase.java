package gestioneDB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
	
	public boolean controllaPrimoAvvio()
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
}

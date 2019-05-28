package gestioneDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Anello;
import models.Bracciale;
import models.Cliente;
import models.Gioiello;
import models.MATERIALE;
import models.Ordine;

public class GestioneQuery
{
	private final String driver = "org.postgresql.Driver";
	private final String url = "jdbc:postgresql://localhost/Gioielleria";
	private Connection con; 
	private Statement cmd;

	public GestioneQuery() throws ClassNotFoundException, SQLException 
	{
		Class.forName(driver);
		con = DriverManager.getConnection(url, "postgres", "password");
		cmd = con.createStatement();
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
	
	public void salvaGioiello(Gioiello gioiello)//, int tipo) 
	{
		
		try
		{
			if(gioiello instanceof Anello)
			{
				Anello a = (Anello)gioiello;
				cmd.executeUpdate("insert into anello values ("+a.getId()+","+a.getPrezzo()+","+a.getPeso()+",'"
						+a.getMateriale()+"','"+a.getGenere()+"',"+a.getVenduto()+","+a.getRaggio()+","+a.isPietra()+",'"+a.getNomeGioiello()+"','"+a.getDescrizione()+"');");
			}
			else if(gioiello instanceof Bracciale)
			{
				Bracciale a = (Bracciale)gioiello;
				cmd.executeUpdate("insert into bracciale values ("+a.getId()+","+a.getPrezzo()+","+a.getPeso()+",'"
					+a.getMateriale()+"','"+a.getGenere()+"',"+a.getVenduto()+","+a.getLunghezza()+","+a.getSpessore()+","+a.getLarghezza()+",'"+a.getNomeGioiello()+"','"+a.getDescrizione()+"');");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public Gioiello findByPrimaryKey(long id) 
	{
		try 
		{
			ResultSet res = cmd.executeQuery("select * from anello where gioielloid = "+ id  + ";");
			while(res.next())
			{
				int gioielloId = res.getInt(1);
			    double prezzo = res.getDouble(2);
			    double peso = res.getDouble(3);
			    MATERIALE materiale = MATERIALE.valueOf(res.getString(4));
			    String genere = res.getString(5);
			    boolean venduto = res.getBoolean(6);
			    double raggio = res.getDouble(7);
			    boolean pietra = res.getBoolean(8);
			    String nomeGioiello = res.getString(9);
			    String descrizione = res.getString(10);
			    return new Anello(gioielloId,prezzo,peso,materiale,genere,venduto,raggio,pietra, nomeGioiello,descrizione);
			}
			
			if(res.next() == false)
			{
				res = cmd.executeQuery("select * from bracciale where gioielloid = "+ id + ";");
				
				while(res.next())
				{
					int gioielloId = res.getInt(1);
				    double prezzo = res.getDouble(2);
				    double peso = res.getDouble(3);
				    MATERIALE materiale = MATERIALE.valueOf(res.getString(4));
				    String genere = res.getString(5);
				    boolean venduto = res.getBoolean(6);
				    double lunghezza = res.getDouble(7);
				    double spessore = res.getDouble(8);
				    double larghezza = res.getDouble(9);
				    String nomeGioiello = res.getString(10);
				    String descrizione = res.getString(11);
				    return new Bracciale(gioielloId,prezzo,peso,materiale,genere,venduto,lunghezza,spessore,larghezza,nomeGioiello,descrizione);
				}
			}
			
			res.close();
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Gioiello> caricaGioielli() 
	{
		ArrayList<Gioiello> gioielli = new ArrayList<Gioiello>();
		
		String query = "select * from anello";
		 
	    try 
	    {
	    	ResultSet res = cmd.executeQuery(query);
	    	while (res.next()) 
	    	{
			    int gioielloId = res.getInt(1);
			    double prezzo = res.getDouble(2);
			    double peso = res.getDouble(3);
			    MATERIALE materiale = MATERIALE.valueOf(res.getString(4));
			    String genere = res.getString(5);
			    boolean venduto = res.getBoolean(6);
			    double raggio = res.getDouble(7);
			    boolean pietra = res.getBoolean(8);
			    String nomeGioiello = res.getString(9);
			    String descrizione = res.getString(10);
			    gioielli.add(new Anello(gioielloId,prezzo,peso,materiale,genere,venduto,raggio,pietra, nomeGioiello,descrizione));
	    	}
	    	
	    	res = cmd.executeQuery("select * from bracciale");
	    	while(res.next())
	    	{
	    		int gioielloId = res.getInt(1);
			    double prezzo = res.getDouble(2);
			    double peso = res.getDouble(3);
			    MATERIALE materiale = MATERIALE.valueOf(res.getString(4));
			    String genere = res.getString(5);
			    boolean venduto = res.getBoolean(6);
			    double lunghezza = res.getDouble(7);
			    double spessore = res.getDouble(8);
			    double larghezza = res.getDouble(9);
			    String nomeGioiello = res.getString(10);
			    String descrizione = res.getString(11);
			    gioielli.add(new Bracciale(gioielloId,prezzo,peso,materiale,genere,venduto,lunghezza,spessore,larghezza,nomeGioiello,descrizione));
	    	}
	    	
	    	res.close();
		} 
	    catch (SQLException e) 
	    {
	    	System.out.println("impossibile raggiungere il server");
			e.printStackTrace();
		}
		
	    return gioielli;
	}

	public void delete(int id) 
	{
		try 
		{
			cmd.executeUpdate("delete from anello where gioielloid = " + id + ";");
			cmd.executeUpdate("delete from bracciale where gioielloid = " + id + ";");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getGioielliNelDB() 
	{
		int size = 0;
		
		try 
		{
			ResultSet res = cmd.executeQuery("select count(*) from anello;");
			while(res.next()) size+=res.getInt(1);
			res = cmd.executeQuery("select count(*) from bracciale;");
			while(res.next()) size+=res.getInt(1);
			
			res.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return size;
	}
	
	public ArrayList<Cliente> caricaClienti()
	{
		ArrayList<Cliente> clienti = new ArrayList<Cliente>();
		String query = "select * from cliente;";
		
		try 
		{
			ResultSet res = cmd.executeQuery(query);
			
			while(res.next())
			{
				clienti.add(new Cliente(res.getInt(1),res.getString(2),res.getString(3),res.getString(4)));
			}
			
			res.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clienti;
	}
	
	public void salvaClienti(Cliente cliente)
	{
		try 
		{
			cmd.executeUpdate("insert into cliente (nome, cognome, numerotelefono) values"
				+ "('"+cliente.getNomeCliente()+"','"+cliente.getCognomeCliente()+"','"+cliente.getNumeroTelefono()+"');");
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
	}
	
	public int getClientiNelDB() 
	{
		int size = 0;
		
		try 
		{
			ResultSet res = cmd.executeQuery("select count(*) from cliente;");
			while(res.next()) size+=res.getInt(1);
			
			res.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return size;
	}
	
	public void salvaOrdine(Ordine o, Cliente cliente) throws SQLException
	{
		if(o.getGioiello() instanceof Anello)
		{
			String query = "insert into ordine(dataEmissione,dataScadenza,tipologia,idAnello,descrizione,idCliente)"
					+ "values('"+o.getDataOrdine()+"','"+o.getDataScadenza()+"','"+o.getTipologia()+"',"+o.getGioiello().getId()+",'"+o.getDescrizione()+"',"+cliente.getId()+")";
			
			cmd.executeUpdate(query);
		}
		else if(o.getGioiello() instanceof Bracciale)
		{
			String query = "insert into ordine(dataEmissione,dataScadenza,tipologia,idBracciale,descrizione,idCliente)"
					+ "values('"+ o.getDataOrdine()+"','"+o.getDataScadenza()+"','"+o.getTipologia()+"',"+o.getGioiello().getId()+",'"+o.getDescrizione()+"',"+cliente.getId()+")";
			cmd.executeUpdate(query);
		}
	}
	
	public int getNumeroOrdini(Cliente cliente)
	{
		int numeroOrdini = 0;
		String query = "select count(*) "
				+ "from ordine, cliente "
				+ "where ordine.idcliente = " + cliente.getId() + ";";
		try 
		{
			ResultSet res = cmd.executeQuery(query);
			while(res.next()) numeroOrdini+=res.getInt(1);
			
			res.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return numeroOrdini;
	}

}

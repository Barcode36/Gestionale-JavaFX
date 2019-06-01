package gestioneDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public GestioneQuery()
	{
		try 
		{
			Class.forName(driver);
			con = DriverManager.getConnection(url, "postgres", "password");
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
	
	public void salvaGioiello(Gioiello gioiello)//, int tipo) 
	{	
		try
		{
			if(gioiello instanceof Anello)
			{
				Anello a = (Anello)gioiello;
				PreparedStatement cmd = con.prepareStatement("insert into anello values(?,?,?,?,?,?,?,?,?,?);");
				cmd.setLong(1, a.getId());
				cmd.setDouble(2, a.getPrezzo());
				cmd.setDouble(3, a.getPeso());
				cmd.setString(4, a.getMateriale().toString());
				cmd.setString(5, a.getGenere());
				cmd.setBoolean(6, a.getVenduto());
				cmd.setDouble(7, a.getRaggio());
				cmd.setBoolean(8, a.isPietra());
				cmd.setString(9, a.getNomeGioiello());
				cmd.setString(10, a.getDescrizione());
				cmd.executeUpdate();
				cmd.close();
			}
			else if(gioiello instanceof Bracciale)
			{
				Bracciale a = (Bracciale)gioiello;
				PreparedStatement cmd = con.prepareStatement("insert into bracciale values(?,?,?,?,?,?,?,?,?,?,?)");
				cmd.setLong(1, a.getId());
				cmd.setDouble(2, a.getPrezzo());
				cmd.setDouble(3, a.getPeso());
				cmd.setString(4, a.getMateriale().toString());
				cmd.setString(5, a.getGenere());
				cmd.setBoolean(6, a.getVenduto());
				cmd.setDouble(7, a.getLunghezza());
				cmd.setDouble(8, a.getSpessore());
				cmd.setDouble(9, a.getLarghezza());
				cmd.setString(10, a.getNomeGioiello());
				cmd.setString(11, a.getDescrizione());
				cmd.executeUpdate();
				cmd.close();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public Gioiello getGioiello(long id) 
	{
		try 
		{
			PreparedStatement cmd = con.prepareStatement("select * from anello where idGioiello = ?");
			cmd.setLong(1, id);
			ResultSet res = cmd.executeQuery();
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
				PreparedStatement cmd2 = con.prepareStatement("select * from bracciale where idGioiello = ?");
				cmd2.setLong(1, id);
				ResultSet res2 = cmd.executeQuery();
				
				while(res2.next())
				{
					int gioielloId = res2.getInt(1);
				    double prezzo = res2.getDouble(2);
				    double peso = res2.getDouble(3);
				    MATERIALE materiale = MATERIALE.valueOf(res2.getString(4));
				    String genere = res2.getString(5);
				    boolean venduto = res2.getBoolean(6);
				    double lunghezza = res2.getDouble(7);
				    double spessore = res2.getDouble(8);
				    double larghezza = res2.getDouble(9);
				    String nomeGioiello = res2.getString(10);
				    String descrizione = res2.getString(11);
				    return new Bracciale(gioielloId,prezzo,peso,materiale,genere,venduto,lunghezza,spessore,larghezza,nomeGioiello,descrizione);
				}
				res2.close();
				cmd2.close();
			}
			
			res.close();
			cmd.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Gioiello> caricaGioielli() 
	{
		ArrayList<Gioiello> gioielli = new ArrayList<Gioiello>();
		
	    try 
	    {
	    	PreparedStatement cmd = con.prepareStatement("select * from anello");
	    	ResultSet res = cmd.executeQuery();
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
	    	
	    	PreparedStatement cmd2 = con.prepareStatement("select * from bracciale");
	    	ResultSet res2 = cmd2.executeQuery();
	    	while(res2.next())
	    	{
	    		int gioielloId = res2.getInt(1);
			    double prezzo = res2.getDouble(2);
			    double peso = res2.getDouble(3);
			    MATERIALE materiale = MATERIALE.valueOf(res2.getString(4));
			    String genere = res2.getString(5);
			    boolean venduto = res2.getBoolean(6);
			    double lunghezza = res2.getDouble(7);
			    double spessore = res2.getDouble(8);
			    double larghezza = res2.getDouble(9);
			    String nomeGioiello = res2.getString(10);
			    String descrizione = res2.getString(11);
			    gioielli.add(new Bracciale(gioielloId,prezzo,peso,materiale,genere,venduto,lunghezza,spessore,larghezza,nomeGioiello,descrizione));
	    	}
	    	
	    	cmd.close();
	    	cmd2.close();
	    	res.close();
	    	res2.close();
		} 
	    catch (SQLException e) 
	    {
	    	System.out.println("impossibile raggiungere il server");
			e.printStackTrace();
		}
		
	    return gioielli;
	}

	public void eliminaOrdine(Ordine ordine) 
	{
		try 
		{
			PreparedStatement cmd = con.prepareStatement("delete from ordine where idOrdine = ?");
			cmd.setLong(1, ordine.getId());
			cmd.executeUpdate();
			cmd.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public int getGioielliNelDB() 
	{
		int size = 0;
		
		try 
		{
			PreparedStatement cmd = con.prepareStatement("select count(*) from anello");
			ResultSet res = cmd.executeQuery();
			while(res.next()) size+=res.getInt(1);
			
			PreparedStatement cmd2 = con.prepareStatement("select count(*) from bracciale");
			ResultSet res2 = cmd2.executeQuery();
			while(res2.next()) size+=res2.getInt(1);
			
			cmd.close();
			cmd2.close();
			res.close();
			res2.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return size;
	}
	
	public ArrayList<Cliente> caricaClienti()
	{
		ArrayList<Cliente> clienti = new ArrayList<Cliente>();
		
		try 
		{
			PreparedStatement cmd = con.prepareStatement("select * from cliente");
			ResultSet res = cmd.executeQuery();
			
			while(res.next())
			{
				Cliente cliente = new Cliente(res.getString(2),res.getString(3),res.getString(4));
				cliente.setId(res.getInt(1));
				clienti.add(cliente);
			}
			
			cmd.close();
			res.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return clienti;
	}
	
	public void salvaClienti(Cliente cliente)
	{
		try 
		{
			PreparedStatement cmd = con.prepareStatement("insert into cliente(nome,cognome,numeroTelefono) values (?,?,?)");
			cmd.setString(1, cliente.getNomeCliente());
			cmd.setString(2, cliente.getCognomeCliente());
			cmd.setString(3, cliente.getNumeroTelefono());
			cmd.executeUpdate();
			
			cmd.close();
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
			PreparedStatement cmd = con.prepareStatement("select count(*) from cliente");
			ResultSet res = cmd.executeQuery();
			while(res.next()) size+=res.getInt(1);
			
			cmd.close();
			res.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return size;
	}
	
	public void salvaOrdine(Ordine o, Cliente cliente) throws SQLException
	{
		if(o.getGioiello() instanceof Anello)
		{
			String query = "insert into ordine(dataEmissione,dataScadenza,tipologia,idAnello,descrizione,idCliente) values(?,?,?,?,?,?)";
			PreparedStatement cmd = con.prepareStatement(query);
			cmd.setString(1, o.getDataOrdine().toString());
			cmd.setString(2, o.getDataScadenza().toString());
			cmd.setString(3, o.getTipologia());
			cmd.setLong(4, o.getGioiello().getId());
			cmd.setString(5, o.getDescrizione());
			cmd.setInt(6, cliente.getId());
			
			cmd.executeUpdate();
			cmd.close();
		}
		else if(o.getGioiello() instanceof Bracciale)
		{
			String query = "insert into ordine(dataEmissione,dataScadenza,tipologia,idBracciale,descrizione,idCliente) values(?,?,?,?,?,?)";
			PreparedStatement cmd = con.prepareStatement(query);
			cmd.setString(1, o.getDataOrdine().toString());
			cmd.setString(2, o.getDataScadenza());
			cmd.setString(3, o.getTipologia());
			cmd.setLong(4, o.getGioiello().getId());
			cmd.setString(5, o.getDescrizione());
			cmd.setInt(6, cliente.getId());
			
			cmd.executeUpdate();
			cmd.close();
		}
	}
	
	public int getNumeroOrdiniCliente(Cliente cliente)
	{
		int numeroOrdini = 0;
		
		try 
		{
			PreparedStatement cmd = con.prepareStatement("select count(*) from ordine where idCliente = ?");
			cmd.setInt(1, cliente.getId());
			ResultSet res = cmd.executeQuery();
			while(res.next()) numeroOrdini+=res.getInt(1);
			
			cmd.close();
			res.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return numeroOrdini;
	}

	public void eliminaGioiello(Gioiello gioiello)
	{
		if(gioiello instanceof Anello)
		{
			try 
			{
				PreparedStatement cmd = con.prepareStatement("update ordine set idAnello = null where idAnello = ?");
				cmd.setLong(1, gioiello.getId());
				cmd.executeUpdate();
				cmd.close();
				
				PreparedStatement cmd2 = con.prepareStatement("delete from anello where idGioiello = ?");
				cmd2.setLong(1, gioiello.getId());
				cmd2.executeUpdate();
				cmd2.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(gioiello instanceof Bracciale)
		{
			try
			{
				PreparedStatement cmd = con.prepareStatement("update ordine set idBracciale = null where idBracciale = ?");
				cmd.setLong(1, gioiello.getId());
				cmd.executeUpdate();
				cmd.close();
				
				PreparedStatement cmd2 = con.prepareStatement("delete from bracciale where idGioiello = ?");
				cmd2.setLong(1, gioiello.getId());
				cmd2.executeUpdate();
				cmd2.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
	}
	
	public void eliminaCliente(Cliente cliente)
	{
		
		try 
		{
			PreparedStatement cmd = con.prepareStatement("delete from ordine where idCliente = ?");
			cmd.setInt(1, cliente.getId());
			cmd.executeUpdate();
			
			PreparedStatement cmd2 = con.prepareStatement("delete from cliente where idCliente = ?");
			cmd2.setInt(1, cliente.getId());
			cmd2.executeUpdate();
			
			cmd.close();
			cmd2.close();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public int getIDultimoCliente()
	{
		int id = 0;
		try 
		{
			PreparedStatement cmd = con.prepareStatement("select MAX(idCliente) from cliente");
			ResultSet res = cmd.executeQuery();
			
			while(res.next()) id = res.getInt(1);
			
			res.close();
			cmd.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return id;
	}
	
	public ArrayList<Ordine> caricaOrdini()
	{
		ArrayList<Ordine> ordini = new ArrayList<Ordine>();
		try 
		{
			PreparedStatement cmd = con.prepareStatement("select * from ordine");
			ResultSet res = cmd.executeQuery();
			
			while(res.next())
			{
				Gioiello gioiello = getGioiello(res.getInt(5));
				if(gioiello == null) gioiello = getGioiello(res.getInt(6));
				ordini.add(new Ordine(res.getInt(1),res.getString(3),gioiello,res.getString(4),res.getString(8)));
			}
			
			cmd.close();
			res.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return ordini;
	}
	
	public ArrayList<Ordine> caricaOrdini(Cliente cliente)
	{
		ArrayList<Ordine> ordini = new ArrayList<Ordine>();
		try 
		{
			PreparedStatement cmd = con.prepareStatement("select * from ordine where idCliente = ?");
			cmd.setInt(1, cliente.getId());
			ResultSet res = cmd.executeQuery();
			while(res.next())
			{
				if(res.getInt(5) != 0)
				{
					Gioiello gioiello = getGioiello(res.getInt(5));
					ordini.add(new Ordine(res.getInt(1),res.getString(3),gioiello,res.getString(4),res.getString(8)));
				}
				else if(res.getInt(6) != 0)
				{
					Gioiello gioiello = getGioiello(res.getInt(6));
					ordini.add(new Ordine(res.getInt(1),res.getString(3),gioiello,res.getString(4),res.getString(8)));
				}
				else
				{
					ordini.add(new Ordine(res.getInt(1),res.getString(3),null,res.getString(4),res.getString(8)));
				}
			}
			
			cmd.close();
			res.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return ordini;
	}
}

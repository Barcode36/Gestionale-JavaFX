package gestioneDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import models.Anello;
import models.Bracciale;
import models.Cliente;
import models.Fattura;
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
	
	public void eliminaFattura(Fattura fattura)
	{
		try 
		{
			PreparedStatement cmd = con.prepareStatement("delete from fattura where idOrdine = ?");
			cmd.setInt(1, fattura.getIdFattura());
			cmd.executeUpdate();
			cmd.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void emettiFattura(Ordine ordine) throws SQLException
	{
		PreparedStatement cmd2 = con.prepareStatement("select distinct nome,cognome from cliente where idCliente = ?");
		cmd2.setInt(1, ordine.getIdCliente());
		ResultSet res = cmd2.executeQuery();
		
		String nomeCliente = null;
		while(res.next()) nomeCliente = res.getString(1)+" "+res.getString(2);
		
		PreparedStatement cmd = con.prepareStatement("insert into Fattura(idOrdine,importo,dataEmissione,idCliente,nomeCliente) values (?,?,?,?,?)");
		cmd.setInt(1, (int) ordine.getId());
		cmd.setDouble(2, ordine.getGioiello().getPrezzo());
		cmd.setString(3, LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		cmd.setInt(4, ordine.getIdCliente());
		cmd.setString(5, nomeCliente);
		cmd.executeUpdate();
		
		cmd.close();
		cmd2.close();
		res.close();
		//cuozm
	}
	
	public Fattura caricaFattura(Ordine ordine)
	{
		Fattura fattura = null;
		try 
		{
			PreparedStatement cmd = con.prepareStatement("select * from fattura where idOrdine = ?");
			cmd.setInt(1, ordine.getId());
			ResultSet res = cmd.executeQuery();
			
			while(res.next())
			{
				String dataEmissione = res.getString(1);
				float importo = res.getFloat(2);
				int idOrdine = res.getInt(3);
				int idCliente = res.getInt(4);
				String nomeCliente = res.getString(5);
				fattura = new Fattura(dataEmissione,importo,idOrdine,idCliente,nomeCliente);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return fattura;
	}
	
	public ArrayList<Fattura> caricaFatture()
	{
		ArrayList<Fattura> fatture = new ArrayList<Fattura>();
		
		try 
		{
			PreparedStatement cmd = con.prepareStatement("select * from fattura");
			ResultSet res = cmd.executeQuery();
			
			while(res.next())
			{
				String dataEmissione = res.getString(1);
				float importo = res.getFloat(2);
				int idOrdine = res.getInt(3);
				int idCliente = res.getInt(4);
				String nomeCliente = res.getString(5);
				fatture.add(new Fattura(dataEmissione,importo,idOrdine,idCliente,nomeCliente));
			}
			
			cmd.close();
			res.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return fatture;
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
	
	public void salvaGioiello(Gioiello gioiello) 
	{	
		try
		{
			if(gioiello instanceof Anello)
			{
				Anello a = (Anello)gioiello;
				PreparedStatement cmd = con.prepareStatement("insert into prodotto (prezzo,peso,materiale,genere,nomeGioiello,descrizione,nomeTabella) values(?,?,?,?,?,?,'anello')");
				cmd.setDouble(1, a.getPrezzo());
				cmd.setDouble(2, a.getPeso());
				cmd.setString(3, a.getMateriale().toString());
				cmd.setString(4, a.getGenere());
				cmd.setString(5, a.getNomeGioiello());
				cmd.setString(6, a.getDescrizione());
				cmd.executeUpdate();
				cmd.close();
				
				PreparedStatement cmd2 = con.prepareStatement("select MAX(idProdotto) from prodotto");
				ResultSet ultimoProdottoinserito = cmd2.executeQuery();
				
				int id = 0;
				while(ultimoProdottoinserito.next()) id = ultimoProdottoinserito.getInt(1);
				cmd2.close();
				ultimoProdottoinserito.close();
				
				PreparedStatement cmd3 = con.prepareStatement("insert into anello(idProdotto,raggio,pietra) values(?,?,?)");
				cmd3.setInt(1, id);
				cmd3.setDouble(2, a.getRaggio());
				cmd3.setBoolean(3, a.isPietra());
				cmd3.executeUpdate();
				cmd3.close();
				
			}
			else if(gioiello instanceof Bracciale)
			{
				Bracciale a = (Bracciale)gioiello;
				
				PreparedStatement cmd = con.prepareStatement("insert into prodotto (prezzo,peso,materiale,genere,nomeGioiello,descrizione,nomeTabella) values(?,?,?,?,?,?,'bracciale')");
				cmd.setDouble(1, a.getPrezzo());
				cmd.setDouble(2, a.getPeso());
				cmd.setString(3, a.getMateriale().toString());
				cmd.setString(4, a.getGenere());
				cmd.setString(5, a.getNomeGioiello());
				cmd.setString(6, a.getDescrizione());
				cmd.executeUpdate();
				cmd.close();
				
				PreparedStatement cmd2 = con.prepareStatement("select MAX(idProdotto) from prodotto");
				ResultSet ultimoProdottoinserito = cmd2.executeQuery();
				
				int id = 0;
				while(ultimoProdottoinserito.next()) id = ultimoProdottoinserito.getInt(1);
				cmd2.close();
				ultimoProdottoinserito.close();
				
				PreparedStatement cmd3 = con.prepareStatement("insert into bracciale(idProdotto,lunghezza,larghezza,spessore) values(?,?,?,?)");
				cmd3.setInt(1, id);
				cmd3.setDouble(2, a.getLunghezza());
				cmd3.setDouble(3, a.getLarghezza());
				cmd3.setDouble(4, a.getSpessore());
				cmd3.executeUpdate();
				cmd3.close();
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public int getNumUltimoGioiello()
	{
		int num = 0;
		
		try 
		{
			PreparedStatement cmd = con.prepareStatement("select MAX(idProdotto) from prodotto");
			ResultSet res = cmd.executeQuery();
			while(res.next()) num = res.getInt(1);
			res.close();
			cmd.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return num;
	}
	
	public Gioiello getGioiello(int id) 
	{
		try 
		{
			PreparedStatement cmd = con.prepareStatement("select nomeTabella from prodotto where idProdotto = ?");
			cmd.setInt(1, id);
			ResultSet res = cmd.executeQuery();
			String nomeTabella = null;
			
			while(res.next()) nomeTabella = res.getString(1);
			res.close();
			cmd.close();
			
			if(nomeTabella != null && nomeTabella.equals("anello"))
			{
				PreparedStatement cmd2 = con.prepareStatement("select * from prodotto inner join anello on anello.idProdotto = prodotto.idProdotto and anello.idProdotto = ?");
				cmd2.setInt(1, id);
		    	ResultSet res2 = cmd2.executeQuery();
		    	
		    	while (res2.next()) 
		    	{
				    int gioielloId = res2.getInt(1);
				    double prezzo = res2.getDouble(2);
				    double peso = res2.getDouble(3);
				    MATERIALE materiale = MATERIALE.valueOf(res2.getString(4));
				    String genere = res2.getString(5);
				    boolean venduto = res2.getBoolean(6);
				    String nomeGioiello = res2.getString(7);
				    String descrizione = res2.getString(9);
				    double raggio = res2.getDouble(11);
				    boolean pietra = res2.getBoolean(12);
				    Anello anello = new Anello(prezzo,peso,materiale,genere,venduto,raggio,pietra, nomeGioiello,descrizione);
				    anello.setId(gioielloId);
				    return anello;
		    	}
		    	
		    	cmd2.close();
		    	res2.close();
			}
			else if(nomeTabella != null && nomeTabella.equals("bracciale"))
			{
				PreparedStatement cmd2 = con.prepareStatement("select * from prodotto inner join bracciale on bracciale.idProdotto = prodotto.IdProdotto and bracciale.idProdotto = ?");
				cmd2.setInt(1, id);
		    	ResultSet res2 = cmd2.executeQuery();
		    	while(res2.next())
		    	{
		    		int gioielloId = res2.getInt(1);
				    double prezzo = res2.getDouble(2);
				    double peso = res2.getDouble(3);
				    MATERIALE materiale = MATERIALE.valueOf(res2.getString(4));
				    String genere = res2.getString(5);
				    boolean venduto = res2.getBoolean(6);
				    String nomeGioiello = res2.getString(7);
				    String descrizione = res2.getString(9);
				    double lunghezza = res2.getDouble(11);
				    double spessore = res2.getDouble(12);
				    double larghezza = res2.getDouble(13);
				    Bracciale bracciale =  new Bracciale(prezzo,peso,materiale,genere,venduto,lunghezza,spessore,larghezza,nomeGioiello,descrizione);
				    bracciale.setId(gioielloId);
				    return bracciale;
		    	}
		    	
		    	cmd2.close();
		    	res2.close();
			}
			
		} 
		catch (SQLException e) 
		{
			System.out.println("sono quì");
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Gioiello> caricaGioielli() 
	{
		ArrayList<Gioiello> gioielli = new ArrayList<Gioiello>();
		
	    try 
	    {
	    	PreparedStatement cmd = con.prepareStatement("select * from prodotto inner join anello on anello.idProdotto = prodotto.idProdotto");
	    	ResultSet res = cmd.executeQuery();
	    	while (res.next()) 
	    	{
			    int gioielloId = res.getInt(1);
			    double prezzo = res.getDouble(2);
			    double peso = res.getDouble(3);
			    MATERIALE materiale = MATERIALE.valueOf(res.getString(4));
			    String genere = res.getString(5);
			    boolean venduto = res.getBoolean(6);
			    String nomeGioiello = res.getString(7);
			    String descrizione = res.getString(9);
			    double raggio = res.getDouble(11);
			    boolean pietra = res.getBoolean(12);
			    Anello anello = new Anello(prezzo,peso,materiale,genere,venduto,raggio,pietra, nomeGioiello,descrizione);
			    anello.setId(gioielloId);
			    gioielli.add(anello);
	    	}
	    	
	    	PreparedStatement cmd2 = con.prepareStatement("select * from prodotto inner join bracciale on bracciale.idProdotto = prodotto.IdProdotto");
	    	ResultSet res2 = cmd2.executeQuery();
	    	while(res2.next())
	    	{
	    		int gioielloId = res2.getInt(1);
			    double prezzo = res2.getDouble(2);
			    double peso = res2.getDouble(3);
			    MATERIALE materiale = MATERIALE.valueOf(res2.getString(4));
			    String genere = res2.getString(5);
			    boolean venduto = res2.getBoolean(6);
			    String nomeGioiello = res2.getString(7);
			    String descrizione = res2.getString(9);
			    double lunghezza = res2.getDouble(11);
			    double spessore = res2.getDouble(12);
			    double larghezza = res2.getDouble(13);
			    Bracciale bracciale = new Bracciale(prezzo,peso,materiale,genere,venduto,lunghezza,spessore,larghezza,nomeGioiello,descrizione);
			    bracciale.setId(gioielloId);
			    gioielli.add(bracciale);
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
			PreparedStatement cmd = con.prepareStatement("select count(*) from prodotto");
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
	
	public void salvaOrdine(Ordine o, Cliente cliente)
	{
		try 
		{
			PreparedStatement cmd = con.prepareStatement("insert into ordine (idProdotto,dataEmissione,dataScadenza,tipologia,idCliente,descrizione) values (?,?,?,?,?,?)");
			cmd.setInt(1, o.getGioiello().getId());
			cmd.setString(2, o.getDataOrdine().toString());
			cmd.setString(3, o.getDataScadenza());
			cmd.setString(4, o.getTipologia());
			cmd.setInt(5, cliente.getId());
			cmd.setString(6, o.getDescrizione());
			cmd.executeUpdate();
			cmd.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
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
				PreparedStatement cmd = con.prepareStatement("update ordine set idProdotto = null where idProdotto = ?");
				cmd.setInt(1, gioiello.getId());
				cmd.executeUpdate();
				cmd.close();
				
				PreparedStatement cmd2 = con.prepareStatement("delete from anello where idProdotto = ?; delete from prodotto where idProdotto = ?");
				cmd2.setInt(1, gioiello.getId());
				cmd2.setInt(2, gioiello.getId());
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
				PreparedStatement cmd = con.prepareStatement("update ordine set idProdotto = null where idProdotto = ?");
				cmd.setLong(1, gioiello.getId());
				cmd.executeUpdate();
				cmd.close();
				
				PreparedStatement cmd2 = con.prepareStatement("delete from bracciale where idProdotto = ?; delete from prodotto where idProdotto = ?");
				cmd2.setLong(1, gioiello.getId());
				cmd2.setInt(2, gioiello.getId());
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
	
	public int getNumUltimoOrdine()
	{
		int num = 0;
		
		try 
		{
			PreparedStatement cmd = con.prepareStatement("select MAX(idOrdine) from ordine");
			ResultSet res = cmd.executeQuery();
			while(res.next()) num = res.getInt(1);
			cmd.close();
			res.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return num;
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
				//ordini.add(new Ordine(res.getInt(1),res.getString(3),gioiello,res.getString(4),res.getString(8)));
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
				Gioiello gioiello = getGioiello(res.getInt(2));
				Ordine ordine = new Ordine(res.getInt(1),res.getString(4),gioiello,res.getString(5),res.getString(7),res.getInt(6));
				ordine.setDataOrdine(res.getString(3));
				ordini.add(ordine);
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

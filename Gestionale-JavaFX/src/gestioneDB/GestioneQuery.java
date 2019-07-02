package gestioneDB;

import java.io.FileInputStream;
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
import models.Collana;
import models.Fattura;
import models.Gioiello;
import models.Immagine;
import models.MATERIALE;
import models.Ordine;
import models.Orecchino;

public class GestioneQuery
{
	public static final String anello = "Anello";
	public static final String bracciale = "Bracciale";
	public static final String orecchino = "Orecchino";
	public static final String collana = "Collana";
	
	private final String driver = "org.postgresql.Driver";
	private final String url = "jdbc:postgresql://localhost/Gioielleria";
	private final String databaseType = "postgres";
	private final String password = "3890498266";
	private Connection con; 

	public GestioneQuery()
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
	
	public void inserisciImmagine(FileInputStream immagine, Gioiello gioiello)
	{
		try 
		{
			PreparedStatement cmd = con.prepareStatement("insert into immagini(immagine,idProdotto) values(?,?)");
			cmd.setBinaryStream(1, immagine);
			cmd.setInt(2,gioiello.getId());
			cmd.executeUpdate();
			cmd.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Immagine> caricaImmagini(Gioiello gioiello)
	{
		ArrayList<Immagine> immagini = new ArrayList<Immagine>();
		
		try 
		{
			PreparedStatement cmd = con.prepareStatement("select immagine,idProdotto,idImmagine from immagini where idProdotto = ?");
			cmd.setInt(1, gioiello.getId());
			ResultSet res = cmd.executeQuery();
			
			while(res.next())
			{
				immagini.add(new Immagine(res.getBinaryStream(1),res.getInt(2),res.getInt(3)));
			}
			cmd.close();
			res.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return immagini;
	}
	
	public void eliminaImmagine(Immagine immagine)
	{
		try 
		{
			PreparedStatement cmd = con.prepareStatement("delete from immagini where idImmagine = ?");
			cmd.setInt(1, immagine.getIdImmagine());
			cmd.executeUpdate();
			cmd.close();
		} 
		catch (SQLException e) 
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
		if(ordine.getGioiello() != null) cmd.setDouble(2, ordine.getGioiello().getPrezzo());
		else cmd.setDouble(2, 0);
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
	
	public void popolaGioielli()
	{
		for(int i = 0; i < 800; i++)
		{
			if(i < 250)
			{
				salvaGioiello(new Anello(15+i,2,MATERIALE.ORO_GIALLO,"Maschile",false,3+i,true,"Anello"+i,""));
			}
			else
			{
				salvaGioiello(new Collana(23,23,MATERIALE.ACCIAIO,"Maschile",false,"Commento","Collana"+i,25,true,25));
			}
		}
	}
	
	public void popolaClienti()
	{
		for(int i = 0; i < 10; i++)
		{
			salvaClienti(new Cliente("ClienteNome"+i,"ClienteCognome"+i,"36554215448"+i));
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
				
				PreparedStatement cmd3 = con.prepareStatement("insert into anello(idProdotto,raggio,pietra) values(?,?,?)");
				cmd3.setInt(1, getNumUltimoGioiello());
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
				
				PreparedStatement cmd3 = con.prepareStatement("insert into bracciale(idProdotto,lunghezza,larghezza,spessore) values(?,?,?,?)");
				cmd3.setInt(1, getNumUltimoGioiello());
				cmd3.setDouble(2, a.getLunghezza());
				cmd3.setDouble(3, a.getLarghezza());
				cmd3.setDouble(4, a.getSpessore());
				cmd3.executeUpdate();
				cmd3.close();
				
			}
			
			else if(gioiello instanceof Orecchino)
			{
				Orecchino a = (Orecchino)gioiello;
				
				PreparedStatement cmd = con.prepareStatement("insert into prodotto (prezzo,peso,materiale,genere,nomeGioiello,descrizione,nomeTabella) values(?,?,?,?,?,?,'orecchino')");
				cmd.setDouble(1, a.getPrezzo());
				cmd.setDouble(2, a.getPeso());
				cmd.setString(3, a.getMateriale().toString());
				cmd.setString(4, a.getGenere());
				cmd.setString(5, a.getNomeGioiello());
				cmd.setString(6, a.getDescrizione());
				cmd.executeUpdate();
				cmd.close();
				
				PreparedStatement cmd2 = con.prepareStatement("insert into orecchino (idProdotto,altezza,tipologiaOrecchino,spessore) values (?,?,?,?)");
				cmd2.setInt(1, getNumUltimoGioiello());
				cmd2.setDouble(2,a.getAltezza());
				cmd2.setString(3, a.getTipologiaOrecchino());
				cmd2.setDouble(4, a.getSpessore());
				cmd2.executeUpdate();
				cmd2.close();
			}
			
			else if(gioiello instanceof Collana)
			{
				Collana a = (Collana)gioiello;
				
				PreparedStatement cmd = con.prepareStatement("insert into prodotto (prezzo,peso,materiale,genere,nomeGioiello,descrizione,nomeTabella) values(?,?,?,?,?,?,'collana')");
				cmd.setDouble(1, a.getPrezzo());
				cmd.setDouble(2, a.getPeso());
				cmd.setString(3, a.getMateriale().toString());
				cmd.setString(4, a.getGenere());
				cmd.setString(5, a.getNomeGioiello());
				cmd.setString(6, a.getDescrizione());
				cmd.executeUpdate();
				cmd.close();
				
				PreparedStatement cmd2 = con.prepareStatement("insert into collana (idProdotto,lunghezza,spessore,ciondolo) values (?,?,?,?)");
				cmd2.setInt(1, getNumUltimoGioiello());
				cmd2.setDouble(2, a.getLunghezza());
				cmd2.setDouble(3, a.getSpessore());
				cmd2.setBoolean(4, a.isCiondolo());
				cmd2.executeUpdate();
				cmd2.close();
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
		Gioiello gioiello = null;
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
		    	ArrayList<Gioiello> gi = costruisciGioielli(res2, anello);
		    	if(gi.size() > 0) gioiello = gi.get(0);
		    	cmd2.close();
		    	res2.close();
			}
			else if(nomeTabella != null && nomeTabella.equals("bracciale"))
			{
				PreparedStatement cmd2 = con.prepareStatement("select * from prodotto inner join bracciale on bracciale.idProdotto = prodotto.IdProdotto and bracciale.idProdotto = ?");
				cmd2.setInt(1, id);
		    	ResultSet res2 = cmd2.executeQuery();
		    	ArrayList<Gioiello> gi = costruisciGioielli(res2, bracciale);
		    	if(gi.size() > 0) gioiello = gi.get(0);
		    	cmd2.close();
		    	res2.close();
			}
			
			else if(nomeTabella != null && nomeTabella.equals("orecchino"))
			{
				PreparedStatement cmd3 = con.prepareStatement("select * from prodotto inner join orecchino on orecchino.idProdotto = prodotto.idProdotto");
		    	ResultSet res3 = cmd3.executeQuery();
		    	ArrayList<Gioiello> gi = costruisciGioielli(res3, orecchino);
		    	if(gi.size() > 0) gioiello = gi.get(0);		    	
		    	cmd3.close();
		    	res3.close();
			}
			
			else if(nomeTabella != null && nomeTabella.equals("collana"))
			{
				PreparedStatement cmd4 = con.prepareStatement("select * from prodotto inner join collana on collana.idProdotto = prodotto.idProdotto");
		    	ResultSet res4 = cmd4.executeQuery();
		    	ArrayList<Gioiello> gi = costruisciGioielli(res4, collana);
		    	if(gi.size() > 0) gioiello = gi.get(0);
		    	cmd4.close();
		    	res4.close();
			}
			
		} 
		catch (SQLException e) 
		{
			System.out.println("sono quì");
			e.printStackTrace();
		}
		
		return gioiello;
	}

	public ArrayList<Gioiello> caricaGioielli() 
	{
		ArrayList<Gioiello> gioielli = new ArrayList<Gioiello>();
	    try 
	    {
	    	PreparedStatement cmd = con.prepareStatement("select * from prodotto inner join anello on anello.idProdotto = prodotto.idProdotto");
	    	ResultSet res = cmd.executeQuery();
	    	gioielli.addAll(costruisciGioielli(res, anello));
	    	res.close();
	    	cmd.close();
	    	
	    	PreparedStatement cmd2 = con.prepareStatement("select * from prodotto inner join bracciale on bracciale.idProdotto = prodotto.IdProdotto");
	    	ResultSet res2 = cmd2.executeQuery();
	    	gioielli.addAll(costruisciGioielli(res2, bracciale));
	    	res2.close();
	    	cmd2.close();
	    	
	    	PreparedStatement cmd3 = con.prepareStatement("select * from prodotto inner join orecchino on orecchino.idProdotto = prodotto.idProdotto");
	    	ResultSet res3 = cmd3.executeQuery();
	    	gioielli.addAll(costruisciGioielli(res3, orecchino));
	    	res3.close();
	    	cmd3.close();
	    	
	    	PreparedStatement cmd4 = con.prepareStatement("select * from prodotto inner join collana on collana.idProdotto = prodotto.idProdotto");
	    	ResultSet res4 = cmd4.executeQuery();
	    	gioielli.addAll(costruisciGioielli(res4, collana));
	    	res4.close();
	    	cmd4.close();
	    	
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
	
	public int getFattureNelDB()
	{
		int fatture = 0;
		
		try 
		{
			PreparedStatement cmd = con.prepareStatement("select count(*) from fattura");
			ResultSet res = cmd.executeQuery();
			while(res.next()) fatture+=res.getInt(1);
			
			cmd.close();
			res.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return fatture;
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
		try 
		{
			PreparedStatement cmd3 = con.prepareStatement("delete from Immagini where idProdotto = ?");
			cmd3.setInt(1, gioiello.getId());
			cmd3.executeUpdate();
			cmd3.close();
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		
		
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
		
		if(gioiello instanceof Orecchino)
		{
			try
			{
				PreparedStatement cmd = con.prepareStatement("update ordine set idProdotto = null where idProdotto = ?");
				cmd.setLong(1, gioiello.getId());
				cmd.executeUpdate();
				cmd.close();
				
				PreparedStatement cmd4 = con.prepareStatement("delete from orecchino where idProdotto = ?; delete from prodotto where idProdotto = ?");
				cmd4.setLong(1, gioiello.getId());
				cmd4.setLong(1, gioiello.getId());
				cmd4.executeUpdate();
				cmd4.close();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(gioiello instanceof Collana)
		{
			try
			{
				PreparedStatement cmd = con.prepareStatement("update ordine set idProdotto = null where idProdotto = ?");
				cmd.setLong(1, gioiello.getId());
				cmd.executeUpdate();
				cmd.close();
				
				PreparedStatement cmd2 = con.prepareStatement("delete from collana where idProdotto = ?; delete from prodotto where idProdotto = ?");
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
	
	public void modificaDatiGioiello(Gioiello gioiello)
	{
		try 
		{
			PreparedStatement cmd = con.prepareStatement("update prodotto set prezzo = ?, peso = ?, materiale = ?, genere = ?, nomeGioiello = ?, descrizione = ? where idProdotto = ?");
			cmd.setDouble(1, gioiello.getPrezzo());
			cmd.setDouble(2, gioiello.getPeso());
			cmd.setString(3, gioiello.getMateriale().toString());
			cmd.setString(4, gioiello.getGenere());
			cmd.setString(5, gioiello.getNomeGioiello());
			cmd.setString(6, gioiello.getDescrizione());
			cmd.setInt(7, gioiello.getId());
			cmd.executeUpdate();
			cmd.close();
			
			if(gioiello instanceof Anello)
			{
				PreparedStatement cmd2 = con.prepareStatement("update anello set raggio = ?, pietra = ? where idProdotto = ?");
				cmd2.setDouble(1, ((Anello) gioiello).getRaggio());
				cmd2.setBoolean(2, ((Anello)gioiello).isPietra());
				cmd2.setInt(3, gioiello.getId());
				cmd2.executeUpdate();
				cmd2.close();
			}
			
			if(gioiello instanceof Bracciale)
			{
				PreparedStatement cmd2 = con.prepareStatement("update bracciale set lunghezza = ?, spessore = ?, larghezza = ? where idProdotto = ?");
				cmd2.setDouble(1, ((Bracciale)gioiello).getLunghezza());
				cmd2.setDouble(2, ((Bracciale)gioiello).getSpessore());
				cmd2.setDouble(3, ((Bracciale)gioiello).getLarghezza());
				cmd2.setInt(4,gioiello.getId());
				cmd2.executeUpdate();
				cmd2.close();
			}
			
			if(gioiello instanceof Orecchino)
			{
				PreparedStatement cmd2 = con.prepareStatement("update orecchino set spessore = ?, altezza = ?, tipologiaOrecchino = ?");
				cmd2.setDouble(1,((Orecchino)gioiello).getSpessore());
				cmd2.setDouble(2, ((Orecchino)gioiello).getAltezza());
				cmd2.setString(3, ((Orecchino)gioiello).getTipologiaOrecchino());
				cmd2.executeUpdate();
				cmd2.close();
			}
			
			if(gioiello instanceof Collana)
			{
				PreparedStatement cmd2 = con.prepareStatement("update collana set lunghezza = ?, spessore = ?, ciondolo = ?");
				cmd2.setDouble(1, ((Collana)gioiello).getLunghezza());
				cmd2.setDouble(2, ((Collana)gioiello).getSpessore());
				cmd2.setBoolean(3, ((Collana)gioiello).isCiondolo());
				cmd2.executeUpdate();
				cmd2.close();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Gioiello> getGioielli(String query, String tipologia)
	{
		ArrayList<Gioiello> gioielli = new ArrayList<Gioiello>();
		
		if(tipologia.equals(bracciale))
		{
			try 
			{
				PreparedStatement cmd2 = con.prepareStatement(query);
				ResultSet res2 = cmd2.executeQuery();
				gioielli.addAll(costruisciGioielli(res2, bracciale));
		    	cmd2.close();
		    	res2.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(tipologia.equals(anello))
		{
	    	try 
			{
	    		PreparedStatement cmd = con.prepareStatement(query);
		    	ResultSet res = cmd.executeQuery();
		    	gioielli.addAll(costruisciGioielli(res, anello));
		    	cmd.close();
		    	res.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(tipologia.equals(orecchino))
		{
			try 
			{
				PreparedStatement cmd3 = con.prepareStatement(query);
				ResultSet res3 = cmd3.executeQuery();
				gioielli.addAll(costruisciGioielli(res3, orecchino));
				cmd3.close();
				res3.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(tipologia.equals(collana))
		{
			try 
			{
				PreparedStatement cmd4 = con.prepareStatement(query);
				ResultSet res4 = cmd4.executeQuery();
				gioielli.addAll(costruisciGioielli(res4, collana));
				cmd4.close();
				res4.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		return gioielli;
	}
	
	private ArrayList<Gioiello> costruisciGioielli(ResultSet res, String tipologia)
	{
		ArrayList<Gioiello> gioielli = new ArrayList<Gioiello>();
		
		if(tipologia.equals(anello))
		{
			try 
			{
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
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(tipologia.equals(bracciale))
		{
			try 
			{
				//System.out.println("entrato nel bracciale");
		    	while(res.next())
		    	{
		    		int gioielloId = res.getInt(1);
				    double prezzo = res.getDouble(2);
				    double peso = res.getDouble(3);
				    MATERIALE materiale = MATERIALE.valueOf(res.getString(4));
				    String genere = res.getString(5);
				    boolean venduto = res.getBoolean(6);
				    String nomeGioiello = res.getString(7);
				    String descrizione = res.getString(9);
				    double lunghezza = res.getDouble(11);
				    double spessore = res.getDouble(12);
				    double larghezza = res.getDouble(13);
				    Bracciale bracciale = new Bracciale(prezzo,peso,materiale,genere,venduto,lunghezza,spessore,larghezza,nomeGioiello,descrizione);
				    bracciale.setId(gioielloId);
				    gioielli.add(bracciale);
		    	}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(tipologia.equals(collana))
		{
			try 
			{
				while(res.next())
				{
					int gioielloId = res.getInt(1);
		    		double prezzo = res.getDouble(2);
				    double peso = res.getDouble(3);
				    MATERIALE materiale = MATERIALE.valueOf(res.getString(4));
				    String genere = res.getString(5);
				    boolean venduto = res.getBoolean(6);
				    String nomeGioiello = res.getString(7);
				    String descrizione = res.getString(9);
				    double lunghezza = res.getDouble(11);
				    double spessore = res.getDouble(12);
				    boolean ciondolo = res.getBoolean(13);
				    
				    Collana collana = new Collana(prezzo,peso,materiale,genere,venduto,descrizione,nomeGioiello,lunghezza,ciondolo,spessore);
				    collana.setId(gioielloId);
				    gioielli.add(collana);
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		if(tipologia.equals(orecchino))
		{
			try 
			{
				while(res.next())
				{
					int gioielloId = res.getInt(1);
		    		double prezzo = res.getDouble(2);
				    double peso = res.getDouble(3);
				    MATERIALE materiale = MATERIALE.valueOf(res.getString(4));
				    String genere = res.getString(5);
				    boolean venduto = res.getBoolean(6);
				    String nomeGioiello = res.getString(7);
				    String descrizione = res.getString(9);
				    double spessore = res.getDouble(11);
				    double altezza = res.getDouble(12);
				    String tipologiaOrecchino = res.getString(13);
				    Orecchino orecchino = new Orecchino(prezzo,peso,materiale,genere,venduto,descrizione,nomeGioiello,spessore,altezza,tipologiaOrecchino);
				    orecchino.setId(gioielloId);
				    gioielli.add(orecchino);
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		return gioielli;
	}
	
	public void modificaCliente(Cliente cliente)
	{
		try 
		{
			PreparedStatement cmd = con.prepareStatement("update cliente set nome = ?, cognome = ?, numeroTelefono = ? where idCliente = ?");
			cmd.setString(1, cliente.getNomeCliente());
			cmd.setString(2, cliente.getCognomeCliente());
			cmd.setString(3, cliente.getNumeroTelefono());
			cmd.setInt(4, cliente.getId());
			cmd.executeUpdate();
			cmd.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}

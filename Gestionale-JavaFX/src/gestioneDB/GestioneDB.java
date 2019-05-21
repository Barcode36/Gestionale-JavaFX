package gestioneDB;

import java.util.ArrayList;
import logica.Gioiello;

public interface GestioneDB
{
	public void save(Gioiello gioiello);//, int tipo);
	public Gioiello findByPrimaryKey(int id);
	public int findIdByElements(Gioiello gioiello);	
	public ArrayList<Gioiello> caricaGioielli();
	public void update(Gioiello gioiello, int tipo);
	public void delete(int id);
	public int getGioielliNelDB();
	public int getClientiNelDB();
}

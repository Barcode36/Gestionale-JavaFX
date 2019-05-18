package logica;

public class Anello extends Gioiello
{
	private double raggio;
	boolean pietra;
	public Anello(int id, double prezzo, double peso, MATERIALE materiale, String genere,boolean venduto, double raggio, boolean pietra, String nomeGioiello,String descrizione)
	{
		super(id,prezzo,peso,materiale,genere,venduto,descrizione);
		this.raggio = raggio;
		this.pietra = pietra;
		this.nomeGioiello = nomeGioiello;
	}
	
	public double getRaggio() { return raggio; }
	public void setRaggio(double raggio) { this.raggio = raggio; }
	
	public boolean isPietra() { return pietra; }
	public void setPietra(boolean pietra) { this.pietra = pietra; }
	
	@Override
	public String toString() { return this.nomeGioiello; }
	
	@Override
	public String save()
	{
		return "Anello;" + super.getId() + ";" + super.getPrezzo() + ";" + super.getPeso() + ";" + super.getMateriale() + ";" + super.getGenere() + ";" + raggio + ";" + pietra + ";" + nomeGioiello;
	}
}

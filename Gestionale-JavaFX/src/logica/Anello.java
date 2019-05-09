package logica;

public class Anello extends Gioiello
{
	private double raggio;
	boolean pietra;
	public Anello(String id, double prezzo, double peso, MATERIALE materiale, String genere, double raggio,boolean pietra)
	{
		super(id,prezzo,peso,materiale,genere);
		this.tipoGioiello = "Anello";
		this.raggio = raggio;
		this.pietra = pietra;
	}
	
	public double getRaggio() { return raggio; }
	public void setRaggio(double raggio) { this.raggio = raggio; }
	
	public boolean isPietra() { return pietra; }
	public void setPietra(boolean pietra) { this.pietra = pietra; }
	
	@Override
	public String toString()
	{
		return "Anello;" + super.getId() + ";" + super.getPrezzo() + ";" + super.getPeso() + ";" + super.getMateriale() + ";" + super.getGenere() + ";" + raggio + ";" + pietra;
	}
}

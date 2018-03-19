package partie3;

public class Plats {
	
	private String plat;
	private double prix;
	
	public Plats(String plat, double prix){
		
		this.plat = plat;
		this.prix = prix;
	}
	

	public String getPlat() {
		return plat;
	}

	public void setPlat(String plat) {
		this.plat = plat;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

}

package hu.kapi.fitdiary.model;

public class Food {
	int ID;
	String name;
	int unit; //0-g, 1-mg, 2-l, 3-dl, 4-cl, 5-ml, 6-db 
	double quantity;
	double fat;
	double sugar;
	double energy;
	double carbohidrate;
	double protein;
	int daily_category; //0-reggeli, 1-ebed, 3-vacsora, 4-nasi, 5-egyeb 
	int resource_category; //0-feherjeforras, 1-szenhidratforras, 2-vegyes, 3-zoldseg, 4-gyumolcs, 5-egyeb
	
	public Food(int iD, String name, int unit, double quantity, double fat,
			double sugar, double energy, double carbohidrate, double protein, int daily_category,
			int resource_category) {
		super();
		ID = iD;
		this.name = name;
		this.unit = unit;
		this.quantity = quantity;
		this.fat = fat;
		this.sugar = sugar;
		this.energy = energy;
		this.carbohidrate = carbohidrate;
		this.protein = protein;
		this.daily_category = daily_category;
		this.resource_category = resource_category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getFat() {
		return fat;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}
	public double getSugar() {
		return sugar;
	}
	public void setSugar(double sugar) {
		this.sugar = sugar;
	}
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double energy) {
		this.energy = energy;
	}
	
	public double getCarbohidrate() {
		return carbohidrate;
	}
	public void setCarbohidrate(double carbohidrate) {
		this.carbohidrate = carbohidrate;
	}
	
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public int getDaily_category() {
		return daily_category;
	}
	public void setDaily_category(int daily_category) {
		this.daily_category = daily_category;
	}
	public int getResource_category() {
		return resource_category;
	}
	public void setResource_category(int resource_category) {
		this.resource_category = resource_category;
	}
	public int getID() {
		return ID;
	}
	@Override
	public String toString() {
		return "Food [ID=" + ID + ", name=" + name + ", unit=" + unit
				+ ", quantity=" + quantity + ", fat=" + fat + ", sugar="
				+ sugar + ", energy=" + energy + ", daily_category="
				+ daily_category + ", resource_category=" + resource_category
				+ "]";
	}
	
}

package it.polito.tdp.food.model;

public class Adiacenza {
	
	private Food f1;
	private Food f2;
	private double peso;
	/**
	 * @param f1
	 * @param f2
	 * @param peso 
	 * @param condimenti
	 */
	public Adiacenza(Food f1, Food f2, double peso) {
		super();
		this.f1 = f1;
		this.f2 = f2;
		this.peso = peso;
	}
	/**
	 * @return the f1
	 */
	public Food getF1() {
		return f1;
	}
	/**
	 * @param f1 the f1 to set
	 */
	public void setF1(Food f1) {
		this.f1 = f1;
	}
	/**
	 * @return the f2
	 */
	public Food getF2() {
		return f2;
	}
	/**
	 * @param f2 the f2 to set
	 */
	public void setF2(Food f2) {
		this.f2 = f2;
	}
	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}
	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((f1 == null) ? 0 : f1.hashCode());
		result = prime * result + ((f2 == null) ? 0 : f2.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adiacenza other = (Adiacenza) obj;
		if (f1 == null) {
			if (other.f1 != null)
				return false;
		} else if (!f1.equals(other.f1))
			return false;
		if (f2 == null) {
			if (other.f2 != null)
				return false;
		} else if (!f2.equals(other.f2))
			return false;
		return true;
	}
	
}

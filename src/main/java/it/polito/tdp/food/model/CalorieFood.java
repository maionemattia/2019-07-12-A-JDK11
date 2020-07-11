package it.polito.tdp.food.model;

public class CalorieFood implements Comparable<CalorieFood>{
	
	private Food food;
	private Double calorie;
	/**
	 * @param food
	 * @param calorie
	 */
	public CalorieFood(Food food, double calorie) {
		super();
		this.food = food;
		this.calorie = calorie;
	}
	/**
	 * @return the food
	 */
	public Food getFood() {
		return food;
	}
	/**
	 * @param food the food to set
	 */
	public void setFood(Food food) {
		this.food = food;
	}
	/**
	 * @return the calorie
	 */
	public double getCalorie() {
		return calorie;
	}
	/**
	 * @param calorie the calorie to set
	 */
	public void setCalorie(double calorie) {
		this.calorie = calorie;
	}
	@Override
	public int compareTo(CalorieFood o) {
		return -this.calorie.compareTo(o.calorie);
	}
	@Override
	public String toString() {
		return "CalorieFood [food=" + food + ", calorie=" + calorie + "]";
	}
	
	

}

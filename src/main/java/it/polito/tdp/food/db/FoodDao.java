package it.polito.tdp.food.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.food.model.Adiacenza;
import it.polito.tdp.food.model.Condiment;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Portion;

public class FoodDao {
	public List<Food> listAllFoods(Map<Integer,Food> idMapFood){
		String sql = "SELECT * FROM food" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Food> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					if(!idMapFood.containsKey(res.getInt("food_code"))) {
						Food food = new Food(res.getInt("food_code"),res.getString("display_name"));
						list.add(food);
						idMapFood.put(res.getInt("food_code"), food);
					}
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Condiment> listAllCondiments(Map<Integer, Condiment> idMapCondiment){
		String sql = "SELECT * FROM condiment" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Condiment> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					Condiment c = new Condiment(res.getInt("condiment_code"),
							res.getString("display_name"),
							res.getDouble("condiment_calories"), 
							res.getDouble("condiment_saturated_fats")
							);
					list.add(c);
					idMapCondiment.put(res.getInt("condiment_code"), c);
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Portion> listAllPortions(){
		String sql = "SELECT * FROM portion" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Portion> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Portion(res.getInt("portion_id"),
							res.getDouble("portion_amount"),
							res.getString("portion_display_name"), 
							res.getDouble("calories"),
							res.getDouble("saturated_fats"),
							res.getInt("food_code")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	public List<Food> listaCibi(int cod,Map<Integer, Food> idMapFood){
		String sql = "SELECT food_code " + 
				"FROM portion " + 
				"GROUP BY food_code " + 
				"HAVING COUNT(*) = ? " + 
				"ORDER BY COUNT(*)";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, cod);
			
			List<Food> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(idMapFood.get(res.getInt("food_code")));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Adiacenza> listaAdiacenze(Map<Integer, Food> idMapFood, int cod){
		String sql = "SELECT f1.food_code as c1, f2.food_code as c2, AVG(c.condiment_calories) as avg " + 
				"FROM food_condiment f1, food_condiment f2, condiment c " + 
				"WHERE f1.id<>f2.id AND f1.condiment_code = f2.condiment_code AND f1.condiment_code = c.condiment_code AND (f1.food_code) IN " + 
				"(SELECT food_code " + 
				"FROM portion " + 
				"GROUP BY food_code " + 
				"HAVING COUNT(*) = ?) " + 
				"AND (f2.food_code) IN " + 
				"(SELECT food_code " + 
				"FROM portion " + 
				"GROUP BY food_code " + 
				"HAVING COUNT(*) = ?) "+
				"GROUP BY c1,c2";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, cod);
			st.setInt(2, cod);
			
			List<Adiacenza> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					Adiacenza adiacenza = new Adiacenza(idMapFood.get(res.getInt("c1")),idMapFood.get(res.getInt("c2")),res.getDouble("avg"));
				//	System.out.println("Adiacenza trovata");
					list.add(adiacenza);
				}catch (Throwable t) {
					t.printStackTrace();
				}
			}
			conn.close();
			//System.out.println(list.size());
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
		
	
}

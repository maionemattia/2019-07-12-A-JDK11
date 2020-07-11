package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	SimpleWeightedGraph<Food, DefaultWeightedEdge> grafo;
	FoodDao dao;
	private List<Food> listaFood;
	private Map<Integer,Food> idMapFood;
	private Map<Integer, Condiment> idMapCondiment;
	private List<Adiacenza> listaAdiacenze;
	
	public Model() {
		dao = new FoodDao();
		listaFood = new ArrayList<>();
		idMapFood = new HashMap<>();
		idMapCondiment = new HashMap<>();
		listaAdiacenze = new ArrayList<>();
	}
	
	public void creaGrafo(int cod) {
		grafo = new SimpleWeightedGraph<Food, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		dao.listAllFoods(idMapFood);
		dao.listAllCondiments(idMapCondiment);
		//System.out.println("Ho lanciato il dao e la mappaCondimenti contiene #elemeti: "+idMapCondiment.size());
		listaFood = dao.listaCibi(cod, idMapFood);
		//System.out.println("La lista cibi ha #elemtenti "+listaFood.size());
		Graphs.addAllVertices(this.grafo, listaFood);
		System.out.format("Grafico creato con %d vertici", this.grafo.vertexSet().size());
		this.listaAdiacenze = dao.listaAdiacenze(idMapFood,cod);
		for(Adiacenza a : this.listaAdiacenze) {
			Graphs.addEdgeWithVertices(this.grafo, a.getF1(), a.getF2(), a.getPeso());
		}
		System.out.format("\nGrafico creato con %d archi", this.grafo.edgeSet().size());
//		for(Food f : this.grafo.vertexSet()) {
//			System.out.println("Vertice : "+f.getDisplay_name()+" ha "+Graphs.neighborListOf(this.grafo,f).size()+" vicini");
//		}
//		for(Adiacenza a : this.listaAdiacenze) {
//			System.out.println(a.getF1().getDisplay_name()+" "+a.getF2().getDisplay_name()+" "+a.getPeso()+"\n");
//		}

	}
	
	public List<Food> getFood(){
		Collections.sort(listaFood);
		return listaFood;
	}
	
	public List<CalorieFood> getFoodCalorie(Food food){
		
		List<CalorieFood> calorie = new ArrayList<>();
		List<Food> vicini = Graphs.neighborListOf(this.grafo, food);
		//System.out.println(vicini.size());
		for(Food f : vicini) {
			CalorieFood c = new CalorieFood(f,this.grafo.getEdgeWeight(this.grafo.getEdge(food, f)));
			calorie.add(c);
		}
		//System.out.println(calorie.size());
		Collections.sort(calorie);
		return calorie;
	}

}

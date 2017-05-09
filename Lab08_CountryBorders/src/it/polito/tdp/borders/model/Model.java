package it.polito.tdp.borders.model;

import java.util.*;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;


public class Model {
	
	private UndirectedGraph<Country, Border> grafo;
	
	BordersDAO dao;
	private List<Country> stati;
	
	public Model() {
	 dao = new BordersDAO();
	 stati = new ArrayList<Country>(); 
	 grafo = new SimpleGraph<Country, Border>(Border.class);
	}
	
	public Map<Country, Integer> calcolaConfini(int anno){
		this.createGraph(anno);
		Map<Country,Integer> numeroConfini = this.getCountryCounts();
//		String result = "";
//		for(Country c : grafo.vertexSet()){
//			result += c.getStateAbb() +c.getStatiConfinanti().size() +"\n";
//		}
		return numeroConfini;
	}

	public void createGraph(int anno) {
		List<Border> coppie = dao.getCountryPairs(anno);
		for(Border b : coppie){
			if(!grafo.containsVertex(b.getC1())){
				grafo.addVertex(dao.settaStato(b.getC1()));}
				if(!grafo.containsVertex(b.getC2())){
					grafo.addVertex(dao.settaStato(b.getC2()));}
				
		grafo.addEdge(b.getC1(), b.getC2(),b);
		b.getC1().aggiungiConfine(b.getC2());
		b.getC2().aggiungiConfine(b.getC1());
		
		}
//		for(Border b : coppie){
//			grafo.addEdge(b.getC1(), b.getC2(), b);
//			
//		}
		System.out.println(grafo);
		
	}

	public List<Country> getCountries() {
		stati = dao.loadAllCountries();
		return stati;
	}

	public Map<Country, Integer> getCountryCounts2() {
		Map<Country,Integer> numeroConfini = new HashMap<Country,Integer>();
		for(Country vertice : grafo.vertexSet() ){
			numeroConfini.put(vertice, vertice.getStatiConfinanti().size());
		}
		return numeroConfini;
	}
	public Map<Country, Integer> getCountryCounts() {
		Map<Country,Integer> numeroConfini = new HashMap<Country,Integer>();
		for(Country vertice : grafo.vertexSet()){
		numeroConfini.put(vertice,grafo.degreeOf(vertice));}
		return numeroConfini;
	}



	public Object getNumberOfConnectedComponents() {
	 	ConnectivityInspector <Country,Border> connessioni = new ConnectivityInspector <Country,Border> (grafo);
		return connessioni.connectedSets().size();
	}
}


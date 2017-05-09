package it.polito.tdp.borders.model;

import java.util.*;

public class Country {
	
	private String stateAbb;
	private int cCode;
	private String stateNme;
	private List<Country> statiConfinanti;
	
	
	public Country(String stateAbb, int cCode, String stateNme) {
		super();
		this.stateAbb = stateAbb;
		this.cCode = cCode;
		this.stateNme = stateNme;
		this.statiConfinanti = new ArrayList<Country>();
	}


	public String getStateAbb() {
		return stateAbb;
	}


	public void setStateAbb(String stateAbb) {
		stateAbb = stateAbb;
	}


	public int getCCode() {
		return cCode;
	}


	public void setCCode(int cCode) {
		cCode = cCode;
	}


	public String getStateNme() {
		return stateNme;
	}


	public void setStateNme(String stateNme) {
		stateNme = stateNme;
	}


	@Override
	public String toString() {
		return stateAbb ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stateAbb == null) ? 0 : stateAbb.hashCode());
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
		Country other = (Country) obj;
		if (stateAbb == null) {
			if (other.stateAbb != null)
				return false;
		} else if (!stateAbb.equals(other.stateAbb))
			return false;
		return true;
	}


	public List<Country> getStatiConfinanti() {
		return statiConfinanti;
	}


	public void setStatiConfinanti(List<Country> statiConfinanti) {
		this.statiConfinanti = statiConfinanti;
	}
	
	public void aggiungiConfine(Country statoConfinante){
		if(!statiConfinanti.contains(statoConfinante)){
			statiConfinanti.add(statoConfinante);
		}
	}
	
	
}

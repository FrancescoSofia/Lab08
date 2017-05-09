package it.polito.tdp.borders.model;

public class Border {
	
	private int cod;
	private Country c1;
	private Country c2;
	private int year;
	
	



	public Border(int cod, Country c1, Country c2,int year) {
		super();
		this.cod = cod;
		this.c1 = c1;
		this.c2 = c2;
		this.year = year;
	}



	public int getCod() {
		return cod;
	}



	public void setCod(int cod) {
		this.cod = cod;
	}



	public Country getC1() {
		return c1;
	}



	public void setC1(Country c1) {
		this.c1 = c1;
	}



	public Country getC2() {
		return c2;
	}



	public void setC2(Country c2) {
		this.c2 = c2;
	}

	@Override
	public String toString() {
		return "Border [cod=" + cod + ", c1=" + c1 + ", c2=" + c2 + ", year=" + year + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c1 == null) ? 0 : c1.hashCode());
		result = prime * result + ((c2 == null) ? 0 : c2.hashCode());
		result = prime * result + cod;
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
		Border other = (Border) obj;
		if (c1 == null) {
			if (other.c1 != null)
				return false;
		} else if (!c1.equals(other.c1))
			return false;
		if (c2 == null) {
			if (other.c2 != null)
				return false;
		} else if (!c2.equals(other.c2))
			return false;
		if (cod != other.cod)
			return false;
		return true;
	}



	public int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
	}

}

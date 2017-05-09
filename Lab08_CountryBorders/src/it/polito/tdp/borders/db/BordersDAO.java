package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode,StateAbb,StateNme " + "FROM country " + "ORDER BY StateAbb ";
		
		 List<Country> stati = new ArrayList<Country>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				stati.add(new Country(rs.getString("StateAbb"),rs.getInt("ccode"), rs.getString("StateNme")));
			}

			conn.close();
			return stati;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- loadAllCountries");
			throw new RuntimeException("Database Error");
		}
	}

	public List<Border> getCountryPairs(int anno) {
		List<Border> confini = new ArrayList<Border>();
		
		String sql = "SELECT dyad,state1no,state1ab,state2no,state2ab,year " + "FROM contiguity " + "WHERE year <= ? && conttype =1 " ;
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c1 = new Country(rs.getString("state1ab"),0,null);
				Country c2 = new Country(rs.getString("state2ab"),0,null);
				confini.add(new Border(rs.getInt("dyad"),c1,c2,rs.getInt("year")));
						}

			conn.close();
			return confini;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- coppie");
			throw new RuntimeException("Database Error");
		}
	}

	public Country settaStato(Country country) {
		
		String sql = "SELECT ccode,StateAbb,StateNme " + "FROM country " +"WHERE StateAbb = ? " ;
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, country.getStateAbb());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				country.setCCode(rs.getInt("ccode"));
				country.setStateNme( rs.getString("StateNme"));
			}

			conn.close();
			return country;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- loadAllCountries");
			throw new RuntimeException("Database Error");
		}
		
	}
	
	//trovo le coppie in base all'anno creando una lista di border,per ogni border creo un collegamento, se il vertice non esiste lo aggiungo.
}

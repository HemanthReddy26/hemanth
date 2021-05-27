package com.hcl.restaurant.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hcl.restaurant.beans.Restaurant;
import com.hcl.restaurant.exception.RestaurantException;
import com.hcl.restaurant.util.DBConnection;

public class RestaurantDAO {
	
	
public boolean isDataPresent(int id) {
		
	DBConnection dbc=new DBConnection();
	Connection con=dbc.getConnection();
		boolean boolValue=false;
		String query="select count(*) from restaurant where id=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			rs=ps.executeQuery();
    		while(rs.next()) {
			if(rs.getInt(1)==0)
		     	boolValue=true;
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbc.closePreparedStatement(ps);
			dbc.closeResultSet(rs);
		}

  	  return boolValue;
	}
	
	
	public Restaurant addRestaurant(Restaurant res) throws RestaurantException {
		
		if(isDataPresent(res.getId())) {
		DBConnection dbc=new DBConnection();
		Connection con=dbc.getConnection();
		PreparedStatement ps=null;
		String sql="insert into restaurant values(?,?,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, res.getId());
			ps.setString(2, res.getName());
			ps.setString(3, res.getLocation());
			ps.setDouble(4, res.getRating());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbc.closePreparedStatement(ps);
		}
		return res;
		}else
			throw new RestaurantException("Id already exists");
		
	}
	
	public void updateRestaurant(Restaurant r){
		
		DBConnection dbc=new DBConnection();
		Connection con=dbc.getConnection();
			String updateQuery="update restaurant set name=?,location=?,rating=? where id=?";
			PreparedStatement ps=null;
			
			try {
				ps=con.prepareStatement(updateQuery);
			
				ps.setString(1,r.getName());
				ps.setString(2,r.getLocation());
				ps.setDouble(3, r.getRating());
				ps.setInt(4,r.getId());
				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbc.closePreparedStatement(ps);
			}	
		}

	public void deleteRestaurant(int id) {
		
		DBConnection dbc=new DBConnection();
		Connection con=dbc.getConnection();
		
		String deleteQuery="delete from restaurant where id=?";
		PreparedStatement ps=null;
		
		try {
			ps=con.prepareStatement(deleteQuery);
			ps.setInt(1,id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbc.closePreparedStatement(ps);
		}
		
	}

	public List<Restaurant> searchRestaurant(int id) {
		DBConnection dbc=new DBConnection();
		Connection con=dbc.getConnection();
		String searchQuery="select id,name,location,rating from restaurant where id=?";
		 List<Restaurant> resList=new ArrayList<>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=con.prepareStatement(searchQuery);
			ps.setInt(1,id);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				Restaurant res = new Restaurant();
				res.setId(rs.getInt(1));
				res.setName(rs.getString(2));
				res.setLocation(rs.getString(3));
				res.setRating(rs.getFloat(4));
				
				resList.add(res);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbc.closePreparedStatement(ps);
			dbc.closeResultSet(rs);
		}	
		return resList;
	}
		
		public List<Restaurant> displayRestaurant() {
			DBConnection dbc=new DBConnection();
			Connection con=dbc.getConnection();
			
	       String sql="select id,name,location,rating from Restaurant";
	       List<Restaurant> resList=new ArrayList<>();
			
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt =con.prepareStatement(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Restaurant res= new Restaurant();
					res.setId(rs.getInt(1));
					res.setName(rs.getString(2));
					res.setLocation(rs.getString(3));
					res.setRating(rs.getFloat(4));
					resList.add(res);
				}
			} catch (SQLException exception) {
				exception.printStackTrace();
			} finally {
				dbc.closePreparedStatement(stmt);
				dbc.closeResultSet(rs);
			}
			
			
			return resList;

		}

}

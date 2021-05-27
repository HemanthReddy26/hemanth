package com.hcl.restaurant.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcl.restaurant.beans.Restaurant;
import com.hcl.restaurant.exception.RestaurantException;
import com.hcl.restaurant.services.*;


@WebServlet("/")
public class RestaurantServlet extends HttpServlet {
	

	private static final long serialVersionUID = -5459994792108004011L;
	private static int idNo;

	public RestaurantServlet(){
		System.out.println("Inside Constructor");
	}
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("INIT method");
	}
	
	
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		System.out.println("Get Method"); 
		if(req.getAttribute("list") == null) {
			IRestaurantServices irs=new RestaurantServicesImpl();
			RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
			req.setAttribute("list",irs.displayRestaurant());
		}
		String action=req.getServletPath();
		System.out.println(action);
		
		switch(action) {
		case "/add":
			addRestaurant(req,res);
			break;
		case "/update":
			updateRestaurant(req,res);
			break;
		case "/search":
			searchRestaurant(req,res);
			break;
		case "/delete":
			deleteRestaurant(req,res);
			break;	
		case "/fill":
			fillDetails(req,res);
			break;	
		default:
			displayRestaurant(req,res);
			break;
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		System.out.println("Post Method");
		doGet(req,res);
	}
	
	protected void addRestaurant(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		String id=req.getParameter("Id");
		String name=req.getParameter("Name");
		String location=req.getParameter("Location");
		String rating=req.getParameter("Rating");
		Restaurant r=new Restaurant();
		r.setId(Integer.parseInt(id));
		r.setName(name);
		r.setLocation(location);
		r.setRating(Double.parseDouble(rating));
		
		try {
			IRestaurantServices irs=new RestaurantServicesImpl();
			irs.addRestaurant(r);
			RequestDispatcher rd=req.getRequestDispatcher("display");
			req.setAttribute("message", "Restaurant added Successfully");
			rd.forward(req, res);

			
		} catch (RestaurantException e) {
			RequestDispatcher rd=req.getRequestDispatcher("display");
			req.setAttribute("message",e.getMessage());
			rd.forward(req, res);
			e.printStackTrace();		}
	}
	
    protected void displayRestaurant(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		IRestaurantServices irs=new RestaurantServicesImpl();
		RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
		req.setAttribute("list",irs.displayRestaurant());
		rd.include(req, res);		
	}
    
    protected void fillDetails(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
    	IRestaurantServices irs=new RestaurantServicesImpl();
    	idNo=Integer.parseInt(req.getParameter("id"));
		RequestDispatcher rd=req.getRequestDispatcher("success.jsp");
		req.setAttribute("list",irs.searchRestaurant(idNo));
		rd.include(req, res);		
	}
   
    
 public void updateRestaurant(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
          

		String name=req.getParameter("Name");
		String location=req.getParameter("Location");
		String rating=req.getParameter("Rating");
		Restaurant r=new Restaurant();
		r.setId(idNo);
		r.setName(name);
		r.setLocation(location);
		r.setRating(Double.parseDouble(rating));
		IRestaurantServices irs=new RestaurantServicesImpl();
		irs.updateRestaurant(r);
		req.setAttribute("message","Updated successfully");
		RequestDispatcher rd=req.getRequestDispatcher("display");
		
		rd.forward(req, res);
		
	}
 
 
 public void searchRestaurant(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
  	
 	    int id=Integer.parseInt(req.getParameter("Id"));
		IRestaurantServices irs=new RestaurantServicesImpl();
		RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
		req.setAttribute("list",irs.searchRestaurant(id));
		rd.forward(req, res);
		
	}
 
 protected void deleteRestaurant(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
	 
 	
 	    int id=Integer.parseInt(req.getParameter("id"));
		IRestaurantServices irs=new RestaurantServicesImpl(); 
		irs.deleteRestaurant(id);
		RequestDispatcher rd=req.getRequestDispatcher("display");
		req.setAttribute("message","Deleted successfully");
		rd.forward(req, res);
		
		
		
	}
	
	public void destroy() {
		System.out.println("Destroy Method");
	}

}

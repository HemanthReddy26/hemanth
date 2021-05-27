<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.util.*" %>
      <%@ page import="com.hcl.restaurant.beans.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%List<Restaurant> list=(ArrayList<Restaurant>)request.getAttribute("list"); %>
<%for(Restaurant res:list){ %>
<form action="update">
Name<input type="text" name="Name" value="<%=res.getName()%>">
Location<input type="text" name="Location" value="<%=res.getLocation()%>">
Rating<input type="text" name="Rating" value="<%=res.getRating()%>">
<input type="submit" value="update">
</form>
<%} %>
</body>
</html>
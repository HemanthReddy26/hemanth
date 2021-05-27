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
<form action="search" method="post">
Id<input type="text" name="Id" placeholder="RestaurantId">
<input type="submit" value="search">
</form>

<form action="add" method="post">
Id<input type="text" name="Id" placeholder="Id">
Name<input type="text" name="Name" placeholder="Name">
Location<input type="text" name="Location" placeholder="Location">
Rating<input type="text" name="Rating" placeholder="Rating">
<input type="submit" value="add">
</form>

<%if(request.getAttribute("message")!=null){ %>
<%=request.getAttribute("message")%>
<%} %>

<%if(request.getAttribute("list")!=null){ %>
<%List<Restaurant> resList=(ArrayList<Restaurant>) request.getAttribute("list"); %>
<table border="1" cellspacing="0" cellpadding="5">
<tr><th>Id</th>
<th>Name</th>
<th>Location</th>
<th>Rating</th></tr>
<%for(Restaurant res:resList){ %>
<tr><td><%=res.getId() %></td>
<td><%=res.getName() %></td>
<td><%=res.getLocation()%></td>
<td><%=res.getRating()%></td>
<td><a href="fill?id=<%=+res.getId()%>"><button type="submit">update</button></a></td>
<td><a href="delete?id=<%=+res.getId()%>"><button type="submit">delete</button></a></td>

</tr>
<%}%>
</table>
<%}%>
</form>
</body>
</html>
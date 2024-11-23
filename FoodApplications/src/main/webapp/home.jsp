<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
		
	 
	<%-- <% String name = (String) session.getAttribute("name");  %> 	  --%>
 
 	  <h1>Welcome <%out.println(session.getAttribute("name")); %> </h1><br>  
 	  
 	  	 		 <%out.println(session.getAttribute("email")+" " +
					 			session.getAttribute("mobile")+" " +
					 			session.getAttribute("password")+" " +
					 			session.getAttribute("address")
					 			);%>
 
		<a href="edit.jsp"><br><button>Edit Mobile No</button></a>
			
</body>
</html>
   
   
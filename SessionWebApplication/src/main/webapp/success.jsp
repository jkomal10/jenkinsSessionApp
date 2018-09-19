<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="index">
<%
//allow access only if session exists
String sessionusername = null;
if(session.getAttribute("sessionusername") == null){
	sessionusername = (String) session.getAttribute("sessionusername");
	response.sendRedirect("index.jsp");
}else sessionusername = (String) session.getAttribute("sessionusername");

%>
<h1>successful!!!</h1>
<h3>Logged in with username ${username} and password ${password}</h3>
</form>
<form action="logout">
<button type="submit">Logout</button>
</form>
</body>
</html>
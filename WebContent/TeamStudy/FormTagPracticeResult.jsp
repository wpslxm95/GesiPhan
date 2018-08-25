<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
   String name=request.getParameter("name");
   String address=request.getParameter("address");
   String []favorite=request.getParameterValues("favorite");
%>
<html>
<head>
<meta charset="UTF-8" />
<title></title>
</head>
<body>
   <ul>
      <li><%=name %></li>
      <li><%=address %></li>
      <li><%for(String favo:favorite) out.println(favo); %></li>
   </ul>
</body>
</html>
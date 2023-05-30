<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Objects"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Boolean error = (Boolean) request.getAttribute("error");
	%>
	<% if(Objects.nonNull(error) && error) { %>
		<h2>すでにIDが利用されています。</h2>
	<% } %>
	<form action="/Login/sign-up" method="POST">
		<div>
			<label>ID :</label>
			<input type="text" name="id" value="<%= (Objects.nonNull(error) && error)
				? request.getParameter("id") : ""%>">
		</div>
		<div>
			<labe>パスワード :</label>
			<input type="password" name="password">
		</div>
		<input type="submit" value="登録">
	</form>
</body>
</html>
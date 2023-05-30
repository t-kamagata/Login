<%@page import="java.util.Objects"%>
<%@page import="jp.co.aforce.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Boolean error = (Boolean) request.getAttribute("error");
		Boolean logout = (Boolean) request.getAttribute("logout");
	%>
	<% if(Objects.nonNull(error) && error) { %>
		<h2>IDもしくはパスワードが違います。</h2>
	<% } %>
	<% if(Objects.nonNull(logout) && logout) { %>
		<h2>ログアウトしました。</h2>
	<% } %>
	<form action="/Login/login" method="POST">
		<div>
			<label>ID :</label>
			<input type="text" name="id" value="<%= (Objects.nonNull(error) && error)
				? request.getParameter("id") : ""%>">
		</div>
		<div>
			<labe>パスワード :</label>
			<input type="password" name="password">
		</div>
		<input type="submit" value="ログイン">
	</form>
	<p><a href="/Login/jsp/signup.jsp">新規登録はこちらから</a></p>
</body>
</html>
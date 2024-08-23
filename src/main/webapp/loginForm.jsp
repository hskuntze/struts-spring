<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<s:head/>
</head>
<body>
	<s:form action="oauthLogin" method="POST">
		<s:textfield name="username" label="Username"></s:textfield>
		<s:textfield name="password" label="Password" type="password"></s:textfield>
		<s:submit value="Login"></s:submit>
	</s:form>
</body>
</html>
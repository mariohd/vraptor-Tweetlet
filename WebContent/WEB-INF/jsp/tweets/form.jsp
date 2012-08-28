<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value='/tweets'/>" method="post">
		<input type="hidden" value="${usuario.id }" id=tweet.usuarioDono.id	
			name=tweet.usuarioDono.id>
		<textarea cols="60" rows="4" name=tweet.corpoMensagem
			id=tweet.corpoMensagem></textarea>
		<button type="submit">Submeter</button>
	</form>
</body>
</html>
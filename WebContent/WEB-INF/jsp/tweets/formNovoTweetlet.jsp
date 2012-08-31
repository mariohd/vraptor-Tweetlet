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
			id=tweet.corpoMensagem
			style="margin-left: 0px; margin-right: 0px; width: 300px; margin-top: 0px; margin-bottom: 9px; height: 90px;"></textarea>
		<button type="submit">Submeter</button>
	</form>
	<div style="color: red;">

		<c:forEach var="error" items="${errors}">
				${error.message}<br />
		</c:forEach>
	</div>
</body>
</html>
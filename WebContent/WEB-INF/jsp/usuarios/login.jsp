<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css"
	rel="stylesheet" type="text/css" />
<script src=http://twitter.github.com/bootstrap/assets/js/bootstrap.js></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url='cabecalho.jsp'></c:import>
	<div class=row-fluid>
		<center>
			<div class=row-fluid>
				<div style="color: red;">
					<ul>
						<c:forEach var="error" items="${errors}">
							<li>${error.message}<br /></li>
						</c:forEach>
					</ul>
				</div>
				<form action="<c:url value='/usuario'/>" method="post">
					Login: <input type="text" id=usuario.login name=login> <br />
					Senha: <input type="text" id=usuario.senha name=senha> <br />
					<button type="submit">Entrar</button>
				</form>
			</div>
		</center>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="usuario" value="${usuario }" scope="session" />
	<label>bem vindo ${usuario.login }</label>
	<br />
	<ul>
		<c:forEach items="${tweetList}" var="tweet">
			<li>${tweet.id} - ${tweet.corpoMensagem} -
				${tweet.usuarioDono.login } - <fmt:formatDate
					pattern="dd/MM/yyyy HH:mm" value="${tweet.dataEnvio }" />
				<c:if test="${tweet.respondeuTweet != null }">
					<label>Em resposta do tweet id ${tweet.respondeuTweet }</label>
				</c:if>
			</li>
			<ul>
				<li>
					<form action=retweet method="post">
						<input type="hidden" value="${tweet.corpoMensagem }" name=mensagem
							id=mensagem> <input type="hidden" value=${usuario.id }
							name=usuarioId id=usuarioId> <input type="hidden"
							value=${tweet.usuarioDono.id } name=donoId id=donoId>
						<button type="submit">Retweet</button>
					</form>
				</li>
				<li>
					<form action=responder method="post">
						<input type="hidden" value=${usuario.id } name=usuarioId id=usuarioId> 
						<input type="hidden" value=${tweet.id } name=tweetId id=tweetId>
						<textarea rows="4" cols="20" name=resposta id=resposta></textarea>
						<button type="submit">Responder</button>
					</form>
				</li>
			</ul>
		</c:forEach>
	</ul>
	<c:import url="formNovoTweetlet.jsp"></c:import>
</body>
</html>
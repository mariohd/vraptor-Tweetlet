<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src=http://twitter.github.com/bootstrap/assets/js/jquery.js></script>
<link
	href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css"
	rel="stylesheet" type="text/css" />
<script src=http://twitter.github.com/bootstrap/assets/js/bootstrap.js></script>
<script type="text/javascript">
$(document).ready(function() {
	  var par = $('*#responderTweet');
	  $(par).hide();
	  
	  $('#fadein').click(function(e) {
	      $(par).slideToggle('slow');
	      e.preventDefault();
	  });
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<c:import url="cabecalho.jsp"></c:import>
	<c:set var="usuario" value="${usuario }" scope="session" />
	<br />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span4">
				<div class="well sidebar-nav"
					style="border-radius: 15px; position: fixed">
					<ul class="nav nav-list">
						<li class="nav-header"><p>Novo tweetlet</p></li>
						<li class="active" style="border-radius: 15px"><c:import
								url="formNovoTweetlet.jsp"></c:import></li>
					</ul>
				</div>
			</div>
			<div class="span6">
				<c:forEach items="${tweetList}" var="tweet">
					<div class="well sidebar-nav">
						<ul class="nav nav-list">
							<li>${tweet.id} - ${tweet.corpoMensagem} -
								${tweet.usuarioDono.login } - <fmt:formatDate
									pattern="dd/MM/yyyy HH:mm" value="${tweet.dataEnvio }" /> <c:if
									test="${tweet.respondeuTweet != null }">
									<label>Em resposta do tweet id ${tweet.respondeuTweet }</label>
								</c:if>
							</li>
							<li>
								<form action=retweet method="post">
									<input type="hidden" value="${tweet.corpoMensagem }"
										name=mensagem id=mensagem> <input type="hidden"
										value=${usuario.id } name=usuarioId id=usuarioId> <input
										type="hidden" value=${tweet.usuarioDono.id } name=donoId
										id=donoId>
									<button type="submit">Retweet</button>
								</form>
							</li>
							<li>
								<button id=fadein>Responder</button>
							</li>
							<li id=responderTweet>
								<form action=responder method="post">
									<input type="hidden" value=${usuario.id } name=usuarioId
										id=usuarioId> <input type="hidden" value=${tweet.id }
										name=tweetId id=tweetId>
									<textarea rows="4" cols="20" name=resposta id=resposta></textarea>
									<button type="submit">Enviar</button>
								</form>
							</li>
						</ul>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>

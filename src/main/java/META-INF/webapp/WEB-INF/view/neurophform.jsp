<%@ include file="header.jsp" %>
<div class="container"  role="main">
	<p>Kõhutäis ei ole kaugel...</p>
	<div class="alert alert-dismissable alert-danger" style="display:none">
		${warning}
	</div>
	<div class="progress">
		<div class="progress-bar progress-bar-success" role="progressbar" style="width: ${(step + 1) * 10}%">
		</div>
	</div>
	<div class="well">
	<h1>${formdata.getQuestion()}</h1>
	<br/>
		<form action="/neuroph" method="POST">
		<div class="btn-group btn-group-justified" style="width: 30%">
			<c:forEach var="answer" items="${formdata.getAnswers()}">
				<div class="btn-group">
					<button type="submit" class="btn btn-lg btn-default"  name="answer" value="${answer[1]}">${answer[0]}</button>
				</div>
			</c:forEach>
		</div>
		</form>
	</div>
</div>
<%@ include file="footer.jsp" %>
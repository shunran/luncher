<%@ include file="header.jsp" %>
<div class="container">
    <h1>Tulemused</h1>
    <div class="well">
	    <h3>Parimaks söögikohaks osutus:</h3>
	    <br/>
	    <h1><span class="label label-warning">${list.name} </span></h1>
	    <br/><br/>
	    <a href="/drools/clear" class="btn btn-default" role="button" >Alusta uuesti</a>
    </div>
</div>
<%@ include file="footer.jsp" %>
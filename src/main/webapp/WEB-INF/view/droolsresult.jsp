<%@ include file="header.jsp" %>
<div class="container">
    <h1>Tulemused</h1>
    <div class="well">
	    <h3>Parimaks söögikohaks osutus:</h3>
	    <br/>
	    <h1><span class="label label-warning">${list[0].name} või ${list[1].name}</span></h1>
	    <br/><br/>
	    <div class="btn-group btn-group-justified" style="width: 40%">
	    <div class="btn-group">
	    <a href="/drools/full" class="btn btn-success" role="button">Vaata detailseid tulemusi</a>
	    </div>
	    <div class="btn-group">
	    <a href="/drools/clear" class="btn btn-default" role="button">Alusta uuesti</a>
	    </div>
	    </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
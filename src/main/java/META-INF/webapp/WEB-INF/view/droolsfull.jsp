<%@ include file="header.jsp" %>
<div class="container"  role="main">
    <h3>Detailsed tulemused:</h3>
    <br />
    <div class="table-responsive">
        <!-- here should go some titles... -->
        <table class="table table-bordered table-striped">
        <thead>
			<tr>
            <th>Nimi</th>
            <th>Rahvusköök</th>
            <th>Keskmine hind (eur)</th>
            <th>Teenindus</th>
            <th>Teeninduse tase</th>
            <th>Keskmine ooteaeg (min)</th>
            <th>Skoor</th>
            </tr>
        </thead>
        <c:forEach var="restaurant" items="${list}">
        <tbody>
        <tr>
            <td>
                <c:out value="${restaurant.name}" />
            </td>
            <td>
                <c:out value="${restaurant.cuisine}" />
            </td>
            <td>
                <c:out value="${(restaurant.minCost + restaurant.maxCost) / 2}" />
            </td>
            <td>
                <c:out value="${restaurant.service}" />
            </td>
            <td>
                <c:out value="${restaurant.serviceClass}" />
            </td>
			<td>
                <c:out value="${(restaurant.minPreparationTime + restaurant.maxPreparationTime) / 2}" />
            </td>
            <td>
                <c:out value="${restaurant.perceptron}" />
            </td>
        </tr>
        </tbody>
        </c:forEach>
    </table>
    <br />
    <a href="/drools/clear" class="btn btn-default" role="button">Alusta uuesti</a>
</div>
<%@ include file="footer.jsp" %>
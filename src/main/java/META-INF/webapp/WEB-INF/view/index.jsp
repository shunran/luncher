<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Luncher</title>
</head>
  <body>
    <h1>Luncher:</h1>
    <p>${formdata.getQuestion()}</p>
    <form action="/" method="POST">
    <c:forEach var="answer" items="${formdata.getAnswers()}">
        <input type="radio" name="answer" value="${answer}">${answer}<br>
    </c:forEach>
    <input type="hidden" name="step" value="${step}">
    <input type="submit" value="Submit" />
</form>
  </body>
</html>
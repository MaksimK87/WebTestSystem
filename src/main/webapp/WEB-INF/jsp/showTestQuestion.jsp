<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <head>
        <title> Test: ${userTest.testName}:</title>
        <br/>
        Not passed questions: ${questionQuantity}
        <fmt:setLocale value="${sessionScope.local}"/>
        <fmt:setBundle basename="local" var="loc"/>

        <fmt:message bundle="${loc}" key="local.signin.lang_button_ru" var="ru_button"/>
        <fmt:message bundle="${loc}" key="local.signin.lang_button_en" var="en_button"/>
        <fmt:message bundle="${loc}" key="local.question_page.next_button" var="next"/>
    </head>
    <%--<c:set var="question" value="${currentQuestion}"/>--%>

    <%--  <c:forEach var="question" items="userTest" >--%>


</head>
<body>
<br/>
<h1><c:out value="${arrTestQuestion[currentQuestionIndex]}"/></h1>
<br/>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="go_to_next_question"/>
    <c:forEach items="${answers}" var="answer">

        <p><input type="checkbox" name=answer value="${answer}"/>${answer}<p/>

    </c:forEach>
    <input type="submit" value="${next}" name="next">
</form>
</body>
</html>

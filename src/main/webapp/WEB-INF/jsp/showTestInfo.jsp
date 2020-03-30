<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<br>
<head>
    <title>TestInfo</title>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.signin.lang_button_ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.signin.lang_button_en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.main.signout_button" var="sign_out"/>
    <fmt:message bundle="${loc}" key="local.page.go_to_main" var="go_to_main"/>
    <fmt:message bundle="${loc}" key="local.main.start_test" var="start"/>
</head>
<body>
<h1>Test: ${userTest.testName}</h1>


General information about test: <br/>

question quantity: ${questionQuantity}<br/>
duration, min: ${userTest.testDuration}<br/>


<form action="Controller" method="post">
    <input type="hidden" name="command" value="change_language"/>
    <input type="hidden" name="lang" value="ru"/>
    <input type="submit" value="${ru_button}"/>
</form>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="change_language"/>
    <input type="hidden" name="lang" value="en"/>
    <input type="submit" value="${en_button}"/>
</form>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="go_to_main_page"/>
    <input type="submit" value="${go_to_main}"/>
</form>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="sign_out"/>
    <input type="submit" value="${sign_out}"/>
</form>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="show_test_question"/>
    <input type="submit" name="start" value="${start}"/>
</form>

<c:if test="${not empty param.errorMessage}">
    <c:out value="${param.errorMessage}"></c:out>
</c:if>


</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Creating new test</title>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.signin.lang_button_ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.signin.lang_button_en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.add_test.next_button" var="next_button"/>


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
</head>

<body>

<p>Please, create new test:</p>

<form action="Controller" method="post">

    <input type="hidden" name="command" value="add_test"/>

    Enter test's name:
    <p><input type="text" name="testName" value="" placeholder="enter name" required/>
    <p/>

    Enter test's duration (minutes):
    <p><input type="number" name="duration" min="0" value="" placeholder="enter duration in minutes" required/>
    </p>

    Enter questions quantity in test:

    <p><input type="number" name="questionQuantity" min="1" value="" placeholder="question quantity" required></p>

    <input type="submit" value="${next_button}">
    </p>


</form>


</body>
</html>

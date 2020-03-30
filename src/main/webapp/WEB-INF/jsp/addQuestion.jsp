<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Add question in test ${testName}</title>
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

<p> Test's name: ${testName}</p>
<p> Questions in test: ${questionQuantity}</p>
<p> Duration: ${duration} min.</p>


<form action="Controller" method="post">
    <p><input type="hidden" name="command" value="add_test_question"/></p>

    Please, enter text of question number ${questionCounter}:

    <p>
        <textarea name="questionText" cols="40" rows="3" placeholder="enter question" required></textarea>
    <p>

        Enter answer's quantity:
    <p><input type="number" name="answerQuantity" min="2" value="" placeholder="answer quantity"></p>

    <p><input type="submit" name="${next_button}"></p>

</form>
</body>
</html>

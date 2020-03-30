<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Add answer in question number: ${questionCounter}</title>
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

    <c:if test="${not empty param.errorMessage}">
        <c:out value="${param.errorMessage}"></c:out>
    </c:if>

</head>

<body>

<p> Test's name: ${testName}</p>
<p> Questions in test: ${questionQuantity}</p>
<p> Duration: ${duration} min.</p>
<p> Question number ${questionCounter}, text: ${questionText}</p>


<form action="Controller" method="post">
    <p><input type="hidden" name="command" value="go_to_add_answer"/></p>

    Please, enter text of answer number:${answerCounter}

    <p>
        <textarea name="answerText" cols="40" rows="3" placeholder="enter answer" required></textarea>
    <p>

    Mark answer, if it's correct for this question? :
    <p><input type="radio" name="isCorrectAnswer" value="correct" required> Yes</p>
    <p><input type="radio" name="isCorrectAnswer" value="incorrect" required>No</p>
    <p><input type="submit" name="${next_button}"></p>

</form>
</body>
</html>
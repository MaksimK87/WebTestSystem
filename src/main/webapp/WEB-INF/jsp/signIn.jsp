<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>

   <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.signin.lang_button_ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.signin.lang_button_en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.signin_button" var="sign_in"/>
    <fmt:message bundle="${loc}" key="local.signin_registration_button" var="registration"/>
</head>

<br>

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

<form action="Controller" method="post" class="formRegistration">
    <input type="hidden" name="command" value="authorization"/>
    <p>
        <span>Login</span>
        <input type="text" name="login" value="" placeholder="enter login" autofocus required/>
    </p>
    <p>
        <span>Password:</span>
        <input type="password" name="password" value="" placeholder="enter password" required/>
        <input type="submit" name="sign in" value="${sign_in}" class="inputBtn"/>
    </p>
</form>


<c:if test="${not empty param.errorMessage}">
    <c:out value="${param.errorMessage}"></c:out>
</c:if>

</br>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="go_to_registration_page"/>
    <input type="submit" value="${registration}"/>
</form>

</body>
</html>

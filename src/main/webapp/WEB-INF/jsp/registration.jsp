<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration page</title>

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.signin.lang_button_ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.signin.lang_button_en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.registration.sign_up" var="sign_up"/>
</head>
<body>

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

    <input type="hidden" name="command" value="registration" required/>
    Name:
    <input type="text" name="name" value="" required/>
    <br/>
    Surname:
    <input type="text" name="surname" value="" required/>
    <br/>
    email:
    <input type="email" name="email" value="" required/>
    <br/>
    Choose role:
    <select name="role" required>
        <option value="STUDENT">Student</option>
        <option value="TUTOR">Tutor</option>
        <option value="ADMINISTRATOR">Administrator</option>
    </select>
    <br/>
    Login:
    <input type="text" name="login" value="" required/>
    <br/>
    Password:
    <input type="password" name="password" value="" required/>

    <input type="submit" name="sign up" value="${sign_up}"/>
</form>

</body>
</html>
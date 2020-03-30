<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="by.htp.jd2.maksimkosmachev.testapp.entity.User"
         pageEncoding="UTF-8" %>
<br>
<head>
    <title></title>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.signin.lang_button_ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.signin.lang_button_en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.main.signout_button" var="sign_out"/>
    <fmt:message bundle="${loc}" key="local.complete_add_test.complete" var="complete"/>
</head>
<h1>Test system web application </h1>
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
    <input type="submit" value="${complete}"/>
</form>
<body>
Your test:
<br> test name: ${test.testname}
<br> test duration (min): ${test.testduration}
<table>
    <c:forEach var="element" items="${test.alltest}">
        <tr>
            <p><c:out value="${element.key}"/> <c:out value="${element.value}"/></p>
        </tr>

    </c:forEach>


</table>
    <c:forEach var="test" items="${test}">
        <p><c:out value="${test.alltest}"/></p>
    </c:forEach>

</body>
</html>

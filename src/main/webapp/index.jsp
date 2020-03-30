
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>test system</title>
    <style>
        <%@include file="/WEB-INF/style/index.css"%>
    </style>
</head>
<body>
<c:redirect url="Controller?command=go_to_sign_in_page"/>
</body>
</html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hi</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <script src="/js/jquery-3.3.1.min.js"></script>
    <script src="/js/users-ajax.js"></script>
</head>
<body>

<form action = "/login" method = "POST">
    First Name: <input type = "text" name = "login" autocomplete="off">
    <br />
    Last Name: <input type = "password" name = "password" autocomplete="off"/>
    <input type = "submit" value = "Submit" />
</form>
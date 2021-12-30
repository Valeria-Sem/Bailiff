<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


    <style>
        <%@include file="/WEB-INF/jsp/error/errorPage.css" %>
    </style>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>OOPS !</h1>
                <div class="error-details">
                    <c:if test="${requestScope.errorMsg != null}">
                        <div class="alert alert-danger" role="alert">
                                ${requestScope.errorMsg}
                        </div>
                    </c:if>
                    <c:if test="${requestScope.errorMsg == null}">
                        <div class="alert alert-danger" role="alert">
                            Ошибка на сервере. Пожалуйста, вернитесь позже.
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
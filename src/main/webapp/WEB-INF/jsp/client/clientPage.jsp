<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="clients" scope="session" type="java.util.List"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <style>
        <%@include file='/WEB-INF/jsp/home/homePage.css' %>
    </style>

    <title>Realt Agency</title>
</head>
<body>
<h1 style="margin-left: 30%; margin-top: 30px; margin-bottom: 50px">Клиенты</h1>

<a class="btn btn-primary" style="margin-top: 10px" href="controller?command=gotohomepage">Назад</a>
<a class="btn btn-primary" style="margin-left: 80%"
   data-bs-toggle="modal"
   data-bs-target="#myModal">Добавить нового клиента</a>

<table class="table table-hover table-striped" style="margin-top: 50px">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">
            ФИО
        </th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${clients}" var="client">
        <tr>
            <th scope="row">${client.id}</th>
            <td>${client.fio}</td>
            <td>
                <a class="btn btn-outline-primary"
                   href="controller?command=gotoupdateclientpage&id=${client.id}">Обновить</a>
                <a class="btn btn-outline-danger" style="margin-left: 20px"
                   href="controller?command=deleteclient&id=${client.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Новый клиент</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="controller?command=createnewclient" method="post">
                <div class="modal-body">

                    <input type="text" class="form-control" id="fio" name="fio" placeholder="ФИО клиента: Маркус Р.Р.">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                    <button class="btn btn-primary" type="submit" data-bs-dismiss="modal">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
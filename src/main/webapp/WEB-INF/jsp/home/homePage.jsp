<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="orders" scope="session" type="java.util.List"/>
<jsp:useBean id="bailiffs" scope="session" type="java.util.List"/>
<jsp:useBean id="operations" scope="session" type="java.util.List"/>
<jsp:useBean id="clients" scope="session" type="java.util.List"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <style>
        <%@include file='/WEB-INF/jsp/home/homePage.css' %>
    </style>

    <title>Bailiff</title>
</head>
<body>
    <h1 style="margin-left: 30%; margin-top: 30px; margin-bottom: 50px">Заказы</h1>

    <form class="col-sm-2" style="margin-left: 50px" action="controller?command=search" method="post">
        <input type="search"
               class="form-control form-control-light caps"
               placeholder="Поиск..."
               aria-label="Поиск"
               name="part" >
        <div>
            <button type="submit" class="btn btn-primary"
               style="margin-top: 10px">Искать</button>
        </div>
    </form>

    <button class="btn btn-primary" style="margin-left: 80%" data-bs-toggle="modal"
       data-bs-target="#myModal" >Добавить новый заказ</button>


    <table class="table table-hover table-striped" style="margin-top: 50px">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">
                    Клиент
                    <div class="btn-group mb-3">
                        <a type="button" class="btn btn-outline-secondary" href="controller?command=sortbyclient&sort=asc">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-up-short" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M8 12a.5.5 0 0 0 .5-.5V5.707l2.146 2.147a.5.5 0 0 0 .708-.708l-3-3a.5.5 0 0 0-.708 0l-3 3a.5.5 0 1 0 .708.708L7.5 5.707V11.5a.5.5 0 0 0 .5.5z"/>
                            </svg>
                            <span class="visually-hidden">Кнопка</span>
                        </a>
                        <a type="button" class="btn btn-outline-secondary" href="controller?command=sortbyclient&sort=desc">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-short" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v5.793l2.146-2.147a.5.5 0 0 1 .708.708l-3 3a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L7.5 10.293V4.5A.5.5 0 0 1 8 4z"></path>
                            </svg>
                            <span class="visually-hidden">Кнопка</span>
                        </a>
                    </div>
                </th>
                <th scope="col">
                    Операция
                    <div class="btn-group mb-3">
                        <a type="button" class="btn btn-outline-secondary" href="controller?command=sortbyoperation&sort=asc">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-up-short" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M8 12a.5.5 0 0 0 .5-.5V5.707l2.146 2.147a.5.5 0 0 0 .708-.708l-3-3a.5.5 0 0 0-.708 0l-3 3a.5.5 0 1 0 .708.708L7.5 5.707V11.5a.5.5 0 0 0 .5.5z"/>
                            </svg>
                            <span class="visually-hidden">Кнопка</span>
                        </a>
                        <a type="button" class="btn btn-outline-secondary" href="controller?command=sortbyoperation&sort=desc">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-short" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v5.793l2.146-2.147a.5.5 0 0 1 .708.708l-3 3a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L7.5 10.293V4.5A.5.5 0 0 1 8 4z"></path>
                            </svg>
                            <span class="visually-hidden">Кнопка</span>
                        </a>
                    </div>
                </th>
                <th scope="col">
                    Судебный исполнитель
                    <div class="btn-group mb-3">
                        <a type="button" class="btn btn-outline-secondary" href="controller?command=sortbybailiff&sort=asc">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-up-short" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M8 12a.5.5 0 0 0 .5-.5V5.707l2.146 2.147a.5.5 0 0 0 .708-.708l-3-3a.5.5 0 0 0-.708 0l-3 3a.5.5 0 1 0 .708.708L7.5 5.707V11.5a.5.5 0 0 0 .5.5z"/>
                            </svg>
                            <span class="visually-hidden">Кнопка</span>
                        </a>
                        <a type="button" class="btn btn-outline-secondary" href="controller?command=sortbybailiff&sort=desc">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-short" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v5.793l2.146-2.147a.5.5 0 0 1 .708.708l-3 3a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L7.5 10.293V4.5A.5.5 0 0 1 8 4z"></path>
                            </svg>
                            <span class="visually-hidden">Кнопка</span>
                        </a>
                    </div>
                </th>
                <th scope="col">
                    <a class="btn btn-primary" href="controller?command=clean">Очистить</a>
                </th>
            </tr>
        </thead>
            <tbody>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <th scope="row">${order.id}</th>
                        <td>${order.client}</td>
                        <td>${order.operation}</td>
                        <td>${order.bailiff}</td>
                        <td>
                            <a class="btn btn-outline-primary"
                               href="controller?command=gotoupdateorderpage&id=${order.id}">Обновить</a>
                            <a class="btn btn-outline-danger"
                               style="margin-left: 20px"
                               href="controller?command=deleteorder&id=${order.id}">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
    </table>

    <div class="btn-group" role="group" style="margin-left: 40%; margin-top: 50px; margin-bottom: 50px">
        <a class="btn btn-danger" href="controller?command=gotoclientpage">Клиенты</a>
        <a class="btn btn-warning" href="controller?command=gotooperationpage">Операции</a>
        <a class="btn btn-success" href="controller?command=gotobailiffpage">Судебные исполнители</a>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Новый заказ</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="controller?command=createneworder" method="post">
                    <div class="modal-body">

                        <select class="form-select" id="client"
                                required="" name="client"
                                aria-label="Клиент">
                            <c:forEach items="${clients}" var="client">
                                <option value="${client.fio}">
                                        ${client.fio}
                                </option>
                            </c:forEach>
                        </select>
                        <select class="form-select" id="operation"
                                required="" name="operation"
                                aria-label="Операция">
                            <c:forEach items="${operations}" var="operation">
                                <option value="${operation.name}">
                                        ${operation.name}
                                </option>
                            </c:forEach>
                        </select>
                        <select class="form-select" id="bailiff"
                                required="" name="bailiff"
                                aria-label="Судебный исполнитель">
                            <c:forEach items="${bailiffs}" var="bailiff">
                                <option value="${bailiff.fio}">
                                        ${bailiff.fio}
                                </option>
                            </c:forEach>
                        </select>
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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Контрольна панель</title>

    <!-- Bootstrap CSS-->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Develops CSS -->
    <link href="resources/css/users.css" rel="stylesheet">
    <link href="resources/css/font_style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script type="javascript" src="resources/js/bootstrap.min.js"></script>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>

<jsp:include page="included_pages/fixedTopNavbar.jsp"/>

<!-- Header Jumbotron -->
<div class="jumbotron text-center">
    <h2>Конфігураційна панель наказів</h2>
    <p>Тут ви можете редагувати конфігурацію наказів!</p>
</div>

<!-- Body -->
<div class="container">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <!-- Information -->
            <div class="container">

                <table class="table table-condensed font-table">
                    <!-- University information -->
                    <thead>
                    <tr>
                        <th class="col-sm-6 text-center"><h4><b>Об'єкти типів наказів</b></h4></th>
                        <th class="col-sm-2 text-center"><h4><b>Дії</b></h4></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <form action="order_configuration" method="post">
                            <td class="col-sm-6">
                                <div class="col-sm-12 col-un-padding">
                                    <input type="text" maxlength="30" class="form-control normal-area-input"
                                           placeholder="Назва нового типу наказів"
                                           name="create_order_type">
                                </div>
                            </td>
                            <td class="col-sm-2">
                                <input type="submit" value="Створити" name="create_order_type_btn"
                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                            </td>
                        </form>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <form action="order_configuration" method="post">
                            <td class="col-sm-6">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-6">
                                    <select name="update_order_type" required>
                                        <option disabled selected>Тип наказу</option>
                                        <c:forEach var="orderTypesList" items="${orderTypesList}">
                                            <option value="${orderTypesList.id}">${orderTypesList.orderTypeTitle}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-3"></div>
                            </td>
                            <td class="col-sm-2">
                                <input type="submit" value="Редагувати" name="update_order_type_btn"
                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                            </td>
                        </form>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <form action="order_configuration" method="post">
                            <td class="col-sm-6">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-6">
                                    <select name="delete_order_type" required>
                                        <option disabled selected>Тип наказу</option>
                                        <c:forEach var="orderTypesList" items="${orderTypesList}">
                                            <option value="${orderTypesList.id}">${orderTypesList.orderTypeTitle}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-sm-3"></div>
                            </td>
                            <td class="col-sm-2">
                                <input type="submit" value="Видалити" name="delete_order_type_btn"
                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                            </td>
                        </form>
                    </tr>
                    </tbody>
                </table>


            </div>

        </div>
        <div class="col-sm-2"></div>
    </div>
</div>

<jsp:include page="included_pages/footerJumbotron.jsp"/>

</body>
</html>
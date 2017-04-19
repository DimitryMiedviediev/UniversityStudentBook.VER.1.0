<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Інформація про студента</title>

    <!-- Bootstrap CSS-->
    <link href="resources/css/bootstrap.css" rel="stylesheet">

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

<jsp:include page="../included_pages/fixedTopNavbar.jsp"/>

<!-- Header Jumbotron -->
<div class="jumbotron text-center">
    <h2>Накази студента</h2>
    <p>Тут ви можете побачити інформацію про накази студента!</p>
</div>

<!-- Body -->
<div class="container">
    <div class="row">
        <c:if test="${addOrderArea eq null and removeOrderArea eq null}">
            <form action="student_info?student_id=${studentObject.id}">
                <div class="col-sm-1"></div>
                <div class="col-sm-2">

                    <div style="padding: 10px"></div>

                    <!-- Buttons area -->
                    <input type="submit" name="to_student_info" value="До інформації студента"
                           class="btn btn-default btn-md btn-block"
                           formmethod="post"/>
                    <input type="submit" name="add_student_orders" value="Додати"
                           class="btn btn-default btn-md btn-block"
                           formmethod="post"/>
                    <input type="submit" name="remove_student_orders" value="Видалити"
                           class="btn btn-default btn-md btn-block"
                           formmethod="post"/>
                </div>
            </form>
            <div class="col-sm-8">
                <!-- Tables -->
                <div class="container">
                    <table id="stupid" class="table table-hover font-table">
                        <thead>
                        <tr>
                            <th data-sort="string-ins">Номер наказу</th>
                            <th data-sort="string-ins">Тип наказу</th>
                            <th data-sort="string-ins">Дата наказу</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="studentOrderList" items="${studentOrderList}">
                            <tr>
                                <td>
                                    <a href="order_info?order_id=${studentOrderList.id}">${studentOrderList.orderNumber}</a>
                                </td>
                                <td>
                                    <a href="order_info?order_id=${studentOrderList.id}">${studentOrderList.orderType.orderTypeTitle}</a>
                                </td>
                                <td>
                                    <a href="order_info?order_id=${studentOrderList.id}">${studentOrderList.orderDate}</a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>

                    </table>
                </div>
            </div>
            <div class="col-sm-1"></div>
        </c:if>
        <c:if test="${addOrderArea ne null}">
            <form action="student_info?student_id=${studentObject.id}">
                <div class="col-sm-1"></div>
                <div class="col-sm-2">

                    <div style="padding: 10px"></div>

                    <!-- Buttons area -->
                    <input type="submit" name="to_student_orders" value="Назад"
                           class="btn btn-default btn-md btn-block"
                           formmethod="post"/>
                    <input type="submit" name="add_student_orders_option" value="Додати вибрані"
                           class="btn btn-default btn-md btn-block"
                           formmethod="post"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-7">
                    <div style="padding: 10px"></div>
                    <!-- Tables -->
                    <div class="container">
                        <select name="orders" required size="15" multiple>
                            <option disabled selected>Накази</option>
                            <c:forEach var="otherOrderList" items="${otherOrderList}">
                                <option value="${otherOrderList.id}">${otherOrderList.orderNumber}/${otherOrderList.orderType.orderTypeTitle}/${otherOrderList.orderDate}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-sm-1"></div>
            </form>
        </c:if>
        <c:if test="${removeOrderArea ne null}">
            <form action="student_info?student_id=${studentObject.id}">
                <div class="col-sm-1"></div>
                <div class="col-sm-2">

                    <div style="padding: 10px"></div>

                    <!-- Buttons area -->
                    <input type="submit" name="to_student_orders" value="Назад"
                           class="btn btn-default btn-md btn-block"
                           formmethod="post"/>
                    <input type="submit" name="remove_student_orders_option" value="Видалити вибрані"
                           class="btn btn-default btn-md btn-block"
                           formmethod="post"/>
                </div>
                <div class="col-sm-1"></div>
                <div class="col-sm-7">
                    <div style="padding: 10px"></div>
                    <!-- Tables -->
                    <div class="container">
                        <select name="orders" required size="15" multiple>
                            <option disabled selected>Накази</option>
                            <c:forEach var="studentOrderList" items="${studentOrderList}">
                                <option value="${studentOrderList.id}">${studentOrderList.orderNumber}/${studentOrderList.orderType.orderTypeTitle}/${studentOrderList.orderDate}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-sm-1"></div>
            </form>
        </c:if>
    </div>
</div>

<jsp:include page="../included_pages/footerJumbotron.jsp"/>

</body>
</html>


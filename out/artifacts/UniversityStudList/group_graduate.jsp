<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Адміністративна панель</title>

    <!-- Bootstrap CSS-->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Develops CSS -->
    <link href="../css/users.css" rel="stylesheet">
    <link href="../css/font_style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script type="javascript" src="../js/bootstrap.min.js"></script>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <!-- Include DataPicker plugin-->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <%--<link rel="stylesheet" href="/resources/demos/style.css">--%>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $("#datepicker1").datepicker();
        });
    </script>

</head>

<body>

<jsp:include page="included_pages/fixedTopNavbar.jsp"/>

<!-- Header Jumbotron -->
<div class="jumbotron text-center">
    <h2>Адміністративна панель</h2>
    <p>Тут ви можете створювати і редагувати списки груп і спеціальностей!</p>
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
                        <th class="col-sm-6 text-center"><h4><b>Наказ про здобуття рівня освіти</b></h4></th>
                        <th class="col-sm-2 text-center"><h4><b>Дії</b></h4></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <form action="control_panel">
                            <td class="col-sm-6">
                                <div class="col-sm-12 col-un-padding">
                                    <c:forEach var="group" items="${group}">
                                        <div class="col-sm-6 col-un-padding">
                                            <input type="text" maxlength="30" class="form-control normal-area-input"
                                                   placeholder="Номер наказу" name="order_graduate">
                                        </div>
                                        <div class="col-sm-6 col-un-padding">
                                            <input type="text" class="date form-control normal-area-input" placeholder="Дата наказу"
                                                   name="date_graduate"
                                                   id="datepicker1">
                                        </div>
                                        <input type="hidden" class="form-control normal-area-input"
                                               name="group_graduate" value="${group.groupId}">
                                    </c:forEach>
                                </div>
                            </td>
                            <td class="col-sm-2">
                                <input type="submit" value="Зберегти" name="graduate_group_submit"
                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                <button formaction="control_panel" class="btn btn-default btn-md btn-block"
                                        formmethod="post">Відмінити
                                </button>
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
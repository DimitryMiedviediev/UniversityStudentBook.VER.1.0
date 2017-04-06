<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Student Profile</title>

    <!-- Bootstrap CSS-->
    <%--<link href="../css/bootstrap.min.css" rel="stylesheet">--%>
    <link href="resources/css/bootstrap.css" rel="stylesheet">

    <!-- Develops CSS -->
    <%--<link href="../css/font_style.css" rel="stylesheet">--%>
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
<div style="margin-top: 200px"></div>

<div class="container">
    <div class="row">

        <div class="col-sm-4"></div>
        <div class="col-sm-4">

            <form class="form-signin" action="authorization" method="post">
                <h2 class="form-signin-heading text-center">Авторизація</h2>


                <c:if test="${error eq null}">
                    <div class="form-group">
                        <input type="email" class="form-control" placeholder="Пошта" required=""
                               autofocus="" name="email" maxlength="100">
                        <input type="password" class="form-control" placeholder="Пароль" required="" name="password"
                               minlength="8">
                    </div>
                </c:if>
                <c:if test="${error ne null}">
                    <div class="form-group has-error">
                        <c:forEach var="userMap" items="${userMap}">
                            <c:if test="${userMap.key eq null}">
                                <input type="email" class="form-control" placeholder="Пошта" required=""
                                       autofocus="" name="email">
                            </c:if>
                            <c:if test="${userMap.key ne null}">
                                <input type="email" class="form-control" placeholder="Пошта" required=""
                                       autofocus="" name="email" value="${userMap.key}">
                            </c:if>
                            <c:if test="${userMap.value eq null}">
                                <input type="password" class="form-control" placeholder="Пароль" required=""
                                       name="password">
                            </c:if>
                            <c:if test="${userMap.value ne null}">
                                <input type="password" class="form-control" placeholder="Пароль" required=""
                                       name="password"
                                       value="${userMap.value}">
                            </c:if>
                        </c:forEach>
                    </div>
                </c:if>

                <button class="btn btn-lg btn-default btn-block" type="submit" formmethod="post" name="signIn"
                        value="signIn">Увійти
                </button>
            </form>

            <a href="registration">Зареєструватись</a>
        </div>
        <div class="col-sm-4"></div>
    </div>
</div>

</body>
</html>


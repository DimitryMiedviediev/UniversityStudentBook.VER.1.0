<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Control panel</title>

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
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <!-- Information -->
            <div class="container">

                <table class="table table-condensed font-table">
                    <!-- University information -->
                    <thead>
                    <tr>
                        <th class="col-sm-6 text-center"><h4><b>Об'єкти груп</b></h4></th>
                        <th class="col-sm-2 text-center"><h4><b>Дії</b></h4></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:forEach var="group" items="${group}">
                            <form action="control_panel">
                                <td class="col-sm-6">
                                    <div class="col-sm-5 col-un-padding">
                                        <select name="cr_gr_spec" class="center-block option-style" required>
                                            <c:forEach var="specList" items="${specList}">
                                                <c:choose>
                                                    <c:when test="${group.specId == specList.id}">
                                                        <option selected>${specList.speciality}</option>
                                                    </c:when>
                                                    <c:when test="${group.specId != specList.id}">
                                                        <option>${specList.speciality}</option>
                                                    </c:when>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-4 col-un-padding">
                                        <select name="cr_gr_qual" class="center-block option-style" required>
                                            <c:choose>
                                                <c:when test="${group.qualificationLevel == 'Бакалавр'}">
                                                    <option selected>Бакалавр</option>
                                                    <option>Спеціаліст</option>
                                                    <option>Магістр</option>
                                                </c:when>
                                                <c:when test="${group.qualificationLevel == 'Спеціаліст'}">
                                                    <option selected>Спеціаліст</option>
                                                    <option>Бакалавр</option>
                                                    <option>Магістр</option>
                                                </c:when>
                                                <c:when test="${group.qualificationLevel == 'Магістр'}">
                                                    <option selected>Магістр</option>
                                                    <option>Бакалавр</option>
                                                    <option>Спеціаліст</option>
                                                </c:when>
                                            </c:choose>
                                        </select>
                                    </div>
                                    <div class="col-sm-3 col-un-padding">
                                        <select name="cr_gr_form" class="center-block option-style" required>
                                            <c:choose>
                                                <c:when test="${group.educationForm == 'Денна'}">
                                                    <option selected>Денна</option>
                                                    <option>Заочна</option>
                                                </c:when>
                                                <c:when test="${group.educationForm == 'Заочна'}">
                                                    <option selected>Заочна</option>
                                                    <option>Денна</option>
                                                </c:when>
                                            </c:choose>
                                        </select>
                                    </div>
                                    <div class="col-sm-6 col-un-padding">
                                        <input type="text" maxlength="10" class="form-control normal-area-input"
                                               placeholder="Номер групи"
                                               name="cr_gr_num" value="${group.number}">
                                    </div>
                                    <div class="col-sm-6 col-un-padding">
                                        <input type="text" maxlength="1" class="form-control normal-area-input"
                                               placeholder="Курс"
                                               name="cr_gr_cource" value="${group.course}">
                                    </div>
                                    <div>
                                        <input type="hidden" class="form-control normal-area-input"
                                               name="cr_gr_id" value="${group.groupId}">
                                    </div>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Зберегти" name="edit_group_submit"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                    <input type="submit" value="Відмінити" name="cancel"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:forEach>
                    </tr>
                    </tbody>
                </table>

            </div>

        </div>
        <div class="col-sm-3"></div>
    </div>
</div>

<jsp:include page="included_pages/footerJumbotron.jsp"/>

</body>
</html>
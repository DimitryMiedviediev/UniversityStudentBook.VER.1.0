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
<jsp:include page="included_pages/headerJumbotronControlPanel.jsp"/>

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
                        <th class="col-sm-6"><h4><b>Speciality objects</b></h4></th>
                        <th class="col-sm-2"><h4><b>Actions</b></h4></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:forEach var="group" items="${group}">
                        <form action="control_panel">
                            <td class="col-sm-6">
                                <div class="col-sm-4 col-un-padding">
                                    <select name="cr_gr_spec" class="center-block option-style" required>
                                        <c:choose>
                                            <c:when test="${group.speciality == 'Mechanics'}">
                                                <option selected>Mechanics</option>
                                                <option>Engineers</option>
                                            </c:when>
                                            <c:when test="${group.speciality == 'Engineers'}">
                                                <option selected>Engineers</option>
                                                <option>Mechanics</option>
                                            </c:when>
                                        </c:choose>
                                    </select>
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <select name="cr_gr_qual" class="center-block option-style" required>
                                        <c:choose>
                                            <c:when test="${group.qualificationLevel == 'Bachelor'}">
                                                <option selected>Bachelor</option>
                                                <option>Specialist</option>
                                                <option>Master</option>
                                            </c:when>
                                            <c:when test="${group.qualificationLevel == 'Specialist'}">
                                                <option selected>Specialist</option>
                                                <option>Bachelor</option>
                                                <option>Master</option>
                                            </c:when>
                                            <c:when test="${group.qualificationLevel == 'Master'}">
                                                <option selected>Master</option>
                                                <option>Bachelor</option>
                                                <option>Specialist</option>
                                            </c:when>
                                        </c:choose>
                                    </select>
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <select name="cr_gr_form" class="center-block option-style" required>
                                        <option selected>Full time</option>
                                        <option>Distance</option>
                                        <c:choose>
                                            <c:when test="${group.educationForm == 'Full time'}">
                                                <option selected>Full time</option>
                                                <option>Distance</option>
                                            </c:when>
                                            <c:when test="${group.educationForm == 'Distance'}">
                                                <option selected>Distance</option>
                                                <option>Full time</option>
                                            </c:when>
                                        </c:choose>
                                    </select>
                                </div>
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" maxlength="10" class="form-control normal-area-input"
                                           placeholder="Group num"
                                           name="cr_gr_num" value="${group.number}">
                                </div>
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" maxlength="1" class="form-control normal-area-input"
                                           placeholder="Course"
                                           name="cr_gr_cource" value="${group.course}">
                                </div>
                            </td>
                            <td class="col-sm-2">
                                <input type="submit" value="Submit" name="edit_group_submit" class="btn btn-default btn-md btn-block"/>
                                <input type="submit" value="Cancel" name="cancel" class="btn btn-default btn-md btn-block"/>
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
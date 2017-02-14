<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <%--<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->--%>

    <%--<title>Student DB</title>--%>

    <%--<!-- Bootstrap CSS-->--%>
    <%--<link href="../css/bootstrap.min.css" rel="stylesheet">--%>

    <%--<!-- Develops CSS -->--%>
    <%--<link href="../css/users.css" rel="stylesheet">--%>
    <%--<link href="../css/font_style.css" rel="stylesheet">--%>
    <%--<link href="../css/stupidtable.css" rel="stylesheet">--%>

    <%--<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->--%>
    <%--<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->--%>
    <%--<!--[if lt IE 9]>--%>

    <%--<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->--%>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
    <%--<!-- Include all compiled plugins (below), or include individual files as needed -->--%>
    <%--<script type="javascript" src="../js/bootstrap.min.js"></script>--%>

    <%--<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>--%>
    <%--<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>--%>

    <%--<!-- Stupidtable plugin for sorting table-data-->--%>
    <%--<script src="../js/stupidtable.js"></script>--%>
    <%--<script>--%>
        <%--$(function () {--%>
            <%--$("table").stupidtable();--%>
        <%--});--%>
    <%--</script>--%>

<%--</head>--%>

<%--<body>--%>

<%--<jsp:include page="included_pages/fixedTopNavbar.jsp"/>--%>
<%--<jsp:include page="included_pages/headerJumbotronDB.jsp"/>--%>

<%--<!-- Body -->--%>
<%--<div class="container">--%>
    <%--<div class="row">--%>
        <%--<div class="col-sm-1"></div>--%>
        <%--<div class="col-sm-2">--%>

            <%--<!-- Sidebar -->--%>
            <%--<div class="list-group font-table">--%>
                <%--<form action="students_list">--%>
                    <%--<c:forEach var="params" items="${paramsList}">--%>
                        <%--<h5>Specialization</h5>--%>
                        <%--<ul>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.mechanics == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="mechanics" value="mechanics" checked> Mechanics--%>
                                    <%--</h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="mechanics" value="mechanics"> Mechanics </h6>--%>
                                    <%--</li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.engineers == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="engineers" value="engineers" checked> Engineers--%>
                                    <%--</h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="engineers" value="engineers"> Engineers </h6>--%>
                                    <%--</li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        <%--</ul>--%>
                        <%--</ul>--%>
                        <%--<h5>Type</h5>--%>
                        <%--<ul>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.educate == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="educate" value="educate" checked> Educate </h6>--%>
                                    <%--</li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="educate" value="educate"> Educate </h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.archive == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="archive" value="archive" checked> Archive </h6>--%>
                                    <%--</li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="archive" value="archive"> Archive </h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        <%--</ul>--%>
                        <%--<h5>Qualification</h5>--%>
                        <%--<ul>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.bachelor == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="bachelor" value="bachelor" checked> Bachelor--%>
                                    <%--</h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="bachelor" value="bachelor"> Bachelor </h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.specialist == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="specialist" value="specialist" checked>--%>
                                        <%--Specialist </h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="specialist" value="specialist"> Specialist--%>
                                    <%--</h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.master == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="master" value="master" checked> Master </h6>--%>
                                    <%--</li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="master" value="master"> Master </h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        <%--</ul>--%>
                        <%--<h5>Courses</h5>--%>
                        <%--<ul>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.course1 == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="1 course" value="1" checked> 1 course--%>
                                    <%--</h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="1 course" value="1"> 1 course </h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.course2 == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="2 course" value="2" checked> 2 course--%>
                                    <%--</h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="2 course" value="2"> 2 course </h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.course3 == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="3 course" value="3" checked> 3 course--%>
                                    <%--</h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="3 course" value="3"> 3 course </h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.course4 == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="4 course" value="4" checked> 4 course--%>
                                    <%--</h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="4 course" value="4"> 4 course </h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.course5 == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="5 course" value="5" checked> 5 course--%>
                                    <%--</h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="5 course" value="5"> 5 course </h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.course6 == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="6 course" value="6" checked> 6 course--%>
                                    <%--</h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="6 course" value="6"> 6 course </h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        <%--</ul>--%>
                        <%--<h5>Group</h5>--%>
                        <%--<ul>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.group == null)}">--%>
                                    <%--<li><input type="text" placeholder="Group" name="group" value="${params.group}">--%>
                                    <%--</li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><input type="text" placeholder="Group" name="group"></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.subgroup == null)}">--%>
                                    <%--<li><input type="text" placeholder="Subgroup" name="subgroup"--%>
                                               <%--value="${params.subgroup}">--%>
                                    <%--</li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><input type="text" placeholder="Subgroup" name="subgroup"></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        <%--</ul>--%>
                        <%--<h5>Financing</h5>--%>
                        <%--<ul>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.government == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="government" value="government" checked>--%>
                                        <%--Government </h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="government" value="government"> Government--%>
                                    <%--</h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.commercial == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="commercial" value="commercial" checked>--%>
                                        <%--Commercial </h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="commercial" value="commercial"> Commercial--%>
                                    <%--</h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        <%--</ul>--%>
                        <%--<h5>Education form</h5>--%>
                        <%--<ul>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.full_time == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="full_time" value="full time" checked> Full-time--%>
                                    <%--</h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="full_time" value="full time"> Full-time </h6>--%>
                                    <%--</li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.distance == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="distance" value="distance" checked> Distance--%>
                                    <%--</h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="distance" value="distance"> Distance </h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        <%--</ul>--%>
                        <%--<h5>Birthplace</h5>--%>
                        <%--<ul>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.state == null)}">--%>
                                    <%--<li><input type="text" placeholder="State" name="state" value="${params.state}">--%>
                                    <%--</li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><input type="text" placeholder="State" name="state"></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.department == null)}">--%>
                                    <%--<li><input type="text" placeholder="Department" name="department"--%>
                                               <%--value="${params.department}"></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><input type="text" placeholder="Department" name="department"></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        <%--</ul>--%>
                        <%--<h5>Exemptions</h5>--%>
                        <%--<ul>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.orphan == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="orphan" value="orphan" checked> Orphan </h6>--%>
                                    <%--</li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="orphan" value="orphan"> Orphan </h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${!(params.disabled == null)}">--%>
                                    <%--<li><h6><input type="checkbox" name="disabled" value="disabled" checked> Disabled--%>
                                    <%--</h6></li>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<li><h6><input type="checkbox" name="disabled" value="disabled"> Disabled </h6></li>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        <%--</ul>--%>
                    <%--</c:forEach>--%>
                    <%--<input type="submit" value="Sort" class="btn btn-default btn-block">--%>
                <%--</form>--%>
            <%--</div>--%>

        <%--</div>--%>
        <%--<div class="col-sm-8">--%>
            <%--<!-- Tables -->--%>
            <%--<div class="container">--%>
                <%--<table id="stupid" class="table table-hover font-table">--%>
                    <%--<thead>--%>
                    <%--<tr>--%>
                        <%--<th data-sort="string-ins">Surname</th>--%>
                        <%--<th data-sort="string-ins">Firstname</th>--%>
                        <%--<th data-sort="string-ins">Lastname</th>--%>
                        <%--<th data-sort="string-ins">Status</th>--%>
                    <%--</tr>--%>
                    <%--</thead>--%>
                    <%--<tbody>--%>

                    <%--<c:forEach var="tempStudList" items="${studList}">--%>
                        <%--<tr>--%>
                            <%--<td><a href="main_page.jsp">${tempStudList.name}</a></td>--%>
                            <%--<td><a href="main_page.jsp">${tempStudList.surname}</a></td>--%>
                            <%--<td><a href="main_page.jsp">${tempStudList.lastname}</a></td>--%>
                            <%--<td><a href="main_page.jsp">${tempStudList.status}</a></td>--%>
                        <%--</tr>--%>
                    <%--</c:forEach>--%>

                    <%--</tbody>--%>

                <%--</table>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="col-sm-1"></div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<jsp:include page="included_pages/footerJumbotron.jsp"/>--%>

<%--</body>--%>
<%--</html>--%>
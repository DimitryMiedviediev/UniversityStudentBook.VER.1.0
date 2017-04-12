<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Список студентів</title>

    <!-- Bootstrap CSS-->
    <link href="resources/css/bootstrap.css" rel="stylesheet">

    <!-- Develops CSS -->
    <%--<link href="../css/users.css" rel="stylesheet">--%>
    <link href="resources/css/font_style.css" rel="stylesheet">
    <link href="resources/css/stupidtable.css" rel="stylesheet">
    <link rel="stylesheet" , href="resources/css/users.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script type="javascript" src="resources/js/bootstrap.min.js"></script>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <!-- Stupidtable plugin for sorting table-data-->
    <script src="resources/js/stupidtable.js"></script>
    <script>
        $(function () {
            $("table").stupidtable();
        });
    </script>

</head>

<body>

<jsp:include page="../included_pages/fixedTopNavbar.jsp"/>

<!-- Header Jumbotron -->
<div class="jumbotron text-center">
    <h2>Список студентів</h2>
    <p>Це список всіх студентів вашого університету!</p>
</div>

<!-- Body -->
<div class="container">
    <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-2">

            <!-- Sidebar -->
            <div class="list-group font-table">


                <!-- Empty DIV -->
                <div style="padding: 10px"></div>

                <form action="student_create" method="post">
                    <input type="submit" name="student_create" value="Створити студента"
                           class="btn btn-default btn-md btn-block"
                           formmethod="post"/>
                </form>

                <form action="student_configuration" method="post">
                    <input type="submit" name="student_config" value="Конфігурація студентів"
                           class="btn btn-default btn-md btn-block"
                           formmethod="post"/>
                </form>

                <form action="student_list" method="post">
                    <h5>Спеціальність</h5>
                    <ul>
                        <c:forEach var="specList" items="${specList}">
                            <c:if test="${specList.value eq true}">
                                <li><h6><input type="checkbox" name="spec=${specList.key}" value="spec=${specList.key}"
                                               checked> ${specList.key} </h6></li>
                            </c:if>
                            <c:if test="${specList.value eq false}">
                                <li><h6><input type="checkbox" name="spec=${specList.key}"
                                               value="spec=${specList.key}"> ${specList.key} </h6></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h5>Статус</h5>
                    <ul>
                        <c:forEach var="statusList" items="${statusList}">
                            <c:if test="${statusList.value eq true}">
                                <li><h6><input type="checkbox" name="stat=${statusList.key}"
                                               value="stat=${statusList.key}"
                                               checked> ${statusList.key} </h6></li>
                            </c:if>
                            <c:if test="${statusList.value eq false}">
                                <li><h6><input type="checkbox" name="stat=${statusList.key}"
                                               value="stat=${statusList.key}"> ${statusList.key} </h6></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h5>Кваліфікаційний рівень</h5>
                    <ul>
                        <c:forEach var="qualList" items="${qualList}">
                            <c:if test="${qualList.value eq true}">
                                <li><h6><input type="checkbox" name="qual=${qualList.key}" value="qual=${qualList.key}"
                                               checked> ${qualList.key} </h6></li>
                            </c:if>
                            <c:if test="${qualList.value eq false}">
                                <li><h6><input type="checkbox" name="qual=${qualList.key}"
                                               value="qual=${qualList.key}"> ${qualList.key} </h6></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h5>Курси</h5>
                    <ul>
                        <c:forEach var="courseList" items="${courseList}">
                            <c:if test="${courseList.value eq true and courseList.key ne null}">
                                <li><h6><input type="checkbox" name="course=${courseList.key}"
                                               value="course=${courseList.key}"
                                               checked> ${courseList.key} курс</h6></li>
                            </c:if>
                            <c:if test="${courseList.value eq false and courseList.key ne null}">
                                <li><h6><input type="checkbox" name="course=${courseList.key}"
                                               value="course=${courseList.key}"> ${courseList.key} курс</h6></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h5>Група</h5>
                    <ul>
                        <c:forEach var="groupList" items="${groupList}">
                            <c:if test="${groupList.value eq true and groupList.key ne null}">
                                <li><h6><input type="checkbox" name="gr=${groupList.key}" value="gr=${groupList.key}"
                                               checked> ${groupList.key} </h6></li>
                            </c:if>
                            <c:if test="${groupList.value eq false and groupList.key ne null}">
                                <li><h6><input type="checkbox" name="gr=${groupList.key}"
                                               value="gr=${groupList.key}"> ${groupList.key} </h6></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h5>Підгрупа</h5>
                    <ul>
                        <c:forEach var="subgroupList" items="${subgroupList}">
                            <c:if test="${subgroupList.value eq true and subgroupList.key ne null}">
                                <li><h6><input type="checkbox" name="sub=${subgroupList.key}"
                                               value="sub=${subgroupList.key}"
                                               checked> ${subgroupList.key} </h6></li>
                            </c:if>
                            <c:if test="${subgroupList.value eq false and subgroupList.key ne null}">
                                <li><h6><input type="checkbox" name="sub=${subgroupList.key}"
                                               value="sub=${subgroupList.key}"> ${subgroupList.key} </h6></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h5>Фінансування</h5>
                    <ul>
                        <c:forEach var="financeList" items="${financeList}">
                            <c:if test="${financeList.value eq true}">
                                <li><h6><input type="checkbox" name="fin=${financeList.key}"
                                               value="fin=${financeList.key}"
                                               checked> ${financeList.key} </h6></li>
                            </c:if>
                            <c:if test="${financeList.value eq false}">
                                <li><h6><input type="checkbox" name="fin=${financeList.key}"
                                               value="fin=${financeList.key}"> ${financeList.key} </h6></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h5>Форма навчання</h5>
                    <ul>
                        <c:forEach var="educFormList" items="${educFormList}">
                            <c:if test="${educFormList.value eq true}">
                                <li><h6><input type="checkbox" name="edform=${educFormList.key}"
                                               value="edform=${educFormList.key}"
                                               checked> ${educFormList.key} </h6></li>
                            </c:if>
                            <c:if test="${educFormList.value eq false}">
                                <li><h6><input type="checkbox" name="edform=${educFormList.key}"
                                               value="edform=${educFormList.key}"> ${educFormList.key} </h6></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h5>Місце народження</h5>
                    <ul>
                        <%--&lt;%&ndash;<c:forEach var="cityParam" items="${cityParam}">&ndash;%&gt;--%>
                        <%--<c:if test="${cityParam ne null}">--%>
                            <%--<input type="text" placeholder="Місто" name="city" class="form-control" list="cities"--%>
                                   <%--value="${cityParam}">--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${cityParam eq null}">--%>
                            <%--<input type="text" placeholder="Місто" name="city" class="form-control" list="cities">--%>
                        <%--</c:if>--%>
                        <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<input type="text" placeholder="Місто" name="city" class="form-control" list="cities">&ndash;%&gt;--%>
                        <%--<datalist id="cities">--%>
                            <%--<c:forEach var="cityList" items="${cityList}">--%>
                                <%--<option value="${cityList.key}"></option>--%>
                            <%--</c:forEach>--%>
                        <%--</datalist>--%>


                        <c:if test="${cityParam ne null}">
                            <input type="text" placeholder="Місто" name="city" class="form-control"
                                   list="cities"
                                   value="${cityParam}">
                        </c:if>
                        <c:if test="${cityParam eq null}">
                            <input type="text" placeholder="Місто" name="city" class="form-control"
                                   list="cities">
                        </c:if>
                        <datalist id="cities">
                            <c:forEach var="cityList" items="${cityList}">
                                <option value="${cityList.key}"></option>
                            </c:forEach>
                        </datalist>


                        <%--<c:forEach var="stateParam" items="${stateParam}">--%>
                        <c:if test="${stateParam ne null}">
                            <input type="text" placeholder="Область" name="state" class="form-control" list="states"
                                   value="${stateParam}">
                        </c:if>
                        <c:if test="${stateParam eq null}">
                            <input type="text" placeholder="Область" name="state" class="form-control"
                                   list="states">
                        </c:if>
                        <%--</c:forEach>--%>
                        <datalist id="states">
                            <c:forEach var="stateList" items="${stateList}">
                                <option value="${stateList.key}"></option>
                            </c:forEach>
                        </datalist>
                    </ul>
                    <hr>
                    <input type="submit" value="Сортувати" name="student_sort" class="btn btn-default btn-block"
                           formmethod="post">
                </form>
            </div>

        </div>
        <div class="col-sm-8">
            <!-- Tables -->
            <div class="container">
                <table id="stupid" class="table table-hover font-table">
                    <thead>
                    <tr>
                        <th data-sort="string-ins">Фамілія</th>
                        <th data-sort="string-ins">Ім'я</th>
                        <th data-sort="string-ins">По-батькові</th>
                        <th data-sort="string-ins">Статус</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="studList" items="${studList}">
                        <tr>
                            <td><a href="student_info?student_id=${studList.id}">${studList.student.surname}</a>
                            </td>
                            <td><a href="student_info?student_id=${studList.id}">${studList.student.name}</a></td>
                            <td><a href="student_info?student_id=${studList.id}">${studList.student.patronimic}</a>
                            </td>
                            <td>
                                <a href="student_info?student_id=${studList.id}">${studList.studentStatus.studentStatusTitle}</a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>

                </table>
            </div>
        </div>
        <div class="col-sm-1"></div>
    </div>
</div>

<jsp:include page="../included_pages/footerJumbotron.jsp"/>

</body>
</html>
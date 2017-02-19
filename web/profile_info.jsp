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

    <jsp:include page="included_pages/cssAndJs.jsp"/>

</head>

<body>

<jsp:include page="included_pages/fixedTopNavbar.jsp"/>

<!-- Header Jumbotron -->
<div class="jumbotron text-center">
    <h2>Профіль студента</h2>
    <p>Сторінка інформації про студента!</p>
</div>

<!-- Body -->
<div class="container">
    <div class="row">
        <c:forEach var="studentInfo" items="${studentInfo}">
        <c:if test="${studentInfo.id ne null}">
        <form action="student_info?stud_id=${studentInfo.id}">
            </c:if>
            </c:forEach>
            <div class="col-sm-1"></div>
            <div class="col-sm-2">
                <!-- Empty DIV -->
                <div style="padding: 10px"></div>

                <!-- Profile photo -->
                <%--<div class="center-block">--%>
                <%--<img src="images/photo.jpg" class="img-responsive img-thumbnail" alt="Responsive image">--%>
                <%--</div>--%>

                <!-- Empty DIV -->
                <div style="padding: 10px"></div>

                <!-- Buttons area -->
                <%--<button type="button" class="btn btn-default btn-sm btn-block" formmethod="post">--%>
                <%--Edit--%>
                <%--</button>--%>
                <input type="submit" name="edit_btn" value="Редагувати" class="btn btn-default btn-md btn-block"
                       formmethod="post"/>
                <input type="submit" name="delete_btn" value="Видалити" class="btn btn-default btn-md btn-block"
                       formmethod="post"/>

            </div>
            <div class="col-sm-8">
                <!-- Empty DIV -->
                <div style="padding: 10px"></div>
                <!-- Information -->
                <div class="container">
                    <!-- University information -->
                    <table class="table table-condensed font-table">
                        <thead>
                        <tr>
                            <th class="col-sm-2">Параметр</th>
                            <th class="col-sm-6">Університетська Інформація</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">ФІО студента:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.surname ne null}">
                                        ${studentInfo.surname}
                                    </c:if>
                                    <c:if test="${studentInfo.name ne null}">
                                        ${studentInfo.name}
                                    </c:if>
                                    <c:if test="${studentInfo.lastname ne null}">
                                        ${studentInfo.lastname}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Дата вступу:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.entryDate ne null}">
                                        ${studentInfo.entryDate}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Статус:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.status ne null}">
                                        ${studentInfo.status}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Спеціальність:</td>
                            <td class="col-sm-6">
                                <c:forEach var="specInfo" items="${specInfo}">
                                    <c:if test="${specInfo.speciality ne null}">
                                        ${specInfo.speciality}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Група:</td>
                            <td class="col-sm-6">
                                <c:forEach var="groupInfo" items="${groupInfo}">
                                    <c:if test="${groupInfo.number ne null}">
                                        ${groupInfo.number}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Підгрупа:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.subGroup ne null}">
                                        ${studentInfo.subGroup}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Кваліфікаційний рівень:</td>
                            <td class="col-sm-6">
                                <c:forEach var="groupInfo" items="${groupInfo}">
                                    <c:if test="${groupInfo.qualificationLevel ne null}">
                                        ${groupInfo.qualificationLevel}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Курс:</td>
                            <td class="col-sm-6">
                                <c:forEach var="groupInfo" items="${groupInfo}">
                                    <c:if test="${groupInfo.course ne null}">
                                        ${groupInfo.course}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Форма навчання:</td>
                            <td class="col-sm-6">
                                <c:forEach var="groupInfo" items="${groupInfo}">
                                    <c:if test="${groupInfo.educationForm ne null}">
                                        ${groupInfo.educationForm}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Фінансування:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.financing ne null}">
                                        ${studentInfo.financing}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Номер залікової книги:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.studBook ne null}">
                                        ${studentInfo.studBook}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <!-- Student information -->
                    <table class="table table-condensed font-table">
                        <thead>
                        <tr>
                            <th class="col-sm-2">Параметр</th>
                            <th class="col-sm-6">Особиста Інформація</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Дата народження:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.birthDate ne null}">
                                        ${studentInfo.birthDate}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Номер паспорту:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.passport ne null}">
                                        ${studentInfo.passport}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Місце видачі:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.passpOffice ne null}">
                                        ${studentInfo.passpOffice}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Дата видачі:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.passpDate ne null}">
                                        ${studentInfo.passpDate}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Ідентифікаційний код:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.identityCode ne null}">
                                        ${studentInfo.identityCode}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Адреса:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.studHouse ne null}">
                                        ${studentInfo.studHouse},
                                    </c:if>
                                    <c:if test="${studentInfo.studStreet ne null}">
                                        ${studentInfo.studStreet},
                                    </c:if>
                                    <c:if test="${studentInfo.studCity ne null}">
                                        ${studentInfo.studCity},
                                    </c:if>
                                    <c:if test="${studentInfo.studState ne null}">
                                        ${studentInfo.studState},
                                    </c:if>
                                    <c:if test="${studentInfo.studZip ne null}">
                                        ${studentInfo.studZip},
                                    </c:if>
                                    <c:if test="${studentInfo.studCountry ne null}">
                                        ${studentInfo.studCountry}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Номер телефону:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.studPhone1 ne null}">
                                        +38 ${studentInfo.studPhone1},
                                    </c:if>
                                    <c:if test="${studentInfo.studPhone2 ne null}">
                                        +38 ${studentInfo.studPhone2}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <!-- Parents information -->
                    <table class="table table-condensed font-table">
                        <thead>
                        <tr>
                            <th class="col-sm-2">Параметр</th>
                            <th class="col-sm-6">Інформація про батьків</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">ФІО батька:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.fatherName ne null}">
                                        ${studentInfo.fatherName}
                                    </c:if>
                                    <c:if test="${studentInfo.fatherSurname ne null}">
                                        ${studentInfo.fatherSurname}
                                    </c:if>
                                    <c:if test="${studentInfo.fatherLastname ne null}">
                                        ${studentInfo.fatherLastname}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Номер телефону:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.fatherPhone1 ne null}">
                                        +38 ${studentInfo.fatherPhone1},
                                    </c:if>
                                    <c:if test="${studentInfo.fatherPhone2 ne null}">
                                        +38 ${studentInfo.fatherPhone2}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">ФІО матері:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.motherName ne null}">
                                        ${studentInfo.motherName}
                                    </c:if>
                                    <c:if test="${studentInfo.motherSurname ne null}">
                                        ${studentInfo.motherSurname}
                                    </c:if>
                                    <c:if test="${studentInfo.motherLastname ne null}">
                                        ${studentInfo.motherLastname}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Номер телефону:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.motherPhone1 ne null}">
                                        +38 ${studentInfo.motherPhone1},
                                    </c:if>
                                    <c:if test="${studentInfo.motherPhone2 ne null}">
                                        +38 ${studentInfo.motherPhone2}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Адреса:</td>
                            <td class="col-sm-6">
                                <c:forEach var="studentInfo" items="${studentInfo}">
                                    <c:if test="${studentInfo.parentsHouse ne null}">
                                        ${studentInfo.parentsHouse},
                                    </c:if>
                                    <c:if test="${studentInfo.parentsStreet ne null}">
                                        ${studentInfo.parentsStreet},
                                    </c:if>
                                    <c:if test="${studentInfo.parentsCity ne null}">
                                        ${studentInfo.parentsCity},
                                    </c:if>
                                    <c:if test="${studentInfo.parentsState ne null}">
                                        ${studentInfo.parentsState},
                                    </c:if>
                                    <c:if test="${studentInfo.parentsZip ne null}">
                                        ${studentInfo.parentsZip},
                                    </c:if>
                                    <c:if test="${studentInfo.parentsCountry ne null}">
                                        ${studentInfo.parentsCountry}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-sm-1"></div>
        </form>
    </div>
</div>

<jsp:include page="included_pages/footerJumbotron.jsp"/>

</body>
</html>


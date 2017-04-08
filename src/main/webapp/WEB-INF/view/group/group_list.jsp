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

    <!-- jQuery mask for input types -->
    <script type="text/javascript" src="resources/js/jquery.mask.js"></script>
    <script type="text/javascript">
        $(function () {
            $('.date').mask('00/00/0000');
            $('.time').mask('00:00:00');
            $('.date_time').mask('00/00/0000 00:00:00');
            $('.cep').mask('00000-000');
            $('.phone').mask('0000-0000');
            $('.phone_with_ddd').mask('(00) 0000-0000');
            $('.phone_us').mask('(000) 000-0000');
            $('.mixed').mask('AAA 000-S0S');
            $('.ip_address').mask('099.099.099.099');
            $('.percent').mask('##0,00%', {reverse: true});
            $('.clear-if-not-match').mask("00/00/0000", {clearIfNotMatch: true});
            $('.placeholder').mask("00/00/0000", {placeholder: "__/__/____"});
            $('.fallback').mask("00r00r0000", {
                translation: {
                    'r': {
                        pattern: /[\/]/,
                        fallback: '/'
                    },
                    placeholder: "__/__/____"
                }
            });

            $('.selectonfocus').mask("00/00/0000", {selectOnFocus: true});

            $('.cep_with_callback').mask('00000-000', {
                onComplete: function (cep) {
                    console.log('Mask is done!:', cep);
                },
                onKeyPress: function (cep, event, currentField, options) {
                    console.log('An key was pressed!:', cep, ' event: ', event, 'currentField: ', currentField.attr('class'), ' options: ', options);
                },
                onInvalid: function (val, e, field, invalid, options) {
                    var error = invalid[0];
                    console.log("Digit: ", error.v, " is invalid for the position: ", error.p, ". We expect something like: ", error.e);
                }
            });

            $('.crazy_cep').mask('00000-000', {
                onKeyPress: function (cep, e, field, options) {
                    var masks = ['00000-000', '0-00-00-00'];
                    mask = (cep.length > 7) ? masks[1] : masks[0];
                    $('.crazy_cep').mask(mask, options);
                }
            });

            $('.cnpj').mask('00.000.000/0000-00', {reverse: true});
            $('.cpf').mask('000.000.000-00', {reverse: true});
            $('.money').mask('#.##0,00', {reverse: true});

            var SPMaskBehavior = function (val) {
                    return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
                },
                spOptions = {
                    onKeyPress: function (val, e, field, options) {
                        field.mask(SPMaskBehavior.apply({}, arguments), options);
                    }
                };

            $('.sp_celphones').mask(SPMaskBehavior, spOptions);

            $(".bt-mask-it").click(function () {
                $(".mask-on-div").mask("000.000.000-00");
                $(".mask-on-div").fadeOut(500).fadeIn(500)
            })

            $('pre').each(function (i, e) {
                hljs.highlightBlock(e)
            });
        });
    </script>

</head>

<body>

<jsp:include page="../included_pages/fixedTopNavbar.jsp"/>

<!-- Header Jumbotron -->
<div class="jumbotron text-center">
    <h2>Список груп</h2>
    <p>Це список груп вашого університету!</p>
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

                <form action="group_create" method="post">
                <input type="submit" name="group_create" value="Створити группу"
                       class="btn btn-default btn-md btn-block"
                       formmethod="post"/>
                </form>

                <form action="group_configuration" method="post">
                    <input type="submit" name="group_config" value="Конфігурація групп"
                           class="btn btn-default btn-md btn-block"
                           formmethod="post"/>
                </form>

                <form action="group_list" method="post">
                    <h5>Спеціальність</h5>
                    <ul>
                        <c:forEach var="groupSpecialityList" items="${groupSpecialityList}">
                            <c:if test="${groupSpecialityList.value eq true}">
                                <li><h6><input type="checkbox" name="groupSpec=${groupSpecialityList.key}"
                                               value="${groupSpecialityList.key}"
                                               checked> ${groupSpecialityList.key} </h6></li>
                            </c:if>
                            <c:if test="${groupSpecialityList.value eq false}">
                                <li><h6><input type="checkbox" name="groupSpec=${groupSpecialityList.key}"
                                               value="${groupSpecialityList.key}"> ${groupSpecialityList.key} </h6></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h5>Форма навчання</h5>
                    <ul>
                        <c:forEach var="groupEducationFormList" items="${groupEducationFormList}">
                            <c:if test="${groupEducationFormList.value eq true}">
                                <li><h6><input type="checkbox" name="groupEducForm=${groupEducationFormList.key}"
                                               value="${groupEducationFormList.key}"
                                               checked> ${groupEducationFormList.key} </h6></li>
                            </c:if>
                            <c:if test="${groupEducationFormList.value eq false}">
                                <li><h6><input type="checkbox" name="groupEducForm=${groupEducationFormList.key}"
                                               value="${groupEducationFormList.key}"> ${groupEducationFormList.key}
                                </h6></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h5>Кваліфікаційний рівень</h5>
                    <ul>
                        <c:forEach var="groupQualificationLevelList" items="${groupQualificationLevelList}">
                            <c:if test="${groupQualificationLevelList.value eq true}">
                                <li><h6><input type="checkbox" name="groupQualLevel=${groupQualificationLevelList.key}"
                                               value="${groupQualificationLevelList.key}"
                                               checked> ${groupQualificationLevelList.key} </h6></li>
                            </c:if>
                            <c:if test="${groupQualificationLevelList.value eq false}">
                                <li><h6><input type="checkbox" name="groupQualLevel=${groupQualificationLevelList.key}"
                                               value="${groupQualificationLevelList.key}"> ${groupQualificationLevelList.key}
                                </h6></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h5>Група</h5>
                    <ul>
                        <c:forEach var="groupNumberList" items="${groupNumberList}">
                            <c:if test="${groupNumberList.value eq true and groupNumberList.key ne null}">
                                <li><h6><input type="checkbox" name="groupNumber=${groupNumberList.key}"
                                               value="${groupNumberList.key}"
                                               checked> ${groupNumberList.key} </h6></li>
                            </c:if>
                            <c:if test="${groupNumberList.value eq false and groupNumberList.key ne null}">
                                <li><h6><input type="checkbox" name="groupNumber=${groupNumberList.key}"
                                               value="${groupNumberList.key}"> ${groupNumberList.key} </h6></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h5>Курси</h5>
                    <ul>
                        <c:forEach var="groupCourseList" items="${groupCourseList}">
                            <c:if test="${groupCourseList.value eq true and groupCourseList.key ne null}">
                                <li><h6><input type="checkbox" name="groupCourse=${groupCourseList.key}"
                                               value="course=${groupCourseList.key}"
                                               checked> ${groupCourseList.key} курс</h6></li>
                            </c:if>
                            <c:if test="${groupCourseList.value eq false and groupCourseList.key ne null}">
                                <li><h6><input type="checkbox" name="groupCourse=${groupCourseList.key}"
                                               value="course=${groupCourseList.key}"> ${groupCourseList.key} курс</h6>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h5>Статус</h5>
                    <ul>
                        <c:forEach var="groupStatusList" items="${groupStatusList}">
                            <c:if test="${groupStatusList.value eq true and groupStatusList.key ne null}">
                                <li><h6><input type="checkbox" name="groupStatus=${groupStatusList.key}"
                                               value="course=${groupStatusList.key}"
                                               checked> ${groupStatusList.key}</h6></li>
                            </c:if>
                            <c:if test="${groupStatusList.value eq false and groupStatusList.key ne null}">
                                <li><h6><input type="checkbox" name="groupStatus=${groupStatusList.key}"
                                               value="course=${groupStatusList.key}"> ${groupStatusList.key}</h6></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <input type="submit" name="group_sort" value="Сортувати" class="btn btn-default btn-block"
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
                        <th data-sort="string-ins">Номер групи</th>
                        <th data-sort="string-ins">Форма навчання</th>
                        <th data-sort="string-ins">Кваліфікаційний рівень</th>
                        <th data-sort="string-ins">Курс</th>
                        <th data-sort="string-ins">Статус</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="groupList" items="${groupList}">
                        <tr>
                            <td><a href="group_info?group_id=${groupList.id}">${groupList.groupNumber}</a></td>
                            <td><a href="group_info?group_id=${groupList.id}">${groupList.groupEducationForm.groupEducationFormTitle}</a></td>
                            <td><a href="group_info?group_id=${groupList.id}">${groupList.groupQualificationLevel.groupQualificationLevelTitle}</a>
                            </td>
                            <td><a href="group_info?group_id=${groupList.id}">${groupList.groupCourse}</a></td>
                            <td><a href="group_info?group_id=${groupList.id}">${groupList.groupStatus.groupStatusTitle}</a></td>
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
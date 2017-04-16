<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Add new student</title>

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
    <script>
        $(function () {
            $("#datepicker2").datepicker();
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

    <!-- Google Map API - Autocomplete Address Form-->
    <script src="resources/js/google_api.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAd_ui82vz6dh7LzxoSF3nO4Sv3UeIQ0zg&signed_in=true&libraries=places&callback=initAutocomplete"
            async defer></script>


</head>

<body>

<jsp:include page="../included_pages/fixedTopNavbar.jsp"/>

<!-- Header Jumbotron -->
<div class="jumbotron text-center">
    <h2>Редагувати студента</h2>
    <p>Тут ви можете редагувати інформацію про студента!</p>
</div>

<!-- Body -->
<div class="container">
    <div class="row">
        <form action="student_info?student_id=${studentObject.id}">
            <div class="col-sm-1"></div>
            <div class="col-sm-2">

                <div style="padding: 10px"></div>

                <!-- Buttons area -->
                <input type="submit" name="save_update_btn" value="Зберегти" class="btn btn-default btn-md btn-block"
                       formmethod="post"/>
                <input type="submit" name="back_to_info_btn" value="Назад" class="btn btn-default btn-md btn-block"
                       formmethod="post"/>

            </div>
            <div class="col-sm-8">
                <!-- Information -->
                <div class="container">

                    <table class="table table-condensed font-table">
                        <thead>
                        <tr>
                            <th class="col-sm-2"><h4><b>Параметр</b></h4></th>
                            <th class="col-sm-6"><h4><b>Університетська інформація</b></h4></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">ФІО:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.student.surname eq null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                                   name="studentSurname">
                                        </c:if>
                                        <c:if test="${studentObject.student.surname ne null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                                   name="studentSurname" value="${studentObject.student.surname}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding has-error">
                                        <c:if test="${studentSurname eq null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                                   name="studentSurname">
                                        </c:if>
                                        <c:if test="${studentSurname ne null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                                   name="studentSurname" value="${studentSurname}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.student.name eq null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                                   name="studentName">
                                        </c:if>
                                        <c:if test="${studentObject.student.name ne null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                                   name="studentName" value="${studentObject.student.name}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding has-error">
                                        <c:if test="${studentName eq null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                                   name="studentName">
                                        </c:if>
                                        <c:if test="${studentName ne null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                                   name="studentName" value="${studentName}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.student.patronimic eq null}">
                                            <input type="text" maxlength="15" class="form-control"
                                                   placeholder="По-батькові"
                                                   name="studentPatronimic">
                                        </c:if>
                                        <c:if test="${studentObject.student.patronimic ne null}">
                                            <input type="text" maxlength="15" class="form-control"
                                                   placeholder="По-батькові"
                                                   name="studentPatronimic" value="${studentObject.student.patronimic}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding has-error">
                                        <c:if test="${studentPatronimic eq null}">
                                            <input type="text" maxlength="15" class="form-control"
                                                   placeholder="По-батькові"
                                                   name="studentPatronimic">
                                        </c:if>
                                        <c:if test="${studentPatronimic ne null}">
                                            <input type="text" maxlength="15" class="form-control"
                                                   placeholder="По-батькові"
                                                   name="studentPatronimic" value="${studentPatronimic}">
                                        </c:if>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Група:</td>
                            <td class="col-sm-2">
                                <div class="col-sm-4 col-un-padding">
                                    <select name="groupId" required>
                                        <option disabled selected>Група</option>
                                        <c:forEach var="groupList" items="${groupList}">
                                            <c:if test="${error eq null}">
                                                <c:if test="${studentObject.group.groupNumber eq groupList.id}">
                                                    <option value="${groupList.id}" selected>
                                                        Гр: ${groupList.groupNumber} /
                                                        Кв: ${groupList.groupQualificationLevel.groupQualificationLevelTitle}
                                                        /
                                                        Сп: ${groupList.speciality.title} /
                                                        Курс ${groupList.groupCourse} /
                                                        Ф: ${groupList.groupEducationForm.groupEducationFormTitle}</option>
                                                </c:if>
                                                <c:if test="${studentObject.group.groupNumber ne groupList.id}">
                                                    <option value="${groupList.id}">Гр: ${groupList.groupNumber} /
                                                        Кв: ${groupList.groupQualificationLevel.groupQualificationLevelTitle}
                                                        /
                                                        Сп: ${groupList.speciality.title} /
                                                        Курс ${groupList.groupCourse} /
                                                        Ф: ${groupList.groupEducationForm.groupEducationFormTitle}</option>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${error ne null}">
                                                <c:if test="${groupId eq groupList.id}">
                                                    <option value="${groupList.id}" selected>
                                                        Гр: ${groupList.groupNumber} /
                                                        Кв: ${groupList.groupQualificationLevel.groupQualificationLevelTitle}
                                                        / Сп: ${groupList.speciality.title} /
                                                        Курс ${groupList.groupCourse} /
                                                        Ф: ${groupList.groupEducationForm.groupEducationFormTitle}</option>
                                                </c:if>
                                                <c:if test="${groupId ne groupList.id}">
                                                    <option value="${groupList.id}">Гр: ${groupList.groupNumber} /
                                                        Кв: ${groupList.groupQualificationLevel.groupQualificationLevelTitle}
                                                        / Сп: ${groupList.speciality.title} /
                                                        Курс ${groupList.groupCourse} /
                                                        Ф: ${groupList.groupEducationForm.groupEducationFormTitle}</option>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Підгрупа:</td>
                            <td class="col-sm-2">
                                <div class="col-sm-4 col-un-padding">
                                    <select name="studentSubgroupId">
                                        <option disabled selected>Підгрупа</option>
                                        <c:forEach var="studentSubgroupList" items="${studentSubgroupList}">
                                            <c:if test="${error eq null}">
                                                <c:if test="${studentObject.studentSubgroup.id eq studentSubgroupList.id}">
                                                    <option value="${studentSubgroupList.id}"
                                                            selected>${studentSubgroupList.studentSubgroupTitle}</option>
                                                </c:if>
                                                <c:if test="${studentObject.studentSubgroup.id ne studentSubgroupList.id}">
                                                    <option value="${studentSubgroupList.id}">${studentSubgroupList.studentSubgroupTitle}</option>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${error ne null}">
                                                <c:if test="${studentSubgroupId eq studentSubgroupList.id}">
                                                    <option value="${studentSubgroupList.id}"
                                                            selected>${studentSubgroupList.studentSubgroupTitle}</option>
                                                </c:if>
                                                <c:if test="${studentSubgroupId ne studentSubgroupList.id}">
                                                    <option value="${studentSubgroupList.id}">${studentSubgroupList.studentSubgroupTitle}</option>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Фінансування:</td>
                            <td class="col-sm-6">
                                <select name="studentFinancingId" required>
                                    <option disabled selected>Фінансування</option>
                                    <c:forEach var="studentFinancingList" items="${studentFinancingList}">
                                        <c:if test="${error eq null}">
                                            <c:if test="${studentObject.studentFinancing.id eq studentFinancingList.id}">
                                                <option value="${studentFinancingList.id}"
                                                        selected>${studentFinancingList.studentFinancingTitle}</option>
                                            </c:if>
                                            <c:if test="${studentObject.studentFinancing.id ne studentFinancingList.id}">
                                                <option value="${studentFinancingList.id}">${studentFinancingList.studentFinancingTitle}</option>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${error ne null}">
                                            <c:if test="${studentFinancingId eq studentFinancingList.id}">
                                                <option value="${studentFinancingList.id}"
                                                        selected>${studentFinancingList.studentFinancingTitle}</option>
                                            </c:if>
                                            <c:if test="${studentFinancingId ne studentFinancingList.id}">
                                                <option value="${studentFinancingList.id}">${studentFinancingList.studentFinancingTitle}</option>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                        <%--&lt;%&ndash;<tbody>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<td class="col-sm-2">Наказ на зарахування:</td>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<td class="col-sm-6">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<select name="orderId" required>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<option disabled selected>Наказ</option>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<c:forEach var="orderList" items="${orderList}">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<c:if test="${error eq null}">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<option value="${orderList.id}">${orderList.orderNumber}/${orderList.orderType.orderTypeTitle}/${orderList.orderDate}</option>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<c:if test="${error ne null}">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<c:if test="${orderId eq orderList.id}">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<option value="${orderList.id}"&ndash;%&gt;--%>
                        <%--&lt;%&ndash;selected>${orderList.orderNumber}/${orderList.orderType.orderTypeTitle}/${orderList.orderDate}</option>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<c:if test="${orderId ne orderList.id}">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<option value="${orderList.id}">${orderList.orderNumber}/${orderList.orderType.orderTypeTitle}/${orderList.orderDate}</option>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</select>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</tbody>&ndash;%&gt;--%>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Номер заліковки:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.studentBook eq null}">
                                            <input type="text" maxlength="8" class="form-control"
                                                   placeholder="Номер заліковки"
                                                   name="studentBook">
                                        </c:if>
                                        <c:if test="${studentObject.studentBook ne null}">
                                            <input type="text" maxlength="8" class="form-control"
                                                   placeholder="Номер заліковки"
                                                   name="studentBook" value="${studentObject.studentBook}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding has-error">
                                        <c:if test="${studentBook eq null}">
                                            <input type="text" maxlength="8" class="form-control"
                                                   placeholder="Номер заліковки"
                                                   name="studentBook">
                                        </c:if>
                                        <c:if test="${studentBook ne null}">
                                            <input type="text" maxlength="8" class="form-control"
                                                   placeholder="Номер заліковки"
                                                   name="studentBook" value="${studentBook}">
                                        </c:if>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>

                        <!-- Spase cap -->
                        <tbody>
                        <tr></tr>
                        </tbody>

                        <%--<!-- Student information -->--%>
                        <thead>
                        <tr>
                            <th class="col-sm-2"><h4><b>Параметр</b></h4></th>
                            <th class="col-sm-6"><h4><b>Особиста інформація</b></h4></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Дата народження:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.studentBirthDate eq null}">
                                            <input type="text" class="date form-control" placeholder="Дата народження"
                                                   name="studentBirthDate"
                                                   id="datepicker1">
                                        </c:if>
                                        <c:if test="${studentObject.studentBirthDate ne null}">
                                            <input type="text" class="date form-control" placeholder="Дата народження"
                                                   name="studentBirthDate"
                                                   id="datepicker1" value="${studentObject.studentBirthDate}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentBirthDate eq null}">
                                            <input type="text" class="date form-control" placeholder="Дата народження"
                                                   name="studentBirthDate"
                                                   id="datepicker1">
                                        </c:if>
                                        <c:if test="${studentBirthDate ne null}">
                                            <input type="text" class="date form-control" placeholder="Дата народження"
                                                   name="studentBirthDate"
                                                   id="datepicker1" value="${studentBirthDate}">
                                        </c:if>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <td class="col-sm-2">Номер паспорту:</td>
                        <td class="col-sm-6">
                            <c:if test="${error eq null}">
                                <div class="col-sm-4 col-un-padding">
                                    <c:if test="${studentObject.studentPassportNumber eq null}">
                                        <input type="text" maxlength="8" class="form-control"
                                               placeholder="Номер паспорту"
                                               name="studentPassportNumber">
                                    </c:if>
                                    <c:if test="${studentObject.studentPassportNumber ne null}">
                                        <input type="text" maxlength="8" class="form-control"
                                               placeholder="Номер паспорту"
                                               name="studentPassportNumber"
                                               value="${studentObject.studentPassportNumber}">
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-4 col-un-padding">
                                    <c:if test="${studentPassportNumber eq null}">
                                        <input type="text" maxlength="8" class="form-control"
                                               placeholder="Номер паспорту"
                                               name="studentPassportNumber">
                                    </c:if>
                                    <c:if test="${studentPassportNumber ne null}">
                                        <input type="text" maxlength="8" class="form-control"
                                               placeholder="Номер паспорту"
                                               name="studentPassportNumber" value="${studentPassportNumber}">
                                    </c:if>
                                </div>
                            </c:if>
                        </td>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Паспорт виданий:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-12 col-un-padding">
                                        <c:if test="${studentObject.studentPassportOffice eq null}">
                                            <input type="text" class="form-control" placeholder="Ким виданий"
                                                   name="studentPassportOffice">
                                        </c:if>
                                        <c:if test="${studentObject.studentPassportOffice ne null}">
                                            <input type="text" class="form-control" placeholder="Ким виданий"
                                                   name="studentPassportOffice"
                                                   value="${studentObject.studentPassportOffice}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-12 col-un-padding">
                                        <c:if test="${studentPassportOffice eq null}">
                                            <input type="text" class="form-control" placeholder="Ким виданий"
                                                   name="studentPassportOffice">
                                        </c:if>
                                        <c:if test="${studentPassportOffice ne null}">
                                            <input type="text" class="form-control" placeholder="Ким виданий"
                                                   name="studentPassportOffice" value="${studentPassportOffice}">
                                        </c:if>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Дата видачі паспорту:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.studentPassportReleaseDate eq null}">
                                            <input type="text" class="date form-control" placeholder="Коли виданий"
                                                   name="studentPassportReleaseDate"
                                                   id="datepicker2">
                                        </c:if>
                                        <c:if test="${studentObject.studentPassportReleaseDate ne null}">
                                            <input type="text" class="date form-control" placeholder="Коли виданий"
                                                   name="studentPassportReleaseDate"
                                                   id="datepicker2" value="${studentObject.studentPassportReleaseDate}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentPassportReleaseDate eq null}">
                                            <input type="text" class="date form-control" placeholder="Коли виданий"
                                                   name="studentPassportReleaseDate"
                                                   id="datepicker2">
                                        </c:if>
                                        <c:if test="${studentPassportReleaseDate ne null}">
                                            <input type="text" class="date form-control" placeholder="Коли виданий"
                                                   name="studentPassportReleaseDate"
                                                   id="datepicker2" value="${studentPassportReleaseDate}">
                                        </c:if>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Ідентифікаційний код:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.studentIdentityCode eq null}">
                                            <input type="text" maxlength="10" class="form-control"
                                                   placeholder="Ідентифікаційний код"
                                                   name="studentIdentityCode">
                                        </c:if>
                                        <c:if test="${studentObject.studentIdentityCode ne null}">
                                            <input type="text" maxlength="10" class="form-control"
                                                   placeholder="Ідентифікаційний код"
                                                   name="studentIdentityCode"
                                                   value="${studentObject.studentIdentityCode}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentIdentityCode eq null}">
                                            <input type="text" maxlength="10" class="form-control"
                                                   placeholder="Ідентифікаційний код"
                                                   name="studentIdentityCode">
                                        </c:if>
                                        <c:if test="${studentIdentityCode ne null}">
                                            <input type="text" maxlength="10" class="form-control"
                                                   placeholder="Ідентифікаційний код"
                                                   name="studentIdentityCode" value="${studentIdentityCode}">
                                        </c:if>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Адреса:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-12 col-un-padding">
                                        <input id="autocomplete1" class="form-control" placeholder="Введіть адресу"
                                               type="text" name="studentAddress"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-12 col-un-padding">
                                        <input id="autocomplete1" class="form-control" placeholder="Введіть адресу"
                                               type="text" name="studentAddress"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.studentAddress.house eq null}">
                                            <input id="street_number1" class="form-control" placeholder="Будинок"
                                                   name="studentHouse" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentObject.studentAddress.house ne null}">
                                            <input id="street_number1" class="form-control" placeholder="Будинок"
                                                   name="studentHouse" disabled="true"
                                                   value="${studentObject.studentAddress.house}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentHouse eq null}">
                                            <input id="street_number1" class="form-control" placeholder="Будинок"
                                                   name="studentHouse" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentHouse ne null}">
                                            <input id="street_number1" class="form-control" placeholder="Будинок"
                                                   name="studentHouse" disabled="true" value="${studentHouse}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.studentAddress.street eq null}">
                                            <input id="route1" class="form-control" placeholder="Вулиця"
                                                   name="studentStreet" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentObject.studentAddress.street ne null}">
                                            <input id="route1" class="form-control" placeholder="Вулиця"
                                                   name="studentStreet" disabled="true"
                                                   value="${studentObject.studentAddress.street}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentStreet eq null}">
                                            <input id="route1" class="form-control" placeholder="Вулиця"
                                                   name="studentStreet" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentStreet ne null}">
                                            <input id="route1" class="form-control" placeholder="Вулиця"
                                                   name="studentStreet" disabled="true"
                                                   value="${studentStreet}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.studentAddress.city eq null}">
                                            <input id="locality1" class="form-control" placeholder="Місто"
                                                   name="studentCity" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentObject.studentAddress.city ne null}">
                                            <input id="locality1" class="form-control" placeholder="Місто"
                                                   name="studentCity" disabled="true"
                                                   value="${studentObject.studentAddress.city}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentCity eq null}">
                                            <input id="locality1" class="form-control" placeholder="Місто"
                                                   name="studentCity" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentCity ne null}">
                                            <input id="locality1" class="form-control" placeholder="Місто"
                                                   name="studentCity" disabled="true" value="${studentCity}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.studentAddress.state eq null}">
                                            <input id="administrative_area_level_11" class="form-control"
                                                   placeholder="Область"
                                                   name="studentState" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentObject.studentAddress.state ne null}">
                                            <input id="administrative_area_level_11" class="form-control"
                                                   placeholder="Область"
                                                   name="studentState" disabled="true"
                                                   value="${studentObject.studentAddress.state}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentState eq null}">
                                            <input id="administrative_area_level_11" class="form-control"
                                                   placeholder="Область"
                                                   name="studentState" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentState ne null}">
                                            <input id="administrative_area_level_11" class="form-control"
                                                   placeholder="Область"
                                                   name="studentState" disabled="true" value="${studentState}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.studentAddress.zip eq null}">
                                            <input id="postal_code1" class="form-control" placeholder="Індекс"
                                                   name="studentZip" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentObject.studentAddress.zip ne null}">
                                            <input id="postal_code1" class="form-control" placeholder="Індекс"
                                                   name="studentZip" disabled="true"
                                                   value="${studentObject.studentAddress.zip}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentZip eq null}">
                                            <input id="postal_code1" class="form-control"
                                                   placeholder="Індекс"
                                                   name="studentZip" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentZip ne null}">
                                            <input id="postal_code1" class="form-control"
                                                   placeholder="Індекс"
                                                   name="studentZip" disabled="true"
                                                   value="${studentZip}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.studentAddress.country eq null}">
                                            <input id="country1" class="form-control" placeholder="Країна"
                                                   name="studentCountry" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentObject.studentAddress.country ne null}">
                                            <input id="country1" class="form-control" placeholder="Країна"
                                                   name="studentCountry" disabled="true"
                                                   value="${studentObject.studentAddress.country}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentCountry eq null}">
                                            <input id="country1" class="form-control" placeholder="Країна"
                                                   name="studentCountry" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentCountry ne null}">
                                            <input id="country1" class="form-control" placeholder="Країна"
                                                   name="studentCountry" disabled="true"
                                                   value="${studentCountry}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <td class="col-sm-2">Номер телефону:</td>
                        <td class="col-sm-6">
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${studentObject.student.phone1 eq null}">
                                        <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                               name="studentPhone1">
                                    </c:if>
                                    <c:if test="${studentObject.student.phone1 ne null}">
                                        <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                               name="studentPhone1" value="${studentObject.student.phone1}">
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${studentPhone1 eq null}">
                                        <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                               name="studentPhone1">
                                    </c:if>
                                    <c:if test="${studentPhone1 ne null}">
                                        <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                               name="studentPhone1" value="${studentPhone1}">
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${studentObject.student.phone2 eq null}">
                                        <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                               name="studentPhone2">
                                    </c:if>
                                    <c:if test="${studentObject.student.phone2 ne null}">
                                        <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                               name="studentPhone2" value="${studentObject.student.phone2}">
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${studentPhone2 eq null}">
                                        <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                               name="studentPhone2">
                                    </c:if>
                                    <c:if test="${studentPhone2 ne null}">
                                        <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                               name="studentPhone2" value="${studentPhone2}">
                                    </c:if>
                                </div>
                            </c:if>
                        </td>
                        </tbody>
                        <tbody>
                        <td class="col-sm-2">Пошта:</td>
                        <td class="col-sm-6">
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${studentObject.student.email eq null}">
                                        <input type="text" class="form-control" placeholder="Email"
                                               name="studentEmail">
                                    </c:if>
                                    <c:if test="${studentObject.student.email ne null}">
                                        <input type="text" class="form-control" placeholder="Email"
                                               name="studentEmail" value="${studentObject.student.email}">
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${studentEmail eq null}">
                                        <input type="text" class="form-control" placeholder="Email"
                                               name="studentEmail">
                                    </c:if>
                                    <c:if test="${studentEmail ne null}">
                                        <input type="text" class="form-control" placeholder="Email"
                                               name="studentEmail" value="${studentEmail}">
                                    </c:if>
                                </div>
                            </c:if>
                        </td>
                        </tbody>

                        <!-- Spase cap -->
                        <tbody>
                        <tr></tr>
                        </tbody>

                        <!-- Parents information -->
                        <thead>
                        <tr>
                            <th class="col-sm-2"><h4><b>Параметр</b></h4></th>
                            <th class="col-sm-6"><h4><b>Інформація про батьків</b></h4></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">ФІО батька:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.father.surname eq null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                                   name="fatherSurname">
                                        </c:if>
                                        <c:if test="${studentObject.father.surname ne null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                                   name="fatherSurname" value="${studentObject.father.surname}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${fatherSurname eq null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                                   name="fatherSurname">
                                        </c:if>
                                        <c:if test="${fatherSurname ne null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                                   name="fatherSurname" value="${fatherSurname}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.father.name eq null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                                   name="fatherName">
                                        </c:if>
                                        <c:if test="${studentObject.father.name ne null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                                   name="fatherName" value="${studentObject.father.name}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${fatherName eq null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                                   name="fatherName">
                                        </c:if>
                                        <c:if test="${fatherName ne null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                                   name="fatherName" value="${fatherName}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.father.patronimic eq null}">
                                            <input type="text" maxlength="15" class="form-control"
                                                   placeholder="По-батькові"
                                                   name="fatherPatronimic">
                                        </c:if>
                                        <c:if test="${studentObject.father.patronimic ne null}">
                                            <input type="text" maxlength="15" class="form-control"
                                                   placeholder="По-батькові"
                                                   name="fatherPatronimic" value="${studentObject.father.patronimic}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${fatherPatronimic eq null}">
                                            <input type="text" maxlength="15" class="form-control"
                                                   placeholder="По-батькові"
                                                   name="fatherPatronimic">
                                        </c:if>
                                        <c:if test="${fatherPatronimic eq null}">
                                            <input type="text" maxlength="15" class="form-control"
                                                   placeholder="По-батькові"
                                                   name="fatherPatronimic" value="${fatherPatronimic}">
                                        </c:if>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <td class="col-sm-2">Номер телефону:</td>
                        <td class="col-sm-6">
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${studentObject.father.phone1 eq null}">
                                        <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                               name="fatherPhone1">
                                    </c:if>
                                    <c:if test="${studentObject.father.phone1 ne null}">
                                        <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                               name="fatherPhone1" value="${studentObject.father.phone1}">
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${fatherPhone1 eq null}">
                                        <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                               name="fatherPhone1">
                                    </c:if>
                                    <c:if test="${fatherPhone1 ne null}">
                                        <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                               name="fatherPhone1" value="${fatherPhone1}">
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${studentObject.father.phone2 eq null}">
                                        <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                               name="fatherPhone2">
                                    </c:if>
                                    <c:if test="${studentObject.father.phone2 ne null}">
                                        <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                               name="fatherPhone2" value="${studentObject.father.phone2}">
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${fatherPhone2 eq null}">
                                        <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                               name="fatherPhone2">
                                    </c:if>
                                    <c:if test="${fatherPhone2 ne null}">
                                        <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                               name="fatherPhone2" value="${fatherPhone2}">
                                    </c:if>
                                </div>
                            </c:if>
                        </td>
                        </tbody>
                        <tbody>
                        <td class="col-sm-2">Пошта:</td>
                        <td class="col-sm-6">
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${studentObject.father.email eq null}">
                                        <input type="text" class="form-control" placeholder="Email"
                                               name="fatherEmail">
                                    </c:if>
                                    <c:if test="${studentObject.father.email ne null}">
                                        <input type="text" class="form-control" placeholder="Email"
                                               name="fatherEmail" value="${studentObject.father.email}">
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${fatherEmail eq null}">
                                        <input type="text" class="form-control" placeholder="Email"
                                               name="fatherEmail">
                                    </c:if>
                                    <c:if test="${fatherEmail ne null}">
                                        <input type="text" class="form-control" placeholder="Email"
                                               name="fatherEmail" value="${fatherEmail}">
                                    </c:if>
                                </div>
                            </c:if>
                        </td>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">ФІО матері:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.mother.surname eq null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                                   name="motherSurname">
                                        </c:if>
                                        <c:if test="${studentObject.mother.surname ne null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                                   name="motherSurname" value="${studentObject.mother.surname}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${motherSurname eq null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                                   name="motherSurname">
                                        </c:if>
                                        <c:if test="${motherSurname ne null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                                   name="motherSurname" value="${motherSurname}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.mother.name eq null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                                   name="motherName">
                                        </c:if>
                                        <c:if test="${studentObject.mother.name ne null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                                   name="motherName" value="${studentObject.mother.name}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${motherName eq null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                                   name="motherName">
                                        </c:if>
                                        <c:if test="${motherName ne null}">
                                            <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                                   name="motherName" value="${motherName}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.mother.patronimic eq null}">
                                            <input type="text" maxlength="15" class="form-control"
                                                   placeholder="По-батькові"
                                                   name="motherPatronimic">
                                        </c:if>
                                        <c:if test="${studentObject.mother.patronimic ne null}">
                                            <input type="text" maxlength="15" class="form-control"
                                                   placeholder="По-батькові"
                                                   name="motherPatronimic" value="${studentObject.mother.patronimic}">
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${motherPatronimic eq null}">
                                            <input type="text" maxlength="15" class="form-control"
                                                   placeholder="По-батькові"
                                                   name="motherPatronimic">
                                        </c:if>
                                        <c:if test="${motherPatronimic ne null}">
                                            <input type="text" maxlength="15" class="form-control"
                                                   placeholder="По-батькові"
                                                   name="motherPatronimic" value="${motherPatronimic}">
                                        </c:if>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <td class="col-sm-2">Номер телефону:</td>
                        <td class="col-sm-6">
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${studentObject.mother.phone1 eq null}">
                                        <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                               name="motherPhone1">
                                    </c:if>
                                    <c:if test="${studentObject.mother.phone1 ne null}">
                                        <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                               name="motherPhone1" value="${studentObject.mother.phone1}">
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${motherPhone1 eq null}">
                                        <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                               name="motherPhone1">
                                    </c:if>
                                    <c:if test="${motherPhone1 ne null}">
                                        <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                               name="motherPhone1" value="${motherPhone1}">
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${studentObject.mother.phone2 eq null}">
                                        <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                               name="motherPhone2">
                                    </c:if>
                                    <c:if test="${studentObject.mother.phone2 ne null}">
                                        <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                               name="motherPhone2" value="${studentObject.mother.phone2}">
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${motherPhone2 eq null}">
                                        <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                               name="motherPhone2">
                                    </c:if>
                                    <c:if test="${motherPhone2 ne null}">
                                        <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                               name="motherPhone2" value="${motherPhone2}">
                                    </c:if>
                                </div>
                            </c:if>
                        </td>
                        </tbody>
                        <tbody>
                        <td class="col-sm-2">Пошта:</td>
                        <td class="col-sm-6">
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${studentObject.mother.email eq null}">
                                        <input type="text" class="form-control" placeholder="Email"
                                               name="motherEmail">
                                    </c:if>
                                    <c:if test="${studentObject.mother.email ne null}">
                                        <input type="text" class="form-control" placeholder="Email"
                                               name="motherEmail" value="${studentObject.mother.email}">
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <c:if test="${motherEmail eq null}">
                                        <input type="text" class="form-control" placeholder="Email"
                                               name="motherEmail">
                                    </c:if>
                                    <c:if test="${motherEmail ne null}">
                                        <input type="text" class="form-control" placeholder="Email"
                                               name="motherEmail" value="${motherEmail}">
                                    </c:if>
                                </div>
                            </c:if>
                        </td>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Адреса:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-12 col-un-padding">
                                        <input id="autocomplete2" class="form-control" placeholder="Введіть адресу"
                                               type="text" name="parentAddress"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-12 col-un-padding">
                                        <input id="autocomplete2" class="form-control" placeholder="Введіть адресу"
                                               type="text" name="parentAddress"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.parentAddress.house eq null}">
                                            <input id="street_number2" class="form-control" placeholder="Будинок"
                                                   name="parentHouse" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentObject.parentAddress.house ne null}">
                                            <input id="street_number2" class="form-control" placeholder="Будинок"
                                                   name="parentHouse" disabled="true"
                                                   value="${studentObject.parentAddress.house}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${parentHouse eq null}">
                                            <input id="street_number2" class="form-control" placeholder="Будинок"
                                                   name="parentHouse" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${parentHouse ne null}">
                                            <input id="street_number2" class="form-control" placeholder="Будинок"
                                                   name="parentHouse" disabled="true" value="${parentHouse}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.parentAddress.street eq null}">
                                            <input id="route2" class="form-control" placeholder="Вулиця"
                                                   name="parentStreet" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentObject.parentAddress.street ne null}">
                                            <input id="route2" class="form-control" placeholder="Вулиця"
                                                   name="parentStreet" disabled="true"
                                                   value="${studentObject.parentAddress.street}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${parentStreet eq null}">
                                            <input id="route2" class="form-control" placeholder="Вулиця"
                                                   name="parentStreet" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${parentStreet ne null}">
                                            <input id="route2" class="form-control" placeholder="Вулиця"
                                                   name="parentStreet" disabled="true"
                                                   value="${parentStreet}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.parentAddress.city eq null}">
                                            <input id="locality2" class="form-control" placeholder="Місто"
                                                   name="parentCity" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentObject.parentAddress.city ne null}">
                                            <input id="locality2" class="form-control" placeholder="Місто"
                                                   name="parentCity" disabled="true"
                                                   value="${studentObject.parentAddress.city}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${parentCity eq null}">
                                            <input id="locality2" class="form-control" placeholder="Місто"
                                                   name="parentCity" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${parentCity ne null}">
                                            <input id="locality2" class="form-control" placeholder="Місто"
                                                   name="parentCity" disabled="true" value="${parentCity}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.parentAddress.state eq null}">
                                            <input id="administrative_area_level_12" class="form-control"
                                                   placeholder="Область"
                                                   name="parentState" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentObject.parentAddress.state ne null}">
                                            <input id="administrative_area_level_12" class="form-control"
                                                   placeholder="Область"
                                                   name="parentState" disabled="true"
                                                   value="${studentObject.parentAddress.state}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${parentState eq null}">
                                            <input id="administrative_area_level_12" class="form-control"
                                                   placeholder="Область"
                                                   name="parentState" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${parentState ne null}">
                                            <input id="administrative_area_level_12" class="form-control"
                                                   placeholder="Область"
                                                   name="parentState" disabled="true" value="${parentState}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.parentAddress.zip eq null}">
                                            <input id="postal_code2" class="form-control" placeholder="Індекс"
                                                   name="parentZip" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentObject.parentAddress.zip ne null}">
                                            <input id="postal_code2" class="form-control" placeholder="Індекс"
                                                   name="parentZip" disabled="true"
                                                   value="${studentObject.parentAddress.zip}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${parentZip eq null}">
                                            <input id="postal_code2" class="form-control"
                                                   placeholder="Індекс"
                                                   name="parentZip" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${parentZip ne null}">
                                            <input id="postal_code2" class="form-control"
                                                   placeholder="Індекс"
                                                   name="parentZip" disabled="true"
                                                   value="${parentZip}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${studentObject.parentAddress.country eq null}">
                                            <input id="country2" class="form-control" placeholder="Країна"
                                                   name="parentCountry" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${studentObject.parentAddress.country ne null}">
                                            <input id="country2" class="form-control" placeholder="Країна"
                                                   name="parentCountry" disabled="true"
                                                   value="${studentObject.parentAddress.country}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <c:if test="${parentCountry eq null}">
                                            <input id="country2" class="form-control" placeholder="Країна"
                                                   name="parentCountry" disabled="true"></input>
                                        </c:if>
                                        <c:if test="${parentCountry ne null}">
                                            <input id="country2" class="form-control" placeholder="Країна"
                                                   name="parentCountry" disabled="true"
                                                   value="${parentCountry}"></input>
                                        </c:if>
                                    </div>
                                </c:if>
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

<jsp:include page="../included_pages/footerJumbotron.jsp"/>

</body>
</html>


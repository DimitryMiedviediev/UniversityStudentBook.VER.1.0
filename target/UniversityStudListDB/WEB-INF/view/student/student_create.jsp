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
    <h2>Створити студента</h2>
    <p>Тут ви можете створити нового студента!</p>
</div>

<!-- Body -->
<div class="container">
    <div class="row">
        <form action="student_create">
            <div class="col-sm-1"></div>
            <div class="col-sm-2">

                <div style="padding: 10px"></div>

                <!-- Buttons area -->
                <input type="submit" name="save_new_student" value="Зберегти" class="btn btn-default btn-md btn-block"
                       formmethod="post"/>
                <input type="submit" name="clear_new_student" value="Очистити" class="btn btn-default btn-md btn-block"
                       formmethod="post"/>
                <input type="submit" name="back_to_list" value="Назад" class="btn btn-default btn-md btn-block"
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
                                        <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                               name="studentSurname">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding has-error">
                                        <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                               name="studentSurname" value="${studentSurname}">
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                               name="studentName">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding has-error">
                                        <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                               name="studentName" value="${studentName}">
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="15" class="form-control" placeholder="По-батькові"
                                               name="studentPatronimic">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding has-error">
                                        <input type="text" maxlength="15" class="form-control" placeholder="По-батькові"
                                               name="studentPatronimic" value="${studentPatronimic}">
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
                                                <option value="${groupList.id}">Гр: ${groupList.groupNumber} /
                                                    Кв: ${groupList.groupQualificationLevel.groupQualificationLevelTitle}
                                                    /
                                                    Сп: ${groupList.speciality.title} /
                                                    Курс ${groupList.groupCourse} /
                                                    Ф: ${groupList.groupEducationForm.groupEducationFormTitle}</option>
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
                                                <option value="${studentSubgroupList.id}">${studentSubgroupList.studentSubgroupTitle}</option>
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
                                            <option value="${studentFinancingList.id}">${studentFinancingList.studentFinancingTitle}</option>
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
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Наказ на зарахування:</td>
                            <td class="col-sm-6">
                                <select name="orderId" required>
                                    <option disabled selected>Наказ</option>
                                    <c:forEach var="orderList" items="${orderList}">
                                        <c:if test="${error eq null}">
                                            <option value="${orderList.id}">${orderList.orderNumber}/${orderList.orderType.orderTypeTitle}/${orderList.orderDate}</option>
                                        </c:if>
                                        <c:if test="${error ne null}">
                                            <c:if test="${orderId eq orderList.id}">
                                                <option value="${orderList.id}"
                                                        selected>${orderList.orderNumber}/${orderList.orderType.orderTypeTitle}/${orderList.orderDate}</option>
                                            </c:if>
                                            <c:if test="${orderId ne orderList.id}">
                                                <option value="${orderList.id}">${orderList.orderNumber}/${orderList.orderType.orderTypeTitle}/${orderList.orderDate}</option>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Номер заліковки:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="8" class="form-control"
                                               placeholder="Номер заліковки"
                                               name="studentBook">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding has-error">
                                        <input type="text" maxlength="8" class="form-control"
                                               placeholder="Номер заліковки"
                                               name="studentBook" value="${studentBook}">
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
                                        <input type="text" class="date form-control" placeholder="Дата народження"
                                               name="studentBirthDate"
                                               id="datepicker1">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" class="date form-control" placeholder="Дата народження"
                                               name="studentBirthDate"
                                               id="datepicker1" value="${studentBirthDate}">
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
                                    <input type="text" maxlength="8" class="form-control" placeholder="Номер паспорту"
                                           name="studentPassportNumber">
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" maxlength="8" class="form-control" placeholder="Номер паспорту"
                                           name="studentPassportNumber" value="${studentPassportNumber}">
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
                                        <input type="text" class="form-control" placeholder="Ким виданий"
                                               name="studentPassportOffice">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-12 col-un-padding">
                                        <input type="text" class="form-control" placeholder="Ким виданий"
                                               name="studentPassportOffice" value="${studentPassportOffice}">
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
                                        <input type="text" class="date form-control" placeholder="Коли виданий"
                                               name="studentPassportReleaseDate"
                                               id="datepicker2">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" class="date form-control" placeholder="Коли виданий"
                                               name="studentPassportReleaseDate"
                                               id="datepicker2" value="${studentPassportReleaseDate}">
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
                                        <input type="text" maxlength="10" class="form-control"
                                               placeholder="Ідентифікаційний код"
                                               name="studentIdentityCode">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="10" class="form-control"
                                               placeholder="Ідентифікаційний код"
                                               name="studentIdentityCode" value="${studentIdentityCode}">
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
                                               type="text" name="studentAddress" value="${studentAddress}"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="street_number1" class="form-control" placeholder="Будинок"
                                               name="studentHouse" disabled="true"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="street_number1" class="form-control" placeholder="Будинок"
                                               name="studentHouse" disabled="true" value="${studentHouse}"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="route1" class="form-control" placeholder="Вулиця"
                                               name="studentStreet" disabled="true"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="route1" class="form-control" placeholder="Вулиця"
                                               name="studentStreet" disabled="true" value="${studentStreet}"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="locality1" class="form-control" placeholder="Місто"
                                               name="studentCity" disabled="true"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="locality1" class="form-control" placeholder="Місто"
                                               name="studentCity" disabled="true" value="${studentCity}"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="administrative_area_level_11" class="form-control"
                                               placeholder="Область"
                                               name="studentState" disabled="true"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="administrative_area_level_11" class="form-control"
                                               placeholder="Область"
                                               name="studentState" disabled="true" value="${studentState}"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="postal_code1" class="form-control" placeholder="Індекс"
                                               name="studentZip" disabled="true"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="postal_code1" class="form-control" placeholder="Індекс"
                                               name="studentZip" disabled="true" value="${studentZip}"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="country1" class="form-control" placeholder="Країна"
                                               name="studentCountry" disabled="true"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="country1" class="form-control" placeholder="Країна"
                                               name="studentCountry" disabled="true" value="${studentCountry}"></input>
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
                                    <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                           name="studentPhone1">
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                           name="studentPhone1" value="${studentPhone1}">
                                </div>
                            </c:if>
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                           name="studentPhone2">
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                           name="studentPhone2" value="${studentPhone2}">
                                </div>
                            </c:if>
                        </td>
                        </tbody>
                        <tbody>
                        <td class="col-sm-2">Пошта:</td>
                        <td class="col-sm-6">
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="form-control" placeholder="Email"
                                           name="studentEmail">
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="form-control" placeholder="Email"
                                           name="studentEmail" value="${studentEmail}">
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
                                        <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                               name="fatherSurname">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                               name="fatherSurname" value="${fatherSurname}">
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                               name="fatherName">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                               name="fatherName" value="${fatherName}">
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="15" class="form-control" placeholder="По-батькові"
                                               name="fatherPatronimic">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="15" class="form-control" placeholder="По-батькові"
                                               name="fatherPatronimic" value="${fatherPatronimic}">
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
                                    <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                           name="fatherPhone1">
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                           name="fatherPhone1" value="${fatherPhone1}">
                                </div>
                            </c:if>
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                           name="fatherPhone2">
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                           name="fatherPhone2" value="${fatherPhone2}">
                                </div>
                            </c:if>
                        </td>
                        </tbody>
                        <tbody>
                        <td class="col-sm-2">Пошта:</td>
                        <td class="col-sm-6">
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="form-control" placeholder="Email"
                                           name="fatherEmail">
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="form-control" placeholder="Email"
                                           name="fatherEmail" value="${fatherEmail}">
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
                                        <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                               name="motherSurname">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                               name="motherSurname" value="${motherSurname}">
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                               name="motherName">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                               name="motherName" value="${motherName}">
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="15" class="form-control" placeholder="По-батькові"
                                               name="motherPatronimic">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="15" class="form-control" placeholder="По-батькові"
                                               name="motherPatronimic" value="${motherPatronimic}">
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
                                    <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                           name="motherPhone1">
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                           name="motherPhone1" value="${motherPhone1}">
                                </div>
                            </c:if>
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                           name="motherPhone2">
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                           name="motherPhone2" value="${motherPhone2}">
                                </div>
                            </c:if>
                        </td>
                        </tbody>
                        <tbody>
                        <td class="col-sm-2">Пошта:</td>
                        <td class="col-sm-6">
                            <c:if test="${error eq null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="form-control" placeholder="Email"
                                           name="motherEmail">
                                </div>
                            </c:if>
                            <c:if test="${error ne null}">
                                <div class="col-sm-6 col-un-padding">
                                    <input type="text" class="form-control" placeholder="Email"
                                           name="motherEmail" value="${motherEmail}">
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
                                               type="text" name="parentAddress" value="${parentAddress}"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="street_number2" class="form-control" placeholder="Будинок"
                                               name="parentHouse" disabled="true"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="street_number2" class="form-control" placeholder="Будинок"
                                               name="parentHouse" disabled="true" value="${parentHouse}"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="route2" class="form-control" placeholder="Вулиця"
                                               name="parentStreet" disabled="true"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="route2" class="form-control" placeholder="Вулиця"
                                               name="parentStreet" disabled="true" value="${parentStreet}"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="locality2" class="form-control" placeholder="Місто"
                                               name="parentCity" disabled="true"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="locality2" class="form-control" placeholder="Місто"
                                               name="parentCity" disabled="true" value="${parentCity}"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="administrative_area_level_12" class="form-control"
                                               placeholder="Область"
                                               name="parentState" disabled="true"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="administrative_area_level_12" class="form-control"
                                               placeholder="Область"
                                               name="parentState" disabled="true" value="${parentState}"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="postal_code2" class="form-control" placeholder="Індекс"
                                               name="parentZip" disabled="true"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="postal_code2" class="form-control" placeholder="Індекс"
                                               name="parentZip" disabled="true" value="${parentZip}"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="country2" class="form-control" placeholder="Країна"
                                               name="parentCountry" disabled="true"></input>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input id="country2" class="form-control" placeholder="Країна"
                                               name="parentCountry" disabled="true" value="${parentCountry}"></input>
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


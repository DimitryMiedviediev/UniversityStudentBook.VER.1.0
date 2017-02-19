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

    <!-- Google Map API - Autocomplete Address Form-->
    <script src="../js/google_api.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAd_ui82vz6dh7LzxoSF3nO4Sv3UeIQ0zg&signed_in=true&libraries=places&callback=initAutocomplete"
            async defer></script>

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
    <script>
        $(function () {
            $("#datepicker3").datepicker();
        });
    </script>

    <!-- jQuery mask for input types -->
    <script type="text/javascript" src="../js/jquery.mask.js"></script>
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

<jsp:include page="included_pages/fixedTopNavbar.jsp"/>

<!-- Header Jumbotron -->
<div class="jumbotron text-center">
    <h2>Додати студента</h2>
    <p>Тут ви можете ввести дані нового студента!</p>
</div>

<!-- Body -->
<div class="container">
    <div class="row">
        <form action="student_add" method="post">
            <div class="col-sm-1"></div>
            <div class="col-sm-2">
                <!-- Profile photo -->
                <%--<div class="center-block">--%>
                    <%--<input type="file" name="pic" accept="image/*">--%>
                <%--</div>--%>

                <div style="padding: 10px"></div>

                <!-- Buttons area -->
                <input type="submit" name="save_btn" value="Зберегти" class="btn btn-default btn-md btn-block" formmethod="post"/>
                <input type="submit" name="clear_btn" value="Очистити" class="btn btn-default btn-md btn-block" formmethod="post"/>

            </div>
            <div class="col-sm-8">
                <!-- Information -->
                <div class="container">

                    <table class="table table-condensed font-table">
                        <!-- University information -->
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
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                           name="surname_student">
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                           name="name_student">
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" maxlength="15" class="form-control" placeholder="По-батькові"
                                           name="lastname_student">
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Дата вступу:</td>
                            <td class="col-sm-6">
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" class="date form-control" placeholder="Дата вступу"
                                           name="date_entry"
                                           id="datepicker1">
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Статус:</td>
                            <td class="col-sm-6">
                                <select name="status" required>
                                    <option selected>Навчається</option>
                                    <option>Відрахований</option>
                                    <option>Академічна відпустка</option>
                                    <option>Закінчив навчання</option>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Група/Підгрупа:</td>
                            <td class="col-sm-2">
                                <select name="group" required>
                                    <c:forEach var="groupList" items="${groupList}">
                                        <option>${groupList.number}</option>
                                    </c:forEach>
                                </select>
                                <select name="subgroup">
                                    <option selected disabled>Підгрупа</option>
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Фінансування:</td>
                            <td class="col-sm-6"><select name="financing" required>
                                <option selected>Державне</option>
                                <option>Комерційне</option>
                            </select></td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Номер заліковки:</td>
                            <td class="col-sm-6">
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" maxlength="8" class="form-control" placeholder="Номер заліковки"
                                           name="stud_book">
                                </div>
                            </td>
                        </tr>
                        </tbody>

                        <!-- Spase cap -->
                        <tbody>
                        <tr></tr>
                        </tbody>

                        <!-- Student information -->
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
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" class="date form-control" placeholder="Дата народження"
                                           name="date_birth"
                                           id="datepicker2">
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <td class="col-sm-2">Номер паспорту:</td>
                        <td class="col-sm-6">
                            <div class="col-sm-4 col-un-padding">
                                <input type="text" maxlength="8" class="form-control" placeholder="Номер паспорту"
                                       name="passp_serial">
                            </div>
                        </td>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Паспорт виданий:</td>
                            <td class="col-sm-6">
                                <div class="col-sm-12 col-un-padding">
                                    <input type="text" class="form-control" placeholder="Ким виданий"
                                           name="passp_office">
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Дата видачі паспорту:</td>
                            <td class="col-sm-6">
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" class="date form-control" placeholder="Коли виданий"
                                           name="date_release"
                                           id="datepicker3">
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Ідентифікаційний код:</td>
                            <td class="col-sm-6">
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" maxlength="10" class="form-control"
                                           placeholder="Ідентифікаційний код"
                                           name="identity_code">
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Адреса:</td>
                            <td class="col-sm-6">
                                <div class="col-sm-12 col-un-padding">
                                    <input id="autocomplete1" class="form-control" placeholder="Введіть адресу"
                                           type="text"></input>
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input id="street_number1" class="form-control" placeholder="Будинок"
                                           name="student_house" disabled="true"></input>
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input id="route1" class="form-control" placeholder="Вулиця"
                                           name="student_street" disabled="true"></input>
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input id="locality1" class="form-control" placeholder="Місто"
                                           name="student_city" disabled="true"></input>
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input id="administrative_area_level_11" class="form-control" placeholder="Область"
                                           name="student_state" disabled="true"></input>
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input id="postal_code1" class="form-control" placeholder="Індекс"
                                           name="student_zip" disabled="true"></input>
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input id="country1" class="form-control" placeholder="Країна"
                                           name="student_country" disabled="true"></input>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <td class="col-sm-2">Номер телефону:</td>
                        <td class="col-sm-6">
                            <div class="col-sm-6 col-un-padding">
                                <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                       name="student_phone_1">
                            </div>
                            <div class="col-sm-6 col-un-padding">
                                <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                       name="student_phone_2">
                            </div>
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
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                           name="surname_father">
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                           name="name_father">
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" maxlength="15" class="form-control" placeholder="По-батькові"
                                           name="lastname_father">
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <td class="col-sm-2">Номер телефону:</td>
                        <td class="col-sm-6">
                            <div class="col-sm-6 col-un-padding">
                                <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                       name="father_phone_1">
                            </div>
                            <div class="col-sm-6 col-un-padding">
                                <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                       name="father_phone_2">
                            </div>
                        </td>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">ФІО матері:</td>
                            <td class="col-sm-6">
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" maxlength="15" class="form-control" placeholder="Фамілія"
                                           name="surname_mother">
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" maxlength="15" class="form-control" placeholder="Ім'я"
                                           name="name_mother">
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input type="text" maxlength="15" class="form-control" placeholder="По-батькові"
                                           name="lastname_mother">
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <td class="col-sm-2">Номер телефону:</td>
                        <td class="col-sm-6">
                            <div class="col-sm-6 col-un-padding">
                                <input type="text" class="phone_us form-control" placeholder="Перший номер"
                                       name="mother_phone_1">
                            </div>
                            <div class="col-sm-6 col-un-padding">
                                <input type="text" class="phone_us form-control" placeholder="Другий номер"
                                       name="mother_phone_2">
                            </div>
                        </td>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Адреса:</td>
                            <td class="col-sm-6">
                                <div class="col-sm-12 col-un-padding">
                                    <input id="autocomplete2" class="form-control" placeholder="Введіть адресу"
                                           type="text"></input>
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input id="street_number2" class="form-control" placeholder="Будинок"
                                           name="parent_house" disabled="true"></input>
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input id="route2" class="form-control" placeholder="Вулиця"
                                           name="parent_street" disabled="true"></input>
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input id="locality2" class="form-control" placeholder="Місто"
                                           name="parent_city" disabled="true"></input>
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input id="administrative_area_level_12" class="form-control" placeholder="Область"
                                           name="parent_state" disabled="true"></input>
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input id="postal_code2" class="form-control" placeholder="Індекс"
                                           name="parent_zip" disabled="true"></input>
                                </div>
                                <div class="col-sm-4 col-un-padding">
                                    <input id="country2" class="form-control" placeholder="Країна"
                                           name="parent_country" disabled="true"></input>
                                </div>
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


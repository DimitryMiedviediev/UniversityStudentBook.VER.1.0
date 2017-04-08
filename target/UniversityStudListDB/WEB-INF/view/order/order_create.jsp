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
    <h2>Створити наказ</h2>
    <p>Тут ви можете створити новий наказ!</p>
</div>

<!-- Body -->
<div class="container">
    <div class="row">
        <form action="order_create">
            <div class="col-sm-1"></div>
            <div class="col-sm-2">

                <div style="padding: 10px"></div>

                <!-- Buttons area -->
                <input type="submit" name="save_new_order" value="Зберегти" class="btn btn-default btn-md btn-block"
                       formmethod="post"/>
                <input type="submit" name="clear_new_order" value="Очистити" class="btn btn-default btn-md btn-block"
                       formmethod="post"/>
                <input type="submit" name="back_to_list" value="Назад" class="btn btn-default btn-md btn-block"
                       formmethod="post"/>

            </div>
            <div class="col-sm-8">
                <!-- Information -->
                <div class="container">

                    <table class="table table-condensed font-table">
                        <!-- University information -->
                        <thead>
                        <tr>
                            <th class="col-sm-2"><h4><b>Параметр</b></h4></th>
                            <th class="col-sm-6"><h4><b>Інформація про наказ</b></h4></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Номер наказу:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" maxlength="15" class="form-control"
                                               placeholder="Номер наказу"
                                               name="orderNumber">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding has-error">
                                        <input type="text" maxlength="15" class="form-control"
                                               placeholder="Номер наказу"
                                               name="orderNumber" value="${orderNumber}">
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Дата наказу:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <input type="text" class="date form-control" placeholder="Дата наказу"
                                               name="orderDate"
                                               id="datepicker1">
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding has-error">
                                        <input type="text" class="date form-control" placeholder="Дата наказу"
                                               name="orderDate"
                                               id="datepicker1" value="${orderDate}">
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Тип наказу:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <select name="orderType" required>
                                            <option disabled selected>Тип наказу</option>
                                            <c:forEach var="orderTypesList" items="${orderTypesList}">
                                                <option value="${orderTypesList.id}">${orderTypesList.orderTypeTitle}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-4 col-un-padding">
                                        <select name="orderType" required>
                                            <c:if test="${orderTypesList ne null}">
                                                <option disabled selected>Тип наказу</option>
                                            </c:if>
                                            <c:if test="${orderTypesList eq null}">
                                                <option disabled>Тип наказу</option>
                                            </c:if>
                                            <c:forEach var="orderTypesList" items="${orderTypesList}">
                                                <c:if test="${orderTypesList.id eq orderType}">
                                                    <option value="${orderTypesList.id}"
                                                            selected>${orderTypesList.orderTypeTitle}</option>
                                                </c:if>
                                                <c:if test="${orderTypesList.id ne orderType}">
                                                    <option value="${orderTypesList.id}">${orderTypesList.orderTypeTitle}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <td class="col-sm-2">Коментарі:</td>
                            <td class="col-sm-6">
                                <c:if test="${error eq null}">
                                    <div class="col-sm-12 col-un-padding">
                                <textarea class="form-control" rows="3"
                                          placeholder="Коментар до наказу"
                                          name="orderComment"></textarea>
                                    </div>
                                </c:if>
                                <c:if test="${error ne null}">
                                    <div class="col-sm-12 col-un-padding has-error">
                                    <textarea class="form-control" rows="3"
                                              placeholder="Коментар до наказу"
                                              name="orderComment">${orderComment}</textarea>
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


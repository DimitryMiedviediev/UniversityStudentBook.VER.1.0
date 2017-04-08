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
    <h2>Список наказів</h2>
    <p>Це список наказів вашого університету!</p>
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

                <form action="order_create" method="post">
                    <input type="submit" name="order_create" value="Створити наказ"
                           class="btn btn-default btn-md btn-block"
                           formmethod="post"/>
                </form>

                <form action="order_configuration" method="post">
                    <input type="submit" name="order_config" value="Конфігурація наказів"
                           class="btn btn-default btn-md btn-block"
                           formmethod="post"/>
                </form>

                <form action="order_list" method="post">
                    <h5>Номер наказу</h5>
                    <ul>
                        <c:if test="${orderNumberParameter ne null}">
                            <input type="text" placeholder="Номер наказу" name="orderNumber" class="form-control"
                                   list="orders"
                                   value="${orderNumberParameter}">
                        </c:if>
                        <c:if test="${orderNumberParameter == null}">
                            <input type="text" placeholder="Номер наказу" name="orderNumber" class="form-control"
                                   list="orders">
                        </c:if>
                        <datalist id="orders">
                            <c:forEach var="orderNumberList" items="${orderNumberList}">
                                <option value="${orderNumberList.key}"></option>
                            </c:forEach>
                        </datalist>
                    </ul>
                    <hr>
                    <h5>Тип наказу</h5>
                    <ul>
                        <c:forEach var="orderTypeList" items="${orderTypeList}">
                            <c:if test="${orderTypeList.value eq true}">
                                <li><h6><input type="checkbox" name="orderType=${orderTypeList.key}"
                                               value="orderType=${orderTypeList.key}"
                                               checked> ${orderTypeList.key} </h6></li>
                            </c:if>
                            <c:if test="${orderTypeList.value eq false}">
                                <li><h6><input type="checkbox" name="orderType=${orderTypeList.key}"
                                               value="orderType=${orderTypeList.key}"> ${orderTypeList.key} </h6></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <hr>
                    <h5>Дата наказу</h5>
                    <ul>
                        <c:if test="${orderDateParameter eq null}">
                            <input type="text" class="date form-control" placeholder="Дата наказу"
                                   name="orderDate"
                                   id="datepicker1">
                        </c:if>
                        <c:if test="${orderDateParameter ne null}">
                            <input type="text" class="date form-control" placeholder="Дата наказу"
                                   name="orderDate"
                                   id="datepicker1" value="${orderDateParameter}">
                        </c:if>
                    </ul>
                    <hr>
                    <input type="submit" value="Сортувати" name="order_sort" class="btn btn-default btn-block" formmethod="post">
                </form>
            </div>

        </div>
        <div class="col-sm-8">
            <!-- Tables -->
            <div class="container">
                <table id="stupid" class="table table-hover font-table">
                    <thead>
                    <tr>
                        <th data-sort="string-ins">Номер наказу</th>
                        <th data-sort="string-ins">Тип наказу</th>
                        <th data-sort="string-ins">Дата наказу</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="orderList" items="${orderList}">
                        <tr>
                            <td><a href="order_info?order_id=${orderList.id}">${orderList.orderNumber}</a></td>
                            <td><a href="order_info?order_id=${orderList.id}">${orderList.orderType.orderTypeTitle}</a></td>
                            <td><a href="order_info?order_id=${orderList.id}">${orderList.orderDate}</a></td>
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
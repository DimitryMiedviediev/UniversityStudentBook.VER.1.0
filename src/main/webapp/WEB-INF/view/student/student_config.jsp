<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Контрольна панель</title>

    <!-- Bootstrap CSS-->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">

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

</head>

<body>

<jsp:include page="../included_pages/fixedTopNavbar.jsp"/>

<!-- Header Jumbotron -->
<div class="jumbotron text-center">
    <h2>Конфігураційна панель студентів</h2>
    <p>Тут ви можете редагувати конфігурацію студентів!</p>
</div>

<!-- Body -->
<div class="container">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <!-- Information -->
            <div class="container">

                <table class="table table-condensed font-table">
                    <!-- Subgroup information -->
                    <thead>
                    <tr>
                        <th class="col-sm-6 text-center"><h4><b>Підгрупа</b></h4></th>
                        <th class="col-sm-2 text-center"><h4><b>Дії</b></h4></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:if test="${createSubgroup eq null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для створення нової підгрупи!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Створити" name="subgroup_create"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${createSubgroup ne null}">
                            <c:if test="${wrongSubgroup eq null}">
                                <form action="student_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6 col-un-padding">
                                            <input type="text" maxlength="30" class="form-control normal-area-input"
                                                   placeholder="Номер нової підгрупи"
                                                   name="subgroup">
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Зберегти" name="subgroup_create_option"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            <input type="submit" value="Відмінити" name="cancel"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            </button>
                                        </div>
                                    </td>
                                </form>
                            </c:if>
                            <c:if test="${wrongSubgroup ne null}">
                                <form action="student_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6 col-un-padding has-error">
                                            <input type="text" maxlength="30" class="form-control normal-area-input"
                                                   placeholder="Номер нової підгрупи"
                                                   name="subgroup" value="${wrongSubgroup}">
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Зберегти" name="subgroup_create_option"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            <input type="submit" value="Відмінити" name="cancel"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            </button>
                                        </div>
                                    </td>
                                </form>
                            </c:if>
                        </c:if>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <c:if test="${updateSubgroup eq null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для вибору підгрупи для редагування!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Редагувати" name="subgroup_update"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${updateSubgroup ne null}">
                            <c:if test="${updateSubgroupArea eq null}">
                                <form action="student_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6">
                                            <select name="update_subgroup" required>
                                                <option disabled selected>Підгрупа</option>
                                                <c:forEach var="subgroupsList"
                                                           items="${subgroupsList}">
                                                    <option value="${subgroupsList.id}">${subgroupsList.studentSubgroupTitle}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Вибрати" name="subgroup_update_option"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            <input type="submit" value="Відмінити" name="cancel"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            </button>
                                        </div>
                                    </td>
                                </form>
                            </c:if>
                            <c:if test="${updateSubgroupArea ne null}">
                                <c:if test="${updateSubgroupAreaError eq null}">
                                    <form action="student_configuration" method="post">
                                        <td class="col-sm-6">
                                            <div class="col-sm-3"></div>
                                            <div class="col-sm-6 col-un-padding">
                                                <input type="text" maxlength="30" class="form-control normal-area-input"
                                                       placeholder="Новий номер підгрупи" name="subgroup_title"
                                                       value="${subgroupToUpdate.studentSubgroupTitle}">
                                                <input type="hidden" maxlength="30"
                                                       class="form-control normal-area-input"
                                                       name="subgroup_id" value="${subgroupToUpdate.id}">
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </td>
                                        <td class="col-sm-2">
                                            <div class="col-sm-12 col-un-padding">
                                                <input type="submit" value="Зберегти" name="subgroup_update_save"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                <input type="submit" value="Відмінити" name="cancel"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                </button>
                                            </div>
                                        </td>
                                    </form>
                                </c:if>
                                <c:if test="${updateSubgroupAreaError ne null}">
                                    <form action="student_configuration" method="post">
                                        <td class="col-sm-6">
                                            <div class="col-sm-3"></div>
                                            <div class="col-sm-6 col-un-padding has-error">
                                                <input type="text" maxlength="30" class="form-control normal-area-input"
                                                       placeholder="Новий номер групи" name="student_title"
                                                       value="${subgroupTitleError}">
                                                <input type="hidden" maxlength="30"
                                                       class="form-control normal-area-input"
                                                       name="subgroup_id" value="${subgroupIdError}">
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </td>
                                        <td class="col-sm-2">
                                            <div class="col-sm-12 col-un-padding">
                                                <input type="submit" value="Зберегти" name="subgroup_update_save"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                <input type="submit" value="Відмінити" name="cancel"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                </button>
                                            </div>
                                        </td>
                                    </form>
                                </c:if>
                            </c:if>
                        </c:if>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <c:if test="${deleteSubgroup eq null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для вибору підгрупи для видалення!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Видалити" name="subgroup_delete"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${deleteSubgroup ne null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <div class="col-sm-3"></div>
                                    <div class="col-sm-6">
                                        <select name="delete_subgroup" required>
                                            <option disabled selected>Підгрупа</option>
                                            <c:forEach var="subgroupsList" items="${subgroupsList}">
                                                <option value="${subgroupsList.id}">${subgroupsList.studentSubgroupTitle}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-3"></div>
                                </td>
                                <td class="col-sm-2">
                                    <div class="col-sm-12 col-un-padding">
                                        <input type="submit" value="Видалити" name="subgroup_delete_option"
                                               class="btn btn-default btn-md btn-block" formmethod="post"/>
                                        <input type="submit" value="Відмінити" name="cancel"
                                               class="btn btn-default btn-md btn-block" formmethod="post"/>
                                        </button>
                                    </div>
                                </td>
                            </form>
                        </c:if>
                    </tr>
                    </tbody>
                    <%--<!-- Financing information -->--%>
                    <thead>
                    <tr>
                        <th class="col-sm-6 text-center"><h4><b>Фінансування</b></h4></th>
                        <th class="col-sm-2 text-center"><h4><b>Дії</b></h4></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:if test="${createFinancing eq null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для створення нового типу фінансування!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Створити" name="financing_create"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${createFinancing ne null}">
                            <c:if test="${wrongFinancing eq null}">
                                <form action="student_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6 col-un-padding">
                                            <input type="text" maxlength="30" class="form-control normal-area-input"
                                                   placeholder="Назва нового типу фінансування"
                                                   name="financing">
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Зберегти"
                                                   name="financing_create_option"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            <input type="submit" value="Відмінити" name="cancel"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            </button>
                                        </div>
                                    </td>
                                </form>
                            </c:if>
                            <c:if test="${wrongFinancing ne null}">
                                <form action="student_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6 col-un-padding has-error">
                                            <input type="text" maxlength="30" class="form-control normal-area-input"
                                                   placeholder="Назва нового типу фінансування"
                                                   name="financing" value="${wrongFinancing}">
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Зберегти"
                                                   name="financing_create_option"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            <input type="submit" value="Відмінити" name="cancel"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            </button>
                                        </div>
                                    </td>
                                </form>
                            </c:if>
                        </c:if>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <c:if test="${updateFinancing eq null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для вибору типу фінансування на редагування!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Редагувати" name="financing_update"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${updateFinancing ne null}">
                            <c:if test="${updateFinancingArea eq null}">
                                <form action="student_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6">
                                            <select name="update_financing" required>
                                                <option disabled selected>Тип фінансування</option>
                                                <c:forEach var="financingList"
                                                           items="${financingList}">
                                                    <option value="${financingList.id}">${financingList.studentFinancingTitle}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Вибрати"
                                                   name="financing_update_option"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            <input type="submit" value="Відмінити" name="cancel"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            </button>
                                        </div>
                                    </td>
                                </form>
                            </c:if>
                            <c:if test="${updateFinancingArea ne null}">
                                <c:if test="${updateFinancingAreaError eq null}">
                                    <form action="student_configuration" method="post">
                                        <td class="col-sm-6">
                                            <div class="col-sm-3"></div>
                                            <div class="col-sm-6 col-un-padding">
                                                <input type="text" maxlength="30" class="form-control normal-area-input"
                                                       placeholder="Нова назва типу фінансування"
                                                       name="financing_title"
                                                       value="${financingToUpdate.studentFinancingTitle}">
                                                <input type="hidden" maxlength="30"
                                                       class="form-control normal-area-input"
                                                       name="financing_id"
                                                       value="${financingToUpdate.id}">
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </td>
                                        <td class="col-sm-2">
                                            <div class="col-sm-12 col-un-padding">
                                                <input type="submit" value="Зберегти"
                                                       name="financing_update_save"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                <input type="submit" value="Відмінити" name="cancel"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                </button>
                                            </div>
                                        </td>
                                    </form>
                                </c:if>
                                <c:if test="${updateFinancingAreaError ne null}">
                                    <form action="student_configuration" method="post">
                                        <td class="col-sm-6">
                                            <div class="col-sm-3"></div>
                                            <div class="col-sm-6 col-un-padding has-error">
                                                <input type="text" maxlength="30" class="form-control normal-area-input"
                                                       placeholder="Нова назва типу фінансування"
                                                       name="financing_title"
                                                       value="${financingTitleError}">
                                                <input type="hidden" maxlength="30"
                                                       class="form-control normal-area-input"
                                                       name="financing_id"
                                                       value="${financingIdError}">
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </td>
                                        <td class="col-sm-2">
                                            <div class="col-sm-12 col-un-padding">
                                                <input type="submit" value="Зберегти"
                                                       name="financing_update_save"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                <input type="submit" value="Відмінити" name="cancel"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                </button>
                                            </div>
                                        </td>
                                    </form>
                                </c:if>
                            </c:if>
                        </c:if>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <c:if test="${deleteFinancing eq null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для вибору типу фінансування на видалення!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Видалити" name="financing_delete"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${deleteFinancing ne null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <div class="col-sm-3"></div>
                                    <div class="col-sm-6">
                                        <select name="delete_financing" required>
                                            <option disabled selected>Тип фінансування</option>
                                            <c:forEach var="financingList"
                                                       items="${financingList}">
                                                <option value="${financingList.id}">${financingList.studentFinancingTitle}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-3"></div>
                                </td>
                                <td class="col-sm-2">
                                    <div class="col-sm-12 col-un-padding">
                                        <input type="submit" value="Видалити" name="financing_delete_option"
                                               class="btn btn-default btn-md btn-block" formmethod="post"/>
                                        <input type="submit" value="Відмінити" name="cancel"
                                               class="btn btn-default btn-md btn-block" formmethod="post"/>
                                        </button>
                                    </div>
                                </td>
                            </form>
                        </c:if>
                    </tr>
                    </tbody>
                    <!-- Status information -->
                    <thead>
                    <tr>
                        <th class="col-sm-6 text-center"><h4><b>Статус</b></h4></th>
                        <th class="col-sm-2 text-center"><h4><b>Дії</b></h4></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:if test="${createStatus eq null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для створення нового статусу!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Створити" name="status_create"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${createStatus ne null}">
                            <c:if test="${wrongStatus eq null}">
                                <form action="student_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6 col-un-padding">
                                            <input type="text" maxlength="30" class="form-control normal-area-input"
                                                   placeholder="Назва нового статусу"
                                                   name="status">
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Зберегти" name="status_create_option"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            <input type="submit" value="Відмінити" name="cancel"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            </button>
                                        </div>
                                    </td>
                                </form>
                            </c:if>
                            <c:if test="${wrongStatus ne null}">
                                <form action="student_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6 col-un-padding has-error">
                                            <input type="text" maxlength="30" class="form-control normal-area-input"
                                                   placeholder="Назва нового статусу"
                                                   name="status" value="${wrongStatus}">
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Зберегти" name="status_create_option"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            <input type="submit" value="Відмінити" name="cancel"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            </button>
                                        </div>
                                    </td>
                                </form>
                            </c:if>
                        </c:if>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <c:if test="${updateStatus eq null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для вибору статусу для редагування!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Редагувати" name="status_update"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${updateStatus ne null}">
                            <c:if test="${updateStatusArea eq null}">
                                <form action="student_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6">
                                            <select name="update_status" required>
                                                <option disabled selected>Статус</option>
                                                <c:forEach var="statusList" items="${statusList}">
                                                    <option value="${statusList.id}">${statusList.studentStatusTitle}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Вибрати" name="status_update_option"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            <input type="submit" value="Відмінити" name="cancel"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            </button>
                                        </div>
                                    </td>
                                </form>
                            </c:if>
                            <c:if test="${updateStatusArea ne null}">
                                <c:if test="${updateStatusAreaError eq null}">
                                    <form action="student_configuration" method="post">
                                        <td class="col-sm-6">
                                            <div class="col-sm-3"></div>
                                            <div class="col-sm-6 col-un-padding">
                                                <input type="text" maxlength="30" class="form-control normal-area-input"
                                                       placeholder="Назва нового статусу" name="status_title"
                                                       value="${statusToUpdate.studentStatusTitle}">
                                                <input type="hidden" maxlength="30"
                                                       class="form-control normal-area-input"
                                                       name="status_id" value="${statusToUpdate.id}">
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </td>
                                        <td class="col-sm-2">
                                            <div class="col-sm-12 col-un-padding">
                                                <input type="submit" value="Зберегти" name="status_update_save"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                <input type="submit" value="Відмінити" name="cancel"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                </button>
                                            </div>
                                        </td>
                                    </form>
                                </c:if>
                                <c:if test="${updateStatusAreaError ne null}">
                                    <form action="student_configuration" method="post">
                                        <td class="col-sm-6">
                                            <div class="col-sm-3"></div>
                                            <div class="col-sm-6 col-un-padding has-error">
                                                <input type="text" maxlength="30" class="form-control normal-area-input"
                                                       placeholder="Назва нового статусу" name="status_title"
                                                       value="${statusTitleError}">
                                                <input type="hidden" maxlength="30"
                                                       class="form-control normal-area-input"
                                                       name="status_id" value="${statusIdError}">
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </td>
                                        <td class="col-sm-2">
                                            <div class="col-sm-12 col-un-padding">
                                                <input type="submit" value="Зберегти" name="status_update_save"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                <input type="submit" value="Відмінити" name="cancel"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                </button>
                                            </div>
                                        </td>
                                    </form>
                                </c:if>
                            </c:if>
                        </c:if>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <c:if test="${deleteStatus eq null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для вибору статусу на видалення!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Видалити" name="status_delete"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${deleteStatus ne null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <div class="col-sm-3"></div>
                                    <div class="col-sm-6">
                                        <select name="delete_status" required>
                                            <option disabled selected>Статус</option>
                                            <c:forEach var="statusList" items="${statusList}">
                                                <option value="${statusList.id}">${statusList.studentStatusTitle}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-3"></div>
                                </td>
                                <td class="col-sm-2">
                                    <div class="col-sm-12 col-un-padding">
                                        <input type="submit" value="Видалити" name="status_delete_option"
                                               class="btn btn-default btn-md btn-block" formmethod="post"/>
                                        <input type="submit" value="Відмінити" name="cancel"
                                               class="btn btn-default btn-md btn-block" formmethod="post"/>
                                        </button>
                                    </div>
                                </td>
                            </form>
                        </c:if>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <c:if test="${trueStatus eq null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для вибору дефолтного статусу!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Вибрати" name="status_true"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${trueStatus ne null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <div class="col-sm-3"></div>
                                    <div class="col-sm-6">
                                        <select name="true_status" required>
                                            <option disabled selected>Статус</option>
                                            <c:forEach var="statusList" items="${statusList}">
                                                <option value="${statusList.id}">${statusList.studentStatusTitle}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-3"></div>
                                </td>
                                <td class="col-sm-2">
                                    <div class="col-sm-12 col-un-padding">
                                        <input type="submit" value="Вибрати" name="status_true_option"
                                               class="btn btn-default btn-md btn-block" formmethod="post"/>
                                        <input type="submit" value="Відмінити" name="cancel"
                                               class="btn btn-default btn-md btn-block" formmethod="post"/>
                                        </button>
                                    </div>
                                </td>
                            </form>
                        </c:if>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <c:if test="${falseStatus eq null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для вибору статусів збереження данних!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Вибрати" name="status_false"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${falseStatus ne null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <div class="col-sm-3"></div>
                                    <div class="col-sm-6">
                                        <select name="false_status" required size="15" multiple>
                                            <option disabled selected>Статус</option>
                                            <c:forEach var="statusList" items="${statusList}">
                                                <option value="${statusList.id}">${statusList.studentStatusTitle}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-3"></div>
                                </td>
                                <td class="col-sm-2">
                                    <div class="col-sm-12 col-un-padding">
                                        <input type="submit" value="Вибрати" name="status_false_option"
                                               class="btn btn-default btn-md btn-block" formmethod="post"/>
                                        <input type="submit" value="Відмінити" name="cancel"
                                               class="btn btn-default btn-md btn-block" formmethod="post"/>
                                        </button>
                                    </div>
                                </td>
                            </form>
                        </c:if>
                    </tr>
                    </tbody>
                    <tbody>
                    <tr>
                        <c:if test="${nullStatus eq null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для вибору статусів збереження груп!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Вибрати" name="status_null"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${nullStatus ne null}">
                            <form action="student_configuration" method="post">
                                <td class="col-sm-6">
                                    <div class="col-sm-3"></div>
                                    <div class="col-sm-6">
                                        <select name="null_status" required size="15" multiple>
                                            <option disabled selected>Статус</option>
                                            <c:forEach var="statusList" items="${statusList}">
                                                <option value="${statusList.id}">${statusList.studentStatusTitle}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-3"></div>
                                </td>
                                <td class="col-sm-2">
                                    <div class="col-sm-12 col-un-padding">
                                        <input type="submit" value="Вибрати" name="status_null_option"
                                               class="btn btn-default btn-md btn-block" formmethod="post"/>
                                        <input type="submit" value="Відмінити" name="cancel"
                                               class="btn btn-default btn-md btn-block" formmethod="post"/>
                                        </button>
                                    </div>
                                </td>
                            </form>
                        </c:if>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>
        <div class="col-sm-2"></div>
    </div>
</div>

<jsp:include page="../included_pages/footerJumbotron.jsp"/>

</body>
</html>
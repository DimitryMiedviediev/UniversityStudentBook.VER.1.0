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
    <h2>Конфігураційна панель груп</h2>
    <p>Тут ви можете редагувати конфігурацію груп!</p>
</div>

<!-- Body -->
<div class="container">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <!-- Information -->
            <div class="container">

                <table class="table table-condensed font-table">
                    <!-- Education form information -->
                    <thead>
                    <tr>
                        <th class="col-sm-6 text-center"><h4><b>Форма навчання</b></h4></th>
                        <th class="col-sm-2 text-center"><h4><b>Дії</b></h4></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:if test="${createEducationForm eq null}">
                            <form action="group_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для створення нової форми навчання!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Створити" name="education_form_create"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${createEducationForm ne null}">
                            <c:if test="${wrongEducationForm eq null}">
                                <form action="group_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6 col-un-padding">
                                            <input type="text" maxlength="30" class="form-control normal-area-input"
                                                   placeholder="Назва нової форми навчання"
                                                   name="education_form">
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Зберегти" name="education_form_create_option"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            <input type="submit" value="Відмінити" name="cancel"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            </button>
                                        </div>
                                    </td>
                                </form>
                            </c:if>
                            <c:if test="${wrongEducationForm ne null}">
                                <form action="group_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6 col-un-padding has-error">
                                            <input type="text" maxlength="30" class="form-control normal-area-input"
                                                   placeholder="Назва нової форми навчання"
                                                   name="education_form" value="${wrongEducationForm}">
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Зберегти" name="education_form_create_option"
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
                        <c:if test="${updateEducationForm eq null}">
                            <form action="group_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для вибору форми навчання для редагування!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Редагувати" name="education_form_update"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${updateEducationForm ne null}">
                            <c:if test="${updateEducationFormArea eq null}">
                                <form action="group_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6">
                                            <select name="update_education_form" required>
                                                <option disabled selected>Форма навчання</option>
                                                <c:forEach var="groupEducationFormsList"
                                                           items="${groupEducationFormsList}">
                                                    <option value="${groupEducationFormsList.id}">${groupEducationFormsList.groupEducationFormTitle}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Вибрати" name="education_form_update_option"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            <input type="submit" value="Відмінити" name="cancel"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            </button>
                                        </div>
                                    </td>
                                </form>
                            </c:if>
                            <c:if test="${updateEducationFormArea ne null}">
                                <c:if test="${updateEducationFormAreaError eq null}">
                                    <form action="group_configuration" method="post">
                                        <td class="col-sm-6">
                                            <div class="col-sm-3"></div>
                                            <div class="col-sm-6 col-un-padding">
                                                <input type="text" maxlength="30" class="form-control normal-area-input"
                                                       placeholder="Нова форма навчання" name="education_form_title"
                                                       value="${educationFormToUpdate.groupEducationFormTitle}">
                                                <input type="hidden" maxlength="30"
                                                       class="form-control normal-area-input"
                                                       name="education_form_id" value="${educationFormToUpdate.id}">
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </td>
                                        <td class="col-sm-2">
                                            <div class="col-sm-12 col-un-padding">
                                                <input type="submit" value="Зберегти" name="education_form_update_save"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                <input type="submit" value="Відмінити" name="cancel"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                </button>
                                            </div>
                                        </td>
                                    </form>
                                </c:if>
                                <c:if test="${updateEducationFormAreaError ne null}">
                                    <form action="group_configuration" method="post">
                                        <td class="col-sm-6">
                                            <div class="col-sm-3"></div>
                                            <div class="col-sm-6 col-un-padding has-error">
                                                <input type="text" maxlength="30" class="form-control normal-area-input"
                                                       placeholder="Нова форма навчання" name="education_form_title"
                                                       value="${educationFormTitleError}">
                                                <input type="hidden" maxlength="30"
                                                       class="form-control normal-area-input"
                                                       name="education_form_id" value="${educationFormIdError}">
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </td>
                                        <td class="col-sm-2">
                                            <div class="col-sm-12 col-un-padding">
                                                <input type="submit" value="Зберегти" name="education_form_update_save"
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
                        <c:if test="${deleteEducationForm eq null}">
                            <form action="group_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для вибору форми навчання на видалення!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Видалити" name="education_form_delete"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${deleteEducationForm ne null}">
                            <form action="group_configuration" method="post">
                                <td class="col-sm-6">
                                    <div class="col-sm-3"></div>
                                    <div class="col-sm-6">
                                        <select name="delete_education_form" required>
                                            <option disabled selected>Форма навчання</option>
                                            <c:forEach var="groupEducationFormsList" items="${groupEducationFormsList}">
                                                <option value="${groupEducationFormsList.id}">${groupEducationFormsList.groupEducationFormTitle}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-3"></div>
                                </td>
                                <td class="col-sm-2">
                                    <div class="col-sm-12 col-un-padding">
                                        <input type="submit" value="Видалити" name="education_form_delete_option"
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
                    <!-- Qualification level information -->
                    <thead>
                    <tr>
                        <th class="col-sm-6 text-center"><h4><b>Кваліфікаційний рівень</b></h4></th>
                        <th class="col-sm-2 text-center"><h4><b>Дії</b></h4></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:if test="${createQualificationLevel eq null}">
                            <form action="group_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для створення нового кваліфікаційного рівня!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Створити" name="qualification_level_create"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${createQualificationLevel ne null}">
                            <c:if test="${wrongQualificationLevel eq null}">
                                <form action="group_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6 col-un-padding">
                                            <input type="text" maxlength="30" class="form-control normal-area-input"
                                                   placeholder="Назва нового кваліфікаційного рівня"
                                                   name="qualification_level">
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Зберегти"
                                                   name="qualification_level_create_option"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            <input type="submit" value="Відмінити" name="cancel"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            </button>
                                        </div>
                                    </td>
                                </form>
                            </c:if>
                            <c:if test="${wrongQualificationLevel ne null}">
                                <form action="group_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6 col-un-padding has-error">
                                            <input type="text" maxlength="30" class="form-control normal-area-input"
                                                   placeholder="Назва нового кваліфікаційного рівня"
                                                   name="qualification_level" value="${wrongQualificationLevel}">
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Зберегти"
                                                   name="qualification_level_create_option"
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
                        <c:if test="${updateQualificationLevel eq null}">
                            <form action="group_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для вибору кваліфікаційного рівня для редагування!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Редагувати" name="qualification_level_update"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${updateQualificationLevel ne null}">
                            <c:if test="${updateQualificationLevelArea eq null}">
                                <form action="group_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6">
                                            <select name="update_qualification_level" required>
                                                <option disabled selected>Кваліфікаційний рівень</option>
                                                <c:forEach var="groupQualificationLevelList"
                                                           items="${groupQualificationLevelList}">
                                                    <option value="${groupQualificationLevelList.id}">${groupQualificationLevelList.groupQualificationLevelTitle}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </td>
                                    <td class="col-sm-2">
                                        <div class="col-sm-12 col-un-padding">
                                            <input type="submit" value="Вибрати"
                                                   name="qualification_level_update_option"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            <input type="submit" value="Відмінити" name="cancel"
                                                   class="btn btn-default btn-md btn-block" formmethod="post"/>
                                            </button>
                                        </div>
                                    </td>
                                </form>
                            </c:if>
                            <c:if test="${updateQualificationLevelArea ne null}">
                                <c:if test="${updateQualificationLevelAreaError eq null}">
                                    <form action="group_configuration" method="post">
                                        <td class="col-sm-6">
                                            <div class="col-sm-3"></div>
                                            <div class="col-sm-6 col-un-padding">
                                                <input type="text" maxlength="30" class="form-control normal-area-input"
                                                       placeholder="Назва нового кваліфікаційного рівня"
                                                       name="qualification_level_title"
                                                       value="${qualificationLevelToUpdate.groupQualificationLevelTitle}">
                                                <input type="hidden" maxlength="30"
                                                       class="form-control normal-area-input"
                                                       name="qualification_level_id"
                                                       value="${qualificationLevelToUpdate.id}">
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </td>
                                        <td class="col-sm-2">
                                            <div class="col-sm-12 col-un-padding">
                                                <input type="submit" value="Зберегти"
                                                       name="qualification_level_update_save"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                <input type="submit" value="Відмінити" name="cancel"
                                                       class="btn btn-default btn-md btn-block" formmethod="post"/>
                                                </button>
                                            </div>
                                        </td>
                                    </form>
                                </c:if>
                                <c:if test="${updateQualificationLevelAreaError ne null}">
                                    <form action="group_configuration" method="post">
                                        <td class="col-sm-6">
                                            <div class="col-sm-3"></div>
                                            <div class="col-sm-6 col-un-padding has-error">
                                                <input type="text" maxlength="30" class="form-control normal-area-input"
                                                       placeholder="Назва нового кваліфікаційного рівня"
                                                       name="qualification_level_title"
                                                       value="${qualificationLevelTitleError}">
                                                <input type="hidden" maxlength="30"
                                                       class="form-control normal-area-input"
                                                       name="qualification_level_id"
                                                       value="${qualificationLevelIdError}">
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </td>
                                        <td class="col-sm-2">
                                            <div class="col-sm-12 col-un-padding">
                                                <input type="submit" value="Зберегти"
                                                       name="qualification_level_update_save"
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
                        <c:if test="${deleteQualificationLevel eq null}">
                            <form action="group_configuration" method="post">
                                <td class="col-sm-6">
                                    <p>Натисніть кнопку для вибору кваліфікаційного рівня на видалення!</p>
                                </td>
                                <td class="col-sm-2">
                                    <input type="submit" value="Видалити" name="qualification_level_delete"
                                           class="btn btn-default btn-md btn-block" formmethod="post"/>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${deleteQualificationLevel ne null}">
                            <form action="group_configuration" method="post">
                                <td class="col-sm-6">
                                    <div class="col-sm-3"></div>
                                    <div class="col-sm-6">
                                        <select name="delete_qualification_level" required>
                                            <option disabled selected>Кваліфікаційний рівень</option>
                                            <c:forEach var="groupQualificationLevelList"
                                                       items="${groupQualificationLevelList}">
                                                <option value="${groupQualificationLevelList.id}">${groupQualificationLevelList.groupQualificationLevelTitle}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-3"></div>
                                </td>
                                <td class="col-sm-2">
                                    <div class="col-sm-12 col-un-padding">
                                        <input type="submit" value="Видалити" name="qualification_level_delete_option"
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
                            <form action="group_configuration" method="post">
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
                                <form action="group_configuration" method="post">
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
                                <form action="group_configuration" method="post">
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
                            <form action="group_configuration" method="post">
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
                                <form action="group_configuration" method="post">
                                    <td class="col-sm-6">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-6">
                                            <select name="update_status" required>
                                                <option disabled selected>Статус</option>
                                                <c:forEach var="groupStatusList" items="${groupStatusList}">
                                                    <option value="${groupStatusList.id}">${groupStatusList.groupStatusTitle}</option>
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
                                    <form action="group_configuration" method="post">
                                        <td class="col-sm-6">
                                            <div class="col-sm-3"></div>
                                            <div class="col-sm-6 col-un-padding">
                                                <input type="text" maxlength="30" class="form-control normal-area-input"
                                                       placeholder="Назва нового статусу" name="status_title"
                                                       value="${statusToUpdate.groupStatusTitle}">
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
                                    <form action="group_configuration" method="post">
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
                            <form action="group_configuration" method="post">
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
                            <form action="group_configuration" method="post">
                                <td class="col-sm-6">
                                    <div class="col-sm-3"></div>
                                    <div class="col-sm-6">
                                        <select name="delete_status" required>
                                            <option disabled selected>Статус</option>
                                            <c:forEach var="groupStatusList" items="${groupStatusList}">
                                                <option value="${groupStatusList.id}">${groupStatusList.groupStatusTitle}</option>
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
                </table>


            </div>

        </div>
        <div class="col-sm-2"></div>
    </div>
</div>

<jsp:include page="../included_pages/footerJumbotron.jsp"/>

</body>
</html>
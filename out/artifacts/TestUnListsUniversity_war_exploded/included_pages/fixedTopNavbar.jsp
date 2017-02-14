<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Fixed top navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
                <form class="navbar-form" role="search" method="post">
                    <div class="navbar-right">
                        <button type="submit" class="btn btn-default" formaction="students_list" formmethod="post">Список студентів</button>
                        <button type="submit" class="btn btn-default" formaction="groups_list" formmethod="post">Список груп</button>
                        <button type="submit" class="btn btn-default" formaction="sign_in.jsp" formmethod="post">Вийти</button>
                    </div>
                    <div class="navbar-left">
                        <button type="submit" class="btn btn-default" formaction="control_panel" formmethod="post">Адміністративна панель</button>
                        <button type="submit" class="btn btn-default" formaction="student_add" formmethod="post">Додати студента
                        </button>
                    </div>
                </form>
            </div>
            <div class="col-sm-1"></div>
        </div>
    </div><!-- /.container-collapse -->
</nav>
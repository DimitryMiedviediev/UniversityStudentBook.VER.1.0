<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Footer Jumbotron -->
<div class="jumbotron text-center">
    <p>Зворотній зв'язок: +38 (097) 709-18-32</p>
    <p>Дата на сервері: <%= new java.util.Date() %>
    </p>
    <p>Кофігурація браузера клієнта: <%= request.getHeader("User-Agent") %>
    </p>
    <p>Мова відклику комп'ютера: <%= request.getLocale() %>
    </p>
    <p>Сессія: <%= session.getId()%>
    </p>
</div>
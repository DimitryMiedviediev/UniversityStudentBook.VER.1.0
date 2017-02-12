<!-- Footer Jumbotron -->
<div class="jumbotron text-center">
    <p>To collaboration call: +38 (097) 709-18-32</p>
    <p>Date on server: <%= new java.util.Date() %>
    </p>
    <p>Request user agent: <%= request.getHeader("User-Agent") %>
    </p>
    <p>Request language: <%= request.getLocale() %>
    </p>
    <p>Session id: <%= session.getId()%>
    </p>
</div>
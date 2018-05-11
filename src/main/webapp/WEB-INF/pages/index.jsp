<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
    <head>
       <link rel="stylesheet" href="<spring:theme code='stylesheet'/>" type="text/css" />
    </head>
    <body>
        <a href="?lang=en"><fmt:message key="label.lang.en" /></a>
        <a href="?lang=de"><fmt:message key="label.lang.de" /></a>
        </br>
        <fmt:message key="label.change_theme" /><a href="?theme=bright"><fmt:message key="label.theme_one" /> |
        <a href="?theme=dark"><fmt:message key="label.theme_two" /></a>

        <h1><fmt:message key="label.welcome" /></h1>
        <a href="<c:url value='/mvc/users'/>"><fmt:message key="label.users_list" /></a><br>
        <a href="<c:url value='/mvc/friendships'/>"><fmt:message key="label.friendships_list" /></a>



        <br/><br/><br/><br/><br/>
        <a href="<c:url value='/h2-web-console'/>">h2-console</a>
        <br/>
        <a href="<c:url value='/actuator/health'/>">health</a>
        <br/>
        <a href="<c:url value='/actuator/health'/>">health</a>




    </body>
</html>

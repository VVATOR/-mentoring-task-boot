<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix='fn' uri='http://java.sun.com/jsp/jstl/functions' %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title><fmt:message key="label.users" /></title>
    </head>
    <body>
    Count users: ${fn:length(users)}
        <table>
            <thead>
                <tr>
                    <th><fmt:message key="label.user_name" /></th>
                    <th><fmt:message key="label.user_surname" /></th>
                    <th><fmt:message key="label.user_birth" /></th>
                    <th><fmt:message key="label.edit" /></th>
                    <th><fmt:message key="label.remove" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><a href="<c:url value='/mvc/users/${user.id}'/>">${user.name}</a></td>
                        <td>${user.surname}</td>
                        <td>${user.birth}</td>
                        <td><a href="<c:url value='/mvc/users/edit_user/${user.id}'/>"><fmt:message key="label.edit" /></a></td>
                        <td><a href="<c:url value='/mvc/users/remove/${user.id}'/>"><fmt:message key="label.remove" /></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="<c:url value='/mvc/users/edit_user'/>"><fmt:message key="label.user_add" /></a>
    </body>
</html>
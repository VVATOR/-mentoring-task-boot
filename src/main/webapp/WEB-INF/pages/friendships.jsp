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

        <h1><fmt:message key="label.friendships" /></h1>

        Count friendships: ${fn:length(friendships)}
        <table>
            <thead>
                <tr>
                    <th><fmt:message key="label.id" /></th>
                    <th><fmt:message key="label.friend_id" /></th>
                    <th><fmt:message key="label.time" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${friendships}" var="friendship">
                    <tr>
                        <td>${friendship.userId1}</a></td>
                        <td>${friendship.userId2}</a></td>
                        <td>${friendship.timestamp}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
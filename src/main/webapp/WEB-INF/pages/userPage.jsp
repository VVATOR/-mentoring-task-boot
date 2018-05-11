<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <style>
        .error {
        	color: #ff0000;
        }

        .errorblock {
        	color: #000;
        	background-color: #ffEEEE;
        	border: 3px solid #ff0000;
        	padding: 8px;
        	margin: 16px;
        }
        </style>
    </head>
   <body>
    <div align="center">
        <c:choose>
            <c:when test="${!addUser}">
                <c:set var = "action" value = "edit_current_user"/>
                <h1><fmt:message key="label.user_edit" /></h1>
            </c:when>
            <c:otherwise>
                <c:set var = "action" value = "add_user"/>
                <h1><fmt:message key="label.user_add" /></h1>
            </c:otherwise>
        </c:choose>

        <form:form action="${action}" method="post" commandName="userForm">
            <form:errors path="*" cssClass="errorblock" element="div" />
            <table border="0">
                <td><form:hidden path="id" /></td>
                <tr>
                    <td><fmt:message key="label.name" /></td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td><fmt:message key="label.surname" /></td>
                    <td><form:input path="surname" /></td>
                </tr>
                <tr>
                    <td><fmt:message key="label.birthday" /> (mm/dd/yyyy):</td>
                      <td><form:input path="birth" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form:form>
        </div>
   </body>
</html>
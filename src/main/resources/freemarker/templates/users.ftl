<!-- FreeMarker Macros -->

<#import "/spring.ftl" as spring/>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title><@spring.message 'label.users' /></title>
    </head>
    <body>
    <#assign i = users?size>
    Count users: ${i}
        <table>
            <thead>
                <tr>
                    <th><@spring.message 'label.user_name' /></th>
                    <th><@spring.message 'label.user_surname' /></th>
                    <th><@spring.message 'label.user_birth' /></th>
                    <th><@spring.message 'label.edit' /></th>
                    <th><@spring.message 'label.remove' /></th>
                </tr>
            </thead>
            <tbody>
                <#list users as user>
                    <tr>
                        <td><a href="<@spring.url '/mvc/users/${user.id}'/>">${user.name}</a></td>
                        <td>${user.surname}</td>
                        <td>${user.birth}</td>
                        <td><a href="<@spring.url '/mvc/users/edit_user/${user.id}'/>"><@spring.message 'label.edit' /></a></td>
                        <td><a href="<@spring.url '/mvc/users/remove/${user.id}'/>"><@spring.message 'label.remove' /></a></td>
                    </tr>
                </#list>
            </tbody>
        </table>
        <a href="<@spring.url '/mvc/users/edit_user'/>"><@spring.message 'label.user_add' /></a>
    </body>
</html>
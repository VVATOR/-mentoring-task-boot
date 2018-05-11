<!-- FreeMarker Macros -->

<#import "/spring.ftl" as spring/>


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
        <#if !addUser >
                <#assign action = 'edit_current_user'>
                <h1><@spring.message 'label.user_edit' /></h1>
        <#else>
                <#assign action = 'add_user'>
                <h1><@spring.message 'label.user_add' /></h1>
        </#if>

        <form action="${action}" method="post">
            <@spring.bind "userForm"/>
            <input type="hidden" name="id" />
            <table border="0">
                <tr>
                    <td><@spring.message 'label.name' /></td>
                    <td><@spring.formInput 'name' /></td>
                </tr>
                <tr>
                    <td><@spring.message 'label.surname' /></td>
                    <td><@spring.formInput 'surname' /></td>
                </tr>
                <tr>
                    <td><@spring.message 'label.birthday' /> (mm/dd/yyyy):</td>
                      <td><@spring.formInput 'birth' /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form>
        </div>
   </body>
</html>
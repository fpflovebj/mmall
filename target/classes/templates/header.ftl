<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<script type="text/javascript" src="/static/js/jquery-1.8.2.js"></script>
<script type="text/javascript">
    //登录
    function doLogin(type) {
        var name =  $('#loginName').attr('value');
        var pw = $('#loginPassword').attr('value');
        var url = 'login.action?user.username='+name+'&user.password='+pw;
        if (name != null && name!='' && pw != null && pw!='') {

            $.ajax({
                url: url,
                type:"post",
                dataType: "json",
                async: false,
                beforeSend: function(data) {
                    $("#loginButton").attr("disabled", true);
                },
                success: function(data) {
                    if(data.user!=null){
                        $("#msg").html("&nbsp;<br>");
                        var html = "欢迎 &nbsp; "+data.user.username+"&nbsp;&nbsp;&nbsp;<label class='ui-green'><input type='button' name='search' value='退   出' onclick='document.location=\"logout.action\"'/></label>";
                        $("#topLoginDiv").html(html);
                    }else{
                        $("#msg").html(data.msg);
                        $("#msg").css("color","red");
                    }

                    $("#loginButton").attr("disabled", false);
                }
            });
        }else{
            alert("请输入用户名或密码！");
        }
    }
</script>

<div id="header" class="wrap">
    <div id="logo"><img src="/static/imgs/logo.gif" /></div>

    <div >
        <div id="msg" align="center">&nbsp;<br></div>
    </div>

    <div align="right">
        <form method="post" id="loginFrom" action="user!login.action">
            <div id="topLoginDiv" style="display: inline">
                用户名:&nbsp;<input name="username" id="loginName" />
                &nbsp;密&nbsp;&nbsp;码:&nbsp;<input name="password"  id="loginPassword"/>
                <label class="ui-green">
                    <input type="button" name="loginButton" id="loginButton"  value="登录" onclick="doLogin();" />
                </label>
                <label class="ui-green">
                    <input type='button' value='注册' onclick='document.location="/pages/register.jsp"'/>
                </label>
            </div>
            <div style="display: inline">
                <label class="ui-green">
                    <input type="button" name="search" value="发布信息" />
                </label>
            </div>
        </form>
    </div>
</div>
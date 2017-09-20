<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Spring Boot Admin</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <link rel="shortcut icon" type="image/x-icon" href="/static/core/img/favicon.png"/>
    <link rel="stylesheet" type="text/css" href="/static/core/css/core.css"/>
    <link rel="stylesheet" type="text/css" href="/static/css/login.css"/>
</head>
<body>
${msg!""}
<header class="navbar header--navbar desktop-only">
    <div class="navbar-inner">
        <div class="container-fluid">

            <div class="spring-logo--container">
                <a class="spring-logo" href="#"><span></span></a>
            </div>
            <div class="spring-logo--container">
                <a class="spring-boot-logo" href="#"><span></span></a>
            </div>
        </div>
    </div>
</header>

<div class="container">
    <form class="form-login" action="/login" method="post">
        <h2 class="form-login-heading">Login</h2>
        <div id="login-controls" class="control-group">
            <input type="text" class="input-block-level" name="username" placeholder="Username">
            <input type="password" class="input-block-level" name="password" placeholder="Password">
            <span class="help-inline error-message">Invalid username or password!</div>
        <input type="submit" class="btn btn-success" value="Login"></input>
</div>
</form>
</div>
</body>
</html>
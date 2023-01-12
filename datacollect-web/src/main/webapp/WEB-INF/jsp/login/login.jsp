<%-- 
    Document   : login
    Created on : May 29, 2018, 9:17:56 AM
    Author     : VJC80439
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <%@include file="../../fragments/common/head.jsp" %>
        <%@include file="../../fragments/common/common-js-pre-body.jsp" %>
        <%@include file="../../fragments/common/common-css.jsp" %>
    </head>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="login-logo">
                <b>DATA</b> COLLECT
            </div>

            <div class="login-box-body">
                <p class="login-box-msg">Sign in to start your session</p>

                <form:form method="post" action="">
                    <div class="form-group has-feedback">
                        <input type="text" name="username" id="username" class="form-control" placeholder="User" autofocus required/>
                        <span class="glyphicon gliphicon-envelope form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="password" name="password" id="password" class="form-control" placeholder="Password" required/>
                        <span class="glyphicon gliphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
                        </div>
                    </div>
                    <c:if test="${error!=null}">
                        <div class="alert alert-danger" role="alert">
                            ${error}
                        </div>
                    </c:if>
                    <c:if test="${logout!=null}">
                        <div class="alert alert-success" role="alert">
                            ${logout}
                        </div>
                    </c:if>
                </form:form>
            </div>
        </div>

        <script>
            $(function () {
                $('input').iCheck({
                    checkboxClass: 'icheckbox_square-blue',
                    radioClass: 'iradio_square-blue',
                    increaseArea: '20%' /* optional */
                });
            });
        </script>

    </body>
</html>

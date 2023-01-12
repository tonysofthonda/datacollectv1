<%-- 
    Document   : 403 - Forbidden (wrong role or lack of permissions)
    Created on : Feb 23, 2018, 9:35:19 AM
    Author     : Cesar Martinez <cesar_x_martinez@hdm.honda.com>
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <%@include file="../../fragments/common/head.jsp" %>
        <%@include file="../../fragments/common/common-js-pre-body.jsp" %>
        <%@include file="../../fragments/common/common-css.jsp" %>
    </head>
    <body class="hold-transition <%@include file="../../fragments/template-based/body-class-skin.jsp" %> sidebar-mini">
        
        <div class="wrapper">
            <%@include file="../../fragments/template-based/main-header.jsp" %>
            <%@include file="../../fragments/template-based/main-sidebar.jsp" %>
            <div class="content-wrapper">
                <jsp:include page="../../fragments/template-based/content-header.jsp" flush="false">
                    <jsp:param name="title" value="403"/>
                    <jsp:param name="subtitle" value="Forbidden"/>
                </jsp:include>
                <section class="content">
                    <div class="error-page">
                        <h2 class="headline text-yellow">403</h2>
                        <div class="error-content">
                            <h3><i class="fa fa-warning text-yellow"></i> Oops! You are not authorized for accessing this resource.</h3>
                            <p>
                                I’m sorry. You do not have permission to access this resource.                                
                            </p>
                            <p>Try to ask your system administrator access.</p>
                        </div>
                    </div>
                </section>
            </div>
            <%@include file="../../fragments/template-based/main-footer.jsp" %>
        </div>
        <%@include file="../../fragments/common/common-js-post-body.jsp" %>
        <!-- put below here specific JS code required by this page -->
    </body>
</html>
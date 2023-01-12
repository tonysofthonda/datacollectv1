<%-- 
    Document   : 404 - Page Not Found Error
    Created on : Feb 22, 2018, 4:35:19 PM
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
        
        <!-- Site wrapper -->
        <div class="wrapper">
            <%@include file="../../fragments/template-based/main-header.jsp" %>
            <!-- ./main-header -->

        <!-- =============================================== -->

            <%@include file="../../fragments/template-based/main-sidebar.jsp" %>
            <!-- ./main-sidebar -->

        <!-- =============================================== -->

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">

                <!-- PARTICULAR CONTENT BY PAGE ********************* -->
                
                <!-- Content Header (Page header) -->
                
                <jsp:include page="../../fragments/template-based/content-header.jsp" flush="false">
                    <jsp:param name="title" value="404"/>
                    <jsp:param name="subtitle" value="Not Found"/>
                </jsp:include>
                <!-- /.content-header -->
                    
                <!-- Main content -->
                <section class="content">
                    
                    <div class="error-page">
                        <h2 class="headline text-yellow">404</h2>

                        <div class="error-content">
                            <h3><i class="fa fa-warning text-yellow"></i> Oops! Page not found.</h3>

                            <p>
                              We could not find the page you were looking for.
                              Meanwhile, you may <a href="dashboard">return to dashboard</a>.
                            </p>

                        </div>
                        <!-- /.error-content -->
                    </div>
                    <!-- /.error-page -->
                    
                </section>
                <!-- /.content -->
                
                <!-- /.END PARTICULAR CONTENT BY PAGE ********************* -->
                
            </div>
            <!-- /.content-wrapper -->

        <!-- =============================================== -->

            <%@include file="../../fragments/template-based/main-footer.jsp" %>
            <!-- /.main-footer -->

        </div>
        <!-- ./wrapper -->
        
        <%@include file="../../fragments/common/common-js-post-body.jsp" %>
        <!-- put below here specific JS code required by this page -->
        
    </body>
    
</html>
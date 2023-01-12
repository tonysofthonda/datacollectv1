<%-- 
    Document   : 500 - Internal Server Error
    Created on : Feb 22, 2018, 4:21:35 PM
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
                    <jsp:param name="title" value="500"/>
                    <jsp:param name="subtitle" value="Internal Server Error"/>
                </jsp:include>
                <!-- /.content-header -->

                <!-- Main content -->
                <section class="content">

                    <div class="error-page">
                        <h2 class="headline text-red">500</h2>

                        <div class="error-content">
                            <h3><i class="fa fa-warning text-red"></i> Oops! Something went wrong.</h3>

                            <p>
                                We will work on fixing that right away.
                                Meanwhile, you may <a href="dashboard">return to dashboard</a>.
                            </p>

                            <p>
                                If problem persist please request an incident ticket to <a href="mailto:HDM_HelpDesk@hdm.honda.com">HDM_HelpDesk@hdm.honda.com</a>
                                and attach below Transaction Detail.
                            </p>
                            
                            <p>
                                Click on the <i class="fa fa-plus"></i> button to expand the detail.
                            </p>

                        </div>
                        <!-- /.error-content -->
                    </div>
                    <!-- /.error-page -->
                    
                    <br/>
                    <br/>
                    
                    <div class="row">
                        <div class="col-md-12">
                            <div class="box box-warning collapsed-box">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Transaction Detail</h3>
                                    <div class="box-tools pull-right">
                                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i>
                                        </button>
                                    </div>
                                      <!-- /.box-tools -->
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <table class="table table-striped" style="table-layout: fixed; width: 100%">
                                        <thead>
                                            <tr>
                                                <th>Attribute</th>
                                                <th>Value</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope}" var="rs">
                                            <tr>
                                                <td style="word-wrap: break-word">${rs.key}</td>
                                                <td style="word-wrap: break-word">${rs.value}</td>
                                            </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                        <!-- ./col -->
                    </div>
                    <!-- /.row -->

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
<%-- 
    Document   : create
    Created on : Feb 02, 2018, 10:57:16 AM
    Author     : Fabian Fonseca <fabian_fonseca@hdm.honda.com>
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
                    <jsp:param name="title" value="Dealer group"/>
                    <jsp:param name="subtitle" value="Form"/>
                </jsp:include>
                <!-- /.content-header -->

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">
                                        <c:choose>
                                            <c:when test="${edit==false}">
                                                Create New
                                            </c:when>
                                            <c:otherwise>
                                                Edit Contact group ${dealerGroupForm.id}
                                            </c:otherwise>
                                        </c:choose>
                                    </h3>
                                </div>
                                <!-- /.box-header -->
                                <form:form method="POST" modelAttribute="dealerGroupForm" class="form-horizontal">

                                    <div class="box-body">
                                        <form:hidden path="id"/>
                                        <div class="form-group">
                                            <label for="name" class="col-sm-2 control-label">Name</label>

                                            <div class="col-sm-10">
                                                <form:input path="name" class="form-control" placeholder="Name" readonly="${edit}" />
                                            </div>
                                            <div class="has-error col-sm-10 pull-right">
                                                <form:errors path="name" class="help-block"/>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /.box-body -->
                                    <div class="box-footer">
                                        <a type="button" href="dealergroup/list" class="btn btn-default">Cancel</a>
                                        <button type="submit" class="btn btn-primary pull-right">Save</button>
                                    </div>
                                    <!-- /.box-footer -->
                                </form:form>
                            </div>
                            <!-- /.box -->
                        </div>
                        <!-- /.col -->
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
        <!-- page script -->
        <script>
            $(function () {
                //Initialize Select2 Elements 
                $('.select2').select2();
            });
        </script>
    </body>

</html>
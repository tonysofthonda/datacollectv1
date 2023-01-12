
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
                <c:choose>
	                <c:when test="${not empty edit && edit == true}">
	                    <c:set var="subtitle" value="Edit"/>
	                </c:when>                
	                <c:otherwise>
	                	<c:set var="subtitle" value="Add"/>
	                </c:otherwise>
                </c:choose> 
                <jsp:include page="../../fragments/template-based/content-header.jsp" flush="false">
                    <jsp:param name="title" value="Model"/>
                    <jsp:param name="subtitle" value="${subtitle}"/>               
                </jsp:include>
                <!-- /.content-header -->

                <c:if test="${not empty error}">
                <div class="pad margin no-print" id="messages">
                    <div class="alert alert-error alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <h4><i class="icon fa fa-warning"></i> Error</h4>
                        ${error}
                    </div>
                </div>
                </c:if>
                
                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="box">
                                <div class="box-header">
                                    <span class="pull-right">
                                        <!-- New Button here TBD-->
                                    </span>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <form:form method="POST" modelAttribute="modelForm" class="form-horizontal">
                                        <form:hidden path="id" />
                                        <div class="form-group">                                        
                                            <form:label path="code" class="col-sm-2 control-label">Code</form:label>
                                            <div class="col-sm-10">                                        
                                                <form:input path="code" class="form-control" placeholder="Code" maxlength="25"/>
                                            </div>
                                            <div class="has-error col-sm-10 pull-right" error-for="code"></div>
                                        </div>
                                        <div class="form-group">                                        
                                            <form:label path="year" class="col-sm-2 control-label">Year</form:label>
                                            <div class="col-sm-10">                                        
                                                <form:input path="year" type="number" class="form-control" placeholder="Year" maxlength="4"/>
                                            </div>
                                            <div class="has-error col-sm-10 pull-right" error-for="year"></div>
                                        </div>
                                        <div class="form-group">                                        
                                            <form:label path="name" class="col-sm-2 control-label">Name</form:label>
                                            <div class="col-sm-10">                                        
                                                <form:input path="name" class="form-control" placeholder="Name" maxlength="20"/>
                                            </div>
                                            <div class="has-error col-sm-10 pull-right" error-for="name"></div>
                                        </div>
                                        <div class="form-group"> 
                                            <form:label path="description" class="col-sm-2 control-label">Description</form:label>
                                            <div class="col-sm-10"> 
                                                <form:textarea path="description" rows="2" cols="30" class="form-control no-resize" maxlength="50" placeholder="description" />
                                            </div>
                                            <div class="has-error col-sm-10 pull-right" error-for="name"></div>
                                        </div>                                        
                                        <div class="box-footer">
                                            <button style="margin-right:10px" class="btn btn-primary pull-right" type="submit">Save</button>
                                            <a href="model/list" class="btn btn-danger">Cancel</a>                                            
                                        </div>
                                    </form:form>
                                </div>
                                <!-- /.box-body -->
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
        <script src="js/util.forms.validations.js"></script>
        <script src="js/view.model.form.js"></script>
    </body>

</html>

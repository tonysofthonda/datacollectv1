
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
                    <jsp:param name="title" value="Territories"/>
                    <jsp:param name="subtitle" value="List"/>
                </jsp:include>
                <!-- /.content-header -->

                <c:if test="${not empty message}">
                <div class="pad margin no-print" id="messages">
                    <div class="alert alert-info alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <h4><i class="icon fa fa-info"></i> Info</h4>
                        ${message}
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
                                    <form:form method="POST" modelAttribute="facilityForm" class="form-horizontal">
                                        <form:hidden path="id" />
                                        <c:if test="${not empty facilityForm.dcRecordStatusId}">
                                            <form:hidden path="dcRecordStatusId" />    
                                        </c:if>
                                        <div class="form-group">                                        
                                            <form:label path="concept" class="col-sm-2 control-label">Concept</form:label>
                                            <div class="col-sm-10">                                        
                                                <form:input path="concept" class="form-control" placeholder="concept" maxlength="50"/>
                                            </div>
                                            <div class="has-error col-sm-10 pull-right"><form:errors path="concept" class="help-block"/></div>
                                        </div>

                                        <div class="form-group"> 
                                            <form:label path="description" class="col-sm-2 control-label">Description</form:label>
                                            <div class="col-sm-10"> 
                                                <form:textarea path="description" rows="3" cols="30" class="form-control" maxlength="100" placeholder="description" />
                                            </div>
                                            <div class="has-error col-sm-10 pull-right"><form:errors path="description" class="help-block"/></div>
                                        </div>
                                        
                                        <div class="box-footer">
                                            <button style="margin-right:10px" class="btn btn-primary pull-right" type="submit">Save</button>
                                            <a href="facility/list" class="btn btn-danger">Cancel</a>
                                            
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

                    <!-- Modal windows -->
                    <div class="modal fade" id="modal-delete">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title">Are you sure you want to delete the record?</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="text-center">
                                        <h4>"<span id="delete-record-name"></span> <span id="delete-record-last-name"></span>"</h4>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancel</button>
                                    <a id="action-delete" href="#" class="btn btn-primary">Yes</a>
                                </div>
                            </div>
                            <!-- /.modal-content -->
                        </div>
                        <!-- /.modal-dialog -->
                    </div>
                    
                    <div class="modal fade" id="modal-contact" tabindex='-1'>
                        <div style="margin:10px" class="modal-dialog">
                            <div  style="width:1500px; min-height:500px;" class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title"></h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                      <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <iframe style="width:1450px; height:500px;" frameBorder="0" src="contact/list/${facilityForm.id}/facility" id="contactModal" ></iframe>
                                </div>
                            </div>
                            <!-- /.modal-content -->
                        </div>
                        <!-- /.modal-dialog -->
                    </div>
                    
                    <!-- /.modal -->
                    <!-- /.Modal Windows -->
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
            $(function(){
               
            });
        </script>
    </body>

</html>

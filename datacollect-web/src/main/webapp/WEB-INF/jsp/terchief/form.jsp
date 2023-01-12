<%-- 
    Document   : list
    Created on : Nov 7, 2018, 11:34:57 AM
    Author     : VJC80485
--%>
<%-- 
    Document   : list
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
                    <jsp:param name="title" value="Head of Territory"/>
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
                                    <form:form method="POST" modelAttribute="terchiefForm" class="form-horizontal">
                                        <form:hidden path="id" />
                                        <c:if test="${not empty terchiefForm.dcRecordStatusId}">
                                            <form:hidden path="dcRecordStatusId" />    
                                        </c:if>
                                        <div class="form-group">                                        
                                            <form:label path="firstName" class="col-sm-2 control-label">Name</form:label>
                                            <div class="col-sm-10">                                        
                                                <form:input path="firstName" class="form-control" placeholder="First Name" />
                                            </div>
                                            <div class="has-error col-sm-10 pull-right"><form:errors path="firstName" class="help-block"/></div>
                                        </div>

                                        <div class="form-group"> 
                                            <form:label path="lastName" class="col-sm-2 control-label">Last Name</form:label>
                                            <div class="col-sm-10"> 
                                                <form:input path="lastName" class="form-control" placeholder="Last Name" />
                                            </div>
                                            <div class="has-error col-sm-10 pull-right"><form:errors path="lastName" class="help-block"/></div>
                                        </div>
                                        <div class="form-group"> 
                                            <form:label path="motherLast" class="col-sm-2 control-label">M Last Name</form:label>
                                            <div class="col-sm-10"> 
                                                <form:input path="motherLast" class="form-control" placeholder="Mother Lasr Name" />
                                            </div>
                                            <div class="has-error col-sm-10 pull-right"><form:errors path="motherLast" class="help-block"/></div>
                                        </div>
                                        <div class="form-group"> 
                                            <form:label path="notes" class="col-sm-2 control-label">Notes</form:label>
                                            <div class="col-sm-10"> 
                                                <form:input path="notes" class="form-control" placeholder="Notes" />
                                            </div>
                                            <div class="has-error col-sm-10 pull-right"><form:errors path="notes" class="help-block"/></div>
                                        </div>
                                        <div class="box-footer">
                                            <button style="margin-right:10px" class="btn btn-primary pull-right" type="submit">Save</button>
                                            <a href="terchief/list" class="btn btn-danger">Cancel</a>
                                            <c:if test="${not empty terchiefForm.id}">                                                
                                                <button style="margin-right:10px" type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#modal-contact" 
                                                             data-record-id="${terchiefForm.id}">Contacts</button>
                                            </c:if>
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
                                    <iframe style="width:1450px; height:500px;" frameBorder="0" src="contact/list/${terchiefForm.id}/terchief" id="contactModal" ></iframe>
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
                $('#modal-contact').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget); // Button that triggered the modal
                    var recordId = button.data('record-id'); // Extract info from data-* attributes
                    var url = "/contact/list/"+recordId+"/terchief"
                    /*console.log(url);
                    var modal = $(this);
                    var iframe = modal.find('iframe#contactModal');
                    iframe.src = url;
                    iframe.src.reload;*/
                });
            });
        </script>
    </body>

</html>

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
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-header">
                                	<c:if test="${userPermissionEvaluator.hasPermission('terchiefs', 'create')}">
	                                    <span class="pull-right">
	                                        <a href="terchief/add" class="btn btn-primary">New</a>
	                                    </span>
                                    </c:if>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <table id="table1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Last Name</th>
                                                 <th>Mother's Last Name</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${terchiefList}" var="terchief">
                                                <tr>
                                                    <td>${terchief.firstName}</td>
                                                    <td>${terchief.lastName}</td>
                                                    <td>${terchief.motherLast}</td>
                                                    <td>          
                                                    	<c:if test="${userPermissionEvaluator.hasPermission('terchiefs', 'edit')}">                                              
                                                        	<a href="terchief/edit/${terchief.id}" class="btn btn-xs btn-primary">Edit</a>&nbsp;
                                                        </c:if>
                                                        <c:if test="${userPermissionEvaluator.hasPermission('terchiefs', 'change_status')}">
                                                        	<button type="button" class="btn btn-xs btn-danger" data-toggle="modal" data-target="#modal-delete" 
                                                                data-record-id="${terchief.id}" data-record-name="${terchief.firstName}" data-record-last-name="${terchief.lastName}">delete</button>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Name</th>
                                                <th>Last Name</th>
                                                <th>Mother's Last Name</th>
                                                <th>Actions</th>
                                            </tr>
                                        </tfoot>
                                    </table>
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
            $(function () {
                //$('#table1').DataTable()
                $('#table1').DataTable({
                    'paging': true,
                    'lengthChange': true,
                    'searching': true,
                    'ordering': true,
                    'info': true,
                    'autoWidth': true
                });

                $('#modal-delete').on('show.bs.modal', function (event) {
                    var button = $(event.relatedTarget); // Button that triggered the modal
                    var recordId = button.data('record-id'); // Extract info from data-* attributes
                    var recordLastName = button.data('record-last-name')
                    var recordName = button.data('record-name');
                    var modal = $(this);
                    modal.find('#delete-record-last-name').text(recordLastName);
                    modal.find('#delete-record-name').text(recordName);
                    modal.find('#action-delete').attr('href','terchief/disable/'+recordId);
                });
            });
        </script>
    </body>

</html>

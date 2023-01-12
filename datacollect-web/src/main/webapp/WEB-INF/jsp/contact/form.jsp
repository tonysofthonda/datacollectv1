<%-- 
    Document   : list
    Created on : Dec 06, 2018, 11:34:57 AM
    Author     : VJC80413
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
    <body>
            <div>

                <!-- PARTICULAR CONTENT BY PAGE ********************* -->

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
                        <div class="col-md-4">
                            <div class="box">
                                <div class="box-header">
                                    <span class="pull-right">
                                        <!-- New Button here TBD-->
                                    </span>
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <c:choose>
                                        <c:when test="${not empty contactForm.id}">   
                                            <c:set var="url" value="contact/edit/${parentId}/${type}/${contactForm.id}" />
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="url" value="contact/add/${parentId}/${type}" />
                                        </c:otherwise>
                                    </c:choose>
                                    <form:form method="POST" modelAttribute="contactForm" action="${url}" class="form-horizontal">
                                        <form:hidden path="id" />
                                        <c:if test="${not empty contactForm.dcRecordStatusId}">
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
                                            <form:label path="motherLastName" class="col-sm-2 control-label">M. Last Name</form:label>
                                            <div class="col-sm-10"> 
                                                <form:input path="motherLastName" class="form-control" placeholder="Mother Last Name" />
                                            </div>
                                            <div class="has-error col-sm-10 pull-right"><form:errors path="motherLastName" class="help-block"/></div>
                                        </div>
                                        <div class="form-group"> 
                                            <form:label path="email" class="col-sm-2 control-label">Email</form:label>
                                            <div class="col-sm-10"> 
                                                <form:input path="email" class="form-control" placeholder="Email" />
                                            </div>
                                            <div class="has-error col-sm-10 pull-right"><form:errors path="email" class="help-block"/></div>
                                        </div>
                                        <div class="form-group"> 
                                            <form:label path="phoneNumber" class="col-sm-2 control-label">Phone Number</form:label>
                                            <div class="col-sm-10"> 
                                                <form:input path="phoneNumber" class="form-control" placeholder="Phone Number" />
                                            </div>
                                            <div class="has-error col-sm-10 pull-right"><form:errors path="phoneNumber" class="help-block"/></div>
                                        </div>                                        
                                        <div class="form-group"> 
                                            <form:label path="notes" class="col-sm-2 control-label">Notes</form:label>
                                            <div class="col-sm-10"> 
                                                <form:input path="notes" class="form-control" placeholder="Notes" />
                                            </div>
                                            <div class="has-error col-sm-10 pull-right"><form:errors path="notes" class="help-block"/></div>
                                        </div>
                                        <div class="box-footer">
                                            <button class="btn btn-primary" type="submit">Save</button>
                                        </div>
                                    </form:form>
                                </div>
                                <!-- /.box-body -->
                            </div>
                            <!-- /.box -->
                        </div>
                        <!-- /.col -->

                         <div class="col-md-8">                        
                            <div class="box-body">
                                <table id="table1" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Last Name</th>
                                            <th>M. Last Name</th>
                                            <th>Email</th>
                                            <th>Phone Number</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${contactList}" var="contact">
                                            <tr>
                                                <td>${contact.firstName}</td>
                                                <td>${contact.lastName}</td>
                                                <td>${contact.motherLastName}</td>
                                                <td>${contact.email}</td>
                                                <td>${contact.phoneNumber}</td>
                                                <td>                                                        
                                                    <a href="contact/edit/${parentId}/${type}/${contact.id}" class="btn btn-xs btn-primary">Edit</a>&nbsp;
                                                    <button type="button" class="btn btn-xs btn-danger" data-toggle="modal" data-target="#modal-delete" 
                                                            data-record-id="${contact.id}" data-record-name="${contact.firstName}" data-record-last-name="${contact.lastName}">Delete</button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>Name</th>
                                            <th>Last Name</th>
                                            <th>M. Last Name</th>
                                            <th>Email</th>
                                            <th>Phone Number</th>
                                            <th>Actions</th>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>

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
                    modal.find('#action-delete').attr('href','contact/disable/${parentId}/${type}/'+recordId);
                });
            });
        </script>
    </body>
    
</html>

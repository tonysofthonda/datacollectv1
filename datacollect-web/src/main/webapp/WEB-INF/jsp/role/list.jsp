<%-- 
    Document   : list
    Created on : August 08, 2019
    Author     : VJC80496
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
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">

                <!-- PARTICULAR CONTENT BY PAGE ********************* -->
                <!-- Content Header (Page header) -->
                <jsp:include page="../../fragments/template-based/content-header.jsp" flush="false">
                    <jsp:param name="title" value="Roles"/>
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
                                	<c:if test="${userPermissionEvaluator.hasPermission('roles', 'create')}">
                                		<span class="pull-right">
	                                        <a href="role/add" class="btn btn-primary">New</a>
	                                    </span>
                                	</c:if>                                    
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <table id="roles-table" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Role</th>
                                                <th>Description</th>
                                                <th>LDAP Groups</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${roleList}" var="role">
                                                <tr>
                                                    <td>${role.name}</td>
                                                    <td>${role.description}</td>
                                                    <td>
                                                    	<c:forEach items="${role.dcLdapGroupList}" var="ldapGroup">
                                                    		<span class="label label-default">${ldapGroup.ldapId} <br></span>
                                                    	</c:forEach>
                                                    </td>
                                                    <td>
                                                    	<c:if test="${userPermissionEvaluator.hasPermission('roles', 'edit')}">
                                                        	<a href="role/edit/${role.id}" class="btn btn-xs btn-primary">edit</a>
                                                        </c:if>
                                                        <c:if test="${userPermissionEvaluator.hasPermission('roles', 'delete')}">
                                                        	<button type="button" class="btn btn-xs btn-danger" data-toggle="modal" data-target="#modal-delete" 
                                                                data-record-id="${role.id}" data-record-name="${role.name}">delete</button>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Role</th>
                                                <th>Description</th>
                                                <th>LDAP Groups</th>
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
                                    <h4 class="modal-title">Are you sure you want to delete this role?</h4>
                                </div>
                                <div class="modal-body">
                                    <h4>"<span id="data-record-name"></span>"</h4>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancel</button>
                                    <a id="action-delete" href="#" class="btn btn-primary">Yes</a>
                                </div>
                            </div>
                        </div>
                    </div>
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
        <script src="js/view.role.list.js"></script>
    </body>

</html>
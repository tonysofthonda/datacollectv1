<%-- 
    Document   : list
    Created on : Feb 02, 2018, 10:57:16 AM
    Author     : Fabian Fonseca <fabian_fonseca@hdm.honda.com>
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum" %>

<html>
    <head>
        <%@include file="../../fragments/common/head.jsp" %>
        <%@include file="../../fragments/common/common-js-pre-body.jsp" %>
        <%@include file="../../fragments/common/common-css.jsp" %>
        <link rel="stylesheet" href="webjars/bootstrap-toggle/css/bootstrap-toggle.css"/>
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
                    <jsp:param name="title" value="Dealers"/>
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
                                	<c:if test="${userPermissionEvaluator.hasPermission('dealers', 'create')}">
                                		<span class="pull-right">
	                                        <a href="dealer/add" class="btn btn-primary">New</a>
	                                    </span>
                                	</c:if>                                    
                                </div>
                                <!-- /.box-header -->
                                <div class="box-body">
                                    <table id="delaers-table" class="table table-bordered table-striped invisible">
                                        <thead>
                                            <tr>
                                                <th>Dealer Number</th>
                                                <th>Dealer Group</th>
                                                <th>Head of Territory</th>
                                                <th>Status</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:set var="hasChangeStatusPermission" value="${userPermissionEvaluator.hasPermission('dealers', 'change_status')}"/>
                                        	<c:set var="hasEditPermission" value="${userPermissionEvaluator.hasPermission('dealers', 'edit')}"/>
                                            <c:forEach items="${dealerList}" var="dealer">
                                                <tr>
                                                    <td>${dealer.dealerNumber}</td>
                                                    <td>${dealer.dcDealerGroup.name}</td>
                                                    <td>${dealer.dcTerchief.firstName} ${dealer.dcTerchief.lastName}</td>
                                                    <td>
                                                   		<div class="input-switch-wrapper">                                                    		
                                                    		<input 
                                                    			type="checkbox" 
                                                   				class="input-switch" 
                                                   				data-record-id="${dealer.id}" 
                                                   				data-dealer-number="${dealer.dealerNumber}" 
                                                   				data-toggle="toggle" 
                                                   				data-onstyle="success" 
                                                   				data-on="Enabled" 
                                                   				data-off="Disabled" 
                                                   				data-size="mini"  
                                                   				<c:if test="${dealer.dcRecordStatusId == DcRecordStatusEnum.ENABLED}">checked</c:if>
                                                   				<c:if test="${!hasChangeStatusPermission}">disabled</c:if>
                                                  				>
                                                    	</div>  
                                                   	</td>
                                                    <td>                                                    
                                                    	<c:if test="${hasEditPermission}">
                                                        	<a href="dealer/edit/${dealer.id}" class="btn btn-xs btn-primary">edit</a>
                                                        </c:if>                                                        
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Dealer Number</th>
                                                <th>Dealer Group</th>
                                                <th>Head of Territory</th>
                                                <th>Status</th>
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
                    <jsp:include page="../../fragments/common/modal-confirm.jsp" flush="false">
						<jsp:param name="modal_id" value="modal-disable"/>
						<jsp:param name="title" value="Are you sure you want to disable this dealer?"/>
						<jsp:param name="message" value=""/>
					</jsp:include>
					
					<jsp:include page="../../fragments/common/modal-confirm.jsp" flush="false">
						<jsp:param name="modal_id" value="modal-enable"/>
						<jsp:param name="title" value="Are you sure you want to enable this dealer?"/>
						<jsp:param name="message" value=""/>
					</jsp:include>
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
        <script src="webjars/bootstrap-toggle/js/bootstrap-toggle.js"></script>
        <script src="js/view.dealer.list.js"></script>
    </body>

</html>
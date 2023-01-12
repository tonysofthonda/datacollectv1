<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page
	import="com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum"%>
<html>
<head>
<%@include file="../../fragments/common/head.jsp"%>
<%@include file="../../fragments/common/common-js-pre-body.jsp"%>
<%@include file="../../fragments/common/common-css.jsp"%>
<link rel="stylesheet"
	href="webjars/bootstrap-toggle/css/bootstrap-toggle.css" />
</head>
<body
	class="hold-transition <%@include file="../../fragments/template-based/body-class-skin.jsp" %> sidebar-mini">
	<div class="wrapper">
		<%@include file="../../fragments/template-based/main-header.jsp"%>
		<!-- ./main-header -->

		<!-- =============================================== -->

		<%@include file="../../fragments/template-based/main-sidebar.jsp"%>
		<!-- ./main-sidebar -->

		<!-- =============================================== -->
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- PARTICULAR CONTENT BY PAGE ********************* -->

			<!-- Content Header (Page header) -->
			<jsp:include page="../../fragments/template-based/content-header.jsp"
				flush="false">
				<jsp:param name="title" value="Orders Type" />
				<jsp:param name="subtitle" value="List" />
			</jsp:include>


			<c:if test="${not empty message}">
				<div class="pad margin no-print" id="messages">
					<div class="alert alert-info alert-dismissible">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
						<h4>
							<i class="icon fa fa-info"></i> Info
						</h4>
						${message}
					</div>
				</div>
			</c:if>

			<c:if test="${not empty error}">
				<div class="pad margin no-print">
					<div class="alert alert-error alert-dismissible">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
						<h4>
							<i class="icon fa fa-warning"></i> Error
						</h4>
						${error}
					</div>
				</div>
			</c:if>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<c:if test="${userPermissionEvaluator.hasPermission('orderstype', 'create')}">
									<span class="pull-right"> <a href="orderstype/add"
										class="btn btn-primary">New</a>
									</span>
								</c:if>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="orderstypeTable" class="table table-bordered table-striped invisible">
								<thead>
										<tr>
											<th>Code</th>
											<th>Description</th>
 											<th>Service</th>
											<th>Actions</th>
										</tr>
									</thead>	
									<tbody>
										<c:forEach items="${operationCodeList}" var="model">
											<tr>
												<td>${model.code}</td>
												<td>${model.description}</td>
												<td>${model.serviceType}</td>
												<td>
												<c:if test="${userPermissionEvaluator.hasPermission('orderstype', 'edit')}">
                                                        	<a href="orderstype/edit/${model.id}" class="btn btn-xs btn-primary">edit</a>
                                                        </c:if> 
												</td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
                                    	<tr>
                                    		<th>Code</th>
											<th>Descripción</th>
											<th>Service</th>
											<th>Actions</th>
                                        </tr>
                                   </tfoot>
								</table>
							</div>	
						</div>
					</div>
				</div>
			</section>
		</div>
		
		<!-- /.content-wrapper -->

            <!-- =============================================== -->

            <%@include file="../../fragments/template-based/main-footer.jsp" %>
            <!-- /.main-footer -->
            
			<jsp:include page="../../fragments/common/modal-confirm.jsp" flush="false">
				<jsp:param name="modal_id" value="modal-disable"/>
				<jsp:param name="title" value="Are you sure you want to disable this model?"/>
				<jsp:param name="message" value=""/>
			</jsp:include>
			
			<jsp:include page="../../fragments/common/modal-confirm.jsp" flush="false">
				<jsp:param name="modal_id" value="modal-enable"/>
				<jsp:param name="title" value="Are you sure you want to enable this model?"/>
				<jsp:param name="message" value=""/>
			</jsp:include>
        </div>
        <!-- ./wrapper -->

        <%@include file="../../fragments/common/common-js-post-body.jsp" %>        
        <!-- put below here specific JS code required by this page -->
        <script src="webjars/bootstrap-toggle/js/bootstrap-toggle.js"></script>
        <script src="js/view.orderstype.list.js"></script>
        <!-- page script -->
        <script>
        </script>
</body>
</html>
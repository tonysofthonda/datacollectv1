<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<%@include file="../../fragments/common/head.jsp"%>
<%@include file="../../fragments/common/common-js-pre-body.jsp"%>
<%@include file="../../fragments/common/common-css.jsp"%>
</head>
<body
	class="hold-transition <%@include file="../../fragments/template-based/body-class-skin.jsp" %> sidebar-mini">
	<!-- Site wrapper -->
	<div class="wrapper">
		<%@include file="../../fragments/template-based/main-header.jsp"%>
		<%@include file="../../fragments/template-based/main-sidebar.jsp"%>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<c:choose>
				<c:when test="${not empty edit && edit == true}">
					<c:set var="subtitle" value="Edit" />
				</c:when>
				<c:otherwise>
					<c:set var="subtitle" value="Add" />
				</c:otherwise>
			</c:choose>
			<jsp:include page="../../fragments/template-based/content-header.jsp" flush="false">
				<jsp:param name="title" value="Role" />
				<jsp:param name="subtitle" value="${subtitle}" />
			</jsp:include>
			<!-- /.content-header -->
			<div class="pad margin no-print" id="messages">
				<c:if test="${not empty error}">
					<div class="alert alert-error alert-dismissible">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
						<h4>
							<i class="icon fa fa-warning"></i> Error
						</h4>
						${error}
					</div>
				</c:if>	
			</div>
			
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-6">
						<div class="box">
							<div class="box-header">
								<span class="pull-right"> <!-- New Button here TBD-->
								</span>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<ul class="nav nav-tabs m-b-1">
									<li class="active"><a data-toggle="tab" href="#general">General</a></li>
									<li><a data-toggle="tab" href="#ldap-group">LDAP Group</a></li>
									<li><a data-toggle="tab" href="#role-permissions">Permissions</a></li>
								</ul>
								<div class="tab-content">
									<div id="general" class="tab-pane fade in active">
										<jsp:include page="../../fragments/role/role-data.jsp" flush="false">
											<jsp:param name="roleData" value="${roleData}" />
										</jsp:include>
									</div>
									<div id="ldap-group" class="tab-pane fade">
										<jsp:include page="../../fragments/role/role-ldap-groups.jsp"
											flush="false">
											<jsp:param name="roleData" value="${roleData}" />
											<jsp:param name="dcLdapGroupList" value="${dcLdapGroupList}" />
										</jsp:include>
									</div>
									<div id="role-permissions" class="tab-pane fade">
										<%@include file="../../fragments/role/role-permissions.jsp"%>
									</div>
								</div>
							</div>
							<!-- /.box-body -->
							<div class="box-footer">
						     	<button id = "btn-save-role" style="margin-right: 10px" class="btn btn-primary pull-right" type="button">Save All</button>
								<a href="role/list" class="btn btn-danger">Cancel</a>
							</div>
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
		<%@include file="../../fragments/template-based/main-footer.jsp"%>
		<!-- /.main-footer -->
	</div>
	<!-- ./wrapper -->
	<%@include file="../../fragments/common/common-js-post-body.jsp"%>
	<script src="webjars/bootstrap-treeview/1.2.0/bootstrap-treeview.min.js"></script>
	<!-- put below here specific JS code required by this page -->
	<script src="js/util.forms.validations.js"></script>	
	<!-- page script -->
	<script>
		const isEdit = ${edit};
		const roleId = ${roleData.id != null ? roleData.id : 'null'};
	</script>
	<script src="js/view.role.form.js"></script>
</body>
</html>
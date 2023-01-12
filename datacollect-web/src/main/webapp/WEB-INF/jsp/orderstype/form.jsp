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
		<!-- ./main-header -->

		<!-- =============================================== -->

		<%@include file="../../fragments/template-based/main-sidebar.jsp"%>
		<!-- ./main-sidebar -->

		<!-- =============================================== -->

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- PARTICULAR CONTENT BY PAGE ********************* -->

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
				<jsp:param name="title" value="Orders Type" />
				<jsp:param name="subtitle" value="${subtitle}" />
			</jsp:include>
			<!-- /.content-header -->
			<c:if test="${not empty error}">
				<div class="pad margin no-print" id="messages">
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
					<div class="col-md-6">
						<div class="box">
							<div class="box-header">
								<span class="pull-right"> <!-- New Button here TBD--> 
								
								</span>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<form:form method="POST" modelAttribute="ordersTypeForm" class="form-horizontal">
									<form:hidden path="id" />
									<div class="form-group">
										<form:label path="code" class="col-sm-2 control-label">Order type</form:label>
										<div class="col-sm-10">
											<form:input path="code" class="form-control" placeholder="Order type" maxlength="5" />
										</div>
										<div class="has-error col-sm-10 pull-right" error-for="code"></div>
									</div>
									<div class="form-group">
										<form:label path="description" class="col-sm-2 control-label">Description</form:label>
										<div class="col-sm-10">
											<form:input path="description" class="form-control" placeholder="Description" maxlength="50" />
										</div>
										<div class="has-error col-sm-10 pull-right" error-for="description"></div>
									</div>
									<div class="form-group">
										<label for="serviceType" class="col-sm-2 control-label">Service</label>	
										<div class="col-sm-10">
											<form:select path="serviceType" class="form-control">
												<option value="">Select an option</option>
												<c:forEach items="${service}" var="item">
									            	<c:choose>
								                        <c:when test="${ordersTypeForm.serviceType != null && ordersTypeForm.serviceType == item}"> 
								                            <form:option value="${fn:substring(item, 0, 2)}" selected="selected">${ordersTypeForm.serviceType}</form:option>
								                        </c:when>
								                        <c:otherwise>
								                        	<form:option value="${fn:substring(item, 0, 2)}">${ item } </form:option>
								                        </c:otherwise>
								                    </c:choose>
												</c:forEach>
								            </form:select>
								        </div>
								        <div class="has-error col-sm-10 pull-right" error-for="serviceType"></div>
									</div>
								    <div class="box-footer">
                                     	<button style="margin-right:10px" class="btn btn-primary pull-right" type="submit">Save</button>
                                       <a href="orderstype/list" class="btn btn-danger">Cancel</a>                                            
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
		<%@include file="../../fragments/template-based/main-footer.jsp"%>
		<!-- /.main-footer -->
	</div>
	<!-- ./wrapper -->
	<%@include file="../../fragments/common/common-js-post-body.jsp"%>
	<!-- put below here specific JS code required by this page -->
	<!-- page script -->
	<script src="js/util.forms.validations.js"></script>
	<script src="js/view.orderstype.form.js"></script>
</body>


</html>
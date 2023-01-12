<%-- any content can be specified here e.g.: --%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<form:form id="role-form" class="form-horizontal" modelAttribute="roleData">
	<c:if test="${edit == true}">
		<form:hidden path="id"/>    
    </c:if>
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">Role</label>
		<div class="col-sm-10">
			<form:input path="name" class="form-control" placeholder="Role" maxlength="50"/>
		</div>
		<div class="has-error col-sm-10 pull-right" error-for="name"></div>
	</div>
	<div class="form-group">
		<label for="description" class="col-sm-2 control-label">Description</label>
		<div class="col-sm-10">
			<form:textarea path="description" rows="2" cols="30" class="form-control no-resize" maxlength="255" placeholder="Description" />
		</div>
		<div class="has-error col-sm-10 pull-right" error-for="description"></div>
	</div>	
</form:form>
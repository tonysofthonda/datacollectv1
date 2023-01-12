<%-- any content can be specified here e.g.: --%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<form id="ldap-groups-form" class="form-horizontal">
	<div class="form-group">
		<label for="ldapId" class="col-sm-2 control-label">LDAP id</label>
		<div class="col-sm-10">
			<input id="ldapId" name="ldapId" class="form-control" placeholder="LDAP id" maxlength="25" />
		</div>
		<div class="has-error col-sm-10 pull-right" error-for="ldapId"></div>
	</div>
	<div class="form-group">
		<label for="ldapDescription" class="col-sm-2 control-label">Description</label>
		<div class="col-sm-10">
			<textarea id ="ldapDescription" name="ldapDescription" rows="2" cols="30" class="form-control no-resize" maxlength="50" placeholder="Description"></textarea>
		</div>
		<div class="has-error col-sm-10 pull-right" error-for="ldapDescription"></div>
	</div>
	<div class="form-group">
	    <div class="col-sm-12">
	        <button class="btn btn-primary pull-right" id="btn-save-ldap-group" type="button" >Save Group</button>
	    </div>        
	</div>
	<table id="ldap-groups-table" class="table table-bordered table-striped">
		<thead>
			<tr>
				<th>LDAP Id</th>
				<th>Description</th>
				<th>Actions</th>
			</tr>	
		</thead>
		<tbody>
			<c:forEach items="${ldapGroupList}" var="group">
				<tr>
					<td>${group.ldapId}</td>
					<td>${group.name}</td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th>LDAP Id</th>
				<th>Description</th>
				<th>Actions</th>
			</tr>
		</tfoot>
	</table>
</form>
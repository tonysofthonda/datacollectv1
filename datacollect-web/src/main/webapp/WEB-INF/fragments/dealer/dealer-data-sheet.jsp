<%-- any content can be specified here e.g.: --%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form id="dealer-facilities-form" class="form-horizontal">
    <div id="facilities-message-container"></div>
    <div class="form-group">
        <label for="facilityId" class="col-sm-2 control-label">Concept</label>
        <div class="col-sm-10">
            <select id="facilityId" name="facilityId" class="form-control">
                <option value="">Select an option</option>
                <c:forEach var="facility" items="${facilityList}">                                                        
                    <option value="${facility.id}">${facility.concept}</option>
                </c:forEach>
            </select>
        </div>
        <div class="has-error col-sm-10 pull-right" error-for="facilityId"></div>
    </div> 
    <div class="form-group">
        <label for="quantity" class="col-sm-2 control-label">Quantity</label>
        <div class="col-sm-10">
            <input type="number" id="quantity" name="quantity" class="form-control" placeholder=""/>
        </div>
        <div class="has-error col-sm-10 pull-right" error-for="quantity"></div>
    </div>
    <div class="form-group">
        <div class="col-sm-12">
            <button class="btn btn-primary pull-right" id="btn-save-facility">Save</button>
        </div>        
    </div>   
    <div style="width: 100%;">
        <table id="dealer-facilities-table" class="table table-bordered table-striped"></table>
    </div>
    <div class="modal fade" id="modal-delete-facility" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <h4>Are you sure you want to delete this record?</h4>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancel</button>
                    <a id="btn-accept-delete-facility" class="btn btn-primary">Yes</a>
                </div>
            </div>
        </div>
    </div>
</form>
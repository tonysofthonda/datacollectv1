<%-- any content can be specified here e.g.: --%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form:form method="post" modelAttribute="dealerData" class="form-horizontal">
    <form:hidden path="id"/>
    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">Dealer Number</label>
        <div class="col-sm-10">
            <form:input type="number" path="dealerNumber" class="form-control" placeholder="Dealer Number"/>
        </div>
        <div class="has-error col-sm-10 pull-right"><form:errors path="dealerNumber" class="help-block"/></div>
    </div>
    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">Dealer Name</label>
        <div class="col-sm-10">
            <form:input path="name" class="form-control" placeholder="Name"/>
        </div>
        <div class="has-error col-sm-10 pull-right"><form:errors path="name" class="help-block"/></div>
    </div>
    <div class="form-group">
        <label for="businessName" class="col-sm-2 control-label">Business Name</label>
        <div class="col-sm-10">
            <form:input path="businessName" class="form-control" placeholder="Business Name" />
        </div>
        <div class="has-error col-sm-10 pull-right"><form:errors path="businessName" class="help-block"/></div>
    </div>
    <div class="form-group">
        <label for="rfc" class="col-sm-2 control-label">RFC</label>
        <div class="col-sm-10">
            <form:input path="rfc" class="form-control" placeholder="RFC" />
        </div>
        <div class="has-error col-sm-10 pull-right"><form:errors path="rfc" class="help-block"/></div>
    </div>
    <div class="form-group">
        <label for="rfc" class="col-sm-2 control-label">Postal Code</label>
        <div class="col-sm-10">
            <form:input path="zipCode" class="form-control" placeholder="Postal Code" />
        </div>
        <div class="has-error col-sm-10 pull-right"><form:errors path="zipCode" class="help-block"/></div>
    </div>
    <div class="form-group">
        <label for="dcCityId.dcStateId" class="col-sm-2 control-label">State</label>
        <div class="col-sm-10">
            <form:select id="stateId" path="dcCityId.dcStateId" class="form-control">
                <option value="-1">Select an option</option>
                <form:options items="${stateList}" itemLabel="name" itemValue="id" />
            </form:select>
        </div>
        <div class="has-error col-sm-10 pull-right"><form:errors path="dcCityId.dcStateId" class="help-block"/></div>
    </div>
    <div class="form-group">
        <label for="dcCityId" class="col-sm-2 control-label">City</label>
        <div class="col-sm-10">
            <form:select path="dcCityId" class="form-control"></form:select>
        </div>
        <input type="hidden" id="cityId" value="${dealerData.dcCityId.id}">
        <div class="has-error col-sm-10 pull-right"><form:errors path="dcCityId" class="help-block"/></div>
    </div>
    <div class="form-group">
        <label for="addrStreet" class="col-sm-2 control-label">Street Address</label>
        <div class="col-sm-10">
            <form:input path="addrStreet" class="form-control" placeholder="Street Address"/>
        </div>
        <div class="has-error col-sm-10 pull-right"><form:errors path="addrStreet" class="help-block"/></div>
    </div> 
    <div class="form-group">
        <label for="addrNeighborhood" class="col-sm-2 control-label">Neighborhood Address</label>
        <div class="col-sm-10">
            <form:input path="addrNeighborhood" class="form-control" placeholder="Neighborhood Address"/>
        </div>
        <div class="has-error col-sm-10 pull-right"><form:errors path="addrNeighborhood" class="help-block"/></div>
    </div> 
    <div class="form-group">
        <label for="dcDealerGroup" class="col-sm-2 control-label">Group</label>
        <div class="col-sm-10">
            <form:select path="dcDealerGroup" class="form-control">
                <option value="">Select an option</option>
                <form:options items="${dealerGroupList}" itemLabel="name" itemValue="id" />
            </form:select>
        </div>
        <div class="has-error col-sm-10 pull-right"><form:errors path="dcDealerGroup" class="help-block"/></div>
    </div>
    <div class="form-group">
        <label for="dcTerchief" class="col-sm-2 control-label">Territory Chief</label>
        <div class="col-sm-10">
            <form:select path="dcTerchief" class="form-control">
                <option value="">Select an option</option>
                <c:forEach var="terchief" items="${terchiefList}">                                                        
                    <c:choose>
                        <c:when test="${terchief.id == dealerData.dcTerchief.id}"> 
                            <form:option value="${terchief.id}" selected="selected">${terchief.firstName} ${terchief.lastName}</form:option>
                        </c:when>
                        <c:otherwise>
                            <form:option value="${terchief.id}">${terchief.firstName} ${terchief.lastName}</form:option>
                        </c:otherwise>
                    </c:choose>                                                          
                </c:forEach>
            </form:select>
        </div>
        <div class="has-error col-sm-10 pull-right"><form:errors path="dcTerchief" class="help-block"/></div>
    </div>
    <div class="form-group">
        <label for="dcWorkshopId" class="col-sm-2 control-label">Workshop Type</label>
        <div class="col-sm-10">
            <form:select path="dcWorkshopId.id" class="form-control">
                <option value="">Select an option</option>
                <form:options items="${workshopList}" itemLabel="name" itemValue="id" />
            </form:select>
        </div>
        <div class="has-error col-sm-10 pull-right"><form:errors path="dcWorkshopId" class="help-block"/></div>
    </div>
    <div class="form-group">
        <div class="col-sm-12">
            <a type="button" href="dealer/list" class="btn btn-default" >Cancel</a>
            <button type="submit" style="margin-right:10px" class="btn btn-primary pull-right">Save</button>
            <c:if test="${not empty dealerData.id}">                                                
                    <button type="button" style="margin-right:10px" class="btn btn-primary pull-right" data-toggle="modal" data-target="#modal-contact" 
                                 data-record-id="${dealerData.id}">Contacts</button>
            </c:if>
        </div>
    </div>
</form:form>
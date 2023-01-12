<%@ page pageEncoding="UTF-8" %>
<c:set var="message_type" value="${param.message_type.toLowerCase()}"/>
//TODO: check this logic in order to make this template generic
<c:choose>
    <c:when test="${message_type == 'error' or message_type = 'warning'}">
        <c:set var="message_icon" value="warning"/>
    </c:when>
    <c:when test="${message_type == 'info'}">
        <c:set var="message_icon" value="info"/>
    </c:when>
</c:choose>
<jsp:include page="../../jspf/common/flash-message.jspf" flush="false">
    <jsp:param name="message_text" value="${message.text}"/>
    <jsp:param name="message_type" value="${message.messageType}"/>
</jsp:include>

<div class="pad margin no-print" id="messages">
    <div class="alert alert-${message_type} alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <h4><i class="icon fa fa-${message_icon}"></i> ${param.message_type}</h4>
        ${param.message_text}
    </div>
</div>

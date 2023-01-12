<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="modal_id" value="modal-confirm" scope="page"/>
<c:if test="${param.modal_id != null}">
    <c:set var="modal_id" value="${param.modal_id}" scope="page"/>
</c:if>
<c:set var="title" value="Confirmation" scope="page"/>
<c:if test="${param.title != null}">
    <c:set var="title" value="${param.title}" scope="page"/>
</c:if>
<c:set var="message" value="Are you sure you want to perfrom this operation?" scope="page"/>
<c:if test="${param.message  != null}">
    <c:set var="message" value="${param.message}" scope="page"/>
</c:if>
<c:set var="btn_ok_text" value="Yes" scope="page"/>
<c:if test="${param.btn_ok_text != null}">
    <c:set var="btn_ok_text" value="${param.btn_ok_text}" scope="page"/>
</c:if>
<c:set var="btn_cancel_text" value="Cancel" scope="page"/>
<c:if test="${param.btn_cancel_text != null}">
    <c:set var="btn_cancel_text" value="${param.btn_cancel_text}" scope="page"/>
</c:if>
<div class="modal fade" id="${modal_id}">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">${title}</h4>
            </div>
            <div class="modal-body">
                <h4>${message}</h4>
            </div>
            <div class="modal-footer">
                <button type="button" id="${modal_id}-btn-cancel" class="btn btn-default pull-left btn-cancel" data-dismiss="modal">${btn_cancel_text}</button>
                <button type="button" id="${modal_id}-btn-ok" href="#" class="btn btn-primary">${btn_ok_text}</button>
            </div>
        </div>
    </div>
</div>
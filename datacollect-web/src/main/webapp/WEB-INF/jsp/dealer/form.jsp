<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <%@include file="../../fragments/common/head.jsp" %>
        <%@include file="../../fragments/common/common-js-pre-body.jsp" %>
        <%@include file="../../fragments/common/common-css.jsp" %>
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
                    <jsp:param name="title" value="Dealer"/>
                    <jsp:param name="subtitle" value="Form"/>
                </jsp:include>
                <!-- /.content-header -->

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">
                                        <c:choose>
                                            <c:when test="${edit==false}">
                                                Create New
                                            </c:when>
                                            <c:otherwise>
                                                Edit Dealer ${dealerData.id}
                                            </c:otherwise>
                                        </c:choose>
                                    </h3>
                                </div>
                                <div class="box-body">
                                    <ul class="nav nav-tabs m-b-1">
                                        <li class="active"><a data-toggle="tab" href="#general">General</a></li>
                                        <c:if test="${edit==true}">
                                            <li><a data-toggle="tab" href="#data-sheet">Data Sheet</a></li>
                                        </c:if>
                                    </ul>
                                    <div class="tab-content">
                                        <div id="general" class="tab-pane fade in active">
                                            <jsp:include page="../../fragments/dealer/dealer-general-info.jsp" flush="false">
                                                <jsp:param name="dealerData" value="${dealerData}"/>
                                                <jsp:param name="stateList" value="${stateList}"/>
                                                <jsp:param name="dealerGroupList" value="${dealerGroupList}"/>
                                                <jsp:param name="terchiefList" value="${terchiefList}"/>
                                            </jsp:include>
                                        </div>
                                        <c:if test="${edit==true}">
                                            <div id="data-sheet" class="tab-pane fade">
                                                <jsp:include page="../../fragments/dealer/dealer-data-sheet.jsp" flush="false">
                                                    <jsp:param name="dealerData" value="${dealerData}"/>
                                                    <jsp:param name="stateList" value="${stateList}"/>
                                                    <jsp:param name="dealerGroupList" value="${dealerGroupList}"/>
                                                    <jsp:param name="terchiefList" value="${terchiefList}"/>
                                                </jsp:include>
                                            </div>
                                        </c:if>                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <!-- /.content -->
                
                <div class="modal fade" id="modal-contact" tabindex='-1'>
                    <div style="margin:10px" class="modal-dialog">
                        <div  style="min-width:1500px; min-height:500px;" class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title"></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                  <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <iframe style="width:1450px; height:500px;" frameBorder="0" src="contact/list/${dealerData.id}/dealer" id="contactModal" ></iframe>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.END PARTICULAR CONTENT BY PAGE ********************* -->
            </div>
            <!-- /.content-wrapper -->

            <!-- =============================================== -->

            <%@include file="../../fragments/template-based/main-footer.jsp" %>
            <!-- /.main-footer -->

        </div>
        <!-- ./wrapper -->

        <%@include file="../../fragments/common/common-js-post-body.jsp" %>
        <!-- put below here specific JS code required by this page -->
        <!-- page script -->
        <script src="js/util.forms.validations.js"></script>
        <script>
        	<%-- TODO: declare as const instead of var --%>
            var onLoadDealerId = '';
            <c:if test="${not empty dealerData.id}">
                onLoadDealerId = ${dealerData.id};
            </c:if>
            
        </script>
        <script src="js/view.dealer.form.js"></script>
    </body>

</html>
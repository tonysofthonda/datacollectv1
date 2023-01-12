<%-- 
    Document   : template
    Created on : Dec 8, 2018, 10:57:16 AM
    Author     : Cesar Martinez <cesar_x_martinez@hdm.honda.com>
--%>
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
                    <jsp:param name="title" value="Dashboard"/>
                    <jsp:param name="subtitle" value="Statistics"/>
                </jsp:include>
                <!-- /.content-header -->
                    
                <!-- Main content -->
                <section class="content">
                    
                    <div class="row">
                        <div class="col-md-12">
                          <div class="box">
                            <div class="box-header with-border">
                              <h3 class="box-title">Section A Header</h3>
                              <div class="box-tools pull-right">
                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                              </div>
                            </div>
                            <!-- /.box-header -->
                            <div class="box-body">
                              <div class="row">
                                <div class="col-md-12">
                                  <p class="text-center">
                                    <strong>Section A Content</strong>
                                    
                                    <% 
                                        int a = 2; 
                                        out.print(a);
                                    %>
                                    Hello World!<%=a%>
                                    <br/>
                                    Using a parameter from model: ${a}
                                  </p>
                                </div>
                                <!-- /.col -->
                              </div>
                              <!-- /.row -->
                            </div>
                            <!-- ./box-body -->
                            <div class="box-footer">
                              <div class="row">
                                <div class="col-sm-12 col-xs-12">
                                  <div class="description-block border-right border-left">
                                    <span class="description-text">Section A Footer</span>
                                  </div>
                                  <!-- /.description-block -->
                                </div>
                                <!-- /.col -->
                              </div>
                              <!-- /.row -->
                            </div>
                            <!-- /.box-footer -->
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

            <%@include file="../../fragments/template-based/main-footer.jsp" %>
            <!-- /.main-footer -->

        </div>
        <!-- ./wrapper -->
        
        <%@include file="../../fragments/common/common-js-post-body.jsp" %>
        <!-- put below here specific JS code required by this page -->
        
    </body>
    
</html>
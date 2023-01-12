<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="img/honda_logo.jpg" class="img-circle" alt="User Image"/>
            </div>
            <div class="pull-left info">
                <p>Honda User</p>
                <!--<p>${user.name} ${user.lastname}</p>-->
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- /.user-panel -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">MAIN NAVIGATION</li>
            <c:if test="${menuCategories != null}">
	            <c:forEach items="${menuCategories}" var="category">
			         <li class="treeview">
		                <a href="#">
		                    <i class="fa fa-lock"></i> <span>${category.getName()}</span>
		                    <span class="pull-right-container">
		                        <i class="fa fa-angle-left pull-right"></i>
		                    </span>
		                </a>
		                <ul class="treeview-menu">
			                <c:forEach items="${category.getViews()}" var="view">
			                	<li><a href="${view.getRoute()}"><i class="fa fa-circle-o"></i> ${view.getFriendlyName()}</a></li>
			                </c:forEach>
		                </ul>
		            </li>
		      	</c:forEach>
	      	</c:if>
            <!-- <li class="treeview">
                <a href="#">
                    <i class="fa fa-lock"></i> <span>Catalogs</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="terchief/list"><i class="fa fa-circle-o"></i> Heads of territory</a></li>
                    <li><a href="dealer/list"><i class="fa fa-circle-o"></i>Dealers</a></li>
                    <li><a href="dealergroup/list"><i class="fa fa-circle-o"></i>Dealer Groups</a></li>
                    <li><a href="facility/list"><i class="fa fa-circle-o"></i>Facilities </a></li>
                    <li><a href="model/list"><i class="fa fa-circle-o"></i>Models </a></li>
                </ul>
            </li>-->
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
<script src="js/view.leftmenu.util.js"></script>

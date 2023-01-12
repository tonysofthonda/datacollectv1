<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" %>
<header class="main-header">

    <!-- Logo -->
    <a href="welcome" class="logo">
        <!-- mini logo for side-bar mini 50x50 pixels -->
        <span class="logo-mini">DTC</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>Data</b> Collect</span>
    </a>

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>

        <!-- Navbar Right Menu -->
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- Notifications: style can be found in dropdown.less -->
                <li class="dropdown notifications-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bell-o"></i>
                        <span class="label label-warning">2</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">You have 2 notifications</li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul class="menu">
                                <li>
                                    <a href="#">
                                        <i class="fa fa-warning text-yellow"></i> Test 1
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fa fa-info-circle text-aqua"></i> Test 2
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="footer"><a href="#">View all</a></li>
                    </ul>
                </li>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.authenticated}">
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <img src="img/avatar_non_user.jpg" class="user-image" alt="User Image">
                                <span class="hidden-xs">${pageContext.request.userPrincipal.name}</span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header">
                                    <img src="img/avatar_non_user.jpg" class="img-circle" alt="User Image">

                                    <p>
                                        ${pageContext.request.userPrincipal.name}
                                        <small>Member since Nov. 2012</small>
                                    </p>
                                </li>
                                <!-- Menu Body -->
                                <!--<li class="user-body">
                                    
                                </li>-->
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <form:form id="logoutForm" method="post" action="logout">
                                        <div class="pull-right">
                                            <button type="submit" onclick="document.getElementById('logoutForm').submit();" class="btn btn-default btn-flat">Sign out</button>
                                        </div>
                                    </form:form>
                                </li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a class="btn btn-outline-primary" href="login/login">
                                Sign In
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>

</header>
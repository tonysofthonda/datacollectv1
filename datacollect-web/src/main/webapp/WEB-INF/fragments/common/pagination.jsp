<%-- any content can be specified here e.g.: --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page pageEncoding="UTF-8" %>
            <div class="box-footer clearfix">
                <div class="pull-left">Showing ${(actualPage*sizePage)+1} to ${(actualPage*sizePage)+totalMessagesThisPage} of ${totalMessages}</div>
                                    <ul class="pagination pagination-md no-margin pull-right">
                                        <c:choose>
                                            <c:when test="${totalPages>7}">
                                                <c:choose>
                                                    <c:when test="${actualPage==0}">
                                                        <li class="disabled"><a>Previous</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <li><a href="sms/list/${actualPage-1}/${sizePage}">Previous</a></li>
                                                        </c:otherwise>
                                                    </c:choose>

                                                <c:forEach begin="1" end="7" var="page">
                                                    <c:choose>
                                                        <c:when test="${page==1}">
                                                            <c:choose>
                                                                <c:when test="${actualPage==page-1}">
                                                                    <li class="active"><a>${page}</a></li>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                    <li><a href="sms/list/${page-1}/${sizePage}">${page}</a></li>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:when>
                                                            <c:when test="${page==2}">
                                                                <c:choose>
                                                                    <c:when test="${actualPage>=4}">
                                                                    <li class="disabled"><a>...</a></li>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <c:choose>
                                                                            <c:when test="${actualPage==page-1}">
                                                                            <li class="active"><a>${page}</a></li>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                            <li><a href="sms/list/${page-1}/${sizePage}">${page}</a></li>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:when>
                                                            <c:when test="${page==3}">
                                                                <c:choose>
                                                                    <c:when test="${actualPage<2}">
                                                                    <li><a href="sms/list/${page-1}/${sizePage}">${page}</a></li>
                                                                    </c:when>
                                                                    <c:when test="${actualPage==2}">
                                                                    <li class="active"><a>${page}</a></li>
                                                                        </c:when>
                                                                        <c:when test="${actualPage>2}">
                                                                            <c:choose>
                                                                                <c:when test="${totalPages-(actualPage+1)<=3}">
                                                                            <li><a href="sms/list/${totalPages-5}/${sizePage}">${totalPages-4}</a></li>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                            <li><a href="sms/list/${actualPage-1}/${sizePage}">${actualPage}</a></li>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:when>
                                                                </c:choose>
                                                            </c:when>
                                                            <c:when test="${page==4}">
                                                                <c:choose>
                                                                    <c:when test="${actualPage<3}">
                                                                    <li><a href="sms/list/${page-1}/${sizePage}">${page}</a></li>
                                                                    </c:when>
                                                                    <c:when test="${actualPage==3}">
                                                                    <li class="active"><a>${page}</a></li>
                                                                        </c:when>
                                                                        <c:when test="${actualPage>3}">
                                                                            <c:choose>
                                                                                <c:when test="${totalPages-(actualPage+1)<=2}">
                                                                            <li><a href="sms/list/${totalPages-4}/${sizePage}">${totalPages-3}</a></li>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                            <li class="active"><a>${actualPage+1}</a></li>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:when>
                                                                </c:choose>
                                                            </c:when>
                                                            <c:when test="${page==5}">
                                                                <c:choose>
                                                                    <c:when test="${actualPage<4}">
                                                                    <li><a href="sms/list/${page-1}/${sizePage}">${page}</a></li>
                                                                    </c:when>
                                                                    <c:when test="${actualPage==4}">
                                                                    <li><a href="sms/list/${page}/${sizePage}">${page+1}</a></li>
                                                                    </c:when>
                                                                    <c:when test="${actualPage>4}">
                                                                        <c:choose>
                                                                            <c:when test="${totalPages-(actualPage+1)<=2}">
                                                                                <c:choose>
                                                                                    <c:when test="${(totalPages-2)==(actualPage+1)}">
                                                                                    <li class="active"><a>${totalPages-2}</a></li>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                    <li><a href="sms/list/${totalPages-3}/${sizePage}">${totalPages-2}</a></li>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                            <li><a href="sms/list/${actualPage+1}/${sizePage}">${actualPage+2}</a></li>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:when>
                                                                </c:choose>
                                                            </c:when>            
                                                            <c:when test="${page==6}">
                                                                <c:choose>
                                                                    <c:when test="${(totalPages-1-actualPage)>3}">
                                                                    <li class="disabled"><a>...</a></li>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <c:choose>
                                                                            <c:when test="${actualPage==totalPages-2}">
                                                                            <li class="active"><a>${totalPages-1}</a></li>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                            <li><a href="sms/list/${totalPages-2}/${sizePage}">${totalPages-1}</a></li>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:when>                    
                                                            <c:when test="${page==7}">
                                                                <c:choose>
                                                                    <c:when test="${actualPage==totalPages-1}">
                                                                    <li class="active"><a>${totalPages}</a></li>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                    <li><a href="sms/list/${totalPages-1}/${sizePage}">${totalPages}</a></li>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:when>
                                                        </c:choose>
                                                    </c:forEach>        

                                                <c:choose>
                                                    <c:when test="${actualPage==totalPages-1}">
                                                        <li class="disabled"><a>Next</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <li><a href="sms/list/${actualPage+1}/${sizePage}">Next</a></li>
                                                        </c:otherwise>
                                                    </c:choose>        
                                                </c:when>
                                                <c:otherwise>
                                                    <c:choose>
                                                        <c:when test="${actualPage==0}">
                                                        <li class="disabled"><a>Previous</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <li><a href="sms/list/${actualPage-1}/${sizePage}">Previous</a></li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:forEach begin="0" end="${totalPages-1}" var="page">
                                                        <c:choose>
                                                            <c:when test="${actualPage==page}">
                                                            <li class="active"><a>${page+1}</a></li>
                                                                </c:when>
                                                                <c:otherwise>
                                                            <li><a href="sms/list/${page}/${sizePage}">${page+1}</a></li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                    <c:choose>
                                                        <c:when test="${actualPage==totalPages-1}">
                                                        <li class="disabled"><a>Next</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <li><a href="sms/list/${actualPage+1}/${sizePage}">Next</a></li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:otherwise>
                                            </c:choose>
                                    </ul>
            </div>
                
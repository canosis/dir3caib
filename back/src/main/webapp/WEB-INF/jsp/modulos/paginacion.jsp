<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:if test="${paginacion.totalPages > 1}">
    <div class="pagination pagination-right">

        <c:url var="firstUrl" value="/${param.elemento}/${param.entidad}/list/1" />
        <c:url var="lastUrl" value="/${param.elemento}/${param.entidad}/list/${paginacion.totalPages}" />
        <c:url var="prevUrl" value="/${param.elemento}/${param.entidad}/list/${paginacion.currentIndex - 1}" />
        <c:url var="nextUrl" value="/${param.elemento}/${param.entidad}/list/${paginacion.currentIndex + 1}" />

        <ul>
            <c:choose>
                <c:when test="${paginacion.currentIndex == 1}">
                </c:when>
                <c:otherwise>
                    <%--<li><a href="${firstUrl}">&lt;&lt;</a></li>--%>
                    <li><a href="${prevUrl}">&laquo;</a></li>
                </c:otherwise>
            </c:choose>

            <c:forEach var="i" begin="${paginacion.beginIndex}" end="${paginacion.endIndex}">
                <c:url var="pageUrl" value="/${param.elemento}/${param.entidad}/list/${i}" />
                <c:choose>
                    <c:when test="${i == paginacion.currentIndex}">
                        <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${paginacion.currentIndex == paginacion.totalPages}">
                </c:when>
                <c:otherwise>
                    <li><a href="${nextUrl}">&raquo;</a></li>
                    <%--<li><a href="${lastUrl}">&gt;&gt;</a></li>--%>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</c:if>
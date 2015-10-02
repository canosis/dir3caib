<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/modulos/includes.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <c:import url="../modulos/imports.jsp"/>
    </head>

    <body>

        <c:import url="../modulos/menu.jsp"/>

        <div class="row-fluid container main">
            <div class="well well-dir3caib">

                <div class="row-fluid">

                    <div class="box span12">

                        <div class="box-header well">
                            <h2><fmt:message key="unidad.existentes"/></h2>
                        </div>

                        <c:import url="../modulos/mensajes.jsp"/>
                       
                        <div class="box-content"> 
                            <c:if test="${empty descarga}">
                                <div class="alert fade in">
                                    <fmt:message key="unidad.existentes.notfound"/><strong><a href="<c:url value="/unidad/obtener"/>"><fmt:message key="dir3caib.obtenerws"/></a></strong>
                                </div>
                            </c:if>
                            <c:if test="${not empty descarga}">
                                <c:if test="${not empty existentes}">
                                
                                    <table class="table table-bordered">
                                        <colgroup>
                                            <col>
                                        </colgroup>
                                        <thead>
                                            <tr>
                                                <th><fmt:message key="dir3caib.fichero"/></th>
                                                <th><fmt:message key="dir3caib.fechasincronizacion"/></th>
                                                <th><fmt:message key="dir3caib.fechaimportacion"/></th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <c:forEach var="existente" items="${existentes}">
                                                <tr>
                                                    <td><a href="<c:url value="/archivo/${existente}/unidad"/>" target="_blank">${existente}</a></td>
                                                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${descarga.fechaFin}" /></td>
                                                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${descarga.fechaImportacion}" /></td>
                                                </tr>
                                            </c:forEach>
                                          </tbody>
                                      </table>
                                    <div class="form-horizontal">
                                      <div class="form-actions">
                                       <%-- <c:if test="${not empty mostrarimportacion}">--%>
                                          <input type="button" value="<fmt:message key="dir3caib.boton.importar"/>" onclick="javascript:confirmDescarga('<c:url value="/unidad/importar"/>','<fmt:message key="dir3caib.confirm.importar"/>');" class="btn btn-primary">
                                        <%--</c:if>
                                        <c:if test="${empty mostrarimportacion}">
                                               <input type="button" value="<fmt:message key="dir3caib.boton.importar"/>" class="btn btn-primary disabled">
                                        </c:if>--%>
                                        <input type="button" value="<fmt:message key="dir3caib.boton.eliminartodos"/>" onclick="javascript:confirm('<c:url value="/unidad/eliminar"/>','<fmt:message key="dir3caib.confirm.eliminar"/>');" class="btn btn-primary">
                                       </div>
                                    </div>

                                </c:if>
                                <c:if test="${empty existentes}">
                                    <fmt:message key="dir3caib.datos.nohay"/>
                                </c:if>
                            </c:if>
                        </div>

                    </div>
                </div>

                <hr>

              <%--  <footer>
                    <p><fmt:message key="dir3caib.version"/></p>
                </footer>--%>

            </div>
        </div>

        <c:import url="../modulos/pie.jsp"/>

    </body>
</html>
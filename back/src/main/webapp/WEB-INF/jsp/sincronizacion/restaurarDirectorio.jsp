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
                            <h2><fmt:message key="sincronizacion.restaurarDirectorio"/></h2>
                        </div>

                        <c:import url="../modulos/mensajes.jsp"/>
                       
                        <div class="box-content">
                            <div class="well formbox">

                                <div class="page-header"><fmt:message key="sincronizacion.restaurarDirectorio"/></div>
                                <div class="row-fluid"><fmt:message key="sincronizacion.restaurarDirectorio.info"/></div>

                                <form:form method="post" cssClass="form-horizontal">
                                    <fieldset>

                                        <div class="form-actions">

                                            <input type="submit" value="<fmt:message key="sincronizacion.restaurarDirectorio"/>" class="btn btn-primary">

                                        </div>

                                     </fieldset>

                                </form:form>
                        </div>
                    </div>

                    </div>
                </div>

                <hr>

            </div>
        </div>

        <c:import url="../modulos/pie.jsp"/>

    </body>
</html>
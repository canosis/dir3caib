package es.caib.dir3caib.ws.api.oficina;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.6.4
 * 2015-11-17T08:51:37.834+01:00
 * Generated source version: 2.6.4
 * 
 */
@WebService(targetNamespace = "http://oficina.ws.dir3caib.caib.es/", name = "Dir3CaibObtenerOficinasWs")
@XmlSeeAlso({ObjectFactory.class})
public interface Dir3CaibObtenerOficinasWs {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "obtenerOficinasSIRUnidad", targetNamespace = "http://oficina.ws.dir3caib.caib.es/", className = "es.caib.dir3caib.ws.api.oficina.ObtenerOficinasSIRUnidad")
    @WebMethod
    @ResponseWrapper(localName = "obtenerOficinasSIRUnidadResponse", targetNamespace = "http://oficina.ws.dir3caib.caib.es/", className = "es.caib.dir3caib.ws.api.oficina.ObtenerOficinasSIRUnidadResponse")
    public java.util.List<es.caib.dir3caib.ws.api.oficina.OficinaTF> obtenerOficinasSIRUnidad(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getVersion", targetNamespace = "http://oficina.ws.dir3caib.caib.es/", className = "es.caib.dir3caib.ws.api.oficina.GetVersion")
    @WebMethod
    @ResponseWrapper(localName = "getVersionResponse", targetNamespace = "http://oficina.ws.dir3caib.caib.es/", className = "es.caib.dir3caib.ws.api.oficina.GetVersionResponse")
    public java.lang.String getVersion();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getVersionWs", targetNamespace = "http://oficina.ws.dir3caib.caib.es/", className = "es.caib.dir3caib.ws.api.oficina.GetVersionWs")
    @WebMethod
    @ResponseWrapper(localName = "getVersionWsResponse", targetNamespace = "http://oficina.ws.dir3caib.caib.es/", className = "es.caib.dir3caib.ws.api.oficina.GetVersionWsResponse")
    public java.lang.String getVersionWs();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "obtenerFechaUltimaActualizacion", targetNamespace = "http://oficina.ws.dir3caib.caib.es/", className = "es.caib.dir3caib.ws.api.oficina.ObtenerFechaUltimaActualizacion")
    @WebMethod
    @ResponseWrapper(localName = "obtenerFechaUltimaActualizacionResponse", targetNamespace = "http://oficina.ws.dir3caib.caib.es/", className = "es.caib.dir3caib.ws.api.oficina.ObtenerFechaUltimaActualizacionResponse")
    public java.sql.Timestamp obtenerFechaUltimaActualizacion();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "obtenerArbolOficinas", targetNamespace = "http://oficina.ws.dir3caib.caib.es/", className = "es.caib.dir3caib.ws.api.oficina.ObtenerArbolOficinas")
    @WebMethod
    @ResponseWrapper(localName = "obtenerArbolOficinasResponse", targetNamespace = "http://oficina.ws.dir3caib.caib.es/", className = "es.caib.dir3caib.ws.api.oficina.ObtenerArbolOficinasResponse")
    public java.util.List<es.caib.dir3caib.ws.api.oficina.OficinaTF> obtenerArbolOficinas(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.sql.Timestamp arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.sql.Timestamp arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "obtenerOficina", targetNamespace = "http://oficina.ws.dir3caib.caib.es/", className = "es.caib.dir3caib.ws.api.oficina.ObtenerOficina")
    @WebMethod
    @ResponseWrapper(localName = "obtenerOficinaResponse", targetNamespace = "http://oficina.ws.dir3caib.caib.es/", className = "es.caib.dir3caib.ws.api.oficina.ObtenerOficinaResponse")
    public es.caib.dir3caib.ws.api.oficina.OficinaTF obtenerOficina(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.sql.Timestamp arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.sql.Timestamp arg2
    );
}

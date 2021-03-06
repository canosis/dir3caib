package es.caib.dir3caib.ws.api.unidad;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 2.6.4
 * 2015-11-17T08:51:38.996+01:00
 * Generated source version: 2.6.4
 * 
 */
@WebServiceClient(name = "Dir3CaibObtenerUnidadesWsService", 
                  wsdlLocation = "http://localhost:8080/dir3caib/ws/Dir3CaibObtenerUnidades?wsdl",
                  targetNamespace = "http://unidad.ws.dir3caib.caib.es/") 
public class Dir3CaibObtenerUnidadesWsService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://unidad.ws.dir3caib.caib.es/", "Dir3CaibObtenerUnidadesWsService");
    public final static QName Dir3CaibObtenerUnidadesWs = new QName("http://unidad.ws.dir3caib.caib.es/", "Dir3CaibObtenerUnidadesWs");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/dir3caib/ws/Dir3CaibObtenerUnidades?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(Dir3CaibObtenerUnidadesWsService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/dir3caib/ws/Dir3CaibObtenerUnidades?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public Dir3CaibObtenerUnidadesWsService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Dir3CaibObtenerUnidadesWsService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Dir3CaibObtenerUnidadesWsService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns Dir3CaibObtenerUnidadesWs
     */
    @WebEndpoint(name = "Dir3CaibObtenerUnidadesWs")
    public Dir3CaibObtenerUnidadesWs getDir3CaibObtenerUnidadesWs() {
        return super.getPort(Dir3CaibObtenerUnidadesWs, Dir3CaibObtenerUnidadesWs.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Dir3CaibObtenerUnidadesWs
     */
    @WebEndpoint(name = "Dir3CaibObtenerUnidadesWs")
    public Dir3CaibObtenerUnidadesWs getDir3CaibObtenerUnidadesWs(WebServiceFeature... features) {
        return super.getPort(Dir3CaibObtenerUnidadesWs, Dir3CaibObtenerUnidadesWs.class, features);
    }

}

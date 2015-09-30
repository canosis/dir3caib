package es.caib.dir3caib.ws.api.catalogo;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by Apache CXF 2.6.4
 * 2015-09-30T11:58:41.951+02:00
 * Generated source version: 2.6.4
 * 
 */
@WebServiceClient(name = "Dir3CaibObtenerCatalogosWsService", 
                  wsdlLocation = "http://localhost:8080/dir3caib/ws/Dir3CaibObtenerCatalogos?wsdl",
                  targetNamespace = "http://catalogo.ws.dir3caib.caib.es/") 
public class Dir3CaibObtenerCatalogosWsService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://catalogo.ws.dir3caib.caib.es/", "Dir3CaibObtenerCatalogosWsService");
    public final static QName Dir3CaibObtenerCatalogosWs = new QName("http://catalogo.ws.dir3caib.caib.es/", "Dir3CaibObtenerCatalogosWs");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/dir3caib/ws/Dir3CaibObtenerCatalogos?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(Dir3CaibObtenerCatalogosWsService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/dir3caib/ws/Dir3CaibObtenerCatalogos?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public Dir3CaibObtenerCatalogosWsService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Dir3CaibObtenerCatalogosWsService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Dir3CaibObtenerCatalogosWsService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns Dir3CaibObtenerCatalogosWs
     */
    @WebEndpoint(name = "Dir3CaibObtenerCatalogosWs")
    public Dir3CaibObtenerCatalogosWs getDir3CaibObtenerCatalogosWs() {
        return super.getPort(Dir3CaibObtenerCatalogosWs, Dir3CaibObtenerCatalogosWs.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Dir3CaibObtenerCatalogosWs
     */
    @WebEndpoint(name = "Dir3CaibObtenerCatalogosWs")
    public Dir3CaibObtenerCatalogosWs getDir3CaibObtenerCatalogosWs(WebServiceFeature... features) {
        return super.getPort(Dir3CaibObtenerCatalogosWs, Dir3CaibObtenerCatalogosWs.class, features);
    }

}

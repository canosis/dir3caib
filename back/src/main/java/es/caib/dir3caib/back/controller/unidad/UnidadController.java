package es.caib.dir3caib.back.controller.unidad;

import es.caib.dir3caib.back.controller.BaseController;
import es.caib.dir3caib.back.form.FechasForm;
import es.caib.dir3caib.back.form.UnidadBusquedaForm;
import es.caib.dir3caib.back.utils.CodigoValor;
import es.caib.dir3caib.back.utils.Mensaje;
import es.caib.dir3caib.persistence.ejb.*;
import es.caib.dir3caib.persistence.model.*;
import es.caib.dir3caib.persistence.utils.Nodo;
import es.caib.dir3caib.persistence.utils.Paginacion;
import es.caib.dir3caib.persistence.utils.ResultadosImportacion;
import es.caib.dir3caib.utils.Configuracio;
import es.caib.dir3caib.utils.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by Fundació BIT.
 * @author earrivi
 * Date: 2/10/13
 */

@Controller
@RequestMapping(value = "/unidad")
public class UnidadController extends BaseController{

    protected final Logger log = Logger.getLogger(getClass());


    @EJB(mappedName = "dir3caib/ImportadorUnidadesEJB/local")
    private ImportadorUnidadesLocal importadorUnidades;
    
    @EJB(mappedName = "dir3caib/UnidadEJB/local")
    protected UnidadLocal unidadEjb;
    
    @EJB(mappedName = "dir3caib/CatAmbitoTerritorialEJB/local")
    protected CatAmbitoTerritorialLocal catAmbitoTerritorialEjb;

    @EJB(mappedName = "dir3caib/CatProvinciaEJB/local")
    protected CatProvinciaLocal catProvinciaEjb;

    @EJB(mappedName = "dir3caib/ContactoUOEJB/local")
    protected ContactoUOLocal contactoUOEjb;
    
    @EJB(mappedName = "dir3caib/DescargaEJB/local")
    protected DescargaLocal descargaEjb;

    @EJB(mappedName = "dir3caib/OficinaEJB/local")
    protected OficinaLocal oficinaEjb;

    @EJB(mappedName = "dir3caib/ArbolEJB/local")
    protected ArbolLocal arbolEjb;


    // Indicamos el formato de fecha dd/MM/yyyy hh:mm:ss
    SimpleDateFormat formatoFecha = new SimpleDateFormat(Dir3caibConstantes.FORMATO_FECHA);


    /**
     * Listado de los {@link es.caib.dir3caib.persistence.model.Unidad}
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listado() {
        return "redirect:/unidad/list";
    }


    /**
     * Carga el formulario para la busqueda de {@link es.caib.dir3caib.persistence.model.Unidad}
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model)throws Exception {

        CatEstadoEntidad vigente = catEstadoEntidadEjb.findByCodigo(Dir3caibConstantes.ESTADO_ENTIDAD_VIGENTE);

        Unidad unidad = new Unidad();
        unidad.setEstado(vigente);

        UnidadBusquedaForm unidadBusqueda = new UnidadBusquedaForm(unidad,1, false);
        model.addAttribute("unidadBusqueda",unidadBusqueda);

        return "unidad/unidadList";

    }


    /**
     * Realiza la busqueda de {@link es.caib.dir3caib.persistence.model.Unidad} según los parametros del formulario
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ModelAndView list(@ModelAttribute UnidadBusquedaForm busqueda)throws Exception {

        ModelAndView mav = new ModelAndView("unidad/unidadList");

        Unidad unidad = busqueda.getUnidad();

        Long codNivelAdministracion = (unidad.getNivelAdministracion()!=null) ? unidad.getNivelAdministracion().getCodigoNivelAdministracion() : null;
        String codAmbitoTerritorial = (unidad.getCodAmbitoTerritorial()!=null) ? unidad.getCodAmbitoTerritorial().getCodigoAmbito() : null;
        Long codComunidad = (unidad.getCodComunidad()!=null) ? unidad.getCodComunidad().getCodigoComunidad() : null;
        Long codAmbProvincia = (unidad.getCodAmbProvincia()!=null) ? unidad.getCodAmbProvincia().getCodigoProvincia(): null;
        String codEstadoEntidad = (unidad.getEstado()!=null)?unidad.getEstado().getCodigoEstadoEntidad():null;

        Paginacion paginacion = unidadEjb.busqueda(busqueda.getPageNumber(),
                        unidad.getCodigo(),
                        unidad.getDenominacion(),
                        codNivelAdministracion,
                        codAmbitoTerritorial,
                        codComunidad,
                        codAmbProvincia, busqueda.getUnidadRaiz(),codEstadoEntidad);

        busqueda.setPageNumber(1);

        mav.addObject("paginacion", paginacion);
        mav.addObject("unidadBusqueda", busqueda);

        return mav;

    }

    
    /**
     * Muestra los ficheros de unidades que hay en el directorio
     *
     * @param request
     * @return 
     */
    @RequestMapping(value = "/ficheros", method = RequestMethod.GET)
     public ModelAndView ficherosList(HttpServletRequest request) throws Exception{

         ModelAndView mav = new ModelAndView("/unidad/unidadFicheros");
         ArrayList<String> existentes = new ArrayList<String>();
         
         // Obtenemos el listado de ficheros que hay dentro del directorio indicado
         Descarga descarga = descargaEjb.findByTipo(Dir3caibConstantes.UNIDAD);

         if(descarga != null) {
          File f = new File(Configuracio.getUnidadesPath(descarga.getCodigo()));
             if(f.exists()) {
                 existentes = new ArrayList<String>(Arrays.asList(f.list()));

                 // Miramos si debemos mostrar el botón de importación,
                 // solo se muestra si la fecha de Inicio descarga es superior a la fechaImportacion
                 Date fechaInicio = descarga.getFechaInicio();
                 Date fechaImportacion = descarga.getFechaImportacion();

                 if (fechaImportacion != null) {
                     if (fechaInicio != null) {
                         if (fechaInicio.after(fechaImportacion)) {
                             mav.addObject("mostrarimportacion", "mostrarImportacion");
                         }
                     }
                 } else {
                     mav.addObject("mostrarimportacion", "mostrarImportacion");
                 }

                 //mav.addObject("descarga", descarga);
             }else{
                 Mensaje.saveMessageError(request, getMessage("descarga.error.importante"));

             }
        }
        mav.addObject("descarga", descarga);
        mav.addObject("existentes", existentes);
         
        return mav;
     }
    
    /**
     * Muestra el formulario para obtener los unidades mediante el WS de DIR3
     */
    @RequestMapping(value = "/obtener", method = RequestMethod.GET)
    public String obtenerUnidades(Model model)throws Exception {
        Descarga descarga = descargaEjb.findByTipo(Dir3caibConstantes.UNIDAD);
        if(descarga != null){
          model.addAttribute("descarga", descarga);
        }
        model.addAttribute("development", Configuracio.isDevelopment());
        model.addAttribute("fechasForm", new FechasForm());
        
        return "/unidad/unidadObtener";
    }
    

    /**
     * Obtiene las unidades mediante el WS de DIR3
     */
    @RequestMapping(value = "/obtener", method = RequestMethod.POST)
    public String descargaUnidades(@ModelAttribute FechasForm fechasForm, HttpServletRequest request) throws Exception {

        if(descargarUnidadesWS(request, fechasForm.getFechaInicio(), fechasForm.getFechaFin())) {
            return "redirect:/unidad/ficheros";
        }else{
            return "redirect:/unidad/obtener";
        }

    }
    
    /**
      * Importa el contenido de un fichero de las Unidades a la bbdd
      * @param request
      * @return 
      */
     @RequestMapping(value = "/importar", method = RequestMethod.GET)
     public ModelAndView importarUnidades(HttpServletRequest request) throws Exception {
         
         ModelAndView mav = new ModelAndView("/unidad/unidadImportacion");
         
         //Date hoy = new Date();
         
         long start = System.currentTimeMillis();
         
         ResultadosImportacion results = importadorUnidades.importarUnidades();
         
         long end = System.currentTimeMillis();
         log.info("Importat unidades en " + Utils.formatElapsedTime(end - start));

         Mensaje.saveMessageInfo(request, getMessage("unidad.importacion.ok"));
         mav.addObject("procesados", results.getProcesados());
         mav.addObject("ficheros",Dir3caibConstantes.UO_FICHEROS);
         mav.addObject("existentes", results.getExistentes());
         mav.addObject("descarga" , results.getDescarga());
         
         return mav;
     }
     
     /**
      * Elimina los ficheros de las unidades del sistema de archivos
      * @param request
      * @return 
      */
     @RequestMapping(value = "/eliminar", method = RequestMethod.GET)
     public ModelAndView eliminarUnidadesCompleto(HttpServletRequest request){
         ModelAndView mav = new ModelAndView("/unidad/unidadFicheros");
         

         
         try {
           Descarga descarga = descargaEjb.findByTipo(Dir3caibConstantes.UNIDAD);
           File directorio = new File(Configuracio.getUnidadesPath(descarga.getCodigo()));
           // Contactos 
           contactoUOEjb.deleteAll();
           //Unidades 
           unidadEjb.deleteHistoricosUnidad();
           unidadEjb.deleteAll();
           descargaEjb.deleteAllByTipo(Dir3caibConstantes.UNIDAD);
         
           FileUtils.cleanDirectory(directorio);
           Mensaje.saveMessageInfo(request, getMessage("unidad.borrar.ok"));
         } catch (Exception ex) {
             Mensaje.saveMessageError(request, getMessage("dir3caib.borrar.directorio.error"));
             ex.printStackTrace();
         } 
         
         return mav;
     }
     
     
      /**
      * Sincroniza las unidades.Obtiene las unidades y sus relaciones a traves de WS desde la última fecha de 
      * sincronización e importa los datos.
      * @param request
      * 
      */
     @RequestMapping(value = "/sincronizar", method = RequestMethod.GET)
     public ModelAndView sincronizarUnidades(HttpServletRequest request){
        
        try{
          // Obtenemos la fecha de la ultima descarga/sincronizacion
          Descarga ultimaDescarga = descargaEjb.findByTipo(Dir3caibConstantes.UNIDAD);
          Date hoy = new Date();
          // Obtenemos los archivos por WS
          boolean descargaOk= descargarUnidadesWS(request, ultimaDescarga.getFechaFin(), hoy);
          // Importamos los datos a la BD si la descarga ha ido bien
          if(descargaOk) {  return importarUnidades(request);}
 
        }catch(Exception ex){
          Mensaje.saveMessageError(request, getMessage("unidad.sincronizacion.error"));
          ex.printStackTrace();
        }
        return new ModelAndView("/unidad/unidadImportacion");
     }
     
     /**
     * Método que se encarga de obtener los archivos de las unidades a través de WS
     * @param request
     * @param fechaInicio
     * @param fechaFin
     */     
    public boolean descargarUnidadesWS(HttpServletRequest request, Date fechaInicio, Date fechaFin) throws Exception {
        try{
            String[] respuesta= importadorUnidades.descargarUnidadesWS(fechaInicio, fechaFin);
            if(Dir3caibConstantes.CODIGO_RESPUESTA_CORRECTO.equals(respuesta[0])){
                Mensaje.saveMessageInfo(request, getMessage("unidad.descarga.ok"));
                return true;
            }else{

                if(Dir3caibConstantes.CODIGO_RESPUESTA_VACIO.equals(respuesta[0])){
                    Mensaje.saveMessageInfo(request, getMessage("unidad.nueva.nohay"));
                    return true;
                }else {
                    Mensaje.saveMessageError(request, getMessage("unidad.descarga.nook")+ ": " + respuesta[1]);
                    return false;
                }
            }
        }catch(IOException ex){
            Mensaje.saveMessageError(request, getMessage("unidad.descomprimir.error"));
            ex.printStackTrace();
            return false;
        }catch (Exception e){
            Mensaje.saveMessageError(request, getMessage("unidad.descarga.nook"));
            e.printStackTrace();
            return false;
        }
    }

   /**
   * Método que obtiene el árbol de unidades de una unidad.
   * @param request
   * @param idUnidad
   * @return
   */
    @RequestMapping(value = "/{idUnidad}/{estadoUnidad}/arbol", method = RequestMethod.GET)
    public ModelAndView mostrarArbolUnidades(HttpServletRequest request, @PathVariable String idUnidad, @PathVariable String estadoUnidad) throws Exception {

        Long start = System.currentTimeMillis();
        ModelAndView mav = new ModelAndView("/arbolList");
        Nodo nodo = new Nodo();
        arbolEjb.arbolUnidades(idUnidad, nodo, estadoUnidad, true);
        Long end = System.currentTimeMillis();

        log.info("TIEMPO CARGA ARBOLarbol: " + Utils.formatElapsedTime(end - start));

      mav.addObject("nodo", nodo);
      return mav;

    }

    /**
     * Obtiene los {@link es.caib.dir3caib.persistence.model.CatAmbitoTerritorial} del nivel administracion seleccionado
     */
    @RequestMapping(value = "/ambitosTerritoriales", method = RequestMethod.GET)
    public @ResponseBody
    List<CodigoValor> ambitosTerritoriales(@RequestParam Long id) throws Exception {

        List<CatAmbitoTerritorial> ambitos = catAmbitoTerritorialEjb.getByAdministracion(id);
        List<CodigoValor> codigosValor= new ArrayList<CodigoValor>();
        for(CatAmbitoTerritorial ambito :ambitos){
           CodigoValor codigoValor = new CodigoValor();
           codigoValor.setId(ambito.getCodigoAmbito());
           codigoValor.setDescripcion(ambito.getDescripcionAmbito());
           codigosValor.add(codigoValor);
        }
        return codigosValor;
    }

    /**
     * Obtiene los {@link es.caib.dir3caib.persistence.model.CatProvincia} de la comunidad autonoma seleccionada
     */
    @RequestMapping(value = "/provincias", method = RequestMethod.GET)
    public @ResponseBody
    List<CodigoValor> provincias(@RequestParam Long id) throws Exception {

      List<CatProvincia> provincias = catProvinciaEjb.getByComunidadAutonoma(id);
      List<CodigoValor> codigosValor= new ArrayList<CodigoValor>();
      for(CatProvincia provincia :provincias){
         CodigoValor codigoValor = new CodigoValor();
         codigoValor.setId(provincia.getCodigoProvincia());
         codigoValor.setDescripcion(provincia.getDescripcionProvincia());
         codigosValor.add(codigoValor);
      }
      return codigosValor;
    }


    /**
     * Método que se encarga de listar todas las descargas que se han realizado del catálogo
     */
    @RequestMapping(value = "/descarga/list", method = RequestMethod.GET)
    public String listadoDescargaUnidad() {

        return "redirect:/unidad/descarga/list/1";
    }

    /**
     * Listado de tipos de asunto
     * @param pageNumber
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/descarga/list/{pageNumber}", method = RequestMethod.GET)
    public ModelAndView descargaUnidadList(@PathVariable Integer pageNumber)throws Exception {

        ModelAndView mav = new ModelAndView("/descargaList");



        List<Descarga> listado = descargaEjb.getPaginationByTipo(((pageNumber - 1) * BaseEjbJPA.RESULTADOS_PAGINACION), Dir3caibConstantes.UNIDAD );
        log.info("LISTADO: " + listado.size());

        Long total = descargaEjb.getTotalByTipo(Dir3caibConstantes.UNIDAD);

        Paginacion paginacion = new Paginacion(total.intValue(), pageNumber);

        mav.addObject("paginacion", paginacion);
        mav.addObject("listado", listado);
        mav.addObject("elemento", "unidad");

        return mav;
    }

     
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        CustomDateEditor dateEditor = new CustomDateEditor(sdf, true);

        binder.registerCustomEditor(java.util.Date.class, dateEditor);
    }

}

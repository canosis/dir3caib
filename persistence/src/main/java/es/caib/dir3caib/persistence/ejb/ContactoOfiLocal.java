/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.caib.dir3caib.persistence.ejb;

import es.caib.dir3caib.persistence.model.ContactoOfi;

/**
 *
 * @author mgonzalez
 */
public interface ContactoOfiLocal extends BaseEjb<ContactoOfi, Long>{
  
   void deleteAll() throws Exception;

    void deleteByOficina(String idOficina) throws Exception;
}

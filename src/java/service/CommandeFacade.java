/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author WAFA
 */
@Stateless
public class CommandeFacade extends AbstractFacade<Commande> {

    @PersistenceContext(unitName = "gestionStockInventairePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeFacade() {
        super(Commande.class);
    }
    
    public void clone(Commande commandeSource,Commande commandeDestination){
        commandeDestination.setId(commandeSource.getId());
        commandeDestination.setDateCommande(commandeSource.getDateCommande());
        commandeDestination.setMontantTotal(commandeSource.getMontantTotal());
     }
    
    public Commande clone(Commande commande){
        Commande cloned=new Commande();
        clone(commande, cloned);
        return cloned;
    }
             
}
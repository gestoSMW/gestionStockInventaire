/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.CommandeItem;
import bean.Produit;
import controler.util.SearchUtil;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author WAFA
 */
@Stateless
public class CommandeItemFacade extends AbstractFacade<CommandeItem> {

    @PersistenceContext(unitName = "gestionStockInventairePU")
    private EntityManager em;

    public List<CommandeItem> findByCommande(Commande commande){
        return em.createQuery("SELECT ci FROM CommandeItem ci WHERE ci.commande.id='"+commande.getId()+"'").getResultList();
    }
    
    public List<CommandeItem> search(Commande commande,Produit produit,Double prixMax,Double prixMin,Double qteMax,Double qteMin){
        String req="SELECT ci FROM CommandeItem ci WHERE 1=1 ";
        req +=SearchUtil.addConstraintMinMax("ci", "prixCommandeItem", prixMin, prixMax);
        req +=SearchUtil.addConstraintMinMax("ci", "quantite", qteMin, qteMax);
        if (produit != null) {
            req += SearchUtil.addConstraint("ci", "produit.id", "=", produit.getId());
        }
        if (commande != null) {
            req += SearchUtil.addConstraint("ci", "commande.id", "=", commande.getId());
        }
        return em.createQuery(req).getResultList();
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeItemFacade() {
        super(CommandeItem.class);
    }
     public void clone(CommandeItem commandeItemSource,CommandeItem commandeItemDestination){
        commandeItemDestination.setId(commandeItemSource.getId());
        commandeItemDestination.setPrixCommandeItem(commandeItemSource.getPrixCommandeItem());
        commandeItemDestination.setQuantite(commandeItemSource.getQuantite());
     }
    
    public CommandeItem clone(CommandeItem commandeItem){
        CommandeItem cloned=new CommandeItem();
        clone(commandeItem, cloned);
        return cloned;
    }
}

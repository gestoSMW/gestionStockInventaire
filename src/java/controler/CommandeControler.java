 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import bean.Commande;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.CommandeFacade;

/**
 *
 * @author WAFA
 */
@Named(value = "commandeControler")
@SessionScoped
public class CommandeControler implements Serializable {

    private Commande selected;
    private List<Commande> items;
    @EJB
    private CommandeFacade ejbFacade;
    
    private void initParam(){
        selected=new Commande();
    }
    public String create(){
        ejbFacade.edit(selected);
        initParam();
        return null;
    }

    public void delete(Commande commande){
        ejbFacade.remove(commande);
        items.remove(items.indexOf(commande));
    }
    
    public String update(Commande commande){
        selected=commande;
        return "/commande/Create";
    }
    
    public String edit(){
        ejbFacade.edit(selected);
        initParam();
        return null;
    }
    
    public Commande getSelected() {
        if(selected==null){
            selected=new Commande();
        }
        return selected;
    }

    public void setSelected(Commande selected) {
        this.selected = selected;
    }

    public List<Commande> getItems() {
        items=ejbFacade.findAll();
        return items;
    }

    public void setItems(List<Commande> items) {
        this.items = items;
    }

    public CommandeFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CommandeFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }
    
    
    
    
    /**
     * Creates a new instance of CommandeControler
     */
    public CommandeControler() {
    }
    
}

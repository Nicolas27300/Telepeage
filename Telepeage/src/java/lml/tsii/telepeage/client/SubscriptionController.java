package lml.tsii.telepeage.client;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import lml.tsii.telepeage.metier.BadgeService;
import lml.tsii.telepeage.metier.CustomerService;
import lml.tsii.telepeage.metier.MetierFactory;
import lml.tsii.telepeage.metier.SubscriptionService;
import lml.tsii.telepeage.metier.entitys.Badge;
import lml.tsii.telepeage.metier.entitys.Customer;
import lml.tsii.telepeage.metier.entitys.Subscription;
import lml.tsii.telepeage.metier.entitys.Type;

@ManagedBean
@ViewScoped
public class SubscriptionController implements Serializable {
    
    private SubscriptionService subscriptionSrv;
    private CustomerService customerSrv;
    private BadgeService badgeSrv;
    private LoginController login;
    private Type classe;
    private String list;
    private Subscription subscription;
    private Badge badge;
    
    @PostConstruct
    public void init(){
        this.subscriptionSrv = MetierFactory.getSubscriptionService();
        this.customerSrv = MetierFactory.getCustomerService();
        this.badgeSrv = MetierFactory.getBadgeService();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        login = (LoginController) session.getAttribute("loginController");
    }

    public Type getClasse() {
        return classe;
    }

    public void setClasse(Type classe) {
        this.classe = classe;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public void add() {
        Subscription subscription = new Subscription();
        try {
            if (this.list.equals("0")){
                subscription.setType(this.classe.Classe1);
            } else if (this.list.equals("1")){
                subscription.setType(this.classe.Classe2);
            } else if (this.list.equals("2")){
                subscription.setType(this.classe.Classe3);
            } else if (this.list.equals("3")){
                subscription.setType(this.classe.Classe4);
            } else if (this.list.equals("4")){
                subscription.setType(this.classe.Classe5);
            }
            Badge badge = new Badge();
            badge.setNum("123456789");
            Badge badgeAdd = this.badgeSrv.add(badge);
            Subscription subscriptionAdd = this.subscriptionSrv.add(subscription);
            this.badgeSrv.update(badgeAdd);
            subscriptionAdd.setBadge(badgeAdd);
            this.subscriptionSrv.update(subscription);
            Customer customer = this.login.getCustomer();
            customer.setSubscription(subscriptionAdd);
            this.customerSrv.update(customer);
            ExternalContext content = FacesContext.getCurrentInstance().getExternalContext();
            content.redirect("/Telepeage/abonnement.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(SubscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean searchSubscription(){
        return this.login.getCustomer().getSubscription() != null;
    }
    
    public void dataSubscription(){
        try {
            this.subscription = this.login.getCustomer().getSubscription();
            this.badge = this.subscription.getBadge();
        } catch (Exception ex) {
            Logger.getLogger(SubscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove(){
        try {
            Customer customer = this.login.getCustomer();
            customer.setSubscription(null);
            this.customerSrv.update(customer);
            this.subscriptionSrv.remove(subscription);
            this.badgeSrv.remove(badge);
            ExternalContext content = FacesContext.getCurrentInstance().getExternalContext();
            content.redirect("/Telepeage/abonnement.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(SubscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

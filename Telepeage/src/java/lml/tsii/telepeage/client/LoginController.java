package lml.tsii.telepeage.client;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import lml.tsii.telepeage.metier.CustomerService;
import lml.tsii.telepeage.metier.MetierFactory;
import lml.tsii.telepeage.metier.entitys.Customer;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {
    
    private CustomerService customerSrv;
    private Customer customer;
    private String mail;
    private String password;
    private boolean logged = false;
    
    @PostConstruct
    public void init(){
        this.customerSrv = MetierFactory.getCustomerService();
    }

    public CustomerService getCustomerSrv() {
        return customerSrv;
    }

    public void setCustomerSrv(CustomerService customerSrv) {
        this.customerSrv = customerSrv;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
    
    public void connexion(){
        try {
            List<Customer> customers = this.customerSrv.getByEmail(this.mail);
            if ((customers != null) && (customers.get(0).getPassword().equals(this.encodeMd5(this.password)))){
                this.customer = customers.get(0);
                this.logged = true;
                ExternalContext content = FacesContext.getCurrentInstance().getExternalContext();
                content.redirect("/Telepeage");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur", "Identifiant incorrect"));
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deconnexion() throws IOException{
        this.logged = false;
        this.customer = null;
        ExternalContext content = FacesContext.getCurrentInstance().getExternalContext();
        content.redirect("/Telepeage");
    }
    
    public String encodeMd5(String mdp) {
        byte[] uniqueKey = mdp.getBytes();
        byte[] hash = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        StringBuilder hashString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length() - 1));
            } else {
                hashString.append(hex.substring(hex.length() - 2));
            }
        }
        return hashString.toString();
    }
    
}

package lml.tsii.telepeage.client;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lml.tsii.telepeage.metier.CustomerService;
import lml.tsii.telepeage.metier.MetierFactory;
import lml.tsii.telepeage.metier.entitys.Customer;

@ManagedBean
@ViewScoped
public class CustomerController implements Serializable {

    private CustomerService customerSrv;
    private long id;
    private String name;
    private String firstname;
    private String mail;
    private String password;
    private String password_confirm;
    private String adress;
    private String cp;
    private String city;
    private boolean adminstrator;

    @PostConstruct
    public void init() {
        this.customerSrv = MetierFactory.getCustomerService();
    }

    public CustomerService getCustomerSrv() {
        return customerSrv;
    }

    public void setCustomerSrv(CustomerService customerSrv) {
        this.customerSrv = customerSrv;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public boolean isAdminstrator() {
        return adminstrator;
    }

    public void setAdminstrator(boolean adminstrator) {
        this.adminstrator = adminstrator;
    }

    public void add() throws Exception {
        List<Customer> customers = this.customerSrv.getByEmail(this.mail);
        if (customers == null) {
            Customer customer = new Customer();
            customer.setFirstName(this.firstname);
            customer.setName(this.name);
            customer.setMail(this.mail);
            customer.setPassword(this.encodeMd5(this.password));
            customer.setCity(this.city);
            customer.setCp(this.cp);
            customer.setAdress(this.adress);
            if (this.password.equals(this.password_confirm)) {
                this.customerSrv.add(customer);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Youpi", "Inscription effectué"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur", "Mot de passe non identique"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur", "Email déjà utilisé"));
        }
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

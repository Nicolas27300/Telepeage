package lml.tsii.telepage.client;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import lml.tsii.telepage.metier.CustomerService;
import lml.tsii.telepage.metier.MetierFactory;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {
    
    private CustomerService customerSrv;
    private String name;
    private String firstName;
    private String password;
    private String mail;
    private String key;
    private Date validated_date;
    private boolean administrator;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getValidated_date() {
        return validated_date;
    }

    public void setValidated_date(Date validated_date) {
        this.validated_date = validated_date;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
    
    public void connexion(){
        
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

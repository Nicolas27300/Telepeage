package lml.tsii.telepage.client;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lml.tsii.telepage.metier.CustomerService;
import lml.tsii.telepage.metier.MetierFactory;
import lml.tsii.telepage.metier.entity.Customer;

@ManagedBean
@ViewScoped
public class CustomerController implements Serializable {
    
    private CustomerService customerSrv;
    private long id;
    private String name;
    private String firstname;
    private String mail;
    private String password;
    private String key;
    private Date validated_date;
    private boolean adminstrator;
    
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

    public boolean isAdminstrator() {
        return adminstrator;
    }

    public void setAdminstrator(boolean adminstrator) {
        this.adminstrator = adminstrator;
    }
    
    public void add(){
        Customer customer = new Customer();
        customer.setFirstName(this.firstname);
        customer.setName(this.name);
        customer.setMail(this.mail);
        customer.setPassword(this.encodeMd5(this.password));
        customer.setKay("hdgfhdfhdg");
        try {
            this.customerSrv.add(customer);
        } catch (Exception ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
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

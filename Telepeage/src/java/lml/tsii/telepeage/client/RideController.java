package lml.tsii.telepeage.client;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import lml.tsii.telepeage.metier.MetierFactory;
import lml.tsii.telepeage.metier.RideService;
import lml.tsii.telepeage.metier.entitys.Ride;

@ManagedBean
@ViewScoped
public class RideController implements Serializable {
    
    private RideService rideSrv;
    List<Ride> rides;
    LoginController login;
    
    @PostConstruct
    public void init(){
        this.rideSrv = MetierFactory.getRideService();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        login = (LoginController) session.getAttribute("loginController");
        try {
            this.rides = this.rideSrv.getByCustomer(this.login.getCustomer());
        } catch (Exception ex) {
            Logger.getLogger(RideController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }
 
}

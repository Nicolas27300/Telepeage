package lml.tsii.telepeage.client;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import lml.tsii.telepeage.metier.BillService;
import lml.tsii.telepeage.metier.MetierFactory;
import lml.tsii.telepeage.metier.RideService;
import lml.tsii.telepeage.metier.entitys.Bill;
import lml.tsii.telepeage.metier.entitys.Ride;

@ManagedBean
@ViewScoped
public class BillController implements Serializable {

    public Date date;
    private float total = 0;
    private String name;
    private List<Ride> rides;
    private List<String> bills;
    private String bill;
    private LoginController login;

    @PostConstruct
    public void init() {
        Date date = new Date();
        String string = date.toString();
        String data[] = string.split(" ");
        StringBuilder dateString = new StringBuilder();
        dateString.append(data[1] + " " + data[5]);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        this.login = (LoginController) session.getAttribute("loginController");
        this.date = new Date();
        RideService rideSrv = MetierFactory.getRideService();
        BillService billSrv = MetierFactory.getBillService();
        try {
            List<Bill> bills = billSrv.getByCustomer(this.login.getCustomer());
            this.bills = new ArrayList();
            for (int i = 0; i < bills.size(); i++) {
                this.bills.add(bills.get(i).getName());
            }
            bills = billSrv.getByCustomerName(this.login.getCustomer(), dateString.toString());
            if (!bills.isEmpty()) {
                this.name = bills.get(0).getName();
                this.rides = bills.get(0).getRides();
                List<Ride> rides = new ArrayList();
                for (int i = this.rides.size() - 1; i >= 0; i--) {
                    rides.add(this.rides.get(i));
                }
                this.rides = rides;
                this.total = bills.get(0).getPrice();
            }
        } catch (Exception ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public LoginController getLogin() {
        return login;
    }

    public void setLogin(LoginController login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public List<String> getBills() {
        return bills;
    }

    public void setBills(List<String> bills) {
        this.bills = bills;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public void search() {
        try {
            List<Bill> bills = MetierFactory.getBillService().getByCustomerName(this.login.getCustomer(), this.bill);
            this.name = bills.get(0).getName();
            this.rides = bills.get(0).getRides();
            List<Ride> rides = new ArrayList();
            for (int i = this.rides.size() - 1; i >= 0; i--) {
                rides.add(this.rides.get(i));
            }
            this.rides = rides;
            this.total = bills.get(0).getPrice();
        } catch (Exception ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pdf() throws DocumentException, FileNotFoundException, BadElementException, IOException {
        Document document = new Document();
        String chemin = "/home/jupiter/Projet Telepeage/GitTelepeage/Telepeage/web/test.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(chemin));
        document.open();
        Image image = Image.getInstance("/home/jupiter/Projet Telepeage/GitTelepeage/Telepeage/web/img/SAPN.png");
        document.add(image);
        document.add(new Paragraph("Société des autoroutes Paris-Normandie\n"
                + "30 BD GALLIENI\n"
                + "92130 ISSY LES MOULINEAUX"));
        document.add(new Paragraph("\nFacture : " + this.name + "\n" + this.login.getCustomer().getFirstName() + " " + this.login.getCustomer().getName() + "\n"
                + this.login.getCustomer().getAdress() + " " + this.login.getCustomer().getCp() + " " + this.login.getCustomer().getCity() + "\n" + this.login.getCustomer().getMail()));
        PdfPTable table = new PdfPTable(4);
        table.addCell("Date");
        table.addCell("Station départ");
        table.addCell("Station arrivée");
        table.addCell("Prix");
        for (int i = 0; i < this.rides.size(); i++) {
            table.addCell(this.rides.get(i).getDate_ride().toString());
            table.addCell(this.rides.get(i).getStart().getName());
            if (this.rides.get(i).getStart().isTicket()) {
                table.addCell(this.rides.get(i).getEnd().getName());
            } else {
                table.addCell("");
            }
            table.addCell(Float.toString(this.rides.get(i).getPrice()));
        }
        document.add(table);
        document.add(new Paragraph("Abonnement télépéage 22€\n Prix à payer : " + this.total + "€"));
        document.close();
    }
}

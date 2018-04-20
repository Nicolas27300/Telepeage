package lml.tsii.telepeage.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lml.tsii.telepeage.metier.HighwayService;
import lml.tsii.telepeage.metier.MetierFactory;
import lml.tsii.telepeage.metier.PriceService;
import lml.tsii.telepeage.metier.StationService;
import lml.tsii.telepeage.metier.entitys.Highway;
import lml.tsii.telepeage.metier.entitys.Price;
import lml.tsii.telepeage.metier.entitys.Station;
import lml.tsii.telepeage.metier.entitys.Type;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class CsvUploadController implements Serializable {

    private UploadedFile file;
    private StationService stationSrv;
    private HighwayService highwaySrv;
    private PriceService priceSrv;

    @PostConstruct
    private void init() {
        this.stationSrv = MetierFactory.getStationService();
        this.highwaySrv = MetierFactory.getHighwayService();
        this.priceSrv = MetierFactory.getPriceService();
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() throws IOException, Exception {
        InputStream inputStream = this.file.getInputstream();
        BufferedReader stream = new BufferedReader(new InputStreamReader(inputStream));
        String buffer;
        while ((buffer = stream.readLine()) != null) {
            String[] data = buffer.split(",");
            Station station = new Station();
            station.setName(data[0]);
            station.setPk(Float.parseFloat(data[1]));
            station.setLongitude(Double.parseDouble(data[3]));
            station.setLatitude(Double.parseDouble(data[2]));
            if (!highwaySrv.getByName(data[4]).isEmpty()){
               station.setHighway(highwaySrv.getByName(data[4]).get(0)); 
            } else {
                Highway highway = new Highway();
                highway.setName(data[4]);
                highway = highwaySrv.add(highway);
                station.setHighway(highway);
            }
            station.setTicket(Boolean.parseBoolean(data[5]));
            station = this.stationSrv.add(station);
            Price price = new Price();
            if ((!data[6].equals("null")) || (!data[7].equals("null"))){
                Map<Type, Float> priceStartCity = new HashMap<Type, Float>();
                Map<Type, Float> priceEndCity = new HashMap<Type, Float>();
                priceStartCity.put(Type.Classe1, Float.parseFloat(data[6]));
                priceStartCity.put(Type.Classe2, Float.parseFloat(data[8]));
                priceStartCity.put(Type.Classe3, Float.parseFloat(data[10]));
                priceStartCity.put(Type.Classe4, Float.parseFloat(data[12]));
                priceStartCity.put(Type.Classe5, Float.parseFloat(data[14]));
                if (!data[7].equals("null")){
                   priceEndCity.put(Type.Classe1, Float.parseFloat(data[7]));
                   priceEndCity.put(Type.Classe2, Float.parseFloat(data[9])); 
                   priceEndCity.put(Type.Classe3, Float.parseFloat(data[11])); 
                   priceEndCity.put(Type.Classe4, Float.parseFloat(data[13])); 
                   priceEndCity.put(Type.Classe5, Float.parseFloat(data[15])); 
                } else {
                    priceEndCity.put(Type.Classe1, (float) 0.0); 
                }
                price.setStartPrice(priceStartCity);
                price.setEndPrice(priceEndCity);
                price = this.priceSrv.add(price);
                station.setPrice(price);
                this.stationSrv.update(station);
            }
        }
    }
}

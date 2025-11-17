package Service;

import model.Manufacture;
import model.Souvenir;

import java.util.List;

public interface SouvenirsService {
    List<Souvenir> getSouvenirs();
    List<Souvenir> getSouvenirsByManufacture(String manufacture);
    void addSouvenir(Souvenir souvenir);
    List<Souvenir> getSouvenirByCountry(String country);
    List<Manufacture> manufacturedFromSouvenirsPrice(int price);
    List<Souvenir> getManufactureFromYearsProduct(String name, int year );
    void deleteManufacture(Manufacture m);
}

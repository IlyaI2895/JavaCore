package repository;

import model.Manufacture;
import model.Souvenir;

import java.util.List;

public interface SouvenirRepository {
    List<Souvenir> getSouvenirs();
    void getSouvenirsByManufacture(String manufacture);
    void addSouvenir(Souvenir souvenir);
    void getSouvenirByCountry(String country);
    void manufacturedFromSouvenirsPrice(int price);
    void getManufactureFromYearsProduct(String name, int year );
    void deleteManufacture(Manufacture m);





}

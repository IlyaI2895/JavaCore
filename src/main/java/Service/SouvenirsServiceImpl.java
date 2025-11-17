package Service;

import model.Manufacture;
import model.Souvenir;
import repository.impl.SouvenirRepositoryJSON;

import java.util.List;

public class SouvenirsServiceImpl implements SouvenirsService {
    private final SouvenirRepositoryJSON repository;

    public SouvenirsServiceImpl() {
        repository = new SouvenirRepositoryJSON();
    }

    @Override
    public List<Souvenir> getSouvenirs() {
        return repository.getSouvenirs();
    }

    @Override
    public List<Souvenir> getSouvenirsByManufacture(String manufacture) {
      return repository.getSouvenirsByManufacture(manufacture);
    }

    @Override
    public void addSouvenir(Souvenir souvenir) {
        repository.addSouvenir(souvenir);
    }

    @Override
    public List<Souvenir> getSouvenirByCountry(String country) {
        return repository.getSouvenirByCountry(country);
    }

    @Override
    public List<Manufacture> manufacturedFromSouvenirsPrice(int price) {
       return repository.manufacturedFromSouvenirsPrice(price);
    }

    @Override
    public List<Souvenir> getManufactureFromYearsProduct(String name, int year) {
       return repository.getManufactureFromYearsProduct(name, year);
    }

    @Override
    public void deleteManufacture(Manufacture m) {
        repository.deleteManufacture(m);
    }
}

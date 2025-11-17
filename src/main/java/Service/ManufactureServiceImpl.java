package Service;

import model.Manufacture;
import repository.impl.ManufactureRepositoryImpl;

import java.util.List;

public class ManufactureServiceImpl implements ManufactureService {
    private final ManufactureRepositoryImpl repository;

    public ManufactureServiceImpl() {
        repository = new ManufactureRepositoryImpl();
    }

    @Override
    public List<Manufacture> getManufactures() {
        return repository.getManufactures();
    }

    @Override
    public void addManufacture(Manufacture m) {
        repository.addManufacture(m);
    }
}

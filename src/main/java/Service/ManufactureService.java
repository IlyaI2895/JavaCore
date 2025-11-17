package Service;

import model.Manufacture;

import java.util.List;

public interface ManufactureService {
    List<Manufacture> getManufactures();
    void addManufacture(Manufacture m);
}

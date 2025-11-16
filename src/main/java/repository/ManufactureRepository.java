package repository;
import model.Manufacture;
import java.util.List;

public interface ManufactureRepository {
    List<Manufacture> getManufactures();
    void addManufacture(Manufacture m);

}

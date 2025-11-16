package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import model.Manufacture;
import model.Souvenir;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class SouvenirRepositoryJSON implements SouvenirRepository {
    private final String DATA = "src\\main\\resources\\souvenirs.json";
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void deleteManufacture(Manufacture m) {
        List<Souvenir> collect = getSouvenirs().stream()
                .filter(souvenir -> m.getFirstName().equalsIgnoreCase(souvenir.getManufacturer().getSurname()))
                .filter(souvenir -> m.getSurname().equalsIgnoreCase(souvenir.getManufacturer().getFirstName()))
                .collect(Collectors.toList());
        rewriteData(collect);
    }

    private void updateData() {
        rewriteData(initRepository());
    }

    private static List<Souvenir> initRepository() {
        List<Souvenir> souvenir = TestDataGenerator.generateRandomSouvenir(50);
        List<Souvenir> localLibrary = new ArrayList<>(souvenir);
        return localLibrary;
    }


    @Override
    public List<Souvenir> getSouvenirs() {
        try {
            return newMapper().readValue(new File(DATA),
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
            updateData();
            throw new RuntimeException(e);

        }
    }

    @Override
    public void getSouvenirsByManufacture(String manufacture) {
        getSouvenirs().stream()
                .filter(souvenir -> souvenir.getManufacturer().getFirstName().equalsIgnoreCase(manufacture) ||
                        souvenir.getManufacturer().getSurname().equalsIgnoreCase(manufacture))
                .forEach(System.out::println);
    }

    @Override
    public void addSouvenir(Souvenir souvenir) {
        List<Souvenir> allsouvenirs = getSouvenirs();
        int nextId;
        if (allsouvenirs.isEmpty()) {
            nextId = 1;
        } else {
            nextId = allsouvenirs.get(allsouvenirs.size() - 1).getId() + 1;
        }
        souvenir.setId(nextId);
        allsouvenirs.add(souvenir);
        rewriteData(allsouvenirs);
    }


    @Override
    public void getSouvenirByCountry(String country) {
        getSouvenirs().stream().filter(souvenir -> souvenir.getManufacturer().getCountry().equalsIgnoreCase(country))
                .forEach(System.out::println);

    }

    @Override
    public void manufacturedFromSouvenirsPrice(int price) {
        getSouvenirs().stream().filter(souvenir -> souvenir.getPrice() < price)
                .map(Souvenir::getManufacturer)
                .forEach(System.out::println);

    }

    @Override
    public void getManufactureFromYearsProduct(String name, int year) {
        try {
            getSouvenirs().stream().filter(souvenir -> name.equalsIgnoreCase(souvenir.getName())
                            || souvenir.getDate() == year)
                    .forEach(System.out::println);
        } catch (RuntimeException e) {
            System.out.println("Такого сувенира не существует");
        }


    }

    private ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(df);
        mapper.setLocale(Locale.ENGLISH);
        mapper.registerModule(new JSR310Module());
        return mapper;
    }

    private void rewriteData(List<Souvenir> souvenir) {
        try {
            newMapper().writeValue(new File(DATA), souvenir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import model.Manufacture;
import model.Souvenir;
import repository.SouvenirRepository;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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

    private static List<Souvenir> initRepository() {
        List<Souvenir> souvenirs = TestDataGenerator.generateRandomSouvenir(50);
        List<Souvenir> localLibrary = new ArrayList<>(); ;
        IntStream.range(0, souvenirs.size())
                .forEach(index -> {
                    Souvenir souvenir = souvenirs.get(index);
                    souvenir.setId(index + 1);
                    souvenir.getManufacturer().setManufactureID(index + 1);
                    localLibrary.add(souvenir);
                });
        return localLibrary;
    }


    @Override
    public List<Souvenir> getSouvenirs() {
        try {
            return newMapper().readValue(new File(DATA),
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
            rewriteData(initRepository());

            throw new RuntimeException(e);

        }
    }

    @Override
    public List<Souvenir> getSouvenirsByManufacture(String manufacture) {
        List<Souvenir> collect = getSouvenirs().stream()
                .filter(souvenir -> manufacture.equalsIgnoreCase(souvenir.getManufacturer().getSurname())
                        || manufacture.equalsIgnoreCase(souvenir.getManufacturer().getFirstName()))
                .collect(Collectors.toList());
        return collect;
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
    public List<Souvenir> getSouvenirByCountry(String country) {
        List<Souvenir> collect = getSouvenirs().stream()
                .filter(souvenir -> souvenir.getManufacturer().getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<Manufacture> manufacturedFromSouvenirsPrice(int price) {
        List<Manufacture> collect = getSouvenirs().stream().filter(souvenir -> souvenir.getPrice() < price)
                .map(Souvenir::getManufacturer)
                .collect(Collectors.toList());
        return collect;


    }

    @Override
    public List<Souvenir> getManufactureFromYearsProduct(String name, int year) {
            List<Souvenir> collect = getSouvenirs().stream().filter(souvenir -> name.equalsIgnoreCase(souvenir.getName())
                            || souvenir.getDate() == year)
                    .collect(Collectors.toList());
            return collect;
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

package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import model.Manufacture;
import model.Souvenir;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ManufactureRepositoryImpl implements ManufactureRepository {
    private final String DATA = "src\\main\\resources\\manufacture.json";
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public List<Manufacture> getManufactures() {
        List<Manufacture> manufactures = new ArrayList<>();
        try {
            manufactures = newMapper().readValue(new File(DATA),
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
            rewriteData(Collections.emptyList());
        }
        return manufactures;
    }

    @Override
    public void addManufacture(Manufacture manufacture) {
        List<Manufacture> allManufactures = getManufactures();
        int nextId;
        if (allManufactures.isEmpty()) {
            nextId = 1;
        } else {
            nextId = allManufactures.get(allManufactures.size() - 1).getManufactureID() + 1;
        }
        manufacture.setManufactureID(nextId);
        allManufactures.add(manufacture);
        rewriteData(allManufactures);
    }

    private ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(df);
        mapper.setLocale(Locale.ENGLISH);
        mapper.registerModule(new JSR310Module());
        return mapper;
    }

    private void rewriteData(List<Manufacture> manufacture) {
        try {
            newMapper().writeValue(new File(DATA), manufacture);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

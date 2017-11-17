package ru.kpecmuk.converter.timing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.database.SaveTimeToDB;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kpecmuk
 * @since 17.11.2017
 */

public class AllRoutesTimeList {
    private static final Logger log = LoggerFactory.getLogger(AllRoutesTimeList.class);

    private final List<RouteTimeList> allRoutesTimeList = new ArrayList<>();

    public List<RouteTimeList> get() {
        return this.allRoutesTimeList;
    }

    public void saveToDB() {
        SaveTimeToDB saveTimeToDB = new SaveTimeToDB("jdbc:postgresql://localhost:5432/transport", "user", "user");
        saveTimeToDB.save(this.allRoutesTimeList);
    }
}

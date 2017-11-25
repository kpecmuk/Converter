package ru.kpecmuk.converter.data_maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.actions.Process;
import ru.kpecmuk.converter.loader.LoadDataToHashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Загрузка данных из файлов и сохранение их в HashMap
 *
 * @author kpecmuk
 * @since 25.11.2017
 */

public class DataMap {
    private static final Logger log = LoggerFactory.getLogger(DataMap.class);

    private final Map<String, String> map = new HashMap<>();

    public DataMap(String fileName) {
        Process process = new Process();
        process.setProcess(new LoadDataToHashMap(fileName, this.map));
        process.execute();
    }

    public Map<String, String> get() {
        return this.map;
    }
}

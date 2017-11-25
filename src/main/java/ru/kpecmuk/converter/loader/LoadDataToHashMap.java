package ru.kpecmuk.converter.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.actions.Action;

import java.io.IOException;
import java.util.Map;

/**
 * @author kpecmuk
 * @since 24.11.2017
 */

public class LoadDataToHashMap extends LoadFile implements Action {
    private static final Logger log = LoggerFactory.getLogger(LoadDataToHashMap.class);

    private Map<String, String> map;

    public LoadDataToHashMap(String fileName, Map<String, String> map) {
        super(fileName);
        this.map = map;
    }

    /**
     * Открываем файл, обрабатываем данные, закрываем файл.
     */
    @Override
    public final void execute() {

        openFile();
        String title, id;

        try {
            while ((title = fin.readLine()) != null) {
                id = fin.readLine();
                map.put(id, title);
            }
        } catch (IOException e) {
            log.error(String.valueOf(e));
            System.exit(1);
        }
        closeFile();
    }
}

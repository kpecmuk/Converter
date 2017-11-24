package ru.kpecmuk.converter.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpecmuk.converter.actions.Action;
import ru.kpecmuk.converter.stops.Stop;

import java.io.IOException;
import java.util.Map;

/**
 * @author kpecmuk
 * @since 24.11.2017
 */

public class LoadStopsToStopMap extends LoadFile implements Action {
    private static final Logger log = LoggerFactory.getLogger(LoadStopsToStopMap.class);

    private Map<String, Stop> stopMap;

    public LoadStopsToStopMap(String fileName, Map<String, Stop> stopMap) {
        super(fileName);
        this.stopMap = stopMap;
    }

    /**
     * Обработка файла с остановками.
     * Открываем файл, обрабатываем данные, закрываем файл.
     */
    @Override
    public final void execute() {

        openFile();
        String title, id;

        try {
            while ((title = fin.readLine()) != null) {
                id = fin.readLine();
                stopMap.put(id, new Stop(id, title));
            }
        } catch (IOException e) {
            log.error(String.valueOf(e));
            System.exit(1);
        }
        closeFile();
    }
}

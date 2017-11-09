package ru.kpecmuk.converter.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author kpecmuk
 * @since 09.11.2017
 */

class Load {
    private static final Logger log = LoggerFactory.getLogger(Load.class);
    BufferedReader fin;
    private String fileName;

    Load(String fileName) {
        this.fileName = fileName;
    }

    void openFile() throws FileNotFoundException {
        this.fin = new BufferedReader(new FileReader(new File(fileName)));
    }

    void closeFile() throws IOException {
        this.fin.close();
    }
}

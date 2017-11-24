package ru.kpecmuk.converter.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kpecmuk
 * @since 24.11.2017
 */

public class Process {
    private static final Logger log = LoggerFactory.getLogger(Process.class);

    private Processing processing;

    public void setProcessing(Processing processing) {
        this.processing = processing;
    }

    public void execute() {
        processing.loadAndProcess();
    }
}

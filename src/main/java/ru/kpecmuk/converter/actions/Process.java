package ru.kpecmuk.converter.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Шаблон Strategy
 *
 * @author kpecmuk
 * @since 24.11.2017
 */

public class Process {
    private static final Logger log = LoggerFactory.getLogger(Process.class);

    private Action action;

    public void setProcess(Action processing) {
        this.action = processing;
    }

    public void execute() {
        action.execute();
    }
}

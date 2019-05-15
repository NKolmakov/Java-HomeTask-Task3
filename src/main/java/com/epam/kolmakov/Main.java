package com.epam.kolmakov;

import com.epam.kolmakov.runner.Runner;
import org.apache.log4j.Logger;

public class Main {
    public static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.run();
    }

}

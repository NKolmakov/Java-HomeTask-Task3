package com.epam.kolmakov.runner;

import com.epam.kolmakov.configs.ApplicationConfig;
import com.epam.kolmakov.managers.PortManager;
import com.epam.kolmakov.entities.Port;
import com.epam.kolmakov.entities.Ship;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Runner {
    public void run() {
        initialize();
    }

    private void initialize() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Port port = context.getBean("port", Port.class);
        PortManager portManager = context.getBean("portManager", PortManager.class);
        List<Ship> ships = context.getBean("filledShips", List.class);

        for (Ship ship : ships) {
            portManager.startWork(ship);
        }

        /*
         *Executors have to work until all ships unload their containers and leave port.
         */
        while (port.getWaitingUnloadQueue().size() > 0 || port.getWaitingDockQueue().size() > 0 || port.getAvailableDocks().size() != 10) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        portManager.getManager().shutdown();
        port.getTime2Wait().shutdown();
    }
}
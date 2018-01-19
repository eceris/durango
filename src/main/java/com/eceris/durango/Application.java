package com.eceris.durango;

import com.eceris.durango.config.Env;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.env.AbstractEnvironment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private static final String PID_FILE = "/var/pid/durango.pid";

    private static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        String profileName = Env.REGION().name();

        if (Region.PROD.name().equalsIgnoreCase(profileName) && Files.exists(Paths.get(PID_FILE))) {
            logger.error("Stormwind already running.");
            System.out.println("Stormwind already running.");
            return;
        }

        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, profileName.toLowerCase());

        SpringApplication application = new SpringApplication(Region.getBootstrapClassOf(profileName));
        application.addListeners(new ApplicationPidFileWriter(PID_FILE));

        ctx = application.run();

        ctx.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                try {
                    Files.deleteIfExists(Paths.get(PID_FILE));
                } catch (IOException e) {
                    logger.error("Pid file delete failed.", e);
                }
            }
        });
    }

    public static ApplicationContext getContext() {
        return ctx;
    }
}

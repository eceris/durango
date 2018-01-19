package com.eceris.durango.config;

import com.eceris.durango.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.AbstractEnvironment;

import java.util.Arrays;
import java.util.Properties;

public final class Env {
    private Env() {
        throw new AssertionError("No kr.co.anajo.stormwind.config.Env instances for you!");
    }

    private static final Logger logger = LoggerFactory.getLogger(Env.class);

    private static Region REGION = Region.DEV;

    private static int SERVER_PORT = 80;

    private static String OPENID_CLIENT_ID;
    private static String OPENID_CLIENT_KEY;
    private static String OPENID_REDIRECT_HOST;

    private static String DRIVE_ROOT_PATH;

    static {
        initSystemProperties();
        initApplicationProperties();
    }

    private static void initSystemProperties() {
        String[] profiles = System.getProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev").split(",");
        Arrays.stream(profiles).forEach((profile) -> {
            decideRegion(profile.trim());
        });
    }

    private static void initApplicationProperties() {
        Properties props = new Properties();
        try {
            props.load(Env.class.getResourceAsStream("/application-" + REGION.name().toLowerCase() + ".properties"));
        } catch (Exception e) {
            logger.error("config properties file load failed. profile: {}", REGION.name().toLowerCase(), e);
        }

        SERVER_PORT = Integer.parseInt(props.getProperty("server.port"));

        OPENID_CLIENT_ID = props.getProperty("openid.clientId");
        OPENID_CLIENT_KEY = props.getProperty("openid.clientKey");
        OPENID_REDIRECT_HOST = props.getProperty("openid.redirectHost");

        DRIVE_ROOT_PATH = props.getProperty("drive.rootPath");
    }

    private static void decideRegion(String profile) {
        Arrays.stream(Region.values()).forEach((region) -> {
            if (region.name().equalsIgnoreCase(profile.trim())) {
                REGION = region;
            }
        });
    }

    public static Region REGION() {
        return REGION;
    }

    public static int SERVER_PORT() {
        return SERVER_PORT;
    }

    public static String OPENID_CLIENT_ID() {
        return OPENID_CLIENT_ID;
    }

    public static String OPENID_CLIENT_KEY() {
        return OPENID_CLIENT_KEY;
    }

    public static String OPENID_REDIRECT_HOST() {
        return OPENID_REDIRECT_HOST;
    }

    public static String DRIVE_ROOT_PATH() {
        return DRIVE_ROOT_PATH;
    }

}
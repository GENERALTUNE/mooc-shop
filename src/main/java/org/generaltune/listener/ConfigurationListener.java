package org.generaltune.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import javax.security.auth.login.Configuration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 * Created by zhumin on 2017/3/15.
 */
public class ConfigurationListener implements ServletContextListener {

    private final  String JAVA_VERSION = "java.version";
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("\r\n#####################################################");
        logger.info("\r\n#      project configuration initialization");
        try {
            String version = System.getProperty(JAVA_VERSION);
            logger.info("\r\n#  configuration OK: Java Version is " + version + "");
        } catch (Exception e) {
            logger.info("\r\n#      Configuration Failed ...");
            e.printStackTrace();
            System.exit(-1);
        }
        logger.info("\r\n#####################################################");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

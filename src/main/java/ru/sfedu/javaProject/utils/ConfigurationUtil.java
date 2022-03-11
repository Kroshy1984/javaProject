package ru.sfedu.javaProject.utils;

import java.io.*;
import java.util.Properties;

/**
 * Configuration utility. Allows to get configuration properties from the
 * default configuration file
 *
 * @author Boris Jmailov
 */
public class ConfigurationUtil {
    private static final String DEFAULT_CONFIG_PATH = System.getProperty("config",
        ConfigurationUtil.class.getClassLoader().getResource("environment.properties").getPath());
    private static final Properties configuration = new Properties();

    /**
     * Hides default constructor
     *
     *
     */
    private ConfigurationUtil() {
    }


    private static Properties getConfiguration() throws IOException {
        if(configuration.isEmpty()){
            loadConfiguration();
        }
        return configuration;
    }

    /**
     * Loads configuration from <code>DEFAULT_CONFIG_PATH</code>
     * @throws IOException In case of the configuration file read failure
     */
    private static void loadConfiguration() throws IOException{
        File nf = new File(DEFAULT_CONFIG_PATH);
        InputStream in = new FileInputStream(nf);
        try {
            configuration.load(in);
        } catch (IOException ex) {
            throw new IOException(ex);
        } finally{
            in.close();
        }
    }
    /**
     * Gets configuration entry value
     * @param key Entry key
     * @return Entry value by key
     * @throws IOException In case of the configuration file read failure
     */
    public static String getConfigurationEntry(String key) throws IOException{
        return getConfiguration().getProperty(key);
    }

}

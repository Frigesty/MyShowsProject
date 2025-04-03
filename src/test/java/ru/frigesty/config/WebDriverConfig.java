package ru.frigesty.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/${env}.properties"
})

public interface WebDriverConfig extends Config {

    @Key("browser")
    @DefaultValue("CHROME")
    String browser();

    @Key("browser_size")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("browser_version")
    @DefaultValue("134.0")
    String browserVersion();

    @Key("baseUrl")
    @DefaultValue("https://myshows.me")
    String baseUrl();

    @Key("baseUri")
    @DefaultValue("https://myshows.me")
    String baseUri();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remoteUrl")
    String remoteUrl();

    @Key("loadStrategy")
    @DefaultValue("eager")
    String loadStrategy();
}
package ru.netology.localization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

class LocalizationServiceImplTest {

    public String ipRus1 = "172.0.32.11";
    public String ipRus2 = "172.0.32.114";
    public String ipUsa1 = "96.44.183.149";
    public String ipUsa2 = "96.44.183.3";
    public String ipLocal = "127.0.0.1";

    public LocalizationServiceImpl localizationService;

    @BeforeEach
    void setUp() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    void test_locale_usa() {
        String localeResult = localizationService.locale(Country.USA);
        Assertions.assertEquals("Welcome", localeResult);
    }

    @Test
    void test_locale_rus() {
        String localeResult = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals("Добро пожаловать", localeResult);
    }
}


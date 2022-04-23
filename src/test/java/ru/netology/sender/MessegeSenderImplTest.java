package ru.netology.sender;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.localization.LocalizationServiceImpl;

class MessegeSenderImplTest {

    public static final String IP_ADDRESS_HEADER = "x-real-ip";
    public static final String rusMessage = "Добро пожаловать";
    public static final String engMessage = "Welcome";

    public String ipRus = "172.0.32.11";
    public String ipUsa = "96.44.183.149";
    public GeoServiceImpl geoService;
    public LocalizationServiceImpl localizationService;
    public MessageSenderImpl messageSender;

    @BeforeEach
    void setUp() {
        geoService = Mockito.mock(GeoServiceImpl.class);
        localizationService = Mockito.mock(LocalizationServiceImpl.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
        when(geoService.byIp(ipRus)).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        when(geoService.byIp(ipUsa)).thenReturn(new Location("New York", Country.USA, null, 0));

        when(localizationService.locale(Country.RUSSIA)).thenReturn(rusMessage);
        when(localizationService.locale(Country.USA)).thenReturn(engMessage);
    }

    @Test
    void test_message_rus() {
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, ipRus);
        String result = messageSender.send(headers);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(rusMessage, result);
    }

    @Test
    void test_message_eng() {
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, ipRus);
        String result = messageSender.send(headers);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(engMessage, result);
    }

}


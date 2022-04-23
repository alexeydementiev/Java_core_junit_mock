package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

class GeoServiceImplTest {

	public String ipRus1 = "172.0.32.11";
	public String ipRus2 = "172.0.32.114";
	public String ipUsa1 = "96.44.183.149";
	public String ipUsa2 = "96.44.183.3";
	public String ipLocal = "127.0.0.1";
	public GeoServiceImpl geoService;

	@BeforeEach
	void setUp() {
		geoService = new GeoServiceImpl();
	}

	@Test
	void test_geoservice_byIP() {
		Location locationMoscowLenina = geoService.byIp(ipRus1);
		Assertions.assertEquals(Country.RUSSIA, locationMoscowLenina.getCountry());
		Assertions.assertEquals("Lenina", locationMoscowLenina.getStreet());

		Location locationMoscow = geoService.byIp(ipRus2);
		Assertions.assertEquals(Country.RUSSIA, locationMoscow.getCountry());
		Assertions.assertNull(locationMoscow.getStreet());

		Location locationNY10Avenue = geoService.byIp(ipUsa1);
		Assertions.assertEquals(Country.USA, locationNY10Avenue.getCountry());
		Assertions.assertEquals("10th Avenue", locationNY10Avenue.getStreet());

		Location locationNY = geoService.byIp(ipUsa2);
		Assertions.assertEquals(Country.USA, locationNY.getCountry());
		Assertions.assertNull(locationNY.getStreet());

		Location locationLocalHost = geoService.byIp(ipLocal);
		Assertions.assertNull(locationLocalHost.getCountry());
	}

}


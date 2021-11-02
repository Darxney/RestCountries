package lv.andris.rest.countries.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.andris.rest.countries.web.classesForRestTests.CountryArrayJsonMapper;
import lv.andris.rest.countries.web.controllers.CountryController_classes.Message;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestTemplateCountryTest {


    //The app needs to be running for these tests to pass!
    private RestTemplate restTemplate;

    private static final String resourceUrl = "http://localhost:8080/";

    @Before
    public void beforeTest() {
        restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
    }

    @Test
    public void A_getCountryInfoForAppShouldPass() {
        ResponseEntity<Message> response = restTemplate.getForEntity(resourceUrl + "getCountryInfoForApp" , Message.class);

        assertThat(response.getBody().getResponse(), is("Country info has been saved/updated in local database and is ready for use"));
    }

    @Test
    public void B_whenSendListAllCountriesForRequestEntity_thenBodyCorrect() throws IOException {
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> response = template.getForEntity(resourceUrl + "listAllCountries", String.class);

        ObjectMapper mapper = new ObjectMapper();
        final JsonNode root = mapper.readTree(response.getBody());
        final JsonNode json = root.path("countries");
        assertThat(json.asText(), notNullValue());
    }

    @Test
    public void C_whenGetListTopCountriesByArea_thenShouldReturnRussia() {
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<CountryArrayJsonMapper> response = template.getForEntity(resourceUrl + "listTopTenByArea", CountryArrayJsonMapper.class);

        CountryArrayJsonMapper countries = response.getBody();

        assertThat(countries.getCountries().get(0).getCountryName().getOfficial(), is("Russian Federation"));
        assertThat(countries.getCountries().get(0).getArea(), is(new BigDecimal(17098242.00).setScale(2, RoundingMode.HALF_UP)));
        assertThat(countries.getCountries().size(), is(10));
    }

    @Test
    public void D_whenGetListTopCountriesByPopulation_thenShouldReturnRussia() {
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<CountryArrayJsonMapper> response = template.getForEntity(resourceUrl + "listTopTenByPopulation", CountryArrayJsonMapper.class);

        CountryArrayJsonMapper countries = response.getBody();

        assertThat(countries.getCountries().get(0).getCountryName().getOfficial(), is("Russian Federation"));
        assertThat(countries.getCountries().get(0).getPopulation(), is(144104080));
        assertThat(countries.getCountries().size(), is(10));
    }

    @Test
    public void E_whenGetListTopCountriesByPopulationDensity_thenShouldReturnMonaco() {
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<CountryArrayJsonMapper> response = template.getForEntity(resourceUrl + "listTopTenByPopulationDensity", CountryArrayJsonMapper.class);

        CountryArrayJsonMapper countries = response.getBody();

        assertThat(countries.getCountries().get(0).getCountryName().getOfficial(), is("Principality of Monaco"));
        assertThat(countries.getCountries().get(0).getPopulationDensity(), is(new BigDecimal(19427.72).setScale(2, RoundingMode.HALF_UP)));
        assertThat(countries.getCountries().size(), is(10));
    }

}

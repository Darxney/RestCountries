package lv.andris.rest.countries.web.controllers;

import lv.andris.rest.countries.core.database.JpaCountryRepository;
import lv.andris.rest.countries.core.domain.Country;
import lv.andris.rest.countries.core.requests.GetCountriesRequest;
import lv.andris.rest.countries.core.responses.GetCountriesResponse;
import lv.andris.rest.countries.core.services.GetCountriesService;
import lv.andris.rest.countries.web.controllers.CountryController_classes.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GetCountriesService service;

    @Autowired
    private JpaCountryRepository repository;

    private static String url = "https://restcountries.com/v3.1/subregion/europe?fields=name,capital,currencies,population,area";

    @GetMapping(path = "/getCountryInfoForApp", produces = MediaType.APPLICATION_JSON_VALUE)
    public Message getCountriesFromJsonAndSaveInDb() {

        List<Country> countries = null;
        Message jsonStatus = new Message();

        try {
            final ResponseEntity<Country[]> response = restTemplate.getForEntity(url, Country[].class);
            countries = Arrays.asList(response.getBody());
            repository.saveAll(countries);

            jsonStatus = new Message("Country info has been saved/updated in local database and is ready for use");

        } catch (Exception e) {
            jsonStatus = new Message("Country info has not been saved due to an error");
        }

       return jsonStatus;

    }

    @GetMapping("/listAllCountries")
    public GetCountriesResponse listAllCountries() {
        GetCountriesRequest request = new GetCountriesRequest();
        return service.allCountries(request);
    }

    @GetMapping("/listTopTenByPopulation")
    public GetCountriesResponse listTopTenByPopulation() {
        GetCountriesRequest request = new GetCountriesRequest();
        return service.topCountriesByPopulation(request, 10);
    }

    @GetMapping("/listTopTenByArea")
    public GetCountriesResponse listTopTenByArea() {
        GetCountriesRequest request = new GetCountriesRequest();
        return service.topCountriesByArea(request, 10);
    }

    @GetMapping("/listTopTenByPopulationDensity")
    public GetCountriesResponse listTopTenByPopulationDensity() {
        GetCountriesRequest request = new GetCountriesRequest();
        return service.topCountriesByPopulationDensity(request, 10);

    }


}

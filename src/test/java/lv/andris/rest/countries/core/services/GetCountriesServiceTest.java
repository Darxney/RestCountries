package lv.andris.rest.countries.core.services;

import lv.andris.rest.countries.core.database.JpaCountryRepository;
import lv.andris.rest.countries.core.domain.Country;
import lv.andris.rest.countries.core.domain.country_classes.Currencies;
import lv.andris.rest.countries.core.domain.country_classes.Name;
import lv.andris.rest.countries.core.requests.GetCountriesRequest;
import lv.andris.rest.countries.core.responses.GetCountriesResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetCountriesServiceTest {

    @Mock
    private JpaCountryRepository repository;

    @InjectMocks
    private GetCountriesService service;

    @Test
    public void shouldReturnAllCountriesFromDb(){
        GetCountriesRequest request = new GetCountriesRequest();

        List<String> testCapitalArray = new ArrayList<>();
        testCapitalArray.add("testCapital");

        Country countryOne = new Country(new Name(), new Currencies(), testCapitalArray, new BigDecimal(5000.2), 10, null);
        Country countryTwo = new Country(new Name(), new Currencies(), testCapitalArray, new BigDecimal(9000.2), 10, null);
        Country countryThree = new Country(new Name(), new Currencies(), testCapitalArray, new BigDecimal(1000.2), 10, null);

        Mockito.when(repository.findAll()).thenReturn(List.of(countryOne, countryTwo, countryThree));

        GetCountriesResponse response = service.allCountries(request);
        assertEquals(response.getCountries().size(), 3);

    }

    @Test
    public void shouldReturnTopCountriesByAreaFromDb(){
        GetCountriesRequest request = new GetCountriesRequest();

        List<String> testCapitalArray = new ArrayList<>();
        testCapitalArray.add("testCapital");

        List<Country> countries = new ArrayList<>();

         countries.add(new Country(new Name(), new Currencies(), testCapitalArray, new BigDecimal(5000.20), 200, new BigDecimal(2)));
         countries.add(new Country(new Name(), new Currencies(), testCapitalArray, new BigDecimal(4000.30), 100, new BigDecimal(1)));
         countries.add(new Country(new Name(), new Currencies(), testCapitalArray, new BigDecimal(3000.40), 300, new BigDecimal(3)));

        Mockito.when(repository.getTopCountriesByArea(PageRequest.of(0,3))).thenReturn(countries);

        GetCountriesResponse response = service.topCountriesByArea(request, 3);

        assertEquals(response.getCountries().size(), 3);

        assertEquals(response.getCountries().get(0).getArea(), new BigDecimal(5000.20).setScale(2, RoundingMode.HALF_UP));

    }

    @Test
    public void shouldReturnTopCountriesByPopulationFromDb(){
        GetCountriesRequest request = new GetCountriesRequest();

        List<String> testCapitalArray = new ArrayList<>();
        testCapitalArray.add("testCapital");

        List<Country> countries = new ArrayList<>();

        countries.add(new Country(new Name(), new Currencies(), testCapitalArray, new BigDecimal(5000.20), 300, new BigDecimal(2)));
        countries.add(new Country(new Name(), new Currencies(), testCapitalArray, new BigDecimal(4000.30), 100, new BigDecimal(1)));
        countries.add(new Country(new Name(), new Currencies(), testCapitalArray, new BigDecimal(3000.40), 200, new BigDecimal(3)));

        Mockito.when(repository.getTopCountriesByPopulation(PageRequest.of(0,3))).thenReturn(countries);

        GetCountriesResponse response = service.topCountriesByPopulation(request, 3);

        assertEquals(response.getCountries().size(), 3);

        assertEquals(response.getCountries().get(0).getPopulation(), new Integer(300));

    }

    @Test
    public void shouldReturnTopCountriesByPopulationDensityFromDb(){
        GetCountriesRequest request = new GetCountriesRequest();

        List<String> testCapitalArray = new ArrayList<>();
        testCapitalArray.add("testCapital");

        List<Country> countries = new ArrayList<>();

        countries.add(new Country(new Name(), new Currencies(), testCapitalArray, new BigDecimal(5000.20), 300, new BigDecimal(3)));
        countries.add(new Country(new Name(), new Currencies(), testCapitalArray, new BigDecimal(4000.30), 100, new BigDecimal(1)));
        countries.add(new Country(new Name(), new Currencies(), testCapitalArray, new BigDecimal(3000.40), 200, new BigDecimal(2)));

        Mockito.when(repository.getTopCountriesByPopulationDensity(PageRequest.of(0,3))).thenReturn(countries);

        GetCountriesResponse response = service.topCountriesByPopulationDensity(request, 3);

        assertEquals(response.getCountries().size(), 3);

        assertEquals(response.getCountries().get(0).getPopulationDensity(), new BigDecimal(3).setScale(2, RoundingMode.HALF_UP));

    }
}

package lv.andris.rest.countries.core.services;

import lv.andris.rest.countries.core.database.JpaCountryRepository;
import lv.andris.rest.countries.core.domain.Country;
import lv.andris.rest.countries.core.requests.GetCountriesRequest;
import lv.andris.rest.countries.core.responses.GetCountriesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetCountriesService {

    @Autowired
    private JpaCountryRepository repository;

    public GetCountriesResponse allCountries(GetCountriesRequest request) {

        List<Country> countries = repository.findAll();
        return new GetCountriesResponse(countries);

    }

    public GetCountriesResponse topCountriesByPopulation(GetCountriesRequest request, int top) {

        List<Country> countries = repository.getTopCountriesByPopulation(PageRequest.of(0, top));
        return new GetCountriesResponse(countries);

    }

    public GetCountriesResponse topCountriesByArea(GetCountriesRequest request, int top) {

        List<Country> countries = repository.getTopCountriesByArea(PageRequest.of(0, top));
        return new GetCountriesResponse(countries);

    }

    public GetCountriesResponse topCountriesByPopulationDensity(GetCountriesRequest request, int top) {

        List<Country> countries = repository.getTopCountriesByPopulationDensity(PageRequest.of(0, top));
        return new GetCountriesResponse(countries);

    }

}

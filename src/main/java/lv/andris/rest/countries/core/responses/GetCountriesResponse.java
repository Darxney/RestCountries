package lv.andris.rest.countries.core.responses;

import lv.andris.rest.countries.core.domain.Country;

import java.util.List;

public class GetCountriesResponse {

    private List<Country> countries;

    public GetCountriesResponse(List<Country> countries) {
        this.countries = countries;
    }

    public List<Country> getCountries() {
        return countries;
    }

}

package lv.andris.rest.countries.web.classesForRestTests;

import lv.andris.rest.countries.core.domain.Country;

import java.util.List;

public class CountryArrayJsonMapper {
    List<Country> countries;

    public CountryArrayJsonMapper() {

    }

    public CountryArrayJsonMapper(List<Country> countries) {
        this.countries = countries;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}

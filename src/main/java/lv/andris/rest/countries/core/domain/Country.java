package lv.andris.rest.countries.core.domain;

import com.fasterxml.jackson.annotation.*;
import lv.andris.rest.countries.core.domain.country_classes.Name;
import lv.andris.rest.countries.core.domain.country_classes.Currencies;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Entity
@Table(name = "countries")
@JsonPropertyOrder({
        "name",
        "currencies",
        "capital",
        "area",
        "population"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    @Id
    @Column(name = "country_name")
    @NotEmpty
    @Embedded
    @JsonProperty("name")
    private Name countryName;

    @Embedded
    @Column(name = "currencies")
    @NotEmpty
    @JsonProperty("currencies")
    private Currencies currencies;

    @ElementCollection(targetClass=String.class)
    @Column(name = "capital")
    @NotEmpty
    @JsonProperty("capital")
    public List<String> capital;

    @Column(name = "area")
    @NotEmpty
    @JsonProperty("area")
    private BigDecimal area;

    @Column(name = "population")
    @NotEmpty
    @JsonProperty("population")
    private Integer population;

    @Column(name = "population_density")
    @Formula("population / area")
    private BigDecimal populationDensity;


    public Country() {

    }

    public Country(@NotEmpty Name countryName, @NotEmpty Currencies currencies, @NotEmpty List<String> capital, @NotEmpty BigDecimal area, @NotEmpty Integer population, @NotEmpty BigDecimal populationDensity) {
        this.countryName = countryName;
        this.currencies = currencies;
        this.capital = capital;
        this.area = area;
        this.population = population;
        this.populationDensity = populationDensity;
    }

    public Name getCountryName() {
        return countryName;
    }

    public void setCountryName(Name countryName) {
        this.countryName = countryName;
    }

    public Currencies getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Currencies currencies) {
        this.currencies = currencies;
    }

    public List<String> getCapital() {
        return capital;
    }

    public void setCapital(List<String> capital) {
        this.capital = capital;
    }

    public BigDecimal getArea() {
        return area.setScale(2, RoundingMode.HALF_UP);
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public BigDecimal getPopulationDensity() {
        return populationDensity.setScale(2, RoundingMode.HALF_UP);
    }

    public void setPopulationDensity(BigDecimal populationDensity) {
        this.populationDensity = populationDensity ;
    }
}

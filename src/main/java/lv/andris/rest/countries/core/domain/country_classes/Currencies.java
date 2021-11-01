
package lv.andris.rest.countries.core.domain.country_classes;

import javax.persistence.Embedded;

import com.fasterxml.jackson.annotation.*;
import lv.andris.rest.countries.core.domain.country_classes.currencies_classes.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Currencies {

    @JsonAlias({"EUR", "GBP", "JEP", "RON", "MKD", "MKD", "IMP", "BYN", "GGP", "BGN", "CHF", "BAM", "NOK", "SEK", "HUF", "RSD", "HRK", "CZK", "DKK", "FOK", "PLN", "MDL", "ISK", "RUB", "UAH", "GIP"})
    @Embedded
    private All all;

    @JsonProperty("ALL")
    public All getAll() {
        return all;
    }

    @JsonProperty("ALL")
    public void setAll(All all) {
        this.all = all;
    }


}

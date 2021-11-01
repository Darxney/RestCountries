
package lv.andris.rest.countries.core.domain.country_classes.currencies_classes;

import javax.annotation.Generated;
import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "symbol"
})
@Generated("jsonschema2pojo")
public class All {

    @Column(name = "currency_name")
    @JsonProperty("name")
    private String name;

    @Column(name = "currency_symbol")
    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}

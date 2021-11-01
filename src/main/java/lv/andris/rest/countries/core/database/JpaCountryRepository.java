package lv.andris.rest.countries.core.database;

import lv.andris.rest.countries.core.domain.Country;
import lv.andris.rest.countries.core.domain.country_classes.Name;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCountryRepository extends JpaRepository<Country, Name> {

    @Query("SELECT c FROM Country c ORDER BY c.population DESC")
    List<Country> getTopCountriesByPopulation(Pageable pageable);

    @Query("SELECT c FROM Country c ORDER BY c.area DESC")
    List<Country> getTopCountriesByArea(Pageable pageable);

    @Query(value = "SELECT c FROM Country c ORDER BY c.populationDensity DESC")
    List<Country> getTopCountriesByPopulationDensity(Pageable pageable);

}

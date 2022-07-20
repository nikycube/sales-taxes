package org.lastminute.salestaxes.model.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

/**
 * A config object that is required for multiple purposes.
 */
public class Config {

    private final List<CategoryMapping> categoryMappings;

    private final Set<String> baseTaxExceptions;

    private final Set<String> importTaxExceptions;

    @JsonCreator
    public Config(
            @JsonProperty("categories_mappings")
            List<CategoryMapping> categoryMappings,
            @JsonProperty("base_tax_exceptions")
            Set<String> baseTaxExceptions,
            @JsonProperty("import_tax_exceptions")
            Set<String> importTaxExceptions) {
        this.categoryMappings = categoryMappings;
        this.baseTaxExceptions = baseTaxExceptions;
        this.importTaxExceptions = importTaxExceptions;
    }

    public List<CategoryMapping> getCategoryMappings() {
        return categoryMappings;
    }

    public Set<String> getBaseTaxExceptions() {
        return baseTaxExceptions;
    }

    public Set<String> getImportTaxExceptions() {
        return importTaxExceptions;
    }
}

package org.lastminute.salestaxes.model.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class CategoryMapping {

    private final String category;

    private final Set<String> keywords;

    @JsonCreator
    public CategoryMapping(
            @JsonProperty("category")
            String category,
            @JsonProperty("keywords")
            Set<String> keywords) {
        this.category = category;
        this.keywords = keywords;
    }

    public String getCategory() {
        return category;
    }

    public Set<String> getKeywords() {
        return keywords;
    }
}

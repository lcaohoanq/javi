package com.lcaohoanq.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.swing.SortOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestCriteria {

    private String search;
    private SortCriterion sort;
}

record SortCriterion(
    @JsonProperty("sort_by") String sortBy,
    SortOrder order
) {

}
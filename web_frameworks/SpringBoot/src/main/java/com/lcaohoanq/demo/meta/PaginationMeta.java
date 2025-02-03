package com.lcaohoanq.demo.meta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginationMeta {
    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_items")
    private long totalItems;

    @JsonProperty("current_page")
    private int currentPage;

    @JsonProperty("page_size")
    private int pageSize;

    @JsonProperty("has_previous")
    private boolean hasPrevious;

    @JsonProperty("has_next")
    private boolean hasNext;

    @JsonProperty("is_first_page")
    private boolean isFirstPage;

    @JsonProperty("is_last_page")
    private boolean isLastPage;

    public void calculatePageMetadata() {
        this.isFirstPage = (currentPage == 1);
        this.isLastPage = (currentPage == totalPages);
        this.hasPrevious = (currentPage > 1);
        this.hasNext = (currentPage < totalPages);
    }
}

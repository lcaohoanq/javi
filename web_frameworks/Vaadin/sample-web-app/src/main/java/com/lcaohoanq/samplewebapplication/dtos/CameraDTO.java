package com.lcaohoanq.samplewebapplication.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CameraDTO(

    @NotBlank(message = "Brand is required")
    @JsonProperty("brand")
    String brand,

    @NotBlank(message = "Model is required")
    @JsonProperty("model")
    String model,

    @JsonProperty("description")
    String description,

    @JsonProperty("thumbnail")
    String thumbnail,

    @Positive(message = "Price must be greater than 0")
    @Min(value = 1, message = "Price must be greater than 0")
    @NotBlank(message = "Price is required")
    @JsonProperty("price")
    Double price
) {}

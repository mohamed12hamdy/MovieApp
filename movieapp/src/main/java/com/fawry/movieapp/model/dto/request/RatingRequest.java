package com.fawry.movieapp.model.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest {

    @NotNull(message = "movieId can not be null")
    private Integer movieId;

    @NotNull(message = "Rate can not be null")
    @Min(1)
    @Max(5)
    private Integer ratingValue;

}

package com.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class DataDTO {
    @NotNull
    private Double x;
    @NotNull
    private Double y;
    @NotNull
    private Double r;
}

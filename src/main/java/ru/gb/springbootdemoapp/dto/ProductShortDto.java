package ru.gb.springbootdemoapp.dto;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class ProductShortDto {

  @NotNull
  @NotEmpty
  private String title;

  @NotNull
  private Float price;

  @NotNull
  private String category;
}

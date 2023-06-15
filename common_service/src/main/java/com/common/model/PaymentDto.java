package com.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/** Order DTO object */
@Setter
@Getter
@ToString
public class PaymentDto {
  private UUID id;
  private int customerId;
  private  float amount;
}

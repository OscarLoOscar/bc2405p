package com.bootcamp.bc_yahoo_finance.model.line;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
// @EqualsAndHashCode(exclude = {"dateTime"})
public class PricePoint<T> {
  private LocalDateTime dateTime;
  private T price;

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (!(obj instanceof PricePoint))
      return false;

    PricePoint<?> pricePoint = (PricePoint<?>) obj;
    return Objects.equals(this.dateTime, pricePoint.getDateTime());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.dateTime);
  }
}

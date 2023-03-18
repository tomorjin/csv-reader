package crisp.assignment.csvimporter.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Order {
    private int orderId;
    private LocalDateTime orderDate;
    private String productId;
    private String productName;
    private BigDecimal quantity;
    private String unit;
}

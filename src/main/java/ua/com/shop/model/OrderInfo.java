package ua.com.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderInfo {

    private long orderId;
    private int totalSum;
    private String productName;
    private int quantity;
    private LocalDateTime createdAt;
}

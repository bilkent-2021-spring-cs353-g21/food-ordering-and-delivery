package tr.com.bilkent.fods.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListDTO {
    private Long totalOrders;

    private Integer totalPages;

    private List<OrderDTO> orders;
}

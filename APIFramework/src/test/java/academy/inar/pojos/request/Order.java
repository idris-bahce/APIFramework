package academy.inar.pojos.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {
    private String cartId;
    private String customerName;
}

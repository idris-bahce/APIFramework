package academy.inar.pojos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cart {
    private boolean created;
    private String cartId;
}

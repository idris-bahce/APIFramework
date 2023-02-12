package academy.inar.pojos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Products {
    private int id;
    private String category;
    private String name;
    private boolean inStock;
}

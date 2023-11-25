package api.pojos.cart;

import api.pojos.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart_Output {
    private List<Product> products;
    private int count;
    private String message;
}

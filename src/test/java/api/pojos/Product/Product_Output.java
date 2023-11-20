package api.pojos.Product;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product_Output {
    private Product data;
    private String message;
}

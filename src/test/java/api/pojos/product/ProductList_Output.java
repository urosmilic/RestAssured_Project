package api.pojos.product;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductList_Output {
    private List<Product> data;
    private int count;
    private String message;
}
package api.pojos.Product;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductList_Output {
    private List<Product> data;
    private int count;
    private String message;
}
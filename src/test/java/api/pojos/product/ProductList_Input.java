package api.pojos.product;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductList_Input {
    private String productName;
    private Integer minPrice;
    private Integer maxPrice;
    private List<String> productCategory;
    private List<String> productSubCategory;
    private List<String> productFor;
}
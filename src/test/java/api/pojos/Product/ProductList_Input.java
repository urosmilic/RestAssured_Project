package api.pojos.Product;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductList_Input {
    private String productName;
    private Integer minPrice;
    private Integer maxPrice;
    private List<String> productCategory;
    private List<String> productSubCategory;
    private List<String> productFor;

/*
        "productName": "",
        "minPrice": null,
        "maxPrice": null,
        "productCategory": [],
        "productSubCategory": [],
        "productFor": []
*/
}
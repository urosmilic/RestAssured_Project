package api.pojos.Product;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    private String _id;
    private String productName;
    private String productCategory;
    private String productSubCategory;
    private int productPrice;
    private String productDescription;
    private String productImage;
    private String productRating;
    private String productTotalOrders;
    private boolean productStatus;
    private String productFor;
    private String productAddedBy;
    private int __v;

/*
                "_id": "6262e95ae26b7e1a10e89bf0",
                "productName": "zara coat 3",
                "productCategory": "fashion",
                "productSubCategory": "shirts",
                "productPrice": 31500,
                "productDescription": "zara coat 3",
                "productImage": "https://rahulshettyacademy.com/api/ecom/uploads/productImage_1650649434146.jpeg",
                "productRating": "0",
                "productTotalOrders": "0",
                "productStatus": true,
                "productFor": "women",
                "productAddedBy": "admin@gmail.com",
                "__v": 0
*/
}
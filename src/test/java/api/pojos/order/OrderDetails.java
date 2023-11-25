package api.pojos.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetails {
    private String _id;
    private String orderById;
    private String orderBy;
    private String productOrderedId;
    private String productName;
    private String country;
    private String productDescription;
    private String productImage;
    private String orderPrice;
    private int __v;
}

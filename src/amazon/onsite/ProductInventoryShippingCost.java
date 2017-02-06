package amazon.onsite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by billjyc on 2017/1/28.
 * output class for milestone 1
 */
public class ProductInventoryShippingCost {
    private ProductInventory productInventory;

    private List<ShippingCost> shippingCostList;

    public ProductInventoryShippingCost() {
        this.shippingCostList = new ArrayList<>();
    }

    public ProductInventory getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(ProductInventory productInventory) {
        this.productInventory = productInventory;
    }

    public List<ShippingCost> getShippingCostList() {
        return shippingCostList;
    }

    public void setShippingCostList(List<ShippingCost> shippingCostList) {
        this.shippingCostList = shippingCostList;
    }
}
package amazon.onsite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by billjyc on 2017/1/28.
 */
public class ShippingCostAPI {
    public List<ShippingCost> getShippingCostByDes(Region des) {
        Input input = new Input();
        List<ShippingCost> res = new ArrayList<>();
        for(ShippingCost sc : input.scs) {
            if(sc.getShipTo() == des) {
                res.add(sc);
            }
        }

        return res;
    }
}

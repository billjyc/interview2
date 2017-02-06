package amazon.onsite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by billjyc on 2017/1/28.
 */
public class InventoryAPI {
    public List<ProductInventory> getProductInventoryByPid(int pid) {
        List<ProductInventory> res = new ArrayList<>();
        Input input = new Input();

        for(ProductInventory pi : input.pis) {
            if(pi.getPid() == pid) {
                res.add(pi);
            }
        }
        return res;
    }
}

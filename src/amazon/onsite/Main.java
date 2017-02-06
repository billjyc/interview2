package amazon.onsite;

import java.util.*;

/**
 * Created by billjyc on 2017/1/28.
 */
public class Main {
    /**
     * 1.给定 一个商品ID 和一个 目的地，返回所有对应这个商品的库存 和 运送花费。
     * @param pid product id
     * @param des destination
     * @return
     */
    public List<ProductInventoryShippingCost> mileStone1(int pid, Region des) {
        List<ProductInventoryShippingCost> res = new ArrayList<>();
        InventoryAPI inventoryAPI = new InventoryAPI();
        ShippingCostAPI shippingCostAPI = new ShippingCostAPI();
        //1) In all ProductInventory, find the inventory contains pid
        List<ProductInventory> products = inventoryAPI.getProductInventoryByPid(pid);
        //2) For the destination region, find the corresponding shipping cost
        List<ShippingCost> shippingCosts = shippingCostAPI.getShippingCostByDes(des);

        /**
         * Method 1: two for-loop; complexity: O(n^2)
         */
        /*for(ProductInventory pi : products) {
            ProductInventoryShippingCost pisc = new ProductInventoryShippingCost();
            pisc.setProductInventory(pi);
            for(ShippingCost sc : shippingCosts) {
                if(sc.getShipFrom() == pi.getRegion()) {
                    pisc.getShippingCostList().add(sc);
                }
            }
            if(!pisc.getShippingCostList().isEmpty()) {
                res.add(pisc);
            }
        }*/

        /*
         * Method 2: Map; Complexity: O(n)
         */
        Map<Region, List<ShippingCost>> map = new HashMap<>();
        for(ShippingCost sc : shippingCosts) {
            if(!map.containsKey(sc.getShipFrom())) {
                map.put(sc.getShipFrom(), new ArrayList<>());
            }
            map.get(sc.getShipFrom()).add(sc);
        }
        for(ProductInventory pi : products) {
            if(map.get(pi.getRegion()) == null || map.get(pi.getRegion()).isEmpty()) {
                continue;
            }

            ProductInventoryShippingCost pisc = new ProductInventoryShippingCost();
            pisc.setProductInventory(pi);
            pisc.setShippingCostList(map.get(pi.getRegion()));
            res.add(pisc);
        }
        return res;
    }

    /**
     * 给定一个订单的列表，要么满足运送最多的订单，要么满足最小化迟到的订单（尽量在用户预期时间内送到）
     * choose one (minimize unfulfilled orders or minimize late orders)
     * @param orders
     */
    public void milestone2(List<Order> orders) {
        if(orders == null || orders.isEmpty()) {
            return;
        }
        Comparator<Order> orderCmp = (o1, o2) -> (o1.getQuantity() - o2.getQuantity());
        Comparator<InventoryMinCost> piCmp = (p1, p2) -> (p1.days - p2.days);

        Collections.sort(orders, orderCmp);
        //boolean[] used = new boolean[orders.size()];
        for(int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            PriorityQueue<InventoryMinCost> pq = new PriorityQueue<>(piCmp);
            List<ProductInventoryShippingCost> list = mileStone1(order.getPid(), order.getRegion());
            if(list == null || list.isEmpty()) continue;

            int sumOfProduct = 0;
            for(ProductInventoryShippingCost pisc : list) {
                ProductInventory pi = pisc.getProductInventory();
                List<ShippingCost> scs = pisc.getShippingCostList();
                if(scs == null || scs.isEmpty()) {
                    continue;
                }

                int minDays = Integer.MAX_VALUE;
                for(ShippingCost sc : scs) {
                    if(sc.getDays() < minDays) {
                        minDays = sc.getDays();
                    }
                }
                InventoryMinCost imc = new InventoryMinCost();
                imc.pi = pi;
                imc.days = minDays;
                pq.offer(imc);
                sumOfProduct += pi.getQuantity();
            }
            if(sumOfProduct < order.getQuantity()) continue;
            int toBeSent = order.getQuantity();
            while(toBeSent > 0 && !pq.isEmpty()) {
                InventoryMinCost imc = pq.poll();
                int sentNum = Math.min(imc.pi.getQuantity(), toBeSent);
                toBeSent -= sentNum;
                //pi.reduceQuantity(sentNum);
            }
            //used[i] = true;
        }
    }

    /**
     * 满足平均每单运费最小
     * minimize the cost of every order (minimize average cost per order),
     * and compare to milestone2, which is better and why?
     * @param orders
     */
    public void milestone3(List<Order> orders) {
        if(orders == null || orders.isEmpty()) {
            return;
        }
        Comparator<Order> orderCmp = (o1, o2) -> (o1.getQuantity() - o2.getQuantity());
        Comparator<InventoryShippingCost> piCmp = (p1, p2) -> (p1.costPerItem - p2.costPerItem);
        Comparator<ShippingCost> scCmp = (sc1, sc2) -> (sc1.getCostPerItem() - sc2.getCostPerItem());

        Collections.sort(orders, orderCmp);
        for(Order order : orders) {
            List<ProductInventoryShippingCost> list = mileStone1(order.getPid(), order.getRegion());
            if(list == null || list.isEmpty()) continue;
            PriorityQueue<InventoryShippingCost> pq = new PriorityQueue<>(piCmp);
            int sumOfProduct = 0;

            for(ProductInventoryShippingCost pisc : list) {
                ProductInventory pi = pisc.getProductInventory();
                List<ShippingCost> scs = pisc.getShippingCostList();
                if(scs == null || scs.isEmpty()) continue;
                int minCostPerItem = Integer.MAX_VALUE;
                for(ShippingCost sc : scs) {
                    minCostPerItem = Math.min(sc.getCostPerItem(), minCostPerItem);
                }
                InventoryShippingCost isc = new InventoryShippingCost();
                isc.pi = pi;
                isc.costPerItem = minCostPerItem;
                pq.offer(isc);
                sumOfProduct += pi.getQuantity();
            }
            if(sumOfProduct < order.getQuantity()) continue;
            int toBeSent = order.getQuantity();

            while(toBeSent > 0 && !pq.isEmpty()) {
                InventoryShippingCost isc = pq.poll();
                int sentNum = Math.min(isc.pi.getQuantity(), toBeSent);
                toBeSent -= sentNum;
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        /*
            Milestone 1
         */
        System.out.println("MileStone 1: ");
        List<ProductInventoryShippingCost> list = main.mileStone1(1, Region.WEST);
        if(list == null || list.size() == 0) {
            System.out.println("There is no satisfying items!");
        } else {
            for(ProductInventoryShippingCost pisc : list) {
                System.out.println(pisc.getProductInventory());
                for(ShippingCost sc : pisc.getShippingCostList()) {
                    System.out.println(sc);
                }
            }
        }
        System.out.println("------------------------------");
        /*
            Milestone 2
         */
        Input input = new Input();
        main.milestone2(input.orders);
    }
}

class InventoryMinCost {
    ProductInventory pi;
    int days;
}

class InventoryShippingCost {
    ProductInventory pi;
    int costPerItem;
}

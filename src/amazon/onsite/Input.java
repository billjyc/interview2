package amazon.onsite;

import java.util.*;

public class Input {
	public final List<Order> orders;
	public final List<ProductInventory> pis;
	public final List<ShippingCost> scs;
	
	public Input(){
		orders = new LinkedList<Order>();
		orders.add(new Order(1, 6, 4, Region.CENTER, 0.3));
		orders.add(new Order(1, 3, 2, Region.WEST, 0.0));
		orders.add(new Order(1, 4, 0, Region.EAST, 0.2));
		orders.add(new Order(3, 100, 0, Region.CENTER, 0.1));
		orders.add(new Order(2, 6, 4, Region.CENTER, 0.3));
		pis = new LinkedList<>();
	    pis.add(new ProductInventory (1, 7, Region.NORTH));
	    pis.add(new ProductInventory (3, 70, Region.NORTH));
	    pis.add(new ProductInventory (3, 20, Region.NORTH));
	    pis.add(new ProductInventory (3, 40, Region.EAST));
	    pis.add(new ProductInventory (3, 30, Region.NORTH));
	    scs = new LinkedList<>();
	    scs.add(new ShippingCost(Region.NORTH, Region.WEST, Method.EXPRESS, 3, 10));
	    scs.add(new ShippingCost(Region.NORTH, Region.WEST, Method.GROUND, 1, 15));
	    scs.add(new ShippingCost(Region.NORTH, Region.EAST, Method.GROUND, 2, 20));
	    scs.add(new ShippingCost(Region.NORTH, Region.CENTER, Method.EXPRESS, 2, 5));
	}	
	
}

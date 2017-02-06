package amazon.onsite;

/**
 * Created by billjyc on 2017/1/28.
 */
public class ShippingCost {
    private final Region shipFrom;
    private final Region shipTo;
    private final Method method;
    private final int costPerItem;
    private final int days;

    public ShippingCost(Region shipFrom, Region shipTo, Method method, int costPerItem, int days) {
        this.shipFrom = shipFrom;
        this.shipTo = shipTo;
        this.method = method;
        this.costPerItem = costPerItem;
        this.days = days;
    }

    public Region getShipFrom() {
        return this.shipFrom;
    }

    public Region getShipTo() {
        return this.shipTo;
    }

    public Method getMethod() {
        return this.method;
    }

    public int getCostPerItem() {
        return this.costPerItem;
    }

    public int getDays() {
        return this.days;
    }

    @Override
    public String toString() {
        return "ShippingCost{" +
                "shipFrom=" + shipFrom +
                ", shipTo=" + shipTo +
                ", method=" + method +
                ", costPerItem=" + costPerItem +
                ", days=" + days +
                '}';
    }
}

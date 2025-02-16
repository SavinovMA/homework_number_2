package savinov;

import java.text.DecimalFormat;

public class Delivery {

    public static final double MINIMUM_DELIVERY_AMOUNT = 400; //константа минимальная сумма доставки


    private final int destinationDistance;
    private final CargoDimension cargoDimension;
    private final boolean isFragile;
    private final ServiceWorkload deliveryServiceWorkload;

    //конструктор
    public Delivery(int destinationDistance, CargoDimension cargoDimension, boolean isFragile, ServiceWorkload serviceWorkload) {
        this.destinationDistance = destinationDistance;
        this.cargoDimension = cargoDimension;
        this.isFragile = isFragile;
        this.deliveryServiceWorkload = serviceWorkload;
    }

    // Вычисление стоимости доставки, связанную с расстоянием до пункта назначения.
    public int getDestinationDistanceCostIncrease(int destinationDistance) {
        if (destinationDistance >30) return 300;
        if (destinationDistance >10) return 200;
        if (destinationDistance >2) return 100;
        if (destinationDistance >=0) return 50;
        throw new IllegalArgumentException("Значение расстояния до пункта назвачения должно быть положительным");
        //выбрасывается исключение, если расстояние до пункта назначения не положительное число
    }

    private int getFragileCostIncrease(boolean isFragile) {
        return isFragile ? 300 : 0; //используется тернарный оператор , Если isFragile равно true, возвращает 300, в противном случае возвращает 0
    }

    public double calculateDeliveryCost() {
        if (this.isFragile&&this.destinationDistance>30)
            throw new UnsupportedOperationException("Хрупкий груз не может быть доставлен на расстояние более 30 км");

        double calculateDeliveryCost =
                (getDestinationDistanceCostIncrease(this.destinationDistance)+this.cargoDimension.getCostIncrease()+
                        getFragileCostIncrease(this.isFragile))*this.deliveryServiceWorkload.getDeliveryRate();
        DecimalFormat df = new DecimalFormat("###"); // форматирует число calculatedDeliveryCost по шаблону, убирая дробную часть.
        return Math.max(Double.parseDouble(df.format(calculateDeliveryCost)), MINIMUM_DELIVERY_AMOUNT); //преобразуем значение double в строку
        // обрезаем дробную часть (df.format(calculateDeliveryCost)), затем строку преобразуем обратно в double (parseDouble)
    }
}

package savinov;

public enum ServiceWorkload {

    //Зависимость загруженности службы доставки и коэффициента доставки
    VERY_HIGH(1.6),
    HIGH(1.4),
    INCREASED(1.2),
    NORMAL(1);

    private final double deliveryRate; //хранит коэффициент доставки для конкретной загружености службы


    ServiceWorkload(double deliveryRate) { //конструктор
        this.deliveryRate = deliveryRate;
    }

    public double getDeliveryRate() { //геттер поля deliveryRate
        return deliveryRate;
    }
}



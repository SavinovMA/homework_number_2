package savinov;

public enum CargoDimension { //Габариты груза через enum

    LARGE(200), // константа, большие габариты: +200 рублей к доставке;
    SMALL(100); // константа, маленькие габариты: +100 рублей к доставке

    private final int costIncrease; //хранит надбавку к стоимости доставки для конкретного размера груза
    CargoDimension(int costIncrease) { //конструктор
        this.costIncrease = costIncrease;
    }

    public int getCostIncrease() { //геттер для поля costIncrease
        return costIncrease;
    }
}

package savinov;

public class Main {



    public static void main(String[] args) {

        Delivery dev1 = new Delivery(50,CargoDimension.SMALL,false,ServiceWorkload.NORMAL);
        System.out.println(dev1.calculateDeliveryCost());
    }
}

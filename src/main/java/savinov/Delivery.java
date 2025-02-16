package savinov;

import java.text.DecimalFormat;

public class Delivery {

    public static final double MINIMUM_DELIVERY_AMOUNT = 400; //��������� ����������� ����� ��������


    private final int destinationDistance;
    private final CargoDimension cargoDimension;
    private final boolean isFragile;
    private final ServiceWorkload deliveryServiceWorkload;

    //�����������
    public Delivery(int destinationDistance, CargoDimension cargoDimension, boolean isFragile, ServiceWorkload serviceWorkload) {
        this.destinationDistance = destinationDistance;
        this.cargoDimension = cargoDimension;
        this.isFragile = isFragile;
        this.deliveryServiceWorkload = serviceWorkload;
    }

    // ���������� ��������� ��������, ��������� � ����������� �� ������ ����������.
    public int getDestinationDistanceCostIncrease(int destinationDistance) {
        if (destinationDistance >30) return 300;
        if (destinationDistance >10) return 200;
        if (destinationDistance >2) return 100;
        if (destinationDistance >=0) return 50;
        throw new IllegalArgumentException("�������� ���������� �� ������ ���������� ������ ���� �������������");
        //������������� ����������, ���� ���������� �� ������ ���������� �� ������������� �����
    }

    private int getFragileCostIncrease(boolean isFragile) {
        return isFragile ? 300 : 0; //������������ ��������� �������� , ���� isFragile ����� true, ���������� 300, � ��������� ������ ���������� 0
    }

    public double calculateDeliveryCost() {
        if (this.isFragile&&this.destinationDistance>30)
            throw new UnsupportedOperationException("������� ���� �� ����� ���� ��������� �� ���������� ����� 30 ��");

        double calculateDeliveryCost =
                (getDestinationDistanceCostIncrease(this.destinationDistance)+this.cargoDimension.getCostIncrease()+
                        getFragileCostIncrease(this.isFragile))*this.deliveryServiceWorkload.getDeliveryRate();
        DecimalFormat df = new DecimalFormat("###"); // ����������� ����� calculatedDeliveryCost �� �������, ������ ������� �����.
        return Math.max(Double.parseDouble(df.format(calculateDeliveryCost)), MINIMUM_DELIVERY_AMOUNT); //����������� �������� double � ������
        // �������� ������� ����� (df.format(calculateDeliveryCost)), ����� ������ ����������� ������� � double (parseDouble)
    }
}

package savinov;

public enum ServiceWorkload {

    //����������� ������������� ������ �������� � ������������ ��������
    VERY_HIGH(1.6),
    HIGH(1.4),
    INCREASED(1.2),
    NORMAL(1);

    private final double deliveryRate; //������ ����������� �������� ��� ���������� ������������ ������


    ServiceWorkload(double deliveryRate) { //�����������
        this.deliveryRate = deliveryRate;
    }

    public double getDeliveryRate() { //������ ���� deliveryRate
        return deliveryRate;
    }
}



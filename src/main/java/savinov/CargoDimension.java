package savinov;

public enum CargoDimension { //�������� ����� ����� enum

    LARGE(200), // ���������, ������� ��������: +200 ������ � ��������;
    SMALL(100); // ���������, ��������� ��������: +100 ������ � ��������

    private final int costIncrease; //������ �������� � ��������� �������� ��� ����������� ������� �����
    CargoDimension(int costIncrease) { //�����������
        this.costIncrease = costIncrease;
    }

    public int getCostIncrease() { //������ ��� ���� costIncrease
        return costIncrease;
    }
}

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import savinov.CargoDimension;
import savinov.Delivery;
import savinov.ServiceWorkload;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeliveryCostTest {

    @ParameterizedTest
    @Tag("Positive")
    @DisplayName("Checking the calculation of the shipping cost with correct data")
    @MethodSource("validDeliveryData")
    void nonProblematicDeliveryTest(int distance, CargoDimension cargo, boolean fragile, ServiceWorkload rate, double expectedCost) {

        Delivery delivery = new Delivery(distance, cargo, fragile, rate);

        double actualCost = delivery.calculateDeliveryCost();
        assertEquals(expectedCost,actualCost);
    }

    static Stream<Arguments> validDeliveryData() {

        return Stream.of(
                Arguments.of(0, CargoDimension.LARGE, true, ServiceWorkload.NORMAL, 550),
                Arguments.of(2, CargoDimension.SMALL, true, ServiceWorkload.INCREASED,540),
                Arguments.of(5, CargoDimension.LARGE, true, ServiceWorkload.VERY_HIGH, 960),
                Arguments.of(10,CargoDimension.SMALL, true, ServiceWorkload.NORMAL,500),
                Arguments.of(20,CargoDimension.LARGE, false, ServiceWorkload.HIGH,560),
                Arguments.of(30, CargoDimension.LARGE, true, ServiceWorkload.VERY_HIGH,1120),
                Arguments.of(31 ,CargoDimension.SMALL, false, ServiceWorkload.HIGH, 560),
                Arguments.of(50 ,CargoDimension.SMALL, false, ServiceWorkload.NORMAL, 400)
        );
    }

    @Test
    @Tag("Negative")
    @DisplayName("Checking if an exception is triggered at a negative distance")
    void negativeDistanceOrderCost() {
       Delivery delivery = new Delivery(-1, CargoDimension.SMALL, true,ServiceWorkload.VERY_HIGH);
       Throwable exception = assertThrows(IllegalArgumentException.class, delivery::calculateDeliveryCost);

       assertEquals("Значение расстояния до пункта назвачения должно быть положительным",exception.getMessage());
    }

    @Test
    @Tag("Negative")
    @DisplayName("Checking if an exception when trying to deliver a fragile item over a distance of more than 30 km")
    void deliveryCostFragileOverLimitTest() {
        Delivery delivery = new Delivery(31, CargoDimension.LARGE, true, ServiceWorkload.NORMAL);
        Throwable exception = assertThrows(UnsupportedOperationException.class, delivery::calculateDeliveryCost);

        assertEquals("Хрупкий груз не может быть доставлен на расстояние более 30 км", exception.getMessage());
    }
}

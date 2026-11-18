import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductCalculationsTest {

    @Test
    public void GetVAT_WithValidData_ReturnsCorrectValue() {
        // Arrange: Price = 3500.0, VAT (15%) = 525.0
        ProductCalculations calc = new ProductCalculations(3500.0);

        // Act & Assert
        assertEquals(525.0, calc.GetVAT(), 0.001, "VAT calculation is incorrect.");
    }

    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        // Arrange: Price = 3500.0, VAT = 525.0, Commission (5%) = 175.0 -> Total = 4200.0
        ProductCalculations calc = new ProductCalculations(3500.0);

        // Act & Assert
        assertEquals(4200.0, calc.GetTotal(), 0.001, "Total sale calculation is incorrect.");
    }
}
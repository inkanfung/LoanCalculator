import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class loanCalculatorHistoryTest {
    private loanCalculator LoanCalculator;
    private loanHistory LoanHistory;

    @BeforeEach
    public void setUp() {
        LoanCalculator = new loanCalculator();
        LoanHistory = new loanHistory();
    }
    @Test
    public void testCalculateInterest() {
        loanInput LoanInput = new loanInput(
                LocalDate.of(2023, 3, 15),
                LocalDate.of(2023, 4, 15),
                BigDecimal.valueOf(10000), "USD",
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(1)
        );

        List<loanOutput> loanOutputList = loanCalculator.interestCalculator(LoanInput);
        BigDecimal totalInterest = BigDecimal.ZERO;

        for (loanOutput LoanOutput : loanOutputList) {
            totalInterest = totalInterest.add(LoanOutput.getDailyInterestAccrual());
        }

        assertEquals(32, loanOutputList.size(), "Number of days in loanOutputList should be 32");
        assertEquals(BigDecimal.valueOf(52.48), totalInterest.setScale(2, BigDecimal.ROUND_HALF_UP), "Total interest should be 52.48");
    }

    @Test
    public void testLoanHistory() {
        loanInput LoanInput = new loanInput(
                LocalDate.of(2023, 3, 15),
                LocalDate.of(2023, 4, 15),
                BigDecimal.valueOf(10000), "USD",
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(1)
        );

        List<loanOutput> loanOutputList = LoanCalculator.interestCalculator(LoanInput);

        assertEquals(32, loanOutputList.size(), "Number of days in loanOutputList should be 32");
        assertEquals(BigDecimal.valueOf(1.64), loanOutputList.get(0).getDailyInterestAccrual().setScale(2, BigDecimal.ROUND_HALF_UP));
        assertEquals(BigDecimal.valueOf(1.37), loanOutputList.get(0).getDailyInterestWithoutMargin().setScale(2, BigDecimal.ROUND_HALF_UP));

    }

    @Test
    public void testCalculateInterestWithNegativeLoanAmount() {
        loanInput LoanInput = new loanInput(
                LocalDate.of(2023, 3, 15),
                LocalDate.of(2023, 4, 15),
                BigDecimal.valueOf(-10000), "USD",
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(1)
        );

        assertThrows(IllegalArgumentException.class, () -> loanCalculator.interestCalculator(LoanInput), "Loan amount should be positive");
    }
}
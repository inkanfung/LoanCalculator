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

        /*  1. Adding two loan calculations to the history.
            2. Verifying the size of the history.
            3. Deleting a calculation from the history and verifying the new size.
            4. Modifying and rerunning a calculation.
            5. Verifying that the loan input in the history matches the modified input.
         */

        // First loan input
        loanInput LoanInput1 = new loanInput(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 31), BigDecimal.valueOf(10000), "USD", BigDecimal.valueOf(4.5), BigDecimal.valueOf(1.5));
        List<loanOutput> LoanOutputs1 = loanCalculator.interestCalculator(LoanInput1);
        LoanHistory.addCalculation(LoanInput1, LoanOutputs1);

        // Second loan input
        loanInput LoanInput2 = new loanInput(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 28), BigDecimal.valueOf(15000), "USD", BigDecimal.valueOf(3.5), BigDecimal.valueOf(1.0));
        List<loanOutput> LoanOutputs2 = loanCalculator.interestCalculator(LoanInput2);
        LoanHistory.addCalculation(LoanInput2, LoanOutputs2);

        // Check the history of loan calculations
        List<loanCalculations> LoanCalculations = LoanHistory.getLoanCalculationList();
        assertEquals(2, LoanCalculations.size());

        // Delete a calculation from the history
        LoanHistory.deleteCalculation(0);

        // Check the history again
        LoanCalculations = LoanHistory.getLoanCalculationList();
        assertEquals(1, LoanCalculations.size());

        // Modify and rerun a calculation
        loanInput modifiedLoanInput = new loanInput(LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 31), BigDecimal.valueOf(20000), "USD", BigDecimal.valueOf(3.0), BigDecimal.valueOf(1.5));
        LoanHistory.modifyAndRerunCalculation(0, modifiedLoanInput);

        // Check the updated history of loan calculations
        LoanCalculations = LoanHistory.getLoanCalculationList();
        assertEquals(1, LoanCalculations.size());

        // Verify the modified loan input in the history
        loanInput retrievedLoanInput = LoanCalculations.get(0).getLoanInput();
        assertEquals(modifiedLoanInput.getStartDate(), retrievedLoanInput.getStartDate());
        assertEquals(modifiedLoanInput.getEndDate(), retrievedLoanInput.getEndDate());
        assertEquals(modifiedLoanInput.getLoanAmount(), retrievedLoanInput.getLoanAmount());
        assertEquals(modifiedLoanInput.getCurrency(), retrievedLoanInput.getCurrency());
        assertEquals(modifiedLoanInput.getBaseInterestRate(), retrievedLoanInput.getBaseInterestRate());
        assertEquals(modifiedLoanInput.getMargin(), retrievedLoanInput.getMargin());

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
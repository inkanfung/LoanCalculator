import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {

    //calculate and output the daily interest accrual, days elapsed, daily interest without margin, daily interest with margin, and accrual date
    //for the given loan input data
    //display the total interest accrued during the specified period
    //if the input data is incorrect display an error message
    public static void main(String[] args) {

        loanInput LoanInput = new loanInput(
                LocalDate.of(2023, 3, 15),
                LocalDate.of(2023, 4, 15),
                BigDecimal.valueOf(10000), "USD",
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(1)

        );

        loanHistory LoanHistory = new loanHistory();

        try {

            LoanInput.validate();

            List<loanOutput> loanOutputList = loanCalculator.interestCalculator(LoanInput);

            //Access the history of past calculations, delete, or modify/re-run using the loanHistoryManager object
            LoanHistory.addCalculation(LoanInput, loanOutputList);

            BigDecimal totalInterest = BigDecimal.ZERO;

            System.out.println("Daily Interest Accrual | Days Elapsed | Daily Interest without Margin | Daily Interest with Margin | Accrual Date");

            for (loanOutput LoanOutput : loanOutputList) {

                System.out.printf("%-20.2f | %-12d | %-28.2f | %-23.2f | %s%n",
                        LoanOutput.getDailyInterestAccrual(),
                        LoanOutput.getDaysElapsed(),
                        LoanOutput.getDailyInterestWithoutMargin(),
                        LoanOutput.getDailyInterestWithMargin(),
                        LoanOutput.getAccrualDate()
                );

                totalInterest = totalInterest.add(LoanOutput.getDailyInterestAccrual());
            }

            System.out.printf("Total Interest: %.2f%n", totalInterest);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());

        }

        //print new line
        System.out.println();

        loanInput LoanInput1 = new loanInput(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 31), BigDecimal.valueOf(10000), "USD", BigDecimal.valueOf(4.5), BigDecimal.valueOf(1.5));
        List<loanOutput> LoanOutputs1 = loanCalculator.interestCalculator(LoanInput1);

        // Add the first loan calculation to the history
        LoanHistory.addCalculation(LoanInput1, LoanOutputs1);

        // Calculate the interest for the second loan
        loanInput LoanInput2 = new loanInput(LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 28), BigDecimal.valueOf(15000), "USD", BigDecimal.valueOf(3.5), BigDecimal.valueOf(1.0));
        List<loanOutput> LoanOutputs2 = loanCalculator.interestCalculator(LoanInput2);

        // Add the second loan calculation to the history
        LoanHistory.addCalculation(LoanInput2, LoanOutputs2);

        // Retrieve the history of loan calculations
        List<loanCalculations> LoanCalculations = LoanHistory.getLoanCalculationList();

        // Print the history of loan calculations
        for (loanCalculations LoanCalculation : LoanCalculations) {
            System.out.println("Loan Input: " + LoanCalculation.getLoanInput());
            System.out.println("Loan Outputs: " + LoanCalculation.getLoanOutputList());
        }

        // Delete a calculation from the history
        LoanHistory.deleteCalculation(0);

        // Modify and rerun a calculation
        loanInput modifiedLoanInput = new loanInput(LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 31), BigDecimal.valueOf(20000), "USD", BigDecimal.valueOf(3.0), BigDecimal.valueOf(1.5));
        LoanHistory.modifyAndRerunCalculation(0, modifiedLoanInput);

        // Retrieve and print the updated history of loan calculations
        LoanCalculations = LoanHistory.getLoanCalculationList();
        for (loanCalculations LoanCalculation : LoanCalculations) {
            System.out.println("Loan Input: " + LoanCalculation.getLoanInput());
            System.out.println("Loan Outputs: " + LoanCalculation.getLoanOutputList());
        }




    }

}

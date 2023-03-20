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
    }

}

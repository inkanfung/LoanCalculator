import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class loanCalculator {

    //This method takes a LoanInput object as a parameter
    //which contains information about the loan, such as start date, end date, loan amount, currency, base interest rate, and margin.
    public static List<loanOutput> interestCalculator(loanInput LoanInput){

        //empty list of LoanOutput objects, which will store the calculation results for each day between the start and end dates.
        List<loanOutput> loanOutputList = new ArrayList<>();

        //get the current date with the start date from loan Input
        LocalDate currentDate = LoanInput.getStartDate();

        //loop that iterates through each day between the start and end dates (inclusive)
        //performs calculations for each day
        //loop continues until currentDate becomes greater than the end date.
        while(!currentDate.isAfter(LoanInput.getEndDate())){

            //calculate the number of days elapsed from the start date to the currentDate
            int daysElapsed = (int) ChronoUnit.DAYS.between(LoanInput.getStartDate(), currentDate);

            //the dailyInterestRate is calculated based on the annual interest rate (with margin),
            //and the dailyInterestAmount is calculated using the adjusted simple interest formula.
            //The dailyInterestAmountWithoutMargin is also calculated for reference.

            //calculate the daily interest without margin by calling calculateSimpleInterest with the loan amount, base interest rate, and days elapsed
            BigDecimal dailyInterestWithoutMargin = calculateSimpleInterest(LoanInput.getLoanAmount(), LoanInput.getBaseInterestRate(), daysElapsed);

            //calculate the daily interest with margin by adding the margin to the base interest rate before passing it to the calculateSimpleInterest
            BigDecimal dailyInterestWithMargin = calculateSimpleInterest(LoanInput.getLoanAmount(), LoanInput.getBaseInterestRate().add(LoanInput.getMargin()), daysElapsed);

        }


        return loanOutputList;
    }


    //the simple interest formula I used is: Simple Interest = Principal * Rate * Time
    //adjusted formula for daily is: Daily Interest = Principal * (Daily Interest Rate)
    //To compute the daily interest rate, we divide the annual interest rate (including the margin) by the number of days in a year (assuming 365 days)
    //Daily Interest Rate = (Base Interest Rate + Margin) / 100 * 365
    private static BigDecimal calculateSimpleInterest(BigDecimal loanAmount, BigDecimal baseInterestRate, int daysElapsed) {
        //TODO Auto-generated method stub


        return null;
    }


}

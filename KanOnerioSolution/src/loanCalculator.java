import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class loanCalculator {


    public static List<loanOutput> interestCalculator(loanInput LoanInput){

        if (LoanInput.getLoanAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Loan amount should be positive");
        }


        List<loanOutput> loanOutputList = new ArrayList<>();

        LocalDate currentDate = LoanInput.getStartDate();

        //loop that iterates through each day between the start and end dates (inclusive)
        while(!currentDate.isAfter(LoanInput.getEndDate())){

            //ChronoUnit is an enum in the java.time.temporal package
            //represents a unit of time, such as years, months, days, hours, minutes, and seconds
            //used to measure the amount of time between two temporal objects or to manipulate a temporal object by adding or subtracting a specific amount of time
            //.between() provides a simple and intuitive way to calculate the difference between two dates in days
            //takes care of potential complexities, such as leap years, when calculating the difference
            int daysElapsed = (int) ChronoUnit.DAYS.between(LoanInput.getStartDate(), currentDate);

            //calculate the daily interest without margin by calling calculateSimpleInterest with the loan amount, base interest rate, and days elapsed
            BigDecimal dailyInterestWithoutMargin = calculateSimpleInterest(LoanInput.getLoanAmount(), LoanInput.getBaseInterestRate(), 1);

            //calculate the daily interest with margin by adding the margin to the base interest rate before passing it to the calculateSimpleInterest
            BigDecimal dailyInterestWithMargin = calculateSimpleInterest(LoanInput.getLoanAmount(), LoanInput.getBaseInterestRate().add(LoanInput.getMargin()), 1);

            //the dailyInterestRate is calculated based on the annual interest rate (with margin),
            //The dailyInterestAmountWithoutMargin is also calculated for reference.
            //2 mistakes that I made that needs to be mentioned:
            //1. I subtracted dailyInterestWithOutMargin from dailyInterestWithMargin which resulted a incorrect dailyInterestAccrual value
            //2. both dailyInterestWithoutMargin and dailyInterestWithMargin are calculated using the same number of days elapsed, which is 1 I used dayElapsed
            BigDecimal dailyInterestAccrual = dailyInterestWithMargin;

            loanOutput LoanOutput = new loanOutput(dailyInterestAccrual, daysElapsed, dailyInterestWithoutMargin, dailyInterestWithMargin, currentDate);
            loanOutputList.add(LoanOutput);

            currentDate = currentDate.plusDays(1);

        }

        return loanOutputList;
    }


    //the simple interest formula: Simple Interest = Principal * Rate * Time
    private static BigDecimal calculateSimpleInterest(BigDecimal principal, BigDecimal rate, int time) {
        // multiplies the principal amount, interest rate, and time together, then divides the result by 100 and 365 to get the simple interest value.
        // The division is rounded to 2 decimal places using the BigDecimal.ROUND_HALF_UP rounding mode
        return principal.multiply(rate).multiply(BigDecimal.valueOf(time)).divide(BigDecimal.valueOf(100 * 365), 2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal getTotalInterest(List<loanOutput> loanOutputList) {

        BigDecimal totalInterest = BigDecimal.ZERO;

        for (loanOutput output : loanOutputList) {
            totalInterest = totalInterest.add(output.getDailyInterestAccrual());
        }

        return totalInterest;
    }

}

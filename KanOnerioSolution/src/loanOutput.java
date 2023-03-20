/*
The output should contain the following:

A) A data structure containing the daily accrued interest for each day between the start and end date of the loan:
1) Daily Interest accrual amount
2) Number of days elapsed until the accrued date
3) Daily Interest Amount without margin
4) Daily Interest Amount with margin
5) Accrual Date

B) An element containing the total interest calculated over the given period.

Use the simple interest formula provided here: https://www.investopedia.com/terms/s/simple_interest.asp

*/

import java.math.BigDecimal;
import java.time.LocalDate;

public class loanOutput {

    //represent decimal numbers with arbitrary precision
    //work with very large or very small numbers without losing accuracy
    //important in financial applications
    //rounding errors and inaccuracies can lead to significant discrepancies over time or in large-scale calculations
    private BigDecimal dailyInterestAccrual;
    private int daysElapsed;

    //provides control over the rounding mode when performing arithmetic operations
    //allows you to handle rounding in a predictable and consistent manner
    //crucial for financial calculations where specific rounding rules might apply
    private BigDecimal dailyInterestWithoutMargin;

    //objects are immutable
    //prevent unintended side effects
    //make code more robust
    private BigDecimal dailyInterestWithMargin;
    private LocalDate accrualDate;

    //float or double primitive types for financial calculations can lead to inaccuracies and rounding errors due to their limited precision
    //they represent decimal numbers in a binary format (IEEE 754 floating-point standard)
    //This can cause unexpected behavior and lead to incorrect results
    public loanOutput(BigDecimal dailyInterestAccrual, int daysElapsed, BigDecimal dailyInterestWithoutMargin, BigDecimal dailyInterestWithMargin, LocalDate accrualDate) {
        this.dailyInterestAccrual = dailyInterestAccrual;
        this.daysElapsed = daysElapsed;
        this.dailyInterestWithoutMargin = dailyInterestWithoutMargin;
        this.dailyInterestWithMargin = dailyInterestWithMargin;
        this.accrualDate = accrualDate;
    }

    public BigDecimal getDailyInterestAccrual() {
        return dailyInterestAccrual;
    }

    public int getDaysElapsed() {
        return daysElapsed;
    }

    public BigDecimal getDailyInterestWithoutMargin() {
        return dailyInterestWithoutMargin;
    }

    public BigDecimal getDailyInterestWithMargin() {
        return dailyInterestWithMargin;
    }

    public LocalDate getAccrualDate() {
        return accrualDate;
    }

    public void setDailyInterestAccrual(BigDecimal dailyInterestAccrual) {
        this.dailyInterestAccrual = dailyInterestAccrual;
    }

    public void setDaysElapsed(int daysElapsed) {
        this.daysElapsed = daysElapsed;
    }

    public void setDailyInterestWithoutMargin(BigDecimal dailyInterestWithoutMargin) {
        this.dailyInterestWithoutMargin = dailyInterestWithoutMargin;
    }

    public void setDailyInterestWithMargin(BigDecimal dailyInterestWithMargin) {
        this.dailyInterestWithMargin = dailyInterestWithMargin;
    }

    public void setAccrualDate(LocalDate accrualDate) {
        this.accrualDate = accrualDate;
    }

}

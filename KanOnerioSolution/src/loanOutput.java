import java.math.BigDecimal;
import java.time.LocalDate;

/*
The output should contain the following:

A) A data structure containing the daily accrued interest for each day between the start and end date of the loan:
1)  Daily Interest accrual amount
2) Number of days elapsed until the until the accrued date
3) Daily Interest Amount without margin
4)  Daily Interest Amount with margin
5) Accrual Date

B) An element containing the total interest calculated over the given period.

Use the simple interest formula provided here: https://www.investopedia.com/terms/s/simple_interest.asp

*/
public class loanOutput {

    private BigDecimal dailyInterestAccrual;
    private int daysElapsed;
    private BigDecimal dailyInterestWithoutMargin;
    private BigDecimal dailyInterestWithMargin;
    private LocalDate accrualDate;

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

/*
The input for the loan should contain:

1. Start and End Date of the Loan (date fields)
2. Loan Amount (amount field)
3. Loan Currency (currency)
4. Base Interest Rate (as percentage)
5. Margin (as percentage)

*/


import java.math.BigDecimal;
import java.time.LocalDate;

public class loanInput {

    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal loanAmount;
    private String currency;
    private BigDecimal baseInterestRate;
    private BigDecimal margin;

    public loanInput(LocalDate startDate, LocalDate endDate, BigDecimal loanAmount, String currency, BigDecimal baseInterestRate, BigDecimal margin) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.loanAmount = loanAmount;
        this.currency = currency;
        this.baseInterestRate = baseInterestRate;
        this.margin = margin;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getBaseInterestRate() {
        return baseInterestRate;
    }

    public BigDecimal getMargin() {
        return margin;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setBaseInterestRate(BigDecimal baseInterestRate) {
        this.baseInterestRate = baseInterestRate;
    }

    public void setMargin(BigDecimal margin) {
        this.margin = margin;
    }

}

import java.util.List;

public class loanCalculations {

    private loanInput LoanInput;
    private List<loanOutput> LoanOutputList;

    public loanCalculations(loanInput LoanInput, List<loanOutput> LoanOutputList){
        this.LoanInput = LoanInput;
        this.LoanOutputList = LoanOutputList;
    }

    public loanInput getLoanInput() {
        return LoanInput;
    }

    public List<loanOutput> getLoanOutputList() {
        return LoanOutputList;
    }

    public void setLoanInput(loanInput LoanInput) {
        this.LoanInput = LoanInput;
    }

    public void setLoanOutputList(List<loanOutput> LoanOutputList) {
        this.LoanOutputList = LoanOutputList;
    }

}

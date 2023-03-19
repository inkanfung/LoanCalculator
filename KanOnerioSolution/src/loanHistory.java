/*
The user has access to the history of past calculations and can delete, or modify/ re-run
* */

import java.util.ArrayList;
import java.util.List;

public class loanHistory {

    //loanCalculations of type List<LoanCalculations>store the history of loan calculations
    private List<loanCalculations> loanCalculationsList;

    public loanHistory(){
        loanCalculationsList = new ArrayList<>();
    }

    public List<loanCalculations> getLoanCalculationList() {
        return loanCalculationsList;
    }

    //takes a LoanInput and a List<LoanOutput> as arguments
    //creates a new LoanCalculations object, and adds it to the loanCalculations list
    public void addCalculation(loanInput LoanInput, List<loanOutput> LoanOutputList){
        loanCalculations LoanCalculations = new loanCalculations(LoanInput, LoanOutputList);
        loanCalculationsList.add(LoanCalculations);
    }

    //removes a loan calculation at the specified index
    public void deleteCalculation(int index){
        loanCalculationsList.remove(index);
    }

    //calculates new loan outputs using the updated input
    //creates a new LoanCalculation object
    //replaces the old calculation with the new one in the loanCalculations list
    public void modifyAndRerunCalculation(int index, loanInput newLoanInput){
        List<loanOutput> newLoanOutputList = loanCalculator.interestCalculator(newLoanInput);
        loanCalculations newCalculation = new loanCalculations(newLoanInput, newLoanOutputList);
        loanCalculationsList.set(index, newCalculation);
    }


}

# Terminologies to Understand
Start and End Date of the Loan: 
The beginning and ending dates of the loan period. The calculator computes interest for each day between these two dates.

Loan Amount: 
The principal amount borrowed. This is the initial sum of money that the borrower owes to the lender.

Loan Currency: 
The currency in which the loan is denominated, such as USD, EUR, or GBP. The interest calculation will be in the same currency.

Base Interest Rate: 
The percentage used in the interest calculation that represents the cost of borrowing the loan amount. It is the primary component of the interest rate charged on the loan.

Margin: 
An additional percentage added to the base interest rate. This represents the lender's profit or compensation for the risk of lending.

Daily Interest accrual amount: 
The interest accrued on the loan for each specific day.

Number of days elapsed until the accrued date: 
The total number of days passed since the start date of the loan until the current accrual date.

Daily Interest Amount without margin: 
The interest amount calculated using only the base interest rate, without considering the margin.

Daily Interest Amount with margin: 
The interest amount calculated using both the base interest rate and the margin.
Accrual Date: The specific date on which the interest accrual is calculated.

Syndicated Loan:
A type of loan where multiple financial institutions, also known as lenders or participants, come together to provide funds to a single borrower. 
The main goal of a syndicated loan is to spread the risk associated with lending a large sum of money across multiple lenders.

# Loan Interest Calculator
A simple Java-based Loan Interest Calculator for bank customers. 

This calculator computes the daily interest accruals and total interest for a loan, given the start and end dates, loan amount, loan currency, base interest rate, and margin.

Lets say you have Input Loan Object:

Start date: 2023-03-15
End date: 2023-04-15
Loan amount: 10,000 USD
Base interest rate: 5% per year
Margin: 1% per year

Total annual interest rate = base interest rate + margin = 5% + 1% = 6%

Daily interest rate = Total annual interest rate / 365 = 6% / 365 = 0.00016438356 (rounded to 14 decimal places)

Daily interest amount = Loan amount * daily interest rate = 10,000 * 0.00016438356 = 1.6438356 (rounded to 7 decimal places)

Total interest = Daily interest amount * loan period = 1.6438356 * 32 = 52.6027392 (rounded to 7 decimal places)

Rounded daily interest amount = 1.64

Total interest = Rounded daily interest amount * loan period = 1.64 * 32 = 52.48

# Features
Calculates daily accrued interest for each day between the start and end date of the loan.

Provides a data structure containing the daily interest accrual amount, days elapsed, daily interest amount without margin, daily interest amount with margin, and accrual date.

Computes the total interest calculated over the given period.

Validates input data and generates error messages when the input is incorrect.
Supports simple interest calculations.

Can be extended to handle different interest rate timeseries and additional financial products.

# How to Use
Clone or download the repository to your local machine.

Open the project in your favorite Java IDE or build tool (e.g., IntelliJ IDEA, Eclipse, or Maven).

Ensure you have Java 8 or later installed on your system.

Run the Main class, which contains the sample usage of the LoanCalculator.

The loanOutputs list will contain the daily accrued interest details for each day between the start and end date of the loan.

# Project Extension Ideas

Handling non-business days (weekends and bank holidays) for the start and end dates of the loan.

Incorporating a timeseries of base interest rates instead of a fixed rate.

Adding support for other financial products, such as credit cards, using a modular approach.

Scaling the solution to handle a large number of calculations per second.

Improving API reliability and performance for large queries.

# Terminologies to Understand

# Loan Interest Calculator
A simple Java-based Loan Interest Calculator for bank customers. 

This calculator computes the daily interest accruals and total interest for a loan, given the start and end dates, loan amount, loan currency, base interest rate, and margin.

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

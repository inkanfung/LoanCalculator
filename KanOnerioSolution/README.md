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
1. I was thinking brute force method first maybe HardCode the dates into a set in the inputLoans object/beans E.g.

private boolean isBusinessDay(LocalDate date) {
    // Check if the date is a weekend (Saturday or Sunday)
    DayOfWeek dayOfWeek = date.getDayOfWeek();
    if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
        return false;
    }

    // Check if the date is a holiday
    Set<LocalDate> holidays = getHolidays();
    if (holidays.contains(date)) {
        return false;
    }

    return true;
}

private Set<LocalDate> getHolidays() {
    Set<LocalDate> holidays = new HashSet<>();

    // Add holidays here (e.g., New Year's Day)
    holidays.add(LocalDate.of(startDate.getYear(), 1, 1));
    holidays.add(LocalDate.of(endDate.getYear(), 1, 1));

    // Add more holidays as needed

    return holidays;
}

Plus a validation in the validate method:
  
  public void validate() throws IllegalArgumentException {
    // ...

    if (!isBusinessDay(startDate)) {
        throw new IllegalArgumentException("Start date must be a business day.");
    }

    if (!isBusinessDay(endDate)) {
        throw new IllegalArgumentException("End date must be a business day.");
    }
}

2. The previous method is convenient but is very prone to errors from human/input users. So we can maybe call some external API which I did dive on:
 
  Nager.Date API (https://date.nager.at/): Nager.Date is a free, open-source API that provides information about public holidays for various countries. You can find more details and documentation at https://date.nager.at/swagger/index.html.

  Enrico Service (https://kayaposoft.com/enrico/): Enrico Service is a free API that provides public holiday information for countries in Europe. You can find more information and documentation at https://kayaposoft.com/enrico/.

  Calendarific API (https://calendarific.com/): Calendarific is a global holiday API that provides information about holidays and observances for more than 200 countries. They offer both free and paid plans with varying limits on the number of API calls. You can find more information and documentation at https://calendarific.com/.
  
In java we can use java.net.HttpURLConnection:
  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HolidayAPI {

    public static void main(String[] args) {
        String countryCode = "GB";
        int year = 2023;

        try {
            String jsonResponse = getHolidays(countryCode, year);
            System.out.println(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHolidays(String countryCode, int year) throws IOException {
        String urlString = "https://date.nager.at/api/v3/publicholidays/" + year + "/" + countryCode;
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            connection.disconnect();
            return content.toString();
        } else {
            throw new IOException("Failed to get holidays, response code: " + responseCode);
        }
    }
}

Incorporating a timeseries of base interest rates instead of a fixed rate.

1. First idea that came to mind was using a Map stores the holidays as key-value pairs.
where the key is a LocalDate object representing the date of the holiday, and the value is a String representing the name of the holiday. 

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

public class HolidayCalendar {

    private static final Map<LocalDate, String> HOLIDAYS = new HashMap<>();

    static {
        HOLIDAYS.put(LocalDate.of(2023, 1, 1), "New Year's Day");
        HOLIDAYS.put(LocalDate.of(2023, 4, 14), "Good Friday");
        HOLIDAYS.put(LocalDate.of(2023, 4, 17), "Easter Monday");
        HOLIDAYS.put(LocalDate.of(2023, 5, 1), "May Day");
        HOLIDAYS.put(LocalDate.of(2023, 5, 29), "Spring Bank Holiday");
        HOLIDAYS.put(LocalDate.of(2023, 8, 28), "Summer Bank Holiday");
        HOLIDAYS.put(LocalDate.of(2023, 12, 25), "Christmas Day");
        HOLIDAYS.put(LocalDate.of(2023, 12, 26), "Boxing Day");
    }

    public static boolean isHoliday(LocalDate date) {
        return HOLIDAYS.containsKey(date);
    }

    public static String getHolidayName(LocalDate date) {
        return HOLIDAYS.get(date);
    }
}

getHolidayName method returns the name of a holiday given its date, by looking up the corresponding value in the HOLIDAYS map.

2. Deep Dive and found a more relevant solution called - NavigableMap is a SortedMap with additional functionality to navigate through the keys or entries. 

The keys in this case are dates, and the values are the base interest rates.

A TreeMap is a popular implementation of the NavigableMap interface. 

It stores key-value pairs in a balanced binary search tree, allowing you to perform most operations in logarithmic time.

NavigableMap<LocalDate, BigDecimal> baseInterestRates = new TreeMap<>();
baseInterestRates.put(LocalDate.of(2020, 1, 1), BigDecimal.valueOf(2.5));
baseInterestRates.put(LocalDate.of(2020, 7, 1), BigDecimal.valueOf(2.0));
baseInterestRates.put(LocalDate.of(2021, 1, 1), BigDecimal.valueOf(1.5));
baseInterestRates.put(LocalDate.of(2021, 6, 1), BigDecimal.valueOf(1.75));

look up the base interest rate for the current date using the floorEntry(date) method. 
This method returns the most recent entry with a key less than or equal to the specified date.




Adding support for other financial products, such as credit cards, using a modular approach.

Scaling the solution to handle a large number of calculations per second.

Improving API reliability and performance for large queries.

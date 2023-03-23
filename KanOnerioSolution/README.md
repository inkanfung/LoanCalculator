# Terminologies to Understand

- **Start and End Date of the Loan:** The beginning and ending dates of the loan period. 

The calculator computes interest for each day between these two dates.

- **Loan Amount:** The principal amount borrowed.

This is the initial sum of money that the borrower owes to the lender.

- **Loan Currency:** The currency in which the loan is denominated, such as USD, EUR, or GBP.

The interest calculation will be in the same currency.

- **Base Interest Rate:** The percentage used in the interest calculation that represents the cost of borrowing the loan amount. 

It is the primary component of the interest rate charged on the loan.

- **Margin:** An additional percentage added to the base interest rate. 

This represents the lender's profit or compensation for the risk of lending.

- **Daily Interest accrual amount:** The interest accrued on the loan for each specific day.

- **Number of days elapsed until the accrued date:** The total number of days passed since the start date of the loan until the current accrual date.

- **Daily Interest Amount without margin:** The interest amount calculated using only the base interest rate, without considering the margin.

- **Daily Interest Amount with margin:** The interest amount calculated using both the base interest rate and the margin.

- **Accrual Date:** The specific date on which the interest accrual is calculated.

- **Syndicated Loan:** A type of loan where multiple financial institutions, also known as lenders or participants, come together to provide funds to a single borrower. 

The main goal of a syndicated loan is to spread the risk associated with lending a large sum of money across multiple lenders.

# Loan Interest Calculator

A simple Java-based Loan Interest Calculator for bank customers.

This calculator computes the daily interest accruals and total interest for a loan, given the start and end dates, loan amount, loan currency, base interest rate, and margin.

**Example:**

Input Loan Object:

- Start date: 2023-03-15
- End date: 2023-04-15
- Loan amount: 10,000 USD
- Base interest rate: 5% per year
- Margin: 1% per year

Total annual interest rate = base interest rate + margin = 5% + 1% = 6%

Daily interest rate = Total annual interest rate / 365 = 6% / 365 = 0.00016438356 (rounded to 14 decimal places)

Daily interest amount = Loan amount * daily interest rate = 10,000 * 0.00016438356 = 1.6438356 (rounded to 7 decimal places)

Total interest = Daily interest amount * loan period = 1.6438356 * 32 = 52.6027392 (rounded to 7 decimal places)

Rounded daily interest amount = 1.64

Total interest = Rounded daily interest amount * loan period = 1.64 * 32 = 52.48

# Features

- Calculates daily accrued interest for each day between the start and end date of the loan.

- Provides a data structure containing the daily interest accrual amount, days elapsed, daily interest amount without margin, daily interest amount with margin, and accrual date.

- Computes the total interest calculated over the given period.

- Validates input data and generates error messages when the input is incorrect.

- Supports simple interest calculations.

- Can be extended to handle different interest rate timeseries and additional financial products.

# How to Use

1. Clone or download the repository to your local machine.

2. Open the project in your favorite Java IDE or build tool (e.g., IntelliJ IDEA, Eclipse, or Maven).

3. Ensure you have Java 8 or later installed on your system.

4. Run the Main class, which contains the sample usage of the LoanCalculator.

5. The loanOutputs list will contain the daily accrued interest details for each day between the start and end date of the loan.

# Project Extension Ideas

## 1. Handling non-business days (weekends and bank holidays) for the start and end dates of the loan.

1. I was thinking brute force method first maybe HardCode the dates into a set or hashmap in the inputLoans object/beans E.g.

```java

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

```

Plus a validation in the validate method:
  

```java  

  public void validate() throws IllegalArgumentException {
    // ...
    if (!isBusinessDay(startDate)) {
        throw new IllegalArgumentException("Start date must be a business day.");
    }
    if (!isBusinessDay(endDate)) {
        throw new IllegalArgumentException("End date must be a business day.");
    }
}

```

Using a Map stores the holidays as key-value pairs.
where the key is a LocalDate object representing the date of the holiday
and the value is a String representing the name of the holiday. 

```java
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
```

2. The previous method is convenient but is very prone to errors from human/input users. So we can maybe call some external API which I did dive on:
 
 - Nager.Date API (https://date.nager.at/):

     Nager.Date is a free, open-source API that provides information about public holidays for various countries. You can find more details and documentation at           https://date.nager.at/swagger/index.html.

- Enrico Service (https://kayaposoft.com/enrico/):

  Enrico Service is a free API that provides public holiday information for countries in Europe. You can find more information and documentation at https://kayaposoft.com/enrico/.

- Calendarific API (https://calendarific.com/): 

  Calendarific is a global holiday API that provides information about holidays and observances for more than 200 countries. They offer both free and paid plans with varying limits on the number of API calls. 
  You can find more information and documentation at https://calendarific.com/.


In Java, we can use java.net.HttpURLConnection:

```java
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
```

## 2. Incorporating a timeseries of base interest rates instead of a fixed rate.

1. First idea that came to mind was using a Map stores the holidays as key-value pairs.
where the key is a LocalDate object representing the date of the holiday, and the value is a String representing the name of the holiday. 

```java
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class InterestRateTimeseries {
    private static final Map<LocalDate, BigDecimal> BASE_INTEREST_RATES;

    static {
        BASE_INTEREST_RATES = new HashMap<>();
        BASE_INTEREST_RATES.put(LocalDate.of(2021, 1, 1), BigDecimal.valueOf(0.5));
        BASE_INTEREST_RATES.put(LocalDate.of(2021, 6, 1), BigDecimal.valueOf(0.6));
        BASE_INTEREST_RATES.put(LocalDate.of(2022, 1, 1), BigDecimal.valueOf(0.7));
    }

    public static BigDecimal getBaseInterestRate(LocalDate date) {
        LocalDate rateEffectiveDate = null;
        for (LocalDate key : BASE_INTEREST_RATES.keySet()) {
            if (key.compareTo(date) <= 0) {
                if (rateEffectiveDate == null || key.compareTo(rateEffectiveDate) > 0) {
                    rateEffectiveDate = key;
                }
        }
}

    if (rateEffectiveDate == null) {
        throw new IllegalArgumentException("No base interest rate data available for the given date.");
    }

    return BASE_INTEREST_RATES.get(rateEffectiveDate);
}
```

2. Deep Dive and found a more relevant solution called - NavigableMap is a SortedMap with additional functionality to navigate through the keys or entries. 

- The keys in this case are dates, and the values are the base interest rates.

- A TreeMap is a popular implementation of the NavigableMap interface. 

- It stores key-value pairs in a balanced binary search tree, allowing you to perform most operations in logarithmic time.

```java
NavigableMap<LocalDate, BigDecimal> baseInterestRates = new TreeMap<>();
baseInterestRates.put(LocalDate.of(2020, 1, 1), BigDecimal.valueOf(2.5));
baseInterestRates.put(LocalDate.of(2020, 7, 1), BigDecimal.valueOf(2.0));
baseInterestRates.put(LocalDate.of(2021, 1, 1), BigDecimal.valueOf(1.5));
baseInterestRates.put(LocalDate.of(2021, 6, 1), BigDecimal.valueOf(1.75));
```

- look up the base interest rate for the current date using the floorEntry/floorKey(date) method.

- Both have a time complexity of O(log n), 
    where n is the number of entries in the map. 
    The only difference is that floorEntry() returns the entire entry (key-value pair), whereas floorKey() returns just the key.
(support the later question)
    
    
```java    
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NavigableMap;
import java.util.TreeMap;
public class InterestRateTimeseries {
    private static final NavigableMap<LocalDate, BigDecimal> BASE_INTEREST_RATES;
    static {
        BASE_INTEREST_RATES = new TreeMap<>();
        BASE_INTEREST_RATES.put(LocalDate.of(2021, 1, 1), BigDecimal.valueOf(0.5));
        BASE_INTEREST_RATES.put(LocalDate.of(2021, 6, 1), BigDecimal.valueOf(0.6));
        BASE_INTEREST_RATES.put(LocalDate.of(2022, 1, 1), BigDecimal.valueOf(0.7));
    }
    public static BigDecimal getBaseInterestRate(LocalDate date) {
        LocalDate rateEffectiveDate = BASE_INTEREST_RATES.floorKey(date);
        if (rateEffectiveDate == null) {
            throw new IllegalArgumentException("No base interest rate data available for the given date.");
        }
        return BASE_INTEREST_RATES.get(rateEffectiveDate);
    }
}
```

3. To elaborate on Navigable Map solution its better than Map solution but in terms of time complexity however its also prone to input errors

    So I again looked at available APIs: 
    
    fetch historical base interest rates from an external data source or API, 
    like the FRED (Federal Reserve Economic Data) or the European Central Bank's Statistical Data Warehouse. 
    This will allow to use real-world data in your calculations.
    
    - The Federal Reserve Economic Data (FRED) is a vast database of economic data provided by the Federal Reserve Bank of St. Louis. 
    
    - It contains data on interest rates, exchange rates, GDP, inflation, and much more...
    
    - FRED API = https://api.stlouisfed.org/fred/series/observations?series_id=FEDFUNDS&api_key=YOUR_API_KEY&file_type=json
    
    - Once fetched the data, advised to parse the JSON response and store the base interest rates in a NavigableMap.
    
    - FRED API has rate limits cache the results to avoid hitting those limits frequently. 
    
    - Additionally, you should handle errors and edge cases, such as missing data or API downtimes.
    
    - By using the FRED API, you can make your loan interest calculator more accurate and up-to-date with real-world data.
    
    
    Example Implementation: 
    
    
    ```java
    
    public NavigableMap<LocalDate, BigDecimal> fetchBaseInterestRates(String seriesId, String apiKey) 
    {
    
    // Build the URL for the FRED API request
    String urlString = String.format("https://api.stlouisfed.org/fred/series/observations?series_id=%s&api_key=%s&file_type=json", seriesId, apiKey);
    
    // Use the Apache HttpClient to send a GET request to the FRED API
    HttpClient httpClient = HttpClientBuilder.create().build();
    
    HttpGet httpGet = new HttpGet(urlString);
    
    HttpResponse httpResponse;
    
    try {
        httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        String responseString = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
        // Parse the JSON response and store the base interest rates in a NavigableMap
        JSONObject jsonResponse = new JSONObject(responseString);
        JSONArray observations = jsonResponse.getJSONArray("observations");
        NavigableMap<LocalDate, BigDecimal> baseInterestRates = new TreeMap<>();
        
        for (int i = 0; i < observations.length(); i++) {
            JSONObject observation = observations.getJSONObject(i);
            LocalDate date = LocalDate.parse(observation.getString("date"));
            BigDecimal value = new BigDecimal(observation.getString("value"));
            baseInterestRates.put(date, value);
        }
        
        return baseInterestRates;
        
    } catch (IOException e) {
    
        e.printStackTrace();
        
        throw new RuntimeException("Error fetching base interest rates from FRED API", e);
    }
}

```
        
       
Things to pass to the Param and example of calling the method:          

String seriesId = "IR14266";
String apiKey = "YOUR_API_KEY";
NavigableMap<LocalDate, BigDecimal> ukBaseInterestRates = fetchBaseInterestRates(seriesId, apiKey);
```
    

## 3. Adding support for other financial products, such as credit cards, using a modular approach.

1. Immediately I thought of is separating the concepts in interfaces/abstract classes 

- Create an abstract class or interface named FinancialProduct that defines the common methods and properties for all financial products, 

- such as calculateInterest() and getInterestRate(). 

- The Loan and CreditCard classes can then inherit from this abstract class or implement the interface, providing their specific implementation of these methods.

```java

public interface FinancialProduct {
    BigDecimal calculateInterest();
    BigDecimal getInterestRate();
}

public class Loan implements FinancialProduct {
    // Loan-specific implementation
}

public class CreditCard implements FinancialProduct {
    // CreditCard-specific implementation
}

```

2. Utilizing a specific Strategy pattern

- Create a separate class for each interest calculation strategy. 

- This would allow you to switch between different strategies depending on the financial product. 

- This pattern separates the behavior (interest calculation) from the context (financial product).


```java

public interface InterestCalculationStrategy {
    BigDecimal calculateInterest(BigDecimal principal, BigDecimal rate, int time);
}

public class SimpleInterestCalculation implements InterestCalculationStrategy {
    // Simple interest calculation implementation
}

public class CreditCardInterestCalculation implements InterestCalculationStrategy {
    // Credit card interest calculation implementation
}

public class Loan {
    private InterestCalculationStrategy interestCalculationStrategy;
    
    // Constructor, getters, and setters
}

```

3. Simply Composition over inheritance 

- Instead of using inheritance, consider using composition to build financial products. 

- This can be done by creating small, reusable components that can be combined to create more complex financial products. 

- For example, create separate classes for InterestRate, RepaymentSchedule, and Fees. 

- You can then create instances of these classes and use them to compose new financial products like Loan and CreditCard.

```java

public class InterestRate {
    // Interest rate implementation
}

public class RepaymentSchedule {
    // Repayment schedule implementation
}

public class Fees {
    // Fees implementation
}

public class Loan {
    private InterestRate interestRate;
    private RepaymentSchedule repaymentSchedule;
    private Fees fees;

    // Constructor, getters, and setters
}

public class CreditCard {
    private InterestRate interestRate;
    private RepaymentSchedule repaymentSchedule;
    private Fees fees;

    // Constructor, getters, and setters
}

```

In summary these ideas will promote modularity and reusability, making it easier to extend the current codebase with new financial products or modify existing ones.

## 4. Scaling the solution to handle a large number of calculations per second.


**Problem 1: Increased CPU usage due to complex calculations.**

**Solution:** 
- Optimize the calculation algorithm to reduce CPU usage. 
- Profile the code to identify performance bottlenecks and improve the efficiency of the calculation algorithm. 
- Consider using parallelism to distribute the workload across multiple CPU cores.


**Problem 2: Memory limitations due to storing large amounts of data.**

**Solution:** 
- Implement caching strategies to minimize memory usage.
- Use a least recently used (LRU) cache or other caching algorithms to store only the most relevant data in memory. 
- For long-term storage, consider using a database to offload memory usage to disk storage.


**Problem 3: Increased response time due to high load.**

**Solution:** 
- Implement a load-balancing strategy to distribute incoming requests across multiple instances of the application. 
- This can be achieved using horizontal scaling, where multiple instances of the application run in parallel to share the load. 
- Use a load balancer to evenly distribute incoming requests among available instances.


**Problem 4: Concurrent access to shared resources.**

**Solution:** 
- Use synchronization mechanisms, such as locks or semaphores, to ensure that only one thread can access shared resources at a time. 
- Alternatively, use an optimistic concurrency control strategy like compare-and-swap (CAS) to reduce contention and improve performance.


**Problem 5: Network latency due to remote API calls.**

**Solution:** 
- Use asynchronous programming techniques to perform API calls without blocking the main application thread. 
- Consider using a thread pool to manage a pool of worker threads that can handle multiple API calls concurrently. 
- You can also implement a retry mechanism to handle transient network issues and improve the overall reliability of the application.


**Problem 6: Database bottlenecks due to a high number of read/write operations.**

**Solution:** 
- Optimize database queries to reduce the time spent on database operations. 
- Use proper indexing, caching, and connection pooling techniques to improve database performance.
- You can also consider using a NoSQL database or an in-memory data store like Redis for faster read and write operations.


## 5. Improving API reliability and performance for large queries.


**Circuit Breaker Pattern:**

- Implement a circuit breaker pattern to detect failures and encapsulate the logic of preventing a failure from constantly recurring. 
- When the circuit breaker detects a problem, it "trips" and temporarily stops sending requests to the failing service, allowing it to recover. 
- After a predefined period, the circuit breaker checks if the service is healthy again and resumes sending requests if it is.


**Retry Mechanism:**

- Incorporate a retry mechanism that automatically retries failed requests with exponential backoff and jitter. 
- This strategy helps to alleviate the impact of transient network issues by giving the remote service a chance to recover before the next retry. 
- The exponential backoff helps to prevent overwhelming the remote service, while the jitter helps to avoid synchronization among retries.



**Timeout Management:**

- Set appropriate timeouts for API requests to ensure that the application does not hang indefinitely when waiting for a response. 
- By enforcing a timeout, the application can fail fast and recover gracefully in case of a slow or unresponsive remote service.



**Rate Limiting and Throttling:**

- Implement rate limiting and throttling to control the number of requests sent to the API in a given time frame. 
- This can help prevent overloading the API and ensures fair usage among clients. You can use token bucket or leaky bucket algorithms to achieve this.



**Caching:**

- Cache the results of API calls to reduce the number of requests made to the API and improve the performance of large queries. 
- You can use a cache with an appropriate eviction policy (e.g., LRU, LFU) to store the most frequently accessed data in memory.



**Pagination and Filtering:**

- For large queries, implement pagination and filtering mechanisms to limit the amount of data returned by the API. 
- By returning smaller chunks of data, you can improve the response time and reduce the load on the API.



**Load Balancing:**

- Use load balancing to distribute incoming API requests across multiple instances of your application. 
- This helps to improve overall performance and ensure that a single instance does not become a bottleneck. 
- You can use round-robin, least connections, or other load balancing algorithms to achieve this.



**Monitoring and Alerting:**

- Implement monitoring and alerting tools to track the performance and health of your API.
- By continuously monitoring the API, you can detect issues early and take corrective actions before they escalate into bigger problems. 
- Set up alerts to notify you when predefined thresholds are breached.

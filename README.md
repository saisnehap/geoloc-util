# Geolocation Utility

## Overview

This utility fetches geolocation data (latitude, longitude, place information) using the OpenWeather Geocoding API. You can provide city/state combinations or zip codes as inputs. The utility can handle multiple location queries at once.

## How to Run the Utility

1. Clone this repository to your local machine.
2. Install the necessary dependencies:
   - If using Maven:
     ```bash
     mvn clean install
     ```
   - If using Gradle:
     ```bash
     gradle build
     ```

3. Run the utility from the command line:
   ```bash
   java -cp out com.example.GeolocationUtil "Madison, WI" "10001" "Los Angeles, CA"

4. The utility will return the latitude, longitude, and place name for each location.

## How to Run Tests

 Compile and run the tests with Maven (if using Maven): 

  ```bash
    mvn clean test 
```
 If running without Maven, use `javac` to compile the test classes and run with JUnit
# addepar-assessment


![addeparScreenshot](https://github.com/user-attachments/assets/01655393-3877-4575-b265-56d4c6e6af81)

https://github.com/user-attachments/assets/f971818c-2c7f-4317-9dd5-ffdd506475ab


# Addepar investment application

## Prerequisite
To set up the application, you need Android Studio Jellyfish or above.
Gradle and Kotlin version:
  
```
------------------------------------------------------------
Gradle 8.6
------------------------------------------------------------

Kotlin:       1.9.20
Groovy:       3.0.17
Ant:          Apache Ant(TM) version 1.10.13 compiled on January 4 2023
JVM:          17.0.10 (JetBrains s.r.o. 17.0.10+0--11572160)
OS:           Windows 11 10.0 amd64
```

  

## Architecture
MVVM architecture is used for the application. The segregation of responsibility is done in layers. The application is divided into 3 layers:

1. Data: The data layer contains all the data holder classes and their sources.
2. Domain: This layer contains all the core business logic.
3. Presentation: This layer contains views and viewModels. The viewModel provides states to be observed by the view.

## DI
Each layer is loosely coupled with another. The implementation of interfaces is injected through Hilt. It makes them easier to test.

## Testing
The application is designed in a reactive manner. For reactive programming, flows are used. Test cases are written to test the emissions as well. 
Test cases cover 100% of business logic, repository, and viewModels.





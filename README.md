# Description of the tests development

**In the process of test development, I used the Java programming language and IntelliJ IDEA development environment. To manage browsers, I used the Selenium tool, and to organize and run tests, I used the TestNG test framework. Testing of the site was performed in the Google Chrome browser on a Windows 64-bit operating system. I used the Maven system for building and project management.**

# Process of testing

**I tested the graphical interface of the https://parabank.parasoft.com/ site and specifically four of its functionalities: Transfer Funds, Bill Pay, Request Loan, and Find Transaction. In order to test these functionalities, it was necessary to register on the site or log in. Therefore, the first test is responsible for registration, and the following tests are for the four functionalities. Before starting each of the next four tests, I logged into the account created in the first test and started testing. Testing was done by checking the display of elements on the page, the possibility of clicking on buttons, clicking on links, entering different test values into the input fields, and displaying messages after input and analyzing them further.**

# How to run tests

**In order to run my code on another computer, the following steps are required:**

1. Clone the repository from GitHub.
2. Install JDK: Make sure the latest version of JDK is installed.
3. Install IntelliJ IDEA: Download and install IntelliJ IDEA if it has not been already installed.
4. Install Maven: Download and install Maven if it has not been already installed.
5. Import the project into IntelliJ IDEA: Open IntelliJ IDEA. Select "Open" and specify the path to the cloned repository. IntelliJ IDEA will automatically detect that it is a Maven project and prompt you to import dependencies.
6. Install the driver for Chrome corresponding to the version of Google Chrome installed on your computer. Make sure `chromedriver.exe` is in the system path or specify the path to it in the code.
- In class Driver, in method initializeDriver you need to change path to Chrome driver
(System.setProperty("webdriver.chrome.driver", "C:\\Users\\37529\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");)
7. Run tests: In IntelliJ IDEA, open a class with tests and run them. If you need to choose the default search engine after running the tests, you should select Google.

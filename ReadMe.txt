API Automation Framework Setup Guide

Framework is developed using the Rest assured library, Java and Maven.. It was designed with the focus on easing the tests as well as validating the REST API.we can verify the Status code, Status message, Headers and even the Body of the response.
 It supports types of request methods such as POST, GET, PUT, DELETE, HEAD, PATCH and OPTIONS to verify the response of these requests.
Input are provided  through the excel file and output were written on the same file and trigger the mail to the mapped email along with the html report and output excel files 

Environmental Setup:

Java Run-time Environment (JRE) 1.6 or later
An Integration Development Environment - it is recommended to use Eclipse (alternatively, it is possible to use other frameworks such as Eclipse, etc.)
Access to get the java code

Download the project from the repository and import the project into the eclipse. Once the import of the project in eclipse is done open the InputData.java file under the Src/Main/Java. 

Filepath:Enter the path of the project location in file path
Filename: Name of the xlsx file in the which going to prepare the script
Sheet name: Name of the xlsx sheet
To address: Enter your  Mail id to receive the output  
After entering the above mentioned details save them and navigate to the Test_api.xls file in the project folder and follow the steps below.

Writing Script:

Open the Api_inputs.xls file to enter the api deatils. The following details are required for writing the script Request URL, Method, Excepted code, Request body type if required and expected response in the sheet on the current directory.  

URL
Method
Expected code
Request Body
Expected Response


Script Execution:

Once the test script are set in the Api_inputs.xls file, launch the Runner.java file and run the script as Java Application by right clicking on the runner.java file. The console will pop up and display the log, wait until it displays the message of email sent successfully. 

Output:
Once the script execution has been completed email will be triggered with the following report:

Extend Reports
Test Case Output
Extent report:
This will be a TestNG listener for generating customized graphical reports. Here we can see the executed results in graphical reports manner like a pie chart with pass steps, failed steps, pass percentage, time is taken for whole execution, etc.



Test Case Output:
Once the execution is completed you can see the automation results on the prepared excel sheet against each test case step and also it will be as an attachment in the output mail.

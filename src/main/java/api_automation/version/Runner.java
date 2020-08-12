package api_automation.version;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Runner {
	
	public ExcelReadWrite excelOp;

	
	public static void main(String args[]) throws IOException {
		String filePath = InputData.filepath;
		String fileName = InputData.filename;
		String sheetName = InputData.sheetname;
		
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(InputData.htmlreport);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		ExcelReadWrite.readExcel(filePath, fileName, sheetName, extent);	
		extent.flush();
		SendEmail.mail(args);
	}		

}

package api_automation.version;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.response.Response;

public class ExcelReadWrite {

	@SuppressWarnings("static-access")
	public static void readExcel(String filePath, String fileName, String sheetName, ExtentReports extent)
			throws IOException {

		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		
		
		String reqUrl = null;
		String methodName = null;
		int codenew;
		String responsedata = null;
		String ExpectedResponse = null;	
		String exceptedcodexls ;
		Workbook workbook = null;
		Response resp;
		String log = ApiRequestProcess.logger2;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xls")) {
			workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);

			int currentBody = 6;
			System.out.println("******************Total APIs are : " + sheet.getLastRowNum() + " ******************");
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				reqUrl = sheet.getRow(i).getCell(1).toString();
				methodName = sheet.getRow(i).getCell(2).toString();
				exceptedcodexls =sheet.getRow(i).getCell(3).toString();
				ExpectedResponse =sheet.getRow(i).getCell(7).toString();
				
				 
				
				System.out.println("Processing " + i + ") " + reqUrl + " " + methodName);
				ApiRequestProcess sendrequ = new ApiRequestProcess();
				sendrequ.testResponseCode(reqUrl, methodName,
						Double.parseDouble(sheet.getRow(i).getCell(3).toString()),
						sheet.getRow(i).getCell(5).toString(),
						 extent);
	
				 codenew = ApiRequestProcess.code;
				 responsedata =ApiRequestProcess.respons;
				 
				if(codenew == Double.parseDouble(sheet.getRow(i).getCell(3).toString()))	  { 
						
						FileOutputStream f2 = new FileOutputStream(file);
						HSSFCellStyle style = sheet.getRow(i).getCell(2).getCellStyle();
						HSSFCell cell = sheet.getRow(i).createCell(6);
						HSSFCell cell1 = sheet.getRow(i).createCell(4);
						HSSFCell cell2 = sheet.getRow(i).createCell(8);

						cell1.setCellStyle(style);
						cell2.setCellStyle(style);
						cell.setCellStyle(style);
						
						cell.setCellValue("PASS");
						cell1.setCellValue(codenew);
						cell2.setCellValue(responsedata);
						System.out.println("PASS");
						workbook.write(f2);
						f2.close();
						System.out.println("******************************************************");
						
						extent.createTest(methodName+" "+reqUrl).pass(log);
						
					}  
					if(codenew != Double.parseDouble(sheet.getRow(i).getCell(3).toString())) {
						FileOutputStream f2 = new FileOutputStream(file);
						HSSFCell cell = sheet.getRow(i).createCell(6);
						HSSFCellStyle style = sheet.getRow(i).getCell(2).getCellStyle();
						HSSFCell cell1 = sheet.getRow(i).createCell(4);
						HSSFCell cell2 = sheet.getRow(i).createCell(8);
						System.out.println("FAIL");
						
						cell1.setCellStyle(style);
						cell2.setCellStyle(style);
						cell.setCellStyle(style);
						
						cell.setCellValue("FAIL");
						cell1.setCellValue(codenew);
						cell2.setCellValue(responsedata);
						workbook.write(f2);
						f2.close();
						System.out.println("******************************************************");
						
						extent.createTest(methodName+" "+reqUrl).fail(log);
					}
	
					if(ExpectedResponse.contentEquals(responsedata)) {
						FileOutputStream f2 = new FileOutputStream(file);
						HSSFCellStyle style = sheet.getRow(i).getCell(2).getCellStyle();
						HSSFCell cell = sheet.getRow(i).createCell(9);	
						cell.setCellStyle(style);
						cell.setCellValue("Matched");			
						workbook.write(f2);
						f2.close();
						System.out.println("******************************************************");
					} else {
						FileOutputStream f2 = new FileOutputStream(file);
						HSSFCell cell = sheet.getRow(i).createCell(9);	
						HSSFCellStyle style = sheet.getRow(i).getCell(2).getCellStyle();
						cell.setCellStyle(style);
						cell.setCellValue("Not Matched");			
						workbook.write(f2);
						f2.close();
						System.out.println("******************************************************");
					}
				
			
	
			}
	
			
		}
	}
}

package com.ajio.utilities;
 
import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
import java.io.File;
 
public class ExtentManager {
 
    private static ExtentReports extent;

    static String projectPath = System.getProperty("user.dir");
 
    public static ExtentReports getInstance() {

        if (extent == null) {

            String reportPath = projectPath + "/src/test/resources/Reports/AjioReport.html";

            File reportFile = new File(reportPath);

            reportFile.getParentFile().mkdirs(); // Ensure directories exist
 
            ExtentSparkReporter spark = new ExtentSparkReporter(reportFile);

            spark.config().setDocumentTitle("Ajio Automation Report");

            spark.config().setReportName("Ajio E-Commerce Test Results");
 
            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo("Project", "Ajio E-Commerce Automation");

            extent.setSystemInfo("Tester", "Mavya");

        }

        return extent;

    }
 
	public static ExtentReports getinstance() {

		// TODO Auto-generated method stub

		return null;

	}

}

 
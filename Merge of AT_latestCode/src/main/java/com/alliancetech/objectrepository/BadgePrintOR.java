package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

public class BadgePrintOR {
  
	public static By lnkBadgePrintAdmin=By.xpath("//a[text()='Badge Print Admin']");
	
	public static By txtBadgePrinter=By.xpath("//h1[text()='Badge Printer']");
	
	public static By chckboxEnablePDFPrint=By.id("badge-print-pdf");
	
	public static By chckboxEnablePDFPrintPositionSelection=By.id("badge-print-pdf-position");
	
	public static By btnSubmit=By.xpath("//input[@value='Submit']");
	
	public static By txtBadgePrintSettingSuccessMsg=By.xpath("//div[text()='Badge print settings have been updated.']");
	
	public static By lnkTools=By.xpath("//a[contains(@id,'all-tools')]");
	
	public static By txtPrintBadges=By.xpath("//h3[text()='Print Badges']");
	
	public static By lnkBadgePrintPDF=By.xpath("//a[text()='Badge Print PDF']");
	
	public static By lnkCurrentPage=By.xpath("//li[@id='current-page-btn-badge-print']/a");
	
	public static By ddPrinterOption=By.xpath("//select[@id='badge-printer-name']/option");
	
	public static By ddPrinter=By.xpath("//select[@id='badge-printer-name']");
	
	public static By chckboxAllAttendees=By.id("check-all");
	
	public static By btnNext=By.xpath("//input[@value='Next']");
	
	public static By btnConfirm=By.xpath("//input[@value='Confirm']");
	
	public static By lnkBadgePrintPDFForOneRegistrant=By.xpath("//li[@id='badge-print-pdf-btn']/a");
	
	public static By btnPrint=By.xpath("//button/span[text()='Print']");
	
	public static By ddPDFPosition=By.id("pdf-position");
	
	public static By ddBadgePrintPDF=By.xpath("//select[@id='badge-printer-name']/option[text()='Badge Print PDF']");
}

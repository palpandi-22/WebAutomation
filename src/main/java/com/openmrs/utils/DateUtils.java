package com.openmrs.utils;

import java.time.Duration;
import java.time.Month;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.openmrs.driver.DriverManager;


public final class DateUtils {

	
	
	
	protected WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));


	/**
	 * @author Palpandi
	 * @implNote : Method will return the current date
	 * @return
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		return date.toString().replace(":", "_").replace(" ", "_");
	}


	public static Month short_Month_Converter(String month) {
		Map<String, Month> monthMap = new HashMap<>();
		monthMap.put("Jan", Month.JANUARY);
		monthMap.put("Feb", Month.FEBRUARY);
		monthMap.put("Mar", Month.MARCH);
		monthMap.put("Apr", Month.APRIL);
		monthMap.put("May", Month.MAY);
		monthMap.put("Jun", Month.JUNE);
		monthMap.put("Jul", Month.JULY);
		monthMap.put("Aug", Month.AUGUST);
		monthMap.put("Sep", Month.SEPTEMBER);
		monthMap.put("Oct", Month.OCTOBER);
		monthMap.put("Nov", Month.NOVEMBER);
		monthMap.put("Dec", Month.DECEMBER);

		return monthMap.get(month);
	}


}

package practiceexpandtesting

import com.kms.katalon.core.annotation.Keyword


public class ScrapeDynamicTableKeyword {

	@Keyword
	static String getCellText(String browser, String metric) {
		return DynamicTableScraper.getCellText(browser, metric)
	}
}

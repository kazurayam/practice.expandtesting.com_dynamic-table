import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import my.PracticeExpandTestingDynamicTableScraper as Scraper
import my.TestObjectUtils

/**
 * TC3
 * 
 * A proposal to the question at
 * https://forum.katalon.com/t/how-handle-a-dynamic-webtable-in-katalon-studio-what-are-the-ways-we-can-access-the-table/177118/
 */


// The HTML of this URL contains a terrible <table>.
// It changes the order of rows and columns
// every time the page is retrieved.
String url = 'https://practice.expandtesting.com/dynamic-table'
WebUI.openBrowser('')
WebUI.navigateToUrl(url)

// wait for the page is loaded on the browser viewport
WebUI.verifyElementPresent(TestObjectUtils.makeTestObject('coreTable', 'id("core")//table'), 10)

def targets = [ ["Name": "Chrome", "Metric": "CPU"],
				["Name": "Firefox", "Metric": "Memory"],
				["Name": "System", "Metric": "Network"]
				]

targets.each { entry ->
	String text = Scraper.getCellText(entry.Name, entry.Metric)
	println "Name:${entry.Name}, Metric:${entry.Metric}, text:${text}"
}

WebUI.closeBrowser()

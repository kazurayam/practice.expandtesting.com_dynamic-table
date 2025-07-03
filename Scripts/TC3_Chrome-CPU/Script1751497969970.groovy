import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import practiceexpandtesting.DynamicTableScraper as Scraper
import my.TestObjectUtils

/**
 * TC3ChromeCPU
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

String text = Scraper.getCellText("Chrome", "CPU")
println "Name:Chrome, Metric:CPU, text:${text}"

WebUI.closeBrowser()

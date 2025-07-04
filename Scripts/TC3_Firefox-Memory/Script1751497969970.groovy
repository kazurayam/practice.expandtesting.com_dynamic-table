import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import practiceexpandtesting.DynamicTableScraper as Scraper
import my.TestObjectUtils

/**
 * TC3_Firefox-Memory
 */

String url = 'https://practice.expandtesting.com/dynamic-table'
WebUI.openBrowser('')
WebUI.navigateToUrl(url)

// wait for the page is loaded on the browser viewport
WebUI.verifyElementPresent(TestObjectUtils.makeTestObject('coreTable', 'id("core")//table'), 10)

String text = Scraper.getCellText("Firefox", "Memory")
println "Name:Firefox, Metric:Memory, text:${text}"

WebUI.closeBrowser()

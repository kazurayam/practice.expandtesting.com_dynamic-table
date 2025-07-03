import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import practiceexpandtesting.DynamicTableScraper as Scraper
import my.TestObjectUtils

/**
 * TC3_Chrome-CPU
 */

String url = 'https://practice.expandtesting.com/dynamic-table'
WebUI.openBrowser('')
WebUI.navigateToUrl(url)

// wait for the page is loaded on the browser viewport
WebUI.verifyElementPresent(TestObjectUtils.makeTestObject('coreTable', 'id("core")//table'), 10)

String text = Scraper.getCellText("Chrome", "CPU")
println "Name:Chrome, Metric:CPU, text:${text}"

WebUI.closeBrowser()

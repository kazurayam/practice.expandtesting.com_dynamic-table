import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import my.TestObjectUtils as TestObjectUtils

/**
 * TC4
 */

String url = 'https://practice.expandtesting.com/dynamic-table'

WebUI.openBrowser('')

WebUI.navigateToUrl(url)

// wait for the page is loaded on the browser viewport
WebUI.verifyElementPresent(TestObjectUtils.makeTestObject('coreTable', 'id("core")//table'), 10)

String chromeCPU = CustomKeywords.'practiceexpandtesting.ScrapeDynamicTableKeyword.getCellText'('Chrome', 'CPU')

println("chrome,CPU=$chromeCPU")

WebUI.closeBrowser()


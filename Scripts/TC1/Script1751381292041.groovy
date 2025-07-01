import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

TestObject makeTestObject(String id, String xpath) {
	TestObject tobj = new TestObject(id)
	tobj.addProperty("xpath", ConditionType.EQUALS, xpath)
	return tobj
}

int findIndexOfWebElementByText(List<WebElement> list, String text) {
	int v = -1
	list.eachWithIndex { webElement, index ->
		if (webElement.getText() == text) {
			v = index
		}
	}
	return v
}

String url = 'https://practice.expandtesting.com/dynamic-table'

WebUI.openBrowser('')
WebUI.navigateToUrl(url)
WebUI.delay(3)

WebUI.verifyElementPresent(makeTestObject('coreTable', 'id("core")//table'), 10)

List<WebElement> listOfTdInThead = 
	WebUI.findWebElements(
		makeTestObject('listofTdInThead', 'id("core")//table/thead/tr/th'), 10)

int nameColumnIndex = findIndexOfWebElementByText(listOfTdInThead, 'Name')
println "nameColumnIndex=${nameColumnIndex}"

int cpuColumnIndex = findIndexOfWebElementByText(listOfTdInThead, 'CPU')
println "cpuColumnIndex=${cpuColumnIndex}"

List<WebElement> listOfTdInNameColumn = 
	WebUI.findWebElements(
		makeTestObject('listOfTdInNameColumn', "id('core')//table/tbody/tr/td[${nameColumnIndex + 1}]"), 10)

int chromeRowIndex = findIndexOfWebElementByText(listOfTdInNameColumn, 'Chrome')
println "chromeRowIndex=${chromeRowIndex}" 

String textOfChromeCPU = 
	WebUI.getText(makeTestObject('chromeCPU', "id('core')//table/tbody/tr[${chromeRowIndex + 1}]/td[${cpuColumnIndex + 1}]"))
println "textofChromeCPU=${textOfChromeCPU}"

WebUI.closeBrowser()
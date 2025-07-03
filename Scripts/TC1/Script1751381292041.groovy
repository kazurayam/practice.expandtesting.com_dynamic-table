import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * TC1
 */

// The HTML of this URL contains a terrible <table>. 
// It changes the order of rows and columns
// every time the page is retrieved.
String url = 'https://practice.expandtesting.com/dynamic-table'
WebUI.openBrowser('')
WebUI.navigateToUrl(url)

// wait for the page is loaded on the browser viewport
WebUI.verifyElementPresent(makeTestObject('coreTable', 'id("core")//table'), 10)

// get the list of <th>Name</th>...<th>CPU</th>... as the column titles
List<WebElement> listOfThInThead = 
	WebUI.findWebElements(
		makeTestObject('listofTdInThead', 'id("core")//table/thead/tr/th'), 10)

// find the index of "Name" column
int nameColumnIndex = findIndexOfWebElementByText(listOfThInThead, 'Name')
println "nameColumnIndex=${nameColumnIndex}"

// find the index of "CPU" column
int cpuColumnIndex = findIndexOfWebElementByText(listOfThInThead, 'CPU')
println "cpuColumnIndex=${cpuColumnIndex}"

// get the list of <td>Chrome</td>...<td>Firefox</td>... as the browser names
List<WebElement> listOfTdInNameColumn = 
	WebUI.findWebElements(
		makeTestObject('listOfTdInNameColumn', "id('core')//table/tbody/tr/td[${nameColumnIndex + 1}]"), 10)

// find the index of "Chrome" row
int chromeRowIndex = findIndexOfWebElementByText(listOfTdInNameColumn, 'Chrome')
println "chromeRowIndex=${chromeRowIndex}" 

// select the <td> of "Chrome"x"CPU", get the content text
String textOfChromeCPU = 
	WebUI.getText(makeTestObject('chromeCPU', "id('core')//table/tbody/tr[${chromeRowIndex + 1}]/td[${cpuColumnIndex + 1}]"))
println "textOfChromeCPU=${textOfChromeCPU}"

WebUI.closeBrowser()

/*
 * 
 */
TestObject makeTestObject(String id, String xpath) {
	TestObject tobj = new TestObject(id)
	tobj.addProperty("xpath", ConditionType.EQUALS, xpath)
	return tobj
}

/*
 * 
 */
int findIndexOfWebElementByText(List<WebElement> list, String text) {
	int v = -1
	list.eachWithIndex { webElement, index ->
		if (webElement.getText() == text) {
			v = index
		}
	}
	return v
}
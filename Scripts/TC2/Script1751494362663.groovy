import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * TC2
 */

String url = 'https://practice.expandtesting.com/dynamic-table'
WebUI.openBrowser('')
WebUI.navigateToUrl(url)

// wait for the page is loaded on the browser viewport
WebUI.verifyElementPresent(makeTestObject('coreTable', 'id("core")//table'), 10)

// get the text of Chrome-CPU cell
String chromeCPU = getCellText("Chrome", "CPU")
println "Chrome-CPU=${chromeCPU}"


// get the text of Firefox-Memory cell
String firefoxMemory = getCellText("Firefox", "Memory")
println "Firefox-Memory=${firefoxMemory}"

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

/*
 * 
 */
String getCellText(String browser, String metric) {
	println "\n>>> browser=${browser}, metric=${metric}"
	
	// get the list of <th>Name</th>...<th>CPU</th>... as the column titles
	List<WebElement> listOfThInThead =
		WebUI.findWebElements(
			makeTestObject('listofTdInThead', 'id("core")//table/thead/tr/th'), 10)
	
	// find the index of "Name" column
	int nameColumnIndex = findIndexOfWebElementByText(listOfThInThead, "Name")
	println "nameColumnIndex=${nameColumnIndex}"
	assert nameColumnIndex >= 0
	
	// find the index of the problem column
	int theColumnIndex = findIndexOfWebElementByText(listOfThInThead, metric)
	println "theColumnIndex=${theColumnIndex}"
	assert theColumnIndex >= 0
	
	// get the list of <td>Chrome</td>...<td>Firefox</td>... as the browser names
	List<WebElement> listOfTdInNameColumn =
		WebUI.findWebElements(
			makeTestObject('listOfTdInNameColumn', "id('core')//table/tbody/tr/td[${nameColumnIndex + 1}]"), 10)
	
	// find the index of the problem row
	int theRowIndex = findIndexOfWebElementByText(listOfTdInNameColumn, browser)
	println "theRowIndex=${theRowIndex}"
	assert theRowIndex >= 0
	
	String text = WebUI.getText(makeTestObject('theCellValue', "id('core')//table/tbody/tr[${theRowIndex + 1}]/td[${theColumnIndex + 1}]"))
	println "text=${text}"
	return text
}


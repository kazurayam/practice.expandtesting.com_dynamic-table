# practice.expandtesting.com_dynamic-table

## Problem to solve

See https://forum.katalon.com/t/how-handle-a-dynamic-webtable-in-katalon-studio-what-are-the-ways-we-can-access-the-table/177118/

The original poster wanted to scrape data out of a `<table>` in https://practice.expandtesting.com/dynamic-table. The subject table is intentionally designed difficult to scrape. In the table, columns and rows change their position upon page reload.

## TC1

The TC1 can scrape the "Chrome-CPU" data successfully.

https://github.com/kazurayam/practice.expandtesting.com_dynamic-table/blob/main/Scripts/TC1/Script1751381292041.groovy

I know, the source of TC1 looks ugly. I will show you Let me show you better variations later.

The 1st result:

```
nameColumnIndex=0
cpuColumnIndex=1
chromeRowIndex=3
textofChromeCPU=4.6%
```

2nd result:

```
nameColumnIndex=0
cpuColumnIndex=4
chromeRowIndex=0
textofChromeCPU=4.5%
```

3rd result:

```
nameColumnIndex=0
cpuColumnIndex=3
chromeRowIndex=0
textofChromeCPU=4.4%
```

In the results, you can find that the `cpuColumnIndex` and `chromeRowIndex` move.

## TC2

The TC1 can extract data of "Chrome-CPU" only. Next, I want to scrape the data of "Firefox-Memory" as well. How to do it?

In the TC2 introduces one more layer of function `String getCellText(String browser, String metric)`.
TC2 calls this function twice with different set of arguments.

https://github.com/kazurayam/practice.expandtesting.com_dynamic-table/blob/main/Scripts/TC2/Script1751494362663.groovy

Please note that the `getCellText` function is located inside the TC2, therefore the `getCellText` is available for the TC2 only; no other scripts can use it.

## TC3_Chrome-CPU, TC3_Firefox-Memory

Next I want to make 2 seperated Test Case scripts. One script will scrape the Chrome-CPU data. Another script will scrape the Firefox-Memory data.

I want to avoid any code duplication. So, I created 2 Groovy classes in the `Keywords` folder:

### `practiceexpandtesting.DynamicTableScraper`

https://github.com/kazurayam/practice.expandtesting.com_dynamic-table/blob/main/Keywords/practiceexpandtesting/DynamicTableScraper.groovy

### `my.TestObjectUtils`

https://github.com/kazurayam/practice.expandtesting.com_dynamic-table/blob/main/Keywords/my/TestObjectUtils.groovy

and I created 2 test case scripts that call these Groovy classes.

### `TC3_Chrome-CPU`

https://github.com/kazurayam/practice.expandtesting.com_dynamic-table/blob/main/Scripts/TC3_Chrome-CPU/Script1751497969970.groovy

### `TC3_Firefox-Memory`

https://github.com/kazurayam/practice.expandtesting.com_dynamic-table/blob/main/Scripts/TC3_Firefox-Memory/Script1751497969970.groovy

These 2 test case scripts calls the external Groovy classes. Thus code duplication is minimized.

## Custom Keyword

Finally, I want to edit a Test Case that calls the `practiceexpandtesting.DynamicTableScraper` class in the Manual view. How to make it possible?

I created a custom *Keyword* that indirectory calls the `practiceexpandtesting.DynamicTableScraper` class:

https://github.com/kazurayam/practice.expandtesting.com_dynamic-table/blob/main/Keywords/practiceexpandtesting/ScrapeDynamicTableKeyword.groovy

Please find that the `@Keyword` annotation is added here. With this annotation given, Katalon Studio now recognizes the `practiceexpandtesting.ScrapeDynamicTableKeyword` class and support it in the Manual view of the Test Case editor. See

![TC4_in_Manual_view](https://kazurayam.github.io/practice.expandtesting.com_dynamic-table/images/TC4_in_Manual_view.png)

The [TC4](https://github.com/kazurayam/practice.expandtesting.com_dynamic-table/blob/main/Scripts/TC4/Script1751500053447.groovy) uses the custom keyword:

```
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
```

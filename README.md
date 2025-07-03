# practice.expandtesting.com_dynamic-table

## Problem to solve

See https://forum.katalon.com/t/how-handle-a-dynamic-webtable-in-katalon-studio-what-are-the-ways-we-can-access-the-table/177118/

The original poster wanted to scrape data from a table in the HTML of https://practice.expandtesting.com/dynamic-table'. In the table, columns and rows change their position upon page reload.

## TC1

I would propose this code: https://github.com/kazurayam/practice.expandtesting.com_dynamic-table/blob/main/Scripts/TC1/Script1751381292041.groovy

The TC1 could get the text of cell "Chrome-CPU" in the table in the https://practice.expandtesting.com/dynamic-table'

The 1st result:

```
nameColumnIndex=0
cpuColumnIndex=1
chromeRowIndex=3
textofChromeCPU=4.6%
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

## TC2

The TC1 can extract data of "Chrome-CPU" only. Next, I want the data of "Firefox-Memory" as well. How to do it?

In the TC2 introduces one more layer of function `String getCellText(String browser, String metric)`.
The `getCellText` function is located inside the TC2. TC2 calls this function twice with different set of arguments.

https://github.com/kazurayam/practice.expandtesting.com_dynamic-table/blob/main/Scripts/TC2/Script1751494362663.groovy


## TC3_Chrome-CPU, TC3_Chrome-Memory

The TC2 calls the `getCellText` function which is located inside the TC2 itself.

Next I want to make 2 seperated Test Case scripts. One script will extract the Chrome-CUP data. Another script will extract the Firefox-Memory data. How can I do it.

I created 2 Groovy classes:

### `Keywords/my/PracticeExpandTesting`


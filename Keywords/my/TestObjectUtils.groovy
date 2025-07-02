package my

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

public class TestObjectUtils {

	static TestObject makeTestObject(String id, String xpath) {
		TestObject tobj = new TestObject(id)
		tobj.addProperty("xpath", ConditionType.EQUALS, xpath)
		return tobj
	}
}

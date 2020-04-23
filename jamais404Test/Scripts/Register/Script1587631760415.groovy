import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('http://157.26.83.82:8888/login')

WebUI.click(findTestObject('Object Repository/Page_Jamais404/a_Dont have an account  Create one'))

WebUI.setText(findTestObject('Object Repository/Page_Jamais404/input_Email_email'), 'exemple@exemple.com')

WebUI.setText(findTestObject('Page_Jamais404/input_Username_username'), 'exemple')

WebUI.setEncryptedText(findTestObject('Page_Jamais404/input_Password_password'), 'ZG2knDkKftJlNr/ofsIG4Q==')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Jamais404/input_Confirm Password_passwordConfirm'), 'ZG2knDkKftJlNr/ofsIG4Q==')

WebUI.click(findTestObject('Object Repository/Page_Jamais404/button_REGISTER'))


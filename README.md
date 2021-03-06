# SELENIUM2 EASY TEST
Automated Selenium 2 based test framework


## Features:

`Execution Server Side Framework`: Server side API to execute the test case (`TestAutomatedServer`)

`Configurable Test Suite`: Properties file configurable test Suite (`TestEngine`)

`Extendable Test Cases`: API to define custom classes to run different test case scenarios or to provide furter information (`BaseTestCase`)

`XML Test Case Definition`: XML file driven test cases definition (`XMLGroupTestCase`)

`Extendable XML Test Cases`: API to define custom XML test cases classes and run different features, security actions and so on ... Classes configurable in the XML file (`XMLGroupTestCase`)

`Command line interface`: Executable class running a the test suite and accepting a custom path for the Test Suite configuration property file (`com.selenium2.easy.test.server.Main`)

`Maven Plugin`: Maven plugin to run the execution as a maven process in the goals `test:execute` (to run the Test Suite)

`UniRest Service Connection`: UniRest Test Cases providing java and xml features to connect service without the Selenium2 WebDriver and collecting the service answer in specific result areas.


## Work in progress :

`Sunfire Maven Plugin Integration`: Sunfire Maven Plugin integration to execute post unit tests the Selenium 2 test cases and report the UI tests and performance statistics integrating the success status of the Sunfire plugin (`Sunfire-Selenium2-Plugin`)


## Status

### S.R. 1


## Maven Commands

To compile the maven project run on the root of the main maven project :

`mvn -U install clean test javadoc:jar`

To make a dry compile of the maven project without tests run on the root of the main maven project :

`mvn -U install clean javadoc:jar -Dmaven.test.skip=true`


To generate the stand-alone JAVADOC run on the root of the main maven project :

`mvn -U javadoc:jar`

For the Stand-alone Tests execution (after the build) run on the root of the main maven project :

`mvn -U test`


## License

Artistic License 2.0 (see the LICENSE file for further information)

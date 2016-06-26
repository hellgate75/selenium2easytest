# SELENIUM2 EASY TEST
Automated Selenium 2 based test framework

## Features:

`Execution Server Side Framework`: Server side API to execute the test case (TestAutomatedServer)

`Configurable Test Suite`: Properties file configurable test Suite (TestEngine)

`Extendable Test Cases`: API to define custom classes to run different test case scenarios or to provide furter information (BaseTestCase)

`XML Test Case Definition`: XML file driven test cases definition (XMLGroupTestCase)

`Extendable XML Test Cases`: API to define custom XML test cases classes and run different features, security actions and so on ... Classes configurable in the XML file (XMLGroupTestCase)

`Command line interface`: Executable class running a the test suite and accepting a custom path for the Test Suite configuration property file (com.selenium2.easy.test.server.Main)


## Work in progress :

`Maven Plugin`: Maven plugin to run the execution as a maven process in the goals test_compile:connect (to run the Test Suite) and the test:disconnect (to dispose and collect the maven statistics from the engine)

`Sunfire Maven Plugin Integration`: Sunfire Maven Plugin integration to execute post unit tests the Selenium 2 test cases and report the UI tests and performance statistics integrating the success status of the Sunfire plugin (Sunfire-Selenium2-Plugin)

To compile the maven project run on the root :
`mvn -U -up install clean test javadoc:jar`


## Status

(** UNDER DEVELOPMENT **)


## Maven Commands

Standalone Tests execution (after the build) :

RUN: mvn -U -up test


## License

Artistic License 2.0 (see the LICENSE file for further information)
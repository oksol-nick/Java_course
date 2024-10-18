package io.cucumber.skeleton;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("io.cucumber.skeleton")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "io.cucumber.skeleton")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, summary, json:target/reports/cucumber-reports/cucumber.json, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
//@IncludeTags("myTag")
public class RunCucumberTest {
}

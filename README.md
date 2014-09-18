concordion-executeonlyif-extension
==================================

Determines whether child test should be executed or not.  Useful if parts of a specification cannot be executed in all environments a test suite may run against.

Requires:

1. xmlns:ext="urn:concordion-extensions:2010" added to the html so concordion can call the extension from the specification

  <code>&lt;html xmlns:c="http://www.concordion.org/2007/concordion" xmlns:ext="urn:concordion-extensions:2010"&gt;</code>

2. Place any concordion commands that you wish to conditionally execute as children of the executeOnlyIf command

  <code>
  // Specification
	&lt;div ext:executeOnlyIf="shouldNotExecute()"&gt;
		&lt;p&gt;When I google "&lt;span c:execute="searchFor(#TEXT)"&gt;6 * 9&lt;/span&gt;" the answer should be "&lt;span c:assertEquals="getCalculatorResult()"&gt;42&lt;/span&gt;".&lt;/p&gt;
	&lt;/div&gt;

  // Fixture
	public boolean shouldNotExecute() {
		return false;
	}
  </code>

3. Optionally you can use the Embed extension to give a reason you haven't excuted part of the spec

  <code>
  // Specification
  &lt;span ext:embed="getNotExecuteReason()"&gt;&lt;/span&gt;

  // Fixture
  public String getNotExecuteReason() {
		String msg = "";

		msg += "<p style=\"background-color: #FFCC99; padding: 8px; border: 1px solid #FF6600; margin: 10px 0px 10px 0px; font-weight: bold\">";
		msg += "The following step has been skipped to prove we can.";
		msg += "</p>";

		return msg;
	}
  </code>

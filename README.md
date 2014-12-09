[![Build Status](https://travis-ci.org/concordion/concordion-executeonlyif-extension.svg?branch=master)](https://travis-ci.org/concordion/concordion-executeonlyif-extension)

This [Concordion](http://www.concordion.org) extension provides the capability to embed screenshots in the output specification.

The [demo project](http://github.com/concordion/concordion-executeonlyif-extension-demo) demonstrates this extension.

# Introduction

Determines whether child test should be executed or not.  Useful if parts of a specification cannot be executed in all environments a test suite may run against.

# Usage

Requires:

1. xmlns:ext="urn:concordion-extensions:2010" added to the html so concordion can call the extension from the specification

```html
  <code><html xmlns:c="http://www.concordion.org/2007/concordion" xmlns:ext="urn:concordion-extensions:2010"></code>
```

2. Place any concordion commands that you wish to conditionally execute as children of the executeOnlyIf command

  // Specification
```html
	<div ext:executeOnlyIf="shouldNotExecute()">
		<p>When I google "<span c:execute="searchFor(#TEXT)">6 * 9</span>" the answer should be "<span c:assertEquals="getCalculatorResult()">42</span>".</p>
	</div>
```
```java
	public boolean shouldNotExecute() {
		return false;
	}
```

3. Optionally you can use the Embed extension to give a reason you haven't excuted part of the spec

  // Specification
```html
  <span ext:embed="getNotExecuteReason()"></span>
```
// Fixture
```code
  public String getNotExecuteReason() {
		String msg = "";

		msg += "<p style=\"background-color: #FFCC99; padding: 8px; border: 1px solid #FF6600; margin: 10px 0px 10px 0px; font-weight: bold\">";
		msg += "The following step has been skipped to prove we can.";
		msg += "</p>";

		return msg;
	}
```

# Further info

* [API](http://concordion.github.io/concordion-executeonlyif-extension/api/index.html)
* [Demo project](http://github.com/concordion/concordion-executeonlyif-extension-demo)

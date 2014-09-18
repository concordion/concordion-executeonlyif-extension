package org.concordion.ext.demo;

import org.concordion.api.FailFast;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.Extension;
import org.concordion.api.extension.Extensions;
import org.concordion.ext.TimestampFormatterExtension;
import org.concordion.ext.TranslatorExtension;
import org.concordion.ext.driver.web.Browser;
import org.concordion.ext.driver.web.SeleniumExceptionMessageTranslator;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A base class for Google search tests that opens up the Google site at the Google search page, and closes the browser once the test is complete.
 */
@RunWith(ConcordionRunner.class)
@Extensions({ TimestampFormatterExtension.class })
@FailFast
public abstract class AceptanceTest {

	private Browser browser = null;
	private final boolean logWebDriverEvents;
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Extension
	public ConcordionExtension seleniumMessageTranslator = new TranslatorExtension(new SeleniumExceptionMessageTranslator());

	public AceptanceTest() {
		this(false);
	}

	public AceptanceTest(final boolean logWebDriverEvents) {
		this.logWebDriverEvents = logWebDriverEvents;
	}

	public Logger getLogger() {
		return logger;
	}

	public boolean isBrowserOpen() {
		return browser != null;
	}

	public Browser getBrowser() {
		if (browser == null) {
			browser = new Browser();

			if (logWebDriverEvents) {
				browser.addLogger();
			}
		}

		return browser;
	}

	public void closeBrowser() {
		if (browser == null) {
			return;
		}

		try {
			browser.quit();
		} catch (Exception ex) {
			getLogger().warn("Exception attempting to quit the browser" + ex);
		}

		browser = null;
	}

	@Before
	public void startUpTest() {
		logger.info("Initialising the acceptance test class {} on thread {}", this.getClass().getSimpleName(), Thread.currentThread().getName());
	}

	@After
	public void tearDownTest() {
		closeBrowser();

		logger.info("Tearing down the acceptance test class on thread {}", Thread.currentThread().getName());
	}
}

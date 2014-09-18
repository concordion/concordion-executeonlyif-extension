package org.concordion.ext.demo;

import org.concordion.api.extension.Extensions;
import org.concordion.ext.EmbedExtension;
import org.concordion.ext.ExecuteOnlyIfExtension;
import org.concordion.ext.driver.page.GoogleResultsPage;
import org.concordion.ext.driver.page.GoogleSearchPage;

/**
 * A fixture class for the ExecuteOnlyIf.html specification.
 * <p>
 * This adds the ExecuteOnlyIf Extension to Concordion to conditionally execute parts of the specification.
 * 
 * Also uses Embed extension
 */
@Extensions({ EmbedExtension.class, ExecuteOnlyIfExtension.class })
public class ExecuteOnlyIfDemo extends AceptanceTest {

	private GoogleSearchPage searchPage;
	private GoogleResultsPage resultsPage;

	/**
	 * Searches for the specified topic, and waits for the results page to load.
	 */
	public void searchFor(final String topic) {
		searchPage = new GoogleSearchPage(getBrowser().getDriver());
		resultsPage = searchPage.searchFor(topic);
	}

	public String getNotExecuteReason() {
		String msg = "";

		msg += "<p style=\"background-color: #FFCC99; padding: 8px; border: 1px solid #FF6600; margin: 10px 0px 10px 0px; font-weight: bold\">";
		msg += "The following step has been skipped to prove we can.";
		msg += "</p>";

		return msg;

	}

	public boolean shouldNotExecute() {
		return false;
	}

	public boolean shouldExecute() {
		return true;
	}

	/**
	 * Returns the result from Google calculation.
	 */
	public String getCalculatorResult() {
		return resultsPage.getCalculatorResult();
	}
}

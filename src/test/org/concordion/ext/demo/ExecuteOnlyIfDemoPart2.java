package org.concordion.ext.demo;

import org.concordion.api.extension.Extensions;
import org.concordion.ext.EmbedExtension;
import org.concordion.ext.ExecuteOnlyIfExtension;

/**
 * A fixture class for the ExecuteOnlyIf.html specification.
 * <p>
 * This adds the ExecuteOnlyIf Extension to Concordion to conditionally execute parts of the specification.
 * 
 * Also uses Embed extension
 */
@Extensions({ EmbedExtension.class, ExecuteOnlyIfExtension.class })
public class ExecuteOnlyIfDemoPart2 extends AceptanceTest {

	/**
	 * Searches for the specified topic, and waits for the results page to load.
	 */
	public void searchFor(final String topic) {
		throw new RuntimeException("Should not get here!");
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
}

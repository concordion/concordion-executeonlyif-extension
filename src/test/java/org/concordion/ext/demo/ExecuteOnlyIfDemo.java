package org.concordion.ext.demo;

import org.concordion.api.FailFast;
import org.concordion.api.extension.Extensions;
import org.concordion.ext.EmbedExtension;
import org.concordion.ext.ExecuteOnlyIfExtension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

/**
 * A fixture class for the ExecuteOnlyIf.html specification.
 * <p>
 * This adds the ExecuteOnlyIf Extension to Concordion to conditionally execute parts of the specification.
 * 
 * Also uses Embed extension
 */
@RunWith(ConcordionRunner.class)
@Extensions({ EmbedExtension.class, ExecuteOnlyIfExtension.class })
@FailFast
public class ExecuteOnlyIfDemo {
	private boolean shouldExecute = false;
	/**
	 * Searches for the specified topic, and waits for the results page to load.
	 */
	public void setShouldExecute(final String value) {
		shouldExecute = Boolean.parseBoolean(value);
	}

	public String getNotExecuteReason() {
		String msg = "";

		if(!shouldExecute) {
			msg += "<p style=\"background-color: #FFCC99; padding: 8px; border: 1px solid #FF6600; margin: 10px 0px 10px 0px; font-weight: bold\">";
			msg += "The following step has been skipped to prove we can.";
			msg += "</p>";
		}
		
		return msg;
	}

	public boolean shouldExecute() {
		return shouldExecute;
	}
	
	public String getCalculatorResult() {
		return ((Integer)(6*7)).toString();
	}
}

package org.concordion.ext;


import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.ext.executeOnlyIf.ExecuteOnlyIfCommand;

/**
 * Determines whether child test should be executed or not.
 */
public class ExecuteOnlyIfExtension implements ConcordionExtension {

	public static final String EXTENSION_NAMESPACE = "urn:concordion-extensions:2010";
	public static final String COMMAND_NAME = "executeOnlyIf";

	private final ExecuteOnlyIfCommand executeOnlyIf = new ExecuteOnlyIfCommand();

	@Override
	public void addTo(final ConcordionExtender concordionExtender) {
		concordionExtender.withCommand(EXTENSION_NAMESPACE, COMMAND_NAME, executeOnlyIf);
	}
}

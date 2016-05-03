package org.concordion.ext.executeOnlyIf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.concordion.api.AbstractCommand;
import org.concordion.api.CommandCall;
import org.concordion.api.CommandCallList;
import org.concordion.api.Element;
import org.concordion.api.Evaluator;
import org.concordion.api.Result;
import org.concordion.api.ResultRecorder;
import org.concordion.api.listener.AssertListener;
import org.concordion.internal.InvalidExpressionException;

public class ExecuteOnlyIfCommand extends AbstractCommand {

	private final List<AssertListener> listeners = new ArrayList<AssertListener>();

	public void addAssertListener(final AssertListener listener) {
		listeners.add(listener);
	}

	public void removeAssertListener(final AssertListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void verify(final CommandCall commandCall, final Evaluator evaluator, final ResultRecorder resultRecorder) {
		Element element = commandCall.getElement();
		String expression = commandCall.getExpression();
		Object result = evaluator.evaluate(expression);
		
		if (result != null && result instanceof Boolean) {
			CommandCallList childCommands = commandCall.getChildren();

			if ((Boolean) result) {
				// Execute all child commands and leaving displaying and reporting results up to them
				childCommands.setUp(evaluator, resultRecorder);
				childCommands.execute(evaluator, resultRecorder);
				childCommands.verify(evaluator, resultRecorder);
			} else {
				// Mark each child command as ignored and report as an ignored test
				for (int i = 0; i < childCommands.size(); i++) {
					resultRecorder.record(Result.IGNORED);
				}
				element.addStyleClass("ignored");
			}
		} else {
			throw new InvalidExpressionException("Expression '" + expression + "' did not produce a boolean result.");
		}
	}
}

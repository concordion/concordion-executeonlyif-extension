package org.concordion.ext.executeOnlyIf;

import org.concordion.api.AbstractCommand;
import org.concordion.api.CommandCall;
import org.concordion.api.CommandCallList;
import org.concordion.api.Element;
import org.concordion.api.Evaluator;
import org.concordion.api.Result;
import org.concordion.api.ResultRecorder;
import org.concordion.api.listener.AssertFailureEvent;
import org.concordion.api.listener.AssertListener;
import org.concordion.api.listener.AssertSuccessEvent;
import org.concordion.internal.InvalidExpressionException;
import org.concordion.internal.util.Announcer;

public class ExecuteOnlyIfCommand extends AbstractCommand {

	private final Announcer<AssertListener> listeners = Announcer.to(AssertListener.class);

	public void addAssertListener(final AssertListener listener) {
		listeners.addListener(listener);
	}

	public void removeAssertListener(final AssertListener listener) {
		listeners.removeListener(listener);
	}

	@Override
	public void verify(final CommandCall commandCall, final Evaluator evaluator, final ResultRecorder resultRecorder) {

		Element element = commandCall.getElement();
		String expression = commandCall.getExpression();
		Object result = evaluator.evaluate(expression);
		if (result != null && result instanceof Boolean) {
			if ((Boolean) result) {
				CommandCallList childCommands = commandCall.getChildren();
				childCommands.setUp(evaluator, resultRecorder);
				childCommands.execute(evaluator, resultRecorder);
				childCommands.verify(evaluator, resultRecorder);

				announceSuccess(element);
				element.addStyleClass("success");
				resultRecorder.record(Result.SUCCESS);
			} else {
				announceSuccess(element);
				element.addStyleClass("ignored");
				resultRecorder.record(Result.IGNORED);
			}
		} else {
			throw new InvalidExpressionException("Expression '" + expression + "' did not produce a boolean result.");
		}
	}

	protected void announceSuccess(final Element element) {
		listeners.announce().successReported(new AssertSuccessEvent(element));
	}

	protected void announceFailure(final Element element, final String expected, final Object actual) {
		listeners.announce().failureReported(new AssertFailureEvent(element, expected, actual));
	}
}

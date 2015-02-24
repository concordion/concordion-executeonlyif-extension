package spec.concordion.ext.executeOnlyIf;

import org.concordion.api.ExpectedToFail;
import org.concordion.api.extension.Extensions;
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
@Extensions({ ExecuteOnlyIfExtension.class })
@ExpectedToFail
public class ExecuteOnlyIf {

	public boolean shouldExecute() {
		return true;
	}
	
	public boolean shouldNotExecute() {
		return false;
	}	
}

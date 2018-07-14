package spec.concordion.ext.executeOnlyIf;

import org.concordion.api.BeforeSuite;
import org.concordion.api.ExpectedToFail;
import org.concordion.api.extension.Extensions;
import org.concordion.ext.ExecuteOnlyIfExtension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.concordion.internal.extension.ExtensionChecker;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

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

    @BeforeSuite
    void dontRunConcordionExtensionVersionCheck() throws Exception {
        Field field = ExtensionChecker.class.getDeclaredField("checked");
        field.setAccessible(true);
        field.set(null, true);
    }

	public boolean shouldExecute() {
		return true;
	}
	
	public boolean shouldNotExecute() {
		return false;
	}	
}

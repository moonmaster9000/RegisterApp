package cashregister.tests.usecases;

import cashregister.domain.usecases.exceptions.InvalidRequest;
import cashregister.domain.values.ValidationError;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class UseCaseAssertions {
    public static void assertThrowsValidationError(Runnable f, ValidationError error) {
        try {
            f.run();
            fail("expected lambda to throw exception, but didn't.");
        } catch (InvalidRequest invalidRequest){
            assertThat(invalidRequest.getErrors(), hasItem(error));
        }
    }
}

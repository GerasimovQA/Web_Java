package GlobalSearch;

import junitparams.Parameters;
import org.junit.Test;

public class GlobalSearchFunctionalTest extends GlobalSearchFunctionalLogic {

    @Test   //13.1
    @Parameters(value = {
            "Акул",
            "Врач",
    })
    public void searchsumResultTest(String Request) {
        sumResult(Request);
    }

}








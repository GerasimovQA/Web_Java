package Permissions.WorkerProfile;

import junitparams.Parameters;
import org.junit.Test;

public class WorkerProfileTest extends WorkerProfileLogic {

    @Test   //9.50
    @Parameters(value = {
            " SuperAdmin",

            " Admin",

            " Spec",
    })
    public void viewWorkerProfileTest(String Role) {
        viewWorkerProfile(Role);

    }


}





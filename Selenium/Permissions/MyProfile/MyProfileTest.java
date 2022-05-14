package Permissions.MyProfile;

import junitparams.Parameters;
import org.junit.Test;

public class MyProfileTest extends MyProfileLogic {

    @Test   //9.30
    @Parameters(value = {
            " SuperAdmin",

            " Admin",

            " Spec",
    })
    public void viewMyProfileTest(String Role) {
        viewMyProfile(Role);

    }

    @Test   //9.31
    @Parameters(value = {
            " SuperAdmin",

            " Admin",

            " Spec",
    })
    public void editMyProfileTest(String Role) {
        editMyProfile(Role);
    }

    @Test   //9.32
    @Parameters(value = {
            " SuperAdmin",

            " Admin",

            " Spec",
    })
    public void editMyWorkplaceTest(String Role) {
        editMyWorkplace(Role);
    }

    @Test   //9.33
    @Parameters(value = {
            " SuperAdmin",

            " Admin",

            " Spec",
    })
    public void editMyServicesTest(String Role) {
        editMyServices(Role);
    }

    @Test   //9.33
    @Parameters(value = {
            " SuperAdmin",

            " Admin",

            " Spec",
    })
    public void editMyDataTest(String Role) {
        editMyData(Role);
    }
}





package User.ListUsers;

import org.junit.Test;

public class ListUsersTest extends ListUsersLogic {

    @Test   //11.1
    public void sumUsersTest() {
        sumUsers();
    }

    @Test   //11.2
    public void viewUserProfileTest() {
        viewUserProfile();
    }

    @Test   //11.3
    public void listElementsTest() {
        listElements();
    }

    @Test   //11.4
    public void blockInfoUserTest() {
        blockInfoUser();
    }

    @Test   //11.5
    public void filterOrgTest() {
        filterOrg();
    }

    @Test   //11.6
    public void filterPostTest() {
        filterPost();
    }

    @Test   //11.7
    public void filterRoleTest() {
        filterRole();
    }

    @Test   //11.8
    public void filterSpecialtyTest() {
        filterSpecialty();
    }

    @Test   //11.9
    public void filterStatusWorkTest() {
        filterStatusWork();
    }

    @Test   //11.10
    public void filterStatusSystemTest() {
        filterStatusSystem();
    }

    @Test   //11.11
    public void filterStatusOnlineTest() {
        filterStatusOnline();
    }

    @Test   //11.12
    public void filterGlobalRoleTest() {
        filterGlobalRole();
    }

    @Test   //11.13
    public void searchSecondNameTest() {
        searchSecondName();
    }


    @Test   //11.14
    public void searchFirstNameTest() {
        searchFirstName();
    }

    @Test   //11.15
    public void searchMiddleNameTest() {
        searchMiddleName();
    }


    @Test   //11.16
    public void searchEmailTest() {
        searchEmail();
    }

    @Test   //11.17
    public void searchPhoneTest() {
        searchPhone();
    }

    @Test   //11.18
    public void searchLoginTest() {
        searchLogin();
    }


}
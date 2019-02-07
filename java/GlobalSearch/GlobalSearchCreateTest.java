package GlobalSearch;

import org.junit.Test;

public class GlobalSearchCreateTest extends GlobalSearchCreateLogic {
    @Test   //10.1
    public void searchFullUserSecondName() {
        searchUser(SecondName);
    }

    @Test   //10.2
    public void searchFullUserFirstName() {
        searchUser(FirstName);
    }

    @Test   //10.3
    public void searchFullUserMiddleName() {
        searchUser(MiddleName);
    }

    @Test   //10.4
    public void searchUserID() {
        searchUser(UserID);
    }

    @Test   //10.5
    public void searchOrgFullName() {
        searchOrg(NameOrg);
    }

    @Test   //10.6
    public void searchOrgShortName() {
        searchOrg(AbbrOrg);
    }

    @Test   //10.7
    public void searchOrgID() {
        searchOrg(OrgID);
    }

    @Test   //10.8
    public void searchOrgService() {
        searchOrg("Проверка зрения");
    }

    @Test   //10.9
    public void searchServiceFullName() {
        searchService(NameService);
    }

    @Test   //10.10
    public void searchServiceVendor() {
        searchService(VendorService);
    }

    @Test   //10.11
    public void searchServiceID() {
        searchService(ServiceID);
    }

    @Test   //10.12
    public void searchSpecialtyFullName() {
        searchSpecialty(NameSpecialty);
    }

    @Test   //10.13
    public void searchSpecialtyID() {
        searchSpecialty(SpecialtyID);
    }
}





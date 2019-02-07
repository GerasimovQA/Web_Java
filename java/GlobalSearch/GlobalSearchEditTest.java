package GlobalSearch;

import org.junit.Test;

public class GlobalSearchEditTest extends GlobalSearchEditLogic {
    @Test   //11.1
    public void searchFullUserNewSecondName() {
        searchEditUser(NewSecondName);
    }

    @Test   //11.2
    public void searchFullUserOldSecondName() {
        searchOldEditUser(SecondName);
    }

    @Test   //11.3
    public void searchFullUserNewFirstName() {
        searchEditUser(NewFirstName);
    }

    @Test   //11.4
    public void searchFullUserOldFirstName() {
        searchOldEditUser(FirstName);
    }

    @Test   //11.5
    public void searchFullUserNewMiddleName() {
        searchEditUser(NewMiddleName);
    }

    @Test   //11.6
    public void searchFullUserOldMiddleName() {
        searchOldEditUser(MiddleName);
    }

    @Test   //11.7
    public void searchFullUserID() {
        searchEditUser(UserID);
    }

    @Test   //11.8
    public void searchOrgEditNewFullName() {
        searchEditOrg(NewNameOrg);
    }

    @Test   //11.9
    public void searchOrgEditOldFullName() {
        searchOldEditOrg(NameOrg);
    }

    @Test   //11.10
    public void searchOrgEditShortName() {
        searchEditOrg(NewAbbrOrg);
    }

    @Test   //11.11
    public void searchOrgEditOldShortName() {
        searchOldEditOrg(AbbrOrg);
    }

    @Test   //11.12
    public void searhOrgEditID() {
        searchEditOrg(OrgID);
    }

    @Test   //11.13
    public void searchOrgEditService() {
        searchEditOrg("Удаление татуировки");
    }

    @Test   //11.14
    public void searchOrgEditOldService() {
        searchOldEditOrg("Промывание мозгов");
    }

    @Test   //11.15
    public void searchServiceNewFullName() {
        searchNewService(NewNameService);
    }

    @Test   //11.16
    public void searchServiceOldFullName() {
        searchOldService(NameService);
    }

    @Test   //11.17
    public void searchServiceNewVendor() {
        searchNewService(NewVendorService);
    }

    @Test   //11.18
    public void searchServiceOldVendor() {
        searchOldService(VendorService);
    }

    @Test   //11.19
    public void searchServiceID() {
        searchNewService(ServiceID);
    }

    @Test   //11.20
    public void searchSpecialtyNewFullName() {
        searchNewSpecialty(NewNameSpecialty);
    }

    @Test   //11.21
    public void searchSpecialtyOldFullName() {
        searchOldSpecialty(NameSpecialty);
    }

    //    @Test   //11.22
//    public void searchSpecialtyNewVendor() {
//        searchNewSpecialty(NewVendorService);
//    }
//
//    @Test   //11.23
//    public void searchSpecialtyOldVendor() {
//        searchOldSpecialty(VendorService);
//    }
//
    @Test   //11.22
    public void searchSpecialtyID() {
        searchNewSpecialty(SpecialtyID);
    }
}








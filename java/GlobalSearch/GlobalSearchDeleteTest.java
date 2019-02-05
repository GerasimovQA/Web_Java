package GlobalSearch;

import Global.EnvironmentGlobalSearch;
import org.junit.Test;

public class GlobalSearchDeleteTest extends GlobalSearchDeleteLogic {
    @Test   //12.1
    public void searchFullUserNewSecondName() {
        searchDeleteUser(NewSecondName);
    }

    @Test   //12.2
    public void searchFullUserNewFirstName() {
        searchDeleteUser(NewFirstName);
    }

    @Test   //12.3
    public void searchFullUserNewMiddleName() {
        searchDeleteUser(NewMiddleName);
    }

    @Test   //12.4
    public void searchFullUserID() {
        searchDeleteUser(EnvironmentGlobalSearch.UserID1);
    }


    @Test   //12.5
    public void searchOrgEditNewFullName() {
        searchDeleteOrg(NewNameOrg);
    }

    @Test   //12.6
    public void searchOrgEditShortName() {
        searchDeleteOrg(NewAbbrOrg);
    }

    @Test   //12.7
    public void searhOrgEditID() {
        searchDeleteOrg(EnvironmentGlobalSearch.OrganizationID);
    }

    @Test   //12.8
    public void searchOrgDeleteService() {
        searchDeleteOrg("Удаление татуировки");
    }


    @Test   //12.9
    public void searchServiceNewFullName() {
        searchDeleteService(NewNameService);
    }

    @Test   //12.10
    public void searchServiceNewVendor() {
        searchDeleteService(NewVendorService);
    }

    @Test   //12.11
    public void searchServiceID() {
        searchDeleteService(EnvironmentGlobalSearch.ServiceID1);
    }

    @Test   //12.12
    public void searchSpecialtyNewFullName() {
        searchDeleteSpecialty(NewNameSpecialty);
    }

    @Test   //12.13
    public void searchSpecialtyID() {
        searchDeleteSpecialty(EnvironmentGlobalSearch.SpecialtyID1);
    }
}








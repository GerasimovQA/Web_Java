package listorganizations;

import org.junit.Test;

public class ListOrganizationTest extends ListOrganizationLogic {

    @Test   //8.1
    public void nameOrgTest() {
        nameOrg();
    }

    @Test   //8.2
    public void nameAbbrOrgTest() {
        nameAbbrOrg();
    }

    @Test   //8.3
    public void directorOrgTest() {
        directorOrg();
    }

    @Test   //8.4
    public void buttonActionsOrgTest() {
        buttonActionsOrg();
    }

     @Test   //8.5
    public void additionalOrgTest() {
         additionalOrg();
    }






    @Test   //8.7
    public void menuActionEditlOrgTest() {
        menuActionEditlOrg();
    }






    @Test   //8.9
    public void menuActionDeletelOrgTest() {
        menuActionDeletelOrg();
    }

    @Test   //8.10
    public void menuActionCancelDeletelOrgTest() {
        menuActionCancelDeletelOrg();
    }

    @Test   //8.11
    public void menuActionDeletelOrgWithChildTest() {
        menuActionDeletelOrgWithChild();
    }

    @Test   //8.12
    public void additionaViewOrgTest() {
        additionaViewOrg();
    }

    @Test   //8.13
    public void filterStructureOrgTest() {
        filterStructurelOrg();
    }

    @Test   //8.14
    public void filterSearchOrgTest() {
        filterSearchlOrg();
    }

    @Test   //8.15
    public void buttonAddOrgTest() {
        buttonAddOrg();
    }
}

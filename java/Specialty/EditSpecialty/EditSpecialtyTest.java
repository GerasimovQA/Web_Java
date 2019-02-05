package Specialty.EditSpecialty;

import Global.EnvironmentIntegration;
import Global.GlobalPage;
import Integration.IntegrationAPI;
import junitparams.Parameters;
import org.junit.Test;

public class EditSpecialtyTest extends EditSpecialtyLogic {
    private EditSpecialtyAPI editSpecialtyAPI = new EditSpecialtyAPI();

    private String Token = null;
    private String UserID = null;
    private String UserID1 = null;
    private String UserID2 = null;
    private String UserID3 = null;
    private String OrgID = null;
    private String ServicesSectionID = null;
    private String ServiceID = null;
    private String ServiceID1 = null;
    private String ServiceID2 = null;
    private String ServiceID3 = null;
    private String SpecialtyID = null;

    @Test   //2.1
    @Parameters(value = {
            "Тестовый Патологоанатом | Работает работу | neurosurgeon | 000000000000000000000001 |" +

                    "Анатомист 2 | Работает работу работая | dental_surgeon | 000000000000000000000002",

            "Ноголом | ломает ноги | neurosurgeon | 000000000000000000000001 |" +

                    "Ногоправ | Чинит ноги | dental_surgeon | 000000000000000000000001",

            "Рукоправ | правит | neurosurgeon | 000000000000000000000002 |" +

                    "Руколом | Ломает | dental_surgeon | 000000000000000000000001",

    })
    public void editSpecialtyTest(String NameSpecialty1, String Description1, String Icon1, String Parent1,

                                  String NewNameSpecialty1, String NewDescription1, String NewIcon1, String NewParent1) {
        Token = editSpecialtyAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        SpecialtyID = editSpecialtyAPI.createSpecialtyAPI(Token, NameSpecialty1, Description1, Icon1, Parent1);
        editSpecialty(SpecialtyID, NameSpecialty1, NewNameSpecialty1, NewDescription1, NewIcon1, NewParent1);
    }

    @Test   //2.2
    @Parameters(value = {
            "Отмены в специальности | Работает работу | neurosurgeon | 000000000000000000000001 |" +

                    "Этого не должно быть видно | Работает работу работая | dental_surgeon | 000000000000000000000002",


    })
    public void cancelEditSpecialtyTest(String NameSpecialty1, String Description1, String Icon1, String Parent1,

                                        String NewNameSpecialty1, String NewDescription1, String NewIcon1, String NewParent1) {
        Token = editSpecialtyAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        SpecialtyID = editSpecialtyAPI.createSpecialtyAPI(Token, NameSpecialty1, Description1, Icon1, Parent1);
        cancelEditSpecialty(SpecialtyID, NameSpecialty1, NewNameSpecialty1, Description1, NewDescription1, NewIcon1, NewParent1);
    }

    @Test   //2.3
    @Parameters(value = {
            "Удаляемая специальность | Удаляется | dental_surgeon | 000000000000000000000001",
})
    public void deleteSpecialtyTest(String NameSpecialty1, String Description1, String Icon1, String Parent1) {
        Token = editSpecialtyAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        SpecialtyID = editSpecialtyAPI.createSpecialtyAPI(Token, NameSpecialty1, Description1, Icon1, Parent1);
        deleteSpecialty(SpecialtyID, NameSpecialty1);
    }

    @Test   //2.4
    @Parameters(value = {
            "НеУничтожаемая специальность | НеУничтожается | dental_surgeon | 000000000000000000000001",
})
    public void cancelDeleteSpecialtyTest(String NameSpecialty1, String Description1, String Icon1, String Parent1) {
        Token = editSpecialtyAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        SpecialtyID = editSpecialtyAPI.createSpecialtyAPI(Token, NameSpecialty1, Description1, Icon1, Parent1);
        cancelDeleteSpecialty(SpecialtyID, NameSpecialty1);
    }
}

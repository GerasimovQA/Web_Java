package Organization.EditCabinet;

import Autorization.AutorizationAPI;
import Global.GlobalPage;
import Organization.CreateCabinet.CreateCabinetAPI;
import junitparams.Parameters;
import org.junit.Test;


public class EditCabinetTest extends EditCabinetLogic {
    private CreateCabinetAPI createCabinetAPI = new CreateCabinetAPI();

    private String Token = null;
    private String UserID = null;
    private String CabinetID = null;
    private String WorkplaceID = null;

    @Test   //17.1
    @Parameters(value = {
            " 5be180eb5a4c8615d6881f25 | 3 название кабинета №3 | Описание кабинета №3 | 3 Наименование рабочего места |" +
                    "Описание рабочего места 3 |" +

                    "Новое название кабинета №3, Новое описание кабинета номер 3 | Новое наименование рабочего места №3 | " +
                    " Второе рабочее место №3",

            " 5be180eb5a4c8615d6881f25 | 4 название кабинета №4 | Описание кабинета №4 | 4 Наименование рабочего места |" +
                    "Описание рабочего места 4 |" +

                    "Новое название кабинета №4, Новое описание кабинета номер 4 | Новое наименование рабочего места №4 | " +
                    " Второе рабочее место №4",
    })
    public void editCabinetTest(String Organization, String NameCabinet, String DescriptionCabinet, String NameWorkplace,
                                String DescriptionWorkplace,

                                String NewNameCabinet, String NewDescriptionCabinet, String NewNameWorkplace,
                                String NewDescriptionWorkplace) {
        Token = createCabinetAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        CabinetID =  createCabinetAPI.createCabinetAPI(Token, Organization, NameCabinet, DescriptionCabinet);
        createCabinetAPI.createWorkplaceAPI(Token, CabinetID, NameWorkplace, DescriptionWorkplace);
        editCabinet(CabinetID, NameCabinet, NameWorkplace, NewNameCabinet, NewDescriptionCabinet, NewNameWorkplace, NewDescriptionWorkplace);
    }

    @Test   //17.2
    @Parameters(value = {
            " 5be180eb5a4c8615d6881f25 | 5 название кабинета №5 | Описание кабинета №5 | 5 Наименование рабочего места |" +
                    "Описание рабочего места 5 ",

            " 5be180eb5a4c8615d6881f25 | 6 название кабинета №6 | Описание кабинета №6 | 6 Наименование рабочего места |" +
                    "Описание рабочего места 6 ",
    })
    public void deleteWorkplaceEditTest(String Organization, String NameCabinet, String DescriptionCabinet, String NameWorkplace,
                                      String DescriptionWorkplace) {
        Token = createCabinetAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        CabinetID =  createCabinetAPI.createCabinetAPI(Token, Organization, NameCabinet, DescriptionCabinet);
        WorkplaceID = createCabinetAPI.createWorkplaceAPI(Token, CabinetID, NameWorkplace, DescriptionWorkplace);
        deleteWorkplaceEdit(CabinetID, NameCabinet, NameWorkplace);
    }

    @Test   //17.3
    @Parameters(value = {
            " 5be180eb5a4c8615d6881f25 | 7 название кабинета №7 | Описание кабинета №7 | 7 Наименование рабочего места |" +
                    "Описание рабочего места 7 ",

            " 5be180eb5a4c8615d6881f25 | 8 название кабинета №8 | Описание кабинета №8 | 8 Наименование рабочего места |" +
                    "Описание рабочего места 8 ",
    })
    public void deleteWorkplaceListTest(String Organization, String NameCabinet, String DescriptionCabinet, String NameWorkplace,
                                        String DescriptionWorkplace) {
        Token = createCabinetAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        CabinetID =  createCabinetAPI.createCabinetAPI(Token, Organization, NameCabinet, DescriptionCabinet);
        WorkplaceID = createCabinetAPI.createWorkplaceAPI(Token, CabinetID, NameWorkplace, DescriptionWorkplace);
        deleteWorkplaceList(NameCabinet, NameWorkplace);
    }

    @Test   //17.4
    @Parameters(value = {
            " 5be180eb5a4c8615d6881f25 | 9 название кабинета №9 | Описание кабинета №9 | 9 Наименование рабочего места |" +
                    "Описание рабочего места 9 ",

            " 5be180eb5a4c8615d6881f25 | 10 название кабинета №10 | Описание кабинета №10 | 10 Наименование рабочего места |" +
                    "Описание рабочего места 10 ",
    })
    public void deleteCabinetLEditTest(String Organization, String NameCabinet, String DescriptionCabinet, String NameWorkplace,
                                      String DescriptionWorkplace) {
        Token = createCabinetAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        CabinetID =  createCabinetAPI.createCabinetAPI(Token, Organization, NameCabinet, DescriptionCabinet);
        WorkplaceID = createCabinetAPI.createWorkplaceAPI(Token, CabinetID, NameWorkplace, DescriptionWorkplace);
        deleteCabinetEdit(CabinetID, NameCabinet, NameWorkplace);
    }

    @Test   //17.5
    @Parameters(value = {
            " 5be180eb5a4c8615d6881f25 | 11 название кабинета №11 | Описание кабинета №11 | 11 Наименование рабочего места |" +
                    "Описание рабочего места 11 ",

            " 5be180eb5a4c8615d6881f25 | 12 название кабинета №12 | Описание кабинета №12 | 12 Наименование рабочего места |" +
                    "Описание рабочего места 12 ",
    })
    public void deleteCabinetListTest(String Organization, String NameCabinet, String DescriptionCabinet, String NameWorkplace,
                                      String DescriptionWorkplace) {
        Token = createCabinetAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        CabinetID =  createCabinetAPI.createCabinetAPI(Token, Organization, NameCabinet, DescriptionCabinet);
        WorkplaceID = createCabinetAPI.createWorkplaceAPI(Token, CabinetID, NameWorkplace, DescriptionWorkplace);
        deleteCabinetList(NameCabinet, NameWorkplace);
    }
}

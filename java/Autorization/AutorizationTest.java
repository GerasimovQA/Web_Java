package Autorization;

import Global.GlobalPage;
import junitparams.Parameters;
import org.junit.Test;

public class AutorizationTest extends AutorizationLogic {
    private AutorizationAPI autorizationAPI = new AutorizationAPI();

    private String Token = null;
    private String UserID = null;

    @Test  //1.1
    @Parameters(value = {
            "Shavermay61| 6simbo| Коршунов Игорь Эдуардович         | Shavermay61 | al1911@ml.ru | +79862648361 | " +
                    "active| Коршунов | Игорь | Эдуардович | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО " +
                    "Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) | active | Врач | | " +
                    "doctor | ",

            "+79887515198| bB8jL9aD3| Игроков Иван Михайлович       | KjdhnUL86 | al19U7IN@il.ru | +79887515198 | " +
                    "active| Игроков | Иван | Михайлович | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый " +
                    "МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) | active | Врач | | doctor | ",

            "89097898361| 98jlkIHO| Мурзилкин Антон Игоревич      | 9JHghj76 | a2klIN@maiuiul.ru | +79097898361 | " +
                    " active| Мурзилкин | Антон | Игоревич | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый " +
                    " МГМУ им. И.М. Сеченова Минздрава России(Сеченовский Университет) | active | Врач | |doctor|",

            "72563598361| bB8jkjkjh8| Комбат Михаил Суздальевич   | mnIOJjh87 | jhk8fgN@yax.ru | +72563598361 | " +
                    "active | Комбат | Михаил | Суздальевич | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый " +
                    " МГМУ им.И.М. Сеченова Минздрава России(Сеченовский Университет) | active | Врач | |doctor|",

            "9094896871| jkho98gJ| Карамзин Евгений Павлович        | lkUYIHbnk | jhk8fgN@dex.ru | +79094896871 | " +
                    " active|Карамзин|Евгений|Павлович|true | false | 5b7d08aa068d7552d904d8be| ФГАОУ ВО Первый МГМУ" +
                    " им.И.М. Сеченова Минздрава России(Сеченовский Университет) | active | Врач | |doctor|",

            "rontra@dex.ru| lksjai897jK| Трешев Аркадий Егорович   | 908JHJjkhoia | rontra@dex.ru | +7909297271" +
                    " | active| Трешев | Аркадий | Егорович | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО " +
                    "Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) | active | Врач | | " +
                    "doctor | ",
    })
    public void succesAutorization(String LoginInput, String Password, String Phrase, String Login, String Email,
                                   String Phone, String Status, String SecondName, String FirstName,
                                   String MiddleName, String Superuser, String SendEmail, String OrgID,
                                   String Depart, String OrgStatus, String Post1, String Post2, String Role1,
                                   String Role2) {
        Token = autorizationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = autorizationAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        sucsses(LoginInput, Password, Phrase);
    }

    @Test   //1.2
    @Parameters(value = {
            "number1@mil.ru | | Поля обязательны для заполнения. Убедитесь\\, что все поля заполнены.",
            " | 123456 | Поля обязательны для заполнения. Убедитесь\\, что все поля заполнены.",
    })
    public void empty(String login, String password, String phrase) {
        emptyLoginPass(login, password, phrase);
    }

    @Test   //1.3
    @Parameters(value = {
            "1adkajslk@ndex.ru| 123456| Неверный логин или пароль",
            "2adbvfglk@ndex.ru| 12345| Неверный логин или пароль",
    })
    public void error(String login, String password, String phrase) {
        errorLoginPass(login, password, phrase);
    }
}

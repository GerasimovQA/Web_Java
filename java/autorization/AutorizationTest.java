package autorization;

import junitparams.Parameters;
import org.junit.Test;
import global.EnvironmentLogic;

public class AutorizationTest extends AutorizationLogic {
    @Test   //1.1
    @Parameters(value = {
            "Shavermay61| 6simbo| Коршунов Игорь Эдуардович         | Shavermay61 | al1911@mail.ru | +79862648361 | active| Коршунов | Игорь | Эдуардович | true",
            "+79887515198| bB8jL9aD3| Игроков Иван Михайлович       | KjdhnUL86 | al19U7IN@mail.ru | +79887515198 | active| Игроков | Иван | Михайлович | true",
//            "89097898361| 98jlkIHO| Мурзилкин Антон Игоревич      | 9JHghj76 | a2klIN@mail.ru | +79097898361 | active| Мурзилкин | Антон | Игоревич | true",
//            "79097898361| bB8jkjkjh8| Комбат Михаил Суздальевич   | mnIOJjh87 | jhk8fgN@yandex.ru | +79097898361 | active| Мурзилкин | Антон | Игоревич | true",
//            "9094896871| jkho98gJ| Мориарти Джек Андреевич        | lkUYIHbnk | jhk8fgN@yandex.ru | +79094896871 | active| Мурзилкин | Антон | Игоревич | true",
            "rontra@yandex.ru| lksjai897jK| Трешев Акакий Егорович   | 908JHJjkhoia | rontra@yandex.ru | +7909297271 | active| Трешев | Акакий | Егорович | true",
    })
    public void succesAutorization(String LoginInput, String Password, String Phrase, String Login, String Email, String Phone, String Status, String SecondName, String FirstName, String MiddleName, String Superuser) {
        EnvironmentLogic.login();
        EnvironmentLogic.createUserAPI(Login, Email, Phone, Password, Status, SecondName, FirstName, MiddleName, Superuser);
        sucsses(LoginInput, Password, Phrase);
    }

    @Test   //1.2
    @Parameters(value = {
            "number1@mail.ru | | Поля обязательны для заполнения. Убедитесь\\, что все поля заполнены.",
            " | 123456 | Поля обязательны для заполнения. Убедитесь\\, что все поля заполнены.",
    })
    public void empty(String login, String password, String phrase) {
        emptyLoginPass(login, password, phrase);
    }

    @Test   //1.3
    @Parameters(value = {
            "adkajslk@yandex.ru| 123456| Неверный логин или пароль",
            "adkajslk@yandex.ru| 12345| Неверный логин или пароль",
    })
    public void error(String login, String password, String phrase) {
        errorLoginPass(login, password, phrase);
    }
}

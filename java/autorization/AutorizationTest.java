package autorization;

import junitparams.Parameters;
import org.junit.Test;

public class AutorizationTest extends AutorizationLogic {
    @Test   //1.1
    @Parameters(value = {
            "Labrador| 000000| Герасимов Алексей Михайлович",
            "+79886515198| bB8jL9aD3| Сеченов Иван Михайлович",
//            "89886515198| bB8jL9aD3| Сеченов Иван Михайлович",
//            "79886515198| bB8jL9aD3| Сеченов Иван Михайлович",
//            "9886515198| bB8jL9aD3| Сеченов Иван Михайлович",
            "rontr@yandex.ru| bB8jL9aD3| Сеченов Иван Михайлович",
    })
    public void succesAutorization(String login, String password, String phrase) {
        sucsses(login, password, phrase);
    }

    @Test   //1.2
    @Parameters(value = {
            "number1@mail.ru| | Поля обязательны для заполнения. Убедитесь\\, что все поля заполнены.",
            " | 123456| Поля обязательны для заполнения. Убедитесь\\, что все поля заполнены.",
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

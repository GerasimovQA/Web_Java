package historychanges;

import global.GlobalPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryChangesPage extends GlobalPage {

    public HistoryChangesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //Глобальные объекты
    //    Ссылка "История изменений"
    @FindBy(xpath = "//span[text()= \"История изменений\"]")
    public WebElement linkHistoryChanges;

    // Маркер загрузки списка - 10 строка в истории
    @FindBy(xpath = "//div/div[2]/div[3]/table/tbody/tr[10]/td[3]/div")
    public WebElement marker10History;

    // Маркер загрузки списка - 10 строка в списке пользователей
    @FindBy(xpath = "//div/div[3]/div/div[3]/table/tbody/tr[10]/td[4]/div")
    public WebElement marker10UserList;

    // Столбец Время
    @FindBy(xpath = "//div/div[2]/div[3]/table/tbody/tr/td[1]/div")
    public List<WebElement> columnTime;

    // Столбец Триггер
    @FindBy(xpath = "//div/div[2]/div[3]/table/tbody/tr/td[2]/div")
    public List<WebElement> columnTrigger;

    //    Столбец Инициатор
    @FindBy(xpath = "//div/div[2]/div[3]/table/tbody/tr/td[3]/div/div")
    public List<WebElement> columnInitiator;

    //    Столбец Действие
    @FindBy(xpath = "//div/div[2]/div[3]/table/tbody/tr/td[4]/div")
    public List<WebElement> columnAction;

    //    Столбец Объект
    @FindBy(xpath = "//div/div[2]/div[3]/table/tbody/tr/td[5]/div")
    public List<WebElement> columnObject;

    //    Номер телефона во всплывающем блоке при нажатии на ФИО инициатора
    @FindBy(css = "div[aria-hidden=\"false\"] > div.user-history-user__popover > div:nth-child(1)")
    public WebElement popoverPhone;

    //    Email во всплывающем блоке при нажатии на ФИО инициатора
    @FindBy(css = "div[aria-hidden=\"false\"] > div.user-history-user__popover > div:nth-child(2)")
    public WebElement popoverEmail;

    //    Email во всплывающем блоке при нажатии на ФИО инициатора
    @FindBy(css = "div[aria-hidden=\"false\"] > div.user-history-user__popover > a")
    public WebElement popoverMore;

    //    Знак вопроса справа от инициатора
    @FindBy(xpath = "//span[text()=\"Инициатор\"]/following-sibling::span")
    public WebElement questionInitiator;

    //    Всплывающая подсказка инициатора
        @FindBy(xpath = "//div[text()=\"Тот кто совершает действие.\"]")
    public WebElement popoverInitiator;

    //    Знак вопрос справа от объекта
    @FindBy(xpath = "//span[text()=\"Объект\"]/following-sibling::span")
    public WebElement questionObject;

    //    Всплывающая подсказка объекта
    @FindBy(xpath = "//div[text()=\"Над кем совершают действие.\"]")
    public WebElement popoverObject;

    //    Поле ввода Действие
    @FindBy(xpath = "//div/div[1]/div[2]/div/div[2]/span/span/i")
    public WebElement inputAction;

    //   Список действий в фильтре
    @FindBy(xpath = "//body/div/div/div/ul/li/span")
    public List<WebElement> filterActionsList;

    //   Календарь
    @FindBy(css = "div.user-history-header > div:nth-child(1) > div")
    public WebElement calendar;

    //   Календарь - Сегодня
    @FindBy(xpath = "//td[@class=\"available today\"]")
    public WebElement calendarToday;

    //   Календарь - Сегодня выделенный день
    @FindBy(css = ".available.today.in-range.start-date")
    public WebElement calendarTodaySelect;


    //    Блок Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//*[@class=\"col-md-6\"]")
    public WebElement blockMoreInfoContext;

    //    Дата события в блоке Информация о событии
    @FindBy(xpath = "//div[text()= \"Дата\"]/..//div[2]")
    public WebElement blocInfoAboutEventDate;

    //   Инициатор события в блоке Информация о событии
    @FindBy(xpath = "//div[text()= \"Инициатор\"]/..//div[2]")
    public WebElement blocInfoAboutEventInitiator;

    //   Действие события в блоке Информация о событии
    @FindBy(xpath = "//div[text()= \"Действие\"]/..//div[2]")
    public WebElement blocInfoAboutEventAction;

    //   Объект события в блоке Информация о событии
    @FindBy(xpath = "//div[text()= \"Объект\"]/..//div[2]")
    public WebElement blocInfoAboutEventObject;

    //    Значение  ID события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"ID события\"]/..//div[2]")
    public WebElement blockMoreInfoContextIDValue;

    //    Значение Устройство события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Устройство\"]/..//div[2]")
    public WebElement blockMoreInfoContextDeviceValue;

    //    Значение IP события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"IP\"]/..//div[2]")
    public WebElement blockMoreInfoContextIPValue;

    //    Значение Логин события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Логин\"]/..//div[2]")
    public WebElement blockMoreInfoContextLoginValue;

    //    Значение Email события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Email\"]/..//div[2]")
    public WebElement blockMoreInfoContextEmailValue;

    //    Значение Телефон события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Телефон\"]/..//div[2]")
    public WebElement blockMoreInfoContextPhoneValue;

    //    Значение Фамилия события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Фамилия\"]/..//div[2]")
    public WebElement blockMoreInfoContextFirstnameValue;

    //    Значение Имя события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Имя\"]/..//div[2]")
    public WebElement blockMoreInfoContextSecondnameValue;

    //    Значение Отчество события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Отчество\"]/..//div[2]")
    public WebElement blockMoreInfoContextMidlenameValue;

    //    Значение Статус события в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[text()= \"Статус\"]/..//div[2]")
    public WebElement blockMoreInfoContextStatusValue;

    //    Кнопка Подробнее в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//div[2]/div/div[1]/a[text()= \"Подробнее\"]")
    public WebElement blockMoreInfoContextButtonMoreInfo;

    //    Кнопка Скопировать ссылку в блоке Контекст в расширенной информации по записи (тригер)
    @FindBy(xpath = "//span[text()= \"Скопировать ссылку\"]")
    public WebElement blockMoreInfoContextButtonCopyLink;

    //    Всплывающее сообщение "Ссылка скопирована в буфер обмена"
    @FindBy(xpath = "//div[3]/p[text()=\"Ссылка скопирована в буфер обмена\"]")
    public WebElement popoverLinkIsCopy;


    public String nowDate() {

        String months[] = {"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сент.",
                "окт", "ноя", "дек"};

        String NowDate = "";
        GregorianCalendar gcalendar = new GregorianCalendar();

        if (gcalendar.get(Calendar.DATE) < 10) {
            if (gcalendar.get(Calendar.MINUTE) < 10) {
                NowDate =
                        "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR) + " " +
                                gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE);
                System.out.println("Искомая дата:" + NowDate);
            } else {
                NowDate =
                        "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR) + " " +
                                gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
                System.out.println("Искомая дата:" + NowDate);
            }
        }
        if (gcalendar.get(Calendar.DATE) >= 10) {
            if (gcalendar.get(Calendar.MINUTE) < 10) {
                NowDate =
                        gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR) + " " +
                                gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + "0" + gcalendar.get(Calendar.MINUTE);
                System.out.println("Искомая дата:" + NowDate);
            } else {
                NowDate =
                        gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR) + " " +
                                gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE);
                System.out.println("Искомая дата:" + NowDate);
            }
        }

        return NowDate;
    }

    public String todayDate() {

        String months[] = {"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сент.",
                "окт", "ноя", "дек"};

        String NowDate = "";
        GregorianCalendar gcalendar = new GregorianCalendar();

        if (gcalendar.get(Calendar.DATE) < 10) {
            NowDate =
                    "0" + gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR);
        } else {
            NowDate =
                    gcalendar.get(Calendar.DATE) + " " + months[gcalendar.get(Calendar.MONTH)] + " " + gcalendar.get(Calendar.YEAR);
        }
        return NowDate;
    }

//    public void splitString (String ListDate) {
//        List<String> listdata = new ArrayList<>();
//        String[] subStr = new String[0];
//        String delimeter = ","; // Разделитель
//        subStr = ListDate.split(delimeter);
//        for (int i = 0; i < subStr.length; i++) {
//                listdata.add(subStr[i]);
//            }
//
//        System.out.println(listdata);


//
//        for (String age : arrayList.get(0)) {
//            subStr = age.split(delimeter); // Разделения строки str с помощью метода split()
//            for (int i = 0; i < subStr.length; i++) {
//                listdata.add(subStr[0]);
//            }
//        }
//        System.out.println("List111: " + listdata);
//        listdata = listdata.stream().distinct().collect(Collectors.toList());
//        return listdata;
    }


//    //    Кнопка ОТМЕНА всего кроме пароля и статуса
//    @FindBy(xpath = "//button/span[contains (text(), \"Отмена\")]")
//    public WebElement buttonCancelProfile;
//
//    //    Вкладка "Данные специалиста"
//    @FindBy(xpath = "//div[contains (text(), \"Данные специалиста\")]")
//    public WebElement specialistData;
//
//    //--------------------------------------------------------------------------
//    //  Учетная запись
//    //    Кнопка РЕДАКТИРОВАТЬ ПРОФИЛЬ
//    @FindBy(xpath = "//div[2]/a/span[contains (text(), \"Редактировать профиль\")]")
//    public WebElement buttonChangeProfile;
//
//    //    Фамилия
//    @FindBy(css = ".edit-user-block__full-name > span:nth-child(1)")
//    public WebElement secondNameProfile;
//
//    //    Имя
//    @FindBy(css = ".edit-user-block__full-name > span:nth-child(2)")
//    public WebElement firstNameProfile;
//
//    //    Отчество
//    @FindBy(css = ".edit-user-block__full-name > span:nth-child(3)")
//    public WebElement middleNameProfile;
//
//    //    Ссылка редактирования ФИО
//    @FindBy(xpath = "//div[contains (text(), \"ФИО\")]/following-sibling::div[2]")
//    public WebElement linkEditfioProfile;
//
//    //    Поле ввода ФАМИЛИЯ
//    @FindBy(css = ".el-input__inner[placeholder=\"Фамилия\"]")
//    public WebElement inputSecondName;
//
//    //    Поле ввода ИМЯ
//    @FindBy(css = ".el-input__inner[placeholder=\"Имя\"]")
//    public WebElement inputFirstName;
//
//    //    Поле ввода Отчество
//    @FindBy(css = ".el-input__inner[placeholder=\"Отчество\"]")
//    public WebElement inputMiddleName;
////-----------------------------------------------------------------------------
//    //    Логин
//    @FindBy(xpath = "//div[contains (text(), \"Логин\")]/following-sibling::div")
//    public WebElement loginProfile;
//
//    //    Email
//    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::div")
//    public WebElement emailProfile;
//
//    //    Phone
//    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::div")
//    public WebElement phoneProfile;
//
//    //    Ссылка редактирования логин/email/телефон
//    @FindBy(xpath = "//div[contains (text(), \"Данные для входа\")]/following-sibling::div[4]")
//    public WebElement linkEditAuthProfile;
//
//    //    Поле ввода ЛОГИН
//    @FindBy(css = ".el-input__inner[placeholder=\"Логин\"]")
//    public WebElement inputLogin;
//
//    //    Поле ввода EMAIL
//    @FindBy(css = ".el-input__inner[placeholder=\"Email\"]")
//    public WebElement inputEmail;
//
//    //    Поле ввода ТЕЛЕФОН
//    @FindBy(css = ".el-input__inner[placeholder=\"Телефон\"]")
//    public WebElement inputPhone;
////--------------------------------------------------------------------------------
//    //    Ссылка изменения пароля
//    @FindBy(xpath = "//div[contains (text(), \"Пароль\")]/following-sibling::div")
//    public WebElement linkEditPasswordProfile;
//
//    //    Поле ввода ПАРОЛЬ
//    @FindBy(css = ".el-input__inner[placeholder=\"Новый пароль\"]")
//    public WebElement inputNewPassword;
//
//    //    Поле ввода ПОВТОРИТЕ ПАРОЛЬ
//    @FindBy(css = ".el-input__inner[placeholder=\"Повторите пароль\"]")
//    public WebElement inputRepeatNewPassword;
//
//    //   Иконка "Глаз" - скрыть пароль
//    @FindBy(css = ".show-password.fa.fa-eye-slash")
//    public WebElement notShowPassword;
//
//    //    Кнопка СОХРАНИТЬ ПАРОЛЬ
//    @FindBy(xpath = "//div[3]//span[contains (text(), \"Сохранить\")]")
//    public WebElement buttonSavePassword;
//
//    //    Кнопка ОТМЕНА ПАРОЛЯ
//    @FindBy(xpath = "//div[3]//span[contains (text(), \"Отмена\")]")
//    public WebElement buttonCancelPassword;
//
//
//    //----------------------------------------------------------------------------------
//    //    Текущий статус
//    @FindBy(xpath = "//div[contains (text(), \"Статус\")]/..//following-sibling::div/span[contains (text(), \"к\")]")
//    public WebElement statusProfile;
//
//    //    Ссылка изменения статуса
//    @FindBy(xpath = "//div[contains (text(), \"Статус\")]/following-sibling::div[2]")
//    public WebElement linkEditStatusProfile;
//
//    //    Выпадающий список СТАТУС
//    @FindBy(css = ".el-input__inner[placeholder=\"Статус\"]")
//    public WebElement listStatus;
//
//    //    Пункт АКТИВЕН в выпадающем списке СТАТУС
//    @FindBy(xpath = "//li/span[contains (text(), \"Активен\")]")
//    public WebElement activeStatus;
//
//    //    Пункт ОТКЛЮЧЕН в выпадающем списке СТАТУС
//    @FindBy(xpath = "//li/span[contains (text(), \"Отключен\")]")
//    public WebElement disbleStatus;
//
//    //    Кнопка СОХРАНИТЬ СТАТУС
//    @FindBy(xpath = "//div[4]//span[contains (text(), \"Сохранить\")]")
//    public WebElement buttonSaveStatus;
//
//    //    Кнопка ОТМЕНА СТАТУСА
//    @FindBy(xpath = "//div[4]//span[contains (text(), \"Отмена\")]")
//    public WebElement buttonCancelStatus;
//
//
//
//
//
////-----------------------------------------------------------------------------------------------------------
//// Данные специалиста
//
//
//    //    ФОТО юзера
//    @FindBy(xpath = "//div[contains (text(), \"Фото\")]/following-sibling::div[1]//img")
//    public WebElement photoProfile;
//
//    //    Ссылка ИЗМЕНИТЬ ФОТО
//    @FindBy(xpath = "//div[@class='profile-userpic__photo userpic userpic--medium']/.
// .//following-sibling::div/div[contains (text(), \"Изменить\")]")
//    public WebElement linkEditPhotoProfile;
//
//    //    Кнопка СОХРАНИТЬ ФОТО
//    @FindBy(xpath = "//div[2]/div/div/button/span[contains (text(), \"Сохранить\")]")
//    public WebElement buttonSavePhotoProfile;
//
//    //    Кнопка ОТМЕНИТЬ СОХРАНИЕ ФОТО
//    @FindBy(xpath = "//div[contains (text(), \"Отмена\")]")
//    public WebElement buttonSavePhotoCancelProfile;
//
//    //    Образование, достижения, регалии
//    @FindBy(xpath = "//div[contains (text(), \"Образование, достижения, регалии\")]/following-sibling::div[1]")
//    public WebElement educationProfile;
//
//    //    Ссылка ИЗМЕНИТЬ Образование, достижения, регалии
//    @FindBy(xpath = "//div[contains (text(), \"Образование, достижения, регалии\")]/following-sibling::div[2]")
//    public WebElement linkEditEducationProfile;
//
//    //    Поле ввода Образование, достижения, регалии
//    @FindBy(xpath = "//div[2]/div[2]/div/textarea")
//    public WebElement inputEducation;
//
//    //    Кнопка СОХРАНИТЬ ОБРАЗОВАНИЕ
//    @FindBy(xpath = "//div[2]/button[1]/span[contains (text(), \"Сохранить\")]")
//    public WebElement buttonSaveEducation;
//
//    //    Кнопка ОТМЕНА СОХРАНЕНИЯ ОБРАЗОВАНИЯ
//    @FindBy(xpath = "//div[2]/button[2]/span[contains (text(), \"Отмена\")]")
//    public WebElement buttonCancelEducation;
//
//    //    Email
//    @FindBy(xpath = "//div[contains (text(), \"Email\")]/following-sibling::a")
//    public WebElement emailContactProfile;
//
//    //    Поле ввода Email
//    @FindBy(css = "div:nth-child(1) > input[placeholder=\"Email\"]")
//    public WebElement inputEmailContactProfile;
//
//    //    Телефон
//    @FindBy(xpath = "//div[contains (text(), \"Телефон\")]/following-sibling::a")
//    public WebElement phoneContactProfile;
//
//    //    Поле ввода Телефон
//    @FindBy(css = "div:nth-child(2) > input[placeholder=\"Телефон\"]")
//    public WebElement inputPhoneContactProfile;
//
//    //    Instagram
//    @FindBy(xpath = "//div[contains (text(), \"Instagram\")]/following-sibling::div")
//    public WebElement instagramContactProfile;
//
//    //    Поле ввода Instagram
//    @FindBy(css = "div:nth-child(3) > input[placeholder=\"Instagram\"]")
//    public WebElement inputInstagramContactProfile;
//
//    //    Vk
//    @FindBy(xpath = "//div[contains (text(), \"Vk\")]/following-sibling::div")
//    public WebElement vkContactProfile;
//
//    //    Поле ввода Vk
//    @FindBy(css = "div:nth-child(4) > input[placeholder=\"Vk\"]")
//    public WebElement inputVkContactProfile;
//
//    //    Whatsapp
//    @FindBy(xpath = "//div[contains (text(), \"Whatsapp\")]/following-sibling::div")
//    public WebElement whatsappContactProfile;
//
//    //    Поле ввода Whatsapp
//    @FindBy(css = "div:nth-child(5) > input[placeholder=\"Whatsapp\"]")
//    public WebElement inputWhatsappContactProfile;
//
//    //    Viber
//    @FindBy(xpath = "//div[contains (text(), \"Viber\")]/following-sibling::div")
//    public WebElement viberContactProfile;
//
//    //    Поле ввода Viber
//    @FindBy(css = "div:nth-child(6) > input[placeholder=\"Viber\"]")
//    public WebElement inputViberContactProfile;
//
//    //    Facebook
//    @FindBy(xpath = "//div[contains (text(), \"Facebook\")]/following-sibling::div")
//    public WebElement facebookContactProfile;
//
//    //    Поле ввода Facebook
//    @FindBy(css = "div:nth-child(7) > input[placeholder=\"Facebook\"]")
//    public WebElement inputFacebookContactProfile;
//
//    //    Другое
//    @FindBy(xpath = "//div[contains (text(), \"Другое\")]/following-sibling::div")
//    public WebElement otherContactProfile;
//
//    //    Поле ввода Другое
//    @FindBy(css = "div:nth-child(1) > input[placeholder=\"Другое...\"]")
//    public WebElement inputOtherContactProfile;
//
//    //    Ссылка ИЗМЕНИТЬ Способы связи
//    @FindBy(xpath = "//div[contains (text(), \"Способы связи\")]/following-sibling::div[contains (text(),
// \"Изменить\")]")
//    public WebElement linkEditCommunicationMethodsProfile;
//
//    //    Кнопка СОХРАНИТЬ Способы связи
//    @FindBy(xpath = "//div[2]/button[1]/span[contains (text(), \"Сохранить\")]")
//    public WebElement buttonSaveCommunicationMethodsProfile;
//
//    //    Кнопка ОТМЕНИТЬ СОХРАНЕНИЕ Способов связи
//    @FindBy(xpath = "//div[2]/button[2]/span[contains (text(), \"Отмена\")]")
//    public WebElement buttonCancelCommunicationMethodsProfile;
//
//
//    public void moveToProfileUserChange(String SecondName) {
//        waitE_ClickableAndClick(menuWorkers);
//        waitE_ClickableAndClick(buttonUserList);
//        waitE_visibilityOf(listSecondName);
//        waitE_ClickableAndClick(sumWorker);
//        waitE_ClickableAndClick(workers50);
//        WebElement findToSecondName = new WebDriverWait(driver, 10).until(ExpectedConditions
// .visibilityOfElementLocated(By.xpath("//tr/td[4]/div[contains (text(), \"" + SecondName + "\")]")));
//        scrollWithOffset(findToSecondName, 0, 30);
//        waitE_ClickableAndClick(findToSecondName);
//        waitE_ClickableAndClick(buttonChangeProfile);
//        waitE_visibilityOf(firstNameProfile);
//    }
//


//}




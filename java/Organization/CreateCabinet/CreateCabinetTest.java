package Organization.CreateCabinet;

import junitparams.Parameters;
import org.junit.Test;


public class CreateCabinetTest extends CreateCabinetLogic {

    @Test   //16.1
    @Parameters(value = {
            "Кабинет №1 | Здесь сидит терапевт | | ",

            "Кабинет №2 | | Рабочее место хирурга | ",

            "Кабинет №3 | | Окулист | Офтальмолог ",
    })
    public void createCabinetTest(String NameCabinet, String DescriptionCabinet, String Workplace1, String Workplace2) {
        createCabinet(NameCabinet, DescriptionCabinet, Workplace1, Workplace2);
    }
}

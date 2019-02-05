package Specialty.CreateSpecialty;

import junitparams.Parameters;
import org.junit.Test;

public class CreateSpecialtyTest extends CreateSpecialtyLogic {
    @Test   //2.1
    @Parameters(value = {
            "Название специальности №1 | Очень нужная специальность\\, без которой и жизнь - не жизнь",

            "One Specialty | Very impotant specialty for everyone",

    })
    public void createSpecialtyTest(String NameSpecialty, String DescriptionSpecialty) {
        createSpecialty(NameSpecialty, DescriptionSpecialty);
    }


}

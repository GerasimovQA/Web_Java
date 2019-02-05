package Service.CreateService;

import junitparams.Parameters;
import org.junit.Test;

public class CreateServiceTest extends CreateServiceLogic {
    @Test   //14.1
    @Parameters(value = {
            "1 Услужный код 1 | Услужное название 1 | Услужное название для печати 1 |" +
                    "Услужный артикул 1 | Услужные Противопоказания 1 |" +
                    "Услужне описание 1 | Услужные предусловия 1 | " +
                    "111 | 222 | 333 | 444",

            "1 Услужный код 2 | Услужное название 2 | Услужное название для печати 2 |" +
                    "Услужный артикул 2 | Услужные Противопоказания 2 |" +
                    "Услужне описание 3 | Услужные предусловия 4 | " +
                    "111 | 222 | 333 | 444",
    })
    public void createServiceTest(String CodeService, String NameService, String PrintNameService,
                                  String VendorService, String ContraindicationsService,
                                  String DescriptionService, String PreconditionService,
                                  String DmsCost, String OmsCost, String PmuCost, String OtherCosts) {
        createService(CodeService, NameService, PrintNameService, VendorService, ContraindicationsService,
                DescriptionService, PreconditionService, DmsCost, OmsCost, PmuCost, OtherCosts);

    }

    @Test   //14.2
    @Parameters(value = {
            "1 Раздельное название 1 | Раздельное название для печати 1 | Раздельный код 1 | Раздельный артикул 1",

            "2 Раздельное название 2 | Раздельное название для печати 2 | Раздельный код 2 | Раздельный артикул 2",
    })
    public void createSectionServiceTest(String NameSectionService, String PrintNameSectionService,
                                         String CodeSectionService, String VendorSectionService) {
        createSectionService(NameSectionService, PrintNameSectionService, CodeSectionService, VendorSectionService);

    }

}

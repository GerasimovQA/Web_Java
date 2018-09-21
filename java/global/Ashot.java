package global;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Ashot extends GlobalPage {

    public static void screenshot(WebDriver driver, String Namefile, int pix) {

        Screenshot shot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        String nameFile = Namefile;
        File expectedFile = new File(expectedDir + nameFile + ".png");
        File actualFile = new File(actualDir + nameFile + ".png");
        if (expectedFile.isFile()) {

            try {
                ImageIO.write(shot.getImage(), "png", actualFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                ImageIO.write(shot.getImage(), "png", expectedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Screenshot expectedScreenshot = new Screenshot(ImageIO.read(new File(expectedDir + nameFile + ".png")));
            Screenshot actualScreenshot = new Screenshot(ImageIO.read(new File(actualDir + nameFile + ".png")));
            ImageDiff diff = new ImageDiffer().makeDiff(expectedScreenshot, actualScreenshot);

            System.out.println("Различие снимков в пикселях: " + diff.getDiffSize());

            if (diff.getDiffSize() >= pix) {
                File diffFile = new File(diffDir + nameFile + ".png");
                ImageIO.write(diff.getMarkedImage(), "png", diffFile);
                expectedFile.delete();
                actualFile.delete();
//                diffFile.delete();
            } else {
                File diffFile = new File(diffDir + nameFile + ".png");
                ImageIO.write(diff.getMarkedImage(), "png", diffFile);
                expectedFile.delete();
                actualFile.delete();
//                diffFile.delete();
                throw new AssertionError("Слишком маленькая разница в пикселях");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
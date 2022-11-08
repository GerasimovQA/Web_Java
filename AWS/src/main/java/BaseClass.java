import com.codeborne.selenide.SelenideElement;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static com.codeborne.selenide.Condition.visible;

public class BaseClass {
    public void sout(String text) {
        System.out.println(text);
    }

    public void click(SelenideElement element) {
        element.shouldBe(visible);
        String Locator = element.toString();
        element.click();
        sout("click " + Locator);
    }

    public void enter_text(SelenideElement element, String text) {
        element.shouldBe(visible);
        String Locator = element.toString();
        element.clear();
        element.sendKeys(text);
        sout("entered: " + text + " в: " + Locator);
    }

    public String executeBashCommand(String command) {
        String result = "";

        if (command.contains(".sh")) {
            try {
//        Download script from file
                Process pb = new ProcessBuilder("/bin/bash", command).start();
            } catch (IOException e) {
                e.printStackTrace();
                throw new AssertionError("Script failed");
            }
        } else {
            sout("Executed in Bash = " + command);
            try {
//        Download script from string
                Process pb = new ProcessBuilder("/bin/bash", "-c", command).start();
                result = new String(pb.getInputStream().readAllBytes());
            } catch (IOException e) {
                e.printStackTrace();
                throw new AssertionError("Script failed");
            }
        }
        return result;
    }

    public String getIDofInstanceWAS() {
        String getInstanceIdAWS = "aws ec2 describe-instances";
        String id = executeBashCommand(getInstanceIdAWS);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = null;
        try {
            actualObj = mapper.readTree(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JsonNode idNode = actualObj.get("Reservations").get(0).get("Instances").get(0).get("InstanceId");
        sout("instanceID = " + idNode.toString());
        return idNode.toString().replace("\"","");
    }

    public void startWAS(String InstanceId) {
        String startInstanceAWS = "aws ec2 start-instances --instance-ids " + InstanceId;
        executeBashCommand(startInstanceAWS);
        sout("Started instance = " + InstanceId);
    }

    public void stopWAS(String InstanceId) {
        String startInstanceAWS = "aws ec2 stop-instances --instance-ids " + InstanceId;
        executeBashCommand(startInstanceAWS);
        sout("Stopped instance = " + InstanceId);
    }

    public String getIPofInstanceAWS(String InstanceId) {
        String getIPofInstanceAWS = "aws ec2 describe-instances --instance-ids " + InstanceId + " --query 'Reservations[*].Instances[*].PublicIpAddress' --output text";
       String iPofInstanceAWS = executeBashCommand(getIPofInstanceAWS);
        sout("IP of instanceAWS = " + iPofInstanceAWS);
        return iPofInstanceAWS;
    }
}

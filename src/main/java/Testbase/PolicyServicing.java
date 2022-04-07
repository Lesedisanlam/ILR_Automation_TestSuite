package Testbase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PolicyServicing extends Base {
    @Test
    private void test(){
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = "Welcome: Mercury ";
    }
}

package Testbase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PolicyServicing extends Base {
    @Test
    private void test(){
        System.out.println(getPolicyNoFromExcel("Policy-Servicing","IncreaseSumAssured"));
    }
}

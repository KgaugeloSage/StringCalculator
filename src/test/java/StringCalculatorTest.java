import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {
    StringCalculator test = new StringCalculator();
    @Test
    void emptyStringTest(){
        assertEquals(0,test.add(""));
    }
    @Test
    void oneNUmber(){
        assertEquals(199, test.add("199"));
    }
    @Test
    void twoNumberAdd(){
        assertEquals(135,test.add("12,123"));
    }
    @Test
    void multipleNumAdd(){
        assertEquals(138,test.add("12,123,1,1,1"));
    }
    @Test
   void addWithWhitespace(){
        assertEquals(3, test.add("1\n,2"));
    }
    @Test
    void addDelimiterHandler(){
//        assertEquals(3, test.add("//;\n1;2"));
        assertEquals(3, test.add("//4\n142"));
    }
    @Test
    void loadError(){
           assertThrows(IllegalArgumentException.class, () -> test.add("-1,-2,3,4"),"ERROR: negatives not allowed");
           //The code Should fail for the test to pass

            //so my test fails because the code is correct
    }
    @Test
    void ignoreGreaterthan1000(){
        assertEquals(3,test.add("//;\n10089;1,2"));
    }
    @Test
    void delimeterOfAnyLength(){
        assertEquals(6,test.add("//***\n1***2***3"));
    }

}

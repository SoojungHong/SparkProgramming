/**
 * Created by a613274 on 10.07.2017.
 *
 * Java Lamda Expression
 * http://www.java2s.com/Tutorials/Java_Lambda/Lambda_Tutorial/Lambda/Functional_interface.htm
 */
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {

        //define the BiFunction, two inputs are string and return is also string
        BiFunction<String, String, String> bi = (x, y) -> {
            return x + y;
        };

        System.out.println(bi.apply("java2s.com", "tutorial"));

        Calculator calculator = new Calculator();
        String result = calculator.calc((a,b) -> ": " + (a * b),3, 5);
        System.out.println(result);

        Processor stringProcessor = (String str) -> str.length();
        String name = "Java Lambda";
        int length = stringProcessor.getStringLength(name);
        System.out.println(length);
    }
}

@FunctionalInterface
interface Processor {
    int getStringLength(String str);
}

class Calculator {
    public String calc(BiFunction<Integer, Integer, String> bi, Integer i1, Integer i2) {
        return bi.apply(i1, i2);
    }
}

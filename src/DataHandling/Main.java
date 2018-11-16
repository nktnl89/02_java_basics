package DataHandling;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
//        System.out.println(new Calculator().clearInputString("4 +___fsdfsd*1-0)/(7"));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Calculator calculator = new Calculator();

        try {
            System.out.print("Введите выражение:");
            String clear = calculator.clearInputString(bufferedReader.readLine());
            System.out.println(clear);
            String polska = calculator.getPolskaRecord(clear);
            System.out.println(polska);
            System.out.println(calculator.calculateResult(polska));
            //System.out.println(calc.calculatePolska(calc.getPolskaString(bufferedReader.readLine())));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

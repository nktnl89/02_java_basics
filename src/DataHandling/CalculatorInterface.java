package DataHandling;

public interface CalculatorInterface {
    public String clearInputString(String input);

    public String getPolskaRecord(String input);

    public double calculateResult(String input) throws Exception;
}

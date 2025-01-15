import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Patients p = new Patients("MitroiStefan722Aufgabe1/src/data.json");
        p.eintraege2.forEach(System.out::println);
    }
}
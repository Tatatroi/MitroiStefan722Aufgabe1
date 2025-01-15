import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class Patient{
    int Id;
    String Patient;
    String Symptom;
    String Diagnose;
    String Datum;
    String Krankenhaus;

    public Patient(int id,String patient, String symptom, String diagnose, String datum, String krankenhaus) {
        Patient = patient;
        Id = id;
        Symptom = symptom;
        Diagnose = diagnose;
        Datum = datum;
        Krankenhaus = krankenhaus;
    }
}

public class Patients {

    List<Patient> eintraege2 = new ArrayList<>();
    public static String dataFile = "src\data.json";

    public Patients() throws IOException {
        this.eintraege2 = lesenDatei2(dataFile);
    }

    public static List<Patient> lesenDatei2(String dateiname) throws IOException {
        List<Patient> eintraege = new ArrayList<>();

        // Read the JSON file content as a String
        String jsonContent = new String(Files.readAllBytes(Paths.get(dataFile))).trim();

        // Remove the surrounding square brackets [ and ]
        jsonContent = jsonContent.substring(1, jsonContent.length() - 1).trim();

        // Split JSON objects using '},{' as the delimiter
        String[] jsonObjects = jsonContent.split("},\\s*\\{");

        for (String jsonObject : jsonObjects) {
            // Clean up the JSON object string
            jsonObject = jsonObject.replace("{", "").replace("}", "").trim();

            // Split the fields using ',' as the delimiter
            String[] fields = jsonObject.split(",\\s*");

            // Extract values from the fields
            int Id = 0;
            String Patient ="";
            String Symptom = "";
            String Diagnose ="";
            String Datum = "";
            String Krankenhaus = "";

            for (String field : fields) {
                String[] keyValue = field.split(":\\s*");
                String key = keyValue[0].replace("\"", "").trim();
                String value = keyValue[1].replace("\"", "").trim();

                switch (key) {
                    case "id":
                        Id = Integer.parseInt(value);
                        break;
                    case "Patient":
                        Patient = value;
                        break;
                    case "Symptom":
                        Symptom = value;
                        break;
                    case "Diagnose":
                        Diagnose = value;
                        break;
                    case "Datum":
                        Datum = value;
                        break;
                    case "Krankenhaus":
                        Krankenhaus = value;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown key: " + key);
                }
            }

            // Add the entry to the list
            eintraege.add(new Patient(Id,Patient,Symptom,Diagnose,Datum,Krankenhaus));
        }

        return eintraege;
    }
}

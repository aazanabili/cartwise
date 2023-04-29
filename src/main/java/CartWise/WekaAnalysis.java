package CartWise;

import weka.core.converters.CSVLoader;
import weka.core.Instances;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WekaAnalysis {
    // Instanzvariable zum Speichern der geladenen Daten
    private Instances data;

    // Konstruktor, der den Pfad zur CSV-Datei als Parameter erwartet
    public WekaAnalysis(String csvFilePath) throws IOException {
        // Erstellen eines CSVLoader-Objekts
        CSVLoader loader = new CSVLoader();
        // Festlegen der CSV-Dateiquelle
        loader.setSource(new File(csvFilePath));
        // Laden der Daten aus der CSV-Datei
        data = loader.getDataSet();
    }

    // Methode, um die Tagesumsätze zu berechnen
    public Map<String, Double> getTaglichDaten() {
        Map<String, Double> gesamtProTag = new HashMap<>();
        int dayIndex = data.attribute("Einkaufstag").index();
        int salesIndex = data.attribute("Einkaufssumme").index();

        // Durchlaufen aller Instanzen und Aufsummieren der Tagesumsätze
        for (int i = 0; i < data.numInstances(); i++) {
            String day = data.instance(i).stringValue(dayIndex);
            double sales = data.instance(i).value(salesIndex);
//            System.out.println("days: " + day);
//            System.out.println("sales: " + sales);

            gesamtProTag.put(day, gesamtProTag.getOrDefault(day, 0.0) + sales);
        }

        return gesamtProTag;
    }

    // Methode, um die stündlichen Umsätze zu berechnen
    public Map<String, Double> getGesamtroStunde() {
        Map<String, Double> gesamtProStunde = new HashMap<>();
        int uhrzeitIndex = data.attribute("Einkaufsuhrzeit").index();
        int salesIndex = data.attribute("Einkaufssumme").index();

        // Durchlaufen aller Instanzen und Aufsummieren der stündlichen Umsätze
        for (int i = 0; i < data.numInstances(); i++) {
            String uhrzeit = data.instance(i).stringValue(uhrzeitIndex);
            double sales = data.instance(i).value(salesIndex);

            gesamtProStunde.put(uhrzeit, gesamtProStunde.getOrDefault(uhrzeit, 0.0) + sales);
        }

        return gesamtProStunde;
    }

    // Methode, um den Tag mit dem höchsten Umsatz zu ermitteln
    public String getTopDay() {
        Map<String, Double> gesamtProTag = getTaglichDaten();
        return Collections.max(gesamtProTag.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    // Methode, um die Stunde mit dem höchsten Umsatz zu ermitteln
    public String getTopHour() {
        Map<String, Double> gesamtProStunde = getGesamtroStunde();
        return Collections.max(gesamtProStunde.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    // Hauptmethode
    public static void main(String[] args) {
        try {
            // Erstellen eines WekaAnalysis-Objekts
            WekaAnalysis analysis = new WekaAnalysis("/Users/smallwest888/Downloads/kd10000.csv");
            // Ermitteln des Tages und der Stunde mit dem höchsten Umsatz
            String topDay = analysis.getTopDay();
            String topHour = analysis.getTopHour();

                        // Ausgabe der Ergebnisse
            System.out.println("umsatzstärkster Einkaufstag: " + topDay);
            System.out.println("Umsatzstärkste Einkaufsuhrzeit: " + topHour);

        } catch (IOException e) {
            // Behandlung von möglichen Eingabe-/Ausgabe-Ausnahmen
            e.printStackTrace();
        }
    }
}

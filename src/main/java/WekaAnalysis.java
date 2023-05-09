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

    // Methode, um die ständlichen Umsätze zu berechnen
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
    
    
    // Methode, die stärkste Einkaufsuhrzeit an den einzelnen Tagen ermitteln
    public Map<String, String> StaerksteZeitAmTag() {
    	
        Map<String,String> map = new HashMap<>();
        Map<String,Double> map2 = new HashMap<>();
        
  
        
        int tagIndex = data.attribute("Einkaufstag").index();
        int uhrzeitIndex = data.attribute("Einkaufsuhrzeit").index();
        int salesIndex = data.attribute("Einkaufssumme").index();

        // Durchlaufen aller Instanzen und Aufsummieren der stündlichen Umsätze
        for (int i = 0; i < data.numInstances(); i++) {
        	
        	String tag = data.instance(i).stringValue(tagIndex);
            String uhrzeit = data.instance(i).stringValue(uhrzeitIndex);
            double sales = data.instance(i).value(salesIndex);

            //map.put(tag,Math.max(map.getOrDefault(tag, 0.0), sales) );
            
            
            if( sales > map2.getOrDefault(tag, 0.0)) {
            	map2.put(tag,sales);
            	map.put(tag,uhrzeit); 
            }
   
        }

        return map;
    }

    public Map<String, Map<String, Integer>> getKundenProTagUndStunde() {
        Map<String, Map<String, Integer>> kundenProTagUndStunde = new HashMap<>();
        int dayIndex = data.attribute("Einkaufstag").index();
        int timeIndex = data.attribute("Einkaufsuhrzeit").index();

        for (int i = 0; i < data.numInstances(); i++) {
            String day = data.instance(i).stringValue(dayIndex);
            String time = data.instance(i).stringValue(timeIndex);

            kundenProTagUndStunde.putIfAbsent(day, new HashMap<>());
            Map<String, Integer> kundenProStunde = kundenProTagUndStunde.get(day);

            kundenProStunde.put(time, kundenProStunde.getOrDefault(time, 0) + 1);
        }

        return kundenProTagUndStunde;
    }

    public Map<String, String> getTopHourByCustomersPerDay() {
        Map<String, Map<String, Integer>> kundenProTagUndStunde = getKundenProTagUndStunde();
        Map<String, String> topHourByCustomersPerDay = new HashMap<>();

        for (Map.Entry<String, Map<String, Integer>> entry : kundenProTagUndStunde.entrySet()) {
            String day = entry.getKey();
            Map<String, Integer> kundenProStunde = entry.getValue();

            String topHour = Collections.max(kundenProStunde.entrySet(), Map.Entry.comparingByValue()).getKey();
            topHourByCustomersPerDay.put(day, topHour);
        }

        return topHourByCustomersPerDay;
    }



    public Map<String, Integer> getTaglichKunden() {
        Map<String, Integer> kundenProTag = new HashMap<>();
        int dayIndex = data.attribute("Einkaufstag").index();

        for (int i = 0; i < data.numInstances(); i++) {
            String day = data.instance(i).stringValue(dayIndex);
            kundenProTag.put(day, kundenProTag.getOrDefault(day, 0) + 1);
        }
        return kundenProTag;
    }

    public Map<String, Integer> getKundenProStunde() {
        Map<String, Integer> kundenProStunde = new HashMap<>();
        int timeIndex = data.attribute("Einkaufsuhrzeit").index();

        for (int i = 0; i < data.numInstances(); i++) {
            String time = data.instance(i).stringValue(timeIndex);

            kundenProStunde.put(time, kundenProStunde.getOrDefault(time, 0) + 1);
        }

        return kundenProStunde;
    }

    public String getTopDayByCustomers() {
        Map<String, Integer> kundenProTag = getTaglichKunden();
        return Collections.max(kundenProTag.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public String getTopHourByCustomers() {
        Map<String, Integer> kundenProStunde = getKundenProStunde();
        return Collections.max(kundenProStunde.entrySet(), Map.Entry.comparingByValue()).getKey();
    }


    // Hauptmethode
    public static void main(String[] args) {
        try {
            // Erstellen eines WekaAnalysis-Objekts
            WekaAnalysis analysis = new WekaAnalysis("/Users/smallwest888/Downloads/kd100.csv");
            // Ermitteln des Tages und der Stunde mit dem höchsten Umsatz
            String topDay = analysis.getTopDay();
            String topHour = analysis.getTopHour();
            String topDayByCustomers = analysis.getTopDayByCustomers();
            String topHourByCustomers = analysis.getTopHourByCustomers();
            Map<String, String> topHourByCustomersPerDay = analysis.getTopHourByCustomersPerDay();

            Map<String,String> mapTest = analysis.StaerksteZeitAmTag();

            // Ausgabe der Ergebnisse
            System.out.println("umsatzstärkster Einkaufstag: " + topDay);
            System.out.println("Umsatzstärkste Einkaufsuhrzeit: " + topHour);
            System.out.println("Stärkster Einkaufstag nach Kundenhäufigkeit:" + topDayByCustomers);
            System.out.println("Kundenstärkste einkaufsuhrzeit nach Kundenhäufigkeit: " + topHourByCustomers);
//            System.out.println("Kundenstärkste Einkaufsuhrzeit pro Tag: " + topHourByCustomersPerDay);

            System.out.println("Umsatzstärkste Einkaufsuhrzeit pro Tag: ");
            Set<String> keySet = mapTest.keySet();
            for (String key : keySet) {
                System.out.println(key + ": " + mapTest.get(key));
            }

            System.out.println("Kundenstärkste einkaufsuhrzeit pro Tag nach Kundenhäufigkeit: ");
            Set<String> keySetNachKunden = topHourByCustomersPerDay.keySet();
            for (String key : keySetNachKunden) {
                System.out.println(key + ": " + topHourByCustomersPerDay.get(key));
            }

        } catch (IOException e) {
            // Behandlung von möglichen Eingabe-/Ausgabe-Ausnahmen
            e.printStackTrace();
        }
    }
}

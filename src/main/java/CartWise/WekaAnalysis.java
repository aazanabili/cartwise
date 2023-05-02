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

    // Methode, um die Tagesums‰tze zu berechnen
    public Map<String, Double> getTaglichDaten() {
        Map<String, Double> gesamtProTag = new HashMap<>();
        int dayIndex = data.attribute("Einkaufstag").index();
        int salesIndex = data.attribute("Einkaufssumme").index();
        


        // Durchlaufen aller Instanzen und Aufsummieren der Tagesums√§tze
        for (int i = 0; i < data.numInstances(); i++) {
        	
            String day = data.instance(i).stringValue(dayIndex);
            double sales = data.instance(i).value(salesIndex);
//            System.out.println("days: " + day);
//            System.out.println("sales: " + sales);

            gesamtProTag.put(day, gesamtProTag.getOrDefault(day, 0.0) + sales);
        }

        return gesamtProTag;
    }

    // Methode, um die st‰ndlichen Ums‰tze zu berechnen
    public Map<String, Double> getGesamtroStunde() {
        Map<String, Double> gesamtProStunde = new HashMap<>();
        int uhrzeitIndex = data.attribute("Einkaufsuhrzeit").index();
        int salesIndex = data.attribute("Einkaufssumme").index();

        // Durchlaufen aller Instanzen und Aufsummieren der st√ºndlichen Ums√§tze
        for (int i = 0; i < data.numInstances(); i++) {
            String uhrzeit = data.instance(i).stringValue(uhrzeitIndex);
            double sales = data.instance(i).value(salesIndex);

            gesamtProStunde.put(uhrzeit, gesamtProStunde.getOrDefault(uhrzeit, 0.0) + sales);
        }

        return gesamtProStunde;
    }

    // Methode, um den Tag mit dem hˆchsten Umsatz zu ermitteln
    public String getTopDay() {
        Map<String, Double> gesamtProTag = getTaglichDaten();
        return Collections.max(gesamtProTag.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    // Methode, um die Stunde mit dem hˆchsten Umsatz zu ermitteln
    public String getTopHour() {
        Map<String, Double> gesamtProStunde = getGesamtroStunde();
        return Collections.max(gesamtProStunde.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    
    
    // Methode, die st‰rkste Einkaufsuhrzeit an den einzelnen Tagen ermitteln
    public Map<String, String> StaerksteZeitAmTag() {
    	
        Map<String,String> map = new HashMap<>();
        Map<String,Double> map2 = new HashMap<>();
        
  
        
        int tagIndex = data.attribute("Einkaufstag").index();
        int uhrzeitIndex = data.attribute("Einkaufsuhrzeit").index();
        int salesIndex = data.attribute("Einkaufssumme").index();

        // Durchlaufen aller Instanzen und Aufsummieren der st√ºndlichen Ums√§tze
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

    // Hauptmethode
    public static void main(String[] args) {
        try {
            // Erstellen eines WekaAnalysis-Objekts
            WekaAnalysis analysis = new WekaAnalysis("C:\\Users\\moham\\Desktop\\kd100.csv");
            // Ermitteln des Tages und der Stunde mit dem h√∂chsten Umsatz
            String topDay = analysis.getTopDay();
            String topHour = analysis.getTopHour();
            
            Map<String,String> mapTest = analysis.StaerksteZeitAmTag();
            
            

            // Ausgabe der Ergebnisse
            System.out.println("umsatzst√§rkster Einkaufstag: " + topDay);
            System.out.println("Umsatzst√§rkste Einkaufsuhrzeit: " + topHour);
            System.out.println(mapTest);

        } catch (IOException e) {
            // Behandlung von m√∂glichen Eingabe-/Ausgabe-Ausnahmen
            e.printStackTrace();
        }
    }
}

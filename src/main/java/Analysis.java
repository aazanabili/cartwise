import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Analysis {

    private final Instances data;

    public Analysis(File f) throws IOException {
        CSVLoader loader = new CSVLoader();
        loader.setSource(f);
        data = loader.getDataSet();
    }

    public List<Number> countDayOccurrences() {
        int monday = 0;
        int tuesday = 0;
        int wednesday = 0;
        int thursday = 0;
        int friday = 0;
        int saturday = 0;

        for (Instance i : data) {
            switch (i.stringValue(5)) {
                case "Montag":
                    monday++;
                    break;
                case "Dienstag":
                    tuesday++;
                    break;
                case "Mittwoch":
                    wednesday++;
                    break;
                case "Donnerstag":
                    thursday++;
                    break;
                case "Freitag":
                    friday++;
                    break;
                case "Samstag":
                    saturday++;
                    break;
                default:
                    System.out.println("Tag nicht erkannt");
            }
        }

        List<Number> list = new ArrayList<>();
        list.add(monday);
        list.add(tuesday);
        list.add(wednesday);
        list.add(thursday);
        list.add(friday);
        list.add(saturday);

        return list;
    }

    public List<Customer> getCustomerCluster(int number) throws Exception {
        String[] result;
        Instances customer = new Instances(data);
        SimpleKMeans model = new SimpleKMeans();
        model.setNumClusters(number);

        for (int i = 0; i < 16; i++) customer.deleteAttributeAt(9);

        model.buildClusterer(customer);

        result = model.getClusterCentroids().toString().split("@data\n");
        result = result[1].split("\n");


        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            customers.add(new Customer(result[i].split(",")));
        }

        return customers;
    }
    
    
    // Methode, um den Umsatzstärkster Einkaufstag zu ermitteln
    
    public Map<String, Double> UmsatzstarksterEinkaufstag () {
    	
    	Map<String,Double> map = new HashMap<>();
    	
        int tagIndex = data.attribute("Einkaufstag").index();
        int summeIndex = data.attribute("Einkaufssumme").index();
      
        // Alle Datensätze durchlaufen und jedes mal summieren
        
        for (int i = 0; i < data.numInstances(); i++) {
        	
        	double summe = data.instance(i).value(summeIndex);
        	String tag = data.instance(i).stringValue(tagIndex);
  
            map.put(tag, map.getOrDefault(tag,0.0)+summe);
        }
        
    	return map;
    	
    }
    
    // Methode, um den Umsatzstärkster Einkaufszeit zu ermitteln
    
    public Map<String, Double> UmsatzstarksteEinkaufszeit() {
    	
    	Map<String,Double> map = new HashMap<>();
    	
        int zeitIndex = data.attribute("Einkaufsuhrzeit").index();
        int summeIndex = data.attribute("Einkaufssumme").index();
      
        // Alle Datensätze durchlaufen und jedes mal summieren
        
        for (int i = 0; i < data.numInstances(); i++) {
        	
        	String zeit = data.instance(i).stringValue(zeitIndex);
        	double summe = data.instance(i).value(summeIndex);
        	
  
            map.put(zeit, map.getOrDefault(zeit,0.0)+summe);
        }
        
    	return map;
    	
    }
    
    
    
    // Methode, um die Umsatzstärkste Einkaufsuhrzeit an einzelnen Tagen zu ermitteln
    
    public Map<String,String> StarksteZeitAmTag() {
    	
    	Map <String,String> result=new HashMap<>();  
        Map<String,Double> montag = new HashMap<>();
        Map<String,Double> dienstag = new HashMap<>();
        Map<String,Double> mittwoch= new HashMap<>();
        Map<String,Double> donnerstag = new HashMap<>();
        Map<String,Double> freitag = new HashMap<>();
        Map<String,Double> samstag= new HashMap<>();

        int tagIndex = data.attribute("Einkaufstag").index();
        int uhrzeitIndex = data.attribute("Einkaufsuhrzeit").index();
        int salesIndex = data.attribute("Einkaufssumme").index();
        
        // Durchlaufen aller Instanzen und Aufsummieren der stündlichen Umsätze
        for (int i = 0; i < data.numInstances(); i++) {
        	
        	
        	String tag = data.instance(i).stringValue(tagIndex);
            String uhrzeit = data.instance(i).stringValue(uhrzeitIndex);
            double sales = data.instance(i).value(salesIndex);
            
            // aktuelle Summe mit der vohrer gespeicherten s vergleichen und die hashmaps aktualiseren

    
            	montag.put(uhrzeit, montag.getOrDefault(uhrzeit,0.0)+sales);
            	
            	 switch (tag) {
                 case "Montag":
                	 montag.put(uhrzeit, montag.getOrDefault(uhrzeit,0.0)+sales);
                     break;
                 case "Dienstag":
                	 dienstag.put(uhrzeit,dienstag.getOrDefault(uhrzeit,0.0)+sales);
                     break;
                 case "Mittwoch":
                	 mittwoch.put(uhrzeit, mittwoch.getOrDefault(uhrzeit,0.0)+sales);
                     break;
                 case "Donnerstag":
                	 donnerstag.put(uhrzeit,donnerstag.getOrDefault(uhrzeit,0.0)+sales);
                     break;
                 case "Freitag":
                	 freitag.put(uhrzeit,freitag.getOrDefault(uhrzeit,0.0)+sales);
                     break;
                 case "Samstag":
                	 samstag.put(uhrzeit,samstag.getOrDefault(uhrzeit,0.0)+sales);
                     break;
                 default:
                     System.out.println("Tag nicht erkannt");
                }
           
        }
        
         String maxKey;
	 
	   	 maxKey = montag.keySet().stream().max(Comparator.comparing(montag::get)).orElse(null);
	   	 result.put("Montag",maxKey);
	   	 
	   	 maxKey = dienstag.keySet().stream().max(Comparator.comparing(dienstag::get)).orElse(null);
	   	 result.put("Dienstag",maxKey);
	   	 
	   	 maxKey = mittwoch.keySet().stream().max(Comparator.comparing(mittwoch::get)).orElse(null);
	   	 result.put("Mittwoch",maxKey);
	   	 
	   	 maxKey = donnerstag.keySet().stream().max(Comparator.comparing(donnerstag::get)).orElse(null);
	   	 result.put("Donnerstag",maxKey);
	   	 
	   	 maxKey = freitag.keySet().stream().max(Comparator.comparing(freitag::get)).orElse(null);
	   	 result.put("Freitag",maxKey);
	   	 
	   	 maxKey = samstag.keySet().stream().max(Comparator.comparing(samstag::get)).orElse(null);
	   	 result.put("Samstag",maxKey);

        return result;
    }
    
    
    // Methode, die um stärkste Einkaufstag nach kundenhaufigkeit
    public Map<String, Integer> StaerksteTagNachHaufigkeit() {
    	
    	Map<String,Integer> map = new HashMap<>();
        int tagIndex = data.attribute("Einkaufstag").index();
        
        // Alle Datensätze durchlaufen und jedes mal inkrementieren
        for (int i = 0; i < data.numInstances(); i++) {
        	
        	String tag = data.instance(i).stringValue(tagIndex);
        	
            map.put(tag, map.getOrDefault(tag,0)+1);
        }
    	return map;
    	
    }
    
    
    // Methode, die um stärkste Einkaufszeit nach kundenhaufigkeit
    
    public Map<String,Integer> StaerksteZeitNachHaufigkeit() {
    	
    	Map<String,Integer> map = new HashMap<>();
        int zeitIndex = data.attribute("Einkaufsuhrzeit").index();
        
        // Alle Datensätze durchlaufen und jedes mal inkrementieren
        for (int i = 0; i < data.numInstances(); i++) {
        
        	String zeit= data.instance(i).stringValue(zeitIndex);
            map.put(zeit, map.getOrDefault(zeit,0)+1);
        }
    	return map;
    }
    
}
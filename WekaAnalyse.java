
public class WekaAnalyse {
	import weka.associations.Apriori;
	import weka.associations.AssociationRule;
	import weka.classifiers.rules.ZeroR;
	import weka.clusterers.SimpleKMeans;
	import weka.core.Instances;
	import weka.core.converters.ArffLoader;
	import weka.core.converters.ArffSaver;
	import weka.core.converters.CSVLoader;
	import weka.filters.Filter;
	import weka.filters.unsupervised.attribute.NumericCleaner;
	import weka.filters.unsupervised.attribute.NumericToNominal;

	import java.io.File;
	import java.util.List;
	
	public static void CsvVerarbeitung(){
		String path = "./";
        String roh = path + "kd100.csv";
        String arffDat = path + "kd100.arff";

        Instances alleDaten;
        Instances nurWaren;
        Instances nurKunden;
        Instances arffDaten;
        
     // CSV-Datei laden
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(roh));
        alleDaten = loader.getDataSet();
        
     // 0 durch ? ersetzen, um fuer die Auswertung nur die Waren zu
        // beruecksichtigen, die gekauft wurden
        NumericCleaner nc = new NumericCleaner();
        nc.setMinThreshold(1.0); // Schwellwert auf 1 setzen
        nc.setMinDefault(Double.NaN); // alles unter 1 durch ? ersetzen
        nc.setInputFormat(alleDaten);
        alleDaten = Filter.useFilter(alleDaten, nc); // Filter anwenden
        
        /*
         * ARFF - Format der Daten fuer Weka erzeugen Das ist zwar komisch (erst
         * speichern und dann wieder einlesen), geht sicher auch anders. Drueber
         * nachdenken .. irgendwann ;-)
         */
        // als ARFF speichern
        ArffSaver saver = new ArffSaver();
        saver.setInstances(alleDaten);
        saver.setFile(new File(arffDat));
        saver.writeBatch();

        // Arff-Datei laden
        ArffLoader aLoader = new ArffLoader();
        aLoader.setSource(new File(arffDat));
        arffDaten = aLoader.getDataSet();
	}
	
	public static void main(String[] args) {
		

	}

}

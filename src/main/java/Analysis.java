import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
}

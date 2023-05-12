import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Analysis {

    private final Instances data;

    public Analysis(File f) throws IOException {
        CSVLoader loader = new CSVLoader();
        loader.setSource(f);
        data = loader.getDataSet();
    }
    
    public List<String>  getFehler() {
    	Instances data_temp = data;
    	Instance firstInstance = data.instance(0);
        String[] result = new String[firstInstance.numAttributes() + 1]; 
        List<String> myList = new ArrayList<>();
        String temp;
        int row_nummer = 1;

        for (Instance d : data_temp) {
                // ------------------------------------------------------------------------------------------Geschlecht
                // 0: m or w
                if (d.toString(0)==null) {
                	myList.add ( "Error:Das Feld in Spalte (Geschlecht) und Zeile (" + row_nummer + "): iat leer");
                } else if (!d.toString(0).equals("w") && !d.toString(0).equals("m")) {
                	myList.add ( "Error:Das Feld in Spalte (Geschlecht) und Zeile (" + row_nummer + "):"
                            + d.toString(0) + ". ");
                }

                // ------------------------------------------------------------------------------------------Alter
                // 1: 18-30 >60

                if (d.toString(1).equals(null)) {
                	myList.add (  "Error:Das Feld in Spalte (Geschlecht) und Zeile (" + row_nummer + "): iat leer");
                } /*else {
                    temp = d.toString(1);
                    d.toString(1).replace("-", "").replace(">", "").replace("<", "").replace(" ", "");
                    try {
                        int intValue = Integer.parseInt(d.toString(1));
                        if (!(intValue > 0) && !(intValue < 6060)) {
                            result[1] = "Error:Das Feld in Spalte (Alter) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[1] = "Error:Das Feld in Spalte (Alter) und Zeile (" + row_nummer + "):invald Value";
                    }
                }*/

                // ------------------------------------------------------------------------------------------Kinder
                // 2: ja or nein
                if (d.toString(2).equals(null)) {
                	myList.add (   "Error:Das Feld in Spalte (Kinder) und Zeile (" + row_nummer + "): iat leer");
                } else if (!d.toString(2).equals("ja") && !d.toString(2).equals("nein")) {
                	myList.add (   "Error:Das Feld in Spalte (Kinder) und Zeile (" + row_nummer + "):"
                            + d.toString(2) + ".");
                }

                // ------------------------------------------------------------------------------------------Familienstand
                // 3: ledig or Partnerschaft
                if (d.toString(3).equals(null)) {
                	myList.add (   "Error:Das Feld in Spalte (Familienstand) und Zeile (" + row_nummer + "): iat leer");
                } else if (!d.toString(3).equals("ledig") && !d.toString(3).equals("Partnerschaft")) {
                	myList.add (   "Error:Das Feld in Spalte (Familienstand) und Zeile (" + row_nummer + "):"
                            + d.toString(3) + ".");
                }
                // ------------------------------------------------------------------------------------------Berufstaetig
                // 4: ja or nein
                if (d.toString(4).equals(null)) {
                	myList.add (   "Error:Das Feld in Spalte (Berufstaetig) und Zeile (" + row_nummer + "): iat leer");
                } else if (!d.toString(4).equals("ja") && !d.toString(4).equals("nein")) {
                	myList.add (   "Error:Das Feld in Spalte (Berufstaetig) und Zeile (" + row_nummer + "):"
                            + d.toString(4) + ".");
                }
                // ------------------------------------------------------------------------------------------Einkaufstag
                // 5: Freitag or Samstag or Montag or Dienstag or Mittwoch or Donnerstag
                if (d.toString(5).equals(null)) {
                	myList.add (   "Error:Das Feld in Spalte (Einkaufstag) und Zeile (" + row_nummer + "): iat leer");
                } else if (!d.toString(5).equals("Freitag") && !d.toString(5).equals("Samstag")
                        && !d.toString(5).equals("Montag") && !d.toString(5).equals("Dienstag")
                        && !d.toString(5).equals("Mittwoch") && !d.toString(5).equals("Donnerstag")) {
                	myList.add (   "Error:Das Feld in Spalte (Einkaufstag) und Zeile (" + row_nummer + "):"
                            + d.toString(5) + ".");
                }
                // ------------------------------------------------------------------------------------------Einkaufszeit
                // 6: >17 Uhr or 12-14 Uhr or <17 Uhr
                if (d.toString(6).equals(null)) {
                	myList.add (  "Error:Das Feld in Spalte (Einkaufszeit) und Zeile (" + row_nummer + "): iat leer");
                } /*else {
                    temp = d.toString(6);
                    d.toString(6).replaceAll("Uhr", "");
                    d.toString(6).replaceAll("-", "");
                    d.toString(6).replaceAll("<", "");
                    d.toString(6).replaceAll(">", "");
                    d.toString(6).replaceAll(" ", "");
                    try {
                        int intValue = Integer.parseInt(d.toString(6));
                        if (!(intValue > 0) && !(intValue < 2323)) {
                            result[6] = "Error:Das Feld in Spalte (Einkaufszeit) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[6] = "Error:Das Feld in Spalte (Einkaufszeit) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Wohnort
                // 7: <10 km or 10-25 km
                if (d.toString(7).equals(null)) {
                	myList.add ( "Error:Das Feld in Spalte (Wohnort) und Zeile (" + row_nummer + "): iat leer");
                } /*else {
                    temp = d.toString(7);
                    d.toString(7).replaceAll("km", "");
                    d.toString(7).replaceAll("-", "");
                    d.toString(7).replaceAll("<", "");
                    d.toString(7).replaceAll(">", "");
                    d.toString(7).replaceAll(" ", "");
                    try {
                        int intValue = Integer.parseInt(d.toString(7));
                        if (!(intValue > 0) && !(intValue < 2525)) {
                            result[7] = "Error:Das Feld in Spalte (Wohnort) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[7] = "Error:Das Feld in Spalte (Wohnort) und Zeile (" + row_nummer + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Haushaltsnettoeinkommen
                // 8: 3200-<4500 or <1000
                if (d.toString(8).equals(null)) {
                	myList.add ( "Error:Das Feld in Spalte (Haushaltsnettoeinkommen) und Zeile (" + row_nummer + "): iat leer");
                } /*else {
                    temp = d.toString(8);
                    d.toString(8).replaceAll("-", "");
                    d.toString(8).replaceAll("<", "");
                    d.toString(8).replaceAll(">", "");
                    d.toString(8).replaceAll(" ", "");
                    try {
                        int intValue = Integer.parseInt(d.toString(8));
                        if (!(intValue > 0) && !(intValue < 2525)) {
                            result[8] = "Error:Das Feld in Spalte (Haushaltsnettoeinkommen) und Zeile (" + row_nummer
                                    + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[8] = "Error:Das Feld in Spalte (Haushaltsnettoeinkommen) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Einkaufssumme
                // 9: integer
                if (d.toString(9).equals(null)) {
                	myList.add (  "Error:Das Feld in Spalte (Einkaufssumme) und Zeile (" + row_nummer + "): iat leer");
                } /*else {
                    temp = d.toString(9);
                    try {
                        int intValue = Integer.parseInt(d.toString(9));
                        if (!(intValue >= 0)) {
                            result[9] = "Error:Das Feld in Spalte (Einkaufssumme) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[9] = "Error:Das Feld in Spalte (Einkaufssumme) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Fertiggerichte
                // 10: integer
                if (d.toString(10).equals(null)) {
                	myList.add (  "Error:Das Feld in Spalte (Fertiggerichte) und Zeile (" + row_nummer + "): iat leer");
                } /*else {
                    temp = d.toString(10);
                    try {
                        int intValue = Integer.parseInt(d.toString(10));
                        if (!(intValue >= 0)) {
                            result[10] = "Error:Das Feld in Spalte (Fertiggerichte) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[10] = "Error:Das Feld in Spalte (Fertiggerichte) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Tiefkuehlware
                // 11: integer
                if (d.toString(11).equals(null)) {
                    result[11] = "Error:Das Feld in Spalte (Tiefkuehlware) und Zeile (" + row_nummer + "): iat leer";
                } /*else {
                    temp = d.toString(11);
                    try {
                        int intValue = Integer.parseInt(d.toString(11));
                        if (!(intValue >= 0)) {
                            result[11] = "Error:Das Feld in Spalte (Tiefkuehlware) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[11] = "Error:Das Feld in Spalte (Tiefkuehlware) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Milchprodukte
                // 12: integer
                if (d.toString(12).equals(null)) {
                    result[12] = "Error:Das Feld in Spalte (Milchprodukte) und Zeile (" + row_nummer + "): iat leer";
                } /*else {
                    temp = d.toString(12);
                    try {
                        int intValue = Integer.parseInt(d.toString(12));
                        if (!(intValue >= 0)) {
                            result[12] = "Error:Das Feld in Spalte (Milchprodukte) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[12] = "Error:Das Feld in Spalte (Milchprodukte) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Backwaren
                // 13: integer
                if (d.toString(13).equals(null)) {
                    result[13] = "Error:Das Feld in Spalte (Backwaren) und Zeile (" + row_nummer + "): iat leer";
                } /*else {
                    temp = d.toString(13);
                    try {
                        int intValue = Integer.parseInt(d.toString(13));
                        if (!(intValue >= 0)) {
                            result[13] = "Error:Das Feld in Spalte (Backwaren) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[13] = "Error:Das Feld in Spalte (Backwaren) und Zeile (" + row_nummer + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Obst/Gemüse
                // 14: integer
                if (d.toString(14).equals(null)) {
                    result[14] = "Error:Das Feld in Spalte (Obst/Gemüse) und Zeile (" + row_nummer + "): iat leer";
                } /*else {
                    temp = d.toString(14);
                    try {
                        int intValue = Integer.parseInt(d.toString(14));
                        if (!(intValue >= 0)) {
                            result[14] = "Error:Das Feld in Spalte (Obst/Gemüse) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[14] = "Error:Das Feld in Spalte (Obst/Gemüse) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Spirituosen
                // 15: integer
                if (d.toString(15).equals(null)) {
                    result[15] = "Error:Das Feld in Spalte (Spirituosen) und Zeile (" + row_nummer + "): iat leer";
                } /*else {
                    temp = d.toString(15);
                    try {
                        int intValue = Integer.parseInt(d.toString(15));
                        if (!(intValue >= 0)) {
                            result[15] = "Error:Das Feld in Spalte (Spirituosen) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[15] = "Error:Das Feld in Spalte (Spirituosen) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Tiernahrung
                // 16: integer
                if (d.toString(16).equals(null)) {
                    result[16] = "Error:Das Feld in Spalte (Tiernahrung) und Zeile (" + row_nummer + "): iat leer";
                } /*else {
                    temp = d.toString(16);
                    try {
                        int intValue = Integer.parseInt(d.toString(16));
                        if (!(intValue >= 0)) {
                            result[16] = "Error:Das Feld in Spalte (Tiernahrung) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[16] = "Error:Das Feld in Spalte (Tiernahrung) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Bier
                // 17: integer
                if (d.toString(17).equals(null)) {
                    result[17] = "Error:Das Feld in Spalte (Bier) und Zeile (" + row_nummer + "): iat leer";
                } /*else {
                    temp = d.toString(17);
                    try {
                        int intValue = Integer.parseInt(d.toString(17));
                        if (!(intValue >= 0)) {
                            result[17] = "Error:Das Feld in Spalte (Bier) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[17] = "Error:Das Feld in Spalte (Bier) und Zeile (" + row_nummer + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Frischfleisch
                // 18: integer
                if (d.toString(18).equals(null)) {
                    result[18] = "Error:Das Feld in Spalte (Frischfleisch) und Zeile (" + row_nummer + "): iat leer";
                } /*else {
                    temp = d.toString(18);
                    try {
                        int intValue = Integer.parseInt(d.toString(18));
                        if (!(intValue >= 0)) {
                            result[18] = "Error:Das Feld in Spalte (Frischfleisch) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[18] = "Error:Das Feld in Spalte (Frischfleisch) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Drogerieartikel
                // 19: integer
                if (d.toString(19).equals(null)) {
                    result[19] = "Error:Das Feld in Spalte (Drogerieartikel) und Zeile (" + row_nummer + "): iat leer";
                } /*else {
                    temp = d.toString(19);
                    try {
                        int intValue = Integer.parseInt(d.toString(19));
                        if (!(intValue >= 0)) {
                            result[19] = "Error:Das Feld in Spalte (Drogerieartikel) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[19] = "Error:Das Feld in Spalte (Drogerieartikel) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Konserven
                // 20: integer
                if (d.toString(20).equals(null)) {
                    result[20] = "Error:Das Feld in Spalte (Konserven) und Zeile (" + row_nummer + "): iat leer";
                } /*else {
                    temp = d.toString(20);
                    try {
                        int intValue = Integer.parseInt(d.toString(20));
                        if (!(intValue >= 0)) {
                            result[20] = "Error:Das Feld in Spalte (Konserven) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[20] = "Error:Das Feld in Spalte (Konserven) und Zeile (" + row_nummer + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Kaffee/Tee
                // 21: integer
                if (d.toString(21).equals(null)) {
                    result[21] = "Error:Das Feld in Spalte (Kaffee/Tee) und Zeile (" + row_nummer + "): iat leer";
                } /*else {
                    temp = d.toString(21);
                    try {
                        int intValue = Integer.parseInt(d.toString(21));
                        if (!(intValue >= 0)) {
                            result[21] = "Error:Das Feld in Spalte (Kaffee/Tee) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[21] = "Error:Das Feld in Spalte (Kaffee/Tee) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Suessigkeiten
                // 22: integer
                if (d.toString(22).equals(null)) {
                    result[22] = "Error:Das Feld in Spalte (Suessigkeiten) und Zeile (" + row_nummer + "): iat leer";
                } /*else {
                    temp = d.toString(22);
                    try {
                        int intValue = Integer.parseInt(d.toString(22));
                        if (!(intValue >= 0)) {
                            result[22] = "Error:Das Feld in Spalte (Suessigkeiten) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[22] = "Error:Das Feld in Spalte (Suessigkeiten) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Wurstwaren
                // 23: integer
                if (d.toString(23).equals(null)) {
                    result[23] = "Error:Das Feld in Spalte (Wurstwaren) und Zeile (" + row_nummer + "): iat leer";
                } /*else {
                    temp = d.toString(23);
                    try {
                        int intValue = Integer.parseInt(d.toString(23));
                        if (!(intValue >= 0)) {
                            result[23] = "Error:Das Feld in Spalte (Wurstwaren) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[23] = "Error:Das Feld in Spalte (Wurstwaren) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                // ------------------------------------------------------------------------------------------Schreibwaren
                // 24: integer
                if (d.toString(24).equals(null)) {
                    result[24] = "Error:Das Feld in Spalte (Schreibwaren) und Zeile (" + row_nummer + "): iat leer";
                } /*else {
                    temp = d.toString(24);
                    try {
                        int intValue = Integer.parseInt(d.toString(24));
                        if (!(intValue >= 0)) {
                            result[24] = "Error:Das Feld in Spalte (Schreibwaren) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[24] = "Error:Das Feld in Spalte (Schreibwaren) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }*/
                row_nummer++;
        }
        //System.out.print(Arrays.toString(result));
        return myList;
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
}

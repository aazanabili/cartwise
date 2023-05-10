import weka.clusterers.SimpleKMeans;
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
    
    public String[] getFehler() {
        Instances data_temp = data;
        Instance firstInstance = data.instance(0);
        String[] result = new String[firstInstance.numAttributes() + 1];

        String temp;
        int row_nummer = 0;

        for (Instance d : data_temp) {
            if (row_nummer > 0) {
                // ------------------------------------------------------------------------------------------Geschlecht
                // 0: m or w
                if (d.stringValue(0).equals(null)) {
                    result[0] = "Error:Das Feld in Spalte (Geschlecht) und Zeile (" + row_nummer + "): iat leer";
                } else if (!d.stringValue(0).equals("w") && !d.stringValue(0).equals("m")) {
                    result[0] = "Error:Das Feld in Spalte (Geschlecht) und Zeile (" + row_nummer + "):"
                            + d.stringValue(0) + ".";
                }

                // ------------------------------------------------------------------------------------------Alter
                // 1: 18-30 >60

                if (d.stringValue(1).equals(null)) {
                    result[1] = "Error:Das Feld in Spalte (Geschlecht) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(1);
                    d.stringValue(1).replaceAll("-", "");
                    d.stringValue(1).replaceAll(">", "");
                    d.stringValue(1).replaceAll("<", "");
                    d.stringValue(1).replaceAll(" ", "");
                    try {
                        int intValue = Integer.parseInt(d.stringValue(1));
                        if (!(intValue > 0) && !(intValue < 6060)) {
                            result[1] = "Error:Das Feld in Spalte (Alter) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[1] = "Error:Das Feld in Spalte (Alter) und Zeile (" + row_nummer + "):invald Value";
                    }
                }

                // ------------------------------------------------------------------------------------------Kinder
                // 2: ja or nein
                if (d.stringValue(2).equals(null)) {
                    result[2] = "Error:Das Feld in Spalte (Kinder) und Zeile (" + row_nummer + "): iat leer";
                } else if (!d.stringValue(2).equals("ja") && !d.stringValue(2).equals("nein")) {
                    result[2] = "Error:Das Feld in Spalte (Kinder) und Zeile (" + row_nummer + "):"
                            + d.stringValue(2) + ".";
                }

                // ------------------------------------------------------------------------------------------Familienstand
                // 3: ledig or Partnerschaft
                if (d.stringValue(3).equals(null)) {
                    result[3] = "Error:Das Feld in Spalte (Familienstand) und Zeile (" + row_nummer + "): iat leer";
                } else if (!d.stringValue(3).equals("ledig") && !d.stringValue(3).equals("Partnerschaft")) {
                    result[3] = "Error:Das Feld in Spalte (Familienstand) und Zeile (" + row_nummer + "):"
                            + d.stringValue(3) + ".";
                }
                // ------------------------------------------------------------------------------------------Berufstaetig
                // 4: ja or nein
                if (d.stringValue(4).equals(null)) {
                    result[4] = "Error:Das Feld in Spalte (Berufstaetig) und Zeile (" + row_nummer + "): iat leer";
                } else if (!d.stringValue(4).equals("ja") && !d.stringValue(4).equals("nein")) {
                    result[4] = "Error:Das Feld in Spalte (Berufstaetig) und Zeile (" + row_nummer + "):"
                            + d.stringValue(4) + ".";
                }
                // ------------------------------------------------------------------------------------------Einkaufstag
                // 5: Freitag or Samstag or Montag or Dienstag or Mittwoch or Donnerstag
                if (d.stringValue(5).equals(null)) {
                    result[5] = "Error:Das Feld in Spalte (Einkaufstag) und Zeile (" + row_nummer + "): iat leer";
                } else if (!d.stringValue(5).equals("Freitag") && !d.stringValue(5).equals("Samstag")
                        && !d.stringValue(5).equals("Montag") && !d.stringValue(5).equals("Dienstag")
                        && !d.stringValue(5).equals("Mittwoch") && !d.stringValue(5).equals("Donnerstag")) {
                    result[5] = "Error:Das Feld in Spalte (Einkaufstag) und Zeile (" + row_nummer + "):"
                            + d.stringValue(5) + ".";
                }
                // ------------------------------------------------------------------------------------------Einkaufszeit
                // 6: >17 Uhr or 12-14 Uhr or <17 Uhr
                if (d.stringValue(6).equals(null)) {
                    result[6] = "Error:Das Feld in Spalte (Einkaufszeit) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(6);
                    d.stringValue(6).replaceAll("Uhr", "");
                    d.stringValue(6).replaceAll("-", "");
                    d.stringValue(6).replaceAll("<", "");
                    d.stringValue(6).replaceAll(">", "");
                    d.stringValue(6).replaceAll(" ", "");
                    try {
                        int intValue = Integer.parseInt(d.stringValue(6));
                        if (!(intValue > 0) && !(intValue < 2323)) {
                            result[6] = "Error:Das Feld in Spalte (Einkaufszeit) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[6] = "Error:Das Feld in Spalte (Einkaufszeit) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Wohnort
                // 7: <10 km or 10-25 km
                if (d.stringValue(7).equals(null)) {
                    result[7] = "Error:Das Feld in Spalte (Wohnort) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(7);
                    d.stringValue(7).replaceAll("km", "");
                    d.stringValue(7).replaceAll("-", "");
                    d.stringValue(7).replaceAll("<", "");
                    d.stringValue(7).replaceAll(">", "");
                    d.stringValue(7).replaceAll(" ", "");
                    try {
                        int intValue = Integer.parseInt(d.stringValue(7));
                        if (!(intValue > 0) && !(intValue < 2525)) {
                            result[7] = "Error:Das Feld in Spalte (Wohnort) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[7] = "Error:Das Feld in Spalte (Wohnort) und Zeile (" + row_nummer + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Haushaltsnettoeinkommen
                // 8: 3200-<4500 or <1000
                if (d.stringValue(8).equals(null)) {
                    result[8] = "Error:Das Feld in Spalte (Haushaltsnettoeinkommen) und Zeile (" + row_nummer
                            + "): iat leer";
                } else {
                    temp = d.stringValue(8);
                    d.stringValue(8).replaceAll("-", "");
                    d.stringValue(8).replaceAll("<", "");
                    d.stringValue(8).replaceAll(">", "");
                    d.stringValue(8).replaceAll(" ", "");
                    try {
                        int intValue = Integer.parseInt(d.stringValue(8));
                        if (!(intValue > 0) && !(intValue < 2525)) {
                            result[8] = "Error:Das Feld in Spalte (Haushaltsnettoeinkommen) und Zeile (" + row_nummer
                                    + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[8] = "Error:Das Feld in Spalte (Haushaltsnettoeinkommen) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Einkaufssumme
                // 9: integer
                if (d.stringValue(9).equals(null)) {
                    result[9] = "Error:Das Feld in Spalte (Einkaufssumme) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(9);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(9));
                        if (!(intValue >= 0)) {
                            result[9] = "Error:Das Feld in Spalte (Einkaufssumme) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[9] = "Error:Das Feld in Spalte (Einkaufssumme) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Fertiggerichte
                // 10: integer
                if (d.stringValue(10).equals(null)) {
                    result[10] = "Error:Das Feld in Spalte (Fertiggerichte) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(10);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(10));
                        if (!(intValue >= 0)) {
                            result[10] = "Error:Das Feld in Spalte (Fertiggerichte) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[10] = "Error:Das Feld in Spalte (Fertiggerichte) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Tiefkuehlware
                // 11: integer
                if (d.stringValue(11).equals(null)) {
                    result[11] = "Error:Das Feld in Spalte (Tiefkuehlware) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(11);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(11));
                        if (!(intValue >= 0)) {
                            result[11] = "Error:Das Feld in Spalte (Tiefkuehlware) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[11] = "Error:Das Feld in Spalte (Tiefkuehlware) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Milchprodukte
                // 12: integer
                if (d.stringValue(12).equals(null)) {
                    result[12] = "Error:Das Feld in Spalte (Milchprodukte) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(12);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(12));
                        if (!(intValue >= 0)) {
                            result[12] = "Error:Das Feld in Spalte (Milchprodukte) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[12] = "Error:Das Feld in Spalte (Milchprodukte) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Backwaren
                // 13: integer
                if (d.stringValue(13).equals(null)) {
                    result[13] = "Error:Das Feld in Spalte (Backwaren) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(13);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(13));
                        if (!(intValue >= 0)) {
                            result[13] = "Error:Das Feld in Spalte (Backwaren) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[13] = "Error:Das Feld in Spalte (Backwaren) und Zeile (" + row_nummer + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Obst/Gemüse
                // 14: integer
                if (d.stringValue(14).equals(null)) {
                    result[14] = "Error:Das Feld in Spalte (Obst/Gemüse) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(14);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(14));
                        if (!(intValue >= 0)) {
                            result[14] = "Error:Das Feld in Spalte (Obst/Gemüse) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[14] = "Error:Das Feld in Spalte (Obst/Gemüse) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Spirituosen
                // 15: integer
                if (d.stringValue(15).equals(null)) {
                    result[15] = "Error:Das Feld in Spalte (Spirituosen) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(15);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(15));
                        if (!(intValue >= 0)) {
                            result[15] = "Error:Das Feld in Spalte (Spirituosen) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[15] = "Error:Das Feld in Spalte (Spirituosen) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Tiernahrung
                // 16: integer
                if (d.stringValue(16).equals(null)) {
                    result[16] = "Error:Das Feld in Spalte (Tiernahrung) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(16);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(16));
                        if (!(intValue >= 0)) {
                            result[16] = "Error:Das Feld in Spalte (Tiernahrung) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[16] = "Error:Das Feld in Spalte (Tiernahrung) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Bier
                // 17: integer
                if (d.stringValue(17).equals(null)) {
                    result[17] = "Error:Das Feld in Spalte (Bier) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(17);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(17));
                        if (!(intValue >= 0)) {
                            result[17] = "Error:Das Feld in Spalte (Bier) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[17] = "Error:Das Feld in Spalte (Bier) und Zeile (" + row_nummer + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Frischfleisch
                // 18: integer
                if (d.stringValue(18).equals(null)) {
                    result[18] = "Error:Das Feld in Spalte (Frischfleisch) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(18);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(18));
                        if (!(intValue >= 0)) {
                            result[18] = "Error:Das Feld in Spalte (Frischfleisch) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[18] = "Error:Das Feld in Spalte (Frischfleisch) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Drogerieartikel
                // 19: integer
                if (d.stringValue(19).equals(null)) {
                    result[19] = "Error:Das Feld in Spalte (Drogerieartikel) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(19);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(19));
                        if (!(intValue >= 0)) {
                            result[19] = "Error:Das Feld in Spalte (Drogerieartikel) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[19] = "Error:Das Feld in Spalte (Drogerieartikel) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Konserven
                // 20: integer
                if (d.stringValue(20).equals(null)) {
                    result[20] = "Error:Das Feld in Spalte (Konserven) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(20);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(20));
                        if (!(intValue >= 0)) {
                            result[20] = "Error:Das Feld in Spalte (Konserven) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[20] = "Error:Das Feld in Spalte (Konserven) und Zeile (" + row_nummer + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Kaffee/Tee
                // 21: integer
                if (d.stringValue(21).equals(null)) {
                    result[21] = "Error:Das Feld in Spalte (Kaffee/Tee) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(21);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(21));
                        if (!(intValue >= 0)) {
                            result[21] = "Error:Das Feld in Spalte (Kaffee/Tee) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[21] = "Error:Das Feld in Spalte (Kaffee/Tee) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Suessigkeiten
                // 22: integer
                if (d.stringValue(22).equals(null)) {
                    result[22] = "Error:Das Feld in Spalte (Suessigkeiten) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(22);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(22));
                        if (!(intValue >= 0)) {
                            result[22] = "Error:Das Feld in Spalte (Suessigkeiten) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[22] = "Error:Das Feld in Spalte (Suessigkeiten) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Wurstwaren
                // 23: integer
                if (d.stringValue(23).equals(null)) {
                    result[23] = "Error:Das Feld in Spalte (Wurstwaren) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(23);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(23));
                        if (!(intValue >= 0)) {
                            result[23] = "Error:Das Feld in Spalte (Wurstwaren) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[23] = "Error:Das Feld in Spalte (Wurstwaren) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                // ------------------------------------------------------------------------------------------Schreibwaren
                // 24: integer
                if (d.stringValue(24).equals(null)) {
                    result[24] = "Error:Das Feld in Spalte (Schreibwaren) und Zeile (" + row_nummer + "): iat leer";
                } else {
                    temp = d.stringValue(24);
                    try {
                        int intValue = Integer.parseInt(d.stringValue(24));
                        if (!(intValue >= 0)) {
                            result[24] = "Error:Das Feld in Spalte (Schreibwaren) und Zeile (" + row_nummer + "):"
                                    + temp + ".";
                        }
                    } catch (NumberFormatException e) {
                        result[24] = "Error:Das Feld in Spalte (Schreibwaren) und Zeile (" + row_nummer
                                + "):invald Value";
                    }
                }
                row_nummer++;
            }
        }
        //return result;
        String fdfd []= {"rrrr","ttttt","55555"};
        return  fdfd;
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

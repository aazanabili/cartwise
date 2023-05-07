import org.primefaces.event.RowEditEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Named
@ViewScoped
public class AnalysisBean implements Serializable {

    private Analysis analysis;
    private BarChartModel bestDayChart;
    private int numCluster = 5;
    private List<Customer> customerCluster;
    private List<Marketing> marketing;
    private String marketingInput;
    private Marketing selectedRow;

    public BarChartModel getBestDayChart() {
        return this.bestDayChart;
    }
    public void setBestDayChart(BarChartModel bestDayChart) {
        this.bestDayChart = bestDayChart;
    }

    public int getNumCluster() {
        return numCluster;
    }
    public void setNumCluster(int numCluster) {
        this.numCluster = numCluster;
    }

    public List<Customer> getCustomerCluster() {
        return customerCluster;
    }
    public void setCustomerCluster(List<Customer> customerCluster) {
        this.customerCluster = customerCluster;
    }

    public List<Marketing> getMarketing() { return marketing; }
    public void setMarketing(List<Marketing> marketing) { this.marketing = marketing; }

    public String getMarketingInput() { return marketingInput; }
    public void setMarketingInput(String marketingInput) { this.marketingInput = marketingInput; }

    public Marketing getSelectedRow() { return selectedRow; }
    public void setSelectedRow(Marketing selectedRow) { this.selectedRow = selectedRow; }


    @PostConstruct
    public void init() {
        String appPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
        String savePath = appPath + "uploads";

        File f = new File(savePath + File.separator + "kd.csv");
        try {
            analysis = new Analysis(f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadMarketing();
        createBestDayModel();
        createCustomerCluster();
    }

    private void createBestDayModel() {
        bestDayChart = new BarChartModel();
        ChartData dayData = new ChartData();

        BarChartDataSet dayBarDataSet = new BarChartDataSet();
        dayBarDataSet.setData(analysis.countDayOccurrences());

        List<String> dayBgColor = new ArrayList<>();
        dayBgColor.add("rgba(51, 168, 118, 0.8)");
        dayBgColor.add("rgba(57, 124, 163, 0.8)");
        dayBgColor.add("rgba(242, 147, 0, 0.8)");
        dayBgColor.add("rgba(214, 27, 27, 0.8)");
        dayBgColor.add("rgba(0, 184, 230, 0.8)");
        dayBgColor.add("rgba(128, 128, 255, 0.8)");
        dayBgColor.add("rgba(153, 51, 51, 0.8)");
        dayBarDataSet.setBackgroundColor(dayBgColor);
        dayData.addChartDataSet(dayBarDataSet);

        List<String> dayLabels = new ArrayList<>();
        dayLabels.add("Montag");
        dayLabels.add("Dienstag");
        dayLabels.add("Mittwoch");
        dayLabels.add("Donnerstag");
        dayLabels.add("Freitag");
        dayLabels.add("Samstag");
        dayData.setLabels(dayLabels);
        bestDayChart.setData(dayData);

        BarChartOptions dayOptions = new BarChartOptions();
        CartesianScales dayCScales = new CartesianScales();
        CartesianLinearAxes dayLinearAxes = new CartesianLinearAxes();
        dayLinearAxes.setOffset(true);
        CartesianLinearTicks dayTicks = new CartesianLinearTicks();
        dayTicks.setBeginAtZero(true);
        dayLinearAxes.setTicks(dayTicks);
        dayCScales.addYAxesData(dayLinearAxes);
        dayOptions.setScales(dayCScales);

        Title dayTitle = new Title();
        dayTitle.setDisplay(false);
        dayTitle.setText("Bester Einkaufstag");
        dayOptions.setTitle(dayTitle);

        Legend dayLegend = new Legend();
        dayLegend.setDisplay(false);
        dayLegend.setPosition("top");
        LegendLabel dayLegendLabels = new LegendLabel();
        dayLegendLabels.setFontStyle("bold");
        dayLegendLabels.setFontColor("#2980B9");
        dayLegendLabels.setFontSize(24);
        dayLegend.setLabels(dayLegendLabels);
        dayOptions.setLegend(dayLegend);

        bestDayChart.setOptions(dayOptions);
    }

    public void createCustomerCluster() {
        try {
            this.customerCluster = analysis.getCustomerCluster(numCluster);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCustomerCluster() {
        try {
            this.customerCluster = analysis.getCustomerCluster(numCluster);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMarketing() {
        marketing = new ArrayList<>();

        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "marketing";
        File saveDir = new File(path);

        if (!saveDir.exists()) saveDir.mkdir();
        List<String> results = new ArrayList<>();

        File f = new File(saveDir + File.separator + "marketing.csv");

        try {
            Scanner rowScanner = new Scanner(f);

            while (rowScanner.hasNextLine()) {
                rowScanner.useDelimiter(",");
                results.add(rowScanner.next());
            }

            int i = 0;
            for (String str : results) {
                marketing.add(new Marketing(i, str));
                i++;
            }
        } catch ( FileNotFoundException e ) {
            // do nothing
        }
    }

    public void saveMarketing() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "marketing";
        File saveDir = new File(path);

        if (!saveDir.exists()) saveDir.mkdir();

        File f = new File(saveDir + File.separator + "marketing.csv");
        f.delete();

        try {
            FileWriter fw = new FileWriter(f);

            for (int i = 0; i < marketing.size() - 1; i++) {
                fw.write(marketing.get(i).getName() + ",");
            }

            fw.write(marketing.get(marketing.size() - 1).getName());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onRowEdit(RowEditEvent<Marketing> event) {
        FacesMessage msg = new FacesMessage("Edited row", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Marketing> event) {
        FacesMessage msg = new FacesMessage("Cancelled edit", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        if (!(marketingInput.trim().length() == 0)) {
            marketing.add(new Marketing(marketing.size() + 1, marketingInput));
            saveMarketing();

            FacesMessage msg = new FacesMessage("Added new row");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Maßnahme darf nicht leer sein!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void delete(Marketing m) {
        marketing.remove(m);
        saveMarketing();

        FacesMessage msg = new FacesMessage("Deleted row", String.valueOf(m.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}

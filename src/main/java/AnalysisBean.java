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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class AnalysisBean implements Serializable {

    private Analysis analysis;
    private BarChartModel bestDayChart;

    public BarChartModel getBestDayChart() {
        return this.bestDayChart;
    }

    public void setBestDayChart(BarChartModel bestDayChart) {
        this.bestDayChart = bestDayChart;
    }


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

        createBestDayModel();
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
}

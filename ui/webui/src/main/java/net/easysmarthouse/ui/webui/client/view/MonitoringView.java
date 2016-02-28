/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.client.view;

import net.easysmarthouse.provider.device.exception.DeviceException;
import net.easysmarthouse.provider.device.sensor.Sensor;
import net.easysmarthouse.provider.device.sensor.SensorType;
import net.easysmarthouse.ui.i18n.Monitoring;
import net.easysmarthouse.ui.webui.client.messages.MessagesHolder;
import net.easysmarthouse.ui.webui.client.model.MonitoringModel;
import net.easysmarthouse.ui.webui.client.util.MessageFormat;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.LineChart;
import com.googlecode.gwt.charts.client.corechart.LineChartOptions;
import com.googlecode.gwt.charts.client.options.CurveType;
import com.googlecode.gwt.charts.client.options.Legend;
import com.googlecode.gwt.charts.client.options.LegendPosition;
import com.googlecode.gwtmvc.client.Controller;
import com.googlecode.gwtmvc.client.ModelProxy;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author rusakovich
 */
public class MonitoringView extends GridTabView<MonitoringModel> {

    private static final double MEASURE_DELAY = 0.5;
    private Monitoring monitoringMessages = MessagesHolder.getInstance().getMonitoringMessages();
    private LineChart temperatureChart;
    private DataTable temperatureDataTable;
    private LineChart humidityChart;
    private DataTable humidityDataTable;
    private LineChart pressureChart;
    private DataTable pressureDataTable;
    private VerticalPanel monitoringPanel;
    private List dataLabels = new ArrayList();
    private Double measureIteration = 0.0;

    public MonitoringView(String id, Controller controller, ModelProxy[] models) {
        super(id, controller, models);
    }

    @Override
    public Widget createWidget() {
        Widget widget = super.createWidget();

        monitoringPanel = new VerticalPanel();
        super.addWidget(monitoringPanel);

        return widget;
    }

    private void drawSensorsChart(LineChart chart, DataTable dataTable, SensorType sensorType) {
        LineChartOptions options = LineChartOptions.create();

        String chartTitle;
        switch (sensorType) {
            case TemperatureSensor:
                chartTitle = monitoringMessages.chartTemperatureTitle();
                break;
            case HumiditySensor:
                chartTitle = monitoringMessages.chartHumidityTitle();
                break;
            case PressureSensor:
                chartTitle = monitoringMessages.chartPressureTitle();
                break;
            default:
                chartTitle = monitoringMessages.chartOtherTitle();
        }

        options.setTitle(chartTitle);
        options.setCurveType(CurveType.FUNCTION);

        LegendPosition legendPosition = LegendPosition.BOTTOM;
        Legend legend = Legend.create(legendPosition);
        options.setLegend(legend);

        dataTable.addColumn(ColumnType.NUMBER, monitoringMessages.chartColumnTiming());
        chart.draw(dataTable, options);
    }

    @Override
    public void onRender() {
        // Create the API Loader
        ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
        chartLoader.loadApi(new Runnable() {
            @Override
            public void run() {
                temperatureChart = new LineChart();
                temperatureChart.setVisible(false);

                humidityChart = new LineChart();
                humidityChart.setVisible(false);

                pressureChart = new LineChart();
                pressureChart.setVisible(false);

                temperatureDataTable = DataTable.create();
                humidityDataTable = DataTable.create();
                pressureDataTable = DataTable.create();

                monitoringPanel.add(temperatureChart);
                monitoringPanel.add(humidityChart);
                monitoringPanel.add(pressureChart);

                drawSensorsChart(temperatureChart, temperatureDataTable, SensorType.TemperatureSensor);
                drawSensorsChart(humidityChart, humidityDataTable, SensorType.HumiditySensor);
                drawSensorsChart(pressureChart, pressureDataTable, SensorType.PressureSensor);
            }
        });

        grid = new Grid();
        grid.addStyleName("table-striped");
        grid.resize(1, 3);
        grid.setHTML(0, 0, monitoringMessages.tableTitleColumnNumber());
        grid.setHTML(0, 1, monitoringMessages.tableTitleColumnName());
        grid.setHTML(0, 2, monitoringMessages.tableTitleColumnValue());
        super.onRender();

        monitoringPanel.add(grid);
    }

    private LineChart getChart(SensorType sensorType) {
        switch (sensorType) {
            case TemperatureSensor:
                return this.temperatureChart;
            case HumiditySensor:
                return this.humidityChart;
            case PressureSensor:
                return this.pressureChart;
            default:
                return null;
        }
    }

    private DataTable getDataTable(SensorType sensorType) {
        switch (sensorType) {
            case TemperatureSensor:
                return this.temperatureDataTable;
            case HumiditySensor:
                return this.humidityDataTable;
            case PressureSensor:
                return this.pressureDataTable;
            default:
                return null;
        }
    }

    @Override
    void delegateModelChange(MonitoringModel monitoringModel) {
        final List<Sensor> sensors = monitoringModel.getValue();

        int row = 1;
        grid.resizeRows(sensors.size() + 1);

        measureIteration += MEASURE_DELAY;

        Map<SensorType, List> sensorDatas = new EnumMap<SensorType, List>(SensorType.class);
        for (SensorType type : SensorType.values()) {
            List sensorsData = new ArrayList();
            sensorsData.add(measureIteration);
            sensorDatas.put(type, sensorsData);
        }

        for (Sensor sensor : sensors) {

            SensorType currSensorType = sensor.getSensorType();

            String sensorLabel = sensor.getLabel();
            if (!dataLabels.contains(sensorLabel)) {
                getDataTable(currSensorType).addColumn(ColumnType.NUMBER, sensorLabel);
                dataLabels.add(sensorLabel);
            }

            grid.setHTML(row, 0, Integer.toString(row));
            grid.setHTML(row, 1, sensor.getLabel());

            String msg;
            try {
                Double value = sensor.getValue();
                sensorDatas.get(currSensorType).add(value);
                msg = MessageFormat.getFormatted(value, 2);
            } catch (DeviceException ex) {
                msg = ex.getMessage();
            }
            grid.setHTML(row, 2, msg);

            row++;
        }

        Set<SensorType> sensorTypes = sensorDatas.keySet();
        for (SensorType sensorType : sensorTypes) {
            List sensorData = sensorDatas.get(sensorType);
            if (sensorData.size() > 1) {
                getChart(sensorType).setVisible(true);
                getDataTable(sensorType).addRow(sensorData.toArray(new Object[sensorData.size()]));
                getChart(sensorType).redraw();
            }
        }


        grid.setVisible(true);
    }
}

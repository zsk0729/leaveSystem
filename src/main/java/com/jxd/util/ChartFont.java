package com.jxd.util;

import java.awt.Font;
import java.awt.Rectangle;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
//设置文字样式

public class ChartFont {

    //柱状图
    public void setChartByFont(JFreeChart chart) {
        //1. 图形标题文字设置
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("宋体",Font.BOLD,20));

        //2. 图形X轴坐标文字的设置
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setLabelFont(new Font("宋体",Font.BOLD,22));  //设置X轴坐标上标题的文字
        axis.setTickLabelFont(new Font("宋体",Font.BOLD,15));  //设置X轴坐标上的文字

        //2. 图形Y轴坐标文字的设置
        ValueAxis valueAxis = plot.getRangeAxis();
        valueAxis.setLabelFont(new Font("宋体",Font.BOLD,15));  //设置Y轴坐标上标题的文字
        valueAxis.setTickLabelFont(new Font("sans-serif",Font.BOLD,12));//设置Y轴坐标上的文字
    }

    // 饼状图
    public void setJfreePie(JFreeChart chart) {

        // 处理图形上的乱码
        // 处理主标题的乱码
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
        // 处理子标题乱码
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));
        // 获取图表区域对象
        PiePlot3D categoryPlot = (PiePlot3D) chart.getPlot();
        // 处理图像上的乱码
        categoryPlot.setLabelFont(new Font("宋体", Font.BOLD, 15));
        // 设置图形的生成格式为（上海 2 （10%））
        String format = "{0} {1} ({2})";
        categoryPlot.setLabelGenerator(new StandardPieSectionLabelGenerator(format));
        // 使用ChartFrame对象显示图像
    }

    // 折线图
    public void setJfreeLine(JFreeChart chart) {
        // 处理图形上的乱码
        // 处理主标题的乱码
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
        // 处理子标题乱码
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));
        // 获取图表区域对象
        CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
        // 获取X轴的对象
        CategoryAxis categoryAxis = (CategoryAxis) categoryPlot.getDomainAxis();
        // 获取Y轴的对象
        NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
        // 处理X轴上的乱码
        categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理X轴外的乱码
        categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理Y轴上的乱码
        numberAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理Y轴外的乱码
        numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理Y轴上显示的刻度，以1作为1格
        numberAxis.setAutoTickUnitSelection(false);
        NumberTickUnit unit = new NumberTickUnit(1);
        numberAxis.setTickUnit(unit);
        // 获取绘图区域对象
        LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) categoryPlot.getRenderer();
        // 在图形上显示数字
        lineAndShapeRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        lineAndShapeRenderer.setBaseItemLabelsVisible(true);
        lineAndShapeRenderer.setBaseItemLabelFont(new Font("宋体", Font.BOLD, 15));
        // 在图形上添加转折点（使用小矩形显示）
        Rectangle shape = new Rectangle(10, 10);
        lineAndShapeRenderer.setSeriesShape(0, shape);
        lineAndShapeRenderer.setSeriesShapesVisible(0, true);
    }

}

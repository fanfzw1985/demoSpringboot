package cn.fzw.utils;

import java.awt.Font;
import java.awt.Rectangle;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class FoldLineTools {
	/**
	 * 创建一个折线图
	 * 
	 * @return
	 */
	public static JFreeChart createFoldLine(DefaultCategoryDataset dataset) {
	    JFreeChart chart = ChartFactory.createLineChart("近六个月日常开销", "月份", "金额（元）",
	            dataset, PlotOrientation.VERTICAL, true, true, true);
	    // 数据的行数 即折线的条数
	    int datasetSize = dataset.getRowCount();
	    getChartByFont(chart, datasetSize);

	    return chart;

	}

	/**
	 * 处理折线图上的文字
	 * 
	 * @param chart
	 * @param datasetSize
	 *            数据的行数 即折线的条数
	 */
	private static void getChartByFont(JFreeChart chart, int datasetSize) {
	    // 处理主标题的文字
	    chart.getTitle().setFont(new Font("楷体", Font.BOLD, 18));
	    // 处理子标题文字
	    chart.getLegend().setItemFont(new Font("楷体", Font.BOLD, 15));
	    // 获取图表区域
	    CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
	    // 获取X轴
	    CategoryAxis categoryAxis = (CategoryAxis) categoryPlot.getDomainAxis();
	    // 获取Y轴
	    NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
	    // 处理X轴上的文字
	    categoryAxis.setTickLabelFont(new Font("楷体", Font.BOLD, 12));
	    // 处理X轴外的文字
	    categoryAxis.setLabelFont(new Font("楷体", Font.BOLD, 12));
	    // 处理Y轴上的文字
	    numberAxis.setTickLabelFont(new Font("楷体", Font.BOLD, 15));
	    // 处理Y轴外的文字
	    numberAxis.setLabelFont(new Font("楷体", Font.BOLD, 15));
	    // 处理Y轴上显示的刻度，以5000作为1格
	    numberAxis.setAutoTickUnitSelection(false);
	    NumberTickUnit unit = new NumberTickUnit(5000);
	    numberAxis.setTickUnit(unit);
	    // 获取绘图区域
	    LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) categoryPlot
	            .getRenderer();
	    // 在图形上显示数字
	    lineAndShapeRenderer
	            .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	    lineAndShapeRenderer.setBaseItemLabelsVisible(true);
	    lineAndShapeRenderer
	            .setBaseItemLabelFont(new Font("楷体", Font.BOLD, 15));
	    // 在图形上添加转折点（小矩形）
	    Rectangle shape = new Rectangle(5, 5);
	    for (int i = 0; i < datasetSize; i++) {
	        lineAndShapeRenderer.setSeriesShape(i, shape);
	        lineAndShapeRenderer.setSeriesShapesVisible(i, true);
	    }
	}
}

/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.achartengine.chartdemo.demo.chart;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer.FillOutsideLine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;

/**
 * Sales comparison demo chart.
 */
public class SalesComparisonChart extends AbstractDemoChart {
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Sales comparison";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "NO.of times played in  last 2 days (interpolated line and area charts)";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Intent execute(Context context) {
    String[] titles = new String[] { "no.of times played on 7/12", "no. of times played on 7/13",
        "Difference between 7/12 and 7/13 sales" };
    List<double[]> values = new ArrayList<double[]>();
    values.add(new double[] { 3, 1, 4, 2, 1, 2, 11, 12, 12, 1,
        14, 6, 7, 9, 2, 8, 7, 2, 2, 1, 6, 7, 8, 9 });
    values.add(new double[] { 9, 2, 8, 7, 2, 2, 1, 6, 7, 8, 9, 5, 7, 8, 9, 10, 20, 15, 1, 2, 4, 6, 4, 7 });
    int length = values.get(0).length;
    double[] diff = new double[length];
    for (int i = 0; i < length; i++) {
      diff[i] = values.get(0)[i] - values.get(1)[i];
    }
    values.add(diff);
    int[] colors = new int[] { Color.YELLOW, Color.WHITE, Color.BLUE };
    PointStyle[] styles = new PointStyle[] { PointStyle.POINT, PointStyle.POINT, PointStyle.POINT };
    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
    setChartSettings(renderer, "NO.of times played in  last 2 days", "hours", "number", 0.75,
        24.25, -5, 27, Color.BLACK, Color.BLACK);
    renderer.setXLabels(24);
    renderer.setYLabels(10);
    renderer.setChartTitleTextSize(20);
    renderer.setTextTypeface("sans_serif", Typeface.BOLD);
    renderer.setLabelsTextSize(14f);
    renderer.setAxisTitleTextSize(15);
    renderer.setLegendTextSize(15);
    length = renderer.getSeriesRendererCount();

    for (int i = 0; i < length; i++) {
      XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
      if (i == length - 1) {
        FillOutsideLine fill = new FillOutsideLine(FillOutsideLine.Type.BOUNDS_ALL);
        fill.setColor(Color.GREEN);
        seriesRenderer.addFillOutsideLine(fill);
      }
      seriesRenderer.setLineWidth(2.5f);
      seriesRenderer.setDisplayChartValues(true);
      seriesRenderer.setChartValuesTextSize(10f);
    }
    return ChartFactory.getCubicLineChartIntent(context, buildBarDataset(titles, values), renderer,
        0.5f);
  }
}

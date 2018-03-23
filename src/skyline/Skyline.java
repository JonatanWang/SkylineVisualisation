/*
 * Visual skyline
 */
package skyline;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Wang Zheng-Yu <zhengyuw@kth.se>
 */
public class Skyline extends Application {
    
    private static Model m;
    private static final int NUM_OF_HOUSES = 10;
    private static int h[][];
    private static List<int[]> points;
    
    @Override
    public void start(Stage primaryStage) {
        
        points = new ArrayList<>();
        m = new Model();
        h = new int[NUM_OF_HOUSES][3];
        for(int i = 0; i < NUM_OF_HOUSES; i ++) {
            
            int x1 = (int) Math.abs(Math.random() * 700); // x1 [0 - 700] block start point
            int x2 = (int) Math.abs(Math.random() * 100 + 20) + x1; // x2 = x1 + [20 - 120]
            int y = (int) Math.abs(Math.random() * 200 + 10); // height [10 - 210]
            int newHouse[] = {x1, y, x2};
            h[i] = newHouse;
        }
        
         //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Horitzontal distance");
        yAxis.setLabel("Vertical height");
        
        // create area chart representing houses
        final AreaChart<Number,Number> areaChart = 
            new AreaChart<>(xAxis,yAxis);
        areaChart.setTitle("Houses");
 
        for(int i= 0; i < h.length; i ++) {
            XYChart.Series house = new XYChart.Series();
            house.setName("House " + i);
            house.getData().add(new XYChart.Data<>(h[i][0], 0));
            house.getData().add(new XYChart.Data<>(h[i][0], h[i][1]));
            house.getData().add(new XYChart.Data<>(h[i][2], h[i][1]));
            house.getData().add(new XYChart.Data<>(h[i][2], 0));
            areaChart.getData().add(house);
        }
        
        //creating the line chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<>(xAxis,yAxis);
                
        lineChart.setTitle("Skyline");
       
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("skyline points");
        
        points = m.skyline(h);
        //populating the series with data
        series.getData().add(new XYChart.Data<>(points.get(0)[0], 0));
        for(int i = 0; i < points.size() - 1; i ++) {
            series.getData().add(new XYChart.Data<>(points.get(i)[0], points.get(i)[1]));
            series.getData().add(new XYChart.Data<>(points.get(i + 1)[0], points.get(i)[1]));
        }
        points.forEach((p) -> {
            series.getData().add(new XYChart.Data(p[0], p[1]));
        });
        lineChart.getData().add(series);
        
        StackPane root = new StackPane();
        Button btn = new Button();
        btn.setText("Draw Skyline");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("skyline!");
            if(root.getChildren().contains(areaChart)) {
                root.getChildren().remove(areaChart);
                root.getChildren().add(lineChart);
            }
        });
        
        root.getChildren().add(areaChart);
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 1200, 800);
        
        primaryStage.setTitle("Skyline problem by divid & conquer, NB44");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
}

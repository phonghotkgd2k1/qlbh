package Business;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

import java.awt.Font;
import javafx.embed.swing.JFXPanel;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class tysuatloinhuan extends JFXPanel {

	public BarChart createChart() {
            
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("%");
        
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.getData().add(new XYChart.Data("", 32.4));
        
        BarChart chart = new BarChart(xAxis, yAxis);
        chart.getData().addAll(dataSeries1);
        chart.setTitle("");
        chart.setLegendVisible(false);
        chart.setCategoryGap(200);
        chart.lookupAll(".chart-bar")
            .forEach(n -> n.setStyle("-fx-bar-fill:#1e2456;"));
        return chart;
    }

    private Scene createScene() {
    	BorderPane root = new BorderPane();
        Scene scene = new Scene(root, javafx.scene.paint.Color.ALICEBLUE);
        root.setCenter(createChart());
        return (scene);
    }

	public tysuatloinhuan() {
		setScene(createScene());
        setFont(new Font("Arial", Font.BOLD, 20));
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "T\u1EF7 Su\u1EA5t L\u1EE3i Nhu\u1EADn", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial",Font.PLAIN,16), null));
               
	}

}

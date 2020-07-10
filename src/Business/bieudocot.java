package Business;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

public class bieudocot extends JFXPanel {
	private double doanhthu;
	private double loinhuan;
	
	public void setDoanhthu(double doanhthu) {
		this.doanhthu = doanhthu;
	}

	public void setLoinhuan(double loinhuan) {
		this.loinhuan = loinhuan;
	}

	public BarChart createChart() {
    	
		 doanhthu = Double.parseDouble(  DonHang.chinhsua(  thongkedoanhthu.lbtongdoanhthu_kq.getText() )   );
		 loinhuan = Double.parseDouble(  DonHang.chinhsua(  thongkedoanhthu.lbtongloinhuan_kq.getText() ) );
	
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("vnd");
     //   yAxis.setTickLabelFill(  Paint.valueOf("red"));
        XYChart.Series dataSeries1 = new XYChart.Series();
        
        dataSeries1.setName("Tiền vốn");
        dataSeries1.getData().add(new XYChart.Data("Tiền vốn", (doanhthu - loinhuan) ));

        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("Doanh Thu");
        dataSeries2.getData().add(new XYChart.Data("Doanh thu",  doanhthu ) );

        BarChart chart = new BarChart(xAxis, yAxis);
        chart.getData().addAll(dataSeries1);
        chart.getData().addAll(dataSeries2);
        chart.setTitle("");
        //     chart.setLegendSide( Side.RIGHT);
        
        
        return chart;
    }

	private Scene createScene() {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, javafx.scene.paint.Color.ALICEBLUE);
		root.setCenter(createChart());
		return (scene);
	}

	public bieudocot() {
		
		setScene(createScene());
		setFont(new Font("Arial", Font.BOLD, 20));
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"l\u1EE3i nhu\u1EADn v\u00E0 kinh doanh ", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 16), new Color(0, 0, 0)));
	}
}

package Business;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

public class bieudoduong extends JFXPanel {

	private int d1;
	private int m1;
	private int y1;
	private int d2;
	private int m2;
	private int y2;

	private double get_doanhthu(int d, int m, int y) {
		String sql = "SELECT sum(CTHD.ThanhTien * (100 - hd.GiamGia)/100 ) FROM ChiTietHoaDon CTHD  JOIN HoaDonBan HD ON HD.MaHD = CTHD.MaHD where DAY(HD.NgayTao)= ?  and MONTH(HD.NGAYTAO) =?  and YEAR(HD.NgayTao) =?";
		try {
			PreparedStatement pr = DangNhap.con.prepareStatement(sql);
			pr.setInt(1, d);
			pr.setInt(2, m);
			pr.setInt(3, y);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (Exception e) {
			System.out.println("dieudoduong - get_doanhthu: " + e.getMessage());
		}
		return 0;
	}

	private double get_chiphi(int d, int m, int y) {
		String sql = "SELECT SUM( m.GiaNhap * CTHD.SoLuong) FROM MatHang m  JOIN ChiTietHoaDon CTHD ON M.MaHang = CTHD.MaHang JOIN HoaDonBan HD ON HD.MaHD = CTHD.MaHD where DAY(HD.NgayTao) =?  and MONTH(HD.NGAYTAO) = ?  and YEAR(HD.NgayTao) = ?";
		try {
			PreparedStatement pr = DangNhap.con.prepareStatement(sql);
			pr.setInt(1, d);
			pr.setInt(2, m);
			pr.setInt(3, y);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				return rs.getDouble(1);
			}
		} catch (Exception e) {
			System.out.println("dieudoduong - get_chiphi: " + e.getMessage());
		}
		return 0;
	}

	private LineChart createChart() {

		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("vnd");
		LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
		// lineChart.setTitle("Line Chart");
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Doanh Thu");

		XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
		series2.setName("Lợi Nhuận");

		SimpleDateFormat simple = new SimpleDateFormat("d/M/yy");
		String ngay1 = d1 + "/" + m1 + "/" + y1;
		String ngay2 = d2 + "/" + m2 + "/" + y2;
		Date date1;
		Date date2;
		Calendar calendar = Calendar.getInstance();
		long tongngay = 0;
		try {
			date1 = simple.parse(ngay1);
			date2 = simple.parse(ngay2);
			tongngay = date2.getTime() - date1.getTime();
			tongngay = tongngay / 1000 / 60 / 60 / 24;

			calendar.setTime(date1);

			for (int i = 0; i <= tongngay; i++) {
				int d = calendar.get(calendar.DATE);
				int m = (calendar.get(calendar.MONTH) + 1);
				int y = calendar.get(calendar.YEAR);
				double doanhthu = get_doanhthu(d, m, y);

				series.getData().add(new XYChart.Data<String, Number>(simple.format(calendar.getTime()), doanhthu));

				double loinhuan = doanhthu - get_chiphi(d, m, y);

				series2.getData().add(new XYChart.Data<String, Number>(simple.format(calendar.getTime()), loinhuan));

				calendar.add(calendar.DATE, +1);

			}
		} catch (ParseException e) {

		}

		lineChart.getData().addAll(series, series2);
		return lineChart;
	}

	private Scene createScene() {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, javafx.scene.paint.Color.ALICEBLUE);
		root.setCenter(createChart());
		return (scene);
	}

	public bieudoduong() {
		this.d1 = cardpanel_baocao.d1;
		this.m1 = cardpanel_baocao.m1;
		this.y1 = cardpanel_baocao.y1;
		this.d2 = cardpanel_baocao.d2;
		this.m2 = cardpanel_baocao.m2;
		this.y2 = cardpanel_baocao.y2;
		setScene(createScene());
		setFont(new Font("Arial", Font.BOLD, 20));
		setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Bi\u1EC3u \u0110\u1ED3 Doanh Thu",
						TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 16), new Color(0, 0, 0)));
	}

}

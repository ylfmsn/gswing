package com.suntoon.plugins;

import com.suntoon.MapPane;
import com.suntoon.utils.RangeHashMap;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.sam.swing.utils.Pair;

import java.awt.*;

/**
 * 标尺插件
 * 
 * @author sam
 *
 */
public class ScalebarPlugin extends PluginBase {

	/**
	 * 标尺的表示范围 double 真实的地理空间跨度，单位米 Pair.Integer 表示的比例尺
	 */
	private static RangeHashMap<Double, Pair<Integer,String>> rulers = new RangeHashMap<>();

	/**
	 * 地理信息对象生成工具
	 */
	private static GeometryFactory gf = JTSFactoryFinder.getGeometryFactory();

	/**
	 * 标尺的字体
	 */
	private static final Font font = new Font("宋体", Font.PLAIN, 12);

	/**
	 * 计算地图横跨范围开始点的坐标
	 */
	private Point pointBegin;

	/**
	 * 计算地图横跨范围结束点的坐标
	 */
	private Point pointEnd;

	/**
	 * 标尺最大长度，默认5cm
	 */
	private double maxLength = 0.01;

	/**
	 * 默认的标尺宽度像素数
	 */
	private int maxPixels;

	/**
	 * 当前显示的dpi
	 */
	private int dpi = 96;

	/**
	 * 一个像素代表多少米
	 */
	private double pixelInMeters;

	/**
	 * 初始化默认标尺表示范围
	 */
	static {
		rulers.put(0.0, new Pair<Integer, String>(1, "1 m"));
		rulers.put(1.01, new Pair<Integer, String>(2, "2 m"));
		rulers.put(2.01, new Pair<Integer, String>(5, "5 m"));
		rulers.put(5.01, new Pair<Integer, String>(10, "10 m"));
		rulers.put(10.01, new Pair<Integer, String>(20, "20 m"));
		rulers.put(20.01, new Pair<Integer, String>(50, "50 m"));
		rulers.put(50.01, new Pair<Integer, String>(100, "100 m"));
		rulers.put(100.01, new Pair<Integer, String>(200, "200 m"));
		rulers.put(200.01, new Pair<Integer, String>(500, "500 m"));
		rulers.put(500.01, new Pair<Integer, String>(1000, "1 Km"));
		rulers.put(1000.01, new Pair<Integer, String>(2000, "2 Km"));
		rulers.put(2000.01, new Pair<Integer, String>(5000, "5 Km"));
		rulers.put(5000.01, new Pair<Integer, String>(10000, "10 Km"));
		rulers.put(10000.01, new Pair<Integer, String>(20000, "20 Km"));
		rulers.put(20000.01, new Pair<Integer, String>(50000, "50 Km"));
		rulers.put(50000.01, new Pair<Integer, String>(100000, "100 Km"));
		rulers.put(100000.01, new Pair<Integer, String>(200000, "200 Km"));
		rulers.put(200000.01, new Pair<Integer, String>(500000, "500 Km"));
		rulers.put(500000.01, new Pair<Integer, String>(1000000, "1000 Km"));
		rulers.put(1000000.01, new Pair<Integer, String>(2000000, "2000 Km"));
		rulers.put(2000000.01, new Pair<Integer, String>(5000000, "5000 Km"));
		rulers.put(5000000.01, new Pair<Integer, String>(10000000, "10000 Km"));
		rulers.put(10000000.01, new Pair<Integer, String>(20000000, "20000 Km"));
	}

	/**
	 * 标尺插件
	 * 
	 * @param title
	 * @param group
	 * @param map
	 */
	public ScalebarPlugin(String title, String group, MapPane map) {
		super(title, group, map);
		pointBegin = gf.createPoint(new Coordinate());
		pointEnd = gf.createPoint(new Coordinate());
		dpi = Toolkit.getDefaultToolkit().getScreenResolution();
		double pixelPerDpi = 1.0 / dpi;
		pixelInMeters = pixelPerDpi * .0254; // to meters per pixel
		maxPixels = (int) Math.ceil(maxLength / pixelInMeters);
	}

	/**
	 * {@inheritDoc} 将标尺直接画到图片上
	 */
	@Override
	public void beforePaint(Graphics2D g2d) {

		MapPane mapPane = this.getMapPane();
		ReferencedEnvelope bounds = mapPane.getMapContent().getViewport().getBounds();
		Coordinate begin = pointBegin.getCoordinate();
		begin.setOrdinate(Coordinate.X, bounds.getMinX());
		begin.setOrdinate(Coordinate.Y, bounds.getMinY());

		Coordinate end = pointEnd.getCoordinate();
		end.setOrdinate(Coordinate.X, bounds.getMaxX());
		end.setOrdinate(Coordinate.Y, bounds.getMinY());

		// 转换成了米
		Rectangle location = mapPane.getVisibleRectangle();
		double distance = pointEnd.distance(pointBegin) * 100000;
		double pixelPerDistance = distance / location.getWidth(); // 每个像素代表的距离
		double max = maxPixels * pixelPerDistance;
		
		if (max < 0.25) return;

		Pair<Integer, String> ruler = rulers.get(max);
		g2d.setFont(font);
		FontMetrics fontMetrics = g2d.getFontMetrics();
		int sWidth = fontMetrics.stringWidth(ruler.getValue());
		int x = (int)(ruler.getKey() / pixelPerDistance);
		int iX = location.width - (int) x - 5;
		int iY = location.height - 10;

		g2d.setColor(Color.WHITE);
		int x2 = location.width - 5;
		int i = (iX + x2) / 2;
		
		g2d.drawString(ruler.getValue(), iX + ((int) x - sWidth) / 2, iY - 7);
		g2d.drawLine(iX, iY,x2 , iY);
		//以下是画3个小短线
		g2d.drawLine(iX, iY, iX, iY - 5);
		g2d.drawLine(i, iY, i, iY - 5);
		g2d.drawLine(x2, iY, x2, iY - 5);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterPaint(Graphics2D g2d, int dx, int dy) {
	}

}

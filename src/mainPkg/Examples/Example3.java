package mainPkg.Examples;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import MathPkg.Lines.Line2D;
import MathPkg.Points.Point2D;
import MathPkg.Rays.Ray2D;
import MathPkg.Segments.Segment2D;
import MathPkg.Shapes.Shapes2D.Circle;
import MathPkg.Shapes.Shapes2D.Reflector2D;
import mainPkg.Main;
import mainPkg.events.Event;
import mainPkg.events.types.MouseEv;
import mainPkg.Graphics.DrawUtils;

public class Example3 implements Example {
	
	private ArrayList<Event<?>> queue = new ArrayList<>();
	
	public static Reflector2D[] refs = new Reflector2D[75];
	
	public static Point2D A = new Point2D(500, 500);
	public static Point2D Aprime = new Point2D(500, 600);
	public static Line2D line = new Line2D(A, Aprime);
	
	public static Ray2D ray = new Ray2D(A, Aprime);
	
	public static Settings.SETTING scrollSetting = Settings.SETTING.LIGHT_INTENSITY;
	public static Settings settings;
	
	class Settings
	{
		enum SETTING
		{
			RAY_NB(700),
			MAX_BOUNCES(20),
			LIGHT_INTENSITY(200),
			FOV(360),
			RAY_ANGLE_OFFSET(1),
			SHOW_RAYS(0);
			
			private int value;
			
			private SETTING(int val)
			{
				this.value = val;
			}
			
			public void setValue(int val)
			{
				this.value = val;
			}
		}
		
		private HashMap<SETTING, Integer> settings = new HashMap<>();
		
		public Settings()
		{
			for(int i = 0; i < SETTING.values().length; i++)
			{
				settings.put(SETTING.values()[i], SETTING.values()[i].value);
			}
		}
		
		public int getSettingValue(SETTING set)
		{
			return settings.get(set);
		}
		
		public void setSettingValue(SETTING set, int val)
		{
			settings.put(set, val);
		}
		
		public int indexOfSetting(SETTING set)
		{
			for(int i = 0; i < SETTING.values().length; i++)
			{
				if(SETTING.values()[i] == set) return i;
			}
			
			return -1;
		}
	}
	
	public Example3()
	{
		settings = new Settings();
		
		for(int i = 0; i < refs.length; i++)
		{
			int x = (int)(Math.random() * 1000.);
			int y = (int)(Math.random() * 1000.);
			
			int r = (int)(Math.random() * 75.);
			
			refs[i] = new Circle(new Point2D(x, y), r);
		}
		
		/*refs[refs.length-4] = new Line2D(new Point2D(0, 0), new Point2D(0, 999));
		refs[refs.length-3] = new Line2D(new Point2D(0, 999), new Point2D(999, 999));
		refs[refs.length-2] = new Line2D(new Point2D(999, 999), new Point2D(999, 0));
		refs[refs.length-1] = new Line2D(new Point2D(999, 0), new Point2D(0, 0));*/
	}
	
	public void addToQueue(Event<?> ev)
	{
		queue.add(ev);
	}
	
	private void resolveEvent(Event<?> event)
	{
		if(event == null) return;
		if(event.getClass() != MouseEv.class) return;
		
		MouseEv mev = (MouseEv)event;
		
		switch (mev) {
			case click:
			{
				scrollSetting = Settings.SETTING.values()[(settings.indexOfSetting(scrollSetting) +1) % Settings.SETTING.values().length];
				break;
			}
			case move:
			{
				int values[] = mev.getValues();
				ray.origin.x = values[0];
				ray.origin.y = values[1];
				break;
			}
			case scroll:
			{
				int scrollAmount = mev.getValues()[0];
				
				settings.setSettingValue(scrollSetting, scrollAmount < 0 ? (int)(settings.getSettingValue(scrollSetting) * 0.9) : (int)(settings.getSettingValue(scrollSetting) * 1.1 + 1));
				settings.setSettingValue(scrollSetting, settings.getSettingValue(scrollSetting) < 0 ? 0 : settings.getSettingValue(scrollSetting));
				
				break;
			}
			
			default:
				break;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void resolveQueue()
	{
		((ArrayList<Event<?>>)queue.clone()).forEach((ev) -> resolveEvent(ev));
		queue.clear();
	}
	
	public void render()
	{
		DrawUtils.clear(Color.black, Main.frame);
		
		for(Settings.SETTING set : Settings.SETTING.values())
		{
			Main.frame.debugPrint(String.format("%s: %d", set.toString(), settings.getSettingValue(set)));
		}
		
		Main.frame.debugPrint(String.format("Scroll mode: %s", scrollSetting.toString()));
		
		double degreePerRay = (double)settings.getSettingValue(Settings.SETTING.FOV)/(double)settings.getSettingValue(Settings.SETTING.RAY_NB);
		
		for(int rayNb = 0; rayNb < settings.getSettingValue(Settings.SETTING.RAY_NB); rayNb++)
		{
			float totalDist = 0;
			
			Ray2D currentRay = new Ray2D(ray.origin, ray.vect.turn(-(double)settings.getSettingValue(Settings.SETTING.FOV)/2 + rayNb*degreePerRay));
			if(settings.getSettingValue(Settings.SETTING.RAY_ANGLE_OFFSET) != 0) currentRay.vect = currentRay.vect.turn(Math.random() * degreePerRay);
			Reflector2D lastRef = null;
			for(int bounce = 0; bounce <= settings.getSettingValue(Settings.SETTING.MAX_BOUNCES); bounce++)
			{
				double dist = Double.MAX_VALUE;
				Reflector2D closestRef = null;
				Point2D closestPoint = null;
				
				for(Reflector2D ref : refs)
				{
					if(lastRef != ref)
					{
						for(Point2D pnt : ref.intersection(currentRay))
						{
							double tmpDist = currentRay.origin.distance(pnt);
							if(tmpDist < dist)
							{
								dist = tmpDist;
								closestRef = ref;
								closestPoint = pnt;
							}
						}
					}
				}
				
				totalDist+= dist;
				
				if(closestRef == null)
				{
					if(settings.getSettingValue(Settings.SETTING.SHOW_RAYS) != 0) DrawUtils.draw(currentRay, Main.frame);
					break;
				}
				if(totalDist > settings.getSettingValue(Settings.SETTING.LIGHT_INTENSITY)) break;
				
				if(settings.getSettingValue(Settings.SETTING.SHOW_RAYS) != 0) DrawUtils.draw(new Segment2D(currentRay.origin, closestPoint), Main.frame);
				
				int intensity = (int)((settings.getSettingValue(Settings.SETTING.LIGHT_INTENSITY) - totalDist) <= 0.5 ? 0 : 255 * (settings.getSettingValue(Settings.SETTING.LIGHT_INTENSITY) - totalDist) / settings.getSettingValue(Settings.SETTING.LIGHT_INTENSITY));
				if(intensity > 255) System.out.println(intensity);
				
				DrawUtils.drawPix((int)closestPoint.x, (int)closestPoint.y, new Color(intensity, intensity, intensity), Main.frame);
				currentRay = closestRef.reflect(currentRay);
				lastRef = closestRef;
			}
			
		}
	}
	
}

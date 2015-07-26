package engine;

public class Line {


	double x0, y0;
		double _angle;
		String _name="default";
		
		public Line (double x0, double y0, double angle)
		{
			refreshName();
		}
		public String toString(){return _name;}
		void setAngle(double a)
		{
			_angle=a;
			refreshName();
		}
		public double getAngle()
		{
			return _angle;
		}
		void refreshName()
		{
			_name="Line "+Double.toString(_angle);
			
		}
}

/* http://en.wikipedia.org/wiki/Bresenham's_line_algorithm */

import java.util.*;

public class Line
{
   public List<Point> points;
   
   public Line(int x0, int x1, int y0, int y1)
   {
      points = new ArrayList<Point>();
      
      int cx = x0;
      int cy = y0;
      int dx = Math.abs(x0 - x1);
      int dy = Math.abs(y0 - y1);
      int sx = x0 < x1 ? 1 : -1;
      int sy = y0 < y1 ? 1 : -1;
      int error = dx - dy;
      
      while (true)
      {
         points.add(new Point(cx, cy));
        
         if (cx == x1 && cy == y1)
            break;
        
         int e2 = error * 2;
            
         if (e2 > -1*dx)
         {
            error -= dy;
            cx += sx;
         }
            
         if (e2 < dx)
         {
            error += dx;
            cy += sy;
         }
      }
   }
}

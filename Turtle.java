import java.awt.*;
import asciiPanel.AsciiPanel;

public class Turtle extends Monster
{
   Turtle(World world)
   {
      super(world);
      this.symbol = 'b';
      this.color = new Color(255,0,127);
      this.health = 100;
      this.strength = 0;
      this.name = "Turtle";
   }
   
   Turtle(World world, int x, int y)
   {
      super(world, x, y, 6, 1, 'b', new Color(255,0, 127));
      this.name = "Turtle";
   }
   
   public void update()
   {
      int x = (world.player.x - this.x);
      if (x != 0)
         x /= Math.abs(world.player.x - this.x);
      int y = (world.player.y - this.y);
      if (y != 0)
         y /= Math.abs(world.player.y - this.y);
      this.move(x, 0);
      this.move(0, y);
   }
}

import java.awt.*;
import asciiPanel.AsciiPanel;

public class Troll extends Monster
{
   Troll(World world)
   {
      super(world);
      this.symbol = 't';
      this.color = new Color(0, 128, 255);
      this.health = 10;
      this.strength = 1;
      this.name = "Troll";
   }
   
   Troll(World world, int x, int y)
   {
      super(world, x, y, 10, 1, 't', new Color(0, 128, 255));
      this.name = "Troll";
   }
   
   /* Needs to be fixed so that trolls move multiple spaces without jumping over player */
   public void update()
   {
      int x = Math.abs(world.player.x - this.x);
      if (x >= 1)
         x /= Math.abs(world.player.x - this.x);
      int y = Math.abs(world.player.y - this.y);
      if (y >= 1)
         y /= Math.abs(world.player.y - this.y);
      this.move(2*x, 0);
      this.move(0, 2*y);
   }
}

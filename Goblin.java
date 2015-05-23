import java.awt.*;
import asciiPanel.AsciiPanel;

public class Goblin extends Monster
{
   Goblin(World world)
   {
      super(world);
      this.symbol = 'g';
      this.color = new Color(0, 175, 0);
      this.health = 6;
      this.strength = 1;
      this.name = "Goblin";
   }
   
   Goblin(World world, int x, int y)
   {
      super(world, x, y, 6, 1, 'g', new Color(0, 175, 0));
      this.name = "Goblin";
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

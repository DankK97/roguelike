import java.awt.*;
import asciiPanel.AsciiPanel;

public class Monster extends Entity
{
   Monster(World world)
   {
      super(world);
      this.name = "Unknown Monster";
   }
   
   Monster(World world, int x, int y, int health, int strength, char symbol, Color color)
   {
      super(world, x, y, health, strength, symbol, color);
      this.name = "Unknown Monster";
   }
   
   @Override
   public void update()
   {
      int x = (int)(Math.random() * 3)-1;
      int y = (int)(Math.random() * 3)-1;
      
      this.move(x, y);
   }
   
   public void move(int x, int y)
   {
      /* Check if moving into a solid tile: */
      if (world.tile(this.x + x, this.y + y).solid)
         return;
      /* Check if moving into another entity */
      if (world.entity(this.x + x, this.y + y) != null)
         return;
      /* Check if moving into the player */
      if (world.player.x == this.x + x && world.player.y == this.y + y)
      {
         /* Attack the player if moving into the player */
         world.player.attackPlayer(this);
         return;
      }
      /* If entity isn't walking into anything then it actually moves */
      this.x += x;
      this.y += y;
   }
}

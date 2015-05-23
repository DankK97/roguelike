import asciiPanel.AsciiPanel;
import java.awt.*;
   
public class Entity
{
   public World world;
   String name = "Unknown";
   public int health;
   public int maxHealth;
   public int strength;
   public int x, y;
   public char symbol;
   public Color color;
   
   public Entity(World world)
   {
      this.world = world;
      this.health = 1;
      this.x = 0;
      this.y = 0;
      this.symbol = '?';
      this.color = new Color(255, 255, 0);
   }
   
   public Entity(World world, int x, int y, int health, int strength, char symbol, Color color)
   {
      this.world = world;
      this.health = health;
      this.strength = strength;
      this.x = x;
      this.y = y;
      this.symbol = symbol;
      this.color = color;
   }

   public void addHealth(int health)
   {
      if (this.health + health < 0)
      {
         world.remove(this);
      }
      else
         this.health += health;
   }
   /* Update code for when the world automatically calls every
      entity every turn
      */
   public void update()
   {
   
   }
   
   void move(int x, int y)
   {
      System.out.println("Entity.move() is being run, it should never be run");
   }
}
   

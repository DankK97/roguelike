import java.awt.*;
import asciiPanel.AsciiPanel;

public class Player extends Entity
{
   public Inventory inventory;
   public Item equipSword;
   
   Player(World world, int x, int y)
   {
      super(world, x, y, 5, 1, '@', new Color(130, 255, 130));
      this.inventory = new Inventory(10);
      this.maxHealth = 10;
      this.equipSword = new Weapon('B', "Beginner Sword", "From henesys", new Color(255, 255, 255), 0, 0);
   }
   
   Player(World world, Point p)
   {
      super(world, p.x, p.y, 5, 1, '@', new Color(130, 255, 130));
      this.inventory = new Inventory(10);
      this.maxHealth = 10;
      this.equipSword = new Weapon('B', "Beginner Sword", "From henesys", new Color(255, 255, 255), 0, 0);
   
   }
   
   Player(World world, Player other)
   {
      super(world, other.x, other.y, other.health, other.strength, '@', new Color(130, 255, 130));
      this.inventory = other.inventory;
      this.maxHealth = other.maxHealth;
      this.equipSword = other.equipSword;
   }
   
   public void addHealth(int health)
   {
      if (this.health + health > maxHealth)
         this.health = maxHealth;
      else
         this.health += health;
   }
   
   public void move(int x, int y)
   {
      /* Check if moving into a solid tile: */
      if (world.tile(this.x + x, this.y + y).solid)
      {
         System.out.println("Player walking into solid tile");
         return;
      }
      /* Check if moving into another entity */
      if (world.entity(this.x + x, this.y + y) != null)
      {
         System.out.println("Player attacking" + world.entity(this.x + x, this.y + y));
         this.attackMonster(world.entity(this.x + x, this.y + y));
         return;
      }
      /* If player isn't walking into anything then it actually moves */
      this.x += x;
      this.y += y;
   }
   
   public void attackPlayer(Entity monster)
   {
      System.out.println(monster + " attacked the player for " + monster.strength);
      this.health -= monster.strength;
   }
   
   public void attackMonster(Entity monster)
   {
     
      monster.health -= this.strength;
      if (monster.health <= 0)
         world.remove(monster);
   }
   
   /* pickUp()
      attempts to pick an item up of the floor beneath the player
      */
   public void pickUp()
   {
      Item item = world.item(x, y);
      
      if (item != null)
      {  
         if (inventory.add(item))
            world.remove(item);
      }
      else
      {
         System.out.println("Nothing to pick up");
      }
   }
   
   public void use()
   {
      if (inventory.item(inventory.selected)!=null)
         inventory.item(inventory.selected).use(this);
   }
   
   public void drop()
   {
      Item toDrop = inventory.item(inventory.selected);
      if (toDrop != null)
      {
         inventory.remove(toDrop);
         world.addItem(toDrop, x, y);
      }  
   }
}

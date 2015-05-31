import java.awt.event.KeyEvent;
import java.awt.Color;
import asciiPanel.AsciiPanel;

public class PlayScreen implements Screen {
   private World world;
   private int level;
   private String name;
   
   private int worldDispWidth = 100;
   private int worldDispHeight = 40;
   private int worldDispX;
   private int worldDispY;
   
   private String[] gui;
   
   public PlayScreen(String name)
   {
      this.name = name;
      level = 1;
      initializeWorld(level);
      
      for (int i = 0; i < 5; i++)
      {
         world.addItemRandomPoint(new Weapon('X', "xyuper ahfuea", "danjhgejhg", new Color(255, 255, 255), 10, 1));
         world.addItemRandomPoint(new HealthPotion());
         world.addItemRandomPoint(new StrengthPotion());
         world.addItemRandomPoint(new AntidotePotion(1));
      }
      for(int i = 0; i < 2; i++)
      {
         world.addEntity(new Turtle(world), world.findEmptySpace());      
      }
      
      
      gui = ArtReader.get("playscreeninfo");
   }
   
   @Override
   public void displayOutput(AsciiPanel terminal) {
      drawWorldTiles(terminal);
      drawWorldItems(terminal);
      drawWorldEntities(terminal);
      drawItem(terminal, world.item(world.player.x, world.player.y), 0, 44);
      drawItem(terminal, world.player.inventory.item(world.player.inventory.selected), 12, 49);
      drawGui(terminal);
      world.player.inventory.drawInventory(terminal);
      drawEquips(terminal);
   }

   @Override
   public Screen respondToUserInput(KeyEvent key) {
      switch (key.getKeyCode()){
         /* Ingame player controls (moving, manipulating the world) */
         case KeyEvent.VK_NUMPAD1:
            world.player.move(-1, 1);
            world.update();
            break;
         case KeyEvent.VK_DOWN:
         case KeyEvent.VK_NUMPAD2: 
            world.player.move(0, 1);
            world.update();
            break;
         case KeyEvent.VK_NUMPAD3:
            world.player.move(1, 1);
            world.update();
            break;
         case KeyEvent.VK_LEFT: 
         case KeyEvent.VK_NUMPAD4:
            world.player.move(-1, 0);
            world.update();
            break;
         case KeyEvent.VK_NUMPAD5:
               // No move: wait
            world.update();
            break;
         case KeyEvent.VK_RIGHT: 
         case KeyEvent.VK_NUMPAD6:
            world.player.move(1, 0);
            world.update();
            break;
         case KeyEvent.VK_NUMPAD7:
            world.player.move(-1, -1);
            world.update();
            break;
         case KeyEvent.VK_UP:
         case KeyEvent.VK_NUMPAD8:
            world.player.move(0, -1);
            world.update();
            break;
         case KeyEvent.VK_NUMPAD9:
            world.player.move(1, -1);
            world.update();
            break;
         case KeyEvent.VK_G:
            world.player.pickUp();
            break;
         case KeyEvent.VK_E:
            world.player.use();
            break;
         case KeyEvent.VK_Q:
            world.player.drop();
            break;
        /* end Player ingame controls */
      }
      /* GUI controls */
      if ((int)key.getKeyChar() >= 48 && (int)key.getKeyChar() <= 57)
      {
         world.player.inventory.select((int)key.getKeyChar() - 49);
         /* Since the 0 key is on the right side, it shouldn't choose the left inventory slot.
            this fixes that */
         if ((int)key.getKeyChar() == 48)
            world.player.inventory.select(9);
      }
      if (world.player.health <= 0)
         return new LoseScreen(name, level);
      if(world.tiles[world.player.x][world.player.y] == Tile.STAIRCASE)
      {
         Player temp = world.player;
         level++;
         initializeWorld(level);
         if(level % 5 == 0)
            world.player = new Player(world, temp, new Point(25, 48));
         else
            world.player = new Player(world, temp, new Point((int)(world.width/2), (int)(world.height/2)));
      }
      if(level > 20)
      {
         return new WinScreen();
      }
         
      return this;
   }
   
   public void drawWorldEntities(AsciiPanel terminal)
   {
      for (Entity entity : world.entities)
      {
         /* if the entity is within the region being displayed => print it */
         if (entity.x >= worldDispX && entity.x < worldDispX+worldDispWidth && entity.y >= worldDispY && entity.y < worldDispY+worldDispHeight)
            terminal.write(entity.symbol, entity.x-worldDispX, entity.y-worldDispY, entity.color);
      }
      
      /* Draw the player
         the player is drawn in the center of the screen
         and the world moves around him
         */
      terminal.write(world.player.symbol, worldDispWidth/2, worldDispHeight/2, world.player.color);
   }
   
   public void drawWorldItems(AsciiPanel terminal)
   {
      for (Item item : world.items)
      {
         /* if the item is within the region being displayed => print it */
         if (item.x >= worldDispX && item.x < worldDispX+worldDispWidth && item.y >= worldDispY && item.y < worldDispY+worldDispHeight)
            terminal.write(item.symbol, item.x-worldDispX, item.y-worldDispY, item.color);
      }
   }
   
   public void drawWorldTiles(AsciiPanel terminal)
   {
      worldDispX = world.player.x - (worldDispWidth/2);
      worldDispY = world.player.y - (worldDispHeight/2);
      
      for (int o = 0; o < worldDispWidth; o++)
      {
         for (int p = 0; p < worldDispHeight; p++)
         {
            terminal.write(world.tile(worldDispX + o, worldDispY + p).symbol, o, p, world.tile(worldDispX + o, worldDispY + p).color);
         }
      }
   }

   public void drawGui(AsciiPanel terminal)
   {
      /* Text at below the map */
      ConsoleHelper.writeArray(terminal, gui, 0, 40);
      
      /* Draw player name beside "name:" */
      terminal.write(name.length() > 30 ? name.substring(0, 30) : name, 13, 40, new Color(130, 255, 130));
      
      /* Draw level # beisde "level:" */
      terminal.write(level + "", 13, 41);
      
      /* Draw health hearts,
         if the health hearts exceed the size of the screen
         don't draw the health hearts, draw a #
         */
      for (int i = 0; i < world.player.health; i++)
         if (65+i < 100)
            terminal.write((char)3, 65+i, 40, AsciiPanel.red);
         else
         {
            for (int j = 65; j < 100; j++)
               terminal.write(' ', j, 40);
            terminal.write(world.player.health + "   ", 65, 40, AsciiPanel.red);
         }
     
      /* Draw strength numbers */
       terminal.write(world.player.strength + "", 65, 41, new Color(255, 128, 0));
       terminal.write(" +" + ((Weapon)world.player.equipSword).damage + "", 66 + (int)Math.log(world.player.strength), 41, new Color(255, 50, 0));
      
      
   }

   public void drawItem(AsciiPanel terminal, Item item, int x, int y)
   {
      // row to display this on
      if (item != null)
      {
         terminal.write(item.symbol, x, y, item.color);
         terminal.write(" | ", x+1, y);
         terminal.write(item.name, x+4, y);
         terminal.write(" | ", x+item.name.length() + 4, y);
         terminal.write(item.tooltip, x+item.name.length() + 7, y, item.color);
      }
   }
   
   public void drawEquips(AsciiPanel terminal)
   {
      if (world.player.equipSword instanceof Weapon)
         terminal.write(world.player.equipSword.symbol, 0, 0, world.player.equipSword.color);
   }
   private void initializeWorld(int level)
   {
      world = new World(50, 50);
      // Empty room for boss battle
      if(level %  5 == 0)
      {
         world.tiles = world.emptyFloorGenerator(world.width, world.height);
      }
      // Regular room
      else
      {
         world.tiles = world.realFloorGenerator(world.width, world.height);
         //world.tiles = world.smooth(8);         
      }
      // Algorithms for enemies
   }
}

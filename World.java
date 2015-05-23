import asciiPanel.AsciiPanel;
import java.util.*;
import java.awt.Color;

public class World
{
   public int width, height;
   public Tile[][] tiles;
   public List<Entity> entities;
   public List<Item> items;
   public Player player;
   
   public World(int x, int y)
   {
      Tile[][] tiles = floorGenerator(x, y);
      this.entities = new ArrayList<Entity>();
      this.items = new ArrayList<Item>();
      this.tiles = tiles;
      this.width = tiles.length;
      this.height = tiles[0].length;
      this.player = new Player(this, findEmptySpace());
   }
   
   /* Method returns a selected tile in the map,
      if the tile is out of bounds then it returns the BOUNDS tile
      reserved for out of bounds tiles.
      */
   public Tile tile(int x, int y)
   {
      if(x < 0 || x >= width || y < 0 || y >= height)
         return Tile.NONE;
      else
         return tiles[x][y];
   }
   
   /* Method returns a newly generated level layout
      of size x by y.
      */
   public Tile[][] floorGenerator(int x, int y)
   {
      Tile[][] tiles = new Tile[x][y];
      for (int i = 0; i < x; i++)
      {
         for (int ii = 0; ii < y; ii++)
         {
            if (i == 0 || ii == 0 || i == x-1 || ii == y-1)
               tiles[i][ii] = Tile.WALL;
            else
               tiles[i][ii] = Math.random() > 0.6 ? Tile.WALL : Tile.FLOOR;
         }
      }
      return tiles;
   }
   
   public Point findEmptySpace()
   {
      int x;
      int y;
      do
      {
         x = (int)(Math.random() * width);
         y = (int)(Math.random() * height);
      } while(this.tiles[x][y].solid);
      return new Point(x, y);
   }
   
   public void remove(Entity entity)
   {
      entities.remove(entity);
   }
   
   public void remove(Item item)
   {
      items.remove(item);
   }
   
   public void addItem(Item item, int x, int y)
   {
      item.x = x;
      item.y = y;
      items.add(item);
   }
   
   public void addEntity(Entity entity, int x, int y)
   {
      entity.x = x;
      entity.y = y;
      entities.add(entity);
   }
   
   public void addEntity(Entity entity, Point point)
   {
      entity.x = point.x;
      entity.y = point.y;
      entities.add(entity);
   }
   
   public void addEntity(Entity entity)
   {
      int x, y;
      do
      {
         x = (int)(Math.random() * width);
         y = (int)(Math.random() * height);
      } while (tile(x,y).solid);
      entity.x = x;
      entity.y = y;
      entities.add(entity);
   }
   
   public Entity entity(int x, int y)
   {
      for (Entity entity : entities)
      {
         if (entity != null)
         {
            if (entity.x == x && entity.y == y)
            // there should not be 2 entities on 1 spot
            // this method returns the first found
               return entity;
         }
      }
      // if there is no entity
      return null;
   }
   
   public Item item(int x, int y)
   {
      for (Item item : items)
      {
         if (item != null)
         {
            if (item.x == x && item.y == y)
            // there should not be 2 entities on 1 spot
            // this method returns the first found
               return item;
         }
      }
      // if there is no entity
      return null;
   }
   
   /* This is Trystan's code from his roguelike tutorial
   http://trystans.blogspot.ca/2011/08/roguelike-tutorial-03-scrolling-through.html
   */
   public Tile[][] smooth(int times) {
      Tile[][] tiles2 = new Tile[width][height];
      for (int time = 0; time < times; time++) {
      
         for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
               int floors = 0;
               int rocks = 0;
            
               for (int ox = -1; ox < 2; ox++) {
                  for (int oy = -1; oy < 2; oy++) {
                     if (x + ox < 0 || x + ox >= width || y + oy < 0
                        || y + oy >= height)
                        continue;
                  
                     if (tiles[x + ox][y + oy] == Tile.FLOOR)
                        floors++;
                     else
                        rocks++;
                  }
               }
               tiles2[x][y] = floors >= rocks ? Tile.FLOOR : Tile.WALL;
            }
         }
      }
      Point portal = findEmptySpace();
      tiles2[portal.x][portal.y] = Tile.STAIRCASE;
      return tiles2;
   }
   
   public void update()
   {
      List<Entity> updateQueue = new ArrayList<Entity>(entities);
      
      for (Entity entity : updateQueue)
      {
         entity.update();
      }
   }
   
   public boolean canSee(int x0, int y0, int x1, int y1)
   {
      Line l = new Line(x0, x1, y0, y1);
      for (Point p : l.points)
      {
         if (tile(p.x, p.y).solid)
            return false;
      }
      return true;
   }
   
   public void addItemRandomPoint(Item item)
   {
      Point p = findEmptySpace();
      addItem(item, p.x, p.y);
   }
}

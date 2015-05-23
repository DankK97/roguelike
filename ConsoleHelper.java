/* ConsoleHelper by Alistair Carscadden 2015
   this class includes (or should include) all helpeful methods for drawing onto the terminal */
import asciiPanel.AsciiPanel;   
import java.awt.*;

public class ConsoleHelper
{
   public static void rectCustom(AsciiPanel terminal, int x, int y, int w, int h, char topLeftCorner, char topRightCorner, char bottomLeftCorner, char bottomRightCorner, char horizontalWall, char verticalWall, char fill, Color color)
   {
      for (int i = x; i < x+w; i++)
      {
         for (int ii = y; ii < y+h; ii++)
         {
            terminal.setCursorX(i);
            terminal.setCursorY(ii);
            // if topLeftCorner
            if (i == x && ii == y)
               terminal.write(topLeftCorner, color);
            // if bottomLeftCorner
            else if (i == x && ii == y + h - 1)
               terminal.write(bottomLeftCorner, color);
            // if topRightCorner
            else if (i == x + w - 1 && ii == y)
               terminal.write(topRightCorner, color);
            else if (i == x + w - 1 && ii == y + h - 1)
            // if bottomRightCorner
               terminal.write(bottomRightCorner, color);
            //if vertical wall
            else if (i == x || i == x + w - 1)
               terminal.write(verticalWall, color);
            //if horizontal wall
            else if (ii == y || ii == y + h - 1)
               terminal.write(horizontalWall, color);
            //if fill
            else
               terminal.write(fill, color);
         }
      }
   }
   
   public static void rect(AsciiPanel terminal, int x, int y, int w, int h)
   {
      rectCustom(terminal, x, y, w, h, (char)218, (char)191, (char)192, (char)217, (char)196, (char)179, ' ', AsciiPanel.white);
   }
   
   public static void rect(AsciiPanel terminal, int x, int y, int w, int h, Color color)
   {
      rectCustom(terminal, x, y, w, h, (char)218, (char)191, (char)192, (char)217, (char)196, (char)179, ' ', color);
   }
   
   public static void rectDouble(AsciiPanel terminal, int x, int y, int w, int h)
   {
      rectCustom(terminal, x, y, w, h, (char)201, (char)187, (char)200, (char)188, (char)205, (char)186, ' ', AsciiPanel.white);
   }
   
   public static void rectFull(AsciiPanel terminal, int x, int y, int w, int h)
   {
      rectCustom(terminal, x, y, w, h, (char)219, (char)219, (char)219, (char)219, (char)219, (char)219, ' ', AsciiPanel.white);
   }
   
   public static void rectFullFill(AsciiPanel terminal, int x, int y, int w, int h)
   {
      rectCustom(terminal, x, y, w, h, (char)219, (char)219, (char)219, (char)219, (char)219, (char)219, (char)219, AsciiPanel.white);
   }
   

   
   public static void writeCenterArray(AsciiPanel terminal, String[] s, int y)
   {
      for (int i = 0; i < s.length; i++)
      {
         terminal.writeCenter(s[i], y + i);
      }
   }
   
   public static void writeCenterArray(AsciiPanel terminal, String[] s, int y, Color fgColor)
   {
      for (int i = 0; i < s.length; i++)
      {
         terminal.writeCenter(s[i], y + i, fgColor);
      }
   }
   
   public static void writeArray(AsciiPanel terminal, String[] s, int x, int y)
   {
      for (int n = 0; n < s.length; n++)
      {
         for (int nx = 0; nx < s[n].length(); nx++)
         {
            terminal.setCursorX(nx + x);
            terminal.setCursorY(n + y);
            terminal.write(s[n].charAt(nx));
         }
      }
   }
   
   public static void writeArray(AsciiPanel terminal, String[] s, int x, int y, Color fgColor)
   {
      for (int n = 0; n < s.length; n++)
      {
         for (int nx = 0; nx < s[n].length(); nx++)
         {
            terminal.setCursorX(nx + x);
            terminal.setCursorY(n + y);
            terminal.write(s[n].charAt(nx), fgColor);
         }
      }
   }
   
   public static void writeArray(AsciiPanel terminal, String[] s, int x, int y, boolean drawBlankSpace)
   {
      for (int n = 0; n < s.length; n++)
      {
         for (int nx = 0; nx < s[n].length(); nx++)
         {
            terminal.setCursorX(nx + x);
            terminal.setCursorY(n + y);
            if (!drawBlankSpace && s[n].charAt(nx) == ' ')
            {
            }
            else
            {
               terminal.write(s[n].charAt(nx));
            }
         }
      }
   }
   
   public static void writeArray(AsciiPanel terminal, String[] s, int x, int y, Color color, boolean drawBlankSpace)
   {
      for (int n = 0; n < s.length; n++)
      {
         for (int nx = 0; nx < s[n].length(); nx++)
         {
            terminal.setCursorX(nx + x);
            terminal.setCursorY(n + y);
            if (!drawBlankSpace && s[n].charAt(nx) == ' ')
            {
            }
            else
            {
               terminal.write(s[n].charAt(nx), color);
            }
         }
      }
   }
}

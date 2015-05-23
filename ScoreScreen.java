import java.awt.event.KeyEvent;
import java.io.*;

import asciiPanel.AsciiPanel;

public class ScoreScreen implements Screen {

   public MenuItemList menuItems = new MenuItemList("Play", "Menu", "Info");
   
   @Override
   public void displayOutput(AsciiPanel terminal) {
      terminal.writeCenter(" _________________________________________ ", 1, AsciiPanel.brightWhite);
      terminal.writeCenter("/                                         \\", 2, AsciiPanel.brightWhite);
      terminal.writeCenter("|                highscores                |", 3, AsciiPanel.brightWhite);
      terminal.writeCenter("\\_________________________________________/", 4, AsciiPanel.brightWhite);
      menuItems.drawList(terminal, 40);
      addScore(new Score("TESTSCORE", 69));
      //load in scores
      String[] scores = new String[0];
      try
      {
         scores = SimpleFiles.readArray("scores");
      }
      catch (Exception e)
      {
         System.out.println("ScoreScreen.java can't find scores file");
      }
      ConsoleHelper.writeCenterArray(terminal, scores, 15);
   }

   @Override
   public Screen respondToUserInput(KeyEvent key) {
      /* Code for choosing menu items. */
      switch (key.getKeyCode()){
         case KeyEvent.VK_UP: 
            this.menuItems.select(-1);
            break;
         case KeyEvent.VK_DOWN: 
            this.menuItems.select(1);
            break;
         case KeyEvent.VK_ENTER:
            switch(menuItems.selected){
               case 0:
                  return new NameScreen();
               case 1:
                  return new StartScreen();
               case 2:
                  return new InfoScreen();
            }
      }
      /* /menu code */
   	
      return this;
   }
   
   private static class Score
   {
      String name;
      int score;
      Score(String name, int score)
      {
         this.name = name;
         this.score = score;
      }
   }
   
   public static void addScore(Score newScore)
   {
      String[] scoreFile = new String[0];
      try
      {
         scoreFile = SimpleFiles.readArray("scores");
      }
      catch (Exception e)
      {
         System.out.println("ScoreScreen.java can't find scores file");
      }
      Score[] scores = new Score[10];
      for(int i = 0; i < 10; i++)
      {
         scores[i] = new Score(scoreFile[i].split("-s")[0].trim(), Integer.parseInt(scoreFile[i].split("-s")[1].trim()));
      }
      int n = 0;
      for(int i = 0; i < 10; i++)
      {
         if (scores[n+1].score <= newScore.score)
         {
            for (int ii = 9; ii > i; ii--)
            {
               scores[ii] = scores[ii-1]; //Move the scores 1 to the back of the list
               
            }
            scores[i] = newScore;
            System.out.println("Score implimented");
            return;
         }
      }
      System.out.println("Score not large enough");
   }
}

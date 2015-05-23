/* ArtReader by Alistair Carscadden 2015
this class is made to retrieve artworks by name from a file called "art"
the art file has a specific writing system to make the arts easy to get
*/

public class ArtReader
{
   public static String[] get(String artName)
   {
      String[] art = new String[0];
      String[] artFile = new String[0];
      try { artFile = SimpleFiles.readArray("art.txt"); } 
      catch (Exception e) { System.out.println("ArtReader.java couldn't find 'art.txt'"); }
      for (int n = 0; n < artFile.length; n++)
      {
         //System.out.println(artFile[n].split(" ")[0]);
         if(artFile[n].split("-l")[0].trim().equals(artName))
         {
            int artLength = Integer.parseInt(artFile[n].split("-l")[1].trim());
            art = new String[artLength];
            for (int artN = 0; artN < artLength; artN++)
            {
               art[artN] = artFile[artN + n + 1];
            }
            return art;
         }
         
      }
      System.out.println("ArtReader.java couldn't find artwork \"" + artName);
      return null;
   }
}

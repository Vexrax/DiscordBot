package Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Util
{
    String RankSheetPath = "Ranks.txt";

    public Ranks getUserRank(String id)
    {
        try
        {
            int filelength = getFileLineLength(RankSheetPath);
            BufferedReader reader = new BufferedReader(new FileReader(RankSheetPath));
            for (int i = 0; i < filelength; i++) {
                String[] splitStr = reader.readLine().split("\\s+");
                if(splitStr[0].equals("Admin") && Arrays.asList(splitStr).contains(id))
                {
                    System.out.println("Admin Rank");
                    return Ranks.Admin;
                }
                if(splitStr[0].equals("Terminator") && Arrays.asList(splitStr).contains(id))
                {
                    System.out.println("Terminator Rank");
                    return Ranks.Terminator;
                }
            }
            reader.close();
        }
        catch (IOException e)
        {
            System.out.println("IOException in Util: " + e.toString());
        }
        System.out.println("No Rank");
        return Ranks.None;
    }

    public int getFileLineLength(String filePath)
    {
        try
        {
            BufferedReader textreader = new BufferedReader(new FileReader(filePath));
            int numberoflines = 0;
            while (textreader.readLine() != null) {
                numberoflines++;
            }
            textreader.close();
            return numberoflines;
        }
        catch (IOException e)
        {
            System.out.println("IOException in Util: " + e.toString());
            return 0;
        }
    }

}

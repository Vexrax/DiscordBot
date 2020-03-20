package DiscordBot.Skynet.Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Util
{
    private String RankSheetPath = "Ranks.txt";
    private HashMap<Integer,  String> IntToUnicodeMap = new HashMap<Integer, String >();
    private HashMap<String, Integer> UnicodeToIntMap = new HashMap<String, Integer>();


    public Util()
    {
        IntToUnicodeMap.put(1, "1⃣");
        IntToUnicodeMap.put(2, "2⃣");
        IntToUnicodeMap.put(3, "3⃣");
        IntToUnicodeMap.put(4, "4⃣");
        IntToUnicodeMap.put(5, "5⃣");
        IntToUnicodeMap.put(6, "6⃣");
        IntToUnicodeMap.put(7, "7⃣");
        IntToUnicodeMap.put(8, "8⃣");
        IntToUnicodeMap.put(9, "9⃣");

        UnicodeToIntMap.put("1⃣", 1);
        UnicodeToIntMap.put("2⃣", 2);
        UnicodeToIntMap.put("3⃣", 3);
        UnicodeToIntMap.put("4⃣", 4);
        UnicodeToIntMap.put("5⃣", 5);
        UnicodeToIntMap.put("6⃣", 6);
        UnicodeToIntMap.put("7⃣", 7);
        UnicodeToIntMap.put("8⃣", 8);
        UnicodeToIntMap.put("9⃣", 9);
    }

    public static String[] cleanArgs(String[] args) {
        if(args.length == 0)
        {
            return new String[0];
        }
        ArrayList<String> tempArray = new ArrayList<String>();
        String[] cleanedArgs = new String[args.length-1];
        int i = 0;
        for (String s : args)
        {
            if(i != 0)
            {
                tempArray.add(s);
            }
            i++;
        }
        return tempArray.toArray(cleanedArgs);
    }

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

    //Single String Has Spaces
    public String convertArgListToSingleString(String[] args, int startindex) {
        StringBuilder builder = new StringBuilder();
        for(int i = startindex; i < args.length; i++)
        {
            builder.append(args[i] + " ");
        }
        return builder.toString();
    }

    public String convertIntegerToUnicodePollString(int number)
    {
        return IntToUnicodeMap.get(number);
    }

    public int convertUnicodeStringToInt(String unicodePoll)
    {
        return UnicodeToIntMap.get(unicodePoll);
    }

    //Checks if the unicode is a valid 1-9 emoji
    public boolean isStringValidUnicodeEmoji(String string)
    {
        return UnicodeToIntMap.containsKey(string);
    }
}

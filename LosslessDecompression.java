import java.util.*;
import java.io.*;
import java.nio.*;

public class LosslessDecompression
{
   public static void main(String[] args)   //Decompressed using RLE: Run-Length-Encoding
   {
      try{
         File file = new File(args[0]);
         BufferedReader reader = new BufferedReader(new FileReader(file));
         String line = null;
         ArrayList<String> flist = new ArrayList<String>(0);
         ArrayList<String> ilist = new ArrayList<String>(0);
         int j=0;
         while ((line = reader.readLine()) != null) 
         {
            ilist.add(line);
            j++;
         }
         
         int numToAdd=0;
         
         for(int i=0; i<ilist.size(); i++)
         {
            if(ilist.get(i).length()==2)
            {
               flist.add(ilist.get(i));
            } else {
               numToAdd=Integer.parseInt(ilist.get(i).substring(2));
               for(int k=0; k<numToAdd; k++)
               {
                  flist.add(ilist.get(i).substring(0, 2));
               }
            }
         }
         String pause = new Scanner(System.in).nextLine();
         file = new File("DecompressedHex.txt");
         
         BufferedWriter bw = new BufferedWriter(new FileWriter(file));
         
         for(int i=0; i<flist.size(); i++)
         {
            bw.write(flist.get(i)+"\n");
         }
         
         bw.close();         
      } catch(Exception e){
         System.out.println(e);
      }
   }
}

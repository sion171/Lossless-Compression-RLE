import java.util.*;
import java.io.*;
import java.nio.*;

public class LosslessCompression
{
   public static void main(String[] args)   //Compressed using RLE: Run-Length-Encoding
   {
      try{
		     assert (args.length == 1);
         File file = new File(args[0]);
         BufferedReader reader = new BufferedReader(new FileReader(file));
         String line = null;
         ArrayList<String> flist = new ArrayList<String>(0);
         ArrayList<Integer> nlist = new ArrayList<Integer>(0);
         ArrayList<String> ilist = new ArrayList<String>(0);
         int j=0;
         while ((line = reader.readLine()) != null) 
         {
            if(j==0)line=line.substring(3);
            ilist.add(line);
            j++;
         }
         
         nlist.add(1);
         flist.add(ilist.get(0));
         
         for(int i=1; i<ilist.size(); i++)
         {
            if(ilist.get(i).equals(ilist.get(i-1))){
               nlist.set(nlist.size()-1, nlist.get(nlist.size()-1)+1);
            } else {
               nlist.add(1);
               flist.add(ilist.get(i));
            }
         }
         
         for(int i=0; i<flist.size(); i++)
         {
            if(nlist.get(i)>1){
               flist.set(i, flist.get(i)+nlist.get(i));
            }
         }
         
         System.out.println(file.getName() + " size: " + file.length()/1024.0 + "KB");
         
         FileWriter fw = new FileWriter(args[0].substring(0, args[0].lastIndexOf(".")) + ".comp");
         file = new File(args[0].substring(0, args[0].lastIndexOf(".")) + ".comp");
         for(int i=0; i<flist.size(); i++)
         {
            fw.write(flist.get(i) + "\n");
         }
         
         fw.close();
         
         System.out.println(file.getName() + " size: " + file.length()/1024.0 + "KB");    
      } catch(Exception e){
         System.out.println(e);
      }
   }
}

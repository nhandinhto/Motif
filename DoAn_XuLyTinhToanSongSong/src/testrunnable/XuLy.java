/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrunnable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutionException;
import javax.swing.JTextArea;
import jdk.nashorn.internal.objects.NativeArray;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 *
 * @author Admin
 */
public class XuLy {


    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here

    }
    
    
    public static ArrayList<String> docFile(String path)
    {
        ArrayList<String> ds = new ArrayList<String>();
        try
        {
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while(line != null)
            {
                ds.add(line);
                line = br.readLine();
            }
            br.close();
            isr.close();
            fis.close();
            return ds;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean ghiFile(String path, ArrayList<String> ds)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(path);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            for(String s: ds)
            {
                bw.write(s);
                bw.newLine();
            }
            bw.close();
            osw.close();
            fos.close();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    /// <summary>
    /// Input: Má»™t chuá»—i x, Má»™t chuá»—i y cáº§n tÃ¬m vÃ  má»™t biáº¿n sá»‘ k sá»‘ sai khÃ¡c 
    /// Náº¿u tÃ¬m Ä‘Æ°á»£c chuá»—i y trong chuá»—i x vá»›i sá»‘ sai khÃ¡c k
    /// tráº£ vá»� List cÃ¡c chuá»—i tÃ¬m tháº¥y vÆ¡i k Ä‘á»™t biáº¿n trong chuá»—i txt
    /// 
    /// </summary>
    /// <param name="txt"></param>
    /// <param name="pattern"></param>
    /// <param name="k"></param>
    /// <returns></returns>
    public static void PatternBranching_Naive(String txt, String pattern, int k,ArrayList<String> motifs)
        {
            int n = txt.length();
            int m = pattern.length();

            char[] pat = pattern.toCharArray();
            char[] text = txt.toCharArray();
            String motif = "";
            for (int i = 0; i <= n - m; i++)
            {
                int j;
                int t = k;
                for (j = 0; j < m; j++)
                {
                    if (text[i + j] != pat[j])
                    {
                        t--;
                    }
                    if (t >= 0)
                        motif += text[i + j];
                    else
                        break;
                }
                if (j == m)
                {
                    motifs.add(motif);
                }
                motif = "";
            }
        }
    
 
    public static boolean KMP(String txt, String pattern)
        {
            int N = txt.length();
            int M = pattern.length();
            int[] lps = new int[M];


            
            int i = 0;
            int j = 0;
            
            char[] pat = pattern.toCharArray();
            char[] text = txt.toCharArray();
            
            computeLPSArray(pat, M, lps);
            
            while (i < N)
            {
                if (pat[j] == text[i])
                {
                    j++;
                    i++;
                }
                else
                {
                    if (j != 0)
                        j = lps[j - 1];
                    else
                        i = i + 1;
                }
                if (j == M)
                {
                    return true;
                }
            }
            return false;
        }

    public static void computeLPSArray(char[] pat, int M, int[] lps)
        {
            int len = 0;
            lps[0] = 0;
            int i = 1;
            
            while (i < M)
            {
                if (pat[i] == pat[len])
                {
                    len++;
                    lps[i] = len;
                    i++;
                }
                else
                {
                    if (len != 0)
                    {
                        len = lps[len - 1];
                    }
                    else
                    {
                        lps[i] = 0;
                        i++;
                    }
                }
            }
        }
    
    public static void thucThiTT(String l, int k)
    {
        int n;
        ArrayList<String> ds = docFile("C:\\Users\\Nhan\\Desktop\\DoAn_XuLyTinhToanSongSong\\File\\chuoitest2.txt");
        n = Integer.parseInt(ds.get(0));

        
        String [] input = new String[ds.size()];
        for(int i=0; i<ds.size();i++)
        {
            input[i] = String.valueOf(ds.get(i));
        }

        String [] DNAs = new String[n];
        for(int i=0; i<n; i++)
        {
            DNAs[i] = input[1+i];
        }
        LinkedList<ArrayList<String>> DNAs_motifs = new LinkedList<>();
        
        for(int i =0;i<n;i++)
        {
            ArrayList<String> chuoi = new ArrayList<>();
            DNAs_motifs.addFirst(chuoi);
        }
        
        
        String output ="";
        if(k==0)
        {
            for(int i = 0;i<n;i++)
            { 
                if(KMP(DNAs[i], l))
                {
                    output += "TÃ¬m tháº¥y chuá»—i "+l + " trong chuá»—i " + (i+1) + "\n";
                    DNAs_motifs.get(i).add(l);
                }
                else
                {
                    output += "KhÃ´ng tÃ¬m tháº¥y chuá»—i trong chuá»—i " + (i+1) + "\n";
                    DNAs_motifs.get(i).add(""+0);
                }
            }
            FormThucThi.setDNAS_motifs(DNAs_motifs);
            FormThucThi.setThongbao(output);
        }
        else
        {

            for(int i =0;i<n;i++)
            {
                ArrayList<String> motifs = new ArrayList<>();
                PatternBranching_Naive(DNAs[i], l, k, motifs);
                if(motifs.isEmpty())
                {
                    output += "KhÃ´ng tÃ¬m tháº¥y chuá»—i phÃ¹ há»£p trong chuá»—i " + (i+1) + "\n";
                }
                else
                {
                    
                    output += "CÃ¡c chuá»—i tÃ¬m tháº¥y trong chuá»—i " + (i+1) + " :";
                    for(int j=0;j<motifs.size();j++)
                    {
                        output += motifs.get(j).toString() +"\t";
                        DNAs_motifs.get(i).add(motifs.get(j));
                    }
                    output += "\n";
                }
       
            }
            FormThucThi.setDNAS_motifs(DNAs_motifs);
            FormThucThi.setThongbao(output);
        }
    }
    
    public static void thucThiSS(String l,int k,int soLuong) throws InterruptedException, ExecutionException
    {
        int n;
        ArrayList<String> ds = docFile("C:\\Users\\Nhan\\Desktop\\DoAn_XuLyTinhToanSongSong\\File\\chuoitest2.txt");
        n = Integer.parseInt(ds.get(0));

        
        String [] input = new String[ds.size()];
        for(int i=0; i<ds.size();i++)
        {
            input[i] = String.valueOf(ds.get(i));
        }
        
        String [] DNAs = new String[n];
        for(int i=0; i<n; i++)
        {
            DNAs[i] = input[1+i];
        }


        LinkedList<ArrayList<String>>DNAs_motifs = tachChuoiDNA_TheoLuong(n, soLuong, DNAs);
        LinkedList<ArrayList<String>> motif_found = new LinkedList<>();

        for(int i=0;i<DNAs_motifs.size();i++)
        {
            ArrayList<String> ls = DNAs_motifs.get(i);
            DNA_Runnable r = new DNA_Runnable(ls.size(), l, k, ls, i);
            r.run();
            motif_found.addAll(r.DNAs_motifs);
        }
        FormThucThi.setDNAS_motifs(motif_found);
        
    }
    
    public static LinkedList<ArrayList<String>> tachChuoiDNA_TheoLuong(int n,int soLuong,String []DNAs)
    {
        LinkedList<ArrayList<String>> DNAs_motifs = new LinkedList<>();
        
        int minsoTT_moiThread = n / soLuong;
        int maxsoTT_moiThread = minsoTT_moiThread +1;
        int thread_maxSoTT = n - (soLuong*minsoTT_moiThread);
  
        int start =0;
        
        for(int i =0;i<soLuong;i++)
        {
          int soTT_1thread = (i < thread_maxSoTT ? maxsoTT_moiThread : minsoTT_moiThread);
          int end = soTT_1thread + start;
          int o=0;
          String [] dnas_i = new String[soTT_1thread];
          for(int j=start;j<end;j++)
          {
          	dnas_i[o] = DNAs[j];
          	o++;
          }
          ArrayList<String>  dnas = new ArrayList<>();
          for(int j=0;j<dnas_i.length;j++)
          {
          	dnas.add(dnas_i[j]);
          }
   
          DNAs_motifs.add(dnas);
          start = end;
        }
        return DNAs_motifs;
    }
        

}

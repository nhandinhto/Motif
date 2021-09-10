/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrunnable;

import com.oracle.jrockit.jfr.DataType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *
 * @author Nhan
 */
public class DNA_Runnable implements Runnable{
    ArrayList<String> DNAs;
    int soTT;
    String l;
    int k;
    String thongbao;
    LinkedList<ArrayList<String>> DNAs_motifs;
    int threadName;
    int nameString_start;
    ArrayList<String> tb;
    public static void main(String[] args) {
        // TODO code application logic here

    }
    
    
    public DNA_Runnable(int soTT,String pattern,int k,ArrayList<String> dnas,int name_start)
    {
        this.DNAs = dnas;
        this.soTT = soTT;
        this.l = pattern;
        this.k = k;
        DNAs_motifs = new LinkedList<>();
        this.nameString_start = name_start;
        tb = new ArrayList<String>();
    }
    
    @Override
    public void run()
    {
        String output ="";
        for(int i =0;i<soTT;i++)
        {
            ArrayList<String> chuoi = new ArrayList<>();
            DNAs_motifs.add(chuoi);
        }
                
        if(k == 0)
        {
            for(int i =0;i<soTT;i++)
            {
                if(XuLy.KMP(DNAs.get(i), l))
                {
                    output += "Tìm thấy chuỗi "+l + " trong chuỗi " + (i+nameString_start+1) + "\n";
                    tb.add(output);
                    DNAs_motifs.get(i).add("1");
                }
                else
                {
                    output += "Không tìm thấy chuỗi trong chuỗi " + (i+nameString_start+1) + "\n";
                    tb.add(output);
                    DNAs_motifs.get(i).add("0");
                }
            }
  
            
        }
        else
        {
            for(int i=0;i<soTT;i++)
            {
                ArrayList<String>motifs = new ArrayList<>();
                XuLy.PatternBranching_Naive(DNAs.get(i), l, k, motifs);
                if(motifs.isEmpty())
                {
                    output += "Không tìm thấy chuỗi phù hợp trong chuỗi " + (i+nameString_start+1) + "\n";
                    DNAs_motifs.get(i).add("0");
                    tb.add(output);
                }
                else
                {
                    
                    output += "Các chuỗi tìm thấy trong chuỗi " + (i+nameString_start+1) + " :";
                    for(int j=0;j<motifs.size();j++)
                    {
                        output += motifs.get(j).toString() +"\t";
                        DNAs_motifs.get(i).add(motifs.get(j));
                    }
                    output += "\n";
                    tb.add(output);
                }
            }
        }
    }
    public void start()
    {
    	
    }
}

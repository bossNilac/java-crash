package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Test {

    double crash_multiplier ;
    int pos;

    public Test(double crash_multiplier,int pos) {
        this.crash_multiplier = crash_multiplier;
        this.pos = pos;
    }

    public static void main(String[] args){
        System.out.println(crash_debug(11));
        writeReport(crash_debug(101));
    }

    static void writeReport(String string){
        try
        {
            String dir= "src/com/company/";
            String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
            PrintWriter writer = new PrintWriter(dir+timeStamp+"_debug.crash", "UTF-8");
            writer.println("CRASH MULTIPLIER = 0.99*E/(E-H):");
            writer.println("The debug for : "+timeStamp);
            writer.println(string);
            writer.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    static String crash_debug(int i){
        String debug_text="";
        ArrayList<Test> crashes = new ArrayList<Test>();
        Random random = new Random();
        int counter_1=0;
        int counter_2=0;
        ArrayList<Integer> crash_pos=new ArrayList<>();
        String new_line="\n";

        for( int x=0; x<i; x++) {

            debug_text += "I: "+x+new_line;

            int pow_no = random.nextInt(52 - 1 +1) + 1;
            debug_text += "pow_no:"+pow_no+new_line;

            int E= (int) Math.pow(2,pow_no);
            debug_text += "E:"+E+new_line;

            int H= random.nextInt(E-1);
            debug_text += "H:"+H+new_line;

            double multiplier=0.99*E/(E-H);
            if(multiplier<=0){
                counter_1++;
                counter_2++;
                crash_pos.add(x);
            }

            if(multiplier<=1){
                counter_2++;
                crash_pos.add(x);
            }

            debug_text += "Crash: "+multiplier+new_line;

            crashes.add(new Test(multiplier,x));
        }

        debug_text += "<0 count: "+counter_1+new_line;
        debug_text += "Crash count at 1 :"+counter_2+new_line;

        for(int j =0;j<crash_pos.size();j++){
            debug_text += "Crash  at 1 pos:"+crash_pos.get(j)+" ";
        }

        Set<Test> set = new HashSet<>();
        for(int y =0; y < crashes.size(); y++) {
            if (set.contains(crashes.get(y))) {
                debug_text += "Crash is duplicated at :"+crashes.get(y)+new_line;

            } else set.add(crashes.get(y));
        }
        return debug_text;
    }
}

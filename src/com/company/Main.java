package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

import static com.company.Test.crash_debug;

public class Main {
    static boolean flag=true;
    static double balance;
    //CRASH MULTIPLIER = 0.99*E/(E-H)

    public static void main(String[] args) {

        loadProperties();

        System.out.println("Welcome to java Crash !");
        System.out.println("Type a bet and multiply it,but be careful you might lose everything");
        while(flag){
            if(balance<=0){
                System.out.println("Out of balance");
                System.out.println("Exiting application");
                System.exit(0);
            }
            double  temp=balance;
            Scanner sc=new Scanner(System.in);
            System.out.println("Type a bet : ");
            double bet = Double.parseDouble(sc.nextLine());
            balance=balance-bet;
            System.out.println("Type a multiplier : ");
            double user_multiplier = Double.parseDouble(sc.nextLine());
            double current_crash=generate_crash_Multiplier();
            System.out.println("Crash: "+ current_crash);
            if(current_crash<1){
                drawCrash(true);
                balance-=bet;
                System.out.println("You lost : "+bet);
            }else{
                if(user_multiplier<current_crash){
                    drawCrash(false);
                    balance+=bet*user_multiplier;
                    System.out.println("You won : "+(balance-temp));
                }else{
                    drawCrash(true);
                    balance-=bet;
                    System.out.println("You lost : "+bet);
                }
            }
            storeProperties((balance));
            System.out.println("Continue?");
            String command=sc.nextLine();
            if(command.equals("no")){
                flag=false;
                System.out.println("Thanks for choosing our service");
            }
        }
    }

    static double generate_crash_Multiplier() {
        Random random=new Random();
        int pow_no = random.nextInt(52 - 1 +1) + 1;
        int E= (int) Math.pow(2,pow_no);
        int H= random.nextInt(E-1);
        double multiplier=0.99*E/(E-H);

        return multiplier;
    }

    private static void loadProperties()  {
        String data = null;
        try {
            data = new String(Files.readAllBytes(Paths.get("src/com/company/balance.crash")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("balance:"+data);
        balance= Double.parseDouble(data);
    }
    private static void storeProperties(double balance)  {
        try
        {
            String filename= "src/com/company/balance.crash";
            FileWriter fw = new FileWriter(filename,false);
            fw.write(""+balance);
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public static void drawCrash(boolean isCrashed){
        int k=20;
        int a, b;
        if(isCrashed){
            for (a = 1; a <= k; a++) {
                for (b = 1; b <=k+23; b++) {
                    if (a == k || b == 1
                            || b == (k - a + 1)
                            ||((b>k&&b<k+5)&&a==1)||((b==k+5&&a==2))||(b==k+6&&a==3)||(b==k+7&&a==4)||(b==k+8&&a==5)||(b==k+9&&a==6)||(b==k+10&&a>=7))

                        System.out.print("* ");
                    else {
                        if ((a>=11&&a<k+23)&& b ==k +a) {
                            System.out.print("C r a s h ! ! !");
                        }else {
                            System.out.print("  ");
                        }
                    }
                }
                System.out.println();
            }

        }else{
            for (a = 1; a <= k; a++) {
                for (b = 1; b <= k; b++) {
                    if (a == k || b == 1
                            || b == (k - a + 1))
                        System.out.print("* ");
                    else {
                        System.out.print("  ");
                    }
                }
                System.out.println();
            }
        }
    }

}

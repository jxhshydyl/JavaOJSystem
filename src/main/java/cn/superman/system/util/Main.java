package cn.superman.system.util;
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);
        int n;
        while(scan.hasNext())
        {
            n=scan.nextInt();
            if(n==0)//0表示结束输入
                return;
            else
                System.out.println(sum(n));
        }
    }
    static int sum(int n)
    {
        int m=0;//表示喝到的饮料数，喝完转化为空瓶
        if(n<=1)
            return 0;
        else if(n==2)
            return 1;
        else if(n==3)
            return 1;
        else{  //数量大于等于3，递归求解
            m=n/3;
            return m+sum(n-3*m+m);
        }
    }
}
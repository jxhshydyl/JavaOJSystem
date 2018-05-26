package cn.superman.system.util;

import cn.superman.web.dto.CodeDTO;
import org.springframework.stereotype.Service;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Service
public class CompilerAndRunUtil {
    /**
     * 得到类名
     * @param code
     * @return
     */
    public static String getClassName(String code){
        String name="";
        if(code.indexOf("class")!=-1){
            name=code.substring(code.indexOf("class")+5,code.indexOf("{"));
        }
        return name.trim();
    }
    /**
     * 编译程序
     * @param command
     * @return
     */
    public static Map<String,Object> compileCode(String command){
        BufferedReader br = null;
        StringBuilder sb=null;
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            Process p = Runtime.getRuntime().exec(command);
            br = new BufferedReader(new InputStreamReader(p.getInputStream(),Charset.forName("gbk")));
            if(br.readLine()==null) {
                br = new BufferedReader(new InputStreamReader(p.getErrorStream(), Charset.forName("gbk")));
            }
            String line = null;
            sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            p.destroy();
            map.put("message",sb.toString().replace("e:\\test\\",""));
            return map;
        }catch (IOException e){
            e.printStackTrace();
        }
        return map;
    }
    /**
     * 运行程序
     * @param command
     * @return
     */
    public static Map<String,Object> runCode(String command, CodeDTO codeDTO){
        BufferedReader br = null;
        int key=0;
        long time=3000;
        long maxTime=1;
        String[] useCase=codeDTO.getExampleInput().split("======");
        String[] exampleOutput=codeDTO.getExampleOutput().split("======");
        Map<String,Object> map=new HashMap<>();
        try{
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<useCase.length;i++){
                long startTime=new Date().getTime();
                Process p = Runtime.getRuntime().exec(command);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
                bw.write(useCase[i].trim());
                bw.newLine();
                bw.flush();
                bw.close();
                br = new BufferedReader(new InputStreamReader(p.getErrorStream(),Charset.forName("gbk")));
                String errorMessage=br.readLine();
                if(errorMessage==null){
                    br = new BufferedReader(new InputStreamReader(p.getInputStream(),Charset.forName("gbk")));
                    String line = null;
                    sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                }else{
                    String line = null;
                    sb = new StringBuilder();
                    sb.append(errorMessage+"\n");
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    p.destroy();
                    map.put("message",sb.toString());
                    return map;
                }
                p.destroy();
                long endTime=new Date().getTime();
                if(time<endTime-startTime){
                    map.put("message","超时");
                    return map;
                }
                if(maxTime<endTime-startTime){
                    maxTime=endTime-startTime;
                }
                String[] split = exampleOutput[i].trim().split("\r\n");
                String[] split1 = sb.toString().split("\n");
                int isOK=0;
                try{
                    for(int j=0;j<split.length;j++){
                        if(split[j] != null){
                            if(split[j].equals(split1[j])){
                                isOK++;
                            }
                        }
                    }
                }catch (Exception e){
                    return null;
                }
                if(isOK==split1.length){
                    key++;
                }
            }
            float sim=(float)(((key*1.0)/(useCase.length/1.0))*100);
            BigDecimal b  =   new  BigDecimal(sim);
            float   f1   =  b.setScale(1,  BigDecimal.ROUND_HALF_UP).floatValue();
            map.put("message","测试数据通过率："+f1+"%");
            map.put("time",maxTime);
            map.put("memory",0);
           return map;
        }catch (IOException e){
            e.printStackTrace();
        }
        return map;
    }
    public static void main(String[] args){
        CompilerAndRunUtil codeService=new CompilerAndRunUtil();
        String code= "import java.util.*;  \n" +
                "public class Main{  \n" +
                "    public static void main(String[] args){  \n" +
                "        Scanner scan=new Scanner(System.in);  \n" +
                "        int n;  \n" +
                "        while(scan.hasNext())  \n" +
                "        {  \n" +
                "            n=scan.nextInt();  \n" +
                "            if(n==0)//0表示结束输入  \n" +
                "                return;  \n" +
                "            else  \n" +
                "                System.out.println(sum(n));  \n" +
                "        }  \n" +
                "    }  \n" +
                "    static int sum(int n)  \n" +
                "    {  \n" +
                "        int m=0;//表示喝到的饮料数，喝完转化为空瓶  \n" +
                "        if(n<=1)  \n" +
                "            return 0;  \n" +
                "        else if(n==2)  \n" +
                "            return 1;  \n" +
                "        else if(n==3)  \n" +
                "            return 1;  \n" +
                "        else{  //数量大于等于3，递归求解  \n" +
                "            m=n/3;  \n" +
                "            return m+sum(n-3*m+m);  \n" +
                "        }  \n" +
                "    }  \n" +
                "}";
//        code= DelComments.delComments(code);
        String str="用例：3\n" +
                "10\n" +
                "81\n" +
                "0\n" +
                "结果：1\n" +
                "5\n" +
                "40\n" ;

        //String message=codeService.dealCode(code,str.split("用例：|结果："));
        System.out.println("1");
        System.out.println("5");
        System.out.println("40");
        Scanner in=new Scanner(System.in);
        System.out.println("获取字符串:"+in.next());
        System.out.println("获取一行数据："+in.nextLine());
        //System.out.println(sdr1.equals(sdr2));
        float sim=(float)(((0)/(1/2.0)));
        System.out.println(sim);
        //System.out.println(message);
    }
}

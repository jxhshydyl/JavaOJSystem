package cn.superman.system.util;

import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;

@Service
public class CompilerAndRunUtil {
    public String dealCode(String code,String[] arr){
        //得到文件名
        String className = CompilerAndRunUtil.getClassName(code);
        //创建文件
        String message="";
        File file=new File("F:\\test\\"+className+".java");
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter=new FileWriter(file,false);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(code);
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
            //编译文件
            String compileCommand ="javac -encoding utf-8 -d F:\\class  F:\\test\\"+className+".java";
            message= CompilerAndRunUtil.compileCode(compileCommand);
            //运行文件
            if("".equals(message)||message==null){
                String runCommand ="java -cp f:\\class "+className+"";
                message= CompilerAndRunUtil.runCode(runCommand,arr);
            }
            return message;
        }catch (IOException e){
            e.printStackTrace();
        }
        return message;
    }
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
    public static String compileCode(String command){
        BufferedReader br = null;
        StringBuilder sb=null;
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
            return sb.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 运行程序
     * @param command
     * @return
     */
    public static String runCode(String command,String[] useCase){
        BufferedReader br = null;
        int key=0;
        long time=3000;
        try{
            StringBuilder sb=new StringBuilder();
            for(int i=1;i<(useCase.length-1)/2+1;i++){
                long startTime=new Date().getTime();
                Process p = Runtime.getRuntime().exec(command);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
                bw.write(useCase[2*i-1].trim());
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
                    return sb.toString();
                }
                p.destroy();
                System.out.println(sb);
                if(time<new Date().getTime()-startTime){
                    return "超时";
                }
                if(useCase[2*i].trim().equals(sb.toString().trim())){
                    key++;
                }
            }
            float sim=(float)((key*1.0)/(useCase.length/2))*100;
           return "测试数据通过率："+sim+"%";
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
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

        String message=codeService.dealCode(code,str.split("用例：|结果："));
        System.out.println(message);
    }
}

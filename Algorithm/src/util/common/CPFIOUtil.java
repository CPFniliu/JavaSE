package util.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class CPFIOUtil
{
    
    public static String getOulerFilePath(String filename){
        URL resource = CPFIOUtil.class.getResource("/");
        return resource.getPath() + "../resource/ouler/" + filename;
    }

    public static String readFile(String filepath){
        String str = null;
        File file = new File(filepath);
        try {
            if (!file.exists()){
                throw new FileNotFoundException(filepath);
            }
            @SuppressWarnings("resource")
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            str = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }
    
    public static void readFileByLines(String fileName) {   
        File file = new File(fileName);   
        BufferedReader reader = null;   
        try {   
            System.out.println("以行为单位读取文件内容，一次读一整行：");   
            reader = new BufferedReader(new FileReader(file));   
            String tempString = null;   
            int line = 1;   
            // 一次读入一行，直到读入null为文件结束   
            while ((tempString = reader.readLine()) != null) {   
                // 显示行号   
                System.out.println("line " + line + ": " + tempString);   
                line++;   
            }   
            reader.close();   
        } catch (IOException e) {   
            e.printStackTrace();   
        } finally {   
            if (reader != null) {   
                try {   
                    reader.close();   
                } catch (IOException e1) {   
                }   
            }   
        }   
    }   
    
}

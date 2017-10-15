package treeserver.utils;

import org.junit.Test;
import treeserver.bean.Tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
 * Created by kevinyin on 2017/10/15.
 */
public class JsonUtilsTest {

    @Test
    public void testDecodeJson(){
        File file = new File("flare.json");
        String json = txt2String(file);
        json = JsonUtils.compress(json);
        System.out.println(json);
    }

    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

}

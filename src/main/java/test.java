import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * Project name(项目名称)：文件编码识别和指定编码读写
 * Package(包名): PACKAGE_NAME
 * Class(类名): test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/12/14
 * Time(创建时间)： 10:15
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class test
{

    public static String autoDiscernEncoding(File file)
    {
        String encoding = "UTF-8";
        try
        {
            Path path = Paths.get(file.getPath());
            byte[] data = Files.readAllBytes(path);
            CharsetDetector detector = new CharsetDetector();
            detector.setText(data);
            CharsetMatch match = detector.detect();
            encoding = match.getName();
            System.out.println("文件：" + file.getName() + "的编码为：" + encoding);
            return encoding;
        }
        catch (IOException e)
        {
            System.out.println("识别失败");
            e.printStackTrace();
            return encoding;
        }
    }

    public static void main(String[] args)
    {
        File file = new File("t1.txt");
        System.out.println(autoDiscernEncoding(file));
    }
}

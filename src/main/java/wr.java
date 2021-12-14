import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Project name(项目名称)：文件编码识别和指定编码读写
 * Package(包名): PACKAGE_NAME
 * Class(类名): wr
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2021/12/14
 * Time(创建时间)： 10:28
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class wr
{
    public static String encoding;

    public static String getFileMD5(String filePath)  //获得文件的MD5值
    {
        try
        {
            InputStream fis = new FileInputStream(filePath);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1)
            {
                md.update(buffer, 0, length);
            }
            fis.close();
            //转换并返回包含16个元素字节数组,返回数值范围为-128到127
            byte[] md5Bytes = md.digest();
            BigInteger bigInt = new BigInteger(1, md5Bytes);
            return bigInt.toString(16);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public static String read(File file)
    {
        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fileInputStream = null;
        InputStreamReader InputStreamReader = null;
        try                                  //文件流打开，文件读写
        {
            fileInputStream = new FileInputStream(file);        // test.autoDiscernEncoding(file)
            encoding = test.autoDiscernEncoding(file);
            InputStreamReader = new InputStreamReader(fileInputStream, encoding);
            char[] buffer = new char[1024];
            int count = 0;
            String str;
            while ((count = InputStreamReader.read(buffer)) != -1)
            {
                str = new String(buffer, 0, count);
                stringBuilder.append(str);
                System.out.println(str);
            }
            return stringBuilder.toString();
        }
        catch (FileNotFoundException e)      //文件未找到
        {
            Toolkit.getDefaultToolkit().beep();
            System.err.println("文件未找到！！！  " + "\n错误内容：" + e.toString());
        }
        catch (Exception e)                  //其它异常
        {
            Toolkit.getDefaultToolkit().beep();
            e.printStackTrace();
        }
        finally
        {
            try                              //关闭流
            {
                if (fileInputStream != null)
                {
                    fileInputStream.close();
                }
                if (InputStreamReader != null)
                {
                    InputStreamReader.close();
                }
            }
            catch (NullPointerException e)    //空指针异常
            {
                Toolkit.getDefaultToolkit().beep();
                System.err.println("文件已经被关闭，无法再次关闭！！！");
            }
            catch (Exception e)              //其它异常
            {
                Toolkit.getDefaultToolkit().beep();
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void write(String str)
    {
        FileWriter fileWriter = null;
        try                                  //文件流打开，文件读写
        {
            File file;
            fileWriter = new FileWriter("out.txt");
            fileWriter.write(str);
        }
        catch (FileNotFoundException e)      //文件未找到
        {
            Toolkit.getDefaultToolkit().beep();
            System.err.println("文件未找到！！！  " + "\n错误内容：" + e.toString());
        }
        catch (Exception e)                  //其它异常
        {
            Toolkit.getDefaultToolkit().beep();
            e.printStackTrace();
        }
        finally
        {
            try                              //关闭流
            {
                if (fileWriter != null)
                {
                    fileWriter.close();
                }
            }
            catch (NullPointerException e)    //空指针异常
            {
                Toolkit.getDefaultToolkit().beep();
                System.err.println("文件已经被关闭，无法再次关闭！！！");
            }
            catch (Exception e)              //其它异常
            {
                Toolkit.getDefaultToolkit().beep();
                e.printStackTrace();
            }
        }
    }

    public static void write_auto(String str)
    {
        FileOutputStream fileOutputStream = null;
        try                                  //文件流打开，文件读写
        {
            fileOutputStream = new FileOutputStream("out.txt");
            fileOutputStream.write(str.getBytes(encoding));
        }
        catch (FileNotFoundException e)      //文件未找到
        {
            Toolkit.getDefaultToolkit().beep();
            System.err.println("文件未找到！！！  " + "\n错误内容：" + e.toString());
        }
        catch (Exception e)                  //其它异常
        {
            Toolkit.getDefaultToolkit().beep();
            e.printStackTrace();
        }
        finally
        {
            try                              //关闭流
            {
                if (fileOutputStream != null)
                {
                    fileOutputStream.close();
                }
            }
            catch (NullPointerException e)    //空指针异常
            {
                Toolkit.getDefaultToolkit().beep();
                System.err.println("文件已经被关闭，无法再次关闭！！！");
            }
            catch (Exception e)              //其它异常
            {
                Toolkit.getDefaultToolkit().beep();
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        String path = "t1.txt";
        File file = new File(path);
        String str = read(file);
        System.out.println(str);
        write_auto(str);
        System.out.println("MD5："+getFileMD5(path).equals(getFileMD5("out.txt")));
    }
}

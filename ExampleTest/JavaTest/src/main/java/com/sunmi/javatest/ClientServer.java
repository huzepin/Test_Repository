package com.sunmi.javatest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author admin
 * @time 2019/9/18 15
 */
public class ClientServer {
    public static void main(String[] args) {
        File file = new File("D:\\test_file\\premiere2015_116990.zip");
       // splitFileDemo(file,5);

        joinFileDemo("D:\\test_file\\premiere2015_116990_data1.zip","D:\\test_file\\premiere2015_116990_data2.zip", "D:\\test_file\\premiere2015_116990_data3.zip","D:\\test_file\\premiere2015_116990_data4.zip","D:\\test_file\\premiere2015_116990_data5.zip");
    }


    //文件分割的方法（方法内传入要分割的文件路径以及要分割的份数）
    private static void splitFileDemo(File src, int m) {
        if(src.isFile()) {
            //获取文件的总长度
            long l = src.length();
            //获取文件名
            String fileName = src.getName().substring(0, src.getName().indexOf("."));
            //获取文件后缀
            String endName = src.getName().substring(src.getName().lastIndexOf("."));
            System.out.println(endName);
            InputStream in = null;
            try {
                in = new FileInputStream(src);
                for(int i = 1; i <= m; i++) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(src.getParent()).append("\\").append(fileName)
                            .append("_data").append(i).append(endName);
                    System.out.println(sb.toString());
                    File file2 = new File(sb.toString());
                    //创建写文件的输出流
                    OutputStream out = new FileOutputStream(file2);
                    int len = -1;
                    byte[] bytes = new byte[10*1024*1024];
                    while((len = in.read(bytes))!=-1) {
                        out.write(bytes, 0, len);
                        if(file2.length() > (l / m)) {
                            break;
                        }
                    }
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //文件合并的方法（传入要合并的文件路径）
    private static void joinFileDemo(String... src) {
        for(int i = 0; i < src.length; i++) {
            File file = new File(src[i]);
            String fileName = file.getName().substring(0, file.getName().indexOf("_"));
            String endName = file.getName().substring(file.getName().lastIndexOf("."));
            StringBuffer sb = new StringBuffer();
            sb.append(file.getParent()).append("\\").append(fileName)
                    .append(endName);
            System.out.println(sb.toString());
            try {
                //读取小文件的输入流
                InputStream in = new FileInputStream(file);
                //写入大文件的输出流
                File file2 = new File(sb.toString());
                OutputStream out = new FileOutputStream(file2,true);
                int len = -1;
                byte[] bytes = new byte[10*1024*1024];
                while((len = in.read(bytes))!=-1) {
                    out.write(bytes, 0, len);
                }
                out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("文件合并完成！");
    }
}

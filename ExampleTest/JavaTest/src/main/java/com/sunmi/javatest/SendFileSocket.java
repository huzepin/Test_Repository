package com.sunmi.javatest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author admin
 * @time 2019/9/18 15
 */
public class SendFileSocket {
    public static void main(String [] args){
        try {
            //绑定服务端口，ip默认为本机ip
            ServerSocket server = new ServerSocket(9999);
            //accept() 服务端阻塞，等待客户端连接。
            Socket socket = server.accept();
            System.out.println("客户端连接成功。。。");
            OutputStream output = socket.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\test_file"));
            long start = System.currentTimeMillis();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = bis.read(buf))!=-1){
                System.out.println(len);
                output.write(buf);
            }
            long end = System.currentTimeMillis();
            System.out.println("传输完成，共用时："+(end-start)+"毫秒！");
            bis.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

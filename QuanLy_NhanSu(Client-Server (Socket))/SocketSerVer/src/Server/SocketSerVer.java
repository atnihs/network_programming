/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Vi01
 */
public class SocketSerVer {

    /**
     * @param args the command line arguments
     */
  
         public static void main(String args[]) {
        try {
            ServerSocket ss = new ServerSocket(9876);// Tao cong 9876 de server lang nghe
            System.out.println("CODE: 1000 -- Server được tạo.... sẵn sàng kết nối với Client");
            while (true)// Cho client ket noi
            {
                System.out.print("Call to here.");

                // Su dung multithread
                // Khi co 1 client gui yeu cau toi thi se tao ra 1 thread phuc vu client do
                new ThreadSocket(ss.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
}

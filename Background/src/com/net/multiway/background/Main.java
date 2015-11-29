package com.net.multiway.background;

import com.net.multiway.background.data.Data0x1000;
import com.net.multiway.background.data.Data0x1002;
import com.net.multiway.background.model.Host;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Host host = new Host("192.168.4.4", 5000);
        int option = 0;

        while (option != 4) {
            System.out.println("Escolha um item:");
            System.out.println("(1) Iniciar teste da conexão (default);");
            System.out.println("(2) Reconfigurar IP do dispositivo ODR;");
            System.out.println("(3) Sair;");
            Scanner scan = new Scanner(System.in);
            option = scan.nextInt();

            switch (option) {
                case 1: {
                    Data0x1000 data = new Data0x1000(1, 0, 65.0f, 1, 1000, 1550, 0, 0, 15000, 1.4685f, 5.0f, 0);
                    host.connect(data);
                    break;
                }

                case 2: {
                    Scanner s = new Scanner(System.in);
                    System.out.println("IP:");
                    String ip = s.next();
                    System.out.println("Mask:");
                    String mask = s.next();
                    System.out.println("Gateway:");
                    String gateway = s.next();

                    host.connect(new Data0x1002(ip, mask, gateway));
                    break;
                }

                case 3: {
                    System.exit(1);
                    break;
                }

                default: {
                    System.out.println("Comando não encontrado;");
                    break;
                }
            }

        }

    }

}

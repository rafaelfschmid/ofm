package com.net.multiway.background;

import com.net.multiway.background.data.Data0x1000;
import com.net.multiway.background.data.Data0x1002;
import com.net.multiway.background.data.dao.Data0x1000DAO;
import com.net.multiway.background.data.dao.Data0x1002DAO;
import com.net.multiway.background.model.Host;
import com.net.multiway.background.receive.Receive0x9001;
import com.net.multiway.background.utils.Utils;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {

		Host host = new Host("192.168.4.4", 5000);
		int option = 0;

		while (option != 4) {
			System.out.println("Escolha um item:");
			System.out.println("(1) Iniciar teste da conexão (default);");
			System.out.println("(2) Reconfigurar IP do dispositivo OTDR;");
			System.out.println("(3) Sair;");
			Scanner scan = new Scanner(System.in);
			option = scan.nextInt();

			switch (option) {
				case 1: {
					Data0x1000 data = new Data0x1000(Long.parseLong("2"),1, 0, 65.0f, 1, 1, 1550, 0, 0, 15000, 1.4685f, 5.0f, 0);
					Data0x1000DAO dao = new Data0x1000DAO();
					dao.create(data);
					host.connect(data);
					break;
				}

				case 2: {
					Data0x1002 data = new Data0x1002(Long.parseLong("1"),Utils.fillAddress("192.168.4.4"),Utils.fillAddress("255.255.255.0"),Utils.fillAddress("192.168.4.1"));
					Data0x1002DAO dao = new Data0x1002DAO();
					dao.create(data);
					//host.connect(data);
					break;
				}

				case 3: {
					System.exit(0);
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

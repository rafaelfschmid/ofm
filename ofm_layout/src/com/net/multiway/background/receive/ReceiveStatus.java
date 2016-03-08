package com.net.multiway.background.receive;

import java.io.DataInputStream;
import java.io.IOException;

import com.net.multiway.background.model.Package;
import com.net.multiway.background.utils.Utils;

/*Classe para recebimento de dados com o código: 0xA000000*/
public class ReceiveStatus extends Package {

    private int data;
    private DataInputStream in;

    public ReceiveStatus(DataInputStream in, int length) {
        this.in = in;
        this.length = length;
    }

    @Override
    public void parser() throws IOException {

        byte[] d = new byte[this.length];
        in.read(d);
        data = Utils.byte4ToInt(d);

        switch (this.data) {
            case 0: {
                System.out.println("Successful processing command!");

                break;
            }
            case 1: {
                System.out.println("Starting character string of illegal frame!");
                break;
            }
            case 2: {
                System.out.println("Illegal version number!");
                break;
            }
            case 3: {
                System.out.println("Illegal frame type!");
                break;
            }
            case 4: {
                System.out.println("Illegal command identifiers!");
                break;
            }
            case 5: {
                System.out.println("Illegal data length");

                break;
            }
            default: {
                System.out.println("Contact Staff");

                break;
            }
        }
        in.read(d);

    }

}

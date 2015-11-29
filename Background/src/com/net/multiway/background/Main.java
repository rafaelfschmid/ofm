package com.net.multiway.background;

import com.net.multiway.background.data.Data0x1000;
import com.net.multiway.background.model.Host;

public class Main {

    public static void main(String[] args) {
        Host host = new Host("192.168.4.4", 5000);
        host.initialize();
        
        Data0x1000 data = new Data0x1000(1, 0, 65.0f, 1, 1000, 1550, 0, 0, 15000, 1.4685f, 5.0f, 0);
        host.connect(data);
    }

}

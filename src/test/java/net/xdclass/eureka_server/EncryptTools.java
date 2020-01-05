package net.xdclass.eureka_server;

import com.alibaba.druid.filter.config.ConfigTools;

public class EncryptTools {
    public static void main(String[]args) throws Exception {
        System.out.println(ConfigTools.encrypt("root"));
    }
}

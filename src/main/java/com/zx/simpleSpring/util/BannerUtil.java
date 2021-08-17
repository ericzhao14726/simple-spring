package com.zx.simpleSpring.util;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BannerUtil {

    private static final String DEF_BANNER = "" +
            " ▗▄▖   █            ▗▄▖        ▗▄▖             █            \n" +
            "▗▛▀▜   ▀            ▝▜▌       ▗▛▀▜             ▀            \n" +
            "▐▙    ██  ▐█▙█▖▐▙█▙  ▐▌   ▟█▙ ▐▙   ▐▙█▙  █▟█▌ ██  ▐▙██▖ ▟█▟▌\n" +
            " ▜█▙   █  ▐▌█▐▌▐▛ ▜▌ ▐▌  ▐▙▄▟▌ ▜█▙ ▐▛ ▜▌ █▘    █  ▐▛ ▐▌▐▛ ▜▌\n" +
            "   ▜▌  █  ▐▌█▐▌▐▌ ▐▌ ▐▌  ▐▛▀▀▘   ▜▌▐▌ ▐▌ █     █  ▐▌ ▐▌▐▌ ▐▌\n" +
            "▐▄▄▟▘▗▄█▄▖▐▌█▐▌▐█▄█▘ ▐▙▄ ▝█▄▄▌▐▄▄▟▘▐█▄█▘ █   ▗▄█▄▖▐▌ ▐▌▝█▄█▌\n" +
            " ▀▀▘ ▝▀▀▀▘▝▘▀▝▘▐▌▀▘   ▀▀  ▝▀▀  ▀▀▘ ▐▌▀▘  ▀   ▝▀▀▀▘▝▘ ▝▘ ▞▀▐▌\n" +
            "               ▐▌                  ▐▌                   ▜█▛▘\n";
    private static final String BANNER = "banner.txt";


    public static void print(){
        // 获取自定义 banner 路径
        URL url = Thread.currentThread().getContextClassLoader().getResource(BANNER);
        if (null == url) {
            System.out.println(DEF_BANNER);
        } else {
            try {
                Files.lines(Paths.get(url.toURI())).forEach(System.out::println);
            } catch (Exception ignored) {
            }
        }
        System.out.println("\033[32;3m" + "Simple spring test by zx, version: 1.0.0" + "\033[0m");
    }
}

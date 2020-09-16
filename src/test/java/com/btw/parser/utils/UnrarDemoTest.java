package com.btw.parser.utils;

import com.fate.decompress.Unrar5;
import junit.framework.TestCase;
import org.junit.Test;

public class UnrarDemoTest extends TestCase {

    @Test
    public void testMain() throws Exception {
        String filepath = "C:\\Users\\T440\\Desktop\\beans\\itaxCoupon_Refund20200901.rar";
        String unrarDir = "C:\\Users\\T440\\Desktop\\beans";
        int exitCode = Unrar5.window(filepath, unrarDir, "G:\\WinRar\\WinRAR.exe");
//        int exitCode = Unrar5.linux(filepath, unrarDir);
        if (exitCode !=0 ){
            System.out.println("Error");
        } else {
            System.out.println("Success");
        }
        System.exit(0);
    }
}
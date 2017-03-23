package com.example.zhuangqf;

import java.util.Random;
import java.util.UUID;

/**
 * Created by zq on 2017/3/23.
 */
public class Mainer {
    public static void main(String[] arg) throws Exception{
        AESService aesService = new AESService();
        for(int i=0;i<10;i++){
            String text = getRandomText();
            String key = getRandomText();
            String cipherText = aesService.encrypt(text,key);
            String clearText = aesService.decrypt(cipherText,key);
            System.out.println("No."+i+":"+text);
            System.out.println("No."+i+":"+clearText);
            System.out.println(clearText.equals(text));
        }
        String text = getRandomText();
    }

    private static String getRandomText() {
        Random random = new Random(System.currentTimeMillis());
        String uuid = UUID.randomUUID().toString();
        int n = random.nextInt();
        if(n<0) n = - n;
        n = n % uuid.length();
        return new String(uuid.getBytes(),0,n);
    }
}

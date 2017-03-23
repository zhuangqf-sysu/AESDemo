package com.example.zhuangqf;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * Created by zq on 2017/3/23.
 */
public class AESService {

    private String encryption = "AES/CBC/PKCS5Padding";  // encription/cipher/padding
    private byte[] iv = "0102030405060708".getBytes();

    public AESService(){}

    public AESService(String encryption){
        this.encryption = encryption;
    }

    public String encrypt(String clearText,String key) throws Exception{
        byte[] raw = MD5(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw,"AES");
        Cipher cipher = Cipher.getInstance(encryption);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,ivParameterSpec);
        byte[] cipherText = cipher.doFinal(clearText.getBytes());
        return new BASE64Encoder().encode(cipherText);
    }

    public String decrypt(String cipherText,String key) throws Exception{
        byte[] raw = MD5(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw,"AES");
        Cipher cipher = Cipher.getInstance(encryption);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,ivParameterSpec);
        byte[] tempText = new BASE64Decoder().decodeBuffer(cipherText);
        byte[] clearText = cipher.doFinal(tempText);
        return new String(clearText);
    }

    public byte[] MD5(String text) throws Exception{
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        return messageDigest.digest(text.getBytes());
    }


}

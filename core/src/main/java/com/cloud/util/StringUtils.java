package com.cloud.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;

public class StringUtils {
    /**
     * 检验字符串是否为空
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        return null == str ? false : "".equals(str) ? false : true;
    }

    /**
     * base64加密
     * @param str
     * @return
     */
    public static String base64Encode(String str) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(str.getBytes("UTF-8"));
    }

    /**
     * base64解密
     * @param str
     * @return
     */
    public static String base64decode(String str) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(str),"UTF-8");
    }
    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }
    /**
     * 随机生成字母加数字的密码
     * @param lengths 密码的位数
     * @return
     */
    public String getStringRandom(int lengths) {
        StringBuffer val = new StringBuffer("");
        Random random = new Random();
        //参数lengths，表示生成几位随机数
        for (int i = 0; i < lengths; i++) {
            String strOrNum = random.nextInt(2) % 2 == 0 ? "str":"num";
            //随机输出是字母还是数字
            if ("str".equalsIgnoreCase(strOrNum)) {
                //随机输出是大写字母还是小写字母
                int temp = random.nextInt(2)%2 == 0 ? 65:97;
                val.append((char)(random.nextInt(26)+temp));
            }else if("num".equalsIgnoreCase(strOrNum)){
                val.append(String.valueOf(random.nextInt(10)));
            }
        }
        return val.toString();
    }
}


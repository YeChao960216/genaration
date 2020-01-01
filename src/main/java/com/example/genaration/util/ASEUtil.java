package com.example.genaration.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ASEUtil {
    /*
     * 加密
     * 1.构造密钥生成器
     * 2.根据ecnodeRules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     */

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static String encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            String encryptResultStr = parseByte2HexStr(result);
            return encryptResultStr; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static String decrypt(String content, String password) {
        try {
            byte[] decryptFrom = parseHexStr2Byte(content);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(decryptFrom);
            return new String(result); // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) {
        ASEUtil aseUtil = new ASEUtil();

        String content = "[\n" +
                "  {\n" +
                "    \"hash\": \"FirstBlock\",\n" +
                "    \"preHash\": \"0\",\n" +
                "    \"data\": \"{\\\"grow\\\":{\\\"growId\\\":\\\"6AD4C2D38C8C484FB15EB92C5E954B54\\\",\\\"growerId\\\":\\\"1\\\",\\\"flag\\\":\\\"龙眼\\\"},\\\"user\\\":{\\\"userId\\\":\\\"1\\\",\\\"name\\\":\\\"叶超\\\",\\\"sex\\\":\\\"男\\\",\\\"idCard\\\":\\\"440902199602160000\\\",\\\"phone\\\":\\\"17827419712\\\",\\\"address\\\":\\\"广东省广州市A区\\\",\\\"userName\\\":\\\"Chace\\\",\\\"password\\\":\\\"123456\\\",\\\"createTime\\\":\\\"Mar 19, 2019 10:38:37 AM\\\",\\\"farm\\\":\\\"A市B农场\\\"},\\\"chemicalsList\\\":[]}\",\n" +
                "    \"timeStamp\": 1554707391663,\n" +
                "    \"nonce\": 8438\n" +
                "  }\n" +
                "]";
        String password = "E8B86743782B445B89C4EB3CDC77F660";
        //加密
        System.out.println("加密前：" + content);
        String encryptResult = aseUtil.encrypt(content, password);
        System.out.println("加密后：" + encryptResult);
        //解密
        //byte[] decryptFrom = aseUtil.parseHexStr2Byte(encryptResultStr);
        String decryptResult = aseUtil.decrypt("E3867807F58DCCA6CD4BC33DD8F190F840D3C66EF21D88A6D87424329B5805F44A05A035526087F5C23732EB8C10D2CF47B5D7C0E2D5281BB13178DEFEA8F645343D8F09415DB1C5300BD023915C23C48A637009F4EF0F01E5A4ECA039AF64B5DF5A225DA75BE2B6024DFDBAC9603EF5D667E67A101E483DE5299F50DD03DB7DDA6B0C44741389B4E06C228B6F6C29B89DAB1E73C308781E9703F5890A90B06C0388D73B4CDBB7C39015FEFA21A6A543DA0F1BEAD6CD3C44D835CDC293335DB3AB7A44CA4E8816B4AFA4EF1F52C6A0A6CD02D8BED9E24D6CBF9B78F86A2D32734DC6B81BF703932DC309A04CB08FC1C5C0E774A726F1D28ED832FB0BC9F15FFC678AA1DCABF6A6AD0F2C854CA58281C95E57DE9008992E2613E1D4D2A62AE50B4C57EDF57835E8624324EF6CC20BA5ABAD601C38DB589A44276DC6C4D2FDA0A4D9338A90C8A21EB21C8BECED74073F853CACEE62D190FF5B6623EDD86C97409C77DA534BC082D139CEA5DCE1BC536D297BAE76C5578BAC3C7B2782A0343F9DDB21246F5894B3C99C4576FCF02904AA92C25F71371EB87BA2D358CA9495438045190605BEFB41083EDCD8302B83629D3E76100650664750828398A8920F97B0C840C5C7432C36D836D9A7E5634EEF2F1BC7C2204736F901187146C58DEDBB3BF2EE51BE89F26E823AC0841F7BF8D8A2E565A8ED309F27BD0FBC0F3CC9B94385BD20EC041DFF42B8125CAC8CBF449035CE897C61E802B109E5D9371A8DD29DDCB1FD5F896EBB10D505D2A9A30A7C6899F42B9A0BE23555A7631AA252E388C32C0B485D41725A3056F1CAC4FA0D3A5B5F19784C6823BCEB21F2D93F8C52CFB0C8985D395A35B5969BAD9E2FD39191AE3EFBD521BE02FFE215184EE9E3220CB455F7", "192253D7F9BA42E4B143F85D896A655B");
        System.out.println("解密后：" + new String(decryptResult));
    }
}

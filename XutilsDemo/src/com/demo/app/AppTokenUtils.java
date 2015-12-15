package com.demo.app;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import android.util.Base64;

import com.lidroid.xutils.util.LogUtils;

/**
 * Created by danhantao on 15/2/5.
 */
public final class AppTokenUtils {
  // 客户端 appToken ⽣生成规则:base64( DES (appID~~设备类型~~时间戳))
  private static final String SEPARATOR = "~~";
  private static final String APPID = "YmY0ZTM2NDItOGViYi00MTE5LTljNjctY2VmODU5ZTcyNTI1";
  private static final String DESKEY = "dreambook";
  private static final String TAG = AppTokenUtils.class.getSimpleName();
  private static final byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8};
  private static final String DES = "DES";
  private static final String CHARSET = "UTF-8";

  /**
   * 生成appToken
   *
   * @param currenTime 当前时间
   * @param deviceType 设备类型
   * @return 3:Android Phone; 4:Android Tablet;
   */
  public static String appTokenGenerate(long currenTime, int deviceType) {
    String ID = APPID + SEPARATOR + deviceType + SEPARATOR + String.valueOf(currenTime);
    byte[] DESencrypt = encryptString(DESKEY, ID); // DES加密
    LogUtils.i("DES加密后为:" + DESencrypt);
    String appTokenResult = new String(Base64.encode(DESencrypt, Base64.DEFAULT));
    LogUtils.i("appToken值为:" + appTokenResult);
    return appTokenResult;
  }

  /**
   * 加密以 byte[] 明文输入 ,byte[] 密文输出
   *
   * @param byteS
   * @return
   */
  public static byte[] encryptByte(byte[] byteS, Key key) throws Exception {
    byte[] byteFina = null;
    Cipher cipher;
    try {
      IvParameterSpec zeroIv = new IvParameterSpec(iv);
      cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
      byteFina = cipher.doFinal(byteS);
    } catch (Exception e) {
      throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
    } finally {
      cipher = null;
    }
    return byteFina;
  }

  //    /**
  //     * 加密方法
  //     *
  //     * @param rawKeyData
  //     * @param str
  //     * @return
  //     * @throws InvalidKeyException
  //     * @throws NoSuchAlgorithmException
  //     * @throws IllegalBlockSizeException
  //     * @throws BadPaddingException
  //     * @throws NoSuchPaddingException
  //     * @throws InvalidKeySpecException
  //     */
  //    public static byte[] encrypt(byte rawKeyData[], String str)
  //            throws InvalidKeyException, NoSuchAlgorithmException,
  //            IllegalBlockSizeException, BadPaddingException,
  //            NoSuchPaddingException, InvalidKeySpecException {
  //        // DES算法要求有一个可信任的随机数源
  //        SecureRandom sr = new SecureRandom();
  //        // 从原始密匙数据创建一个DESKeySpec对象
  //        DESKeySpec dks = new DESKeySpec(rawKeyData);
  //        // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
  //        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
  //        SecretKey key = keyFactory.generateSecret(dks);
  //        // Cipher对象实际完成加密操作
  //        Cipher cipher = Cipher.getInstance("DES");
  //        // 用密匙初始化Cipher对象
  //        cipher.init(Cipher.ENCRYPT_MODE, key, sr);
  //        // 现在，获取数据并加密
  //        byte data[] = str.getBytes();
  //        // 正式执行加密操作
  //        byte[] encryptedData = cipher.doFinal(data);
  //        LogUtils.i(TAG, "加密后===>" + new String(encryptedData));
  //        return encryptedData;
  //    }

  /**
   * 通过字符串生成密钥对象
   *
   * @param keyStr 密钥字符串(8位的倍数)
   * @return
   */
  public static Key generateKey(String keyStr)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException,
      InvalidKeyException {
    DESKeySpec desKeySpec = new DESKeySpec(keyStr.getBytes(CHARSET));
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
    SecretKey key = keyFactory.generateSecret(desKeySpec);
    return key;
  }

  public static byte[] encryptString(String raw, String str) {
    try {
      Key key = generateKey(raw);
      return encryptByte(str.getBytes("UTF-8"), key);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  //    /**
  //     * 解密方法
  //     *
  //     * @param rawKeyData
  //     * @param encryptedData
  //     * @throws IllegalBlockSizeException
  //     * @throws BadPaddingException
  //     * @throws InvalidKeyException
  //     * @throws NoSuchAlgorithmException
  //     * @throws NoSuchPaddingException
  //     * @throws InvalidKeySpecException
  //     */
  //    public static String decrypt(byte rawKeyData[], byte[] encryptedData)
  //            throws IllegalBlockSizeException, BadPaddingException,
  //            InvalidKeyException, NoSuchAlgorithmException,
  //            NoSuchPaddingException, InvalidKeySpecException {
  //        // DES算法要求有一个可信任的随机数源
  //        SecureRandom sr = new SecureRandom();
  //        // 从原始密匙数据创建一个DESKeySpec对象
  //        DESKeySpec dks = new DESKeySpec(rawKeyData);
  //        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
  //        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
  //        SecretKey key = keyFactory.generateSecret(dks);
  //        // Cipher对象实际完成解密操作
  //        Cipher cipher = Cipher.getInstance("DES");
  //        // 用密匙初始化Cipher对象
  //        cipher.init(Cipher.DECRYPT_MODE, key, sr);
  //        // 正式执行解密操作
  //        byte decryptedData[] = cipher.doFinal(encryptedData);
  //        LogUtils.i(TAG, "解密后===>" + new String(decryptedData));
  //        return new String(decryptedData);
  //    }
}
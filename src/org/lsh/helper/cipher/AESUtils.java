package org.lsh.helper.cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by lsh on 15/2/15.
 */
public class AESUtils {

    private static SecretKey getKey(String password) {
        byte[] keyData = DigestUtils.sha256(password.getBytes());
        keyData = Arrays.copyOf(keyData, 16);
        return new SecretKeySpec(keyData, "AES");
    }

    public static String encrypt(String plain, String password) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getKey(password));
            return Base64.encodeBase64URLSafeString(cipher.doFinal(plain.getBytes()));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String ciphertext, String password) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getKey(password));
            byte[] data = Base64.decodeBase64(ciphertext);
            byte[] result = cipher.doFinal(data);
            return new String(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

}

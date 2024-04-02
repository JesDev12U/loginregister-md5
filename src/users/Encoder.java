package users;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

public class Encoder {
        String secretKey = "xXTQ@55-";
        public String encode(String str){
        String strEncrypt = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] keyPassword = md5.digest(
                    secretKey.getBytes("utf-8")
            );
            byte[] BytesKey = Arrays.copyOf(keyPassword, 24);
            SecretKey key = new SecretKeySpec(BytesKey, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTextBytes = str.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            strEncrypt = new String(base64Bytes);
        } catch (UnsupportedEncodingException | 
                InvalidKeyException | 
                NoSuchAlgorithmException | 
                BadPaddingException | 
                IllegalBlockSizeException | 
                NoSuchPaddingException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al encriptar"
            );
        }
        return strEncrypt;
    }

    public String decode(String strEncrypt) {
        String StrDesencrypt = "";
        try {
            byte[] message = Base64.decodeBase64(
                    strEncrypt.getBytes("utf-8"));
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md5.digest(
                    secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(
                    digestOfPassword,
                    24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainText = decipher.doFinal(message);
            StrDesencrypt = new String(plainText, "UTF-8");

        } catch (UnsupportedEncodingException | 
                InvalidKeyException | 
                NoSuchAlgorithmException | 
                BadPaddingException | 
                IllegalBlockSizeException | 
                NoSuchPaddingException ex) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Error al desencriptar");
        }
        return StrDesencrypt;
    }
}


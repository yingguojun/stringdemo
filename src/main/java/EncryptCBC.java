import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptCBC {

	private static final char[] BCS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

	private static final char PAD = '=';

	private static final byte[] PASSWORD_CRYPT_KEY = { 0x61, 0x73, 0x64, 0x66, 0x67, 0x68, 0x6a, 0x6b, 0x66, 0x67, 0x67, 0x66, 0x64, 0x67, 0x6b,
			0x77, 0x65, 0x77, 0x65, 0x57, 0x45, 0x40, 0x23, 0x40 };

	private static final byte[] PASSWORD_IV = { 0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xab, (byte) 0xcd, (byte) 0xef };

	public static void main(String[] args) throws Exception {
		String code = encode(encryptCBC("vliping$".getBytes("UTF-8"), PASSWORD_CRYPT_KEY, PASSWORD_IV, "PKCS5Padding"));
		code = URLEncoder.encode(code);
		System.out.println(code);
	}

	public static String encode(byte[] id) {
		
		char[] cd = encodeChunk(id, 0, id.length);
		return new String(cd, 0, cd.length);
	}

	private static char[] encodeChunk(byte[] id, int o, int l) {
		if (l <= 0)
			return null;
		char[] out;
		if ((l - o) % 3 == 0)
			out = new char[l / 3 * 4];
		else
			out = new char[l / 3 * 4 + 4];
		int rindex = o;
		int windex = 0;
		int rest = l - o;
		while (rest >= 3) {
			int i = ((id[rindex] & 0xff) << 16) + ((id[rindex + 1] & 0xff) << 8) + (id[rindex + 2] & 0xff);
			out[windex++] = BCS[i >> 18];
			out[windex++] = BCS[(i >> 12) & 0x3f];
			out[windex++] = BCS[(i >> 6) & 0x3f];
			out[windex++] = BCS[i & 0x3f];
			rindex += 3;
			rest -= 3;
		}
		if (rest == 1) {
			int i = id[rindex] & 0xff;
			out[windex++] = BCS[i >> 2];
			out[windex++] = BCS[(i << 4) & 0x3f];
			out[windex++] = PAD;
			out[windex++] = PAD;
		}
		else if (rest == 2) {
			int i = ((id[rindex] & 0xff) << 8) + (id[rindex + 1] & 0xff);
			out[windex++] = BCS[i >> 10];
			out[windex++] = BCS[(i >> 4) & 0x3f];
			out[windex++] = BCS[(i << 2) & 0x3f];
			out[windex++] = PAD;
		}
		return out;
	}

	public static byte[] encryptCBC(byte[] bytes, byte[] key, byte[] iv, String padding) throws Exception {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key, "DESede");
			Cipher cipher = Cipher.getInstance("DESede/CBC/" + padding);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(iv));
			return cipher.doFinal(bytes);
		}
		catch (InvalidKeyException e) {
			throw new Exception(e);
		}
		catch (InvalidAlgorithmParameterException e) {
			throw new Exception(e);
		}
		catch (NoSuchAlgorithmException e) {
			throw new Exception(e);
		}
		catch (NoSuchPaddingException e) {
			throw new Exception(e);
		}
		catch (IllegalBlockSizeException e) {
			throw new Exception(e);
		}
		catch (BadPaddingException e) {
			throw new Exception(e);
		}
	}
}

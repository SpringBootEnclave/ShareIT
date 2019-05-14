package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtil {

	public static String md5(String str) {
		MessageDigest md;
		String result = "";
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			BigInteger bi = new BigInteger(1, md.digest());

			result = bi.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String makeSlug(String title) {
		String slug = Normalizer.normalize(title, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		slug = pattern.matcher(slug).replaceAll("");
		slug = slug.toLowerCase();
		// Thay Ã„â€˜ thÃƒÂ nh d
		slug = slug.replaceAll("đ", "d");
		slug = slug.replaceAll("ê", "e");
		slug = slug.replaceAll("ă", "a");
		slug = slug.replaceAll("â", "a");
		slug = slug.replaceAll("ô", "o");
		slug = slug.replaceAll("ư", "u");
		slug = slug.replaceAll("ơ", "o");
		// XÃƒÂ³a cÃƒÂ¡c kÃƒÂ½ tÃ¡Â»Â± Ã„â€˜Ã¡ÂºÂ·t biÃ¡Â»â€¡t
		slug = slug.replaceAll("([^0-9a-z-\\s])", "");
		// Thay space thÃƒÂ nh dÃ¡ÂºÂ¥u gÃ¡ÂºÂ¡ch ngang
		slug = slug.replaceAll("[\\s]", "-");
		// Ã„ï¿½Ã¡Â»â€¢i nhiÃ¡Â»ï¿½u kÃƒÂ½ tÃ¡Â»Â± gÃ¡ÂºÂ¡ch ngang liÃƒÂªn tiÃ¡ÂºÂ¿p thÃƒÂ nh 1 kÃƒÂ½ tÃ¡Â»Â± gÃ¡ÂºÂ¡ch
		// ngang
		slug = slug.replaceAll("(-+)", "-");
		// XÃƒÂ³a cÃƒÂ¡c kÃƒÂ½ tÃ¡Â»Â± gÃ¡ÂºÂ¡ch ngang Ã¡Â»Å¸ Ã„â€˜Ã¡ÂºÂ§u vÃƒÂ  cuÃ¡Â»â€˜i
		slug = slug.replaceAll("^-+", "");
		slug = slug.replaceAll("-+$", "");
		return slug;
	}

	public static String standardizedString(String str) {
		str = str.trim();
		str = str.replaceAll("\\s+", " ");
		return str;
	}
	public static String standardizedString1(String str) {
		str = str.trim();
		str = str.replaceAll("\\s+", "");
		return str;
	}

}

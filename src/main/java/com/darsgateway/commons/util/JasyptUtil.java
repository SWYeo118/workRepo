package com.darsgateway.commons.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JasyptUtil {
	private static StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
	private static final String key = "yes3420911$";

	public static String encrypt(String pwd) {
		;
		try {
			pbeEnc.setAlgorithm("PBEWithMD5AndDES");
			pbeEnc.setPassword(key);
		} catch (Exception ex) {

		}
		String password = pbeEnc.encrypt(pwd);

		return password;
	}

	public static String decrypt(String pwd) {
		try {
			pbeEnc.setAlgorithm("PBEWithMD5AndDES");
			pbeEnc.setPassword(key);
		} catch (Exception ex) {

		}
		String password = pbeEnc.decrypt(pwd);

		return password;
	}

	public static void main(String[] args) {
		String pwd = encrypt("rural#2024!");

		 //System.out.println("encrypted : " + pwd);

		// pwd = decrypt(pwd);
		pwd = decrypt("6if4dZu/ERPXgAdHo/dnQYAPww7T+iVx");

		System.out.println("decrypted : " + pwd);

		pwd = encrypt("yes9114!");

		// System.out.println("encrypted : " + pwd);

		pwd = encrypt("1234qwer");

		// System.out.println("decrypted : " + pwd);

		pwd = encrypt("yes3420911$");

		// System.out.println("encrypted : " + pwd);

		pwd = decrypt(pwd);

		// System.out.println("decrypted : " + pwd);
	}
}

package com.MyStandalone;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		String sql ="AND TRUNC(UPLOADED_DATE) > TRUNC(SYSDATE - " + ":searchDayParam" + ")";

//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date());
//		cal.add(Calendar.MONTH, -1);
//		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
//		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
//		String runDate = formatter.format(cal.getTime());
//		Date tradingMonth = new SimpleDateFormat( "yyyyMMdd" ).parse( "2021030" );
//		Date startDate = DateUtil.firstDomainDateOfMonth(tradingMonth);
//		Date endDate = DateUtil.lastDomainDateOfMonth(tradingMonth);
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		String runDate = formatter.format(cal.getTime());
		
//		System.out.println(startDate);
//		System.out.println(endDate);
		
		//sealObjectExample();
		
		
		File file = new File("/app/jboss-app/testfile.txt");
		System.out.println(file.getCanonicalPath());
		System.out.println(file.getAbsolutePath());
		
		File sample = file.getCanonicalFile();
		System.out.println(sample.getCanonicalPath());
		
		sealObjectExample();

	}
	
	private static void sealObjectExample() throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128);
		SecretKey aesKey = kgen.generateKey();
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		
		String userName = "admin";
		String password = "P@ssw0rd";
		SealedObject test = new SealedObject(userName, cipher);
		
		System.out.println(test);
		System.out.println(test.getObject(aesKey));
	}
	
}

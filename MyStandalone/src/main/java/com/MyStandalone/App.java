package com.MyStandalone;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import com.github.vertical_blank.sqlformatter.SqlFormatter;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {		
		String sqlMCRun = "SELECT DENSE_RANK() over (order by PKA.authorisation_date desc ) rnk_no, "
				+ "PKA.authorisation_date auth_date, MCR.ID mcr_id, 'NONRER' mcr_run_type FROM NEM.NEM_MARKET_CLEARING_RUNS MCR, "
				+ "NEM.PAV_PACKAGES PKG, NEM.NEM_PACKAGE_AUTHORISATIONS PKA "
				+ "WHERE to_char(MCR.RUN_DATE,'dd-mm-yyyy') = ? AND MCR.MCR_TYPE IN ('DPR','ADM','RGP') "
				+ "AND mcr.period = ? AND mcr.pkg_id = pkg.id "
				+ "AND PKG.ID = PKA.PKG_ID AND PKA.authorisation_status ='AUTHORISED' ";
		String sqlMCRerun = "SELECT DENSE_RANK() over (order by mcr.approval_datetime desc ) rnk_no, "
				+ "mcr.approval_datetime auth_date, mcr.ID mcr_id, 'RER' mcrReRunType  "
				+ "FROM NEM.NEM_MARKET_CLEARING_RERUNS mcr "
				+ "WHERE to_char(mcr.run_date,'dd-mm-yyyy') = ? AND mcr.period = ? "
				+ "AND MCR.MCR_TYPE = 'RER' AND mcr.approved = 'Y'";

		//System.out.println(SqlFormatter.format(sqlMCRun));
		
		String pkgVersion = "900971";
		String pkg_version = ""	;
		if(pkg_version.length() == 0) {
			pkg_version = pkgVersion;
		}
		System.out.println(pkg_version);
		System.out.println(pkg_version.length());
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

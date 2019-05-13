package com.muhuan.Service;

import com.muhuan.Service.controller.UserController;
import com.muhuan.Service.mapper.ClassroomApplySheetMapper;
import com.muhuan.Service.pojo.flow.ClassroomApplySheet;
import com.muhuan.Service.util.InnerRsaUtil;
import com.muhuan.Service.util.RsaUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceApplicationTests {


	private UserController userController;

	@Test
	public void Test(){
//		try {
//
//			BigInteger modulus = new BigInteger("cb2474a7fcf3260f0e4bc5d2d8370a99e5d376e94d496b5f13be798b6d3bc31e94bfaa2b67fe20cfbd6c286fbd426d2e6cd29577275cceda50882df810ba54b8caa0123deb5b1f327af380136cf94342b5a26a09aeeb9d637b9a2c5f52b3be7e83d5b50447ce7130afce4bd208856371832220a85479260cb0344f895f61561d".getBytes());
//			BigInteger privateExponent = new BigInteger("392860ca8399c6b3a5ca1c375f2edd84bfede81f6ce7a39674efbb3347e0f0ddc1eccbecbaadfe7d7546e179cf3ec989ae84f2193cb21861c5170c18285cc549592522c178523f97f0d6a74f961a6980b842f1e7889f6c4bd69392eb8ccbcfff343b3b6d235016ae9305cd7ccf4961df29b0dcbb942397c5c94cacf944c7b701".getBytes());
//			BigInteger publicExponent = new BigInteger("10001".getBytes());
//			BigInteger primeP = new BigInteger("e4bfcde9f19795d1c1a3d74c026921bdc211432c40910751319bd54d28670025c0d517aeca7e167b843230cf9c1657405f3c668aafa844b0b39cfc248c1be6b9".getBytes());
//			BigInteger primeQ = new BigInteger("e357b76897330b835e97a04066509bc19139b719ecde75edd20dc1d087ef45e9a3def79f7993850a8dd6f6361cd3a78fb76378dc37355682f473083b98ba3885".getBytes());
//			BigInteger primeExponentP = new BigInteger("a2794db36dc2c24a3324c1661e8c37c80550a78a85edbce9c1b7b2c054031ccab3f56715e283be4a4af22f5336c57381a863495e1638095a0ca9633a3e3e0f19".getBytes());
//			BigInteger primeExponentQ = new BigInteger("35114aaccd6e8ae775732b631cfefffca52bb5c40578c04a6d57ecc0029b25d4adfde40b83d39da0a30f6495a910f6f2233ac30194998cc230d19140681c4009".getBytes());
//			BigInteger crtCoefficient = new BigInteger("3e0910e59174971b94860a0a3bcf7a732dbec4f5fae7500f0c69197d891bf0f378bf394804c81ea65f2f096d5bdd90f32e64d53875c24dcb0a48463fd08617b7".getBytes());
//			RSAPrivateCrtKeySpec spec = new RSAPrivateCrtKeySpec(modulus, publicExponent, privateExponent, primeP, primeQ, primeExponentP, primeExponentQ, crtCoefficient);
//			KeyFactory keyFactory = KeyFactory.getInstance("RSA","BC");
//			PrivateKey privateKey = keyFactory.generatePrivate(spec);
//			System.out.println(privateKey);
//
//		}catch (Exception e){
//			System.out.println(e);
//		}


		try {

			KeyPair keyPair = RsaUtil.generateKeyPair();


//			序列化
			FileOutputStream f = new FileOutputStream("D:\\javaProject\\keyPair");
			ObjectOutputStream s = new ObjectOutputStream(f);
			s.writeObject(keyPair);
			s.flush();
			s.close();

			try {
				FileInputStream in = new FileInputStream("D:\\javaProject\\keyPair");
				ObjectInputStream s1 = new ObjectInputStream(in);
				keyPair = (KeyPair)s1.readObject(); //恢复对象;
				RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
				RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
				String Modulus = rsaPublicKey.getModulus().toString(16);
				String Exponent = rsaPublicKey.getPublicExponent().toString(16);
				System.out.println(rsaPrivateKey);
				System.out.println(rsaPublicKey);
			}catch (Exception e){
				System.out.println(e);
			}

		}catch (Exception e){
			System.out.println(e);
		}


		try {
//			Map<String, String> keyMap = InnerRsaUtil.createKeys(1024);
//			String publicKey = keyMap.get("publicKey");
//			String privateKey = keyMap.get("privateKey");

			String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcq2rzZLzbZpu2LUUMZUkxtsAC\n" +
					"iCfKK7RrdDTzIdniZEIpcPSBvAzfS6dtbAhefhw7ynHnm2Ee7YN6lzmBpIeBapvZ\n" +
					"OqRdaOxnCzOB+ZX3yyBqyHbJGWpA8gjDFmrq/ErQUMWSqgpf6vj3A3WqoWDEyUcS\n" +
					"oTqxyz7KzNZubJj7LQIDAQAB";
			String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANyravNkvNtmm7Yt\n" +
					"RQxlSTG2wAKIJ8ortGt0NPMh2eJkQilw9IG8DN9Lp21sCF5+HDvKceebYR7tg3qX\n" +
					"OYGkh4Fqm9k6pF1o7GcLM4H5lffLIGrIdskZakDyCMMWaur8StBQxZKqCl/q+PcD\n" +
					"daqhYMTJRxKhOrHLPsrM1m5smPstAgMBAAECgYB8G6C0MTUShFwREtbhyNlor2rA\n" +
					"Qcl3KCt5v8rD74b2kAKyAghSKuxmbctFfFwcoPrKGesEItx1o4mt2f2Kz3kxoaOP\n" +
					"RwYkHL3MQuIBV0/RzphVsO8OplzCcteR6lz8cB/Om7JkzquV2ZBfS9JUjHQKaNLu\n" +
					"PRnXyfhC6PUKn6vQIQJBAPhFTmpOki7JMBU8uHrB1ivjGiKUU8nCWaODA59LaBDw\n" +
					"I6McT/78hNsT+P8aapa7kXFbQSRnPwsk/riTAnwllrkCQQDjiiIWU+yynqs+H+4U\n" +
					"3mOLdUPcEwJnXVUNtT39aa68rfOZ5QPYwkzY6cynoZVhjIDzvti0Iar+1MZXOyqe\n" +
					"sY4VAkAs681eas0Ebh6nGQ+AFqZ71mGaNCBc9y9k6IW1Qt2XgvvPvYWz61jWkuyQ\n" +
					"q+TxVQrh6dMFlTDRAWadWuwuLlbxAkAGJhZzugLcdNM105EQeU4BV8LksJLRDkGd\n" +
					"JDevoGp7aMv7bafj9KQ0/GRuZzxtLWnSrGaYv4wqZL+TXeLx9ORdAkAK++uNMZKd\n" +
					"Pj9M/HXYKjjm+yaK5C19rJc130ZNIpM2EgTAzy5/jpSW2kJycbb2OCmIjQhzPUIx\n" +
					"sgh1hZQGvXn1";

			System.out.println("公钥:" + publicKey);
			System.out.println("私钥:" + privateKey);
			System.out.println("公钥加密——私钥解密");

			String str = "陈美琪";
			System.out.println("明文:" + str);
			System.out.println("明文大小:" + str.getBytes().length);
			String encodedData = InnerRsaUtil.publicEncrypt(str, InnerRsaUtil.getPublicKey(publicKey));
			System.out.println("密文：" + encodedData);
			encodedData = "oTqPSKI+V0lp5tXsAhIlR24umMt0HRQ3qDFux0T6XhmvFYXNebi/MchJ+3D5VYjc1O6GgE8BVJDhbkZbKqyGMrDOIRCFPMbGagbbo4WL4ODqC3E4EU6tGorEkda4ps+dorgrPPBdmrbXMsA/b9NHGYPFeA7j3xEtnuk5Yozos+A=";
			String decodedData = InnerRsaUtil.privateDecrypt(encodedData, InnerRsaUtil.getPrivateKey(privateKey));
			System.out.println("解密后文字:" + decodedData);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

	}

	@Autowired
	ClassroomApplySheetMapper classroomApplySheetMapper;


//	@Test
//	public void Test2(){
//
//
//		List<ClassroomApplySheet> classroomApplySheets = classroomApplySheetMapper.teacherProveList("162011",new Date());
//		for (ClassroomApplySheet classroomApplySheet : classroomApplySheets){
//			System.out.println(classroomApplySheet.toString());
//		}
//
//		classroomApplySheetMapper.updateTProve(26,true,"等待管理员老师同意","");
//
//		System.out.println("-------------");
//		List<ClassroomApplySheet> classroomApplySheets1  = classroomApplySheetMapper.adminProveList(new Date());
//		for (ClassroomApplySheet classroomApplySheet : classroomApplySheets1){
//			System.out.println(classroomApplySheet.toString());
//		}
//		classroomApplySheetMapper.updateAProve(26,true,"通过审核","");
//	}


}
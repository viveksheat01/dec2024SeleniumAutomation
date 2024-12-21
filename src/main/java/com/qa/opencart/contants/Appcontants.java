package com.qa.opencart.contants;

import java.util.Arrays;
import java.util.List;

public class Appcontants {
	
	
	public static final String CONFIF_FILE_PATH="./src/test/resource/config/config.properties";
	public static final String CONFIF_dEV_FILE_PATH="./src/test/resource/config/dev.properties";
	public static final String CONFIF_QA_FILE_PATH="./src/test/resource/config/qa.properties";
	public static final String CONFIF_UAT_FILE_PATH="./src/test/resource/config/uat.properties";

	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String ACCOUNT_PAGE_TITLE="My Account";
	public static final String LOGIN_PAGE_FRACTION_URL="route=account/login";
	public static final String ACC_PAGE_FRACTION_URL="route=account/account";
	
	public static final String LOGIN_WITH_INVALID_CREDENTAIL
	="Warning: No match for E-Mail Address and/or Password.";

	
	
	public static final List<String> ACC_PAGE_HEADERS_LIST=
			Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	
	
	public static final CharSequence USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	
	//******************************SHEET CONTANTES************************//
	
	public static final String REGISTER_SHEET_NAME="reg";
	public static final String PRODUCT_IMAGES_COUNT = "productimages";

	
	
	





}

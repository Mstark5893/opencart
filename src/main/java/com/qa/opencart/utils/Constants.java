package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String LOGIN_PAGE_TITLE = "Account Login";
    public static final String LOGIN_PAGE_URL = "account/login";


    public static final int DEFAULT_PAGE_LOAD_TIME = 5;
    public static final List<String> EXPECTED_HEADER_LIST = new ArrayList<>(Arrays.asList("My Account", "My Orders",
            "My Affiliate Account", "Newsletter"));

    public static final String SEARCH_PAGE_HEADER_TEX_MACBOOK = "Search - macbook";
    public static final String SEARCH_PAGE_HEADER_TEXT_APPLE = "Search - apple";


    public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";
    public static final String REGISTER_SHEET_NAME = "register";
}
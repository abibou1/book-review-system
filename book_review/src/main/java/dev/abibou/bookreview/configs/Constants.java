package dev.abibou.bookreview.configs;

import dev.abibou.bookreview.payload.request.UserRequest;

public class Constants {
	
	public static final String SAVE_BOOK_URL = "/admin/book/save";
	public static final String LOGIN_URL = "/api/auth/login";
	public static final String SIGNUP_URL = "/api/auth/signup";
	public static final String GET_ALL_BOOKS = "/authenticated/book/all-books";
	
	public static final UserRequest ADMIN_USER = new UserRequest("ambodji", "1234567", "ADMIN");
	public static final UserRequest SIMPLE_USER = new UserRequest("simpleuser", "1234567", "USER");

}

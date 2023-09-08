package dev.abibou.bookreview.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {
	
	public static String convertToString(final Object obj) {
		try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}

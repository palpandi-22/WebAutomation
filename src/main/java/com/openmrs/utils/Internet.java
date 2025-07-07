package com.openmrs.utils;

import java.net.URL;
import java.net.URLConnection;

public class Internet {



	/**
	 * @author Palpandi
	 * @implNote will check the internet is available or not
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean check_Internet_Connection()
	{
		boolean no_Internet = true;
		try {
			URL url = new URL(ConfigLoader.getInstance().getBaseUrl());
			URLConnection connection = url.openConnection();
			connection.connect();
		} catch (Exception e) {
			no_Internet = false;

		}
		return no_Internet;
	}

}

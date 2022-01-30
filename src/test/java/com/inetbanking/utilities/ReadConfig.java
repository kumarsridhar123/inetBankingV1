package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {         // constructor to run a complete file
	
	Properties pro;
	
	public ReadConfig()
	{
	File src = new File ("./Configurations/config.properties");
	try {
		
		FileInputStream fis = new FileInputStream(src);
		pro= new Properties();
		pro.load(fis);
	} catch (Exception e) {
		
	    System.out.println("Exception is" + e.getMessage());
	
	}
}
	public String getApplicationURL()
	{
		String url = pro.getProperty("baseURL");
		return url;
			}
	
	public String getUsername()
	{
		String username = pro.getProperty("username");
		return username;
			}
	
	public String getPassword()
	{
		String password = pro.getProperty("password");
		return password;
			}
	public String getchromepath()
	{
		String chrome = pro.getProperty("chromepath");
		return chrome;
			}
	
	public String getiepath()
	{
		String ie = pro.getProperty("iepath");
		return ie;
			}
	public String getfirefox()
	{
		String firefox = pro.getProperty("firefoxpath");
		return firefox;
			}
	
	
	
}

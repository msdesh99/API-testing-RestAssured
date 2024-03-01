package com.numpyninja.apibootcamp.api.requestbody;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.numpyninja.apibootcamp.api.payload.ResponseModel;
import com.numpyninja.apibootcamp.api.payload.User;
import com.numpyninja.apibootcamp.api.utils.XLUtility;


public class UserRequestBody extends XLUtility{

	public static Object[] GetPostRequestBody(String sheetname, String file, int rowNumber) throws Exception {
       System.out.println("in userrequest to get xlshhee");
		Map<String, String> requestMap;
		User user = new User();
		ResponseModel responseModel = new ResponseModel();
		
		requestMap = XLUtility.getData(sheetname,file,rowNumber);	
		user.setUser_first_name(requestMap.get("FirstName"));
		user.setUser_last_name(requestMap.get("LastName"));
		user.setUser_contact_number(requestMap.get("ContactNumber"));
		user.setUser_email_id(requestMap.get("EmailId"));
		//if(requestMap.containsKey("UserId"))  		
			//user.setUser_id(Integer.valueOf(requestMap.get("UserId")));

		HashMap<String, String> newMap = new LinkedHashMap<String, String>(); 
		newMap.put("plotNumber",requestMap.get("PlotNo"));
		newMap.put("street",requestMap.get("Street"));
		newMap.put("state",requestMap.get("State"));
		newMap.put("country",requestMap.get("Country"));
		newMap.put("zipCode",requestMap.get("ZipCode"));
       
        user.setUserAddress(newMap);
        responseModel.setStatus(requestMap.get("Status"));
        responseModel.setStatusCode(requestMap.get("StatusCode"));
        responseModel.setStatusMessage(requestMap.get("Message"));

        Object[] obj = {user,responseModel};
		return obj;
		
	}
}


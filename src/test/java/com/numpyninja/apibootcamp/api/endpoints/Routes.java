package com.numpyninja.apibootcamp.api.endpoints;

public class Routes {

    public static String base_url = "https://userapi-8877aadaae71.herokuapp.com/uap";
	
	
	//UserModule
        public static String PostUser_Url = base_url+"/createusers";
        public static String GetAllUsers_Url = base_url+"/users";
        public static String GetUser_ByUserId_Url = base_url+"user/{userId}";
        public static String GetUser_ByUserFristname_Url = base_url+"users/username/{userFirstName}";
        public static String UpdateUser_ByUserId_Url = base_url+"updateuser/{userId}";		
        public static String DeleteUser_ByUserId_Url = base_url+" /deleteuser/{userId}";
        public static String DeleteUser_ByUserFristname_Url= base_url+"/deleteuser/username/{userfirstname}";


}

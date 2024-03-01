package com.numpyninja.apibootcamp.api.endpoints;
/*
enum Endpoints{
    //postUser("postUserEndpoint",baseUrl+"/createusers");
    CREATEUSER("postUserEndpoint");

	String endpoint;
	Endpoints(String string) {
	}
	public void Endpoint(String endpoint) {
		this.endpoint = endpoint ;
	}
}	*/
public class Endpoints {
    public static String baseUrl = "https://userapi-8877aadaae71.herokuapp.com/uap";
	//public static String dataFile = System.getProperty("user.dir") + "/src/test/resources/testData/UserPostData.xlsx";
	public static String dataFile = System.getProperty("user.dir") + "/src/test/resources/testData/UserPostDataNew.xlsx";

	
	//UserModule
        public static String postUserEndpoint = baseUrl+"/createusers";
        //public static String postRequestXLFile = fileLocation+"UserPostData.xlsx";
        public static String postPositiveScenarioSheet = "UserPositivePostScenario";
        public static String postandputNegativeScenarioSheet = "UserNegativePutandPostScenario";



        public static String getAllUsersEndpoint = baseUrl+"/users";
        public static String getUserByUserIdEndpoint = baseUrl+"/user/";
        public static String getUserByFirsttNameEndpoint = baseUrl+"/users/username/";
        
        public static String putUserByUserIdEndpoint = baseUrl+"/updateuser/";		
        public static String putPositiveScenarioSheet = "UserPositivePutScenario";


        public static String DeleteUserByUserIdEndpoint = baseUrl+"/deleteuser/";
        public static String DeleteUserByUserFirstNameEndpoint= baseUrl+"/deleteuser/username/";


}

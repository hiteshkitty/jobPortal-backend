package com.troika.helper;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.troika.domain.view.CompanyProfile;

public class ObjectToJson {
//	public static void main(String[] args) {
//        CompanyProfile  company = new CompanyProfile();
//       
//        company.setCompanyName("Quark");
//        company.setCompanyType(1);
//        company.setCompanyWebsite("http://quark.com");
//        company.setEmailId("quark@quark.com");
//        company.setEstablishmentDate(new Date());
//        company.setIsActive(true);
//        company.setLocation("Mohali");
//        company.setName("Hitesh");
//        company.setPrimaryMobileNumber("11111111");
//        company.setStreetAddress("Phase 8");
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            String json = mapper.writeValueAsString(company);
//            System.out.println("JSON = " + json);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
	
	void createCompany() {
		 CompanyProfile  company = new CompanyProfile();

		company.setCompanyName("Quark");
		company.setCompanyType(1);
		company.setCompanyWebsite("http://quark.com");
		company.setEmailId("quark@quark.com");
		company.setEstablishmentDate(new Date());
		company.setIsActive(true);
		company.setPrimaryMobileNumber("11111111");
		company.setStreetAddress("Phase 8");
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(company);
			System.out.println("JSON = " + json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		 }

		 
	}
}

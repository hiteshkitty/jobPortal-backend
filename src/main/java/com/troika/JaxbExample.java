//package com.troika;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//
//import com.troika.domain.view.SeekerProfile;
//import com.troika.domain.view.UserProfile;
//
//public class JaxbExample {
//
//	private static final String FILE_NAME = "jaxb-ipcFilter.xml";
//
//	public static void main(String[] args) {
//
//		UserProfile request = new UserProfile();
//
//		// List<String> list = new ArrayList<>();
//		// list.add("Java");
//		// list.add("Spring");
//		// request.setExperience(2);
//		// request.setLocation("Noida");
//		// request.setSalary(111);
//		// request.setSkillSetList(list);
//
//		request.setContactNumber(12121);
//		request.setDateOfBirth("2apr");
//		request.setEmail("hitesh@gmail.com");
//		request.setEmailNotificationActive(Boolean.FALSE);
//		request.setGender("M");
//		request.setPassword("abc");
//		request.setIsActive(true);
//		request.setRegistrationDate("14apr");
//		request.setUserid(1);
//		request.setUserType(1);
//
//		SeekerProfile sp = new SeekerProfile();
//
//		sp.setCurrentLocation("NOida");
//		sp.setExpInMonths(122);
//		sp.setFirsName("hitesh");
//		sp.setHighestQualification("MCA");
//		sp.setLastName("Sharma");
//		sp.setPreferredLocation("Noida");
//		sp.setProfileSummary("Java tech architect");
//
//		List<Integer> seekerSkillSets = new ArrayList<Integer>();
//		seekerSkillSets.add(1);
//		seekerSkillSets.add(2);
//
//		sp.setSeekerSkillSets(seekerSkillSets);
//
//		request.setSeekerProfileRequest(sp);
//		jaxbObjectToXML(request);
//
//		// IpcFilter ipcFilterFromFile = jaxbXMLToObject();
//		// System.out.println(ipcFilterFromFile.toString());
//	}
//
//	private static void jaxbObjectToXML(UserProfile recruiterProfile) {
//
//		try {
//			JAXBContext context = JAXBContext.newInstance(UserProfile.class);
//			Marshaller m = context.createMarshaller();
//			// for pretty-print XML in JAXB
//			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//
//			// Write to System.out for debugging
//			m.marshal(recruiterProfile, System.out);
//
//			// Write to File
//			// m.marshal(ipcFilter, new File(FILE_NAME));
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
//
////
//// private static RecruiterProfile jaxbXMLToObject() {
//// try {
//// JAXBContext context = JAXBContext.newInstance(IpcFilter.class);
//// Unmarshaller un = context.createUnmarshaller();
//// IpcFilter ipcFilter = (IpcFilter) un.unmarshal(new File(FILE_NAME));
//// return ipcFilter;
//// } catch (JAXBException e) {
//// e.printStackTrace();
//// }
//// return null;
//// }

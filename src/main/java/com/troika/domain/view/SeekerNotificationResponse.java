package com.troika.domain.view;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class SeekerNotificationResponse implements Serializable{

	private static final long serialVersionUID = 6106851417947470681L;

	private String jobTitle;
	
	private String jobDescription;
	
	private String location;
	
	private Integer salary;
	
	private Integer experience;
	
	private Date appliedOn;

}

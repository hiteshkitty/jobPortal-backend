package com.troika.domain.view;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchResumeResponse implements Serializable {

	private static final long serialVersionUID = -7337273930582922420L;

	private String firstName;

	private String lastName;

	private Integer experience;

	private Integer salary;

	private String location;

	private String profileSummary;

	private List<String> skillSet;

}

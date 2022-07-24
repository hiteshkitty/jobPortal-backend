package com.troika.services;

import java.util.Set;

import com.troika.domain.model.UserAccount;
import com.troika.helper.GenericException;

public interface AuthenticationService {

	UserAccount authenticate(String authCredentials, Set<String> rolesSet) throws GenericException;

}

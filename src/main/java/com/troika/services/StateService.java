package com.troika.services;

import java.util.List;

import com.troika.domain.model.State;

public interface StateService {

	State retrieveStateById(final Integer stateId);

	State postState(State state);

	State deleteStateById(Integer compId);

	State updateState(State state);

	List<State> findAll();

}

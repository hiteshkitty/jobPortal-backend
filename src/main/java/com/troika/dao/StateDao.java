package com.troika.dao;

import java.util.List;

import com.troika.domain.model.State;

public interface StateDao {

	State retireveStateById(Integer stateId);

	State createState(State state);

	void deleteState(State stateToDelete);

	State updateState(State stateToUpdate);

	List<State> findAll();
}

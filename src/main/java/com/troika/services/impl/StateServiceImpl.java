package com.troika.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.troika.dao.StateDao;
import com.troika.domain.model.State;
import com.troika.services.StateService;

@Service
public class StateServiceImpl implements StateService {

	private static final Logger LOGGER = Logger.getLogger(StateServiceImpl.class);

	@Autowired
	private StateDao stateDao;

	@Override
	public State retrieveStateById(Integer stateId) {
		State state = stateDao.retireveStateById(stateId);

		return state;
	}

	@Override
	public State postState(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State deleteStateById(Integer stateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State updateState(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Cacheable("states")
	public List<State> findAll() {

		LOGGER.trace("fetching all states");

		List<State> states = stateDao.findAll();

		LOGGER.trace("fetched states: " + states);

		return states;

	}

}

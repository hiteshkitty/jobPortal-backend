package com.troika.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.troika.dao.StateDao;
import com.troika.domain.model.State;
import com.troika.repo.StateRepo;

@Component
public class StateDaoImpl implements StateDao {

	private static final Logger LOGGER = Logger.getLogger(StateDaoImpl.class);

	@Autowired
	private StateRepo stateRepo;

	@Override
	@Cacheable("state")
	public State retireveStateById(Integer stateId) {

		State comp = stateRepo.findOne(stateId);

		LOGGER.trace("State: " + comp);

		return comp;

	}

	@Override
	public State createState(State state) {

		LOGGER.trace("creating state details: " + state);

		State stateCreated = stateRepo.save(state);

		LOGGER.trace("created StateDetails: " + stateCreated);

		return stateCreated;

	}

	@Override
	public void deleteState(State stateToDelete) {

		LOGGER.trace("deleting state details: " + stateToDelete);

		stateRepo.delete(stateToDelete);

		LOGGER.trace("created StateDetails: " + stateToDelete);

	}

	@Override
	public State updateState(State stateToUpdate) {

		LOGGER.trace("updating state details: " + stateToUpdate);

		State stateUpdated = stateRepo.save(stateToUpdate);

		LOGGER.trace("stateUpdated StateDetails: " + stateUpdated);

		return stateUpdated;
	}

	@Override
	public List<State> findAll() {

		List<State> state = null;

		LOGGER.trace("fetching all State");

		state = (List) stateRepo.findAll();

		LOGGER.trace("fetch all State: ");

		return state;
	}

}

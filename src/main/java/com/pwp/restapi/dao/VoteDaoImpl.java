package com.pwp.restapi.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwp.restapi.model.Contest;
import com.pwp.restapi.model.User;
import com.pwp.restapi.model.Vote;

@Repository
@Transactional
public class VoteDaoImpl implements VoteDao {

	private static final Logger logger = LoggerFactory.getLogger(VoteDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public Boolean addVote(Vote v, int userId, int contestId) {
		// TODO Auto-generated method stub	
		Session session = this.sessionFactory.getCurrentSession();
		
		Contest contest = (Contest)session.load(Contest.class, new Integer(contestId));
		v.setContest(contest);
		
		User user = (User)session.load(User.class, new Integer(userId));
		v.setUser(user);
		
		try {
			session.persist(v);
			logger.info("Vote added successfully, Contest Details="+v);

		} catch( Exception e ) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Vote> getAllVotes(int contestId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();

		List<Vote> voteList = session.createQuery(" from Vote vote where vote.contest.id="+contestId).list();
		
		for(Vote vote : voteList) {
			logger.info("Vote List::"+vote);
		}

		return voteList;
	}
}
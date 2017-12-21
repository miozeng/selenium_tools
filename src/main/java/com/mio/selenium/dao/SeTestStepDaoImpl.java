package com.mio.selenium.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mio.selenium.domain.SeTestStep;

@Repository
public class SeTestStepDaoImpl extends BaseDaoImpl<SeTestStep, Long> implements SeTestStepDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer getMaxSeq(Long caseId) {
		Session session =sessionFactory.getCurrentSession();
		Query q = session.createQuery("select max(a.sequence) from SeTestStep a where a.testCase.caseId =?" );
		q.setParameter(0, caseId);
		if(q.uniqueResult() == null){
			return 0;
		}
		Integer c = (Integer)q.uniqueResult();
		return c;
	}



}

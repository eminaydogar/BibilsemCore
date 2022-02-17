package com.project.job.scheduledTask;

import java.util.ArrayList;
import java.util.List;

import com.project.annotation.JobDefinition;
import com.project.job.AJob;
import com.project.job.JobDao;
import com.project.job.beans.CouponValidatiorBean;


@JobDefinition
public class CouponValidatorJob extends AJob {

	private final JobDao dao;

	public CouponValidatorJob(JobDao dao) {
		this.dao = dao;
		identifier = 1L;
	}
	
	@Override
	protected void initilazeBean() {
		jobBean = dao.initilazer(identifier);
	}
	
	@Override
	protected void execute() {

		List<CouponValidatiorBean> beanList = new ArrayList<CouponValidatiorBean>();
		@SuppressWarnings("unchecked")
		List<Object[]> results = dao.getEntityManager().createNativeQuery("SELECT \r\n"
				+ "CD.ID COUPON_ID,\r\n"
				+ "QD.ID QUESTION_ID,\r\n"
				+ "QAD.ANSWER_VALUE COUPON_ANSWER,\r\n"
				+ "QD.QUESTION_TEXT,\r\n"
				+ "QD.ANSWER QUESTION_ANSWER\r\n"
				+ " FROM coupon_definition CD \r\n"
				+ "JOIN COUPON_QUESTION_ANSWER_LIST CQAL\r\n"
				+ "ON CQAL.COUPON_ID = CD.ID\r\n"
				+ "JOIN QUESTION_ANSWER_DEFINITION QAD\r\n"
				+ "ON QAD.ID=CQAL.QUESTION_ANSWER_ID\r\n"
				+ "JOIN QUESTION_DEFINITION QD\r\n"
				+ "ON QD.ID=QAD.QUESTION_ID\r\n"
				+ "WHERE CD.EDATE IS NULL AND CD.COUPON_STATUS ='W'").getResultList();
		CouponValidatiorBean tempBean = null;
		if(results!=null) {
			for(Object[] result:results) {
				CouponValidatiorBean bean = new CouponValidatiorBean(result);
				if(tempBean==null) {
					beanList.add(bean);
					continue;
				}
				else if(tempBean.getCouponId()==bean.getCouponId()) {
					tempBean.setNext(bean);
				}else {
					beanList.add(tempBean);
				}
			}
			
		}

	}



}

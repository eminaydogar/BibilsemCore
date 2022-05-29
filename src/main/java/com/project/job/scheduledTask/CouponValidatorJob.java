package com.project.job.scheduledTask;

import java.util.List;

import com.project.common.annotation.JobDefinition;
import com.project.job.AJob;
import com.project.job.JobDao;
import com.project.job.beans.CouponValidatiorBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@JobDefinition
public class CouponValidatorJob extends AJob {

	private final JobDao dao;

	public CouponValidatorJob(JobDao dao) {
		this.dao = dao;
		identifier = 1L;
	}


	@Override
	protected void execute() {

		List<Object[]> results = null; /*dao.getCoreManager().getObjectList("SELECT \r\n" + "				CD.ID COUPON_ID,\r\n"
				+ "				QD.ID QUESTION_ID,\r\n" + "				QAD.ANSWER_VALUE COUPON_ANSWER,\r\n"
				+ "				QD.ANSWER QUESTION_ANSWER,\r\n" + "				UCL.USER_ID,\r\n"
				+ "				CD.COUPON_PRICE\r\n" + "				FROM coupon_definition CD \r\n"
				+ "				LEFT JOIN COUPON_QUESTION_ANSWER_LIST CQAL\r\n"
				+ "				ON CQAL.COUPON_ID = CD.ID\r\n"
				+ "				LEFT JOIN QUESTION_ANSWER_DEFINITION QAD\r\n"
				+ "				ON QAD.ID=CQAL.QUESTION_ANSWER_ID\r\n"
				+ "				LEFT JOIN QUESTION_DEFINITION QD\r\n" + "				ON QD.ID=QAD.QUESTION_ID\r\n"
				+ "				                JOIN USER_COUPON_LIST UCL\r\n"
				+ "                ON UCL.COUPON_ID = CD.ID"
				+ "                WHERE CD.COUPON_STATUS ='W' AND QD.ANSWER IS NOT NULL ORDER BY COUPON_ID", null); */
		CouponValidatiorBean processBean = null;
		if (results != null) {
			for (Object[] result : results) {
				CouponValidatiorBean bean = new CouponValidatiorBean(result);
				if (processBean == null) {
					processBean = bean;
				} else if (processBean.getCouponId() == bean.getCouponId()) {
					setNextNode(processBean, bean);
				} else {
					System.out.println("--------------------UPDATE ZAMANI-------------");
					updateCouponStatus(processBean);
					processBean = bean;
				}
			}
			// son kupon dizide process olmayacağı için bu kosul konulmalı
			if (processBean != null) {
				updateCouponStatus(processBean);
			}

		}

	}

	private void updateCouponStatus(CouponValidatiorBean bean) {

		boolean isExecuted = false;
		CouponValidatiorBean tempBean = bean;
		do {

			if (!tempBean.getQuestion_answer().equalsIgnoreCase(tempBean.getCoupon_answer())) {
				executeStatus("F", bean.getCouponId());
				isExecuted = true;
				break;
			}
			tempBean = tempBean.getNext();

		} while (tempBean != null);

		if (!isExecuted) {
			executeStatus("S", bean.getCouponId());
			executeBBPoint(bean.getCouponPrize(), bean.getUserId());
		}

	}

	private void executeStatus(String status, Long couponId) {
		try {
			dao.getCoreManager().saveOrUpdate("UPDATE COUPON_DEFINITION SET COUPON_STATUS = ? WHERE ID=?", status, couponId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private void executeBBPoint(Long couponPrice, Long userId) {
		try {
			dao.getCoreManager().saveOrUpdate("UPDATE USER_DEFINITION SET bbpoint = bbpoint + ? WHERE ID=?", couponPrice, userId);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private void setNextNode(CouponValidatiorBean baseBean, CouponValidatiorBean childBean) {
		while (baseBean.getNext() != null) {
			baseBean = baseBean.getNext();
		}
		baseBean.setNext(childBean);
	}

}

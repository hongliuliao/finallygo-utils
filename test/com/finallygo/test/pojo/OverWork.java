/*
 * Created on 2010-04-20
 *
 */
package com.finallygo.test.pojo;


import java.sql.Date;
import java.sql.Timestamp;


/**
 * @author denlly-BeanGenerator
 *
 */
public class OverWork {

	private Integer workId;
	private Date workTime;
	private String workArea;
	private String workContent;
	private String whoWork;
	private Timestamp updateDt;

	public Integer getWorkId(){
		return this.workId;
	}
	public void setWorkId(Integer workId){
		this.workId=workId;
	}

	public Date getWorkTime(){
		return this.workTime;
	}
	public void setWorkTime(Date workTime){
		this.workTime=workTime;
	}

	public String getWorkArea(){
		return this.workArea;
	}
	public void setWorkArea(String workArea){
		this.workArea=workArea;
	}

	public String getWorkContent(){
		return this.workContent;
	}
	public void setWorkContent(String workContent){
		this.workContent=workContent;
	}

	public Timestamp getUpdateDt(){
		return this.updateDt;
	}
	public void setUpdateDt(Timestamp updateDt){
		this.updateDt=updateDt;
	}
	public String getWhoWork() {
		return whoWork;
	}
	public void setWhoWork(String whoWork) {
		this.whoWork = whoWork;
	}

}

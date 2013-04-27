/*
 * Created on 2010-04-13
 *
 */
package com.finallygo.test.pojo;


import java.sql.Timestamp;


/**
 * @author denlly-BeanGenerator
 *
 */
public class GoodsInfo {

	private Integer goodsId;
	private String goodsName;
	private Double goodsPrice;
	private String goodsPlace;
	private Timestamp updateDt;

	public Integer getGoodsId(){
		return this.goodsId;
	}
	public void setGoodsId(Integer goodsId){
		this.goodsId=goodsId;
	}

	public String getGoodsName(){
		return this.goodsName;
	}
	public void setGoodsName(String goodsName){
		this.goodsName=goodsName;
	}

	public Double getGoodsPrice(){
		return this.goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice){
		this.goodsPrice=goodsPrice;
	}

	public String getGoodsPlace(){
		return this.goodsPlace;
	}
	public void setGoodsPlace(String goodsPlace){
		this.goodsPlace=goodsPlace;
	}

	public Timestamp getUpdateDt(){
		return this.updateDt;
	}
	public void setUpdateDt(Timestamp updateDt){
		this.updateDt=updateDt;
	}

}

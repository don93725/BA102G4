package com.place_time.model;

import java.sql.Date;

import com.gyms.model.GymsVO;
import com.place.model.PlaceVO;
import com.place_pic.model.Place_PicVO;
import com.place_report.model.PlaceReportVO;

public class Place_timeVO implements java.io.Serializable {

	private String pt_no;
	private String p_no;
	private String opc_acc;
	private Date rp_date;
	private Integer rp_time;
	private Date op_date;
	private String pbu_price;
	private String pau_price;
	private Date pbu_date;
	private Date pau_date;
	private PlaceVO placeVO;
	private GymsVO gymsVO;
	private String rp_timeShow;
	private Integer report;
	private Integer eva;
	private String eva_ct;
	private PlaceReportVO placeReportVO;
	private Place_PicVO place_picVO;
	
	
	public Place_timeVO(String pt_no, String p_no, String opc_acc, Date rp_date, Integer rp_time, Date op_date,
			String pbu_price, String pau_price, Date pbu_date, Date pau_date, PlaceVO placeVO) {
		this.pt_no = pt_no;
		this.p_no = p_no;
		this.opc_acc = opc_acc;
		this.rp_date = rp_date;
		this.rp_time = rp_time;
		this.op_date = op_date;
		this.pbu_price = pbu_price;
		this.pau_price = pau_price;
		this.placeVO = placeVO;
	}

	public Place_timeVO() {

	}

	public String getPt_no() {
		return pt_no;
	}

	public void setPt_no(String pt_no) {
		this.pt_no = pt_no;
	}

	public String getP_no() {
		return p_no;
	}

	public void setP_no(String p_no) {
		this.p_no = p_no;
	}

	public String getOpc_acc() {
		return opc_acc;
	}

	public void setOpc_acc(String opc_acc) {
		this.opc_acc = opc_acc;
	}

	public Date getRp_date() {
		return rp_date;
	}

	public void setRp_date(Date rp_date) {
		this.rp_date = rp_date;
	}

	public Integer getRp_time() {
		return rp_time;
	}

	public void setRp_time(Integer rp_time) {
		this.rp_time = rp_time;
	}

	public Date getOp_date() {
		return op_date;
	}

	public void setOp_date(Date op_date) {
		this.op_date = op_date;
	}

	public String getPbu_price() {
		return pbu_price;
	}

	public void setPbu_price(String pbu_price) {
		this.pbu_price = pbu_price;
	}

	public String getPau_price() {
		return pau_price;
	}

	public void setPau_price(String pau_price) {
		this.pau_price = pau_price;
	}

	public Date getPbu_date() {
		return pbu_date;
	}

	public void setPbu_date(Date pbu_date) {
		this.pbu_date = pbu_date;
	}

	public Date getPau_date() {
		return pau_date;
	}

	public void setPau_date(Date pau_date) {
		this.pau_date = pau_date;
	}

	public PlaceVO getPlaceVO() {
		return placeVO;
	}

	public void setPlaceVO(PlaceVO placeVO) {
		this.placeVO = placeVO;
	}

	public GymsVO getGymsVO() {
		return gymsVO;
	}

	public void setGymsVO(GymsVO gymsVO) {
		this.gymsVO = gymsVO;
	}

	public String getRp_timeShow() {
		return rp_timeShow;
	}

	public void setRp_timeShow(String rp_timeShow) {
		this.rp_timeShow = rp_timeShow;
	}

	public Integer getReport() {
		return report;
	}

	public void setReport(Integer report) {
		this.report = report;
	}

	public Integer getEva() {
		return eva;
	}

	public void setEva(Integer eva) {
		this.eva = eva;
	}

	public String getEva_ct() {
		return eva_ct;
	}

	public void setEva_ct(String eva_ct) {
		this.eva_ct = eva_ct;
	}

	public PlaceReportVO getPlaceReportVO() {
		return placeReportVO;
	}

	public void setPlaceReportVO(PlaceReportVO placeReportVO) {
		this.placeReportVO = placeReportVO;
	}

	public Place_PicVO getPlace_picVO() {
		return place_picVO;
	}

	public void setPlace_picVO(Place_PicVO place_picVO) {
		this.place_picVO = place_picVO;
	}
	

}

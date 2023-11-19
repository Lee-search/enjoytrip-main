package com.ssafy.vue.map.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "AttractionDto (관광지 정보)", description = "관광지의 컨텐츠 아이디, 컨텐츠 유형 아이디, 관광지 이름, 주소1, 주소2, 주소코드, 전화번호, "
		+ "이미지1, 이미지2, 조회수, 시도코드, 구군코드, 위도, 경도, 계층, 상세설명")
public class AttractionDto {
	
	@ApiModelProperty(value = "컨텐츠 아이디", example = "0")
	private int contentId; 
	@ApiModelProperty(value = "컨텐츠 유형 아이디", example = "12")
	private int contentTypeId; 
	@ApiModelProperty(value = "관광지 이름")
	private String title; 
	@ApiModelProperty(value = "주소1")
	private String addr1; 
	@ApiModelProperty(value = "주소2")
	private String addr2; 
	@ApiModelProperty(value = "주소코드")
	private String zipcode;
	@ApiModelProperty(value = "전화번호")
	private String tel;  
	@ApiModelProperty(value = "이미지1")
	private String firstImage; 
	@ApiModelProperty(value = "이미지2")
	private String firstImage2; 
	@ApiModelProperty(value = "조회수", example = "0")
	private int readCount; 
	@ApiModelProperty(value = "시도코드", example = "32")
	private int sidoCode;
	@ApiModelProperty(value = "구군코드", example = "18")
	private int gugunCode; 
	@ApiModelProperty(value = "위도")
	private double latitude; 
	@ApiModelProperty(value = "경도" , example = "0")
	private double longitude; 
	@ApiModelProperty(value = "계층", example = "0")
	private String mlevel;
	@ApiModelProperty(value = "상세설명", example = "관광지 부가 설명")
	private String overview;
	
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFirstImage() {
		return firstImage;
	}
	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}
	public String getFirstImage2() {
		return firstImage2;
	}
	public void setFirstImage2(String firstImage2) {
		this.firstImage2 = firstImage2;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}
	public int getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getMlevel() {
		return mlevel;
	}
	public void setMlevel(String mlevel) {
		this.mlevel = mlevel;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	@Override
	public String toString() {
		return "AttractionDto [contentId=" + contentId + ", contentTypeId=" + contentTypeId + ", title=" + title
				+ ", addr1=" + addr1 + ", addr2=" + addr2 + ", zipcode=" + zipcode + ", tel=" + tel + ", firstImage="
				+ firstImage + ", firstImage2=" + firstImage2 + ", readCount=" + readCount + ", sidoCode=" + sidoCode
				+ ", gugunCode=" + gugunCode + ", latitude=" + latitude + ", longitude=" + longitude + ", mlevel="
				+ mlevel + ", overview=" + overview + "]";
	}
}

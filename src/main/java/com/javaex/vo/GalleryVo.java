package com.javaex.vo;

public class GalleryVo {

	// 필드
	private int no;
	private String userNo;
	private String name;
	private String content;
	private String filePath;
	private String orgName;
	private String saveName;
	private String fileSize;

	// 생성자
	public GalleryVo() {

	}

	public GalleryVo(int no, String userNo, String name, String content, String filePath, String orgName,
			String saveName, String fileSize) {
		super();
		this.no = no;
		this.userNo = userNo;
		this.name = name;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}

	// 메소드 gs
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return userNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", userNo=" + userNo + ", name=" + name + ", content=" + content + ", filePath="
				+ filePath + ", orgName=" + orgName + ", saveName=" + saveName + ", fileSize=" + fileSize + "]";
	}

}

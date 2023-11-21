package tukorea.web.club.domain;

import java.sql.Date;

public class voteVO {
	private int voteNum;
	private String voteTitle;
	private String voteContent;
	private String memberID;
	private int good;
	private int bad;
	private Date voteDate;
	
	public int getVoteNum() {
		return voteNum;
	}
	public void setVoteNum(int voteNum) {
		this.voteNum = voteNum;
	}
	
	public String getVoteTitle() {
		return voteTitle;
	}
	public void setVoteTitle(String voteTitle) {
		this.voteTitle = voteTitle;
	}
	
	public String getVoteContent() {
		return voteContent;
	}
	public void setVoteContent(String voteContent) {
		this.voteContent = voteContent;
	}
	
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	
	public Date getVoteDate() {
		return voteDate;
	}
	public void setVoteDate(Date voteDate) {
		this.voteDate = voteDate;
	}
}

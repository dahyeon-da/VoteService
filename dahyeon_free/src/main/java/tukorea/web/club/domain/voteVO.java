package tukorea.web.club.domain;

public class voteVO {
	private int voteNum;
	private String voteTitle;
	private String voteContent;
	private String memberID;
	private int good;
	private int bad;
	
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
	
	public int getGoodCount() {
		return good;
	}
	public void setGoodCount(int good) {
		this.good = good;
	}
	
	public int getBadCount() {
		return bad;
	}
	public void setBadCount(int bad) {
		this.bad = bad;
	}
}

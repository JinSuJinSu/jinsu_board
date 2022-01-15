package vo;

public class ReplyVO {
	private int replyNo;
	private int boardNo;
	private String replyer;
	private String replyContent;
	private String replyDate;
	


	public ReplyVO() {
		
	}
	
	
	public ReplyVO(int replyNo, int boardNo, String replyer, String replyContent, String replyDate) {
		this.replyNo = replyNo;
		this.boardNo = boardNo;
		this.replyer = replyer;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
	
	
	
	

}

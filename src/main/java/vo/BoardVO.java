package vo;

import java.util.Date;

public class BoardVO {
	private int boardNO;
	private String userID;
	private String title;
	private String content;
	private int readCount;
	private int replyCount;
	private String writeDate;
	private String fileurl;
	
	
	public BoardVO() {
	}
	
	public BoardVO(int boardNo, String userID, String title,
			int readCount, int replyCount, String writeDate) {
		this.boardNO = boardNo;
		this.userID = userID;
		this.title = title;
		this.readCount = readCount;
		this.replyCount = replyCount;
		this.writeDate = writeDate;
		
	}
	
	public BoardVO(int boardNo, String userID, String title,
		int readCount, int replyCount, String writeDate, String fileurl) {
		this.boardNO = boardNo;
		this.userID = userID;
		this.title = title;
		this.readCount = readCount;
		this.replyCount = replyCount;
		this.writeDate = writeDate;
		this.fileurl = fileurl;	
	}
	
	
	public int getBoardNO() {
		return boardNO;
	}
	public void setBoardNO(int boardNO) {
		this.boardNO = boardNO;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public  String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	
	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	
	

}

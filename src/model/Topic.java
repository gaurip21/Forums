package model;

public class Topic {

	

	private int topicid;
	private String topicname;
	private String author;
	private int replies;
	private String latepost;
	private int forumid;
	
	public int getForumid() {
		return forumid;
	}

	public void setForumid(int forumid) {
		this.forumid = forumid;
	}
	
	public int getTopicid() {
		
		return topicid;
		
	}
	
	public void setTopicid(int topicid) {
		
		this.topicid = topicid;
		
	}
	
	public String getTopicname() {
		
		return topicname;
		
	}
	
	public void setTopicname(String topicname) {
		
		this.topicname = topicname;
		
	}
	
	public String getAuthor() {
		
		return author;
		
	}
	
	public void setAuthor(String author) {
		
		this.author = author;
		
	}
	
	public int getReplies() {
		
		return replies;
		
	}
	
	public void setReplies(int replies) {
		
		this.replies = replies;
		
	}
	
	public String getLatepost() {
		
		return latepost;
		
	}
	
	public void setLatepost(String latepost) {
		
		this.latepost = latepost;
		
	}
	
}

package model;

public class Post {
	
	private int postid;
	private String authorname;
	private String content;
	private String postedon;
	private int topicid;
	
	public int getTopicid() {
		return topicid;
	}

	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}

	public int getPostid() {
		
		return postid;
		
	}
	
	public void setPostid(int postid) {
		
		this.postid = postid;
		
	}
	
	public String getAuthorname() {
		
		return authorname;
		
	}
	
	public void setAuthorname(String authorname) {
		
		this.authorname = authorname;
		
	}
	
	public String getContent() {
		
		return content;
		
	}
	
	public void setContent(String content) {
		
		this.content = content;
		
	}
	
	public String getPostedon() {
		
		return postedon;
		
	}
	
	public void setPostedon(String postedon) {
		
		this.postedon = postedon;
		
	}
	
}

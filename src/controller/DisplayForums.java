package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Forum;
import model.Post;
import model.Topic;




@WebServlet("/DisplayForums")

public class DisplayForums extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public DisplayForums() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		
		super.init( config );
		
//		List<Forum> forums = new ArrayList<Forum>();
//		Forum forum;
//		List<Topic> topics = new ArrayList<Topic>();
//		Topic topic;
//		List<Post> posts = new ArrayList<Post>();
//		Post post;
//		
//		forum = new Forum();
//		forum.setForumname("General Discussion");
//		forum.setForumid(1);
//		forum.setForumtopics(0);
//        forums.add(forum);
//        
//        forum = new Forum();
//		forum.setForumname("CS3220 Web Programming");
//		forum.setForumid(2);
//		forum.setForumtopics(2);
//        forums.add(forum);
//        
//        topic = new Topic();
//        topic.setTopicname("Eclipse Problem");
//		topic.setTopicid(1);
//		topic.setForumid(2);
//		topic.setAuthor("John");
//		topic.setReplies(1);
//		topic.setLatepost("2/20/2020 2:17PM");
//		topics.add(topic);
//        
//		topic = new Topic();
//		topic.setTopicname("HW1 HELP!");
//		topic.setTopicid(2);
//		topic.setForumid(2);
//		topic.setAuthor("Jane");
//		topic.setReplies(0);
//		topic.setLatepost("2/25/2020 9:01AM");
//		topics.add(topic);
//		
//		post = new Post();
//		post.setPostid(1);
//		post.setAuthorname("John");
//		post.setContent("I couldn't find Dynamic Web Project when I tried to create a new project in Eclipse.");
//		post.setPostedon("2/19/2020 8:34PM");
//		post.setTopicid(1);
//		posts.add(post);
//		
////		post = new Post();
////		post.setPostid(2);
////		post.setAuthorname("Jane");
////		post.setContent("You probably got the wrong Eclipse package.\n" + 
////				"\n" + 
////				"You need Eclipse for Enterprise Java Developers, not Java Developer.");
////		post.setPostedon("2/20/2020 2:17PM");
////		post.setTopicid(1);
////		posts.add(post);
//        
//        getServletContext().setAttribute("forums", forums);
//        getServletContext().setAttribute("topics", topics);
//        getServletContext().setAttribute("posts", posts);
		
		
		try
        {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
		

		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Forum> forums = new ArrayList<Forum>();
		Forum forum;
//		List<Topic> topics = new ArrayList<Topic>();
//		Topic topic;
//		List<Post> posts = new ArrayList<Post>();
//		Post post;
		
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu57";
            String username = "cs3220stu57";
            String password = "!VEo*E6d";
            
            int topicid, count, forumid, cnt;

            c = DriverManager.getConnection( url, username, password );
            
            Statement stmt = c.createStatement();
            
            ResultSet rs3 = stmt.executeQuery("SELECT topics.topicid as topicid, Count(posts.topicid) as cnt FROM posts LEFT JOIN topics ON posts.topicid=topics.topicid GROUP BY posts.topicid ORDER BY cnt DESC");
            
            
            while( rs3.next() )
            {
            	topicid = rs3.getInt("topicid");
            	count = rs3.getInt("cnt");
            	
            	String sql = "update topics set replies = "+count+" where topicid = "+topicid+"";
            	
            	PreparedStatement pstmt = c.prepareStatement( sql );
                
                pstmt.executeUpdate();

            }
            
            ResultSet rs4 = stmt.executeQuery("SELECT forums.forumid as forumid, Count(topics.forumid) as cnt FROM topics LEFT JOIN forums ON topics.forumid=forums.forumid GROUP BY topics.forumid ORDER BY cnt DESC");
            
            
            while( rs4.next() )
            {
            	forumid = rs4.getInt("forumid");
            	cnt = rs4.getInt("cnt");
            	
            	String sql1 = "update forums set forumtopics = "+cnt+" where forumid = "+forumid+"";
            	
            	PreparedStatement pstmt = c.prepareStatement( sql1 );
                
                pstmt.executeUpdate();

            }
            
            ResultSet rs = stmt.executeQuery( "select * from forums" );

            while( rs.next() )
            {
        		forum = new Forum();
        		forum.setForumname(rs.getString( "forumname" ));
        		forum.setForumid(rs.getInt("forumid"));
        		forum.setForumtopics(rs.getInt("forumtopics"));
                forums.add(forum);
            }

//            ResultSet rs1 = stmt.executeQuery( "select * from topics" );
//
//            while( rs1.next() )
//            {
//            	topic = new Topic();
//        		topic.setTopicname(rs1.getString( "topicname" ));
//        		topic.setTopicid(rs1.getInt( "topicid" ));
//        		topic.setForumid(rs1.getInt( "forumid" ));
//        		topic.setAuthor(rs1.getString( "author" ));
//        		topic.setReplies(rs1.getInt( "replies" ));
//        		topic.setLatepost(rs1.getTimestamp("latepost").toString());
//        		topics.add(topic);
//            }
//            
//            ResultSet rs2 = stmt.executeQuery( "select * from posts" );
//
//            while( rs2.next() )
//            {
//            	post = new Post();
//        		post.setPostid(rs2.getInt("postid"));
//        		post.setAuthorname(rs2.getString("authorname"));
//        		post.setContent(rs2.getString("content"));
//        		post.setPostedon(rs2.getTimestamp("postedon").toString());
//        		post.setTopicid(rs2.getInt( "topicid" ));
//        		posts.add(post);
//            }
//            
            
            getServletContext().setAttribute("forums", forums);
//            getServletContext().setAttribute("topics", topics);
//            getServletContext().setAttribute("posts", posts);
  	
            
            c.close();
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        finally
        {
            try
            {
                if( c != null ) c.close();
            }
            catch( SQLException e )
            {
                throw new ServletException( e );
            }
        }

		
        request.getRequestDispatcher("Forum.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Forum;
import model.Post;
import model.Topic;

/**
 * Servlet implementation class DisplayPosts
 */
@WebServlet("/DisplayPosts")
public class DisplayPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayPosts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int topic_id = Integer.parseInt(request.getParameter("topic_id"));
		
//		List<Post> posts = (List<Post>) getServletContext().getAttribute("posts");
//		List<Topic> topics = (List<Topic>) getServletContext().getAttribute("topics");
//		Post post;
//		Topic topic;
//		
//		List<Post> currentTopicPosts = new ArrayList<Post>();
//		
//		Enumeration<Post> enu = Collections.enumeration(posts);
//		
//		while(enu.hasMoreElements()){
//			
//			post = (Post) enu.nextElement();
//			
//			if (post.getTopicid() == topic_id) {
//				
//				currentTopicPosts.add(post);
//				
//			}
//			
//		}
//		
//		Enumeration<Topic> enumm = Collections.enumeration(topics);
//		
//		while(enumm.hasMoreElements()){
//			
//			topic = (Topic) enumm.nextElement();
//			
//			if (topic.getTopicid() == topic_id) {
//				
//				getServletContext().setAttribute("topic_name", topic.getTopicname());
//				
//			}
//			
//		}
//		
		getServletContext().setAttribute("topic_id", topic_id);
//		getServletContext().setAttribute("currentTopicPosts", currentTopicPosts);
//		
		
		List<Post> posts = new ArrayList<Post>();
		Post post;
		
		String topic_name = "";
		
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu57";
            String username = "cs3220stu57";
            String password = "!VEo*E6d";
            
            c = DriverManager.getConnection( url, username, password );
            
            Statement stmt = c.createStatement();
            
            ResultSet rs2 = stmt.executeQuery( "select posts.*,topics.topicname from posts LEFT JOIN topics ON posts.topicid = topics.topicid where posts.topicid = "+topic_id+"" );
            
            while( rs2.next() )
             {
                 post = new Post();
                 post.setPostid(rs2.getInt("postid"));
                 post.setAuthorname(rs2.getString("authorname"));
                 post.setContent(rs2.getString("content"));
                 post.setPostedon(rs2.getTimestamp("postedon").toString());
                 post.setTopicid(rs2.getInt( "topicid" ));
                 topic_name = rs2.getString("topicname");
                 posts.add(post);
              }
            
            getServletContext().setAttribute("posts", posts);
            getServletContext().setAttribute("topic_name", topic_name);
                      	
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

        
		
		request.getRequestDispatcher("Post.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Forum;
import model.Topic;

@WebServlet("/DisplayTopics")

public class DisplayTopics extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public DisplayTopics() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		
		super.init( config );
		
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int forum_id = Integer.parseInt(request.getParameter("forum_id"));
		
		
		
//		List<Topic> topics = (List<Topic>) getServletContext().getAttribute("topics");
//		List<Forum> forums = (List<Forum>) getServletContext().getAttribute("forums");
//		Topic topic;
//		Forum forum;
//		
//		
//		
//		List<Topic> currentForumTopics = new ArrayList<Topic>();
//		
//		Enumeration<Topic> enu = Collections.enumeration(topics);
//		
//		while(enu.hasMoreElements()){
//			
//			topic = (Topic) enu.nextElement();
//			
//			if (topic.getForumid() == forum_id) {
//				
//				currentForumTopics.add(topic);
//				
//			}
//			
//		}
//		
//		Enumeration<Forum> enumm = Collections.enumeration(forums);
//		
//		while(enumm.hasMoreElements()){
//			
//			forum = (Forum) enumm.nextElement();
//			
//			if (forum.getForumid() == forum_id) {
//				
//				getServletContext().setAttribute("forum_name", forum.getForumname());
//				
//			}
//			
//		}
//		
//		getServletContext().setAttribute("currentForumTopics", currentForumTopics);
		getServletContext().setAttribute("forum_id", forum_id);
		
		
		List<Topic> topics = new ArrayList<Topic>();
		Topic topic;
		String forum_name = "";
		
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu57";
            String username = "cs3220stu57";
            String password = "!VEo*E6d";
            
            c = DriverManager.getConnection( url, username, password );
            
            Statement stmt = c.createStatement();
            
          ResultSet rs1 = stmt.executeQuery( "select topics.*, forums.forumname from topics LEFT JOIN forums ON topics.forumid = forums.forumid where topics.forumid = "+forum_id+"" );

          while( rs1.next() )
          {
          	topic = new Topic();
      		topic.setTopicname(rs1.getString( "topicname" ));
      		topic.setTopicid(rs1.getInt( "topicid" ));
      		topic.setForumid(rs1.getInt( "forumid" ));
      		topic.setAuthor(rs1.getString( "author" ));
      		topic.setReplies(rs1.getInt( "replies" ));
      		forum_name = rs1.getString("forumname");
      		topic.setLatepost(rs1.getTimestamp("latepost").toString());
      		topics.add(topic);
          }
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
        
        getServletContext().setAttribute("topics", topics);
        getServletContext().setAttribute("forum_name", forum_name);
		
		request.getRequestDispatcher("Topic.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}

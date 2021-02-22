package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Topic;

@WebServlet("/CreateTopic")

public class CreateTopic extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public CreateTopic() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Topic> topics = (List<Topic>) getServletContext().getAttribute("topics");
		
		Topic topic = new Topic();
		
		int forum_id = Integer.parseInt(request.getParameter("forum_id"));
		String topic_name = request.getParameter("topic_name");
		String topic_subject = request.getParameter("topic_subject");
		String topic_content = request.getParameter("topic_content");
		
		Connection c = null;
		
		
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu57";
            String username = "cs3220stu57";
            String password = "!VEo*E6d";
            
            String sql = "insert into topics (topicname,author,replies,forumid) values ('"+topic_subject+"','"+topic_name+"',0,'"+forum_id+"')";

            c = DriverManager.getConnection( url, username, password );
            
            PreparedStatement pstmt = c.prepareStatement( sql );
            
            Statement stmt = c.createStatement();
            
            pstmt.executeUpdate();
            
            String sql1 = "update forums set forumtopics = forumtopics + 1 where forumid="+forum_id+"";
            
            pstmt = c.prepareStatement( sql1 );
            
            stmt = c.createStatement();
            
            pstmt.executeUpdate();
            
            topics.clear();
            
            ResultSet rs1 = stmt.executeQuery( "select * from topics where forumid="+forum_id+"" );

            while( rs1.next() )
            {
            	topic = new Topic();
        		topic.setTopicname(rs1.getString( "topicname" ));
        		topic.setTopicid(rs1.getInt( "topicid" ));
        		topic.setForumid(rs1.getInt( "forumid" ));
        		topic.setAuthor(rs1.getString( "author" ));
        		topic.setReplies(rs1.getInt( "replies" ));
        		topic.setLatepost(rs1.getTimestamp("latepost").toString());
        		topics.add(topic);
            }

    		
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

		getServletContext().setAttribute("topics", topics);
        
		request.getRequestDispatcher("Topic.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
		
	}

}

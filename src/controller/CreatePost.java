package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Post;
import model.Topic;

/**
 * Servlet implementation class CreatePost
 */
@WebServlet("/CreatePost")
public class CreatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Post> posts = (List<Post>) getServletContext().getAttribute("posts");
		Post post;
		
		
		int topic_id = Integer.parseInt(request.getParameter("topic_id"));
		
		String post_name = request.getParameter("post_name");
		String post_reply = request.getParameter("post_reply");
		
		Connection c = null;
		
		
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu57";
            String username = "cs3220stu57";
            String password = "!VEo*E6d";
            
            c = DriverManager.getConnection( url, username, password );
            
            Statement stmt = c.createStatement();
            
            String sql = "insert into posts (authorname,content,topicid) values ('"+post_name+"','"+post_reply+"','"+topic_id+"')";

            PreparedStatement pstmt = c.prepareStatement( sql );
            
            pstmt.executeUpdate();
            
            String sql1 = "update topics set replies = replies + 1 where topicid="+topic_id+"";
            
            pstmt = c.prepareStatement( sql1 );
            
            stmt = c.createStatement();
            
            pstmt.executeUpdate();
            
            posts.clear();
            
            ResultSet rs2 = stmt.executeQuery( "select * from posts where topicid = "+topic_id+"" );

            while( rs2.next() )
            {
            	post = new Post();
        		post.setPostid(rs2.getInt("postid"));
        		post.setAuthorname(rs2.getString("authorname"));
        		post.setContent(rs2.getString("content"));
        		post.setPostedon(rs2.getTimestamp("postedon").toString());
        		post.setTopicid(rs2.getInt( "topicid" ));
        		posts.add(post);
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
		
//		post = new Post();
//		post.setAuthorname(post_name);
//		post.setContent(post_reply);
//		post.setTopicid(topic_id);
//		post.setPostedon("" + date);
//		posts.add(post);
//		
//		getServletContext().setAttribute("posts", posts);
//		
//		posts = (List<Post>) getServletContext().getAttribute("currentTopicPosts");
//		
//		posts.add(post);
		
        getServletContext().setAttribute("posts", posts);
		
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

package tn.connectapp.services.club;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.connectapp.entities.club.Post;
import tn.connectapp.utils.commun.InputControl;
import tn.connectapp.utils.commun.MyConnection;

public class PostService {

    private Connection cnx;

    public PostService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void createPost(Post p) throws SQLException {

            String req = "INSERT INTO Post (post_name,	creation_user,	creation_date,	status,description) "
                    + "values ('" + p.getPostName() + "'," + p.getCreationUser()
                    + ",'" + p.getCreationDate() + "','" + p.getStatus() + "','" + p.getDescription() + "');";

            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);

            System.out.println("Values Inserted");


    }

    public void updatePost(Post updateEntity) throws SQLException {


            String req = "UPDATE Post SET post_name = ? ,creation_user = ? , "
                    + "creation_date = ? , status = ? , description = ? "
                    + " where post_id = ? ;";

            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setString(1, updateEntity.getPostName());
            pre.setLong(2, updateEntity.getCreationUser());
            pre.setDate(3, updateEntity.getCreationDate());
            pre.setString(4, updateEntity.getStatus());
            pre.setString(5, updateEntity.getDescription());
            pre.setLong(6, updateEntity.getPostId());

            pre.executeUpdate();

            System.out.println("Values Updated");


    }

    public boolean deletePost(Long idPost) throws SQLException {

            String req = "UPDATE Post SET status = 'HEXP' WHERE id_Post = ? ;";

            PreparedStatement pre = cnx.prepareStatement(req);

            pre.setLong(1, idPost);

            if (pre.executeUpdate() == 1) {
                return true;
            }


        return false;
    }

    public List<Post> ReadListPost(String status) throws SQLException {
        List<Post> posts = new ArrayList<>();

            String sql = "SELECT p.*,(select concat(u.FirstName,' ',u.LastName)"
                    + " From user u where p.creation_user = u.id_user) user_name FROM Post p";
            if (!InputControl.isNull(status)) {
                sql = sql + " WHERE status = '" + status +"'" ;
            }
            sql += ";";
            System.out.println(sql);
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {

                Post post = new Post();
                post.setPostId(rs.getLong("post_id"));
                post.setPostName(rs.getString("post_name"));
                post.setStatus(rs.getString("status"));
                post.setCreationDate(rs.getDate("creation_date"));
                post.setCreationUser(rs.getLong("creation_user"));
                post.setDescription(rs.getString("description"));
                post.setCreationUserName(rs.getString("user_name"));

                posts.add(post);

            }


        return posts;
    }

    public Post ReadPost(Long idPost,String postname) throws SQLException {
        Post post = new Post();
            String sql = "SELECT p.*,(select concat(u.FirstName,' ',u.LastName)"
                    + " From user u where p.creation_user = u.id_user) user_name "
                    + "FROM Post p WHERE post_id = " + idPost + " or post_name = '"
                    + postname + "';";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            if (rs.first()) {
                post.setCreationUserName(rs.getString("user_name"));
                post.setPostId(rs.getLong("post_id"));
                post.setPostName(rs.getString("post_name"));
                post.setStatus(rs.getString("status"));
                post.setCreationDate(rs.getDate("creation_date"));
                post.setCreationUser(rs.getLong("creation_user"));
                post.setDescription(rs.getString("description"));

            }

        return post;
    }
}

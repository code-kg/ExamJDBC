import java.sql.*;
import java.time.LocalDate;

public class NewsContent {
    private Dbworker dbworker = new Dbworker();
    public void insertNews (String title, String content, int time){
//        private final Dbworker dbworker = new Dbworker();
        String SQL = "insert into users (title, news_content, publication_time) values(?,?,?)";
        try (Connection connection = dbworker.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.execute();
        } catch (SQLException e){
            System.out.println("NO news");
        }
    }

    public void showNewsInfo(){
        String SQL = "select n.title, n.news_content from news n where news.id = n.id";
        try (
                Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                System.out.println(rs.getString(2) + ": "
                        + rs.getString(3));
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteNews(int newsId){
        String SQL = "delete from news where news_id =?";
        try(Connection connection = dbworker.connect();
            PreparedStatement preparedStatement=connection.prepareStatement(SQL)){
            preparedStatement.setInt(1,newsId);
            preparedStatement.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public boolean updateNews(String title, String content){
        String SQL = "update * from news where title = ? and news_content = ?";
        try (Connection connection = dbworker.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)){
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) return true;
            return false;
        } catch (SQLException e){
            return false;
        }
    }
}

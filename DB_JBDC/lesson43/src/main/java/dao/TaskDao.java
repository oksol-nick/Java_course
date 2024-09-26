package dao;

import entity.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class TaskDao {
  private final DataSource dataSource;

  public TaskDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Task save(Task task) {

    String sql = "INSERT INTO task (title, finished, created_date) VALUES (?, ?, ?)";
    try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
    {
      statement.setString(1, task.getTitle());
      statement.setBoolean(2, task.getFinished());
      statement.setTimestamp(3, java.sql.Timestamp.valueOf(task.getCreatedDate()));
      statement.executeUpdate();

      try (ResultSet resultSet = statement.getGeneratedKeys()) {
        if(resultSet.next()) {
          task.setId(resultSet.getInt(1));
        }
      }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return task;
        }

  public List<Task> findAll() {
    List<Task> tasks = new ArrayList<>();
    try (Connection connection = dataSource.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT task_id, title, finished, created_date FROM task ORDER BY task_id");) {
      while (resultSet.next()) {
        final Task task = new Task(resultSet.getString(2), resultSet.getBoolean(3),
                resultSet.getTimestamp(4).toLocalDateTime());
        task.setId(resultSet.getInt(1));
        tasks.add(task);
      }
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return tasks;
  }

  public int deleteAll() {
    int rowsNumber = 0;
    try (Connection connection = dataSource.getConnection();
         Statement statement = connection.createStatement();)
    {
        rowsNumber = findAll().size();
        if(rowsNumber > 0) {
          statement.execute("TRUNCATE task");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return rowsNumber;
    }

  public Task getById(Integer id) {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM task WHERE task_id = ?");) {
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if(resultSet.next()) {

        final Task task = new Task(resultSet.getString(2), resultSet.getBoolean(3),
                resultSet.getTimestamp(4).toLocalDateTime());
        task.setId(resultSet.getInt(1));

        return task;
      } else {
        return null;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Task> findAllNotFinished() {
    List<Task> notFinishedTasks = new ArrayList<>();
    try (Connection connection = dataSource.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery( "SELECT * FROM task WHERE finished = false");) {
      while (resultSet.next()) {
        final Task task = new Task(resultSet.getString(2), resultSet.getBoolean(3),
                resultSet.getTimestamp(4).toLocalDateTime());
        task.setId(resultSet.getInt(1));
        notFinishedTasks.add(task);
      }
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return notFinishedTasks;
  }

  public List<Task> findNewestTasks(Integer numberOfNewestTasks) {
    List<Task> tasks = new ArrayList<>();
    try (Connection connection = dataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM task ORDER BY created_date DESC LIMIT ?");) {
      statement.setInt(1, numberOfNewestTasks);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        final Task task = new Task(resultSet.getString(2), resultSet.getBoolean(3),
                resultSet.getTimestamp(4).toLocalDateTime());
        task.setId(resultSet.getInt(1));
        tasks.add(task);
      }
      return tasks;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Task finishTask(Task task) {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement("UPDATE task SET finished = true WHERE task_id = ?");) {
      statement.setInt(1, task.getId());
      statement.executeUpdate();
      task.setFinished(true);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return task;
  }

  public void deleteById(Integer id) {
    try (Connection connection = dataSource.getConnection();
         PreparedStatement statement = connection.prepareStatement("DELETE FROM task WHERE task_id = ?");) {
      statement.setInt(1, id);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}

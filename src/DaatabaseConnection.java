import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaatabaseConnection implements AutoCloseable {


    private Connection conn;
    private String url, password, user;

    private void setConnectionProperties() throws IOException {
        var reader = new BufferedReader(new FileReader("credentials.properties"));
        Properties props = new Properties();
        props.load(reader);
        user = props.getProperty("user");
        password = props.getProperty("password");
        url = props.getProperty("url");
    }

    private Connection establishConnection() throws IOException, SQLException {
        setConnectionProperties();
        return DriverManager.getConnection(this.url, this.user, this.password);
    }

    public DaatabaseConnection() throws IOException, SQLException {
        this.conn = establishConnection();
    }


    @Override
    public void close() throws Exception {
        conn.close();

    }

    public Connection getConn() {
        return this.conn;
    }

}


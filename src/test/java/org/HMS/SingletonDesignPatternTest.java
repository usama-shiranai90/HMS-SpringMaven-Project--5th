package org.HMS;

import DataConnection.DatabaseConnection;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class SingletonDesignPatternTest {


    @Test
    public void isCheckConnectionEstablished() throws SQLException {

        Assert.assertEquals(DatabaseConnection.getInstance(), DatabaseConnection.getInstance());
        Assert.assertSame(DatabaseConnection.getInstance(), DatabaseConnection.getInstance());

    }
}

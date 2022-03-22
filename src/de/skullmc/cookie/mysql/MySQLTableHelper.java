package de.skullmc.cookie.mysql;

import de.skullmc.cookie.Cookie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MySQLTableHelper {

    private final Cookie plugin;

    public MySQLTableHelper(Cookie plugin) {
        this.plugin = plugin;
    }

    public void addUpgrade(String uuid, String upgradeName) {
        final String sql = "UPDATE upgrades SET " + upgradeName + "= ? WHERE UUID= ?";
        int nextUpgradeAmount = getUpgrade(uuid, upgradeName) + 1;
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
            preparedStatement.setInt(1, nextUpgradeAmount);
            preparedStatement.setString(2, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void resetUpgrade(String uuid, String upgradeName) {
        final String sql = "UPDATE upgrades SET " + upgradeName + "=0 WHERE UUID= ?";
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
            preparedStatement.setString(1, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public int getUpgrade(String uuid, String upgradeName) {
        final String sql = "SELECT * FROM upgrades WHERE UUID= ?";
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
            preparedStatement.setString(1, uuid);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(upgradeName);
            }
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public void setCookies(String uuid, long amount) {
        final String sql = "UPDATE cookies SET COOKIES= ? WHERE UUID= ?";
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
            preparedStatement.setLong(1, amount);
            preparedStatement.setString(2, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    public void setAchievments(String uuid, int newlyAchievment) {
        final String sql = "UPDATE achievments SET ACHIEVMENTS= ? WHERE UUID= ?";
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
            preparedStatement.setInt(1, newlyAchievment);
            preparedStatement.setString(2, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void setName(String uuid, String name) {
        final String sql = "UPDATE cookies SET NAME= ? WHERE UUID= ?";
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public int getAchievments(String uuid) {
        final String sql = "SELECT ACHIEVMENTS FROM achievments WHERE UUID= ?";
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
            preparedStatement.setString(1, uuid);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ACHIEVMENTS");
            }
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public Long getCookiesByName(String name) {
        final String sql = "SELECT COOKIES FROM cookies WHERE NAME= ?";
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
            preparedStatement.setString(1, name);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("COOKIES");
            }
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0L;
    }

    public Long getCookies(String uuid) {
        final String sql = "SELECT COOKIES FROM cookies WHERE UUID= ?";
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
            preparedStatement.setString(1, uuid);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("COOKIES");
            }
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0L;
    }

    public String getName(String uuid) {
        final String sql = "SELECT NAME FROM cookies WHERE UUID= ?";
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
            preparedStatement.setString(1, uuid);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("NAME");
            }
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public String getUniqueId(String name) {
        final String sql = "SELECT UUID FROM cookies WHERE NAME= ?";
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
            preparedStatement.setString(1, name);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("UUID");
            }
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public boolean playerExists(UUID uuid) {
        final String sql = "SELECT UUID FROM cookies WHERE UUID= ?";
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
            preparedStatement.setString(1, uuid.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("UUID") != null;
            }
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean playerExists(String name) {
        final String sql = "SELECT NAME FROM cookies WHERE NAME= ?";
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("NAME") != null;
            }
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public void createPlayer(String uuid, String name) {
        try {
            {
                final String sql = "INSERT INTO cookies(UUID, NAME, COOKIES) VALUES (?, ?, '0')";
                final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
                preparedStatement.setString(1, uuid);
                preparedStatement.setString(2, name);
                preparedStatement.executeUpdate();
            }
            {
                final String sql = "INSERT INTO achievments(UUID, ACHIEVMENTS) VALUES (?, '0')";
                final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
                preparedStatement.setString(1, uuid);
                preparedStatement.executeUpdate();
            }
            {
                final String sql = "INSERT INTO upgrades(UUID,HELPER,GRANDMA,FINGER,CHICKEN,MINIGUN,CODER,PLANTATION,TURBINE,HUSTLER,CHINA,NUCLEAR) VALUES (?, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0')";
                final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement(sql).join();
                preparedStatement.setString(1, uuid);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

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
        int nextUpgradeAmount = getUpgrade(uuid, upgradeName) + 1;
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("UPDATE upgrades SET " + upgradeName + "= ? WHERE UUID= ?");
            preparedStatement.setInt(1, nextUpgradeAmount);
            preparedStatement.setString(2, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void resetUpgrade(String uuid, String upgradeName) {
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("UPDATE upgrades SET " + upgradeName + "=0 WHERE UUID= ?");
            preparedStatement.setString(1, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public int getUpgrade(String uuid, String upgradeName) {
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("SELECT * FROM upgrades WHERE UUID= ?");
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
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("UPDATE cookies SET COOKIES= ? WHERE UUID= ?");
            preparedStatement.setLong(1, amount);
            preparedStatement.setString(2, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    public void setAchievments(String uuid, int newlyAchievment) {
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("UPDATE achievments SET ACHIEVMENTS= ? WHERE UUID= ?");
            preparedStatement.setInt(1, newlyAchievment);
            preparedStatement.setString(2, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void setName(String uuid, String name) {
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("UPDATE cookies SET NAME= ? WHERE UUID= ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public int getAchievments(String uuid) {
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("SELECT ACHIEVMENTS FROM achievments WHERE UUID= ?");
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
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("SELECT COOKIES FROM cookies WHERE NAME= ?");
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
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("SELECT COOKIES FROM cookies WHERE UUID= ?");
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
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("SELECT NAME FROM cookies WHERE UUID= ?");
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
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("SELECT UUID FROM cookies WHERE NAME= ?");
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
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("SELECT UUID FROM cookies WHERE UUID= ?");
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
        try {
            final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("SELECT NAME FROM cookies WHERE NAME= ?");
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
                final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("INSERT INTO cookies(UUID, NAME, COOKIES) VALUES (?, ?, '0')");
                preparedStatement.setString(1, uuid);
                preparedStatement.setString(2, name);
                preparedStatement.executeUpdate();
            }
            {
                final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("INSERT INTO achievments(UUID, ACHIEVMENTS) VALUES (?, '0')");
                preparedStatement.setString(1, uuid);
                preparedStatement.executeUpdate();
            }
            {
                final PreparedStatement preparedStatement = plugin.getMySqlConnection().getStatement("INSERT INTO upgrades(UUID,HELPER,GRANDMA,FINGER,CHICKEN,MINIGUN,CODER,PLANTATION,TURBINE,HUSTLER,CHINA,NUCLEAR) VALUES (?, '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0')");
                preparedStatement.setString(1, uuid);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

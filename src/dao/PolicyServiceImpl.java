package dao;


import entity.Policy;
import util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolicyServiceImpl implements IPolicyService {
    private Connection connection;

    public PolicyServiceImpl() {
        this.connection = DBConnUtil.getConnection("db.properties");
    }

    @Override
    public boolean createPolicy(Policy policy) {
        String query = "INSERT INTO Policy (policyId, policyName, coverageAmount, premium) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, policy.getPolicyId());
            stmt.setString(2, policy.getPolicyName());
            stmt.setDouble(3, policy.getCoverageAmount());
            stmt.setDouble(4, policy.getPremium());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Policy getPolicy(int policyId) {
        String query = "SELECT * FROM Policy WHERE policyId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, policyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Policy(
                    rs.getInt("policyId"),
                    rs.getString("policyName"),
                    rs.getDouble("coverageAmount"),
                    rs.getDouble("premium")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Policy> getAllPolicies() {
        String query = "SELECT * FROM Policy";
        List<Policy> policies = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                policies.add(new Policy(
                    rs.getInt("policyId"),
                    rs.getString("policyName"),
                    rs.getDouble("coverageAmount"),
                    rs.getDouble("premium")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        String query = "UPDATE Policy SET policyName = ?, coverageAmount = ?, premium = ? WHERE policyId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, policy.getPolicyName());
            stmt.setDouble(2, policy.getCoverageAmount());
            stmt.setDouble(3, policy.getPremium());
            stmt.setInt(4, policy.getPolicyId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePolicy(int policyId) {
        String query = "DELETE FROM Policy WHERE policyId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, policyId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

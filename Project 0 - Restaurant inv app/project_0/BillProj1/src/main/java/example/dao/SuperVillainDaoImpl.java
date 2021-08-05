package example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import example.model.SuperVillain;

public class SuperVillainDaoImpl implements SuperVillainDao {

	public void insertVillain(SuperVillain sVill) {
		// TODO Auto-generated method stub

	}

	public void insertVillainPower(SuperVillain sVill, String newPower) {
		// TODO Auto-generated method stub

	}

	public List<SuperVillain> selectAllVillains() {
		List<SuperVillain> vills = new ArrayList<>();

		try (Connection conn = CustomConnectionFactory.getConnection()) {

			String sql = "SELECT * FROM super_human_power_join";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			SuperVillain nextEntry;
			while (rs.next()) {
				if (vills.size() > 0 && vills.get(vills.size() - 1).getName().equals(rs.getString("shuman_name"))) {

					vills.get(vills.size() - 1).getSuperpower().add(rs.getString("spower_name"));
					continue;
				}

				ArrayList<String> powerHolder = new ArrayList<String>();
				powerHolder.add(rs.getString("spower_name"));

				vills.add(new SuperVillain(rs.getInt("shuman_id"), rs.getString("shuman_name"), powerHolder,
						rs.getInt("bounty")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vills;
	}

	public SuperVillain selectVillainById(SuperVillain sVill) {

		try (Connection conn = CustomConnectionFactory.getConnection()) {

			String sql = "SELECT * FROM super_human_power_join WHERE shuman_id=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sVill.getId());

			ResultSet rs = ps.executeQuery();

			SuperVillain returnVillain = null;
			while (rs.next()) {
				if (returnVillain != null && returnVillain.getName().equals(rs.getString("shuman_name"))) {

					returnVillain.getSuperpower().add(rs.getString("spower_name"));
					continue;
				} else if (returnVillain != null && !returnVillain.getName().equals(rs.getString("shuman_name"))) {
					break;
				} else {
					ArrayList<String> powerHolder = new ArrayList<String>();
					powerHolder.add(rs.getString("spower_name"));

					returnVillain = new SuperVillain(rs.getInt("shuman_id"), rs.getString("shuman_name"), powerHolder,
							rs.getInt("bounty"));
				}

			}
			return returnVillain;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public SuperVillain selectVillainByName(SuperVillain sVill) {
		try (Connection conn = CustomConnectionFactory.getConnection()) {

			String sql = "SELECT * FROM super_human_power_join WHERE shuman_name=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sVill.getName());

			ResultSet rs = ps.executeQuery();

			SuperVillain returnVillain = null;
			while (rs.next()) {
				if (returnVillain != null && returnVillain.getName().equals(rs.getString("shuman_name"))) {

					returnVillain.getSuperpower().add(rs.getString("spower_name"));
					continue;
				} else if (returnVillain != null && !returnVillain.getName().equals(rs.getString("shuman_name"))) {
					break;
				} else {
					ArrayList<String> powerHolder = new ArrayList<String>();
					powerHolder.add(rs.getString("spower_name"));

					returnVillain = new SuperVillain(rs.getInt("shuman_id"), rs.getString("shuman_name"), powerHolder,
							rs.getInt("bounty"));
				}

			}
			return returnVillain;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}

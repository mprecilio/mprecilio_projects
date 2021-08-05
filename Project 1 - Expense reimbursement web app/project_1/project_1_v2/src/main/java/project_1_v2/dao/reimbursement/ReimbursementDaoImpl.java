package project_1_v2.dao.reimbursement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import project_1_v2.dao.MyControllerFactory;
import project_1_v2.model.Reimbursement;
import project_1_v2.utilities.EventLogger;

public class ReimbursementDaoImpl implements ReimbursementDao {
	

	@Override
	public List<Reimbursement> getAllEmployeeReimbs(String user) {
		List<Reimbursement> allReimbs = new ArrayList<Reimbursement>();
		try (Connection conn = MyControllerFactory.getConnection()) {

			String sql = "SELECT * "
					+ "FROM reimb_view "
					+ "WHERE author = ? "
					+ "ORDER BY reimb_id DESC;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery(); // <--query not update
			while (rs.next()) {
				int reimbId = rs.getInt(1);
				double reimbAmount = rs.getDouble(2);
				String reimbTimeSubmitted = rs.getTimestamp(3).toString();
				Timestamp reimbTimeResolvedTS = rs.getTimestamp(4);
				String reimbTimeResolved = null;
				if(reimbTimeResolvedTS != null) reimbTimeResolved = reimbTimeResolvedTS.toString();
				String reimbDescription = rs.getString(5);
				String reimbAuthor = rs.getString(6);
				String reimbResolver = rs.getString(7);
				String reimbStatus = rs.getString(8);
				String reimbType = rs.getString(9);
				Reimbursement myReimb = new Reimbursement(reimbId,reimbAmount,reimbTimeSubmitted,reimbTimeResolved,reimbDescription,reimbAuthor,reimbResolver,reimbStatus,reimbType);
				allReimbs.add(myReimb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			EventLogger.loggy.error("A SQL exception was thrown in ReimbursementDaoImpl : getAllEmployeeReimbs: ", e);
		}

		return allReimbs;
	}
	
	@Override
	public List<Reimbursement> getAllReimbs() {
		List<Reimbursement> allReimbs = new ArrayList<Reimbursement>();
		try (Connection conn = MyControllerFactory.getConnection()) {

			String sql = "SELECT * "
					+ "FROM reimb_view "
					+ "ORDER BY reimb_id DESC";

			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery(); // <--query not update
			while (rs.next()) {
				int reimbId = rs.getInt(1);
				double reimbAmount = rs.getDouble(2);
				String reimbTimeSubmitted = rs.getTimestamp(3).toString();
				Timestamp reimbTimeResolvedTS = rs.getTimestamp(4);
				String reimbTimeResolved = null;
				if(reimbTimeResolvedTS != null) reimbTimeResolved = reimbTimeResolvedTS.toString();
				String reimbDescription = rs.getString(5);
				String reimbAuthor = rs.getString(6);
				String reimbResolver = rs.getString(7);
				String reimbStatus = rs.getString(8);
				String reimbType = rs.getString(9);
				Reimbursement myReimb = new Reimbursement(reimbId,reimbAmount,reimbTimeSubmitted,reimbTimeResolved,reimbDescription,reimbAuthor,reimbResolver,reimbStatus,reimbType);
				allReimbs.add(myReimb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			EventLogger.loggy.error("A SQL exception was thrown in ReimbursementDaoImpl : getAllReimbs: ", e);
		}

		return allReimbs;
	}
	
	@Override
	public Boolean updateReimbStatus(Reimbursement reimb) {
		int resolver = Integer.parseInt(reimb.getReimbResolver());
		int reimbStatus = Integer.parseInt(reimb.getReimbStatus());
		try (Connection conn = MyControllerFactory.getConnection()) {

			String sql = "update ers_reimbursement "
					+ "Set REIMB_RESOLVED = CURRENT_TIMESTAMP, REIMB_RESOLVER = ? , REIMB_STATUS_ID = ?"
					+ "WHERE REIMB_ID = ?;";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, resolver);
			ps.setInt(2, reimbStatus);
			ps.setInt(3, reimb.getReimbId());
			ps.executeUpdate(); // <--query not update
			String logMsg = "" + resolver+ " updated the status of "+ reimb.getReimbId() + " to " + reimbStatus;
			EventLogger.loggy.info(logMsg);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			EventLogger.loggy.error("A SQL exception was thrown in ReimbursementDaoImpl : updateReimbStatus: ", e);
			return false;
		}
	}
	
	@Override
	public void newReimb(Reimbursement reimb) {
		try (Connection conn = MyControllerFactory.getConnection()) {
			int author = Integer.parseInt(reimb.getReimbAuthor());
			int status = Integer.parseInt(reimb.getReimbType());
			String sql = "INSERT INTO ERS_REIMBURSEMENT "
					+ "VALUES (DEFAULT, ?, CURRENT_TIMESTAMP, NULL, ?, NULL, ?, NULL, 0, ?);";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, reimb.getReimbAmount());
			ps.setString(2, reimb.getReimbDescription());
			ps.setInt(3, author);
			ps.setInt(4, status);
			ps.executeUpdate(); // <--query not update
			String logMsg = author + " created a new reimbursement request for $"+ reimb.getReimbAmount();
			EventLogger.loggy.info(logMsg);
		} catch (SQLException e) {
			e.printStackTrace();
			EventLogger.loggy.error("A SQL exception was thrown in ReimbursementDaoImpl : newReimb: ", e);
		}
	}
	

}

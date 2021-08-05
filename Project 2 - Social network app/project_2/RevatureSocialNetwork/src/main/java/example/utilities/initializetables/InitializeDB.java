package example.utilities.initializetables;

import example.model.user.UserGender;
import example.model.user.UserRole;

public class InitializeDB {
	
	
	public static void main(String[] args) {
		

		
		//	public UserRole(String role) {
		UserRole myRole0 = new UserRole("Admin");
		UserRole myRole1 = new UserRole("Admin");
		UserRole myRole2 = new UserRole("Admin");

		
		
		//  public UserGender(String gender, String pronouns) {
		UserGender gender0 = new UserGender ("Male", "He/Him");
		UserGender gender1 = new UserGender ("Female", "She/Her");
		UserGender gender2 = new UserGender ("Other", "They/Them");
		
//		Session ses = HibernateSessionGenrator.getSession();
//		Transaction tx = ses.beginTransaction();
//		ses.save(gender2);
//		tx.commit();
//		ses.close();
		
		byte[] myArr = {1,2,3};
 		//  	public User(String username, String password, byte[] salt, String fname, String mname, String lname,
		//  String userEmail, Date bday, UserGender userGender, UserRole userRole) {

		
		//  public Post(String postWrittenContent, User postedBy) {
		
		//  public PostComment(String comment, Post postId, User userId) 
		
		//  public PostPhoto(String postPhotoKey) {
	}

}


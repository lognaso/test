package kr.co.cwit.common.util.dao;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserUtilVO implements Serializable {

	private String url;
	private String userId;
	private String userPw;
	private String status;
	private String userNm;
	
	private UserUtilVO access_token;
	private String refreshToken;
	private String token;
	
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserUtilVO getAccess_token() {
		return access_token;
	}
	public void setAccess_token(UserUtilVO access_token) {
		this.access_token = access_token;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

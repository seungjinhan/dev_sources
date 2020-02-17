package com.chunlab.app.token;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.chunlab.app.utils.domain.BaseVo;

import lombok.Data;

/**
 * 
  * @FileName : TokenVo.java
  * @Project : app_server
  * @Date : 2019. 9. 26. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
@Table(name = "tokens")
@Entity
@Data
public class TokenVo extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1938555522837628411L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "no", nullable = false, unique = true, length = 20)
	private long no;

	/**
	 * app device no
	 */
	@Column(name = "app_device_no", nullable = true, unique = false, length = 20)
	private Long appDeviceNo;
	
	/**
	 * 리프레쉬 토큰
	 */
	@Column(name = "refresh_token", nullable = false, unique = true, length = 100)
	private String refreshToken;
	
	/**
	 * 이메일
	 */
	@Column(name = "email", nullable = false, unique = true, length = 50)
	private String email;
	
	/**
	 * 생성일
	 */
	@Column(name = "create_date", nullable = true, unique = false, length = 20)
	private String createDate;
	
	// Access token (session에 저장됨)
	@Transient
	private String accessToken;

	// Access Token 만료일
	@Transient
	private String accessTokenExpire;
	
	
	// Refresh Token 만료일
	@Transient
	private String refreshTokenExpire;
	
	
}

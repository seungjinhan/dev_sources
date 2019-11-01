package com.chunlab.app.token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
  * @FileName : TokenRepository.java
  * @Project : app_server
  * @Date : 2019. 9. 26. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
@Transactional
public interface TokenRepository extends PagingAndSortingRepository<TokenVo, Long> {
	
	Optional< TokenVo> findByNo( Long no);
	
	Optional< List< TokenVo>> findByNoGreaterThanEqual( Long no);
	
	// 앱키로 조회
	Optional< TokenVo> findByAppDeviceNo( Long appDeviceNo);
	
	// 이메일로 삭제처리
	int deleteByEmail(String email);
	
	// 이메일로 조회
	Optional< TokenVo> findByEmail(String email);
	
	// Refresh Token으로 조회하기
	Optional< TokenVo> findByRefreshToken( String refreshToken);
	
	
	// 수정
	@Modifying
	@Query( value = "UPDATE TokenVo t "
			+ " SET "
			+ " t.appDeviceNo=:appDeviceNo, "
			+ " t.refreshToken=:refreshToken, "
			+ " t.createDate=:createDate "
			+ " WHERE t.email=:email", nativeQuery = false)
	Integer modifyByEmail( 	@Param("appDeviceNo")Long appDeviceNo,
						@Param("refreshToken")String refreshToken,
						@Param("createDate")String createDate,
						@Param("email")String email
			);
	
	// 새로운 앱키를 저장할때 기존에 동일한 앱키로 사용중이면 기존앱키를 사용하는 정보에 앱키 정보를 제거한다
	@Modifying
	@Query( value = "UPDATE TokenVo t "
			+ " SET "
			+ " t.appDeviceNo=0L "
			+ " WHERE t.appDeviceNo=:appDeviceNo ", nativeQuery = false)
	Integer initAppKeyForAvoidDuplicate( 	
						@Param("appDeviceNo")Long appDeviceNo
						);
}

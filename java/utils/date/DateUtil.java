package com.chunlab.app.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.chunlab.app.system.enums.EnumExceptionOthers;
import com.chunlab.app.system.exception.ExceptionBase;
import com.chunlab.app.utils.date.enums.EnumDateType;
import com.chunlab.app.utils.date.enums.EnumDayType;
import com.chunlab.app.utils.date.enums.EnumExceptionDate;
import com.chunlab.app.utils.enums.EnumExceptionObject;
import com.chunlab.app.utils.enums.EnumSizeCompare;
import com.chunlab.app.utils.others.LogUtil;
import com.chunlab.app.utils.string.enums.EnumExceptionString;

/**
 * 날짜처리 유틸
 * 
 * @package : com.chunlab.app.utils.date
 * @file    : DateUtil.java
 * @author  : seungjin.han
 * @Date    : 2018. 11. 7.
 * @version : 0.1
 */
public class DateUtil {

	/**
	 * 날짜 가져오기 YYYY
	 * 
	 * @return
	 */
	private static String nowYYYY () {

		return String.valueOf(LocalDate.now().getYear());
	}

	/**
	 * 월 가져오기
	 * 
	 * @return
	 */
	private static String nowMM () {

		return String.format("%02d", LocalDate.now().getMonthValue());
	}

	/**
	 * 일 가져오기
	 * 
	 * @return
	 */
	private static String nowDD () {

		return String.format("%02d", LocalDate.now().getDayOfMonth());
	}

	private static String nowHH() {
		
		return String.format("%02d", LocalDateTime.now().getHour());
	}
	
	private static String nowmm() {
		
		return String.format("%02d", LocalDateTime.now().getMinute());
	}
	
	private static String nowss() {
		
		return String.format("%02d", LocalDateTime.now().getSecond());
	}
	
	
	/**
	 * 날짜 가져오기 YYYYMMDD
	 * 
	 * @return
	 */
	private static String nowYYYYMMDD ( String seq) {

		return nowYYYY() + seq + nowMM() + seq + nowDD();

	}
	
	private static String nowMMDD( String seq) {
		
		return nowMM() + seq + nowDD();
	}


	private static String nowYYYYMM ( String seq) {

		return nowYYYY() + seq + nowMM();
	}


	/**
	 * 
	  * @Method Name : nowYYYYMMDDHHmm
	  * @작성일 : 2019. 10. 7.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 :
	  * @param daySeq 년원일 구분자
	  * @param dayTimeSeq 년월일 시간 사이 구분자
	  * @param timeSeq 시분초 사이 구분자
	  * @return
	 */
	private static String nowYYYYMMDDHHmm (String daySeq, String dayTimeSeq ,String timeSeq) {
		String now = nowYYYYMMDD( daySeq);		
		String time = nowHHmm( timeSeq);
		
		return now + dayTimeSeq + time;
	}

	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	  * @Method Name : getLocalDateType
	  * @작성일 : 2019. 10. 7.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : String type날짜를 LocalDate로 변환
	  * @param yyyymmdd
	  * @return
	  * @throws ExceptionBase
	 */
	public static LocalDate getLocalDateType( String yyyymmdd) throws ExceptionBase {
		LocalDate date = null;
		try {
			date = LocalDate.of( 
				Integer.parseInt(yyyymmdd.substring(0, 4)), 
				Integer.parseInt(yyyymmdd.substring(4, 6)), 
				Integer.parseInt(yyyymmdd.substring(6, 8)));
		}
		catch(StringIndexOutOfBoundsException e) {
			
			throw new ExceptionBase( EnumExceptionDate.WRONG_DATE_FORMAT_ERROR, yyyymmdd);
		}
		catch( Exception e) {
			throw new ExceptionBase( EnumExceptionDate.CHANGE_DATE_ERROR, yyyymmdd);
		}
		
		return date;
	}
	
	/**
	 * 
	  * @Method Name : getLocalDateTime
	  * @작성일 : 2019. 10. 31.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 :
	  * @param yyyymmddhhmm
	  * @return
	  * @throws ExceptionBase
	 */
	public static LocalDateTime getLocalDateTime( String yyyymmddhhmm) throws ExceptionBase {
		LocalDateTime date = null;
		try {
			date = LocalDateTime.of( 
					Integer.parseInt(yyyymmddhhmm.substring(0, 4)), 
					Integer.parseInt(yyyymmddhhmm.substring(4, 6)), 
					Integer.parseInt(yyyymmddhhmm.substring(6, 8)),
					Integer.parseInt(yyyymmddhhmm.substring(8, 10)),
					Integer.parseInt(yyyymmddhhmm.substring(10, 12))					
					);
		}
		catch(StringIndexOutOfBoundsException e) {
			
			throw new ExceptionBase( EnumExceptionDate.WRONG_DATE_FORMAT_ERROR, yyyymmddhhmm);
		}
		catch( Exception e) {
			throw new ExceptionBase( EnumExceptionDate.CHANGE_DATE_ERROR, yyyymmddhhmm);
		}
		
		return date;
	}
	
	/**
	 * 시,분 2자리수 
	 * @return
	 */
	public static String nowHHmm( String seq) {
		
		return nowHH()+ seq + nowmm();
	}

	/**
	 * 
	  * @Method Name : nowYYYYMMDDHHmmss
	  * @작성일 : 2019. 10. 7.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 :
	  * @param daySeq 년원일 구분자
	  * @param dayTimeSeq 년월일 시간 사이 구분자
	  * @param timeSeq 시분초 사이 구분자
	  * @return
	 */
	private static String nowYYYYMMDDHHmmss (String dateSeq, String dayTimeSeq ,String timeSeq) {

		return nowYYYYMMDDHHmm(dateSeq, dayTimeSeq , timeSeq) + timeSeq + nowss();
	}

	/**
	 * DB에 저장할 생성일
	 * @return
	 */
	public static String getDatabaseCreateDate() {
		
		return now( EnumDateType.YYYYMMDDHHmmss);
	}

	/**
	 * 날짜, 시간 찾기
	 * 
	 * @param type
	 * @return
	 */
	public static String now ( EnumDateType type) {

		String date = "";

		if ( type == EnumDateType.YYYY){

			return nowYYYY();
		}
		else if( type == EnumDateType.MM) {
		    
		    return nowMM();
		}
		else if( type == EnumDateType.DD) {
			
			return nowDD();
		}
		else if( type == EnumDateType.MMDD) {
			
			return nowMMDD("");
		}
		else if ( type == EnumDateType.YYYYMM){

			return nowYYYYMM("");
		}
		else if ( type == EnumDateType.YYYYMMDD){

			return nowYYYYMMDD("");
		}
		else if ( type == EnumDateType.YYYYMMDDHHmm){

			return nowYYYYMMDDHHmm("" ,"" ,  "");
		}
		else if ( type == EnumDateType.YYYYMMDDHHmmss){

			return nowYYYYMMDDHHmmss("" , "", "");
		}

		return date;
	}

	/**
	 * 시간을 타입으로 구분자와 함께 반환
	 * 
	 * @param type
	 * @param seperator
	 * @return
	 * @throws ExceptionBase 
	 */
	public static String now ( EnumDateType type, String daySeq, String dayTimeSeq, String timeSeq) throws ExceptionBase {

		if ( type == EnumDateType.YYYY){

			return nowYYYY();
		}
		else if( type == EnumDateType.MM) {
		    
		    return nowMM();
		}
		else if( type == EnumDateType.DD) {
			
			return nowDD();
		}
		else if( type == EnumDateType.MMDD) {
			
			return nowMMDD( daySeq);
		}
		else if ( type == EnumDateType.YYYYMM){

			return nowYYYYMM( daySeq);
		}
		else if ( type == EnumDateType.YYYYMMDD){

			return nowYYYYMMDD( daySeq);
		}
		else if ( type == EnumDateType.YYYYMMDDHHmm){

			return nowYYYYMMDDHHmm(daySeq, dayTimeSeq, timeSeq);
		}
		else if ( type == EnumDateType.YYYYMMDDHHmmss){

			return nowYYYYMMDDHHmmss(daySeq, dayTimeSeq, timeSeq);
		}
		else {
			throw new ExceptionBase( EnumExceptionOthers.IllegalAccessException);
		}

	}

	/**
	 * 요일가져오기
	  * @Method Name : nowWeek
	  * @작성일 : 2019. 10. 7.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 :
	  * @return
	 */
	public static DayOfWeek nowWeek () {
		
		return LocalDate.now().getDayOfWeek();
	}
	
	/**
	 * 
	  * @Method Name : nextDate
	  * @작성일 : 2019. 10. 7.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 날을 더하거나 뺀 날을 구하기
	  * @param yyyymmdd
	  * @param addDay
	  * @param seq
	  * @return
	  * @throws ParseException
	  * @throws ExceptionBase
	 */
	public static String nextDate( String yyyymmdd, long addDay, String seq) throws ExceptionBase {
		
		if( yyyymmdd == null) {
		
			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}
		
		LocalDate date = getLocalDateType( yyyymmdd);
		
		return date.plusDays( addDay).format( DateTimeFormatter.ofPattern("yyyy"+seq+"MM"+seq+"dd"));
	}


	/**
	 * 
	  * @Method Name : getWeek
	  * @작성일 : 2019. 10. 7.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 :
	  * @param today : 오늘 날짜 "20190506"
	  * @param idx   : 오늘 기준으로 몇번째 주를 가져올지 (시작 : 1)
	  * @return
	  * @throws ParseException
	  * @throws ExceptionBase
	 */
	public static Map<Integer, String> getWeekDays( String yyyymmdd , long idx, String seq) throws ExceptionBase {

		LocalDate date = getLocalDateType( yyyymmdd);
		
		date = date.plusWeeks( idx);
		Map< Integer, String> result = new HashMap<Integer, String>();
		
		DayOfWeek dayofWeek = date.getDayOfWeek();

		int move = -1;
		if( dayofWeek == DayOfWeek.SUNDAY) {
			
			move = 0;
		}
		int index = dayofWeek.getValue() * move;
		for (int i = index; i < (index+7); i++) {
			
			LocalDate newDate = date.plusDays(i);
			result.put( Integer.parseInt( newDate.format( DateTimeFormatter.ofPattern("yyyyMMdd"))) , newDate.format( DateTimeFormatter.ofPattern("yyyy"+seq+"MM"+seq+"dd")));
		}
		
		return result;
	}
	
	/**
	 * 
	  * @Method Name : getDay
	  * @작성일 : 2019. 10. 8.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 :
	  * @param yyyymmdd
	  * @param idx
	  * @param seq
	  * @return
	  * @throws ExceptionBase
	 */
	public static Map<Integer, String> getDay( String yyyymmdd , long index, String seq) throws ExceptionBase {
		
		LocalDate date = getLocalDateType( yyyymmdd);
		
		date = date.plusDays( index);
		
		Map< Integer, String> result = new HashMap<Integer, String>();
		result.put( Integer.parseInt( date.format( DateTimeFormatter.ofPattern("yyyyMMdd"))) , date.format( DateTimeFormatter.ofPattern("yyyy"+seq+"MM"+seq+"dd")));
		
		return result;
	}
	
	/**
	  * @Method Name : checkTimeOver
	  * @작성일 : 2019. 10. 31.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 현재시간과 비교하여 시간이 지났는지 여부를 확인
	  * @param yyyymmddhhmm : 기준시간
	  * @param howLongHour  : 기준시간에서 더할 시간
	  * @return
	  * @throws ExceptionBase
	 */
	public static EnumSizeCompare checkTimeOver( String yyyymmddhhmm, long howLongHour) throws ExceptionBase {
		
		LocalDateTime date = getLocalDateTime( yyyymmddhhmm);
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime datePlusOneDay = date.plusHours(howLongHour);
		
		long nowLong = Long.parseLong( now.format( DateTimeFormatter.ofPattern("yyyyMMddHHmm")));
		long datePlusLong = Long.parseLong( datePlusOneDay.format( DateTimeFormatter.ofPattern("yyyyMMddHHmm")));
		
		// 년월일시분 동일함
		if( nowLong == datePlusLong) {
			
			return EnumSizeCompare.SAME;
		}
		// 아직 시간이 안지남  
		else if( nowLong > datePlusLong ) {
			
			return EnumSizeCompare.SMALL;
		}
		// 이미 시간이 지남
		else {
			
			return EnumSizeCompare.BIG;
		}
	}
	/**
	  * @Method Name : getWeekFirstDayAndLastDay
	  * @작성일 : 2019. 10. 7.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 해당하는 주의 첫날 끝날
	  * @param yyyymmdd
	  * @param seq
	  * @return
	  * @throws ExceptionBase
	 */
	public static String[] getWeekFirstDayAndLastDay( String yyyymmdd, String seq) throws ExceptionBase {
		
		LocalDate date = getLocalDateType( yyyymmdd);
		DayOfWeek dayofWeek = date.getDayOfWeek();
		
		int index = dayofWeek.getValue() * -1;
		
		LocalDate start = date.plusDays( index);
		LocalDate end  = date.plusDays( index+6);
		
		String[] result = new String[2];
		result[0] = start.format( DateTimeFormatter.ofPattern("yyyy"+seq+"MM"+seq+"dd"));
		result[1] = end.format( DateTimeFormatter.ofPattern("yyyy"+seq+"MM"+seq+"dd"));
		
		return result;
	}
	
	/**
	 * 
	  * @Method Name : getDateListForDays
	  * @작성일 : 2019. 10. 7.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 원하는 날짜만큰 리스트 가져오기
	  * @param startDayYYYYmmdd
	  * @param days
	  * @param seq
	  * @return
	  * @throws ExceptionBase
	 */
	public static Map<Integer, String> getDateListForDays( String startDayYYYYmmdd, long days, String seq) throws ExceptionBase {
		
		LocalDate date = getLocalDateType(startDayYYYYmmdd);
		
		Map<Integer, String> result = new HashMap<Integer, String>();
		
		for (int i = 0 ; i < days; i++) {
			result.put(i,  date.plusDays(i).format( DateTimeFormatter.ofPattern("yyyy"+seq+"MM"+seq+"dd")));
		}
		
		return result;
	}
	
	/**
	  * @Method Name : getMonthDays
	  * @작성일 : 2019. 10. 7.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 해당 날에 해당하는 월의 날짜 전체를 가져오기
	  * @param yyyymmdd (일)
	  * @param seq (구분자)
	  * @return
	 * @throws ExceptionBase 
	 */
	public static Map<Integer, String> getMonthDays( String yyyymmdd, String seq) throws ExceptionBase {
		
		LocalDate date = getLocalDateType( yyyymmdd);
		
		LocalDate start = date.with( TemporalAdjusters.firstDayOfMonth());
		LocalDate end  = date.with( TemporalAdjusters.lastDayOfMonth());
		
		Map<Integer, String> sb = new HashMap<Integer, String>();
		
		Period p = start.until(end);
		Integer endday = p.getDays();

		int year = date.getYear();
		int month = date.getMonthValue();
		String monthStr = String.format("%02d", month);
		
		for (int i = 0; i < endday; i++) {
		
			String day = String.format("%02d", i+1);
			String temp = "" + year + seq + monthStr + seq + day;
			sb.put( Integer.parseInt( year + monthStr + day), temp);
		}
		
		return sb;
	}
	
	public static Map<Integer, String> getMonthDays( String yyyymmdd, int index, String seq) throws ExceptionBase {
		
		LocalDate date = getLocalDateType( yyyymmdd + "01");
		
		for (int i = 0; i < Math.abs(index); i++) {
		
			// 0보다 클때는 마지막 날에서 하루를 더하면 다음 시작일이 나옴
			if( index > 0) {
				
				date =  date.with( TemporalAdjusters.lastDayOfMonth());
				date = date.plusDays( 1);
			}
			// 0보다 작을때는 첫째날에서 하루를 빼면 이전달 마지막 날이 나옴
			else {
				
				date =  date.with( TemporalAdjusters.firstDayOfMonth());
				date = date.minusDays( 1);
			}
		}
		
		LocalDate start = date.with( TemporalAdjusters.firstDayOfMonth());
		LocalDate end  = date.with( TemporalAdjusters.lastDayOfMonth());
		
		Map<Integer, String> sb = new HashMap<Integer, String>();
		
		Period p = start.until(end);
		Integer endday = p.getDays();
		
		int year = date.getYear();
		int month = date.getMonthValue();
		String monthStr = String.format("%02d", month);
		
		for (int i = 0; i <= endday; i++) {
			
			String day = String.format("%02d", i+1);
			String temp = "" + year + seq + monthStr + seq + day;
			sb.put( Integer.parseInt( year + monthStr + day), temp);
		}
		
		return sb;
	}
	
	public static Map<Integer, String> getYearDays( String yyyy, int index , String seq) throws ExceptionBase {
		
		int yearI = Integer.parseInt(yyyy) + index;

		Map<Integer, String> sb = new HashMap<Integer, String>();
		for (int i = 1; i < 13; i++) {
		
			String month = String.format("%02d", i);
			Map<Integer, String> t = getMonthDays( yearI + month + "01", 0, seq);
			sb.putAll(t);
		}
		
		return sb;
	}
	
	/**
	 * 
	  * @Method Name : getMonthFirstDayAndLastDay
	  * @작성일 : 2019. 10. 7.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 해당 달의 첫날 끝날
	  * @param yyyymmdd
	  * @param seq
	  * @return
	  * @throws ExceptionBase
	 */
	public static String[] getMonthFirstDayAndLastDay( String yyyymmdd, String seq) throws ExceptionBase {
		
		LocalDate date = getLocalDateType( yyyymmdd);
		
		LocalDate start = date.with( TemporalAdjusters.firstDayOfMonth());
		LocalDate end  = date.with( TemporalAdjusters.lastDayOfMonth());
		
		String[] result = new String[2];
		result[0] = start.format( DateTimeFormatter.ofPattern("yyyy"+seq+"MM"+seq+"dd"));
		result[1] = end.format( DateTimeFormatter.ofPattern("yyyy"+seq+"MM"+seq+"dd"));
		
		return result;
	}
	

	/**
	 * 
	  * @Method Name : getDays
	  * @작성일 : 2019. 10. 16.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 날짜 리스트 가져오기
	  * @param yyyymmdd
	  * @param dayType
	  * @param seq
	  * @param index
	  * @return
	  * @throws ExceptionBase
	 */
	public static Map<Integer, String> getDays( String yyyymmdd, EnumDayType dayType, String seq, int index) throws ExceptionBase{
		
		
		Map<Integer, String> sb = new HashMap<Integer, String>();
		
		if( dayType == EnumDayType.MONTH) {
			
			sb = getMonthDays(yyyymmdd, index, seq);
		}
		else if( dayType == EnumDayType.WEEK) {
			
			sb = getWeekDays(yyyymmdd, index , seq);
		}
		else if( dayType == EnumDayType.DAY) {
			sb = getDay(yyyymmdd, index, seq);
		}
		else if( dayType == EnumDayType.YEAR) {
			sb = getYearDays(yyyymmdd, index, seq);
		}
		else {
			throw new ExceptionBase( EnumExceptionDate.CANNOT_MAKE_DATA, dayType);
		}
		
		return sb;
	}
	

	/**
	 * 
	  * @Method Name : getFirstDayAndLastDay
	  * @작성일 : 2019. 10. 7.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 시작날 끝날 가져오기
	  * @param yyyymmdd
	  * @param dayType
	  * @param seq
	  * @return
	  * @throws ExceptionBase
	 */
	public static String[] getFirstDayAndLastDay( String yyyymmdd, EnumDayType dayType, String seq) throws ExceptionBase{
		
		
		String[] sb = null;
		
		if( dayType == EnumDayType.MONTH) {
			
			sb = getMonthFirstDayAndLastDay(yyyymmdd, seq);
		}
		else if( dayType == EnumDayType.WEEK) {
			
			sb = getWeekFirstDayAndLastDay(yyyymmdd, seq);
		}
		else {
			throw new ExceptionBase( EnumExceptionDate.CANNOT_MAKE_DATA, dayType);
		}
		
		return sb;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 분 계산하기
	 * @param date yyyyMMddHHmmss
	 * @return
	 * @throws ParseException 
	 */
	public static String addMiniutesFromNow( EnumDateType dateType, int minutes ) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateType.getType());
		Date today = dateFormat.parse( now(dateType));
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.MINUTE, minutes);
		
		String result = dateFormat.format(cal.getTime());
		return result;
	}



	/**
	  * @Method Name : addDaysFromToday
	  * @작성일 : 2019. 10. 31.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 :
	  * @param days
	  * @return
	  * @throws ParseException
	 */
	public static Long addDaysFromToday( int days ) throws ParseException {
		
		LocalDate now = LocalDate.now();
		LocalDate nowPlus = now.plusDays( days);
		return Long.parseLong( nowPlus.format( DateTimeFormatter.ofPattern("yyyyMMdd")));
	}
	
	
	/**
	 * 날짜를 비교
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 * @throws ExceptionBase 
	 */
	public static EnumSizeCompare compareDate ( Date d1, Date d2) throws ExceptionBase {

		if( d1 == null || d2 == null) {
			
			throw new ExceptionBase( EnumExceptionObject.OBJECT_NULL);
		}
		
		int result = d1.compareTo(d2);
		
		if ( result == -1){
		    
			return EnumSizeCompare.SMALL;
		}
		else if ( result == 0){

			return EnumSizeCompare.SAME;
		}
		else if ( result == 1){

			return EnumSizeCompare.BIG;
		}
		
		return EnumSizeCompare.ERROR;

	}

	/**
	 * 스트링타입의 날짜를 비교
	 * 
	 * @param s1
	 * @param s2
	 * @param format
	 * @return
	 * @throws ParseException
	 * @throws ExceptionBase 
	 * @throws ExceptionBase 
	 */
	public static EnumSizeCompare compareDate ( String s1, String s2, EnumDateType format) throws ParseException, ExceptionBase {

		if( s1 == null || s2 == null) {
			
			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format.getType());
		Date d1 = null;
		Date d2 = null;
		d1 = simpleDateFormat.parse(s1);
		d2 = simpleDateFormat.parse(s2);

		return compareDate(d1, d2);
	}

	
	/**
	 * 두 시간의 차리를 분으로 확인
	 * @param date
	 * @param date2
	 * @return
	 * @throws ParseException
	 * @throws ExceptionBase 
	 */
	public static Long  dateDifferenceByMinutes( String date, String date2) throws ParseException, ExceptionBase {

		if( date == null || date2 == null) {
			
			throw new ExceptionBase( EnumExceptionString.STRING_NULL);
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
		
		//요청시간을 Date로 parsing 후 time가져오기 
		Date reqDate = dateFormat.parse(date); 
		Date reqDate2 = dateFormat.parse(date2);
		
		long reqDateTime = reqDate.getTime(); 
		long reqDateTime2 = reqDate2.getTime(); 
		
		long minute = (reqDateTime2 - reqDateTime) / 60000;
		
		LogUtil.logDebug("요청시간 : " + reqDateTime); 
		LogUtil.logDebug("현재시간 : " + reqDateTime2); 
		LogUtil.logDebug(minute+"분 차이");
		
		return minute;
	}
	

	/**
	 * 오늘날짜 기준으로 입력날짜 비교
	 * 
	 * 엊그제 : -2
	 * 어제 : -1
	 * 오늘 : 0
	 * 내일 : 1
	 * 내일 모레 : 2
	 * 
	 * @param today
	 * @return
	 */
	public static int compareDateWithToday( int compareDate ) {
		
		Integer today = Integer.parseInt(now( EnumDateType.YYYYMMDD ));
		Integer result = compareDate - today;
		
		return result ;
	}
	
	/**
	 * 오늘 기준으로 해당날짜 계산
	 * 
	 * @param calcDay
	 * @return
	 * @throws ParseException 
	 */
	public static int calcDateBasedOnToday( int calcDay ) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date today = dateFormat.parse(now(EnumDateType.YYYYMMDD));

		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DATE, calcDay);
		
		String result = dateFormat.format(cal.getTime());
		return Integer.valueOf(result);
	}
	
	/**
	 * 
	  * @Method Name : dateDiffence
	  * @작성일 : 2019. 11. 6.
	  * @작성자 : deepplin
	  * @변경이력 : 
	  * @Method 설명 : 두 날짜간 차이
	  * @param date1
	  * @param date2
	  * @return
	  * @throws ExceptionBase
	 */
	public static int dateDiffence( String date1, String date2) throws ExceptionBase {
	
		LocalDate date01 = getLocalDateType( date1);
		LocalDate date02 = getLocalDateType( date2);
		return Period.between(date01, date02).getDays();
		
	}
	
	public static void main(String[] args) throws ParseException, ExceptionBase {

		System.out.println( dateDiffence("20191102", "20191101"));
		
//		System.out.println( checkTimeOver("201910301112", 24));
		
		
//		System.out.println( getYearDays("2019",  0 , "").size());
//		System.out.println( getLocalDateType("99991213"));
//		System.out.println( calcDateBasedOnToday(50));
//		System.out.println( now(EnumDateType.YYYYMMDDHHmm));
//		System.out.println( addMiniutes( EnumDateType.YYYYMMDDHHmm, 120));
//		System.out.println( getWeekDays("20191020", 0, "-"));
//		System.out.println( nextDate("20191011", -1, "-"));
//		System.out.println(now(EnumDateType.YYYYMMDDHHmm));
//		System.out.println(now(EnumDateType.YYYYMMDDHHmmss, "-" , " " , ":"));
//		
		//System.out.println( getMonthFirstDayAndLastDay("20191021" , "")[1]);
		
//		System.out.println( getDays("20191010", EnumDayType.MONTH, "", 0));
//		System.out.println( getDays("20191010", EnumDayType.MONTH, "", -1));
//		System.out.println( getDays("20191010", EnumDayType.MONTH, "", 1));
//		System.out.println( getDays("20191010", EnumDayType.WEEK, "", 0));
//		System.out.println( getDays("20191010", EnumDayType.WEEK, "", -1));
//		System.out.println( getDays("20191010", EnumDayType.WEEK, "", 1));
//		System.out.println( getDays("20191008", EnumDayType.DAY, "", 0));
//		LocalDate date = getLocalDateType( "20191001");
//		
//		LocalDate start = date;
//		System.out.println(start.getMonthValue());
//		
//		for (int i = 0; i < 3; i++) {
//
//			start = start.with( TemporalAdjusters.firstDayOfMonth());
//			start = start.minusDays(1);
//			start = start.with( TemporalAdjusters.firstDayOfMonth());
//			
//		}
//		
//		System.out.println(start.with( TemporalAdjusters.firstDayOfMonth()));
//		System.out.println(start.with( TemporalAdjusters.lastDayOfMonth()));
//		System.out.println( getMonthDays("20191007" , "-"));

//		System.out.println(DayOfWeek.MONDAY.getValue());
//		System.out.println(DayOfWeek.TUESDAY.getValue());
//		System.out.println(DayOfWeek.WEDNESDAY.getValue());
//		System.out.println(DayOfWeek.THURSDAY.getValue());
//		System.out.println(DayOfWeek.FRIDAY.getValue());
//		System.out.println(DayOfWeek.SATURDAY.getValue());
//		System.out.println(DayOfWeek.SUNDAY.getValue());
//		System.out.println( LocalDate.of(2019, 10, 2).with( TemporalAdjusters.firstDayOfMonth()) );
//		System.out.println( LocalDate.of(2019, 10, 2).with( TemporalAdjusters.lastDayOfMonth()) );
	}
}

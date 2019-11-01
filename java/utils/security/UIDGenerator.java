package com.chunlab.app.utils.security;


/**
 * 
  * @FileName : UIDGenerator.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public class UIDGenerator {

	private static final int WAIT_TIME = 2;
	private static final int NUMBER_SCALE = 64;
	private static final int MAX_SEQ = NUMBER_SCALE * NUMBER_SCALE * NUMBER_SCALE;
	private static final UIDGenerator UIDGenerator = new UIDGenerator(System.getProperty("uid.serverid", "1"));
	
	private char m_cServerID;
	private long m_lIssuedTime;
	private int m_nSequence = 0;

	private long getNewIssuedTime() {
		//long issuedTime = System.currentTimeMillis();
		long issuedTime = System.nanoTime();
		if (m_lIssuedTime == issuedTime){
			wait_for_a_while();
			issuedTime = getNewIssuedTime();
		}
		return issuedTime;
	}

	private void wait_for_a_while() {
		try
		{
			Thread.sleep(WAIT_TIME);
		}
		catch (InterruptedException e)
		{}
	}
	
	public UIDGenerator(String serverID) {
		setServerID(serverID);
		//m_lIssuedTime = System.currentTimeMillis();
		m_lIssuedTime = System.nanoTime();
	}

	public static String generateUID() {
		return UIDGenerator.makeUID();
	}

	public synchronized String makeUID() {
		char[] arrID = new char[11];
		if (m_nSequence != (MAX_SEQ))
		{
			arrID[0] = m_cServerID;
			char[] issuedTime = Long2String64.convertLongTo64String(getNewIssuedTime());
			for (int i = 0; i < 7; i++)
			{
				arrID[i + 1] = issuedTime[i];
			}
			char[] sequence = Long2String64.convertLongTo64String(m_nSequence, 3);
			for (int i = 0; i < 3; i++)
			{
				arrID[i + 8] = sequence[i];
			}
			m_nSequence++;
		}
		else
		{
			m_lIssuedTime = getNewIssuedTime();
			m_nSequence = 0;
			return makeUID();
		}
		return new String(arrID);
	}


	public void setServerID(String serverID) {
		if (serverID == null)
			throw new RuntimeException("UIDGenerator:serverID is null");
		if (serverID.length() != 1)
			throw new RuntimeException("UIDGenerator:serverID has invalid length");
		m_cServerID = serverID.charAt(0);
	}


}

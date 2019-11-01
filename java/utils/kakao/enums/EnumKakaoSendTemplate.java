package com.chunlab.app.utils.kakao.enums;

/**
 * 
  * @FileName : EnumKakaoSendTemplate.java
  * @Project : app_server
  * @Date : 2019. 10. 2. 
  * @작성자 : seungjin.han
  * @변경이력 :
  * @프로그램 설명 :
 */
public enum EnumKakaoSendTemplate {

	smb2SendHealthNoteNotLogin1("smb2SendHealthNoteNotLogin1" , 
								"헬스노트_비로그인개발용", 
								"[스마일바이오미]\n" + 
								"신청하신 프로그램에 따라 헬스노트를 작성해주세요^^",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code="),

	smb2CTZJoin(				"smb2CTZJoin",
								"프로그램_신청시",
								"[스마일바이오미]\n" +
								"안녕하세요.\n" + 
								"시민과학프로젝트 시즌2에 참여해주셔서 감사합니다. \n\n" +
								"시민과학 키트와 프로바이오틱스는 공휴일 제외 3~5일 내에 배송될 예정입니다. \n" +
								"키트를 수령한 후 설명서에 따라 키트를 등록하고 당일에 채변해주세요:)",
								"스마일바이오미 바로가기",
								"https://www.smilebiome.com/",
								"https://www.smilebiome.com/"),
	
	smb2SendHealthNoteLink1(	"smb2SendHealthNoteLink1",
								"시즌2_헬스노트1일차",
								"[스마일바이오미]\n" + 
								"키트를 등록해 주셔서 감사합니다.\n\n" + 
								"■ 키트 등록 당일인 오늘, 꼭 채변해주세요. \n" + 
								"■ 천랩에서 제공하는 프로바이오틱스 제품은 1차 채변 후에 섭취를 시작해주세요.\n" + 
								"■ 프로젝트 기간(14일)동안 헬스노트를 잊지 말고 작성해주세요^^\n\n" + 
								"헬스노트에 기록되는 모든 정보는 여러분의 건강과 장내 미생물 생태계의 균형을 알아보는 중요한 지표로 사용됩니다.\n\n" + 
								"헬스노트를 5회 이상 작성하신 분들께 2차 키트가 발송되며, 5회 미만으로 작성하신 분들은 프로그램이 자동 종료됩니다.\n\n" + 
								"시민과학프로젝트를 꾸준히 실천하여 장내 미생물 생태계를 잘 가꾸시길 응원합니다!",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								""),
	
	smb2SendHealthNoteLink2(	"smb2SendHealthNoteLink2" , 
								"시즌2_헬스노트2일차", 
								"[스마일바이오미]\n" + 
								"즐거운 식사시간 보내셨나요?\n" + 
								"참여 중인 시민과학프로젝트의 정확한 분석을 위해 오늘 드신 음식을 헬스노트에 작성해주세요^^",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								""),
	
	smb2SendHealthNoteLink3(	"smb2SendHealthNoteLink3" , 
								"시즌2_헬스노트3일차", 
								"[스마일바이오미]\n" + 
								"헬스노트를 매일매일 작성해주세요.\n\n" + 
								"섭취 중인 프로바이오틱스를 매일 꾸준하게 먹을수록 시민과학프로젝트의 신뢰도가 높아집니다.\n\n" + 
								"장내 미생물 분석에 단서가 되는 시민과학프로젝트 헬스노트!\n" + 
								"잊지 말고 작성해주세요^^",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								""),
	
	smb2SendHealthNoteLink4(	"smb2SendHealthNoteLink4" , 
								"시즌2_헬스노트4일차", 
								"[스마일바이오미]\n" + 
								"기록의 신뢰도를 위해 하루 전 날과 당일의 헬스노트만 작성하실 수 있어요.\n" + 
								"오늘의 하루를 헬스노트에 기록해주세요^^",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								""),
	
	smb2SendHealthNoteLink5(	"smb2SendHealthNoteLink5" , 
								"시즌2_헬스노트5일차", 
								"[스마일바이오미]\n" + 
								"프로바이오틱스 섭취하셨나요? :)\n" + 
								"오늘 하루 어떤 음식을 드셨나요?\n" + 
								"헬스노트에 자유롭게 기록해주세요!",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								""),
	
	smb2SendHealthNoteLink6(	"smb2SendHealthNoteLink6" , 
								"시즌2_헬스노트6일차", 
								"[스마일바이오미]\n" + 
								"오늘의 기분을 알려주세요:)\n" + 
								"참여 중인 시민과학프로젝트의 정확한 분석을 위해 오늘의 기분을 헬스노트에 작성해주세요^^",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								""),

	smb2SendHealthNoteLink7Type1(	"smb2SendHealthNoteLink7Type1" , 
									"시즌2_헬스노트7일차_3건미만", 
									"[스마일바이오미]\n" + 
									"헬스노트 작성을 잊으셨나요?\n\n" + 
									"참여 중인 시민과학프로젝트의 정확한 분석을 위해 헬스노트를 꼭! 작성해주세요.\n" + 
									"헬스노트를 5일 미만으로 작성할 경우, 정확한 분석이 불가하다고 판단하여 프로젝트가 자동 종료됩니다. \n" + 
									"헬스노트를 잊지 말고 작성해주세요!",
									"헬스노트 바로가기",
									"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
									""),

	smb2SendHealthNoteLink7Type2(	"smb2SendHealthNoteLink7Type2" , 
									"시즌2_헬스노트7일차_3건이상", 
									"[스마일바이오미]\n" + 
									"오늘은 헬스노트 7일차 입니다:)\n" + 
									"프로바이오틱스를 섭취한 후 헬스노트를 작성해주세요!",
									"헬스노트 바로가기",
									"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
									""),
	
	smb2SendHealthNoteLink8(	"smb2SendHealthNoteLink8" , 
								"시즌2_헬스노트8일차", 
								"[스마일바이오미]\n" + 
								"헬스노트를 기록할 시간입니다:)\n" + 
								"내가 보낸 오늘 하루가 내 몸에 어떤 영향을 줄까요?\n" + 
								"오늘의 메모에 자유롭게 기록해주세요!",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								""),

	smb2SendHealthNoteLink9(	"smb2SendHealthNoteLink9" , 
								"시즌2_헬스노트9일차", 
								"[스마일바이오미]\n" + 
								"오늘 복통이 있으셨나요?\n" + 
								"헬스노트에 오늘 하루를 기록해주세요!",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								""),
	
	smb2SendHealthNoteLink10Type1(	"smb2SendHealthNoteLink10Type1" , 
									"시즌2_헬스노트10일차_5건미만", 
									"[스마일바이오미]\n" + 
									"헬스노트 작성할 시간이에요:)\n\n" + 
									"헬스노트를 5일 이상 작성한 참여자에게만 2차 키트가 배송됩니다.\n" + 
									"잊지 말고 작성해주세요!",
									"헬스노트 바로가기",
									"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
									""),
	
	smb2SendHealthNoteLink10Type2(	"smb2SendHealthNoteLink10Type2" , 
									"시즌2_헬스노트10일차_5건이상", 
									"[스마일바이오미]\n" + 
									"곧 2차 키트가 배송될 예정입니다:)\n\n" + 
									"헬스노트 14일차 작성을 완료한 후 3일 이내에 2차 키트를 등록, 채변해주세요!\n" + 
									"분석이 완료되면 안내해 드리겠습니다. (2차 키트 접수 후, 약 6주 소요 예정)",
									"헬스노트 바로가기",
									"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
									""),
	
	smb2SendHealthNoteLink11(	"smb2SendHealthNoteLink11" , 
								"시즌2_헬스노트11일차", 
								"[스마일바이오미]\n" + 
								"오늘은 헬스노트 11일차입니다.\n" + 
								"오늘 하루를 헬스노트에 기록해주세요!",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								""),
	
	smb2SendHealthNoteLink12(	"smb2SendHealthNoteLink12" , 
								"시즌2_헬스노트12일차", 
								"[스마일바이오미]\n" + 
								"어느덧 헬스노트 12일차가 되었습니다.\n" + 
								"참여자님의 하루를 헬스노트에 기록해주세요!",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								""),
	
	smb2SendHealthNoteLink13(	"smb2SendHealthNoteLink13" , 
								"시즌2_헬스노트13일차", 
								"[스마일바이오미]\n" + 
								"매일 작성하는 헬스노트를 되돌아보면 참여자님의 생활습관을 알 수 있어요.\n" + 
								"오늘은 어떤 하루를 보내고 계시나요? 헬스노트에 남겨주세요:)",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								""),
	
	smb2SendHealthNoteLink14(	"smb2SendHealthNoteLink14" , 
								"시즌2_헬스노트14일차", 
								"[스마일바이오미]\n" + 
								"헬스노트 마지막날입니다.\n\n" + 
								"시민과학프로젝트를 위한 헬스노트는 오늘이 마지막이지만, \n" + 
								"헬스노트는 계속 작성할 수 있어요. \n" + 
								"나의 하루를 꾸준히 작성해보세요!\n\n" + 
								"■ 오늘부터 3일 이내에 2차 키트를 등록하고, \n" + 
								"키트 등록 당일날 채변해주세요.",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								""),
	
	smb2NotRegKitType3(			"smb2NotRegKitType3" , 
								"2차 키트 미등록자_7일", 
								"[스마일바이오미]\n" + 
								"2차 키트를 발송해주세요!\n\n" + 
								"2주 동안의 프로바이오틱스 섭취가 끝나면 채변한 키트를 발송해주셔야 합니다. \n" + 
								"키트를 발송하지 않으면, 프로젝트가 자동 종료 되오니 꼭 키트를 발송해주시기 바랍니다.\n" + 
								"채변 전에 키트 등록하는 것도 잊지 마세요!",
								"키트 등록 바로가기",
								"https://www.smilebiome.com/smilebiome/kit/kit_registration_CTZ02.do",
								"https://www.smilebiome.com/smilebiome/kit/kit_registration_CTZ02.do"),

	smb2SendReportComplete(		"smb2SendReportComplete" , 
								"시즌2_리포트활성화", 
								"[스마일바이오미]\n" + 
								"기다려 주셔서 감사합니다^^\n\n" + 
								"참여하신 시민과학프로젝트 시즌2 분석이 완료되었습니다.\n" + 
								"나의 장내 미생물 생태계를 확인할 수 있는 리포트를 지금 확인하세요!",
								"리포트 바로가기",
								"https://www.smilebiome.com/",
								"https://www.smilebiome.com/"),

	smb2NotRegKitEnd(			"smb2NotRegKitEnd" , 
								"시즌2_프로젝트_종료_미충족", 
								"[스마일바이오미]\n" + 
								"시민과학프로젝트가 자동 종료되었습니다.\n\n" + 
								"참여하신 시민과학프로젝트는 IRB 승인을 받은 연구로, 연구계획대로 진행되지 않을 경우 자동 종료됩니다. \n" + 
								"이점 양해 부탁 드립니다.",
								"",
								"",
								""),
	
	
	smb2NotRegKitType1(			"smb2NotRegKitType1" , 
								"1차_키트미등록자_4일", 
								"[스마일바이오미]\n" + 
								"키트를 등록해주세요.\n\n" + 
								"배송 받은 키트의 채변통에는 고유의 키트번호가 적혀 있습니다.\n" + 
								"사이트에서 키트번호를 등록해야만 참여자 고유의 번호로 분석이 진행 되오니, \n" + 
								"반드시 키트 등록 후 채변하시기 바랍니다^^",
								"키트 등록 바로가기",
								"https://www.smilebiome.com/smilebiome/kit/kit_registration_CTZ02.do",
								"https://www.smilebiome.com/smilebiome/kit/kit_registration_CTZ02.do"),
	
	smb2NotRegKitType2(			"smb2NotRegKitType2" , 
								"1차_키트미등록자_14일", 
								"[스마일바이오미]\n" + 
								"키트를 발송해주세요!\n\n" + 
								"시민과학프로젝트 참여자는 키트를 등록한 후, \n" + 
								"채변한 키트를 발송해주셔야 합니다.\n\n" + 
								"배송 완료일로부터 30일 안에 채변한 키트를 발송하지 않으면, \n" + 
								"프로젝트가 자동 종료 되오니 이점 양해 부탁드립니다.",
								"키트 등록 바로가기",
								"https://www.smilebiome.com/smilebiome/kit/kit_registration_CTZ02.do",
								"https://www.smilebiome.com/smilebiome/kit/kit_registration_CTZ02.do"),
	
	// 2019.07.16 카카오 알림톡 버전2 추가
	
	smb2CTZJoin_Ver2(			"smb2CTZJoin_Ver2" , 
								"프로그램_참여승인시", 
								"[스마일바이오미]\n" + 
								"안녕하세요.\n" + 
								"시민과학프로젝트 시즌2에 참여해주셔서 감사합니다.\n\n" + 
								"시민과학 키트와 프로바이오틱스는 공휴일 제외 3~5일 내에 배송될 예정입니다. 키트를 수령한 후 설명서에 따라 키트를 등록하고 당일에 채변해주세요:)\n\n" + 
								"- 프로바이오틱스 안내-\n" + 
								"■ 냉장보관하세요.\n" + 
								"■ 채변 후에 1일 1정 섭취하세요.",
								"스마일바이오미 바로가기",
								"https://www.smilebiome.com/",
								"https://www.smilebiome.com/"),
	
	smb2SendHealthNoteLink_Ver2("smb2SendHealthNoteLink_Ver2" , 
								"시즌2_헬스노트1일차_Ver2", 
								"[스마일바이오미]\n" + 
								"키트를 등록해 주셔서 감사합니다.\n\n" + 
								"■ 키트 등록 당일인 오늘 채변을 한 후, 천랩에서 제공하는 프로바이오틱스를 섭취하세요.\n" + 
								"■ 프로바이오틱스는 냉장 보관하시고 1일 1정 섭취하세요.\n" + 
								"■ 프로젝트 기간(14일)동안 헬스노트를 잊지 말고 작성해주세요.\n" + 
								"■ 헬스노트에 기록한 프로바이오틱스 섭취 횟수가 5회 이상인 분에게만 2차 키트를 보내드립니다.\n\n" + 
								"헬스노트에 기록되는 모든 정보는 참여자의 건강과 장내 미생물 생태계의 균형을 알아보는 중요한 지표로 사용됩니다. \n" + 
								"헬스노트의 프로바이오틱스 섭취횟수가 5회 미만일 경우 프로젝트가 자동 종료되오니 양해 부탁드립니다.\n\n" + 
								"시민과학프로젝트를 꾸준히 실천하여 장내 미생물 생태계를 잘 가꾸시길 응원합니다!",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								""),
	
	smb2SendHealthNoteLink7Type5("smb2SendHealthNoteLink7Type5" , 
								"시즌2_헬스노트7일차_3건미만_Ver2", 
								"[스마일바이오미]\n" + 
								"헬스노트 작성을 잊으셨나요?\n\n" + 
								"참여 중인 시민과학프로젝트의 정확한 분석을 위해 헬스노트를 꼭! 작성해주세요.\n" + 
								"헬스노트의 프로바이오틱스 섭취 횟수가 5회 미만일 경우, 정확한 분석이 불가하다고 판단하여 프로젝트가 자동 종료됩니다. \n\n" + 
								"헬스노트를 잊지 말고 작성해주세요!",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								""),
	
	smb2SendHealthNoteLink10Type5("smb2SendHealthNoteLink10Type5" , 
								"시즌2_헬스노트10일차_5건미만_Ver2", 
								"[스마일바이오미]\n" + 
								"헬스노트 작성할 시간이에요:)\n\n" + 
								"헬스노트의 프로바이오틱스 섭취 횟수가 5회 이상인 참여자에게만 2차 키트가 배송됩니다.\n" + 
								"잊지 말고 작성해주세요!",
								"헬스노트 바로가기",
								"https://www.smilebiome.com/smilebiome/main/healthnote_link.do?code=",
								"");
	
	private String id;
	private String desc;
	private String msg;
	private String buttonName;
	private String buttonLinkMobile;
	private String buttonLinkPC;
	

	private EnumKakaoSendTemplate(String id, String desc, String msg, String buttonName, String buttonLinkMobile,
			String buttonLinkPC) {
		this.id = id;
		this.desc = desc;
		this.msg = msg;
		this.buttonName = buttonName;
		this.buttonLinkMobile = buttonLinkMobile;
		this.buttonLinkPC = buttonLinkPC;
	}
	
	
	public String getButtonLinkMobile() {
		return buttonLinkMobile;
	}

	public String getButtonLinkPC() {
		return buttonLinkPC;
	}

	public String getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public String getMsg() {
		return msg;
	}

	public String getButtonName() {
		return buttonName;
	}
}

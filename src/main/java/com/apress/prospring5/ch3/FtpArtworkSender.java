package com.apress.prospring5.ch3;

/*
 * 3-64. ArtworkSender 인터페이스로부터 다양한 구현체를 만들 수 있음.
 * XML 구성 외에 애노테이션을 사용해 컬렉션을 주입할 수도 있지만,
 * 유지보수를 쉽게 하려면 컬렉션 값을 외부 구성 파일로 관리하는 것이 좋다. (app-context-annotation.xml)
 */
public class FtpArtworkSender implements ArtworkSender {

	@Override
	public void SendArtwork(String artworkPath, Recipient recipient) {
		//ftp 로직을 여기에 구현 
	}

	@Override
	public String getFirendlyName() {
		return "파일 전송 프로토콜";
	}

	@Override
	public String getShortName() {
		return "ftp";
	}

	
	
}

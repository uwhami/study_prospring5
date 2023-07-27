package com.apress.prospring5.ch3;

public interface ArtworkSender {
	void SendArtwork(String artworkPath, Recipient recipient);
	String getFirendlyName();
	String getShortName();
}

package com.suyin.weChat.model.response;
/**
 * 响应消息之音乐消息
 * @author madara
 *
 */
public class MusicResponseMessage extends BaseResponseMessage {
	private static final long serialVersionUID = 5673893515544137145L;

	private Music Music;
	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
	
}

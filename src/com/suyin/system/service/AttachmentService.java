package com.suyin.system.service;

import java.util.List;

import com.suyin.system.model.Attachment;

public interface AttachmentService {
	
	public Integer addAttachment(Attachment attachment);
	
	public Integer deleteAttachment(Attachment attachment);
	
	//public Integer updateAttachment(Attachment attachment);
	
	public List<Attachment> findAttachment(Attachment attachment);
	
	
	public Attachment getAttachment(Attachment attachment);
}

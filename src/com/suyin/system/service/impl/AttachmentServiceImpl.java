package com.suyin.system.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.AttachmentMapper;
import com.suyin.system.model.Attachment;
import com.suyin.system.service.AttachmentService;

@Transactional
@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {
	
	@Autowired
	AttachmentMapper attachmentMapper;
	
	@Override
	public Integer addAttachment(Attachment attachment) {
		return attachmentMapper.addAttachment(attachment);
	}

	@Override
	public Integer deleteAttachment(Attachment attachment) {
		 attachment=this.getAttachment(attachment);
		 
		 //附件删除是文件删除
         File file = new File(attachment.getFile_path());  
         // 路径为文件且不为空则进行删除  
         if (file.isFile() && file.exists()) {  
             file.delete();  
         } 
         return attachmentMapper.deleteAttachment(attachment);
	}

	@Override
	public List<Attachment> findAttachment(Attachment attachment) {
		// TODO Auto-generated method stub
		return attachmentMapper.findAttachment(attachment);
	}

	@Override
	public Attachment getAttachment(Attachment attachment) {
		if(!this.findAttachment(attachment).isEmpty() && 1==this.findAttachment(attachment).size()){
			return this.findAttachment(attachment).get(0);
		}
		return null;
	}

}

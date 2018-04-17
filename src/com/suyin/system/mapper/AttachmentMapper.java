package com.suyin.system.mapper;

import java.util.List;

import com.suyin.system.model.Attachment;


public interface AttachmentMapper {

    public Integer addAttachment(Attachment attachment);

    public Integer deleteAttachment(Attachment attachment);

    //public Integer updateAttachment(Attachment attachment);

    public List<Attachment> findAttachment(Attachment attachment);
    
    public List<Attachment> findAttachmentByExpId(Attachment attachment);

    public Integer addAttachments(List<Attachment> attachment);
}

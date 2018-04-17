package com.suyin.about.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.about.mapper.AboutMapper;
import com.suyin.about.model.About;
import com.suyin.about.service.AboutService;
import com.suyin.system.mapper.AttachmentMapper;
import com.suyin.system.model.Attachment;

@Transactional
@Service("aboutService")
public class AboutServiceImpl implements AboutService {

	private final static Logger log = Logger.getLogger(AboutServiceImpl.class);

	@Autowired
	private AboutMapper aboutMapper;

	@Autowired
	private AttachmentMapper attachmentMapper;

	/**
	 * 修改信息 
	 * @param entity
	 * @return
	 */
	@Override
	public Integer updateAbout(About entity) {

		Integer result = 0;
		try {
			if (entity == null) {
				return result;
			} else {
				result = aboutMapper.updateAbout(entity);
				if (entity.getAttachments().size() > 0) {
					for (Attachment a : entity.getAttachments())
						a.setEntity(entity.getAboutId());
					this.attachmentMapper.addAttachments(entity.getAttachments());
				}
			}
		} catch (Exception e) {

			log.error("Service Error AboutServiceImpl ->updateAbout " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e);

		}
		return result;

	}

	/**
	 * 新增信息
	 * @param entity
	 * @return
	 */
	@Override
	public Integer addAbout(About entity) {
		// TODO Auto-generated method stub
		Integer result = 0;
		try {
			if (entity == null) {
				return result;
			} else {
				result = aboutMapper.addAbout(entity);
				if (entity.getAttachments().size() > 0) {
					for (Attachment a : entity.getAttachments())
						a.setEntity(entity.getAboutId());
					this.attachmentMapper.addAttachments(entity.getAttachments());
				}
			}
		} catch (Exception e) {
			log.error("Service Error AboutServiceImpl -> addAbout " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 根据id查询对应的信息
	 * @param entity
	 * @return
	 */
	@Override
	public About findAboutById(About entity) {
		return aboutMapper.findAboutById(entity);
	}
	
	/**
	 * 分页查询信息
	 * @param entity
	 * @return
	 */
	@Override
	public List<About> findAboutBypage(About entity) {
		// TODO Auto-generated method stub
		return aboutMapper.findAboutByPage(entity);
	}
	
	/**
	 * 删除信息
	 * @param entity
	 * @return
	 */
	@Override
	public Integer deleteAbout(String id) {
		// TODO Auto-generated method stub
		return aboutMapper.deleteAbout(id);
	}
	
   /**
    * 
    */
	@Override
	public List<About> findAboutByType(About entity) {
		// TODO Auto-generated method stub
		return aboutMapper.findAboutByType(entity);
	}

@Override
public Integer deleteAboutByBatch(String[] id) {
	// TODO Auto-generated method stub
	return aboutMapper.deleteAboutByBatch(id);
}
}

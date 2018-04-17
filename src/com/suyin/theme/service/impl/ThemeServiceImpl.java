package com.suyin.theme.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suyin.theme.mapper.ThemeMapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.AttachmentMapper;
import com.suyin.system.model.Attachment;
import com.suyin.system.util.Md5Util;

import java.util.*;

import com.suyin.theme.model.*;
import com.suyin.theme.service.*;

@Transactional
@Service("ThemeService")
public class ThemeServiceImpl implements ThemeService {

	private final static Logger log = Logger.getLogger(ThemeServiceImpl.class);

	@Autowired
	private ThemeMapper ThemeMapper;

	@Autowired
	private AttachmentMapper attachmentMapper;

	/**
	 * 新增信息
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public Integer addTheme(Theme entity) {
		Integer result = 0;
		try {
			if (entity == null) {
				return result;
			} else {
				result = ThemeMapper.addTheme(entity);
				if (entity.getAttachments().size() > 0) {
					for (Attachment a : entity.getAttachments())
						a.setEntity(entity.getId());
					this.attachmentMapper.addAttachments(entity.getAttachments());
				}
			}
		} catch (Exception e) {
			log.error("Theme信息修改异常" + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;

	}

	/**
	 * 修改信息
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public Integer updateTheme(Theme entity) {

		Integer result = 0;
		try {
			if (entity == null) {
				return result;
			} else {
				result = ThemeMapper.updateTheme(entity);
				if (entity.getAttachments().size() > 0) {
					for (Attachment a : entity.getAttachments())
						a.setEntity(entity.getId());
					this.attachmentMapper.addAttachments(entity.getAttachments());
				}
			}
		} catch (Exception e) {

			log.error("Theme信息修改异常" + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e);

		}
		return result;

	}

	/**
	 * 删除信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Integer deleteTheme(String id) {
		return ThemeMapper.deleteTheme(id);
	}

	/**
	 * 批量删除信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Integer deleteThemeByBatch(String[] ids) {
		return ThemeMapper.deleteThemeByBatch(ids);
	}

	/**
	 * 查找信息列表
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public List<Theme> findTheme(Theme entity) {

		return ThemeMapper.findThemeList(entity);
	}

	/**
	 * 查找信息列表(分页)
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public List<Theme> findThemeByPage(Theme entity) {

		return ThemeMapper.findThemeByPage(entity);
	}

	/**
	 * 根据id查询对应的信息
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public Theme findThemeById(Theme entity) {
		return ThemeMapper.findThemeById(entity);
	}
}

package com.happyge.empl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcFile;
import com.happyge.empl.repository.FileMapper;
import com.happyge.empl.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;
    @Override
    public void savefile(HllcFile hllcFile) {
        this.fileMapper.savefile(hllcFile);
    }

    @Override
    public PageInfo<HllcFile> listfile(int curPage, int pageNum) {
        PageHelper.startPage(curPage, pageNum);
        List<HllcFile> blogs = fileMapper.listfile();
        PageInfo<HllcFile> pageInfo = new PageInfo<HllcFile>(blogs);
        return pageInfo;
    }
}

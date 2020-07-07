package com.happyge.empl.service;


import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcFile;

import java.util.List;

public interface FileService {
    void savefile(HllcFile hllcFile);

    PageInfo<HllcFile> listfile(int curPage, int pageNum);
}

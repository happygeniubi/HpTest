package com.happyge.empl.repository;



import com.happyge.empl.model.HllcFile;

import java.util.List;

public interface FileMapper {
    void savefile(HllcFile hllcFile);

    List<HllcFile> listfile();

}

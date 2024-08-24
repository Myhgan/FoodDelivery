package com.demo.food.service.imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileServiceImp {
    Boolean savefile(MultipartFile file);
    Resource loadFile(String fileName);
}

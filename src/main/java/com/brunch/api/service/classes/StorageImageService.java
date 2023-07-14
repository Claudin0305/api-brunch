package com.brunch.api.service.classes;

import com.brunch.api.entity.Event;
import com.brunch.api.entity.ImageData;
import com.brunch.api.repository.StorageImageRepository;
import com.brunch.api.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageImageService {
    @Autowired
    private StorageImageRepository storageImageRepository;
    public String uploadImage(MultipartFile file, Event event) throws IOException {

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
       ImageData imageData =  storageImageRepository.save(ImageData.builder()
                .name(fileName)
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                        .isActive(true)
                        .event__(event)
                .build()
        );
       if(imageData != null){
           return "image uploaded";
       }
       return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = storageImageRepository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    public List<ImageData> findAll(){
        return storageImageRepository.findAll();
    }

    public ImageData download(String name){
        return storageImageRepository.findByName(name).orElse(null);
    }
}

package com.brunch.api.service.classes;

import com.brunch.api.entity.Event;
import com.brunch.api.entity.ImageData;
import com.brunch.api.repository.StorageImageRepository;
import com.brunch.api.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public List<ImageData> findAll() {
        Sort sort = Sort.by("createdAt").descending();
        return storageImageRepository.findAll(sort);
    }

    public ImageData createFile(ImageData fileEntity) {

        fileEntity.setActive(true);
        return storageImageRepository.save(fileEntity);
    }

    public ImageData findByName(String name) {
        return storageImageRepository.findByName(name);
    }

    public ImageData findById(Long id) {
        return storageImageRepository.findById(id).orElse(null);
    }

    public ImageData updateFile(Long id, ImageData toUpdate) {
        ImageData fileEntity = findById(id);
        if (fileEntity == null) {
            return null;
        }
        fileEntity.setName(toUpdate.getName());
        fileEntity.setType(toUpdate.getType());
        return storageImageRepository.save(fileEntity);
    }



    public void deleteFile(Long id) {
        storageImageRepository.deleteById(id);
    }
//    public String uploadImage(MultipartFile file, Event event) throws IOException {
//
//        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//       ImageData imageData =  storageImageRepository.save(ImageData.builder()
//                .name(fileName)
//                .type(file.getContentType())
//                        .isActive(true)
//                        .event__(event)
//                .build()
//        );
//       if(imageData != null){
//           return "image uploaded";
//       }
//       return null;
//    }
//
//    public byte[] downloadImage(String fileName){
//        Optional<ImageData> dbImageData = storageImageRepository.findByName(fileName);
//        return images;
//    }
//
//    public List<ImageData> findAll(){
//        return storageImageRepository.findAll();
//    }
//
//    public ImageData download(String name){
//        return storageImageRepository.findByName(name).orElse(null);
//    }
}

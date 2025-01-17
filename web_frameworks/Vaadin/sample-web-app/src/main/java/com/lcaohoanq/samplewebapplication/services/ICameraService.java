package com.lcaohoanq.samplewebapplication.services;

import com.lcaohoanq.samplewebapplication.dtos.CameraDTO;
import com.lcaohoanq.samplewebapplication.models.Camera;
import java.util.List;

public interface ICameraService {

    List<Camera> findAll();
    Camera save(Camera camera);
    void delete(Long id);
    Camera findById(Long id);
    Camera update(Long id, CameraDTO cameraDTO);
}

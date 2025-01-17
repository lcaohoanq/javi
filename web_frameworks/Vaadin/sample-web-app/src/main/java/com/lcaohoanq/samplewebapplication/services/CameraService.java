package com.lcaohoanq.samplewebapplication.services;

import com.lcaohoanq.samplewebapplication.dtos.CameraDTO;
import com.lcaohoanq.samplewebapplication.models.Camera;
import com.lcaohoanq.samplewebapplication.repositories.CameraRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CameraService implements ICameraService {

    private final CameraRepository repository;

    public List<Camera> findAll() {
        return repository.findAll();
    }

    public Camera save(Camera camera) {
        // Add any business logic here
        log.info("Saving camera: {}", camera);
        return repository.save(camera);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Camera findById(Long id) {
        if(repository.findById(id).isEmpty()) {
            log.error("Camera not found");
            throw new RuntimeException("Camera not found");
        }

        return repository.findById(id).get();
    }

    @Override
    public Camera update(Long id, CameraDTO cameraDTO) {
        Camera camera = findById(id);
        camera.setBrand(cameraDTO.brand());
        camera.setModel(cameraDTO.model());
        camera.setDescription(cameraDTO.description());
        camera.setThumbnail(cameraDTO.thumbnail());
        camera.setPrice(cameraDTO.price());
        log.info("Updating camera: {}", camera);
        return repository.save(camera);
    }
}


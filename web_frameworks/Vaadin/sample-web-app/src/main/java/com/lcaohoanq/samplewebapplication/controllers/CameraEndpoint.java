package com.lcaohoanq.samplewebapplication.controllers;

import com.lcaohoanq.samplewebapplication.dtos.CameraDTO;
import com.lcaohoanq.samplewebapplication.models.Camera;
import com.lcaohoanq.samplewebapplication.services.ICameraService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;
import com.vaadin.hilla.Nonnull;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Endpoint
@AnonymousAllowed
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cameras")
public class CameraEndpoint {
    private final ICameraService cameraService;

    @GetMapping("")
    public List<Camera> findAll() {
        return cameraService.findAll();
    }

    @PostMapping("")
    public Camera save(
      @Valid @RequestBody @Nonnull Camera camera) {
        return cameraService.save(camera);
    }

    @DeleteMapping("/{id}")
    public void delete(
      @PathVariable @Nonnull Long id) {
        cameraService.delete(id);
    }

    @GetMapping("/{id}")
    public Camera findById(
      @PathVariable @Nonnull Long id) {
        return cameraService.findById(id);
    }

    @PutMapping("/{id}")
    public Camera update(
      @PathVariable @Nonnull Long id,
      @Valid @RequestBody @Nonnull CameraDTO cameraDTO) {
        return cameraService.update(id, cameraDTO);
    }

}

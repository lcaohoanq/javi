package com.lcaohoanq.samplewebapplication.repositories;

import com.lcaohoanq.samplewebapplication.models.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera, Long> {
}

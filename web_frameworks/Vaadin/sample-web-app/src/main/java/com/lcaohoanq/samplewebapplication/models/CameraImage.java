package com.lcaohoanq.samplewebapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "camera_images")
@Builder
public class CameraImage {

  public static final int MAXIMUM_IMAGES_PER_PRODUCT = 6;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "camera_id")
  @JsonIgnore
  private Camera camera;

  @Column(name = "image_url", length = 300)
  @JsonProperty("image_url")
  private String imageUrl;

  @Column(name = "video_url", length = 500)
  @JsonProperty("video_url")
  private String videoUrl;

}


package com.guess.api;

import com.guess.controller.ImagesApi;
import com.guess.model.Image;
import com.guess.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

import static com.guess.util.HeaderUtil.createPaginationHeaders;
import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
public class ImagesApiController implements ImagesApi {

    private final ImageService imageService;

    @Override
    public ResponseEntity<List<Image>> getAllImages(
            @Min(1) @Valid @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @Min(10) @Max(30) @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        final Page<Image> imagesPage = imageService.getAllImages(page, size);
        final HttpHeaders headers = createPaginationHeaders(imagesPage, getAllImagesPath);
        return new ResponseEntity<>(imagesPage.getContent(), headers, OK);
    }

    @Override
    public ResponseEntity<Image> getImage(@PathVariable("imageId") UUID imageId) {
        final Image image = imageService.getImage(imageId);
        return new ResponseEntity<>(image, OK);
    }

    @Override
    public ResponseEntity<Image> uploadImage(@Valid @RequestPart("file") MultipartFile file) {
        final Image image = imageService.uploadImage(file);
        return new ResponseEntity<>(image, CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteImage(@PathVariable("imageId") UUID imageId) {
        imageService.deleteImage(imageId);
        return new ResponseEntity<>(NO_CONTENT);
    }

}

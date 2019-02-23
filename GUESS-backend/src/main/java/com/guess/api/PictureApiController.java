package com.guess.api;

import com.guess.controller.PictureApi;
import com.guess.model.Picture;
import lombok.AllArgsConstructor;
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

@RestController
@AllArgsConstructor
public class PictureApiController implements PictureApi {

    @Override
    public ResponseEntity<List<Picture>> getPictures(
            @Min(1) @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page,
            @Min(10) @Max(30) @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size) {

        return null;
    }

    @Override
    public ResponseEntity<Picture> getPicture(@PathVariable("pictureId") UUID pictureId) {

        return null;
    }

    @Override
    public ResponseEntity<Picture> uploadPicture(@Valid @RequestPart("file") MultipartFile file) {

        return null;
    }

    @Override
    public ResponseEntity<Void> deletePicture(@PathVariable("pictureId") UUID pictureId) {

        return null;
    }

}

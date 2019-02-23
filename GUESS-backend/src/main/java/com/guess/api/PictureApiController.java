package com.guess.api;

import com.guess.controller.PictureApi;
import com.guess.model.Picture;
import com.guess.service.PictureService;
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
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
public class PictureApiController implements PictureApi {

    private final PictureService pictureService;

    @Override
    public ResponseEntity<List<Picture>> getPictures(
            @Min(1) @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page,
            @Min(10) @Max(30) @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size) {

        final Page<Picture> picturesPage = pictureService.getPictures(page, size);
        final HttpHeaders headers = createPaginationHeaders(picturesPage, getPicturesPath);
        return new ResponseEntity<>(picturesPage.getContent(), headers, OK);
    }

    @Override
    public ResponseEntity<Picture> getPicture(@PathVariable("pictureId") UUID pictureId) {

        final Picture picture = pictureService.getPicture(pictureId);
        return new ResponseEntity<>(picture, OK);
    }

    @Override
    public ResponseEntity<Picture> uploadPicture(@Valid @RequestPart("file") MultipartFile file) {

        final Picture picture = pictureService.uploadPicture(file);
        return new ResponseEntity<>(picture, CREATED);
    }

    @Override
    public ResponseEntity<Void> deletePicture(@PathVariable("pictureId") UUID pictureId) {

        pictureService.deletePicture(pictureId);
        return new ResponseEntity<>(NO_CONTENT);
    }

}

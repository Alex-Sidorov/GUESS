package com.guess.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import static com.guess.util.enums.HttpHeader.X_TOTAL_COUNT;
import static org.springframework.http.HttpHeaders.LINK;

@UtilityClass
public class HeaderUtil {

    public static <T> HttpHeaders createPaginationHeaders(Page<T> page, String methodPath) {
        return buildHeaders(page, UriComponentsBuilder.fromUriString(methodPath).toUriString());
    }

    public static <T> HttpHeaders createPaginationHeaders(Page<T> page, String methodPath, String parameters) {

        return buildHeaders(page, UriComponentsBuilder.fromUriString(methodPath + parameters).toUriString());
    }

    private static <T> HttpHeaders buildHeaders(Page<T> page, String baseUri) {
        String link = "<" + buildPageUri(baseUri, 1, page.getSize()) + ">; rel=\"first\", ";
        if (page.hasPrevious()) {
            link += "<" + buildPageUri(baseUri, page.getNumber(), page.getSize()) + ">; rel=\"prev\", ";
        }
        if (page.hasNext()) {
            link += "<" + buildPageUri(baseUri, page.getNumber() + 2, page.getSize()) + ">; rel=\"next\", ";
        }
        int lastPage = page.getTotalPages() == 0 ? 1 : page.getTotalPages();
        link += "<" + buildPageUri(baseUri, lastPage, page.getSize()) + ">; rel=\"last\"";

        HttpHeaders headers = new HttpHeaders();
        headers.add(LINK, link);
        headers.add(X_TOTAL_COUNT.toString(), Long.toString(page.getTotalElements()));
        return headers;
    }

    private static String buildPageUri(String baseUri, int page, int size) {
        return UriComponentsBuilder.fromUriString(baseUri)
                .queryParam("page", page)
                .queryParam("size", size)
                .toUriString();
    }

}

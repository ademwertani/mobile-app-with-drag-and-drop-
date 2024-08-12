package com.kian.dragdropflutter_back.data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@Data
public class FlutterUI {

    @NotBlank(message = "homePageRoute is mandatory")
    private final String homePageRoute;
    private final String loginRoute;

    @NotNull(message = "pages is mandatory")
    @Valid
    private final List<AppPage> pages;
}

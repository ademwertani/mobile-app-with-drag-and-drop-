package com.kian.dragdropflutter_back.Controller;

import com.kian.dragdropflutter_back.data.AppPage;
import com.kian.dragdropflutter_back.data.FlutterUI;
import com.kian.dragdropflutter_back.services.FlutterCommandService;
import com.kian.dragdropflutter_back.services.GitService;
import com.kian.dragdropflutter_back.services.InjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static java.lang.System.out;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Slf4j
public class BuildAngAppController {
    private final GitService gitService;
    private final InjectService injectService;
    private final FlutterCommandService flutterCommandService;

    @PostMapping("/runBuild2")
    @ResponseStatus(HttpStatus.OK)
    protected void runBuild(@Valid @RequestBody FlutterUI flutterAngApplication
                            ,@RequestParam String email ) throws Exception {

        String dataTime = UUID.randomUUID().toString();
        String cloneResult = gitService.cloneRepository(dataTime);
        log.info(cloneResult);
        int pageIndex = 0;

        for (AppPage pageAng : flutterAngApplication.getPages()) {
            String pageDirectoryName = "page" + pageIndex;
            pageIndex++;
            String path = "src/main/flutter/";
            try {
                // Assuming `copyDirectory` method exists in `GitService` to handle the directory copying
                String result = gitService.copyDirectory(path + dataTime + "/lib/modules/template_module", pageDirectoryName, dataTime);

                log.info("Page directory cloned: " + result);
            } catch (Exception e) {
                log.error("Error cloning page directory for page " + pageDirectoryName, e);
            }
            int x = pageIndex - 1;
            injectService.searchCommentInFlutterProject("AppContextState<GeneratedPage> {", path + dataTime + "/lib/modules/page" + x, pageAng, x);
            injectService.searchCommentInFlutterProject("PAGE_UI", path + dataTime + "/lib/modules/page" + x, pageAng, x);
            injectService.searchCommentInFlutterProject("IMPORT_PATH", path + dataTime + "/lib/config/routes", pageAng, x);
            injectService.searchCommentInFlutterProject("DEFINE_ROUTES", path + dataTime + "/lib/config/routes", pageAng, x);
            InjectService.injectIndex(x, dataTime, pageAng.getRoute());
            InjectService.injectRoute(dataTime, flutterAngApplication.getHomePageRoute(), flutterAngApplication.getLoginRoute());
            log.info(pageAng.generateCodeToInject());
        }
        // Call buildApk with the dynamic email
        flutterCommandService.buildApk(email);
    }
}
